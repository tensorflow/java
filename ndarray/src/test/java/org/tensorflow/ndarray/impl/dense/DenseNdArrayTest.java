package org.tensorflow.ndarray.impl.dense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.index.Indices;

public class DenseNdArrayTest {

  @Test
  public void arrayEquals() {
    IntNdArray array = NdArrays.ofInts(Shape.of(2, 2))
        .set(NdArrays.vectorOf(1, 2), 0)
        .set(NdArrays.vectorOf(3, 4), 1);

    assertTrue(array.equals(StdArrays.ndCopyOf(new int[][] {{1, 2}, {3, 4}})));
    assertTrue(array.equals(StdArrays.ndCopyOf(new Integer[][] {{1, 2}, {3, 4}})));
    assertFalse(array.equals(NdArrays.vectorOf(1, 2, 3, 4)));
    assertFalse(array.equals(StdArrays.ndCopyOf(new int[][] {{3, 4}, {1, 2}})));
    assertFalse(array.equals(StdArrays.ndCopyOf(new long[][] {{1L, 2L}, {3L, 4L}})));
  }

  @Test
  public void equalsAndHashCodeOnSlices() {
    IntNdArray vector1 = NdArrays.vectorOf(3, 4);
    IntNdArray vector2 = NdArrays.vectorOf(1, 2, 3, 4);
    IntNdArray matrix1 = StdArrays.ndCopyOf(new int[][] {{1, 2}, {3, 4}});
    IntNdArray matrix2 = StdArrays.ndCopyOf(new int[][] {{1, 0, 2, 0}, {3, 0, 4, 0}});
    IntNdArray matrix3d1 = StdArrays.ndCopyOf(new int[][][] {
        {{1, 2}, {3, 4}},
        {{5, 6}, {7, 8}}
    });
    IntNdArray matrix3d2 = StdArrays.ndCopyOf(new int[][][] {
        {{1, 2}, {4, 5}},
        {{3, 4}, {6, 7}}
    });

    assertTrue(vector1.equals(vector2.slice(Indices.from(2))));
    assertTrue(vector1.equals(matrix1.get(1)));
    assertTrue(vector1.equals(matrix2.get(1).slice(Indices.even())));
    assertTrue(matrix1.equals(matrix2.slice(Indices.all(), Indices.even())));
    assertTrue(matrix3d1.get(0).equals(matrix1));
    assertFalse(matrix3d1.get(0).equals(vector2));
    assertTrue(matrix1.equals(matrix3d2.slice(Indices.all(), Indices.at(0))));
  }
}
