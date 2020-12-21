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
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.types.TFloat64;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_DOUBLE} tensors
 * to a n-dimensional data space.
 */
public final class TFloat64Mapper extends TensorMapper<TFloat64> {

  @Override
  protected TFloat64 mapDense(RawTensor tensor) {
    DoubleDataBuffer buffer = TensorBuffers.toDoubles(nativeHandle(tensor));
    return new DenseTFloat64(tensor, buffer);
  }

  private static final class DenseTFloat64 extends DoubleDenseNdArray implements TFloat64 {

    @Override
    public Class<TFloat64> type() {
      return TFloat64.class;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTFloat64(RawTensor rawTensor, DoubleDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
