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

import java.nio.DoubleBuffer;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.impl.buffer.misc.MiscDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;

public abstract class DoubleDataBufferTestBase extends DataBufferTestBase<Double> {

  @Override
  protected abstract DoubleDataBuffer allocate(long size);

  @Override
  protected Double valueOf(Long val) {
    return val.doubleValue();
  }

  @Test
  public void writeAndReadFromArray() {
    DoubleDataBuffer buffer = allocate(10L);
    double[] oneToFive = new double[]{ 1.0, 2.0, 3.0, 4.0, 5.0 };

    buffer.write(oneToFive);
    assertEquals(2.0, buffer.getDouble(1), 0.0);

    buffer.offset(5).write(oneToFive);
    assertEquals(2.0, buffer.getDouble(1), 0.0);
    assertEquals(2.0, buffer.getDouble(6), 0.0);

    double[] read = new double[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read, 0.0);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3.0, buffer.getDouble(0), 0.0);
    assertEquals(4.0, buffer.getDouble(1), 0.0);
    assertEquals(3.0, buffer.getDouble(2), 0.0);

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0.0, read[0], 0.0);
    assertEquals(3.0, read[1], 0.0);
    assertEquals(4.0, read[2], 0.0);
    assertEquals(0.0, read[3], 0.0);
  }

  @Test
  public void equalWithDoubleNioBuffer() {
    DoubleDataBuffer nioBuffer1 = NioDataBufferFactory.create(DoubleBuffer.wrap(new double[] { 1.0, 16.0 }));
    DoubleDataBuffer nioBuffer2 = NioDataBufferFactory.create(DoubleBuffer.wrap(new double[] { 1.0, 25.0 }));

    DoubleDataBuffer buffer = allocate(2)
        .setDouble(1.0, 0)
        .setDouble(16.0, 1);

    assertTrue(nioBuffer1.equals(buffer));
    assertTrue(buffer.equals(nioBuffer1));
    assertEquals(nioBuffer1.hashCode(), buffer.hashCode());

    assertFalse(nioBuffer2.equals(buffer));
    assertFalse(buffer.equals(nioBuffer2));
    assertNotEquals(nioBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithDoubleRawBuffer() {
    DoubleDataBuffer rawBuffer1 = RawDataBufferFactory.create(new double[] { 1.0, 16.0 }, true);
    DoubleDataBuffer rawBuffer2 = RawDataBufferFactory.create(new double[] { 1.0, 25.0 }, true);

    DoubleDataBuffer buffer = allocate(2)
        .setDouble(1.0, 0)
        .setDouble(16.0, 1);

    assertTrue(rawBuffer1.equals(buffer));
    assertTrue(buffer.equals(rawBuffer1));
    assertEquals(rawBuffer1.hashCode(), buffer.hashCode());

    assertFalse(rawBuffer2.equals(buffer));
    assertFalse(buffer.equals(rawBuffer2));
    assertNotEquals(rawBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithDoubleObjectBuffer() {
    DataBuffer<Double> objBuffer1 = MiscDataBufferFactory.create(new Double[] { 1.0, 16.0 }, true);
    DataBuffer<Double> objBuffer2 = MiscDataBufferFactory.create(new Double[] { 1.0, 25.0 }, true);

    DoubleDataBuffer buffer = allocate(2)
        .setDouble(1.0, 0)
        .setDouble(16.0, 1);

    assertTrue(objBuffer1.equals(buffer));
    assertTrue(buffer.equals(objBuffer1));
    assertEquals(objBuffer1.hashCode(), buffer.hashCode());

    assertFalse(objBuffer2.equals(buffer));
    assertFalse(buffer.equals(objBuffer2));
    assertNotEquals(objBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void notEqualWithOtherTypes() {
    DoubleDataBuffer buffer = allocate(2)
        .setDouble(1.0, 0)
        .setDouble(16.0, 1);
    FloatDataBuffer floatBuffer = DataBuffers.of(1.0f, 16.0f);

    assertFalse(buffer.equals(floatBuffer));
    assertFalse(floatBuffer.equals(buffer));
  }
}
