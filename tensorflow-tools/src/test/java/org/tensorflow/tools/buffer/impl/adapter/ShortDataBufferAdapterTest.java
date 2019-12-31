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
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBufferTestBase;
import org.tensorflow.tools.buffer.layout.ShortDataLayout;

public class ShortDataBufferAdapterTest extends ShortDataBufferTestBase {

  private static class TestShort8Layout implements ShortDataLayout {

    @Override
    public void writeShort(ByteDataBuffer buffer, short value, long index) {
      buffer.setObject((byte)(((value & 0x8000) >> 8) | (value & 0x7F)), index);
    }

    @Override
    public short readShort(ByteDataBuffer buffer, long index) {
      int b = buffer.getObject(index);
      return (short)(((b & 0x80) << 8) | (b & 0x7F));
    }

    @Override
    public int sizeInBytes() {
      return 1;
    }
  }

  public ShortDataBuffer allocate(long size) {
    return DataBuffers.ofShorts(size, new TestShort8Layout());
  }
}
