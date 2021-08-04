package org.tensorflow.ndarray.impl.sparse;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.index.Indices;

import java.nio.FloatBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FloatSparseNdArrayTest {
  long[][] indicesArray = {{0, 0}, {1, 2}};
  float[] valuesArray = {1, 2};
  float[] denseArray = {
    1, 0, 0, 0,
    0, 0, 2, 0,
    0, 0, 0, 0
  };

  float[][] dense2DArrayDefaultValue = {{1, -1, -1, -1}, {-1, -1, 2, -1}, {-1, -1, -1, -1}};

  Shape shape = Shape.of(3, 4);
  LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
  FloatNdArray values = StdArrays.ndCopyOf(valuesArray);

  @Test
  public void testBasic() {
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));
    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(shape, instance.shape());
  }

  @Test
  public void testRead() {
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));
    FloatDataBuffer dataBuffer = DataBuffers.ofFloats(instance.shape().size());

    instance.read(dataBuffer);

    float[] array = new float[denseArray.length];
    dataBuffer.read(array);
    assertArrayEquals(denseArray, array);
  }

  @Test
  public void testWrite() {

    FloatDataBuffer dataBuffer = NioDataBufferFactory.create(FloatBuffer.wrap(denseArray));
    // use a zero buffer
    FloatSparseNdArray instance = FloatSparseNdArray.create(DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
  }

  @Test
  public void testWriteDefaultValue() {
    // change 0 to -1
    float[] denseArrayDefaultValue = new float[denseArray.length];
    for (int i = 0; i < denseArrayDefaultValue.length; i++) {
      denseArrayDefaultValue[i] = denseArray[i] == 0f ? -1f : denseArray[i];
    }
    FloatDataBuffer dataBuffer = RawDataBufferFactory.create(denseArrayDefaultValue, false);
    // use a zero buffer
    FloatSparseNdArray instance = FloatSparseNdArray.create(-1f, DimensionalSpace.create(shape));
    instance.write(dataBuffer);

    assertEquals(indices, instance.getIndices());
    assertEquals(values, instance.getValues());
    assertEquals(-1f, instance.getFloat(2, 0));
  }

  @Test
  public void testGetObject() {
    float[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    FloatNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getObject(n, m), instance.getObject(n, m));
      }
    }
  }

  @Test
  public void testGetFloat() {
    float[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    FloatNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getFloat(n, m), instance.getFloat(n, m));
      }
    }
  }

  @Test
  public void testGetFloatDefaultValue() {
    FloatNdArray ndArray = StdArrays.ndCopyOf(dense2DArrayDefaultValue);
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, -1, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getFloat(n, m), instance.getFloat(n, m));
      }
    }
  }

  @Test
  public void testGet() {
    float[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    FloatNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));

    for (int n = 0; n < ndArray.shape().get(0); n++) {
      assertEquals(ndArray.get(n), instance.get(n));
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.get(n, m), instance.get(n, m));
      }
    }
  }

  @Test
  public void testSetObject() {
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(java.nio.ReadOnlyBufferException.class, () -> instance.setObject(2f, 0, 0));
  }

  @Test
  public void testSet() {
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));

    assertThrows(
        java.nio.ReadOnlyBufferException.class,
        () -> instance.set(instance.getDefaultArray(), 0, 0));
  }

  @Test
  public void testSort() {

    long[][] indicesArray = {{0, 0}, {1, 2}, {0, 1}, {2, 3}, {1, 4}};
    long[][] sortedIndicesArray = {{0, 0}, {0, 1}, {1, 2}, {1, 4}, {2, 3}};
    float[] valuesArray = {1, 3, 2, 5, 4};
    float[] sortedValuesArray = {1, 2, 3, 4, 5};

    LongNdArray indices = StdArrays.ndCopyOf(indicesArray);
    LongNdArray sortedIndices = StdArrays.ndCopyOf(sortedIndicesArray);
    FloatNdArray values = StdArrays.ndCopyOf(valuesArray);
    FloatNdArray sortedValues = StdArrays.ndCopyOf(sortedValuesArray);

    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));

    instance.sortIndicesAndValues();

    // should be sorted in ascending row-wise coordinate order based on test values
    assertEquals(sortedIndices, instance.getIndices());
    assertEquals(sortedValues, instance.getValues());
  }

  @Test
  public void testElements() {

    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));

    float[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    instance
        .elements(0)
        .forEachIndexed(
            (idx, item) -> {
              float[] slice = dense2DArray[(int) idx[0]];
              item.scalars()
                  .forEachIndexed((dx, f) -> assertEquals(slice[(int) dx[0]], f.getObject()));
            });
  }

  @Test
  public void testDense() {
    float[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};

    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));
    FloatNdArray denseInstance = instance.toDense();
    FloatNdArray expectedDense = StdArrays.ndCopyOf(dense2DArray);
    assertEquals(expectedDense, denseInstance);
  }

  @Test
  public void testFromDense() {
    float[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    FloatNdArray ndArray = StdArrays.ndCopyOf(dense2DArray);
    FloatSparseNdArray instance =
        FloatSparseNdArray.create(DimensionalSpace.create(ndArray.shape()));
    instance.fromDense(ndArray);
    assertNotNull(instance.getIndices());
    assertEquals(2, instance.getIndices().shape().get(0));
    assertNotNull(instance.getValues());
    assertEquals(2, instance.getValues().size());

    assertEquals(ndArray.shape(), instance.shape());
    for (int n = 0; n < ndArray.shape().get(0); n++) {
      for (int m = 0; m < ndArray.shape().get(1); m++) {
        assertEquals(ndArray.getFloat(n, m), instance.getFloat(n, m));
      }
    }
  }

  @Test
  public void testElements1() {
    float[] expected = {1, 0, 0};
    float[][] dense2DArray = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};

    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));
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
    FloatNdArray dst = NdArrays.ofFloats(shape);
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));
    instance.copyTo(dst);
    for (int n = 0; n < instance.shape().get(0); n++) {
      for (int m = 0; m < instance.shape().get(1); m++) {
        assertEquals(instance.getFloat(n, m), dst.getFloat(n, m));
      }
    }
  }

  @Test
  public void testCreate() {
    float[] denseArray = {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0};
    float[][] dense2Array = {{1, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));
    FloatSparseNdArray instanceA =
        FloatSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
    assertEquals(instance, instanceA);

    FloatDataBuffer dataBuffer = RawDataBufferFactory.create(denseArray, false);
    // use a zero buffer
    FloatSparseNdArray instanceB = FloatSparseNdArray.create(DimensionalSpace.create(shape));
    instanceB.write(dataBuffer);
    assertEquals(instance, instanceB);

    FloatSparseNdArray instanceC =
        FloatSparseNdArray.create(dataBuffer, DimensionalSpace.create(shape));
    assertEquals(instanceB, instanceC);

    FloatSparseNdArray instanceD = FloatSparseNdArray.create(dataBuffer, shape);
    assertEquals(instanceB, instanceD);

    FloatNdArray ndArray = StdArrays.ndCopyOf(dense2Array);
    FloatSparseNdArray instanceE = FloatSparseNdArray.create(ndArray);
    assertEquals(instance, instanceE);
  }

  @Test
  public void testSlice() {
    float[] expected = {0, 0, 2, 0, 0, 0};
    FloatSparseNdArray instance =
        new FloatSparseNdArray(indices, values, DimensionalSpace.create(shape));

    FloatNdArray sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original sparse array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getFloat()));
    // check values from elements(0) of a slice against the  original sparse array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getFloat())));
  }
}
