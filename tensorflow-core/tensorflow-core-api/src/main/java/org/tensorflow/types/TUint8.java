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
import org.tensorflow.internal.types.TUint8Mapper;
import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.types.family.TNumber;

/** 8-bit unsigned integer tensor type. */
public interface TUint8 extends ByteNdArray, TNumber {

  /** readable-name for the data type */
  static final String NAME = "UINT8";

  /** Type metadata */
  DataType<TUint8> DTYPE = DataType.create(NAME, 4, 1, new TUint8Mapper());

  /**
   * Allocates a new tensor for storing a single byte value.
   *
   * @param value byte to store in the new tensor
   * @return the new tensor
   */
  static TUint8 scalarOf(byte value) {
    return Tensor.of(DTYPE, Shape.scalar(), data -> data.setByte(value));
  }

  /**
   * Allocates a new tensor for storing a vector of bytes.
   *
   * @param values bytes to store in the new tensor
   * @return the new tensor
   */
  static TUint8 vectorOf(byte... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensor.of(DTYPE, Shape.of(values.length), data -> StdArrays.copyTo(values, data));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of bytes.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TUint8 tensorOf(NdArray<Byte> src) {
    return Tensor.of(DTYPE, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TUint8 tensorOf(Shape shape) {
    return Tensor.of(DTYPE, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of bytes to initialize the tensor with
   * @return the new tensor
   */
  static TUint8 tensorOf(Shape shape, ByteDataBuffer data) {
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
  static TUint8 tensorOf(Shape shape, Consumer<TUint8> dataInit) {
    return Tensor.of(DTYPE, shape, dataInit);
  }
}
