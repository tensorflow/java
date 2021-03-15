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
package org.tensorflow.op.generator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.internal.types.registry.TensorTypeRegistry;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.AttrValue;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.OpDef;
import org.tensorflow.proto.framework.OpDef.ArgDef;
import org.tensorflow.proto.framework.OpDef.AttrDef;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * A utility class to handle type calculations for a {@link ClassGenerator}.  Should be one to one with {@link
 * ClassGenerator} instances.
 */
final class TypeResolver {

  static TypeName WILDCARD = WildcardTypeName.subtypeOf(TypeName.OBJECT);
  static TypeName STRING = TypeName.get(java.lang.String.class);

  /**
   * Get the {@link TType} type for a datatype, or {@code ? extends TType} if there isn't one.
   */
  static TypeName forDataType(DataType dataType) {
    try {
      return TypeName.get(TensorTypeRegistry.find(dataType).type());
    } catch (IllegalArgumentException ignored) {
      return WildcardTypeName.subtypeOf(TypeName.get(TType.class));
    }
  }

  /**
   * The op def to get types for.
   */
  private final OpDef op;

  /**
   * Known types.  Not simply a cache.
   */
  private Map<String, ResolvedType> known = new HashMap<>();

  /**
   * Attributes that were reached while getting the types of inputs.
   *
   * These are excluded from factory methods.
   */
  private Set<String> reachedFromInput = new HashSet<>();

  /**
   * The processed argument types.
   */
  private final Map<ArgDef, ResolvedType> argTypes = new HashMap<>();

  TypeResolver(OpDef op) {
    this.op = op;
    calculateArgTypes();
  }

  /**
   * Calculate the types of the arguments.
   * <p>
   * Removes extraneous generics and wraps in Operand/Output.
   */
  private void calculateArgTypes() {
    Map<String, Integer> genericCounts = new HashMap<>();

    ArrayList<ArgDef> args = new ArrayList<>(op.getInputArgCount() + op.getOutputArgCount());
    Set<ArgDef> outputs = new HashSet<>(op.getOutputArgList());
    args.addAll(op.getInputArgList());
    args.addAll(op.getOutputArgList());

    for (ArgDef arg : args) {
      ResolvedType type = calculateTypeOf(arg);
      argTypes.put(arg, type);
      if (type.javaType instanceof TypeVariableName) {
        String name = ((TypeVariableName) type.javaType).name;

        // can't remove output types, as the class will depend on them.
        int incr = outputs.contains(arg) ? 2 : 1;

        genericCounts.put(name, genericCounts.getOrDefault(name, 0) + incr);
      }
    }

    for (Map.Entry<ArgDef, ResolvedType> entry : argTypes.entrySet()) {
      if (entry.getValue().javaType instanceof TypeVariableName) {
        TypeVariableName type = (TypeVariableName) entry.getValue().javaType;
        if (genericCounts.get(type.name) <= 1 && type.bounds.size() == 1) {
          entry.setValue(new ResolvedType(WildcardTypeName.subtypeOf(type.bounds.get(0)), entry.getValue().iterable));
        }
      }
      ClassName baseClass;
      if (outputs.contains(entry.getKey())) {
        baseClass = ClassName.get(Output.class);
      } else {
        baseClass = ClassName.get(Operand.class);
      }

      entry.setValue(
          new ResolvedType(ParameterizedTypeName.get(baseClass, entry.getValue().javaType), entry.getValue().iterable));
    }

  }

  /**
   * Returns true if the attribute with name {@code attrName} was reached while calculating input types.
   */
  boolean partOfInput(String attrName) {
    return reachedFromInput.contains(attrName);
  }

  private char nextGenericLetter = 'T';

  /**
   * Get a new generic letter.
   */
  private TypeVariableName nextGeneric() {
    char letter = nextGenericLetter++;
    if (nextGenericLetter > 'Z') {
      nextGenericLetter = 'A';
    }
    return TypeVariableName.get(String.valueOf(letter));
  }

  /**
   * Data types that are real numbers.
   */
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

  /**
   * Returns true if the attribute is a real number type or is limited to real number types.
   */
  private boolean isRealNumberTyped(AttrValue value) {
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

  /**
   * Get the family {@link TType} of an attribute, i.e. {@link TNumber}
   */
  private TypeName typeFamily(AttrDef attr) {
    if (isRealNumberTyped(attr.getAllowedValues())) {
      return TypeName.get(TNumber.class);
    }

    return TypeName.get(TType.class);
  }

  /**
   * Get the type of an attribute.
   */
  ResolvedType typeOf(AttrDef attr) {
    return typeOf(attr, false);
  }

  /**
   * Get the type of an attribute
   * @param fromInput whether we're calculating input types and should add this attr to {@link #reachedFromInput}
   */
  private ResolvedType typeOf(AttrDef attr, boolean fromInput) {
    if (known.containsKey(attr.getName())) {
      return known.get(attr.getName());
    }

    boolean iterable = false;
    String typeName = attr.getType();
    if (typeName.startsWith("list(")) {
      iterable = true;
      typeName = typeName.substring(5, typeName.length() - 1);
    }

    ResolvedType types;

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
    known.put(attr.getName(), types);
    if (fromInput) {
      reachedFromInput.add(attr.getName());
    }
    return types;
  }

  /**
   * Get the type of an argument (calculated in the constructor)
   */
  ResolvedType typeOf(ArgDef arg) {
    return argTypes.get(arg);
  }

  /**
   * Calculate the type of an argument.  Should only be used when creating {@link #argTypes}.
   *
   * Will add to {@link #reachedFromInput} if {@code arg} is an input of the op.
   */
  private ResolvedType calculateTypeOf(ArgDef arg) {

    boolean isInput = op.getInputArgList().contains(arg);

    ResolvedType type = new ResolvedType(WILDCARD);

    if (arg.getType() != DataType.DT_INVALID) {
      type = new ResolvedType(forDataType(arg.getType()));
    } else if (!arg.getTypeAttr().isEmpty()) {
      String typeAttr = arg.getTypeAttr();
      if (known.containsKey(typeAttr)) {
        type = known.get(typeAttr);
      } else {
        AttrDef attr = op.getAttrList().stream().filter(x -> x.getName().equals(typeAttr)).findFirst().get();
        type = typeOf(attr, isInput);
      }
    } else if (!arg.getTypeListAttr().isEmpty()) {
      type = type.withIterable(true);
      if (isInput) {
        reachedFromInput.add(arg.getTypeListAttr());
      }
    } else {
      throw new IllegalArgumentException(
          "Can't resolve type of argument " + arg.getName() + " in operation " + op.getName());
    }

    if (!arg.getNumberAttr().isEmpty()) {
      type = type.withIterable(true);
      if (isInput) {
        reachedFromInput.add(arg.getNumberAttr());
      }
      known.put(arg.getNumberAttr(), new ResolvedType(TypeName.LONG));
    }

    return type;
  }

}
