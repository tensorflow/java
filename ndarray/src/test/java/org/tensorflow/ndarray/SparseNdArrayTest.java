/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.ndarray;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.impl.sparse.BooleanSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.ByteSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.DoubleSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.FloatSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.IntSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.LongSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.ShortSparseNdArray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}, {2, 3}};
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  Shape shape = Shape.of(3, 4);
  double epsilon = 0.001;

  @Test
  public void testBoolean() {
    BooleanSparseNdArray instance =
        NdArrays.sparseOf(indices, NdArrays.vectorOf(true, true, true), shape);
    assertEquals(6, instance.getIndices().size());
    assertEquals(3, instance.getValues().size());
    assertTrue(instance.getBoolean(0, 0));
    assertFalse(instance.getBoolean(0, 1));
    assertFalse(instance.getBoolean(0, 2));
    assertFalse(instance.getBoolean(0, 3));

    assertFalse(instance.getBoolean(1, 0));
    assertFalse(instance.getBoolean(1, 1));
    assertTrue(instance.getBoolean(1, 2));
    assertFalse(instance.getBoolean(1, 3));

    assertFalse(instance.getBoolean(2, 0));
    assertFalse(instance.getBoolean(2, 1));
    assertFalse(instance.getBoolean(2, 2));
    assertTrue(instance.getBoolean(2, 3));
  }

  @Test
  public void testByte() {
    ByteSparseNdArray instance =
        NdArrays.sparseOf(
            indices, NdArrays.vectorOf((byte) 1, (byte) 18, (byte) 0xff), shape);
    assertEquals(6, instance.getIndices().size());
    assertEquals(3, instance.getValues().size());
    assertEquals((byte) 1, instance.getByte(0, 0));
    assertEquals((byte) 0, instance.getByte(0, 1));
    assertEquals((byte) 0, instance.getByte(0, 2));
    assertEquals((byte) 0, instance.getByte(0, 3));

    assertEquals((byte) 0, instance.getByte(1, 0));
    assertEquals((byte) 0, instance.getByte(1, 1));
    assertEquals((byte) 18, instance.getByte(1, 2));
    assertEquals((byte) 0, instance.getByte(1, 3));

    assertEquals((byte) 0, instance.getByte(2, 0));
    assertEquals((byte) 0, instance.getByte(2, 1));
    assertEquals((byte) 0, instance.getByte(2, 2));
    assertEquals((byte) 0xff, instance.getByte(2, 3));
  }

  @Test
  public void testDouble() {
    DoubleSparseNdArray instance =
        NdArrays.sparseOf(indices, NdArrays.vectorOf(1., 1.8, 3.14), shape);
    assertEquals(6, instance.getIndices().size());
    assertEquals(3, instance.getValues().size());
    assertEquals(1., instance.getDouble(0, 0), epsilon);
    assertEquals(0, instance.getDouble(0, 1), epsilon);
    assertEquals(0, instance.getDouble(0, 2), epsilon);
    assertEquals(0, instance.getDouble(0, 3), epsilon);

    assertEquals(0, instance.getDouble(1, 0), epsilon);
    assertEquals(0, instance.getDouble(1, 1), epsilon);
    assertEquals(1.8, instance.getDouble(1, 2), epsilon);
    assertEquals(0, instance.getDouble(1, 3), epsilon);

    assertEquals(0, instance.getDouble(2, 0), epsilon);
    assertEquals(0, instance.getDouble(2, 1), epsilon);
    assertEquals(0, instance.getDouble(2, 2), epsilon);
    assertEquals(3.14, instance.getDouble(2, 3), epsilon);
  }

  @Test
  public void testFloat() {
    FloatSparseNdArray instance =
        NdArrays.sparseOf(indices, NdArrays.vectorOf(1.f, 1.8f, 3.14f), shape);
    assertEquals(6, instance.getIndices().size());
    assertEquals(3, instance.getValues().size());
    assertEquals(1.f, instance.getFloat(0, 0), epsilon);
    assertEquals(0f, instance.getFloat(0, 1), epsilon);
    assertEquals(0f, instance.getFloat(0, 2), epsilon);
    assertEquals(0f, instance.getFloat(0, 3), epsilon);

    assertEquals(0f, instance.getFloat(1, 0), epsilon);
    assertEquals(0f, instance.getFloat(1, 1), epsilon);
    assertEquals(1.8f, instance.getFloat(1, 2), epsilon);
    assertEquals(0f, instance.getFloat(1, 3), epsilon);

    assertEquals(0f, instance.getFloat(2, 0), epsilon);
    assertEquals(0f, instance.getFloat(2, 1), epsilon);
    assertEquals(0f, instance.getFloat(2, 2), epsilon);
    assertEquals(3.14f, instance.getFloat(2, 3), epsilon);
  }

  @Test
  public void testInt() {
    IntSparseNdArray instance =
        NdArrays.sparseOf(indices, NdArrays.vectorOf(1, 18, 256), shape);
    assertEquals(6, instance.getIndices().size());
    assertEquals(3, instance.getValues().size());
    assertEquals(1, instance.getInt(0, 0));
    assertEquals(0, instance.getInt(0, 1));
    assertEquals(0, instance.getInt(0, 2));
    assertEquals(0, instance.getInt(0, 3));

    assertEquals(0, instance.getInt(1, 0));
    assertEquals(0, instance.getInt(1, 1));
    assertEquals(18, instance.getInt(1, 2));
    assertEquals(0, instance.getInt(1, 3));

    assertEquals(0, instance.getInt(2, 0));
    assertEquals(0, instance.getInt(2, 1));
    assertEquals(0, instance.getInt(2, 2));
    assertEquals(256, instance.getInt(2, 3));
  }

  @Test
  public void testLong() {
    LongSparseNdArray instance =
        NdArrays.sparseOf(indices, NdArrays.vectorOf(1L, 18L, 256L), shape);
    assertEquals(6, instance.getIndices().size());
    assertEquals(3, instance.getValues().size());
    assertEquals(1L, instance.getLong(0, 0));
    assertEquals(0L, instance.getLong(0, 1));
    assertEquals(0L, instance.getLong(0, 2));
    assertEquals(0L, instance.getLong(0, 3));

    assertEquals(0L, instance.getLong(1, 0));
    assertEquals(0L, instance.getLong(1, 1));
    assertEquals(18, instance.getLong(1, 2));
    assertEquals(0L, instance.getLong(1, 3));

    assertEquals(0L, instance.getLong(2, 0));
    assertEquals(0L, instance.getLong(2, 1));
    assertEquals(0L, instance.getLong(2, 2));
    assertEquals(256L, instance.getLong(2, 3));
  }

  @Test
  public void testShort() {
    ShortSparseNdArray instance =
        NdArrays.sparseOf(
            indices, NdArrays.vectorOf((short) 1, (short) 18, (short) 0xff00), shape);
    assertEquals(6, instance.getIndices().size());
    assertEquals(3, instance.getValues().size());
    assertEquals((short) 1, instance.getShort(0, 0));
    assertEquals((short) 0, instance.getShort(0, 1));
    assertEquals((short) 0, instance.getShort(0, 2));
    assertEquals((short) 0, instance.getShort(0, 3));

    assertEquals((short) 0, instance.getShort(1, 0));
    assertEquals((short) 0, instance.getShort(1, 1));
    assertEquals((short) 18, instance.getShort(1, 2));
    assertEquals((short) 0, instance.getShort(1, 3));

    assertEquals((short) 0, instance.getShort(2, 0));
    assertEquals((short) 0, instance.getShort(2, 1));
    assertEquals((short) 0, instance.getShort(2, 2));
    assertEquals((short) 0xff00, instance.getShort(2, 3));
  }

  @Test
  public void withShape() {
    NdArray<?> sparseArray = NdArrays.sparseOf(indices, NdArrays.vectorOf(1, 2, 3), shape);
    assertThrows(UnsupportedOperationException.class, () -> sparseArray.withShape(shape.prepend(1)));
  }
}
