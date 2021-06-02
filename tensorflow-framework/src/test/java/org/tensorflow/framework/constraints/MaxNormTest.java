package org.tensorflow.framework.constraints;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

class MaxNormTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  private float[] getSampleArray() {
    Random rand = new Random(3537L);
    float[] result = new float[100 * 100];
    for (int i = 0; i < result.length; i++) {
      result[i] = rand.nextFloat() * 100 - 50;
    }
    result[0] = 0;
    return result;
  }

  /** Test of call method, of class MaxNorm. */
  @Test
  public void testCall() {
    double[] testValues = {0.1, 0.5, 3, 8, 1e-7};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        final float[] array = getSampleArray();
        Operand<TFloat32> weights = tf.reshape(tf.constant(array), tf.constant(Shape.of(100, 100)));
        for (AtomicInteger i = new AtomicInteger();
            i.get() < testValues.length;
            i.getAndIncrement()) {
          MaxNorm instance = new MaxNorm(testValues[i.get()]);
          Operand<TFloat32> result = instance.call(tf, weights);
          session.evaluate(result, v -> v.floatValue() <= testValues[i.get()]);
        }
      }
  }
  /** Test of call method, of class MaxNorm. */
  @Test
  public void testCall1() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        MaxNorm instance = new MaxNorm(2.0);
        Operand<TFloat32> weights =
            tf.constant(
                new float[][] {
                  {0, 1, 3, 3}, {0, 0, 0, 3}, {0, 0, 0, 3},
                });
        Operand<TFloat32> result = instance.call(tf, weights);
        float[] expected = {
          0, 1, 2, 1.1547005f,
          0, 0, 0, 1.1547005f,
          0, 0, 0, 1.1547005f
        };
        session.evaluate(expected, result);
      }
  }
}
