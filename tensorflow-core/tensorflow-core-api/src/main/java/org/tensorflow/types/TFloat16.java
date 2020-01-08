/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.layout.DataLayouts;
import org.tensorflow.tools.ndarray.FloatNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.family.TNumber;

/**
 * IEEE-754 half-precision 16-bit float tensor type.
 *
 * <p>Since there is no floating-point type that fits in 16 bits in Java, a conversion (with potentially
 * a precision loss) is required for each 32 bits value written or read on a tensor of this type from
 * the JVM. Therefore, if a lot of I/O operations are to be expected on a tensor, performances will be
 * improved by working with {@link TFloat32} or {@link TFloat64} data types whenever possible.
 *
 * <p>Also, {@code TFloat16} tensors normally perform better if they are located in GPU memory since most
 * CPUs do not support this format natively. For CPU computation on 16-bit floats, the {@link TBFloat16}
 * tensor type might be a better option.
 */
public interface TFloat16 extends FloatNdArray, TNumber {

  /** Type metadata */
  DataType<TFloat16> DTYPE = DataType.create("FLOAT16", 19, 2, TFloat16Impl::mapTensor);

  /**
   * Allocates a new tensor for storing a single float value.
   *
   * @param value float to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> scalarOf(float value) {
    Tensor<TFloat16> t = ofShape();
    t.data().setFloat(value);
    return t;
  }

  /**
   * Allocates a new tensor for storing a vector of floats.
   *
   * @param values floats to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> vectorOf(float... values) {
    Tensor<TFloat16> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TFloat16> ofShape(Shape shape) {
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
  static Tensor<TFloat16> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of floats.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> copyOf(NdArray<Float> src) {
    Tensor<TFloat16> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

/**
 * Hidden implementation of a {@code TFloat16}
 */
class TFloat16Impl extends FloatDenseNdArray implements TFloat16 {

  static TFloat16 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TFloat16Impl(DataLayouts.FLOAT16.applyTo(TensorBuffers.toShorts(nativeTensor)), shape);
  }

  private TFloat16Impl(FloatDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}

