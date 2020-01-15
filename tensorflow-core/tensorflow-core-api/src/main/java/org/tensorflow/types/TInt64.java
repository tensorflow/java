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

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.ndarray.LongNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.types.family.TNumber;

/**
 * 64-bit signed integer tensor type.
 */
public interface TInt64 extends LongNdArray, TNumber {

  /** Type metadata */
  DataType<TInt64> DTYPE = DataType.create("INT64", 9, 8, TInt64Impl::mapTensor);

  /**
   * Allocates a new tensor for storing a single long value.
   *
   * @param value long to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TInt64> scalarOf(long value) {
    return Tensor.allocate(DTYPE, Shape.scalar(), data -> data.setLong(value));
  }

  /**
   * Allocates a new tensor for storing a vector of longs.
   *
   * @param values longs to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TInt64> vectorOf(long... values) {
    return Tensor.allocate(DTYPE, Shape.make(values.length), data -> data.write(values));
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TInt64> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * <p>Invoking {@code ofShape(x, y, z)} is equivalent to {@code ofShape(Shape.make(x, y, z))}
   *
   * @param dimensionSizes dimension sizes that defines the shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TInt64> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of longs.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static Tensor<TInt64> copyOf(NdArray<Long> src) {
    return Tensor.allocate(DTYPE, src.shape(), src::copyTo);
  }
}

/**
 * Hidden implementation of a {@code TInt64}
 */
class TInt64Impl extends LongDenseNdArray implements TInt64 {

  static TInt64 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TInt64Impl(TensorBuffers.toLongs(nativeTensor), shape);
  }

  private TInt64Impl(LongDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}
