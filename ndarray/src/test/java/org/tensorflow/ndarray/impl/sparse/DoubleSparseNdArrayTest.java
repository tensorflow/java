package org.tensorflow.ndarray.impl.sparse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.index.Indices;

import java.nio.DoubleBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoubleSparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}};
  double[] valuesArray = {1, 256};
  double[] denseArray = {
    1, 0, 0, 0,
    0, 0, 256, 0,
    0, 0, 0, 0
  };
  double[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 256, 0}, {0, 0, 0, 0}};

  double[][] dense2DArrayDefaultValue = {{1, -1, -1, -1}, {-1, -1, 256, -1}, {-1, -1, -1, -1}};

  Shape shape = Shape.of(3, 4);
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  DoubleNdArray values = StdArrays.ndCopyOf(valuesArray);

  @Test
  public void testBasic() {
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));
    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(shape, instance.shape());
  }

  @Test
  public void testRead() {
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));
    DoubleDataBuffer dataBuffer = DataBuffers.ofDoubles(instance.shape().size());

    instance.read(dataBuffer);

    double[] array = new double[denseArray.length];
    dataBuffer.read(array);
    assertArrayEquals(denseArray, array);
  }

  @Test
  public void testWrite() {

    DoubleDataBuffer dataBuffer = NioDataBufferFactory.create(DoubleBuffer.wrap(denseArray));
    // use a zero buffer
    DoubleSparseNdArray instance = DoubleSparseNdArray.create(DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
  }

  @Test
  public void testWriteDefaultValue() {
    // change 0 to -1
    double[] denseArrayDefaultValue = Arrays.stream(denseArray).map(x -> x == 0 ? -1 : x).toArray();
    DoubleDataBuffer dataBuffer = RawDataBufferFactory.create(denseArrayDefaultValue, false);
    // use a zero buffer
    DoubleSparseNdArray instance = DoubleSparseNdArray.create(-1d, DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(-1d, instance.getDouble(2, 0));
  }

  @Test
  public void testGetObject() {

    DoubleNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGetDouble() {
    DoubleNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getDouble(n, m), instance.getDouble(n, m));
      }
    }
  }

  @Test
  public void testGetDoubleDefaultValue() {
    DoubleNdArray ndArray = StdArrays.ndCopyOf(dense2DArrayDefaultValue);
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, -1d, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getDouble(n, m), instance.getDouble(n, m));
      }
    }
  }

  @Test
  public void testGet() {
    DoubleNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      assertEquals(ndArray.get(n), instance.get(n));
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.get(n, m), instance.get(n, m));
      }
    }
  }

  @Test
  public void testSetObject() {
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(java.nio.ReadOnlyBufferException.class, () -> instance.setObject(2d, 0, 0));
  }

  @Test
  public void testSet() {
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(
        java.nio.ReadOnlyBufferException.class,
        () -> instance.set(instance.getDefaultArray(), 0, 0));
  }

  @Test
  public void testSort() {

    long[][] indicesArray = {{0, 0}, {1, 2}, {0, 1}, {2, 3}, {1, 4}};
    long[][] sortedIndicesArray = {{0, 0}, {0, 1}, {1, 2}, {1, 4}, {2, 3}};
    double[] valuesArray = {1, 3, 2, 5, 4};
    double[] sortedValuesArray = {1, 2, 3, 4, 5};

    LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
    LongNdArray sortedIndices = StdArrays.ndCopyOf(sortedIndicesArray);
    DoubleNdArray values = StdArrays.ndCopyOf(valuesArray);
    DoubleNdArray sortedValues = StdArrays.ndCopyOf(sortedValuesArray);

    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance.sortIndicesAndValues();

    // should be sorted in ascending row-wise coordinate order based on test values
    assertEquals(sortedIndices, instance.getIndices());
    assertEquals(sortedValues, instance.getValues());
  }

  @Test
  public void testElements() {

    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance
        .elements(0)
        .forEachIndexed(
            (idx, item) -> {
              double[] slice = dense2DArray[(int) idx[0]];
              item.scalars()
                  .forEachIndexed((dx, f) -> assertEquals(slice[(int) dx[0]], f.getObject()));
            });
  }

  @Test
  public void testDense() {

    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));
    DoubleNdArray denseInstance = instance.toDense();
    DoubleNdArray expectedDense = StdArrays.ndCopyOf(dense2DArray);
    assertEquals(expectedDense, denseInstance);
  }

  @Test
  public void testFromDense() {
    DoubleNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    DoubleSparseNdArray instance =
        DoubleSparseNdArray.create(DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    assertNotNull(instance.getIndices());
    assertEquals(2, instance.getIndices().shape().get(0));
    assertNotNull(instance.getValues());
    assertEquals(2, instance.getValues().size());

    assertEquals(ndArray.shape(), instance.shape());
    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getDouble(n, m), instance.getDouble(n, m));
      }
    }
  }

  @Test
  public void testElements1() {
    double[] expected = {1, 0, 0};

    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));
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
    DoubleNdArray dst = NdArrays.ofDoubles(shape);
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));
    instance.copyTo(dst);
    for (int n = 0; n < instance.shape().get(0); n++) {
      for (int m = 0; m < instance.shape().get(1); m++) {
        assertEquals(instance.getDouble(n, m), dst.getDouble(n, m));
      }
    }
  }

  @Test
  public void testCreate() {
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));
    DoubleSparseNdArray instanceA =
        DoubleSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
    assertEquals(instance, instanceA);

    DoubleDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    DoubleSparseNdArray instanceB = DoubleSparseNdArray.create(DimensionalSpace.create(shape));
    instanceB.write(dataBuffer);
    assertEquals(instance, instanceB);

    DoubleSparseNdArray instanceC =
        DoubleSparseNdArray.create(dataBuffer, DimensionalSpace.create(shape));
    assertEquals(instanceB, instanceC);

    DoubleSparseNdArray instanceD = DoubleSparseNdArray.create(dataBuffer, shape);
    assertEquals(instanceB, instanceD);

    DoubleNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    DoubleSparseNdArray instanceE = DoubleSparseNdArray.create(ndArray);
    assertEquals(instance, instanceE);
  }

  @Test
  public void testSlice() {
    double[] expected = {0, 0, 256, 0, 0, 0};
    DoubleSparseNdArray instance =
        new DoubleSparseNdArray(indices, values, DimensionalSpace.create(shape));

    DoubleNdArray sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original sparse array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getDouble()));
    // check values from elements(0) of a slice against the  original sparse array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getDouble())));
  }

  @Test
  public void testToString() {
    DoubleNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    DoubleSparseNdArray instance =
            DoubleSparseNdArray.create(DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    Assertions.assertEquals("DoubleSparseNdArray(defaultValue=0.0, numElements=2, shape=[3, 4])",instance.toString());
    DoubleSparseNdArray empty = DoubleSparseNdArray.create(DimensionalSpace.create(Shape.of(5)));
    Assertions.assertEquals("DoubleSparseNdArray(defaultValue=0.0, numElements=0, shape=[5])",empty.toString());
  }
}
