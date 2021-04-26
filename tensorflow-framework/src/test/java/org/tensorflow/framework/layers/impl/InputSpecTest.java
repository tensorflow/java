package org.tensorflow.framework.layers.impl;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.Shape;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InputSpecTest {

  @Test
  public void testAxis() {

    InputSpec instance =
        new InputSpec(
            InputSpec.Options.create()
                .shape(Shape.of(1, Shape.UNKNOWN_SIZE, 2, 3))
                .axesMap(3, 5L)
                .axesMap(2, 2L));

    assertThrows(
        java.lang.IllegalArgumentException.class,
        () -> {
          InputSpec instance1 =
              new InputSpec(
                  InputSpec.Options.create()
                      .shape(Shape.of(1, Shape.UNKNOWN_SIZE, 2, 3))
                      .axesMap(4, 5L));
        });

  }

  @Test
  public void testDefinedShape() {
      Shape expected = Shape.of(1, Shape.UNKNOWN_SIZE, 2, 3);
        InputSpec instance =
                new InputSpec(InputSpec.Options.create().shape(expected));
      assertArrayEquals(expected.asArray(), instance.toShape().asArray());
  }

    @Test
    public void testDefinedRank() {
        InputSpec instance =
                new InputSpec(InputSpec.Options.create().rank(5));
        long[] dims = new long[5];
        Arrays.fill(dims, Shape.UNKNOWN_SIZE);
        assertArrayEquals(dims, instance.toShape().asArray());

        instance = new InputSpec(InputSpec.Options.create().rank(0));
        dims = new long[0];
        assertArrayEquals(dims, instance.toShape().asArray());

        instance = new InputSpec(InputSpec.Options.create().rank(3).axesMap(1,3L).axesMap(-1,2L));
        dims = new long[] {Shape.UNKNOWN_SIZE, 3, 2};
        assertArrayEquals(dims, instance.toShape().asArray());
    }

    @Test
    public void  testUndefinedShapes() {
        InputSpec instance =
                new InputSpec(InputSpec.Options.create().maxRank(5));
        Shape genShaped = instance.toShape();
        assertTrue(genShaped.isUnknown());

        instance =
                new InputSpec(InputSpec.Options.create().minRank(5).maxRank(5));
        genShaped = instance.toShape();
        assertTrue(genShaped.isUnknown());

    }
}
