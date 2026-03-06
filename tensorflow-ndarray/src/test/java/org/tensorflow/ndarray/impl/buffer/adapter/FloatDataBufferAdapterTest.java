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
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBufferTestBase;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.FloatDataLayout;

public class FloatDataBufferAdapterTest extends FloatDataBufferTestBase {

  @Override
  public FloatDataBuffer allocate(long size) {
    return LAYOUT.applyTo(DataBuffers.ofShorts(size * LAYOUT.scale()));
  }

  @Override
  protected long maxSize() {
    return super.maxSize() / 2;
  }

  private static FloatDataLayout<ShortDataBuffer> LAYOUT = new FloatDataLayout<ShortDataBuffer>() {

    @Override
    public void writeFloat(ShortDataBuffer buffer, float value, long index) {
      int bits = Float.floatToIntBits(value);
      buffer.setShort((short)(bits >> 16), index);
    }

    @Override
    public float readFloat(ShortDataBuffer buffer, long index) {
      int i = buffer.getShort(index);
      return Float.intBitsToFloat(i << 16);
    }
  };
}
