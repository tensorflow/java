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
import org.tensorflow.Tensors;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.tensor.IntTensorImpl;
import org.tensorflow.internal.tensor.buffer.TensorBuffers;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.tensor.IntTensor;
import org.tensorflow.types.family.TNumber;

/**
 * 32-bit signed integer tensor type.
 */
public interface TInt32 extends IntTensor, TNumber<TInt32, Integer> {

  /** readable-name for the data type */
  static final String NAME = "INT32";

  /** Type metadata */
  DataType<TInt32> DTYPE = DataType.create(NAME, 3, 4, TInt32Impl::new);

  /**
   * Allocates a new tensor for storing a single int value.
   *
   * @param value int to store in the new tensor
   * @return the new tensor
   */
  static TInt32 scalarOf(int value) {
    return Tensors.of(DTYPE, Shape.scalar(), t -> t.setInt(value));
  }

  /**
   * Allocates a new tensor for storing a vector of ints.
   *
   * @param values ints to store in the new tensor
   * @return the new tensor
   * @throws IllegalArgumentException if no values are provided
   */
  static TInt32 vectorOf(int... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensors.of(DTYPE, Shape.of(values.length), t -> StdArrays.copyTo(values, t));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of ints.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TInt32 tensorOf(NdArray<Integer> src) {
    return Tensors.of(DTYPE, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TInt32 tensorOf(Shape shape) {
    return Tensors.of(DTYPE, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of ints to initialize the tensor with
   * @return the new tensor
   */
  static TInt32 tensorOf(Shape shape, IntDataBuffer data) {
    return Tensors.of(DTYPE, shape, t -> t.write(data));
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param tensorInit tensor data initializer
   * @return the new tensor
   */
  static TInt32 tensorOf(Shape shape, Consumer<TInt32> tensorInit) {
    return Tensors.of(DTYPE, shape, tensorInit);
  }
}

/**
 * Hidden implementation of a {@code TInt32}
 */
class TInt32Impl extends IntTensorImpl implements TInt32 {

  TInt32Impl(TF_Tensor nativeTensor, Shape shape) {
    super(nativeTensor, DTYPE, shape, TensorBuffers.toInts(nativeTensor));
  }
}
