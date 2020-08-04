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

import org.checkerframework.checker.units.qual.C;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.types.family.TType;

/**
 * A statically typed multi-dimensional array whose elements are of a type described by T.
 *
 * <p>Instances of a Tensor are <b>not</b> thread-safe.
 *
 * <p><b>WARNING:</b> Resources consumed by the Tensor object <b>must</b> be explicitly freed by
 * invoking the {@link #close()} method when the object is no longer needed. For example, using a
 * try-with-resources block:
 *
 * <pre>{@code
 * try (Tensor t = Tensor.of(...)) {
 *   doSomethingWith(t);
 * }
 * }</pre>
 */
public interface Tensor<T> extends NdArray<T>, AutoCloseable {

  /**
   * Returns this Tensor object with the type {@code Tensor<U>}. This method is useful when given a
   * value of type {@code Tensor<?>}.
   *
   * @param dt any supported tensor data type
   * @param <U> a tensor type
   * @return a tensor of the requested data type
   * @throws IllegalArgumentException if the actual data type of this object does not match the type
   *     {@code U}.
   */
  <U extends TType> U expect(DataType<U> dt);

  /**
   * Release resources associated with the Tensor.
   *
   * <p><b>WARNING:</b>This must be invoked for all tensors that were not been produced by an eager
   * operation or memory will be leaked.
   *
   * <p>The Tensor object is no longer usable after {@code close} returns.
   */
  @Override
  void close();

  /** Returns the {@link DataType} of elements stored in the Tensor. */
  DataType<?> dataType();

  /** Returns the size, in bytes, of the tensor data. */
  long numBytes();

  /**
   * Returns the raw data of this tensor as a buffer of bytes.
   *
   * <p>Use this method to obtain a read-only serializable view of the tensor raw data and must be
   * used with care since there is no guard on the element boundaries. For regular input or output
   * operations, use {@link #data()}.
   *
   * @return the tensor raw data mapped to a read-only byte buffer
   * @throws IllegalStateException if the tensor has been closed
   */
  ByteDataBuffer rawData();

  @Override
  Tensor<T> set(NdArray<T> src, long... coordinates);

  @Override
  Tensor<T> setObject(T value, long... coordinates);

  @Override
  Tensor<T> copyTo(NdArray<T> dst);

  @Override
  Tensor<T> read(DataBuffer<T> dst);

  @Override
  Tensor<T> write(DataBuffer<T> src);
}



