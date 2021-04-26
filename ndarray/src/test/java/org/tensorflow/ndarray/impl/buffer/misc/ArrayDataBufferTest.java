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
package org.tensorflow.ndarray.impl.buffer.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBufferTestBase;

public class ArrayDataBufferTest extends DataBufferTestBase<BigDecimal> {

  @Override
  protected DataBuffer<BigDecimal> allocate(long size) {
    return new ArrayDataBuffer<>(new BigDecimal[(int)size], false);
  }

  @Override
  protected BigDecimal valueOf(Long val) {
    return BigDecimal.valueOf(val);
  }

  @Test
  public void byteArrayBufferEquals() {
    DataBuffer<byte[]> buffer1 = new ArrayDataBuffer<>(new byte[][] { { 0x01 }, { 0x03 } }, true);
    DataBuffer<byte[]> buffer2 = new ArrayDataBuffer<>(new byte[][] { { 0x01 }, { 0x03 } }, true);
    DataBuffer<byte[]> buffer3 = new ArrayDataBuffer<>(new byte[][] { { 0x02 }, { 0x03 } }, true);
    DataBuffer<byte[][]> buffer4 = new ArrayDataBuffer<>(new byte[][][] { { { 0x01 } }, { { 0x03 } } }, true);
    DataBuffer<byte[][]> buffer5 = new ArrayDataBuffer<>(new byte[][][] { { { 0x01 } }, { { 0x03 } } }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }

  @Test
  public void intArrayBufferEquals() {
    DataBuffer<int[]> buffer1 = new ArrayDataBuffer<>(new int[][] { { 10 }, { 30 } }, true);
    DataBuffer<int[]> buffer2 = new ArrayDataBuffer<>(new int[][] { { 10 }, { 30 } }, true);
    DataBuffer<int[]> buffer3 = new ArrayDataBuffer<>(new int[][] { { 20 }, { 30 } }, true);
    DataBuffer<int[][]> buffer4 = new ArrayDataBuffer<>(new int[][][] { { { 10 } }, { { 30 } } }, true);
    DataBuffer<int[][]> buffer5 = new ArrayDataBuffer<>(new int[][][] { { { 10 } }, { { 30 } } }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }

  @Test
  public void shortArrayBufferEquals() {
    DataBuffer<short[]> buffer1 = new ArrayDataBuffer<>(new short[][] { { 10 }, { 30 } }, true);
    DataBuffer<short[]> buffer2 = new ArrayDataBuffer<>(new short[][] { { 10 }, { 30 } }, true);
    DataBuffer<short[]> buffer3 = new ArrayDataBuffer<>(new short[][] { { 20 }, { 30 } }, true);
    DataBuffer<short[][]> buffer4 = new ArrayDataBuffer<>(new short[][][] { { { 10 } }, { { 30 } } }, true);
    DataBuffer<short[][]> buffer5 = new ArrayDataBuffer<>(new short[][][] { { { 10 } }, { { 30 } } }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }

  @Test
  public void longArrayBufferEquals() {
    DataBuffer<long[]> buffer1 = new ArrayDataBuffer<>(new long[][] { { 10 }, { 30 } }, true);
    DataBuffer<long[]> buffer2 = new ArrayDataBuffer<>(new long[][] { { 10 }, { 30 } }, true);
    DataBuffer<long[]> buffer3 = new ArrayDataBuffer<>(new long[][] { { 20 }, { 30 } }, true);
    DataBuffer<long[][]> buffer4 = new ArrayDataBuffer<>(new long[][][] { { { 10 } }, { { 30 } } }, true);
    DataBuffer<long[][]> buffer5 = new ArrayDataBuffer<>(new long[][][] { { { 10 } }, { { 30 } } }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }

  @Test
  public void floatArrayBufferEquals() {
    DataBuffer<float[]> buffer1 = new ArrayDataBuffer<>(new float[][] { { 10 }, { 30 } }, true);
    DataBuffer<float[]> buffer2 = new ArrayDataBuffer<>(new float[][] { { 10 }, { 30 } }, true);
    DataBuffer<float[]> buffer3 = new ArrayDataBuffer<>(new float[][] { { 20 }, { 30 } }, true);
    DataBuffer<float[][]> buffer4 = new ArrayDataBuffer<>(new float[][][] { { { 10 } }, { { 30 } } }, true);
    DataBuffer<float[][]> buffer5 = new ArrayDataBuffer<>(new float[][][] { { { 10 } }, { { 30 } } }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }

  @Test
  public void doubleArrayBufferEquals() {
    DataBuffer<double[]> buffer1 = new ArrayDataBuffer<>(new double[][] { { 10 }, { 30 } }, true);
    DataBuffer<double[]> buffer2 = new ArrayDataBuffer<>(new double[][] { { 10 }, { 30 } }, true);
    DataBuffer<double[]> buffer3 = new ArrayDataBuffer<>(new double[][] { { 20 }, { 30 } }, true);
    DataBuffer<double[][]> buffer4 = new ArrayDataBuffer<>(new double[][][] { { { 10 } }, { { 30 } } }, true);
    DataBuffer<double[][]> buffer5 = new ArrayDataBuffer<>(new double[][][] { { { 10 } }, { { 30 } } }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }

  @Test
  public void booleanArrayBufferEquals() {
    DataBuffer<boolean[]> buffer1 = new ArrayDataBuffer<>(new boolean[][] { { true }, { false } }, true);
    DataBuffer<boolean[]> buffer2 = new ArrayDataBuffer<>(new boolean[][] { { true }, { false} }, true);
    DataBuffer<boolean[]> buffer3 = new ArrayDataBuffer<>(new boolean[][] { { false }, { false } }, true);
    DataBuffer<boolean[][]> buffer4 = new ArrayDataBuffer<>(new boolean[][][] { { { true } }, { { false } } }, true);
    DataBuffer<boolean[][]> buffer5 = new ArrayDataBuffer<>(new boolean[][][] { { { true } }, { { false } } }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }

  @Test
  public void objectArrayBufferEquals() {
    DataBuffer<String[]> buffer1 = new ArrayDataBuffer<>(new String[][] { { "10" }, { "30" } }, true);
    DataBuffer<String[]> buffer2 = new ArrayDataBuffer<>(new String[][] { { "10" }, { "30" } }, true);
    DataBuffer<String[]> buffer3 = new ArrayDataBuffer<>(new String[][] { { "20" }, { "30" } }, true);
    DataBuffer<String[][]> buffer4 = new ArrayDataBuffer<>(new String[][][] { { { "10" } }, { { "30" } } }, true);
    DataBuffer<String[][]> buffer5 = new ArrayDataBuffer<>(new String[][][] { { { "10" } }, { { "30" } } }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }

  @Test
  public void nullableObjectArrayBufferEquals() {
    DataBuffer<String[]> buffer1 = new ArrayDataBuffer<>(new String[][] { null, { "30" } }, true);
    DataBuffer<String[]> buffer2 = new ArrayDataBuffer<>(new String[][] { null, { "30" } }, true);
    DataBuffer<String[]> buffer3 = new ArrayDataBuffer<>(new String[][] { { "20" }, { "30" } }, true);
    DataBuffer<String[][]> buffer4 = new ArrayDataBuffer<>(new String[][][] { { { "10" } }, null }, true);
    DataBuffer<String[][]> buffer5 = new ArrayDataBuffer<>(new String[][][] { { { "10" } }, null }, true);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer1.equals(buffer3));
    assertFalse(buffer3.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer3.hashCode());

    assertFalse(buffer1.equals(buffer4));
    assertFalse(buffer4.equals(buffer1));
    assertNotEquals(buffer1.hashCode(), buffer4.hashCode());

    assertTrue(buffer4.equals(buffer5));
    assertTrue(buffer4.equals(buffer5));
    assertEquals(buffer4.hashCode(), buffer5.hashCode());
  }
}
