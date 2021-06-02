package org.tensorflow.framework.constraints;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class NonNegTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void testTFloat32() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        float[][] array = {{-1, 2, -3, 4}, {-10, 11, 12, -13}};
        Operand<TFloat32> weights = tf.constant(array);
        NonNeg instance = new NonNeg();
        Operand<TFloat32> result = instance.call(tf, weights);
        float[] expected = {0, 2, 0, 4, 0, 11, 12, 0};
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testTFloat64() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        final double[][] array = {{-1, 2, -3, 4}, {-10, 11, 12, -13}};
        Operand<TFloat64> weights = tf.constant(array);
        NonNeg instance = new NonNeg();
        Operand<TFloat64> result = instance.call(tf, weights);
        double[] expected = {0, 2, 0, 4, 0, 11, 12, 0};
        session.evaluate(expected, result);
      }
  }
}
