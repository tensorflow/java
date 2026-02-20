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

import java.nio.LongBuffer;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.impl.buffer.misc.MiscDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;

public abstract class LongDataBufferTestBase extends DataBufferTestBase<Long> {

  @Override
  protected abstract LongDataBuffer allocate(long size);

  @Override
  protected Long valueOf(Long val) {
    return val;
  }

  @Test
  public void writeAndReadFromArray() {
    LongDataBuffer buffer = allocate(10L);
    long[] oneToFive = new long[]{ 1L, 2L, 3L, 4L, 5L };

    buffer.write(oneToFive);
    assertEquals(2, buffer.getLong(1));

    buffer.offset(5).write(oneToFive);
    assertEquals(2L, buffer.getLong(1));
    assertEquals(2L, buffer.getLong(6));

    long[] read = new long[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3L, buffer.getLong(0));
    assertEquals(4L, buffer.getLong(1));
    assertEquals(3L, buffer.getLong(2));

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0L, read[0]);
    assertEquals(3L, read[1]);
    assertEquals(4L, read[2]);
    assertEquals(0L, read[3]);
  }

  @Test
  public void equalWithLongNioBuffer() {
    LongDataBuffer nioBuffer1 = NioDataBufferFactory.create(LongBuffer.wrap(new long[] { 1, 16 }));
    LongDataBuffer nioBuffer2 = NioDataBufferFactory.create(LongBuffer.wrap(new long[] { 1, 25 }));

    LongDataBuffer buffer = allocate(2)
        .setLong(1, 0)
        .setLong(16, 1);

    assertTrue(nioBuffer1.equals(buffer));
    assertTrue(buffer.equals(nioBuffer1));
    assertEquals(nioBuffer1.hashCode(), buffer.hashCode());

    assertFalse(nioBuffer2.equals(buffer));
    assertFalse(buffer.equals(nioBuffer2));
    assertNotEquals(nioBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithLongRawBuffer() {
    LongDataBuffer rawBuffer1 = RawDataBufferFactory.create(new long[] { 1, 16 }, true);
    LongDataBuffer rawBuffer2 = RawDataBufferFactory.create(new long[] { 1, 25 }, true);

    LongDataBuffer buffer = allocate(2)
        .setLong(1, 0)
        .setLong(16, 1);

    assertTrue(rawBuffer1.equals(buffer));
    assertTrue(buffer.equals(rawBuffer1));
    assertEquals(rawBuffer1.hashCode(), buffer.hashCode());

    assertFalse(rawBuffer2.equals(buffer));
    assertFalse(buffer.equals(rawBuffer2));
    assertNotEquals(rawBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithLongObjectBuffer() {
    DataBuffer<Long> objBuffer1 = MiscDataBufferFactory.create(new Long[] { 1L, 16L }, true);
    DataBuffer<Long> objBuffer2 = MiscDataBufferFactory.create(new Long[] { 1L, 25L }, true);

    LongDataBuffer buffer = allocate(2)
        .setLong(1, 0)
        .setLong(16, 1);

    assertTrue(objBuffer1.equals(buffer));
    assertTrue(buffer.equals(objBuffer1));
    assertEquals(objBuffer1.hashCode(), buffer.hashCode());

    assertFalse(objBuffer2.equals(buffer));
    assertFalse(buffer.equals(objBuffer2));
    assertNotEquals(objBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void notEqualWithOtherTypes() {
    LongDataBuffer buffer = allocate(2)
        .setLong(1L, 0)
        .setLong(16L, 1);
    IntDataBuffer intBuffer = DataBuffers.of(1, 16);

    assertFalse(buffer.equals(intBuffer));
    assertFalse(intBuffer.equals(buffer));
  }
}
