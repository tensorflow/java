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
import org.tensorflow.Tensors;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.tensor.DoubleTensorImpl;
import org.tensorflow.internal.tensor.buffer.TensorBuffers;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.annotation.TensorType;
import org.tensorflow.types.tensor.DoubleTensor;
import org.tensorflow.types.family.TFloating;

/**
 * IEEE-754 double-precision 64-bit float tensor type.
 */
@TensorType(dataType = DataType.DT_DOUBLE, byteSize = 8, impl = TFloat64Impl.class)
public interface TFloat64 extends DoubleTensor, TFloating<TFloat64, Double> {

  /**
   * Allocates a new tensor for storing a single double value.
   *
   * @param value double to store in the new tensor
   * @return the new tensor
   */
  static TFloat64 scalarOf(double value) {
    return Tensors.of(TFloat64.class, Shape.scalar(), t -> t.setDouble(value));
  }

  /**
   * Allocates a new tensor for storing a vector of doubles.
   *
   * @param values doubles to store in the new tensor
   * @return the new tensor
   */
  static TFloat64 vectorOf(double... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensors.of(TFloat64.class, Shape.of(values.length), t -> StdArrays.copyTo(values, t));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of doubles.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TFloat64 tensorOf(NdArray<Double> src) {
    return Tensors.of(TFloat64.class, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TFloat64 tensorOf(Shape shape) {
    return Tensors.of(TFloat64.class, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of doubles to initialize the tensor with
   * @return the new tensor
   */
  static TFloat64 tensorOf(Shape shape, DoubleDataBuffer data) {
    return Tensors.of(TFloat64.class, shape, t -> t.write(data));
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param tensorInit tensor data initializer
   * @return the new tensor
   * @throws TensorFlowException if the tensor cannot be allocated or initialized
   */
  static TFloat64 tensorOf(Shape shape, Consumer<TFloat64> tensorInit) {
    return Tensors.of(TFloat64.class, shape, tensorInit);
  }
}

/**
 * Hidden implementation of a {@code TFloat64}
 */
class TFloat64Impl extends DoubleTensorImpl implements TFloat64 {

  TFloat64Impl(TF_Tensor nativeTensor, Shape shape) {
    super(nativeTensor, shape, TensorBuffers.toDoubles(nativeTensor));
  }

  @Override
  public Class<TFloat64> type() {
    return TFloat64.class;
  }
}
