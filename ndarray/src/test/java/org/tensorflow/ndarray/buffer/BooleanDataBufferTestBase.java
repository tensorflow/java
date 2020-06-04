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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.BitSet;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.impl.buffer.misc.MiscDataBufferFactory;

public abstract class BooleanDataBufferTestBase extends DataBufferTestBase<Boolean> {

  @Override
  protected abstract BooleanDataBuffer allocate(long size);

  @Override
  protected Boolean valueOf(Long val) {
    return val != 0;
  }

  @Test
  public void writeAndReadFromArray() {
    BooleanDataBuffer buffer = allocate(10L);
    boolean[] values = new boolean[]{true, false, false, true, false};

    buffer.write(values);
    assertTrue(buffer.getObject(0));
    assertFalse(buffer.getObject(1));

    buffer.offset(5).write(values);
    assertTrue(buffer.getObject(5));

    boolean[] read = new boolean[5];
    buffer.read(read);
    assertArrayEquals(values, read);

    buffer.write(values, 2, 3);
    assertFalse(buffer.getObject(0));
    assertTrue(buffer.getObject(1));
    assertFalse(buffer.getObject(2));

    Arrays.fill(read, false);
    buffer.read(read, 1, 2);
    assertFalse(read[0]);
    assertFalse(read[1]);
    assertTrue(read[2]);
    assertFalse(read[3]);
  }

  @Test
  public void equalWithBitSetBuffer() {
    BitSet bitSet1 = BitSet.valueOf(new byte[] { 0x01, 0x01 });
    BooleanDataBuffer bitSet1Buffer = MiscDataBufferFactory.create(bitSet1, 12, true);

    BitSet bitSet2 = BitSet.valueOf(new byte[] { 0x11, 0x01 });
    BooleanDataBuffer bitSet2Buffer = MiscDataBufferFactory.create(bitSet2, 12, true);

    BooleanDataBuffer buffer = allocate(12)
        .setBoolean(true, 0)
        .setBoolean(true, 8);

    assertTrue(bitSet1Buffer.equals(buffer));
    assertTrue(buffer.equals(bitSet1Buffer));
    assertEquals(bitSet1Buffer.hashCode(), buffer.hashCode());

    assertFalse(bitSet2Buffer.equals(buffer));
    assertFalse(buffer.equals(bitSet2Buffer));
    assertNotEquals(bitSet2Buffer.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithBooleanArrayBuffer() {
    boolean[] array1 = new boolean[] { false, false, false, true, true, false };
    BooleanDataBuffer array1Buffer = MiscDataBufferFactory.create(array1, true);

    boolean[] array2 = new boolean[] { false, false, false, true, true, true };
    BooleanDataBuffer array2Buffer = MiscDataBufferFactory.create(array2, true);

    BooleanDataBuffer buffer = allocate(6)
        .setBoolean(true, 3)
        .setBoolean(true, 4);

    assertTrue(array1Buffer.equals(buffer));
    assertTrue(buffer.equals(array1Buffer));
    assertEquals(array1Buffer.hashCode(), buffer.hashCode());

    assertFalse(array2Buffer.equals(buffer));
    assertFalse(buffer.equals(array2Buffer));
    assertNotEquals(array2Buffer.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithBooleanObjectBuffer() {
    Boolean[] array1 = new Boolean[] { false, false, false, true, true, false };
    DataBuffer<Boolean> array1Buffer = MiscDataBufferFactory.create(array1, true);

    boolean[] array2 = new boolean[] { false, false, false, true, true, true };
    DataBuffer<Boolean> array2Buffer = MiscDataBufferFactory.create(array2, true);

    BooleanDataBuffer buffer = allocate(6)
        .setBoolean(true, 3)
        .setBoolean(true, 4);

    assertTrue(array1Buffer.equals(buffer));
    assertTrue(buffer.equals(array1Buffer));
    assertEquals(array1Buffer.hashCode(), buffer.hashCode());

    assertFalse(array2Buffer.equals(buffer));
    assertFalse(buffer.equals(array2Buffer));
    assertNotEquals(array2Buffer.hashCode(), buffer.hashCode());
  }

  @Test
  public void notEqualWithOtherTypes() {
    BooleanDataBuffer buffer = allocate(2)
        .setBoolean(false, 0)
        .setBoolean(true, 1);
    ByteDataBuffer byteBuffer = DataBuffers.of((byte)0, (byte)1);

    assertFalse(buffer.equals(byteBuffer));
    assertFalse(byteBuffer.equals(buffer));
  }
}
