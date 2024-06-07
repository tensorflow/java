/*
 *  Copyright 2022-2024 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.SparseTensor;
import org.tensorflow.Tensor;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.types.TUint16Mapper;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.ShortNdArray;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.annotation.TensorType;
import org.tensorflow.types.family.TIntegral;

/** 16-bit unsigned integer tensor type. */
@TensorType(dataType = DataType.DT_UINT16, byteSize = 2, mapperClass = TUint16Mapper.class)
public interface TUint16 extends ShortNdArray, TIntegral {

  /**
   * Allocates a new tensor for storing a single short value.
   *
   * @param value short to store in the new tensor
   * @return the new tensor
   */
  static TUint16 scalarOf(short value) {
    return Tensor.of(TUint16.class, Shape.scalar(), data -> data.setShort(value));
  }

  /**
   * Allocates a new tensor for storing a vector of shorts.
   *
   * @param values short to store in the new tensor
   * @return the new tensor
   */
  static TUint16 vectorOf(short... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensor.of(
        TUint16.class, Shape.of(values.length), data -> StdArrays.copyTo(values, data));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of shorts.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TUint16 tensorOf(NdArray<Short> src) {
    return Tensor.of(TUint16.class, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TUint16 tensorOf(Shape shape) {
    return Tensor.of(TUint16.class, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of shorts to initialize the tensor with
   * @return the new tensor
   */
  static TUint16 tensorOf(Shape shape, ShortDataBuffer data) {
    return Tensor.of(TUint16.class, shape, d -> d.copyFrom(data));
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param dataInit tensor data initializer
   * @return the new tensor
   * @throws TensorFlowException if the tensor cannot be allocated or initialized
   */
  static TUint16 tensorOf(Shape shape, Consumer<TUint16> dataInit) {
    return Tensor.of(TUint16.class, shape, dataInit);
  }

  /**
   * Create a sparse tensor from {@code indices}, {@code values} and {@code denseShape} dense
   * tensors, with a default value of zero.
   *
   * <p>The returned instance also implements the {@link SparseTensor}
   * interface, allowing a user to access directly the dense tensors when needed.
   *
   * @param indices A 2-D tensor of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse tensor that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D tensor of shape {@code [N]}, which supplies the values for each element in
   *     indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3]} specifies that element {@code [1,3,1]} of the sparse tensor has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the tensor has a value of {@code 3}.
   * @param denseShape A 1-D tensor of shape {@code [ndims]} where each the value at index {@code i}
   *     represents the size of dimension {@code i} in a dense version of that tensor.
   * @return the new sparse tensor
   * @see SparseTensor for more details on sparse tensors and how to release their memory properly
   */
  static TUint16 sparseTensorOf(TInt64 indices, TUint16 values, TInt64 denseShape) {
    return SparseTensor.of(indices, values, denseShape).asTypedTensor();
  }
}
