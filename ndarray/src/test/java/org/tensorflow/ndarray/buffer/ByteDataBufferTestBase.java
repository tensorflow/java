/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.ndarray.buffer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.impl.buffer.misc.MiscDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;

public abstract class ByteDataBufferTestBase extends DataBufferTestBase<Byte> {

  @Override
  protected abstract ByteDataBuffer allocate(long size);

  @Override
  protected Byte valueOf(Long val) {
    return val.byteValue();
  }

  @Test
  public void writeAndReadFromArray() {
    ByteDataBuffer buffer = allocate(10L);
    byte[] oneToFive = new byte[]{ 1, 2, 3, 4, 5 };

    buffer.write(oneToFive);
    assertEquals(2, buffer.getByte(1));

    buffer.offset(5).write(oneToFive);
    assertEquals(2, buffer.getByte(1));
    assertEquals(2, buffer.getByte(6));

    byte[] read = new byte[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3, buffer.getByte(0));
    assertEquals(4, buffer.getByte(1));
    assertEquals(3, buffer.getByte(2));

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0, read[0]);
    assertEquals(3, read[1]);
    assertEquals(4, read[2]);
    assertEquals(0, read[3]);
  }

  @Test
  public void equalWithByteNioBuffer() {
    ByteDataBuffer nioBuffer1 = NioDataBufferFactory.create(ByteBuffer.wrap(new byte[] { 0x01, 0x10 }));
    ByteDataBuffer nioBuffer2 = NioDataBufferFactory.create(ByteBuffer.wrap(new byte[] { 0x01, 0x11 }));

    ByteDataBuffer buffer = allocate(2)
        .setByte((byte)0x01, 0)
        .setByte((byte)0x10, 1);

    assertTrue(nioBuffer1.equals(buffer));
    assertTrue(buffer.equals(nioBuffer1));
    assertEquals(nioBuffer1.hashCode(), buffer.hashCode());

    assertFalse(nioBuffer2.equals(buffer));
    assertFalse(buffer.equals(nioBuffer2));
    assertNotEquals(nioBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithByteRawBuffer() {
    ByteDataBuffer rawBuffer1 = RawDataBufferFactory.create(new byte[] { 0x01, 0x10 }, true);
    ByteDataBuffer rawBuffer2 = RawDataBufferFactory.create(new byte[] { 0x01, 0x11 }, true);

    ByteDataBuffer buffer = allocate(2)
        .setByte((byte)0x01, 0)
        .setByte((byte)0x10, 1);

    assertTrue(rawBuffer1.equals(buffer));
    assertTrue(buffer.equals(rawBuffer1));
    assertEquals(rawBuffer1.hashCode(), buffer.hashCode());

    assertFalse(rawBuffer2.equals(buffer));
    assertFalse(buffer.equals(rawBuffer2));
    assertNotEquals(rawBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithByteObjectBuffer() {
    DataBuffer<Byte> objBuffer1 = MiscDataBufferFactory.create(new Byte[] { 0x01, 0x10 }, true);
    DataBuffer<Byte> objBuffer2 = MiscDataBufferFactory.create(new Byte[] { 0x01, 0x11 }, true);

    ByteDataBuffer buffer = allocate(2)
        .setByte((byte)0x01, 0)
        .setByte((byte)0x10, 1);

    assertTrue(objBuffer1.equals(buffer));
    assertTrue(buffer.equals(objBuffer1));
    assertEquals(objBuffer1.hashCode(), buffer.hashCode());

    assertFalse(objBuffer2.equals(buffer));
    assertFalse(buffer.equals(objBuffer2));
    assertNotEquals(objBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void notEqualWithOtherTypes() {
    ByteDataBuffer buffer = allocate(2)
        .setByte((byte)1, 0)
        .setByte((byte)16, 1);
    LongDataBuffer longBuffer = DataBuffers.of(1L, 16L);

    assertFalse(buffer.equals(longBuffer));
    assertFalse(longBuffer.equals(buffer));

    try {
      IntDataBuffer intBuffer = buffer.asInts();

      assertFalse(buffer.equals(intBuffer));
      assertFalse(intBuffer.equals(buffer));

    } catch (IllegalStateException e) {
      // some byte buffers cannot be converted to ints, ignore the test in that case
    }
  }
}
