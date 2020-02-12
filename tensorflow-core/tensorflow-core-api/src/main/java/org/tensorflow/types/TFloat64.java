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
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.ndarray.DoubleNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.StdArrays;
import org.tensorflow.tools.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.types.family.TNumber;

/**
 * IEEE-754 double-precision 64-bit float tensor type.
 */
public interface TFloat64 extends DoubleNdArray, TNumber {

  /** Type metadata */
  DataType<TFloat64> DTYPE = DataType.create("DOUBLE", 2, 8, TFloat64Impl::mapTensor);

  /**
   * Allocates a new tensor for storing a single double value.
   *
   * @param value double to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat64> scalarOf(double value) {
    return Tensor.of(DTYPE, Shape.scalar(), data -> data.setDouble(value));
  }

  /**
   * Allocates a new tensor for storing a vector of doubles.
   *
   * @param values doubles to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat64> vectorOf(double... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensor.of(DTYPE, Shape.of(values.length), data -> StdArrays.copyTo(data, values));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of doubles.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat64> tensorOf(NdArray<Double> src) {
    return Tensor.of(DTYPE, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TFloat64> tensorOf(Shape shape) {
    return Tensor.of(DTYPE, shape);
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param dataInit tensor data initializer
   * @return the new tensor
   * @throws org.tensorflow.TensorFlowException if the tensor cannot be allocated or initialized
   */
  static Tensor<TFloat64> tensorOf(Shape shape, Consumer<TFloat64> dataInit) {
    return Tensor.of(DTYPE, shape, dataInit);
  }
}

/**
 * Hidden implementation of a {@code TFloat64}
 */
class TFloat64Impl extends DoubleDenseNdArray implements TFloat64 {

  static TFloat64 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TFloat64Impl(TensorBuffers.toDoubles(nativeTensor), shape);
  }

  private TFloat64Impl(DoubleDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}
