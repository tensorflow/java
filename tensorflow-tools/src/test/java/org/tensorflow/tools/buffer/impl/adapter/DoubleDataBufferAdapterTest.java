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
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.DoubleDataBufferTestBase;
import org.tensorflow.tools.buffer.layout.DoubleDataLayout;

public class DoubleDataBufferAdapterTest extends DoubleDataBufferTestBase {

  @Override
  protected DoubleDataBuffer allocate(long size) {
    return DataBuffers.ofDoubles(size, new TestDoubleLayout());
  }

  @Override
  protected long maxSize() {
    return super.maxSize() / 3;
  }

  private static class TestDoubleLayout implements DoubleDataLayout {

    @Override
    public void writeDouble(ByteDataBuffer buffer, double value, long index) {
      long bits = Double.doubleToLongBits(value);
      buffer.setObject((byte)((bits >> 56) & 0xFF), index);
      buffer.setObject((byte)((bits >> 48) & 0xFF), index + 1);
      buffer.setObject((byte)((bits >> 40) & 0xFF), index + 2);
    }

    @Override
    public double readDouble(ByteDataBuffer buffer, long index) {
      long byte7 = buffer.getObject(index);
      long byte6 = buffer.getObject(index + 1);
      long byte5 = buffer.getObject(index + 2);
      return Double.longBitsToDouble(((byte7 & 0xFF) << 56) | ((byte6 & 0xFF) << 48) | ((byte5 & 0xFF) << 40));
    }

    @Override
    public int sizeInBytes() {
      return 3;
    }
  }
}
