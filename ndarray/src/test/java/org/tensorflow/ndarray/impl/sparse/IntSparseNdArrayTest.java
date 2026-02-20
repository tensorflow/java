package org.tensorflow.ndarray.impl.sparse;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.index.Indices;

import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntSparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}};
  int[] valuesArray = {1, 2};
  int[] denseArray = {
    1, 0, 0, 0,
    0, 0, 2, 0,
    0, 0, 0, 0
  };

  int[][] dense2DArrayDefaultValue = {{1, -1, -1, -1}, {-1, -1, 2, -1}, {-1, -1, -1, -1}};

  Shape shape = Shape.of(3, 4);
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  IntNdArray values = StdArrays.ndCopyOf(valuesArray);

  @Test
  public void testBasic() {
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));
    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(shape, instance.shape());
  }

  @Test
  public void testCopyToBuffer() {
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));
    IntDataBuffer dataBuffer = DataBuffers.ofInts(instance.shape().size());

    instance.copyTo(dataBuffer);

    int[] array = new int[denseArray.length];
    dataBuffer.read(array);
    assertArrayEquals(denseArray, array);
  }

  @Test
  public void testCopyFromBufferBuffer() {

    IntDataBuffer dataBuffer = NioDataBufferFactory.create(IntBuffer.wrap(denseArray));
    // use a zero buffer
    IntSparseNdArray instance = IntSparseNdArray.create(DimensionalSpace.create(shape));
    instance.copyFrom(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
  }

  @Test
  public void testWriteDefaultValue() {
    // change 0 to -1
    int[] denseArrayDefaultValue = Arrays.stream(denseArray).map(x -> x == 0 ? -1 : x).toArray();

    IntDataBuffer dataBuffer = RawDataBufferFactory.create(denseArrayDefaultValue, false);
    // use a zero buffer
    IntSparseNdArray instance = IntSparseNdArray.create(-1, DimensionalSpace.create(shape));
    instance.copyFrom(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(-1, instance.getInt(2, 0));
  }

  @Test
  public void testGetObject() {
    int[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    IntNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGetInt() {
    int[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    IntNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getInt(n, m), instance.getInt(n, m));
      }
    }
  }

  @Test
  public void testGetIntDefaultValue() {
    IntNdArray ndArray = StdArrays.ndCopyOf(dense2DArrayDefaultValue);
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, -1, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getInt(n, m), instance.getInt(n, m));
      }
    }
  }

  @Test
  public void testGet() {
    int[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    IntNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      assertEquals(ndArray.get(n), instance.get(n));
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.get(n, m), instance.get(n, m));
      }
    }
  }

  @Test
  public void testSetObject() {
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(java.nio.ReadOnlyBufferException.class, () -> instance.setObject(2, 0, 0));
  }

  @Test
  public void testSet() {
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(
        java.nio.ReadOnlyBufferException.class,
        () -> instance.set(instance.getDefaultArray(), 0, 0));
  }

  @Test
  public void testSort() {

    long[][] indicesArray = {{0, 0}, {1, 2}, {0, 1}, {2, 3}, {1, 4}};
    long[][] sortedIndicesArray = {{0, 0}, {0, 1}, {1, 2}, {1, 4}, {2, 3}};
    int[] valuesArray = {1, 3, 2, 5, 4};
    int[] sortedValuesArray = {1, 2, 3, 4, 5};

    LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
    LongNdArray sortedIndices = StdArrays.ndCopyOf(sortedIndicesArray);
    IntNdArray values = StdArrays.ndCopyOf(valuesArray);
    IntNdArray sortedValues = StdArrays.ndCopyOf(sortedValuesArray);

    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance.sortIndicesAndValues();

    // should be sorted in ascending row-wise coordinate order based on test values
    assertEquals(sortedIndices, instance.getIndices());
    assertEquals(sortedValues, instance.getValues());
  }

  @Test
  public void testElements() {

    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));

    int[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    instance
        .elements(0)
        .forEachIndexed(
            (idx, item) -> {
              int[] slice = dense2DArray[(int) idx[0]];
              item.scalars()
                  .forEachIndexed((dx, f) -> assertEquals(slice[(int) dx[0]], f.getObject()));
            });
  }

  @Test
  public void testDense() {
    int[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};

    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));
    IntNdArray denseInstance = instance.toDense();
    IntNdArray expectedDense = StdArrays.ndCopyOf(dense2DArray);
    assertEquals(expectedDense, denseInstance);
  }

  @Test
  public void testFromDense() {
    int[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    IntNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    IntSparseNdArray instance = IntSparseNdArray.create(DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    assertNotNull(instance.getIndices());
    assertEquals(2, instance.getIndices().shape().get(0));
    assertNotNull(instance.getValues());
    assertEquals(2, instance.getValues().size());

    assertEquals(ndArray.shape(), instance.shape());
    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getInt(n, m), instance.getInt(n, m));
      }
    }
  }

  @Test
  public void testElements1() {
    int[] expected = {1, 0, 0};
    int[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};

    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));
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
    IntNdArray dst = NdArrays.ofInts(shape);
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));
    instance.copyTo(dst);
    for (int n = 0; n < instance.shape().get(0); n++) {
      for (int m = 0; m < instance.shape().get(1); m++) {
        assertEquals(instance.getInt(n, m), dst.getInt(n, m));
      }
    }
  }

  @Test
  public void testCreate() {
    int[] denseArray = {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0};
    int[][] dense2Array = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));
    IntSparseNdArray instanceA =
        IntSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
    assertEquals(instance, instanceA);

    IntDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    IntSparseNdArray instanceB = IntSparseNdArray.create(DimensionalSpace.create(shape));
    instanceB.copyFrom(dataBuffer);
    assertEquals(instance, instanceB);

    IntSparseNdArray instanceC =
        IntSparseNdArray.create(dataBuffer, DimensionalSpace.create(shape));
    assertEquals(instanceB, instanceC);

    IntSparseNdArray instanceD = IntSparseNdArray.create(dataBuffer, shape);
    assertEquals(instanceB, instanceD);

    IntNdArray ndArray = StdArrays.ndCopyOf(dense2Array);
    IntSparseNdArray instanceE = IntSparseNdArray.create(ndArray);
    assertEquals(instance, instanceE);
  }

  @Test
  public void testSlice() {
    int[] expected = {0, 0, 2, 0, 0, 0};
    IntSparseNdArray instance =
        new IntSparseNdArray(indices, values, DimensionalSpace.create(shape));

    IntNdArray sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original sparse array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getInt()));
    // check values from elements(0) of a slice against the  original sparse array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getInt())));
  }
}
