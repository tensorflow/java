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
package org.tensorflow.ndarray.impl.sparse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.index.Indices;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringSparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}};
  String[] valuesArray = {"alpha", "omega"};
  String[] denseArray = {
    "alpha", null, null, null, null, null, "omega", null, null, null, null, null
  };
  String[][] dense2DArray = {
    {"alpha", null, null, null}, {null, null, "omega", null}, {null, null, null, null}
  };

  String[][] dense2DArrayDefault = {
    {"alpha", "default", "default", "default"},
    {"default", "default", "omega", "default"},
    {"default", "default", "default", "default"}
  };

  Shape shape = Shape.of(3, 4);
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  NdArray<String> values = StdArrays.ndCopyOf(valuesArray);

  @Test
  public void testBasic() {
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));
    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(shape, instance.shape());
  }

  @Test
  public void testRead() {
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));
    DataBuffer<String> dataBuffer = DataBuffers.ofObjects(String.class, instance.shape().size());

    instance.read(dataBuffer);

    String[] array = new String[denseArray.length];
    dataBuffer.read(array);
    assertArrayEquals(denseArray, array);
  }

  @Test
  public void testWrite() {

    DataBuffer<String> dataBuffer = DataBuffers.ofObjects(denseArray);
    // use a zero buffer
    SparseNdArray<String, NdArray<String>> instance =
        SparseNdArray.create(String.class, DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(indices.shape().get(0), values.size());
    assertEquals(2, values.size());
  }

  @Test
  public void testWriteDefaultValue() {
    String defaultValue = "default";
    String[] denseArrayDefaultValue = new String[denseArray.length];
    for (int i = 0; i < denseArrayDefaultValue.length; i++) {
      denseArrayDefaultValue[i] = denseArray[i] == null ? defaultValue : denseArray[i];
    }

    DataBuffer<String> dataBuffer = DataBuffers.ofObjects(denseArrayDefaultValue);
    // use a zero buffer
    SparseNdArray<String, NdArray<String>> instance =
        SparseNdArray.create(String.class, defaultValue, DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(2, values.size());
    assertEquals(indices.shape().get(0), values.size());
  }

  @Test
  public void testGetObject() {
    NdArray<String> ndArray = StdArrays.ndCopyOf(dense2DArray);
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGetObjectDefaultValue() {
    String defaultValue = "default";

    NdArray<String> ndArray = StdArrays.ndCopyOf(dense2DArrayDefault);
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(
            String.class, indices, values, defaultValue, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGet() {
    NdArray<String> ndArray = StdArrays.ndCopyOf(dense2DArray);
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      assertEquals(ndArray.get(n), instance.get(n));
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.get(n, m), instance.get(n, m));
      }
    }
  }

  @Test
  public void testSetObject() {
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));

    assertThrows(java.nio.ReadOnlyBufferException.class, () -> instance.setObject(null, 0, 0));
  }

  @Test
  public void testSet() {
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));

    assertThrows(
        java.nio.ReadOnlyBufferException.class,
        () -> instance.set(instance.getDefaultArray(), 0, 0));
  }

  @Test
  public void testSort() {

    long[][] indicesArray = {{0, 0}, {1, 2}, {0, 1}, {2, 3}, {1, 4}};
    long[][] sortedIndicesArray = {{0, 0}, {0, 1}, {1, 2}, {1, 4}, {2, 3}};
    String[] valuesArray = {"b", "d", "a", null, "c"};
    String[] sortedValuesArray = {"b", "a", "d", "c", null};

    LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
    LongNdArray sortedIndices = StdArrays.ndCopyOf(sortedIndicesArray);
    NdArray<String> values = StdArrays.ndCopyOf(valuesArray);
    NdArray<String> sortedValues = StdArrays.ndCopyOf(sortedValuesArray);

    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));

    instance.sortIndicesAndValues();

    // should be sorted in ascending row-wise coordinate order based on test values
    assertEquals(sortedIndices, instance.getIndices());
    assertEquals(sortedValues, instance.getValues());
  }

  @Test
  public void testElements() {

    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));

    instance
        .elements(0)
        .forEachIndexed(
            (idx, item) -> {
              String[] slice = dense2DArray[(int) idx[0]];
              item.scalars()
                  .forEachIndexed((dx, f) -> assertEquals(slice[(int) dx[0]], f.getObject()));
            });
  }

  @Test
  public void testDense() {

    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));
    NdArray<String> denseInstance = instance.toDense();
    NdArray<String> expectedDense = StdArrays.ndCopyOf(dense2DArray);
    assertEquals(expectedDense, denseInstance);
  }

  @Test
  public void testFromDense() {
    NdArray<String> ndArray = StdArrays.ndCopyOf(dense2DArray);
    SparseNdArray<String, NdArray<String>> instance =
        SparseNdArray.create(String.class, DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    assertNotNull(instance.getIndices());
    assertEquals(2, instance.getIndices().shape().get(0));
    assertNotNull(instance.getValues());
    assertEquals(2, instance.getValues().size());

    assertEquals(ndArray.shape(), instance.shape());
    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testElements1() {
    String[] expected = {"alpha", null, null};

    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));
    instance
        .elements(0)
        .forEachIndexed((idx, l) -> assertEquals(expected[(int) idx[0]], l.getObject()));
    instance
        .elements(1)
        .forEachIndexed(
            (idx, l) -> assertEquals(dense2DArray[(int) idx[0]][(int) idx[1]], l.getObject()));
  }

  @Test
  public void testCopyTo() {
    NdArray<String> dst = NdArrays.ofObjects(String.class, shape);
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));
    instance.copyTo(dst);
    for (int n = 0; n < instance.shape().get(0); n++) {
      for (int m = 0; m < instance.shape().get(1); m++) {
        assertEquals(instance.getObject(n, m), dst.getObject(n, m));
      }
    }
  }

  @Test
  public void testCreate() {

    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));
    SparseNdArray<String, NdArray<String>> instanceA =
        SparseNdArray.create(String.class, indices, values, DimensionalSpace.create(shape));
    assertEquals(instance, instanceA);

    DataBuffer<String> dataBuffer = DataBuffers.ofObjects(denseArray);

    // use a zero buffer
    SparseNdArray<String, NdArray<String>> instanceB =
        SparseNdArray.create(String.class, DimensionalSpace.create(shape));
    instanceB.write(dataBuffer);
    assertEquals(instance, instanceB);

    SparseNdArray<String, NdArray<String>> instanceC =
        SparseNdArray.create(String.class, dataBuffer, DimensionalSpace.create(shape));
    assertEquals(instanceB, instanceC);

    SparseNdArray<String, NdArray<String>> instanceD =
        SparseNdArray.create(String.class, dataBuffer, shape);
    assertEquals(instanceB, instanceD);

    NdArray<String> ndArray = StdArrays.ndCopyOf(dense2DArray);
    SparseNdArray<String, NdArray<String>> instanceE = SparseNdArray.create(String.class, ndArray);
    assertEquals(instance, instanceE);
  }

  @Test
  public void testSlice() {
    String[] expected = {null, null, "omega", null, null, null};
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));

    NdArray<String> sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original sparse array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getObject()));
    // check values from elements(0) of a slice against the  original sparse array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getObject())));
  }

  @Test
  public void testNullDefault() {
    SparseNdArray<String, NdArray<String>> instance =
        new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));

    NdArray<String> dArray = instance.getDefaultArray();
    assertEquals(1L, dArray.size());
    assertNull(dArray.getObject());

    instance =
        new SparseNdArray<>(
            String.class, indices, values, "a default", DimensionalSpace.create(shape));

    dArray = instance.getDefaultArray();
    assertEquals(1L, dArray.size());
    assertNotNull(dArray.getObject());
    assertEquals("a default", dArray.getObject());
  }

  @Test
  public void testToString() {
    SparseNdArray<String, NdArray<String>> instance =
            new SparseNdArray<>(String.class, indices, values, DimensionalSpace.create(shape));
    Assertions.assertEquals("SparseNdArray(type=String, defaultValue=<null>, numElements=2, shape=[3, 4])",instance.toString());
    instance = new SparseNdArray<>(
                    String.class, indices, values, "a default", DimensionalSpace.create(shape));
    Assertions.assertEquals("SparseNdArray(type=String, defaultValue='a default', numElements=2, shape=[3, 4])",instance.toString());
  }
}
