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
import org.tensorflow.Tensor;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.types.TBoolMapper;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.annotation.TensorType;
import org.tensorflow.types.family.TType;

/**
 * Boolean tensor type.
 *
 * <p>If direct memory mapping is not available in the JVM, tensors of this type might require an
 * explicit mapping between Java boolean values and byte buffers using the {@link DataLayouts#BOOL
 * BOOL} layout, which may impact I/O performances.
 */
@TensorType(dataType = DataType.DT_BOOL, byteSize = 1, mapperClass = TBoolMapper.class)
public interface TBool extends BooleanNdArray, TType {

  /**
   * Allocates a new tensor for storing a single boolean value.
   *
   * @param value boolean to store in the new tensor
   * @return the new tensor
   */
  static TBool scalarOf(boolean value) {
    return Tensor.of(TBool.class, Shape.scalar(), data -> data.setBoolean(value));
  }

  /**
   * Allocates a new tensor for storing a vector of booleans.
   *
   * @param values booleans to store in the new tensor
   * @return the new tensor
   */
  static TBool vectorOf(boolean... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensor.of(TBool.class, Shape.of(values.length), data -> StdArrays.copyTo(values, data));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of booleans.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TBool tensorOf(NdArray<Boolean> src) {
    return Tensor.of(TBool.class, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TBool tensorOf(Shape shape) {
    return Tensor.of(TBool.class, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of booleans to initialize the tensor with
   * @return the new tensor
   */
  static TBool tensorOf(Shape shape, BooleanDataBuffer data) {
    return Tensor.of(TBool.class, shape, d -> d.write(data));
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param dataInit tensor data initializer
   * @return the new tensor
   * @throws TensorFlowException if the tensor cannot be allocated or initialized
   */
  static TBool tensorOf(Shape shape, Consumer<TBool> dataInit) {
    return Tensor.of(TBool.class, shape, dataInit);
  }
}
