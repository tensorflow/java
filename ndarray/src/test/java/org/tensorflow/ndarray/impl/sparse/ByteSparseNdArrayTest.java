package org.tensorflow.ndarray.impl.sparse;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.index.Indices;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ByteSparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}};
  byte[] valuesArray = {1, 16};
  byte[] denseArray = {
    1, 0, 0, 0,
    0, 0, 16, 0,
    0, 0, 0, 0
  };
  byte[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 16, 0}, {0, 0, 0, 0}};

  byte[][] dense2DArrayDefaultValue = {{1, -1, -1, -1}, {-1, -1, 16, -1}, {-1, -1, -1, -1}};

  Shape shape = Shape.of(3, 4);
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  ByteNdArray values = StdArrays.ndCopyOf(valuesArray);

  @Test
  public void testBasic() {
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));
    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(shape, instance.shape());
  }

  @Test
  public void testCopyToBuffer() {
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));
    ByteDataBuffer dataBuffer = DataBuffers.ofBytes(instance.shape().size());

    instance.copyTo(dataBuffer);

    byte[] array = new byte[denseArray.length];
    dataBuffer.read(array);
    assertArrayEquals(denseArray, array);
  }

  @Test
  public void testCopyFromBuffer() {

    ByteDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    ByteSparseNdArray instance = ByteSparseNdArray.create(DimensionalSpace.create(shape));
    instance.copyFrom(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
  }

  @Test
  public void testWriteDefaultValue() {
    // change 0 to -1
    byte[] denseArrayDefaultValue = new byte[denseArray.length];
    for (int i = 0; i < denseArrayDefaultValue.length; i++) {
      denseArrayDefaultValue[i] = denseArray[i] == 0 ? -1 : denseArray[i];
    }
    ByteDataBuffer dataBuffer = RawDataBufferFactory.create(denseArrayDefaultValue, false);
    // use a zero buffer
    ByteSparseNdArray instance =
        ByteSparseNdArray.create((byte) -1, DimensionalSpace.create(shape));
    instance.copyFrom(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals((byte) -1, instance.getByte(2, 0));
  }

  @Test
  public void testGetObject() {
    ByteNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGetByte() {
    ByteNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getByte(n, m), instance.getByte(n, m));
      }
    }
  }

  @Test
  public void testGetByteDefaultValue() {
    ByteNdArray ndArray = StdArrays.ndCopyOf(dense2DArrayDefaultValue);
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, (byte) -1, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getByte(n, m), instance.getByte(n, m));
      }
    }
  }

  @Test
  public void testGet() {
    ByteNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      assertEquals(ndArray.get(n), instance.get(n));
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.get(n, m), instance.get(n, m));
      }
    }
  }

  @Test
  public void testSetObject() {
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(java.nio.ReadOnlyBufferException.class, () -> instance.setObject((byte) 0, 0, 0));
  }

  @Test
  public void testSet() {
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(
        java.nio.ReadOnlyBufferException.class,
        () -> instance.set(instance.getDefaultArray(), 0, 0));
  }

  @Test
  public void testSort() {

    long[][] indicesArray = {{0, 0}, {1, 2}, {0, 1}, {2, 3}, {1, 4}};
    long[][] sortedIndicesArray = {{0, 0}, {0, 1}, {1, 2}, {1, 4}, {2, 3}};
    byte[] valuesArray = {1, 3, 2, 5, 4};
    byte[] sortedValuesArray = {1, 2, 3, 4, 5};

    LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
    LongNdArray sortedIndices = StdArrays.ndCopyOf(sortedIndicesArray);
    ByteNdArray values = StdArrays.ndCopyOf(valuesArray);
    ByteNdArray sortedValues = StdArrays.ndCopyOf(sortedValuesArray);

    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance.sortIndicesAndValues();

    // should be sorted in ascending row-wise coordinate order based on test values
    assertEquals(sortedIndices, instance.getIndices());
    assertEquals(sortedValues, instance.getValues());
  }

  @Test
  public void testElements() {

    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance
        .elements(0)
        .forEachIndexed(
            (idx, item) -> {
              byte[] slice = dense2DArray[(int) idx[0]];
              item.scalars()
                  .forEachIndexed((dx, f) -> assertEquals(slice[(int) dx[0]], f.getObject()));
            });
  }

  @Test
  public void testDense() {

    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));
    ByteNdArray denseInstance = instance.toDense();
    ByteNdArray expectedDense = StdArrays.ndCopyOf(dense2DArray);
    assertEquals(expectedDense, denseInstance);
  }

  @Test
  public void testFromDense() {
    ByteNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ByteSparseNdArray instance = ByteSparseNdArray.create(DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    assertNotNull(instance.getIndices());
    assertEquals(2, instance.getIndices().shape().get(0));
    assertNotNull(instance.getValues());
    assertEquals(2, instance.getValues().size());

    assertEquals(ndArray.shape(), instance.shape());
    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getByte(n, m), instance.getByte(n, m));
      }
    }
  }

  @Test
  public void testElements1() {
    byte[] expected = {1, 0, 0};

    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));
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
    ByteNdArray dst = NdArrays.ofBytes(shape);
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));
    instance.copyTo(dst);
    for (int n = 0; n < instance.shape().get(0); n++) {
      for (int m = 0; m < instance.shape().get(1); m++) {
        assertEquals(instance.getByte(n, m), dst.getByte(n, m));
      }
    }
  }

  @Test
  public void testCreate() {

    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));
    ByteSparseNdArray instanceA =
        ByteSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
    assertEquals(instance, instanceA);

    ByteDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    ByteSparseNdArray instanceB = ByteSparseNdArray.create(DimensionalSpace.create(shape));
    instanceB.copyFrom(dataBuffer);
    assertEquals(instance, instanceB);

    ByteSparseNdArray instanceC =
        ByteSparseNdArray.create(dataBuffer, DimensionalSpace.create(shape));
    assertEquals(instanceB, instanceC);

    ByteSparseNdArray instanceD = ByteSparseNdArray.create(dataBuffer, shape);
    assertEquals(instanceB, instanceD);

    ByteNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    ByteSparseNdArray instanceE = ByteSparseNdArray.create(ndArray);
    assertEquals(instance, instanceE);
  }

  @Test
  public void testSlice() {
    byte[] expected = {0, 0, 16, 0, 0, 0};
    ByteSparseNdArray instance =
        new ByteSparseNdArray(indices, values, DimensionalSpace.create(shape));

    ByteNdArray sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original sparse array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getByte()));
    // check values from elements(0) of a slice against the  original sparse array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getByte())));
  }
}
