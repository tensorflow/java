package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.op.Ops;
import org.tensorflow.op.sparse.SparseSplit;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt64;

public class SparseTensorTest {

  @Test
  public void createSparseTensor() {
    long[][] indicesArray = new long[][] {
        {0, 0, 3}, {0, 2, 3}, {1, 0, 0}, {2, 2, 1}
    };
    TInt64 indices = TInt64.tensorOf(StdArrays.ndCopyOf(indicesArray));
    TFloat64 values = TFloat64.vectorOf(10.0, 20.0, 30.0, 40.0);
    TInt64 denseShape = TInt64.vectorOf(3, 3, 4);

    TFloat64 tensor = TFloat64.sparseTensorOf(indices, values, denseShape);
    assertNotNull(tensor);
    assertEquals(Shape.of(3, 3, 4), tensor.shape());

    tensor.scalars().forEachIndexed((coords, scalar) -> {
      if (Arrays.equals(coords, indicesArray[0])) {
        assertEquals(10.0, scalar.getDouble());
      } else if (Arrays.equals(coords, indicesArray[1])) {
        assertEquals(20.0, scalar.getDouble());
      } else if (Arrays.equals(coords, indicesArray[2])) {
        assertEquals(30.0, scalar.getDouble());
      } else if (Arrays.equals(coords, indicesArray[3])) {
        assertEquals(40.0, scalar.getDouble());
      } else {
        assertEquals(0.0, scalar.getDouble());
      }
    });

    assertTrue(SparseTensor.class.isAssignableFrom(tensor.getClass()));
    SparseTensor<TFloat64> sparseTensor = (SparseTensor<TFloat64>) tensor;
    assertEquals(indices, sparseTensor.indices());
    assertEquals(values, sparseTensor.values());
    assertEquals(denseShape, sparseTensor.denseShape());
  }

  @Test
  public void splitSparseTensor() {
    Ops tf = Ops.create();
    TInt64 indices = TInt64.tensorOf(StdArrays.ndCopyOf(new long[][] {
        {0, 0}, {0, 2}, {1, 0}, {1, 1}, {1, 3}, {2, 2}
    }));
    TFloat64 values = TFloat64.vectorOf(10.0, 20.0, 30.0, 40.0, 50.0, 60.0);
    TInt64 dimensions = TInt64.vectorOf(3, 4);

    SparseTensor<TFloat64> sparseTensor = SparseTensor.of(indices, values, dimensions);

    // [10.0   0.0  20.0   0.0]        [10.0   0.0]     [20.0   0.0]
    // [30.0  40.0   0.0  50.0]   ==>  [30.0  40.0]  +  [ 0.0  50.0]
    // [ 0.0   0.0  60.0   0.0]        [ 0.0   0.0]     [60.0   0.0]
    SparseSplit<TFloat64> split = tf.sparse.sparseSplit(
        tf.constant(1L),
        tf.constant(sparseTensor.indices()),
        tf.constant(sparseTensor.values()),
        tf.constant(sparseTensor.denseShape()),
        2L
    );
    List<Output<TInt64>> splitIndices = split.outputIndices();
    List<Output<TFloat64>> splitValues = split.outputValues();
    List<Output<TInt64>> splitDenseShape = split.outputShape();

    assertEquals(2, splitIndices.size());
    assertEquals(2, splitValues.size());
    assertEquals(2, splitDenseShape.size());

    SparseTensor<TFloat64> sparsePart1 = SparseTensor.of(
        splitIndices.get(0).asTensor(),
        splitValues.get(0).asTensor(),
        splitDenseShape.get(0).asTensor()
    );
    assertEquals(StdArrays.ndCopyOf(new long[][] {{0, 0}, {1, 0}, {1, 1}}), sparsePart1.indices());
    assertEquals(NdArrays.vectorOf(10.0, 30.0, 40.0), sparsePart1.values());
    assertEquals(NdArrays.vectorOf(3L, 2L), sparsePart1.denseShape());

    SparseTensor<TFloat64> sparsePart2 = SparseTensor.of(
        splitIndices.get(1).asTensor(),
        splitValues.get(1).asTensor(),
        splitDenseShape.get(1).asTensor()
    );
    assertEquals(StdArrays.ndCopyOf(new long[][] {{0, 0}, {1, 1}, {2, 0}}), sparsePart2.indices());
    assertEquals(NdArrays.vectorOf(20.0, 50.0, 60.0), sparsePart2.values());
    assertEquals(NdArrays.vectorOf(3L, 2L), sparsePart2.denseShape());
  }
}
