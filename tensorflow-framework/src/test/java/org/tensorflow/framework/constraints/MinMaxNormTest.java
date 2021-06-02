package org.tensorflow.framework.constraints;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.ND;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

class MinMaxNormTest {

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

  /** Test of call method, of class MinMaxNorm. */
  @Test
  public void testCall() {
    float[] testValues = {0.1f, 0.5f, 3f, 8f, 1e-7f};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        final float[] array = getSampleArray();
        Operand<TFloat32> weights = tf.reshape(tf.constant(array), tf.constant(Shape.of(100, 100)));
        for (AtomicInteger i = new AtomicInteger();
            i.get() < testValues.length;
            i.getAndIncrement()) {
          MinMaxNorm instance = new MinMaxNorm(testValues[i.get()], testValues[i.get()] * 2);
          Operand<TFloat32> result = instance.call(tf, weights);
          if (tfMode == TestSession.Mode.EAGER)
            evaluate(session, result.asTensor(), testValues[i.get()]);
          else
            try (TFloat32 tensor =
                (TFloat32) session.getGraphSession().runner().fetch(result).run().get(0)) {
              evaluate(session, tensor, testValues[i.get()]);
            }
        }
      }
  }

  private void evaluate(TestSession session, TFloat32 tensor, float m) {
    FloatNdArray tensorArray = NdArrays.ofFloats(tensor.shape());
    tensor.copyTo(tensorArray);
    tensorArray = ND.square(tensorArray);
    FloatNdArray normArray = ND.sum(tensorArray, 0);
    FloatNdArray normOfNormalized = ND.sqrt(normArray);
    session.evaluate(
        normOfNormalized, (f) -> f.floatValue() >= m && f.floatValue() <= m * 2f + 1e-5f);
  }
}
