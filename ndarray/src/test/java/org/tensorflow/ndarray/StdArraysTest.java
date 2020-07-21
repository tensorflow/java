package org.tensorflow.ndarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StdArraysTest {

  @Test
  public void vectors() {
    IntNdArray vector = NdArrays.ofInts(Shape.of(2));

    StdArrays.copyTo(new int[] {1, 2}, vector);
    assertEquals(1, vector.getInt(0));
    assertEquals(2, vector.getInt(1));

    try {
      StdArrays.copyTo(new int[] {1, 2, 3}, vector);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyTo(new int[] {1, 2}, NdArrays.ofInts(Shape.of(4)));
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyTo(new int[] {1, 2}, NdArrays.ofInts(Shape.of(2, 2)));
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }

    int[] array = StdArrays.array1dCopyOf(vector);
    assertEquals(1, array[0]);
    assertEquals(2, array[1]);

    array = new int[3];
    StdArrays.copyFrom(vector, array);
    assertEquals(1, array[0]);
    assertEquals(2, array[1]);
    assertEquals(0, array[2]);

    try {
      StdArrays.copyFrom(vector, new int[1]);
      fail();
    } catch (ArrayIndexOutOfBoundsException e) {
      // as expected
    }
    try {
      StdArrays.copyFrom(vector, new int[1][2]);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyFrom(vector, new int[2][2][2]);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void matrices() {
    IntNdArray matrix = NdArrays.ofInts(Shape.of(2, 2));

    StdArrays.copyTo(new int[][] {
        {1, 2},
        {3, 4}
    }, matrix);
    assertEquals(1, matrix.getInt(0, 0));
    assertEquals(2, matrix.getInt(0, 1));
    assertEquals(3, matrix.getInt(1, 0));
    assertEquals(4, matrix.getInt(1, 1));
    try {
      StdArrays.copyTo(new int[][] {{1, 2, 3}, {4, 5, 6}}, matrix);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyTo(new int[][] {{1, 2}, {3, 4}}, NdArrays.ofInts(Shape.of(3, 3)));
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyTo(new int[][] {{1, 2}, {3, 4}}, NdArrays.ofInts(Shape.of(2, 2, 1)));
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }

    int[][] array = StdArrays.array2dCopyOf(matrix);
    assertEquals(1, array[0][0]);
    assertEquals(2, array[0][1]);
    assertEquals(3, array[1][0]);
    assertEquals(4, array[1][1]);

    array = new int[3][3];
    StdArrays.copyFrom(matrix, array);
    assertArrayEquals(new int[] { 1, 2, 0 }, array[0]);
    assertArrayEquals(new int[] { 3, 4, 0 }, array[1]);
    assertArrayEquals(new int[] { 0, 0, 0 }, array[2]);

    try {
      StdArrays.copyFrom(matrix, new int[1][2]);
      fail();
    } catch (ArrayIndexOutOfBoundsException e) {
      // as expected
    }
    try {
      StdArrays.copyFrom(matrix, new int[2][1]);
      fail();
    } catch (ArrayIndexOutOfBoundsException e) {
      // as expected
    }
    try {
      StdArrays.copyFrom(matrix, new int[2]);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyFrom(matrix, new int[1][2][2]);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      StdArrays.copyFrom(matrix, new int[2][2][2]);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void objectMatrix() {
    NdArray<String> matrix = StdArrays.ndCopyOf(new String[][] {{"ab", "bc"}, {"cd", "de"}});
    assertEquals(NdArrays.vectorOfObjects("ab", "bc"), matrix.get(0));
    assertEquals(NdArrays.vectorOfObjects("cd", "de"), matrix.get(1));

    String[][] array = StdArrays.array2dCopyOf(matrix, String.class);
    assertEquals("ab", array[0][0]);
    assertEquals("bc", array[0][1]);
    assertEquals("cd", array[1][0]);
    assertEquals("de", array[1][1]);

    array = new String[2][3];
    StdArrays.copyFrom(matrix, array);
    assertEquals("ab", array[0][0]);
    assertEquals("bc", array[0][1]);
    assertNull(array[0][2]);
    assertEquals("cd", array[1][0]);
    assertEquals("de", array[1][1]);
    assertNull(array[1][2]);
  }

  @Test
  public void cannotInitDenseMatrixWithRaggedArray() {
    IntNdArray matrix = NdArrays.ofInts(Shape.of(2, 2));
    try {
      StdArrays.copyTo(new int[][]{
          {1, 2},
          {3}
      }, matrix);
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
}
