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
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBufferTestBase;
import org.tensorflow.tools.buffer.layout.FloatDataLayout;

public class FloatDataBufferAdapterTest extends FloatDataBufferTestBase {

  @Override
  protected long maxSize() {
    return super.maxSize() / 2;
  }

  private static class TestFloat16Layout implements FloatDataLayout {

    @Override
    public void writeFloat(ByteDataBuffer buffer, float value, long index) {
      int bits = Float.floatToIntBits(value);
      buffer.setObject((byte)((bits >> 24) & 0xFF), index);
      buffer.setObject((byte)((bits >> 16) & 0xFF), index + 1);
    }

    @Override
    public float readFloat(ByteDataBuffer buffer, long index) {
      int byte3 = buffer.getObject(index);
      int byte2 = buffer.getObject(index + 1);
      return Float.intBitsToFloat(((byte3 & 0xFF) << 24) | ((byte2 & 0xFF) << 16));
    }

    @Override
    public int sizeInBytes() {
      return 2;
    }
  }

  public FloatDataBuffer allocate(long size) {
    return DataBuffers.ofFloats(size, new TestFloat16Layout());
  }
}
