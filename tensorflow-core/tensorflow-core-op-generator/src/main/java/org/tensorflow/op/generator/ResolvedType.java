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

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.tensorflow.Operand;
import org.tensorflow.Output;

public final class ResolvedType {

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

  /**
   * Returns a copy of this type with the specified {@code iterable} value.
   */
  public ResolvedType withIterable(boolean iterable) {
    return new ResolvedType(javaType, jniType, iterable);
  }

  /**
   * Get the unboxed version of {@code javaType} if it is a boxed primitive.
   */
  public TypeName unboxed() {
    if (javaType.isBoxedPrimitive()) {
      return javaType.unbox();
    } else {
      return javaType;
    }
  }

  /**
   * Wrap {@code javaType} in an array if this type is iterable.
   */
  public ResolvedType arrayIfIterable() {
    TypeName newJType;
    if (iterable) {
      newJType = ArrayTypeName.of(javaType);
    } else {
      newJType = javaType;
    }
    return new ResolvedType(newJType, jniType, iterable);
  }

  /**
   * Wrap {@code javaType} in {@link Iterable} if this type is iterable.
   */
  public ResolvedType iterableIfIterable() {
    TypeName newJType;
    if (iterable) {
      newJType = ParameterizedTypeName.get(ClassName.get(Iterable.class), javaType);
    } else {
      newJType = javaType;
    }
    return new ResolvedType(newJType, jniType, iterable);
  }

  /**
   * Wrap {@code javaType} in {@link List} if this type is iterable.
   */
  public ResolvedType listIfIterable() {
    TypeName newJType;
    if (iterable) {
      newJType = ParameterizedTypeName.get(ClassName.get(List.class), javaType);
    } else {
      newJType = javaType;
    }
    return new ResolvedType(newJType, jniType, iterable);
  }

  /**
   * True if wrapping will be done by {@link #classIfGeneric()}
   */
  public boolean shouldWrapInClass() {
    return javaType instanceof TypeVariableName || javaType instanceof WildcardTypeName;
  }

  /**
   * If {@code javaType} is a single type variable or a wildcard, wrap it in {@link Class}.
   */
  public ResolvedType classIfGeneric() {
    TypeName newJType;
    if (javaType instanceof TypeVariableName || javaType instanceof WildcardTypeName) {
      newJType = ParameterizedTypeName.get(ClassName.get(Class.class), javaType);
    } else {
      newJType = javaType;
    }
    return new ResolvedType(newJType, jniType, iterable);
  }

  /**
   * Recursively get all type variable names in {@code javaType}.
   */
  public Set<TypeVariableName> findGenerics() {
    if (javaType instanceof TypeVariableName) {
      return Collections.singleton((TypeVariableName) javaType);
    } else if (javaType instanceof ParameterizedTypeName) {
      Set<TypeVariableName> names = new LinkedHashSet<>();
      for (TypeName t : ((ParameterizedTypeName) javaType).typeArguments) {
        names.addAll(new ResolvedType(t).findGenerics());
      }
      return names;
    }
    return Collections.emptySet();
  }

  /**
   * Return the type argument if {@code javaType} is {@link Operand} or {@link Output}, or return {@code javaType}.
   */
  public TypeName unwrapArg(){
    if(javaType instanceof ParameterizedTypeName){
      ParameterizedTypeName pType = (ParameterizedTypeName) javaType;
      if(pType.rawType.equals(ClassName.get(Operand.class)) || pType.rawType.equals(ClassName.get(Output.class))){
        return pType.typeArguments.get(0);
      }
    }
    return javaType;
  }

  @Override
  public String toString() {
    return "ResolvedType{" + javaType.toString() + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResolvedType that = (ResolvedType) o;
    return iterable == that.iterable && Objects.equals(javaType, that.javaType) && Objects
        .equals(jniType, that.jniType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(javaType, jniType, iterable);
  }
}
