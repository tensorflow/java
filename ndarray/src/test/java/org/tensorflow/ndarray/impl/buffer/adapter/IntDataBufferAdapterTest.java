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

import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBufferTestBase;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.IntDataLayout;

public class IntDataBufferAdapterTest extends IntDataBufferTestBase {

  @Override
  protected IntDataBuffer allocate(long size) {
    return LAYOUT.applyTo(DataBuffers.ofShorts(size * LAYOUT.scale()));
  }

  @Override
  protected long maxSize() {
    return super.maxSize() / 2;
  }

  private static IntDataLayout<ShortDataBuffer> LAYOUT = new IntDataLayout<ShortDataBuffer>() {

    @Override
    public void writeInt(ShortDataBuffer buffer, int value, long index) {
      buffer.setShort((short)(((value & 0x80000000) >> 16) | (value & 0x7FFF)), index);
    }

    @Override
    public int readInt(ShortDataBuffer buffer, long index) {
      int i = buffer.getShort(index);
      return ((i & 0x8000) << 16) | ((i & 0x7FFF));
    }
  };
}
