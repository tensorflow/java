/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.types;

import java.util.function.Consumer;
import org.tensorflow.DataType;
import org.tensorflow.RawTensor;
import org.tensorflow.Tensor;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.types.TInt64Mapper;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.types.family.TNumber;

/** 64-bit signed integer tensor type. */
public interface TInt64 extends LongNdArray, TNumber {

  /** readable-name for the data type */
  static final String NAME = "INT64";

  /** Type metadata */
  DataType<TInt64> DTYPE = DataType.create(NAME, 9, 8, new TInt64Mapper());

  /**
   * Allocates a new tensor for storing a single long value.
   *
   * @param value long to store in the new tensor
   * @return the new tensor
   */
  static TInt64 scalarOf(long value) {
    return Tensor.of(DTYPE, Shape.scalar(), data -> data.setLong(value));
  }

  /**
   * Allocates a new tensor for storing a vector of longs.
   *
   * @param values longs to store in the new tensor
   * @return the new tensor
   */
  static TInt64 vectorOf(long... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensor.of(DTYPE, Shape.of(values.length), data -> StdArrays.copyTo(values, data));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of longs.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TInt64 tensorOf(NdArray<Long> src) {
    return Tensor.of(DTYPE, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TInt64 tensorOf(Shape shape) {
    return Tensor.of(DTYPE, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of longs to initialize the tensor with
   * @return the new tensor
   */
  static TInt64 tensorOf(Shape shape, LongDataBuffer data) {
    return Tensor.of(DTYPE, shape, d -> d.write(data));
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param dataInit tensor data initializer
   * @return the new tensor
   * @throws TensorFlowException if the tensor cannot be allocated or initialized
   */
  static TInt64 tensorOf(Shape shape, Consumer<TInt64> dataInit) {
    return Tensor.of(DTYPE, shape, dataInit);
  }
}
