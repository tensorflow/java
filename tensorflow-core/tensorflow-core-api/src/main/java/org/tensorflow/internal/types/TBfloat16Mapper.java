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
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.TBfloat16;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_BFLOAT16} tensors
 * to a n-dimensional data space.
 */
public final class TBfloat16Mapper extends TensorMapper<TBfloat16> {

  @Override
  protected TBfloat16 mapDense(RawTensor tensor) {
    FloatDataBuffer buffer = DataLayouts.BFLOAT16.applyTo(TensorBuffers.toShorts(nativeHandle(tensor)));
    return new DenseTBfloat16(tensor, buffer);
  }

  private static final class DenseTBfloat16 extends FloatDenseNdArray implements TBfloat16 {

    @Override
    public Class<TBfloat16> type() {
      return TBfloat16.class;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTBfloat16(RawTensor rawTensor, FloatDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
