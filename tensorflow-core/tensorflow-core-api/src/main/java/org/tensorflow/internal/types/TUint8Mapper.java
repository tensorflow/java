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

import org.tensorflow.DataType;
import org.tensorflow.RawTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.types.TUint8;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_UINT8} tensors
 * to a n-dimensional data space.
 */
public final class TUint8Mapper extends TensorMapper<TUint8> {

  @Override
  protected TUint8 mapDense(RawTensor tensor) {
    ByteDataBuffer buffer = TensorBuffers.toBytes(nativeHandle(tensor));
    return new DenseTUint8(tensor, buffer);
  }

  private static final class DenseTUint8 extends ByteDenseNdArray implements TUint8 {

    @Override
    public DataType<?> dataType() {
      return TUint8.DTYPE;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTUint8(RawTensor rawTensor, ByteDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
