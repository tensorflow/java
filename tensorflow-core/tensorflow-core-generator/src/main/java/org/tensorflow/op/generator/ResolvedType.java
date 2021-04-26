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
import java.util.StringJoiner;
import org.tensorflow.Names;

/**
 * Holds type information for inputs, outputs, or attributes, and provides utilities.
 */
final class ResolvedType {

  /**
   * The java level type.
   */
  final TypeName javaType;

  /**
   * The type for jni/attribute setting use.
   */
  final TypeName jniType;

  /**
   * The type of the attribute, for accessors, if supported.
   */
  final AttributeType attributeType;

  /**
   * Whether this type should be made iterable when used.
   *
   * <p>See {@link #arrayIfIterable()}, {@link #listIfIterable()}, {@link #iterableIfIterable()}.
   */
  final boolean iterable;

  ResolvedType(TypeName javaType, TypeName jniType, AttributeType attributeType, boolean iterable) {
    if (javaType == null) {
      throw new NullPointerException("Can't create with a null javaType");
    }
    if (jniType == null) {
      throw new NullPointerException("Can't create with a null jniType");
    }

    this.javaType = javaType;
    this.jniType = jniType;
    this.iterable = iterable;
    this.attributeType = attributeType;
  }

  ResolvedType(TypeName javaType, TypeName jniType, boolean iterable) {
    this(javaType, jniType, null, iterable);
  }

  ResolvedType(TypeName javaType, TypeName jniType, AttributeType attributeType) {
    this(javaType, jniType, attributeType, false);
  }

  ResolvedType(TypeName javaType, TypeName jniType) {
    this(javaType, jniType, null, false);
  }

  ResolvedType(TypeName type, AttributeType attributeType, boolean iterable) {
    if (type == null) {
      throw new NullPointerException("Can't create with a null type");
    }

    if (type.isPrimitive()) {
      this.javaType = type.box();
    } else {
      this.javaType = type;
    }
    jniType = type;
    this.iterable = iterable;
    this.attributeType = attributeType;
  }

  ResolvedType(TypeName type, boolean iterable) {
    this(type, (AttributeType) null, iterable);
  }

  ResolvedType(TypeName type, AttributeType attributeType) {
    this(type, attributeType, false);
  }

  ResolvedType(TypeName type) {
    this(type, false);
  }

  /**
   * Returns a copy of this type with the specified {@code iterable} value.
   */
  ResolvedType withIterable(boolean iterable) {
    return new ResolvedType(javaType, jniType, attributeType, iterable);
  }

  /**
   * Get the unboxed version of {@code javaType} if it is a boxed primitive.
   */
  TypeName unboxed() {
    if (javaType.isBoxedPrimitive()) {
      return javaType.unbox();
    } else {
      return javaType;
    }
  }

  /**
   * Return a copy, wrapping {@code javaType} in an array if this type is iterable.
   */
  ResolvedType arrayIfIterable() {
    TypeName newJType;
    if (iterable) {
      newJType = ArrayTypeName.of(javaType);
    } else {
      newJType = javaType;
    }
    return new ResolvedType(newJType, jniType, attributeType, iterable);
  }

  /**
   * Return a copy, wrapping {@code javaType} in {@link Iterable} if this type is iterable.
   */
  ResolvedType iterableIfIterable() {
    TypeName newJType;
    if (iterable) {
      newJType = ParameterizedTypeName.get(ClassName.get(Iterable.class), javaType);
    } else {
      newJType = javaType;
    }
    return new ResolvedType(newJType, jniType, attributeType, iterable);
  }

  /**
   * Return a copy, wrapping {@code javaType} in {@link List} if this type is iterable.
   */
  ResolvedType listIfIterable() {
    TypeName newJType;
    if (iterable) {
      newJType = ParameterizedTypeName.get(ClassName.get(List.class), javaType);
    } else {
      newJType = javaType;
    }
    return new ResolvedType(newJType, jniType, attributeType, iterable);
  }

  /**
   * True if wrapping will be done by {@link #classIfGeneric()}
   */
  boolean shouldWrapInClass() {
    return javaType instanceof TypeVariableName || javaType instanceof WildcardTypeName;
  }

  /**
   * Return a copy, wrapping {@code javaType} in {@link Class} if it is a single type variable or a
   * wildcard.
   */
  ResolvedType classIfGeneric() {
    TypeName newJType;
    if (javaType instanceof TypeVariableName || javaType instanceof WildcardTypeName) {
      newJType = ParameterizedTypeName.get(ClassName.get(Class.class), javaType);
    } else {
      newJType = javaType;
    }
    return new ResolvedType(newJType, jniType, attributeType, iterable);
  }

  /**
   * Recursively get all type variable names in {@code javaType}.
   */
  Set<TypeVariableName> findGenerics() {
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
   * Return the type argument if {@code javaType} is {@code Operand} or {@code Output}, or return
   * {@code javaType}.
   */
  TypeName unwrapArg() {
    if (javaType instanceof ParameterizedTypeName) {
      ParameterizedTypeName pType = (ParameterizedTypeName) javaType;
      if (pType.rawType.equals(Names.Operand) || pType.rawType.equals(Names.Output)) {
        return pType.typeArguments.get(0);
      }
    }
    return javaType;
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
    return iterable == that.iterable
        && Objects.equals(javaType, that.javaType)
        && Objects.equals(jniType, that.jniType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(javaType, jniType, iterable);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ResolvedType.class.getSimpleName() + "(", ")")
        .add("javaType=" + javaType)
        .add("jniType=" + jniType)
        .add("iterable=" + iterable)
        .toString();
  }
}
