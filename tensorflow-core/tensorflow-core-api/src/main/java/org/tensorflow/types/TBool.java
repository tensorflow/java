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
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.types.family.TType;

/**
 * Boolean tensor type.
 *
 * <p>If direct memory mapping is not available in the JVM, tensors of this type might require an
 * explicit mapping between Java boolean values and byte buffers using the {@link DataLayouts#BOOL
 * BOOL} layout, which may impact I/O performances.
 */
public interface TBool extends BooleanNdArray, TType {
  /** readable-name for the data type */
  static final String NAME = "BOOL";

  /** Type metadata */
  DataType<TBool> DTYPE = DataType.create(NAME, 10, 1, TBoolImpl::mapTensor);

  /**
   * Allocates a new tensor for storing a single boolean value.
   *
   * @param value boolean to store in the new tensor
   * @return the new tensor
   */
  static TBool scalarOf(boolean value) {
    return Tensor.of(DTYPE, Shape.scalar(), data -> data.setBoolean(value));
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
    return Tensor.of(DTYPE, Shape.of(values.length), data -> StdArrays.copyTo(values, data));
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
    return Tensor.of(DTYPE, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static TBool tensorOf(Shape shape) {
    return Tensor.of(DTYPE, shape);
  }

  /**
   * Allocates a new tensor of the given shape, initialized with the provided data.
   *
   * @param shape shape of the tensor to allocate
   * @param data buffer of booleans to initialize the tensor with
   * @return the new tensor
   */
  static TBool tensorOf(Shape shape, BooleanDataBuffer data) {
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
  static TBool tensorOf(Shape shape, Consumer<TBool> dataInit) {
    return Tensor.of(DTYPE, shape, dataInit);
  }
}

/** Hidden implementation of a {@code TBool} */
class TBoolImpl extends BooleanDenseNdArray implements TBool {

  @Override
  public DataType<?> dataType() {
    return TBool.DTYPE;
  }

  @Override
  public RawTensor asRawTensor() {
    return rawTensor;
  }

  static TBool mapTensor(RawTensor tensor, TF_Tensor nativeHandle) {
    BooleanDataBuffer buffer = TensorBuffers.toBooleans(nativeHandle);
    return new TBoolImpl(tensor, buffer);
  }

  private final RawTensor rawTensor;

  private TBoolImpl(RawTensor rawTensor, BooleanDataBuffer buffer) {
    super(buffer, rawTensor.shape());
    this.rawTensor = rawTensor;
  }
}
