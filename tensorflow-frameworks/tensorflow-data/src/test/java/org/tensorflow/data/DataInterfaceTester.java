package org.tensorflow.data;

import static org.junit.Assert.*;
import static org.tensorflow.tools.StaticApi.*;

import org.junit.Test;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.StaticApi;
import org.tensorflow.tools.ndarray.IntNdArray;
import org.tensorflow.tools.ndarray.NdArray;

import java.util.Arrays;

public class DataInterfaceTester {
  @Test
  public void testSomething() {
    IntNdArray matrix2d = ndArrayOfInts(shapeOf(3, 4));
    assertEquals(2, matrix2d.rank());


    matrix2d.elements(0).forEachIndexed((coords, matrix) -> {
      assertEquals(1, matrix.rank());
      assertEquals(shapeOf(4), matrix.shape());
      matrix.set(vectorOf(
          1 + (int) coords[0], 2 + (int) coords[0],
          3 + (int) coords[0], 4 + (int) coords[0])
      );
    });

    matrix2d.scalars().forEachIndexed((coords, scalar) ->
        System.out.println("Scalar at " + Arrays.toString(coords) + " has value " + scalar.getInt())
    );
  }
}
