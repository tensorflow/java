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

import java.math.BigInteger;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBufferTestBase;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.layout.DataLayout;

public class BigIntegerDataBufferAdapterTest extends DataBufferTestBase<BigInteger> {

  @Override
  protected DataBuffer<BigInteger> allocate(long size) {
    return LAYOUT.applyTo(DataBuffers.ofBytes(size * LAYOUT.scale()));
  }

  @Override
  protected long maxSize() {
    return super.maxSize() / 3;
  }

  @Override
  protected BigInteger valueOf(Long val) {
    return BigInteger.valueOf(val);
  }

  private static DataLayout<ByteDataBuffer, BigInteger> LAYOUT = new DataLayout<ByteDataBuffer, BigInteger>() {

    @Override
    public void writeObject(ByteDataBuffer buffer, BigInteger value, long index) {
      byte[] bytes = value.toByteArray();
      buffer.setByte(bytes.length > 2 ? bytes[2] : 0, index);
      buffer.setByte(bytes.length > 1 ? bytes[1] : 0, index + 1);
      buffer.setByte(bytes[0], index + 2);
    }

    @Override
    public BigInteger readObject(ByteDataBuffer buffer, long index) {
      byte byte2 = buffer.getByte(index);
      byte byte1 = buffer.getByte(index + 1);
      byte byte0 = buffer.getByte(index + 2);
      return new BigInteger(new byte[] { byte2, byte1, byte0 });
    }

    @Override
    public int scale() {
      return 3;
    }
  };
}
