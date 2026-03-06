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

import java.nio.FloatBuffer;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.impl.buffer.misc.MiscDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;

public abstract class FloatDataBufferTestBase extends DataBufferTestBase<Float> {

  @Override
  protected abstract FloatDataBuffer allocate(long size);

  @Override
  protected Float valueOf(Long val) {
    return val.floatValue();
  }

  @Test
  public void writeAndReadFromArray() {
    FloatDataBuffer buffer = allocate(10L);
    float[] oneToFive = new float[]{ 1.0f, 2.0f, 3.0f, 4.0f, 5.0f };

    buffer.write(oneToFive);
    assertEquals(2.0f, buffer.getFloat(1), 0.0f);

    buffer.offset(5).write(oneToFive);
    assertEquals(2.0f, buffer.getFloat(1), 0.0f);
    assertEquals(2.0f, buffer.getFloat(6), 0.0f);

    float[] read = new float[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read, 0.0f);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3.0f, buffer.getFloat(0), 0.0f);
    assertEquals(4.0f, buffer.getFloat(1), 0.0f);
    assertEquals(3.0f, buffer.getFloat(2), 0.0f);

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0.0f, read[0], 0.0f);
    assertEquals(3.0f, read[1], 0.0f);
    assertEquals(4.0f, read[2], 0.0f);
    assertEquals(0.0f, read[3], 0.0f);
  }

  @Test
  public void equalWithFloatNioBuffer() {
    FloatDataBuffer nioBuffer1 = NioDataBufferFactory.create(FloatBuffer.wrap(new float[] { 1.0f, 16.0f }));
    FloatDataBuffer nioBuffer2 = NioDataBufferFactory.create(FloatBuffer.wrap(new float[] { 1.0f, 25.0f }));

    FloatDataBuffer buffer = allocate(2)
        .setFloat(1.0f, 0)
        .setFloat(16.0f, 1);

    assertTrue(nioBuffer1.equals(buffer));
    assertTrue(buffer.equals(nioBuffer1));
    assertEquals(nioBuffer1.hashCode(), buffer.hashCode());

    assertFalse(nioBuffer2.equals(buffer));
    assertFalse(buffer.equals(nioBuffer2));
    assertNotEquals(nioBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithFloatRawBuffer() {
    FloatDataBuffer rawBuffer1 = RawDataBufferFactory.create(new float[] { 1.0f, 16.0f }, true);
    FloatDataBuffer rawBuffer2 = RawDataBufferFactory.create(new float[] { 1.0f, 25.0f }, true);

    FloatDataBuffer buffer = allocate(2)
        .setFloat(1.0f, 0)
        .setFloat(16.0f, 1);

    assertTrue(rawBuffer1.equals(buffer));
    assertTrue(buffer.equals(rawBuffer1));
    assertEquals(rawBuffer1.hashCode(), buffer.hashCode());

    assertFalse(rawBuffer2.equals(buffer));
    assertFalse(buffer.equals(rawBuffer2));
    assertNotEquals(rawBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void equalWithFloatObjectBuffer() {
    DataBuffer<Float> objBuffer1 = MiscDataBufferFactory.create(new Float[] { 1.0f, 16.0f }, true);
    DataBuffer<Float> objBuffer2 = MiscDataBufferFactory.create(new Float[] { 1.0f, 25.0f }, true);

    FloatDataBuffer buffer = allocate(2)
        .setFloat(1.0f, 0)
        .setFloat(16.0f, 1);

    assertTrue(objBuffer1.equals(buffer));
    assertTrue(buffer.equals(objBuffer1));
    assertEquals(objBuffer1.hashCode(), buffer.hashCode());

    assertFalse(objBuffer2.equals(buffer));
    assertFalse(buffer.equals(objBuffer2));
    assertNotEquals(objBuffer2.hashCode(), buffer.hashCode());
  }

  @Test
  public void notEqualWithOtherTypes() {
    FloatDataBuffer buffer = allocate(2)
        .setFloat(1.0f, 0)
        .setFloat(16.0f, 1);
    DoubleDataBuffer doubleBuffer = DataBuffers.of(1.0, 16.0);

    assertFalse(buffer.equals(doubleBuffer));
    assertFalse(doubleBuffer.equals(buffer));
  }
}
