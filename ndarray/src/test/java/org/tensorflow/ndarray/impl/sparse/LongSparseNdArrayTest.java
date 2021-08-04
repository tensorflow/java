package org.tensorflow.ndarray.impl.sparse;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.index.Indices;

import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LongSparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}};
  long[] valuesArray = {1, 2};
  long[] denseArray = {
    1, 0, 0, 0,
    0, 0, 2, 0,
    0, 0, 0, 0
  };

  long[][] dense2DArrayDefaultValue = {{1, -1, -1, -1}, {-1, -1, 2, -1}, {-1, -1, -1, -1}};

  Shape shape = Shape.of(3, 4);
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  LongNdArray values = StdArrays.ndCopyOf(valuesArray);

  @Test
  public void testBasic() {
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));
    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(shape, instance.shape());
  }

  @Test
  public void testRead() {
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));
    LongDataBuffer dataBuffer = DataBuffers.ofLongs(instance.shape().size());

    instance.read(dataBuffer);

    long[] array = new long[denseArray.length];
    dataBuffer.read(array);
    assertArrayEquals(denseArray, array);
  }

  @Test
  public void testWrite() {

    LongDataBuffer dataBuffer = NioDataBufferFactory.create(LongBuffer.wrap(denseArray));
    // use a zero buffer
    LongSparseNdArray instance = LongSparseNdArray.create(DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
  }

  @Test
  public void testWriteDefaultValue() {
    // change 0 to -1
    long[] denseArrayDefaultValue = Arrays.stream(denseArray).map(x -> x == 0 ? -1 : x).toArray();

    LongDataBuffer dataBuffer = RawDataBufferFactory.create(denseArrayDefaultValue, false);
    // use a zero buffer
    LongSparseNdArray instance = LongSparseNdArray.create(-1L, DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(-1L, instance.getLong(2, 0));
  }

  @Test
  public void testGetObject() {
    long[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    LongNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGetLong() {
    long[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    LongNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getLong(n, m), instance.getLong(n, m));
      }
    }
  }

  @Test
  public void testGetLongDefaultValue() {
    LongNdArray ndArray = StdArrays.ndCopyOf(dense2DArrayDefaultValue);
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, -1L, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getLong(n, m), instance.getLong(n, m));
      }
    }
  }

  @Test
  public void testGet() {
    long[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    LongNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      assertEquals(ndArray.get(n), instance.get(n));
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.get(n, m), instance.get(n, m));
      }
    }
  }

  @Test
  public void testSetObject() {
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(java.nio.ReadOnlyBufferException.class, () -> instance.setObject(2L, 0, 0));
  }

  @Test
  public void testSet() {
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(
        java.nio.ReadOnlyBufferException.class,
        () -> instance.set(instance.getDefaultArray(), 0, 0));
  }

  @Test
  public void testSort() {

    long[][] indicesArray = {{0, 0}, {1, 2}, {0, 1}, {2, 3}, {1, 4}};
    long[][] sortedIndicesArray = {{0, 0}, {0, 1}, {1, 2}, {1, 4}, {2, 3}};
    long[] valuesArray = {1, 3, 2, 5, 4};
    long[] sortedValuesArray = {1, 2, 3, 4, 5};

    LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
    LongNdArray sortedIndices = StdArrays.ndCopyOf(sortedIndicesArray);
    LongNdArray values = StdArrays.ndCopyOf(valuesArray);
    LongNdArray sortedValues = StdArrays.ndCopyOf(sortedValuesArray);

    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance.sortIndicesAndValues();

    // should be sorted in ascending row-wise coordinate order based on test values
    assertEquals(sortedIndices, instance.getIndices());
    assertEquals(sortedValues, instance.getValues());
  }

  @Test
  public void testElements() {

    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));

    long[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    instance
        .elements(0)
        .forEachIndexed(
            (idx, item) -> {
              long[] slice = dense2DArray[(int) idx[0]];
              item.scalars()
                  .forEachIndexed((dx, f) -> assertEquals(slice[(int) dx[0]], f.getObject()));
            });
  }

  @Test
  public void testDense() {
    long[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};

    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));
    LongNdArray denseInstance = instance.toDense();
    LongNdArray expectedDense = StdArrays.ndCopyOf(dense2DArray);
    assertEquals(expectedDense, denseInstance);
  }

  @Test
  public void testFromDense() {
    long[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    LongNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    LongSparseNdArray instance = LongSparseNdArray.create(DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    assertNotNull(instance.getIndices());
    assertEquals(2, instance.getIndices().shape().get(0));
    assertNotNull(instance.getValues());
    assertEquals(2, instance.getValues().size());

    assertEquals(ndArray.shape(), instance.shape());
    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getLong(n, m), instance.getLong(n, m));
      }
    }
  }

  @Test
  public void testElements1() {
    long[] expected = {1, 0, 0};
    long[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};

    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));
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
    LongNdArray dst = NdArrays.ofLongs(shape);
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));
    instance.copyTo(dst);
    for (int n = 0; n < instance.shape().get(0); n++) {
      for (int m = 0; m < instance.shape().get(1); m++) {
        assertEquals(instance.getLong(n, m), dst.getLong(n, m));
      }
    }
  }

  @Test
  public void testCreate() {
    long[] denseArray = {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0};
    long[][] dense2Array = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));
    LongSparseNdArray instanceA =
        LongSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
    assertEquals(instance, instanceA);

    LongDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    LongSparseNdArray instanceB = LongSparseNdArray.create(DimensionalSpace.create(shape));
    instanceB.write(dataBuffer);
    assertEquals(instance, instanceB);

    LongSparseNdArray instanceC =
        LongSparseNdArray.create(dataBuffer, DimensionalSpace.create(shape));
    assertEquals(instanceB, instanceC);

    LongSparseNdArray instanceD = LongSparseNdArray.create(dataBuffer, shape);
    assertEquals(instanceB, instanceD);

    LongNdArray ndArray = StdArrays.ndCopyOf(dense2Array);
    LongSparseNdArray instanceE = LongSparseNdArray.create(ndArray);
    assertEquals(instance, instanceE);
  }

  @Test
  public void testSlice() {
    long[] expected = {0, 0, 2, 0, 0, 0};
    LongSparseNdArray instance =
        new LongSparseNdArray(indices, values, DimensionalSpace.create(shape));

    LongNdArray sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original sparse array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getLong()));
    // check values from elements(0) of a slice against the  original sparse array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getLong())));
  }
}
