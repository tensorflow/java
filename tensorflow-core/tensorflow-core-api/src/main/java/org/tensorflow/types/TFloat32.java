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
import org.tensorflow.SparseTensor;
import org.tensorflow.Tensor;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.types.TFloat32Mapper;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.annotation.TensorType;
import org.tensorflow.types.family.TFloating;

/** IEEE-754 single-precision 32-bit float tensor type. */
@TensorType(dataType = DataType.DT_FLOAT, byteSize = 4, mapperClass = TFloat32Mapper.class)
public interface TFloat32 extends FloatNdArray, TFloating {

  /**
   * Allocates a new tensor for storing a single float value.
   *
   * @param value float to store in the new tensor
   * @return the new tensor
   */
  static TFloat32 scalarOf(float value) {
    return Tensor.of(TFloat32.class, Shape.scalar(), data -> data.setFloat(value));
  }

  /**
   * Allocates a new tensor for storing a vector of floats.
   *
   * @param values floats to store in the new tensor
   * @return the new tensor
   */
  static TFloat32 vectorOf(float... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensor.of(
        TFloat32.class, Shape.of(values.length), data -> StdArrays.copyTo(values, data));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of floats.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TFloat32 tensorOf(NdArray<Float> src) {
    return Tensor.of(TFloat32.class, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TFloat32 tensorOf(Shape shape) {
    return Tensor.of(TFloat32.class, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of floats to initialize the tensor with
   * @return the new tensor
   */
  static TFloat32 tensorOf(Shape shape, FloatDataBuffer data) {
    return Tensor.of(TFloat32.class, shape, d -> d.write(data));
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param dataInit tensor data initializer
   * @return the new tensor
   * @throws TensorFlowException if the tensor cannot be allocated or initialized
   */
  static TFloat32 tensorOf(Shape shape, Consumer<TFloat32> dataInit) {
    return Tensor.of(TFloat32.class, shape, dataInit);
  }

  /**
   * Create a sparse tensors from {@code indices}, {@code values} and {@code denseShape} dense
   * tensors, with a default value of zero.
   *
   * <p>The returned instance also implements the {@link SparseTensor SparseTensor<TFloat32>}
   * interface, allowing a user to access directly the dense tensors when needed.
   *
   * @param indices A 2-D tensor of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse tensor that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D tensor of shape {@code [N]}, which supplies the values for each element in
   *     indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18f, 3.8f]} specifies that element {@code [1,3,1]} of the sparse tensor has a value
   *     of {@code 18f}, and element {@code [2,4,0]} of the tensor has a value of {@code 3.8f}.
   * @param denseShape A 1-D tensor of shape {@code [ndims]} where each the value at index {@code i}
   *     represents the size of dimension {@code i} in a dense version of that tensor.
   * @return the new sparse tensor
   * @see SparseTensor for more details on sparse tensors and how to release their memory properly
   */
  static TFloat32 sparseTensorOf(TInt64 indices, TFloat32 values, TInt64 denseShape) {
    return SparseTensor.of(indices, values, denseShape).asTypedTensor();
  }
}
