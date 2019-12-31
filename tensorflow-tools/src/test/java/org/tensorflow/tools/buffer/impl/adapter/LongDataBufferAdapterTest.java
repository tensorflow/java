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

package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.LongDataBufferTestBase;
import org.tensorflow.tools.buffer.layout.LongDataLayout;

public class LongDataBufferAdapterTest extends LongDataBufferTestBase {

  @Override
  protected LongDataBuffer allocate(long size) {
    return DataBuffers.ofLongs(size, new TestLongLayout());
  }

  @Override
  protected long maxSize() {
    return super.maxSize() / 3;
  }

  private static class TestLongLayout implements LongDataLayout {

    @Override
    public void writeLong(ByteDataBuffer buffer, long value, long index) {
      buffer.setObject((byte)(((value >> 56) & 0x80) | ((value >> 16) & 0x7F)), index);
      buffer.setObject((byte)((value >> 8) & 0xFF), index + 1);
      buffer.setObject((byte)(value & 0xFF), index + 2);
    }

    @Override
    public long readLong(ByteDataBuffer buffer, long index) {
      long msb = buffer.getObject(index);
      long midb = buffer.getObject(index + 1);
      long lsb = buffer.getObject(index + 2);
      return ((msb & 0x80) << 56) | ((msb & 0x7F) << 16) | ((midb & 0xFF) << 8) | (lsb & 0xFF);
    }

    @Override
    public int sizeInBytes() {
      return 3;
    }
  }
}
