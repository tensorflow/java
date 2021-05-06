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

class GaussianNoiseTest {
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
                {{3.451546, 1.694727, 8.036768}, {9.233009, 6.462616, 7.226611}},
                {{6.644345, 2.605274, 4.224330}, {2.912649, 2.843349, 1.097698}},
                {{5.672600, 5.269178, 2.187318}, {9.298789, 7.292978, 8.849604}}
              });

      GaussianNoise<TFloat64> instance = new GaussianNoise<>(tf, 1.f, seed, TFloat64.class);

      // first pass, trainable is false, so there should be no dropout
      Operand<TFloat64> result = instance.call(input, false, TFloat64.class);
      assertEquals(expectedShape, result.shape());
      session.evaluate(tf.identity(input), result);

      Operand<TFloat64> expected =
          tf.constant(
              new double[][][] {
                {{3.679269, 1.977210, 8.807560}, {9.322395, 6.767639, 7.653679}},
                {{6.874095, 3.046032, 4.328507}, {3.396144, 3.414768, 1.099349}},
                {{6.208934, 6.194471, 3.045125}, {10.126389, 7.881398, 9.125002}}
              });

      // second pass, trainable is true, so there should be noise applied
      result = instance.call(input, true, TFloat64.class);
      assertEquals(expectedShape, result.shape());
      // cannot evaluate more than once, else it doesn't match expected
      // because of random number generation.
      // session.print(result);
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
