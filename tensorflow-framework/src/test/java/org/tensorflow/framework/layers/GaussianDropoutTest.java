package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GaussianDropoutTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testShape3_2_3() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      long seed = 1001L;
      Shape expectedShape = Shape.of(3, 2, 3);
      Operand<TFloat64> input =
          tf.constant(
              new double[][][] {
                {{3.22382299, 1.41224385, 7.265976}, {9.1436238, 6.15759347, 6.79954284}},
                {{6.41459591, 2.16451569, 4.12015256}, {2.42915398, 2.27193001, 1.09604702}},
                {{5.13626611, 4.34388458, 1.32951124}, {8.47118881, 6.70455732, 8.57420547}}
              });

      GaussianDropout<TFloat64> instance = new GaussianDropout<>(tf, 0.5f, seed, TFloat64.class);

      // first pass, trainable is false, so there should be no dropout
      Operand<TFloat64> result = instance.call(input, false, TFloat64.class);
      assertEquals(expectedShape, result.shape());
      session.evaluate(tf.identity(input), result);

      Operand<TFloat64> expected =
          tf.constant(
              new double[][][] {
                {{0.734139, 0.398936, 5.600555}, {0.817308, 6.134556, 2.074016}},
                {{3.984928, 1.046533, 2.354332}, {1.876065, 1.218514, 1.014165}},
                {{4.405925, 3.813551, 1.100304}, {4.984621, 1.846423, 2.097348}}
              });

      // second pass, trainable is true, so there should be  dropout
      result = instance.call(input, true, TFloat64.class);
      assertEquals(expectedShape, result.shape());
      session.evaluate(expected, result);
    }
  }

  @Test
  public void testSupportsMasking() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      long seed = 1001L;
      Dropout<TFloat32> instance = new Dropout<>(tf, 0.5f, seed, TFloat32.class);
      assertTrue(instance.isSupportsMasking());
    }
  }
}
