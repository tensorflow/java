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
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.IntDataBufferTestBase;
import org.tensorflow.tools.buffer.layout.IntDataLayout;

public class IntDataBufferAdapterTest extends IntDataBufferTestBase {

  @Override
  protected IntDataBuffer allocate(long size) {
    return DataBuffers.ofInts(size, new TestIntLayout());
  }

  @Override
  protected long maxSize() {
    return super.maxSize() / 2;
  }

  private static class TestIntLayout implements IntDataLayout {

    @Override
    public void writeInt(ByteDataBuffer buffer, int value, long index) {
      buffer.setObject((byte)(((value & 0x80000000) >> 24) | ((value & 0x7F) >> 7)), index);
      buffer.setObject((byte)(value), index + 1);
    }

    @Override
    public int readInt(ByteDataBuffer buffer, long index) {
      int msb = buffer.getObject(index);
      int lsb = buffer.getObject(index + 1);
      return ((msb & 0x80) << 24) | ((msb & 0x7F) << 7) | lsb;
    }

    @Override
    public int sizeInBytes() {
      return 2;
    }
  }
}
