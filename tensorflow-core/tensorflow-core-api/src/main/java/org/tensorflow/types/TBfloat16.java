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

import java.util.function.Consumer;
import org.tensorflow.Tensor;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.types.TBfloat16Mapper;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.annotation.TensorType;
import org.tensorflow.types.family.TFloating;

/**
 * Brain 16-bit float tensor type.
 *
 * <p>This type differs from {@link TFloat16} as it truncates the mantissa of a 32-bit float and
 * preserve all exponent bits for faster conversion, while the latter shrink the exponent and have a
 * longer mantissa for more precision.
 *
 * <p>Since there is no floating-point type that fits in 16 bits in Java, a conversion (with
 * potentially a precision loss) is required for each 32 bits value written or read on a tensor of
 * this type from the JVM. Therefore, if a lot of I/O operations are to be expected on a tensor,
 * performances will be improved by working with {@link TFloat32} or {@link TFloat64} data types
 * whenever possible.
 *
 * <p>Note that some CPUs support the bfloat16 format natively, which can result in faster
 * computation compared to {@link TFloat16} when GPUs are not used.
 */
@TensorType(dataType = DataType.DT_BFLOAT16, byteSize = 2, mapperClass = TBfloat16Mapper.class)
public interface TBfloat16 extends FloatNdArray, TFloating {

  /**
   * Allocates a new tensor for storing a single float value.
   *
   * @param value float to store in the new tensor
   * @return the new tensor
   */
  static TBfloat16 scalarOf(float value) {
    return Tensor.of(TBfloat16.class, Shape.scalar(), data -> data.setFloat(value));
  }

  /**
   * Allocates a new tensor for storing a vector of floats.
   *
   * @param values floats to store in the new tensor
   * @return the new tensor
   */
  static TBfloat16 vectorOf(float... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensor.of(TBfloat16.class, Shape.of(values.length), data -> StdArrays.copyTo(values, data));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of floats.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TBfloat16 tensorOf(NdArray<Float> src) {
    return Tensor.of(TBfloat16.class, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TBfloat16 tensorOf(Shape shape) {
    return Tensor.of(TBfloat16.class, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of floats to initialize the tensor with
   * @return the new tensor
   */
  static TBfloat16 tensorOf(Shape shape, FloatDataBuffer data) {
    return Tensor.of(TBfloat16.class, shape, d -> d.write(data));
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param dataInit tensor data initializer
   * @return the new tensor
   * @throws TensorFlowException if the tensor cannot be allocated or initialized
   */
  static TBfloat16 tensorOf(Shape shape, Consumer<TBfloat16> dataInit) {
    return Tensor.of(TBfloat16.class, shape, dataInit);
  }
}

