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
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.ndarray.FloatNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.family.TNumber;

/**
 * IEEE-754 single-precision 32-bit float tensor type.
 */
public interface TFloat32 extends FloatNdArray, TNumber {

  /** Type metadata */
  DataType<TFloat32> DTYPE = DataType.create("FLOAT", 1, 4, TFloat32Impl::mapTensor);

  /**
   * Allocates a new tensor for storing a single float value.
   *
   * @param value float to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat32> scalarOf(float value) {
    return Tensor.allocate(DTYPE, Shape.scalar(), data -> data.setFloat(value));
  }

  /**
   * Allocates a new tensor for storing a vector of floats.
   *
   * @param values floats to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat32> vectorOf(float... values) {
    return Tensor.allocate(DTYPE, Shape.make(values.length), data -> data.write(values));
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TFloat32> ofShape(Shape shape) {
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
  static Tensor<TFloat32> ofShape(long... dimensionSizes) {
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
  static Tensor<TFloat32> copyOf(NdArray<Float> src) {
    return Tensor.allocate(DTYPE, src.shape(), src::copyTo);
  }
}

/**
 * Hidden implementation of a {@code TFloat32}
 */
class TFloat32Impl extends FloatDenseNdArray implements TFloat32 {

  static TFloat32 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TFloat32Impl(TensorBuffers.toFloats(nativeTensor), shape);
  }

  private TFloat32Impl(FloatDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}

