/*
  Copyright 2021 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ==============================================================================
 */
package org.tensorflow;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.tensorflow.internal.types.registry.TensorTypeRegistry;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.AttrValue;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.OpDef;
import org.tensorflow.proto.framework.OpDef.ArgDef;
import org.tensorflow.proto.framework.OpDef.AttrDef;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

public class TypeResolver {

  public static TypeName WILDCARD = WildcardTypeName.subtypeOf(TypeName.OBJECT);
  public static TypeName STRING = TypeName.get(java.lang.String.class);

  public static TypeName forDataType(DataType dataType) {
    try {
      return TypeName.get(TensorTypeRegistry.find(dataType).type());
    } catch (IllegalArgumentException ignored) {
      return TypeName.get(TType.class);
    }
  }

  private final OpDef op;

  private Map<String, TypeName> known = new HashMap<>();

  public TypeResolver(OpDef op) {
    this.op = op;
  }

  public boolean visited(String attrName) {
    return known.containsKey(attrName);
  }

  public static class ResolvedType {

    public final TypeName javaType;
    public final TypeName jniType;
    public final boolean iterable;

    public ResolvedType(TypeName javaType, TypeName jniType, boolean iterable) {
      this.javaType = javaType;
      this.jniType = jniType;
      this.iterable = iterable;
    }

    public ResolvedType(TypeName javaType, TypeName jniType) {
      this(javaType, jniType, false);
    }

    public ResolvedType(TypeName type, boolean iterable) {
      if (type.isPrimitive()) {
        this.javaType = type.box();
        jniType = type;
      } else {
        this.javaType = type;
        this.jniType = type;
      }
      this.iterable = iterable;
    }

    public ResolvedType(TypeName type) {
      this(type, false);
    }

    public ResolvedType(Class<?> javaType, Class<?> jniType, boolean iterable) {
      this(TypeName.get(javaType), TypeName.get(jniType), iterable);
    }

    public ResolvedType(Class<?> javaType, Class<?> jniType) {
      this(TypeName.get(javaType), TypeName.get(jniType), false);
    }

    public ResolvedType(Class<?> type, boolean iterable) {
      this(TypeName.get(type), iterable);
    }

    public ResolvedType(Class<?> type) {
      this(type, false);
    }

    public ResolvedType withIterable(boolean iterable) {
      return new ResolvedType(javaType, jniType, iterable);
    }

    public TypeName unboxed() {
      if (javaType.isBoxedPrimitive()) {
        return javaType.unbox();
      } else {
        return javaType;
      }
    }

    public ResolvedType arrayIfIterable() {
      TypeName newJType;
      if (iterable) {
        newJType = ArrayTypeName.of(javaType);
      } else {
        newJType = javaType;
      }
      return new ResolvedType(newJType, jniType, iterable);
    }

    public ResolvedType listIfIterable() {
      TypeName newJType;
      if (iterable) {
        newJType = ParameterizedTypeName.get(ClassName.get(List.class), javaType);
      } else {
        newJType = javaType;
      }
      return new ResolvedType(newJType, jniType, iterable);
    }

    public ResolvedType classIfGeneric() {
      TypeName newJType;
      if (javaType instanceof TypeVariableName) {
        newJType = ParameterizedTypeName.get(ClassName.get(Class.class), javaType);
      } else {
        newJType = javaType;
      }
      return new ResolvedType(newJType, jniType, iterable);
    }

  }

  private char nextGenericLetter = 'T';

  public TypeVariableName nextGeneric() {
    char letter = nextGenericLetter++;
    if (nextGenericLetter > 'Z') {
      nextGenericLetter = 'A';
    }
    return TypeVariableName.get(String.valueOf(letter));
  }

  private static Set<DataType> realNumberTypes = new HashSet<>();

  static {
    realNumberTypes.add(DataType.DT_FLOAT);
    realNumberTypes.add(DataType.DT_DOUBLE);
    realNumberTypes.add(DataType.DT_INT32);
    realNumberTypes.add(DataType.DT_UINT8);
    realNumberTypes.add(DataType.DT_INT16);
    realNumberTypes.add(DataType.DT_INT8);
    realNumberTypes.add(DataType.DT_INT64);
    realNumberTypes.add(DataType.DT_QINT8);
    realNumberTypes.add(DataType.DT_QUINT8);
    realNumberTypes.add(DataType.DT_QINT32);
    realNumberTypes.add(DataType.DT_BFLOAT16);
    realNumberTypes.add(DataType.DT_QINT16);
    realNumberTypes.add(DataType.DT_QUINT16);
    realNumberTypes.add(DataType.DT_UINT16);
    realNumberTypes.add(DataType.DT_HALF);
    realNumberTypes.add(DataType.DT_UINT32);
    realNumberTypes.add(DataType.DT_UINT64);
  }

  public boolean isRealNumberTyped(AttrValue value) {
    if (!value.hasList()) {
      return realNumberTypes.contains(value.getType());
    }
    for (DataType d : value.getList().getTypeList()) {
      if (!realNumberTypes.contains(d)) {
        return false;
      }
    }
    return true;
  }

  public TypeName typeFamily(AttrDef attr) {
    if (isRealNumberTyped(attr.getAllowedValues())) {
      return TypeName.get(TNumber.class);
    }

    return TypeName.get(TType.class);
  }

  public ResolvedType typesOf(AttrDef attr) {
    return typesOf(attr, false);
  }

  private ResolvedType typesOf(AttrDef attr, boolean fromInput) {
    boolean iterable = false;
    String typeName = attr.getType();
    if (typeName.startsWith("list(")) {
      iterable = true;
      typeName = typeName.substring(5, typeName.length() - 1);
    }

    ResolvedType types = new ResolvedType(WILDCARD);

    switch (typeName) {
      case "string":
        types = new ResolvedType(STRING);
        break;
      case "int":
        types = new ResolvedType(TypeName.LONG);
        break;
      case "float":
        types = new ResolvedType(TypeName.FLOAT);
        break;
      case "bool":
        types = new ResolvedType(TypeName.BOOLEAN);
        break;
      case "shape":
        types = new ResolvedType(Shape.class);
        break;
      case "tensor":
        types = new ResolvedType(Tensor.class);
        break;
      case "type":
        TypeName family = typeFamily(attr);
        TypeName type = iterable ? WildcardTypeName.subtypeOf(family) : nextGeneric().withBounds(family);
        types = new ResolvedType(type, TypeName.get(DataType.class));
        break;
      case "func":
        types = new ResolvedType(ConcreteFunction.class);
        break;
      default:
        throw new IllegalArgumentException("No Java type for " + typeName);
    }

    types = types.withIterable(iterable);
    if (fromInput) {
      known.put(attr.getName(), types.javaType);
    }
    return types;
  }

  public ResolvedType typeOf(ArgDef arg) {
    boolean iterable = false;

    TypeName type = WILDCARD;

    if (arg.getType() != DataType.DT_INVALID) {
      type = forDataType(arg.getType());
    } else if (!arg.getTypeAttr().isEmpty()) {
      String typeAttr = arg.getTypeAttr();
      if (known.containsKey(typeAttr)) {
        type = known.get(typeAttr);
      } else {
        AttrDef attr = op.getAttrList().stream().filter(x -> x.getName().equals(typeAttr)).findFirst().get();
        ResolvedType types = typesOf(attr, op.getInputArgList().contains(arg));
        type = types.javaType;
        iterable = types.iterable;
      }
    } else if (!arg.getTypeListAttr().isEmpty()) {
      iterable = true;
      known.put(arg.getTypeListAttr(), type);
    } else {
      throw new IllegalArgumentException(
          "Can't resolve type of argument " + arg.getName() + " in operation " + op.getName());
    }

    if (!arg.getNumberAttr().isEmpty()) {
      iterable = true;
      known.put(arg.getNumberAttr(), TypeName.INT);
    }

    return new ResolvedType(type, iterable);
  }

}
