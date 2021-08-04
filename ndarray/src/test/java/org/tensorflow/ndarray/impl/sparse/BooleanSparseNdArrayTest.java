package org.tensorflow.ndarray.impl.sparse;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.index.Indices;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BooleanSparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}};
  boolean[] valuesArray = {true, true};
  boolean[] valuesArrayDefaultValue = {false, false};
  boolean[] denseArray = {
    true, false, false, false,
    false, false, true, false,
    false, false, false, false
  };
  boolean[][] dense2DArray = {
    {true, false, false, false}, {false, false, true, false}, {false, false, false, false}
  };

  Shape shape = Shape.of(3, 4);
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  BooleanNdArray values = StdArrays.ndCopyOf(valuesArray);

  @Test
  public void testBasic() {
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));
    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(shape, instance.shape());
  }

  @Test
  public void testRead() {
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));
    BooleanDataBuffer dataBuffer = DataBuffers.ofBooleans(instance.shape().size());

    instance.read(dataBuffer);

    boolean[] array = new boolean[denseArray.length];
    dataBuffer.read(array);
    assertArrayEquals(denseArray, array);
  }

  @Test
  public void testWrite() {

    BooleanDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    BooleanSparseNdArray instance = BooleanSparseNdArray.create(DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
  }

  @Test
  public void testWriteDefaultValue() {
    // invert true/false
    boolean[] denseArrayDefaultValue = new boolean[denseArray.length];
    for (int i = 0; i < denseArrayDefaultValue.length; i++) {
      denseArrayDefaultValue[i] = !denseArray[i];
    }

    BooleanNdArray valuesDefault = StdArrays.ndCopyOf(new boolean[] {false, false});
    BooleanDataBuffer dataBuffer = RawDataBufferFactory.create(denseArrayDefaultValue, false);
    // use a zero buffer
    BooleanSparseNdArray instance =
        BooleanSparseNdArray.create(true, DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(valuesDefault, instance.getValues());
  }

  @Test
  public void testGetObject() {
    BooleanNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGetBoolean() {
    BooleanNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getBoolean(n, m), instance.getBoolean(n, m));
      }
    }
  }

  @Test
  public void testGetBooleanDefaultValue() {
    // flip the truth table
    BooleanNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(
            indices,
            NdArrays.vectorOf(valuesArrayDefaultValue),
            true,
            DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertNotEquals(ndArray.getBoolean(n, m), instance.getBoolean(n, m));
      }
    }
  }

  @Test
  public void testGet() {
    BooleanNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      assertEquals(ndArray.get(n), instance.get(n));
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.get(n, m), instance.get(n, m));
      }
    }
  }

  @Test
  public void testSetObject() {
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(java.nio.ReadOnlyBufferException.class, () -> instance.setObject(false, 0, 0));
  }

  @Test
  public void testSet() {
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(
        java.nio.ReadOnlyBufferException.class,
        () -> instance.set(instance.getDefaultArray(), 0, 0));
  }

  @Test
  public void testSort() {

    long[][] indicesArray = {{0, 0}, {1, 2}, {0, 1}, {2, 3}, {1, 4}};
    long[][] sortedIndicesArray = {{0, 0}, {0, 1}, {1, 2}, {1, 4}, {2, 3}};
    boolean[] valuesArray = {true, true, false, true, false};
    boolean[] sortedValuesArray = {true, false, true, false, true};

    LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
    LongNdArray sortedIndices = StdArrays.ndCopyOf(sortedIndicesArray);
    BooleanNdArray values = StdArrays.ndCopyOf(valuesArray);
    BooleanNdArray sortedValues = StdArrays.ndCopyOf(sortedValuesArray);

    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance.sortIndicesAndValues();

    // should be sorted in ascending row-wise coordinate order based on test values
    assertEquals(sortedIndices, instance.getIndices());
    assertEquals(sortedValues, instance.getValues());
  }

  @Test
  public void testElements() {

    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance
        .elements(0)
        .forEachIndexed(
            (idx, item) -> {
              boolean[] slice = dense2DArray[(int) idx[0]];
              item.scalars()
                  .forEachIndexed((dx, f) -> assertEquals(slice[(int) dx[0]], f.getObject()));
            });
  }

  @Test
  public void testDense() {

    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));
    BooleanNdArray denseInstance = instance.toDense();
    BooleanNdArray expectedDense = StdArrays.ndCopyOf(dense2DArray);
    assertEquals(expectedDense, denseInstance);
  }

  @Test
  public void testFromDense() {
    BooleanNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    BooleanSparseNdArray instance =
        BooleanSparseNdArray.create(DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    assertNotNull(instance.getIndices());
    assertEquals(2, instance.getIndices().shape().get(0));
    assertNotNull(instance.getValues());
    assertEquals(2, instance.getValues().size());

    assertEquals(ndArray.shape(), instance.shape());
    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getBoolean(n, m), instance.getBoolean(n, m));
      }
    }
  }

  @Test
  public void testElements1() {
    boolean[] expected = {true, false, false};

    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));
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
    BooleanNdArray dst = NdArrays.ofBooleans(shape);
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));
    instance.copyTo(dst);
    for (int n = 0; n < instance.shape().get(0); n++) {
      for (int m = 0; m < instance.shape().get(1); m++) {
        assertEquals(instance.getBoolean(n, m), dst.getBoolean(n, m));
      }
    }
  }

  @Test
  public void testCreate() {

    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));
    BooleanSparseNdArray instanceA =
        BooleanSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
    assertEquals(instance, instanceA);

    BooleanDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    BooleanSparseNdArray instanceB = BooleanSparseNdArray.create(DimensionalSpace.create(shape));
    instanceB.write(dataBuffer);
    assertEquals(instance, instanceB);

    BooleanSparseNdArray instanceC =
        BooleanSparseNdArray.create(dataBuffer, DimensionalSpace.create(shape));
    assertEquals(instanceB, instanceC);

    BooleanSparseNdArray instanceD = BooleanSparseNdArray.create(dataBuffer, shape);
    assertEquals(instanceB, instanceD);

    BooleanNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    BooleanSparseNdArray instanceE = BooleanSparseNdArray.create(ndArray);
    assertEquals(instance, instanceE);
  }

  @Test
  public void testSlice() {
    boolean[] expected = {false, false, true, false, false, false};
    BooleanSparseNdArray instance =
        new BooleanSparseNdArray(indices, values, DimensionalSpace.create(shape));

    BooleanNdArray sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original sparse array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getBoolean()));
    // check values from elements(0) of a slice against the  original sparse array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getBoolean())));
  }
}
