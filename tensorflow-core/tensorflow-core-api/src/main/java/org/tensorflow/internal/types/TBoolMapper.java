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
package org.tensorflow.internal.types;

import org.tensorflow.RawTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.types.TBool;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_BOOL} tensors
 * to a n-dimensional data space.
 */
public final class TBoolMapper extends TensorMapper<TBool> {

  @Override
  protected TBool mapDense(RawTensor tensor) {
    BooleanDataBuffer buffer = TensorBuffers.toBooleans(nativeHandle(tensor));
    return new DenseTBool(tensor, buffer);
  }

  private static final class DenseTBool extends BooleanDenseNdArray implements TBool {

    @Override
    public Class<TBool> type() {
      return TBool.class;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTBool(RawTensor rawTensor, BooleanDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
