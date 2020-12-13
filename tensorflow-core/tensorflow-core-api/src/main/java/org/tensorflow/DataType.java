/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/** Represents a type of elements in a {@link Tensor} */
public final class DataType<T extends TType> {

  /**
   * Creates a new datatype
   *
   * @param name readable-name for this type
   * @param value must match the corresponding TF_* value in the TensorFlow C API.
   * @param byteSize size of an element of this type, in bytes, -1 if unknown
   * @param <T> a tensor type
   * @param tensorMapper method for mapping tensor memory to elements of this type
   */
  public static <T extends TType> DataType<T> create(
      String name, int value, int byteSize, TensorMapper<T> tensorMapper) {
    return new DataType<>(name, value, byteSize, tensorMapper);
  }

  /**
   * Gets the DataType associated with the readable-name for the type
   * <p>The name must match exactly the name used to create the desired DataType</p>
   *
   * @param name readable-name for the type
   * @return the DataType
   * @throws java.lang.IllegalArgumentException if the name is not a valid data type name
   * @throws java.lang.NullPointerException if name is null
   */
  public static DataType<? extends TType> of(String name) {
    switch (name) {
      case TBfloat16.NAME:
        return TBfloat16.DTYPE;
      case TFloat16.NAME:
        return TFloat16.DTYPE;
      case TFloat32.NAME:
        return TFloat32.DTYPE;
      case TFloat64.NAME:
        return TFloat64.DTYPE;
      case TUint8.NAME:
        return TUint8.DTYPE;
      case TInt32.NAME:
        return TInt32.DTYPE;
      case TInt64.NAME:
        return TInt64.DTYPE;
      case TBool.NAME:
        return TBool.DTYPE;
      case TString.NAME:
        return TString.DTYPE;
      default:
        throw new IllegalArgumentException(String.format("%s is an unknown DataType", name));
    }
  }

  /** Returns true if this data type represents a floating point type */
  public boolean isFloating() {
    switch (this.name()) {
      case TBfloat16.NAME:
      case TFloat16.NAME:
      case TFloat32.NAME:
      case TFloat64.NAME:
        return true;
      default:
        return false;
    }
  }

  /** Returns true if this data type represents an integer type */
  public boolean isInteger() {
    switch (this.name()) {
      case TInt32.NAME:
      case TInt64.NAME:
      case TUint8.NAME:
        return true;
      default:
        return false;
    }
  }

  /** Returns true if this data type represents a numeric type */
  public boolean isNumeric() {
    return isFloating() || isInteger();
  }

  /** Returns true if this data type represents a boolean type */
  public boolean isBoolean() {
    return this.name().equals(TBool.NAME);
  }

  /** Returns true if this data type represents a string type */
  public boolean isString() {
    return this.name().equals(TString.NAME);
  }

  /** Returns the size of an element of this type, in bytes, or -1 if element size is variable. */
  public int byteSize() {
    return byteSize;
  }

  /** Returns true if this datatype has elements of variable length */
  public boolean isVariableLength() {
    return byteSize == -1;
  }

  /** Returns a readable name for this type */
  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return name + " (" + nativeCode + ")";
  }

  /** Returns the numeric code for this datatype, as recognized by the native library (C API) */
  int nativeCode() {
    return nativeCode;
  }

  /**
   * Maps a raw tensor to a typed tensor.
   *
   * @param tensor tensor to map
   * @return data structure of elements of this type
   */
  T map(RawTensor tensor) {
    return tensorMapper.mapDense(tensor);
  }

  private final int nativeCode;
  private final int byteSize;
  private final String name;
  private final TensorMapper<T> tensorMapper;

  private DataType(String name, int nativeCode, int byteSize, TensorMapper<T> tensorMapper) {
    this.name = name;
    this.nativeCode = nativeCode;
    this.byteSize = byteSize;
    this.tensorMapper = tensorMapper;
  }
}
