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

import java.nio.IntBuffer;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.impl.buffer.misc.MiscDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;

public abstract class IntDataBufferTestBase extends DataBufferTestBase<Integer> {

  @Override
  protected abstract IntDataBuffer allocate(long size);

  @Override
  protected Integer valueOf(Long val) {
    return val.intValue();
  }

  @Test
  public void writeAndReadFromArray() {
    IntDataBuffer buffer = allocate(10L);
    int[] oneToFive = new int[]{ 1, 2, 3, 4, 5 };

    buffer.write(oneToFive);
    assertEquals(2, buffer.getInt(1));

    buffer.offset(5).write(oneToFive);
    assertEquals(2, buffer.getInt(1));
    assertEquals(2, buffer.getInt(6));

    int[] read = new int[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3, buffer.getInt(0));
    assertEquals(4, buffer.getInt(1));
    assertEquals(3, buffer.getInt(2));

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0, read[0]);
    assertEquals(3, read[1]);
    assertEquals(4, read[2]);
    assertEquals(0, read[3]);
  }

  @Test
  public void equalWithIntNioBuffer() {
    IntDataBuffer nioBuffer1 = NioDataBufferFactory.create(IntBuffer.wrap(new int[] { 1, 16 }));
    IntDataBuffer nioBuffer2 = NioDataBufferFactory.create(IntBuffer.wrap(new int[] { 1, 25 }));

    IntDataBuffer buffer = allocate(2)
        .setInt(1, 0)
        .setInt(16, 1);

    assertTrue(nioBuffer1.equals(buffer));
    assertTrue(buffer.equals(nioBuffer1));
    assertEquals(nioBuffer1.hashCode(), buffer.hashCode());

    assertFalse(nioBuffer2.equals(buffer));
    assertFalse(buffer.equals(nioBuffer2));
    assertNotEquals(nioBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithIntRawBuffer() {
    IntDataBuffer rawBuffer1 = RawDataBufferFactory.create(new int[] { 1, 16 }, true);
    IntDataBuffer rawBuffer2 = RawDataBufferFactory.create(new int[] { 1, 25 }, true);

    IntDataBuffer buffer = allocate(2)
        .setInt(1, 0)
        .setInt(16, 1);

    assertTrue(rawBuffer1.equals(buffer));
    assertTrue(buffer.equals(rawBuffer1));
    assertEquals(rawBuffer1.hashCode(), buffer.hashCode());

    assertFalse(rawBuffer2.equals(buffer));
    assertFalse(buffer.equals(rawBuffer2));
    assertNotEquals(rawBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithIntObjectBuffer() {
    DataBuffer<Integer> objBuffer1 = MiscDataBufferFactory.create(new Integer[] { 1, 16 }, true);
    DataBuffer<Integer> objBuffer2 = MiscDataBufferFactory.create(new Integer[] { 1, 25 }, true);

    IntDataBuffer buffer = allocate(2)
        .setInt(1, 0)
        .setInt(16, 1);

    assertTrue(objBuffer1.equals(buffer));
    assertTrue(buffer.equals(objBuffer1));
    assertEquals(objBuffer1.hashCode(), buffer.hashCode());

    assertFalse(objBuffer2.equals(buffer));
    assertFalse(buffer.equals(objBuffer2));
    assertNotEquals(objBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void notEqualWithOtherTypes() {
    IntDataBuffer buffer = allocate(2)
        .setInt(1, 0)
        .setInt(16, 1);
    LongDataBuffer longBuffer = DataBuffers.of(1L, 16L);

    assertFalse(buffer.equals(longBuffer));
    assertFalse(longBuffer.equals(buffer));
  }
}
