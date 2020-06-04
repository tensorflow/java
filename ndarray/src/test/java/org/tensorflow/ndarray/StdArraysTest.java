package org.tensorflow.ndarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StdArraysTest {

  @Test
  public void initVector() {
    IntNdArray vector = NdArrays.ofInts(Shape.of(2));

    StdArrays.copyTo(vector, new int[] {1, 2});
    assertEquals(1, vector.getInt(0));
    assertEquals(2, vector.getInt(1));

    try {
      StdArrays.copyTo(vector, new int[] {1, 2, 3});
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyTo(NdArrays.ofInts(Shape.of(4)), new int[] {1, 2});
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyTo(NdArrays.ofInts(Shape.of(2, 2)), new int[] {1, 2});
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void initMatrix() {
    IntNdArray matrix = NdArrays.ofInts(Shape.of(2, 2));

    StdArrays.copyTo(matrix, new int[][] {
        {1, 2},
        {3, 4}
    });
    assertEquals(1, matrix.getInt(0, 0));
    assertEquals(2, matrix.getInt(0, 1));
    assertEquals(3, matrix.getInt(1, 0));
    assertEquals(4, matrix.getInt(1, 1));
    try {
      StdArrays.copyTo(matrix, new int[][] {{1, 2, 3}, {4, 5, 6}});
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyTo(NdArrays.ofInts(Shape.of(3, 3)), new int[][] {{1, 2}, {3, 4}});
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyTo(NdArrays.ofInts(Shape.of(2, 2, 1)), new int[][] {{1, 2}, {3, 4}});
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void cannotInitDenseMatrixWithRaggedArray() {
    IntNdArray matrix = NdArrays.ofInts(Shape.of(2, 2));
    try {
      StdArrays.copyTo(matrix, new int[][]{
          {1, 2},
          {3}
      });
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void computeShapeDense3DMatrix() {
    Shape shape = StdArrays.shapeOf(new int[][][] {
        {
            {1, 2, 3}, {4, 5, 6}
        },
        {
            {1, 2, 3}, {4, 5, 6}
        }
    });
    assertArrayEquals(new long[] {2, 2, 3}, shape.asArray());
  }

  @Test
  public void shapeOfRagged3DMatrix() {
    Shape shape = StdArrays.shapeOf(new int[][][] {
        {
            {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        },
        {
            {1, 2, 3}, {4, 5, 6}
        }
    });
    assertArrayEquals(new long[] {2, Shape.UNKNOWN_SIZE, 3}, shape.asArray());
  }

  @Test
  public void shapeOfEmptyArray() {
    Shape shape = StdArrays.shapeOf(new int[2][2][3]);
    assertArrayEquals(new long[] {2, 2, 3}, shape.asArray());
  }

  @Test
  public void createArrayFromObjectMatrix() {
    NdArray<String> ndArray = StdArrays.ndCopyOf(new String[][] {{"a", "b"}, {"c", "d"}});
    assertEquals(NdArrays.vectorOfObjects("a", "b"), ndArray.get(0));
    assertEquals(NdArrays.vectorOfObjects("c", "d"), ndArray.get(1));
  }
}
