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

package org.tensorflow.ndarray.impl.buffer.adapter;

import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBufferTestBase;
import org.tensorflow.ndarray.buffer.layout.ShortDataLayout;

public class ShortDataBufferAdapterTest extends ShortDataBufferTestBase {

  public ShortDataBuffer allocate(long size) {
    return LAYOUT.applyTo(DataBuffers.ofBytes(size * LAYOUT.scale()));
  }

  private static ShortDataLayout<ByteDataBuffer> LAYOUT = new ShortDataLayout<ByteDataBuffer>() {

    @Override
    public void writeShort(ByteDataBuffer buffer, short value, long index) {
      buffer.setByte((byte)(((value & 0x8000) >> 8) | (value & 0x7F)), index);
    }

    @Override
    public short readShort(ByteDataBuffer buffer, long index) {
      int b = buffer.getByte(index);
      return (short)(((b & 0x80) << 8) | (b & 0x7F));
    }
  };
}
