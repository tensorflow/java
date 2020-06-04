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

import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.family.TType;

/** Represents a type of elements in a {@link Tensor} */
public final class DataType<T extends TType> {

  @FunctionalInterface
  public interface TensorMapper<T> {

    /**
     * Maps tensor memory to a data structure for manipulating elements of this type.
     *
     * @param nativeTensor pointer to the native tensor
     * @param shape the shape of the tensor
     * @return data structure of elements of this type
     */
    T apply(TF_Tensor nativeTensor, Shape shape);
  }

  /**
   * Creates a new datatype
   *
   * @param name readable-name for this type
   * @param value must match the corresponding TF_* value in the TensorFlow C API.
   * @param byteSize size of an element of this type, in bytes, -1 if unknown
   * @param tensorMapper method for mapping tensor memory to elements of this type
   */
  public static <T extends TType> DataType<T> create(String name, int value, int byteSize, TensorMapper<T> tensorMapper) {
    return new DataType<>(name, value, byteSize, tensorMapper);
  }

  /**
   * Returns the size of an element of this type, in bytes, or -1 if element size is variable.
   */
  public int byteSize() {
    return byteSize;
  }

  /**
   * Returns true if this datatype has elements of variable length
   */
  public boolean isVariableLength() {
    return byteSize == -1;
  }

  /**
   * Returns a readable name for this type
   */
  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return name + " (" + nativeCode + ")";
  }

  /**
   * Returns the numeric code for this datatype, as recognized by the native library (C API)
   */
  int nativeCode() {
    return nativeCode;
  }

  /**
   * Maps a tensor to a data structure for manipulating elements of this type.
   *
   * @param tensor tensor to map
   * @return data structure of elements of this type
   */
  T map(Tensor<T> tensor) {
    return tensorMapper.apply(tensor.nativeHandle(), tensor.shape());
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
