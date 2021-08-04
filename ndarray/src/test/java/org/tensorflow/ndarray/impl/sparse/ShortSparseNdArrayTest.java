package org.tensorflow.ndarray.impl.sparse;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.ShortNdArray;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.index.Indices;

import java.nio.ShortBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShortSparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}};
  short[] valuesArray = {1, 2};
  short[] denseArray = {
    1, 0, 0, 0,
    0, 0, 2, 0,
    0, 0, 0, 0
  };
  short[][] dense2DArrayDefaultValue = {{1, -1, -1, -1}, {-1, -1, 2, -1}, {-1, -1, -1, -1}};

  Shape shape = Shape.of(3, 4);
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  ShortNdArray values = StdArrays.ndCopyOf(valuesArray);

  @Test
  public void testBasic() {
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));
    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(shape, instance.shape());
  }

  @Test
  public void testRead() {
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));
    ShortDataBuffer dataBuffer = DataBuffers.ofShorts(instance.shape().size());

    instance.read(dataBuffer);

    short[] array = new short[denseArray.length];
    dataBuffer.read(array);
    assertArrayEquals(denseArray, array);
  }

  @Test
  public void testWrite() {

    ShortDataBuffer dataBuffer = NioDataBufferFactory.create(ShortBuffer.wrap(denseArray));
    // use a zero buffer
    ShortSparseNdArray instance = ShortSparseNdArray.create(DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
  }

  @Test
  public void testWriteDefaultValue() {
    // change 0 to -1
    short[] denseArrayDefaultValue = new short[denseArray.length];
    for (int i = 0; i < denseArrayDefaultValue.length; i++) {
      denseArrayDefaultValue[i] = denseArray[i] == 0 ? (short) -1 : denseArray[i];
    }

    ShortDataBuffer dataBuffer = RawDataBufferFactory.create(denseArrayDefaultValue, false);
    // use a zero buffer
    ShortSparseNdArray instance =
        ShortSparseNdArray.create((short) -1, DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals((short) -1, instance.getShort(2, 0));
  }

  @Test
  public void testGetObject() {
    short[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    ShortNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGetShort() {
    short[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    ShortNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getShort(n, m), instance.getShort(n, m));
      }
    }
  }

  @Test
  public void testGetShortDefaultValue() {
    ShortNdArray ndArray = StdArrays.ndCopyOf(dense2DArrayDefaultValue);
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, (short) -1, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getShort(n, m), instance.getShort(n, m));
      }
    }
  }

  @Test
  public void testGet() {
    short[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    ShortNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      assertEquals(ndArray.get(n), instance.get(n));
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.get(n, m), instance.get(n, m));
      }
    }
  }

  @Test
  public void testSetObject() {
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(java.nio.ReadOnlyBufferException.class, () -> instance.setObject((short) 2, 0, 0));
  }

  @Test
  public void testSet() {
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(
        java.nio.ReadOnlyBufferException.class,
        () -> instance.set(instance.getDefaultArray(), 0, 0));
  }

  @Test
  public void testSort() {

    long[][] indicesArray = {{0, 0}, {1, 2}, {0, 1}, {2, 3}, {1, 4}};
    long[][] sortedIndicesArray = {{0, 0}, {0, 1}, {1, 2}, {1, 4}, {2, 3}};
    short[] valuesArray = {1, 3, 2, 5, 4};
    short[] sortedValuesArray = {1, 2, 3, 4, 5};

    LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
    LongNdArray sortedIndices = StdArrays.ndCopyOf(sortedIndicesArray);
    ShortNdArray values = StdArrays.ndCopyOf(valuesArray);
    ShortNdArray sortedValues = StdArrays.ndCopyOf(sortedValuesArray);

    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance.sortIndicesAndValues();

    // should be sorted in ascending row-wise coordinate order based on test values
    assertEquals(sortedIndices, instance.getIndices());
    assertEquals(sortedValues, instance.getValues());
  }

  @Test
  public void testElements() {

    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));

    short[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    instance
        .elements(0)
        .forEachIndexed(
            (idx, item) -> {
              short[] slice = dense2DArray[(int) idx[0]];
              item.scalars()
                  .forEachIndexed((dx, f) -> assertEquals(slice[(int) dx[0]], f.getObject()));
            });
  }

  @Test
  public void testDense() {
    short[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};

    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));
    ShortNdArray denseInstance = instance.toDense();
    ShortNdArray expectedDense = StdArrays.ndCopyOf(dense2DArray);
    assertEquals(expectedDense, denseInstance);
  }

  @Test
  public void testFromDense() {
    short[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    ShortNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ShortSparseNdArray instance =
        ShortSparseNdArray.create(DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    assertNotNull(instance.getIndices());
    assertEquals(2, instance.getIndices().shape().get(0));
    assertNotNull(instance.getValues());
    assertEquals(2, instance.getValues().size());

    assertEquals(ndArray.shape(), instance.shape());
    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getShort(n, m), instance.getShort(n, m));
      }
    }
  }

  @Test
  public void testElements1() {
    short[] expected = {1, 0, 0};
    short[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};

    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));
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
    ShortNdArray dst = NdArrays.ofShorts(shape);
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));
    instance.copyTo(dst);
    for (int n = 0; n < instance.shape().get(0); n++) {
      for (int m = 0; m < instance.shape().get(1); m++) {
        assertEquals(instance.getShort(n, m), dst.getShort(n, m));
      }
    }
  }

  @Test
  public void testCreate() {
    short[] denseArray = {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0};
    short[][] dense2Array = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));
    ShortSparseNdArray instanceA =
        ShortSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
    assertEquals(instance, instanceA);

    ShortDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    ShortSparseNdArray instanceB = ShortSparseNdArray.create(DimensionalSpace.create(shape));
    instanceB.write(dataBuffer);
    assertEquals(instance, instanceB);

    ShortSparseNdArray instanceC =
        ShortSparseNdArray.create(dataBuffer, DimensionalSpace.create(shape));
    assertEquals(instanceB, instanceC);

    ShortSparseNdArray instanceD = ShortSparseNdArray.create(dataBuffer, shape);
    assertEquals(instanceB, instanceD);

    ShortNdArray ndArray = StdArrays.ndCopyOf(dense2Array);
    ShortSparseNdArray instanceE = ShortSparseNdArray.create(ndArray);
    assertEquals(instance, instanceE);
  }

  @Test
  public void testSlice() {
    short[] expected = {0, 0, 2, 0, 0, 0};
    ShortSparseNdArray instance =
        new ShortSparseNdArray(indices, values, DimensionalSpace.create(shape));

    ShortNdArray sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original sparse array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getShort()));
    // check values from elements(0) of a slice against the  original sparse array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getShort())));
  }
}
