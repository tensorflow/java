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
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.ndarray.ByteNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.types.family.TNumber;

/**
 * 8-bit unsigned integer tensor type.
 */
public interface TUint8 extends ByteNdArray, TNumber {

  /** Type metadata */
  DataType<TUint8> DTYPE = DataType.create("UINT8", 4, 1, TUint8Impl::mapTensor);

  /**
   * Allocates a new tensor for storing a single byte value.
   *
   * @param value byte to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TUint8> scalarOf(byte value) {
    return Tensor.allocate(DTYPE, Shape.scalar(), data -> data.setByte(value));
  }

  /**
   * Allocates a new tensor for storing a vector of bytes.
   *
   * @param values bytes to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TUint8> vectorOf(byte... values) {
    return Tensor.allocate(DTYPE, Shape.make(values.length), data -> data.write(values));
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TUint8> ofShape(Shape shape) {
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
  static Tensor<TUint8> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of bytes.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static Tensor<TUint8> copyOf(NdArray<Byte> src) {
    return Tensor.allocate(DTYPE, src.shape(), src::copyTo);
  }
}

/**
 * Hidden implementation of a {@code TUint8}
 */
class TUint8Impl extends ByteDenseNdArray implements TUint8 {

  static TUint8 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TUint8Impl(TensorBuffers.toBytes(nativeTensor), shape);
  }

  private TUint8Impl(ByteDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}
