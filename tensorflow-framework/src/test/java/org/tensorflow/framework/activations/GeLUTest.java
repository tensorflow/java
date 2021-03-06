package org.tensorflow.framework.activations;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class GeLUTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of GeLU call method */
  @Test
  public void testCallFloat() {
    float[][] input = {
      {0.22805803f, 0.60407318f, 0.91519962f, 0.35643331f, 0.28702669f},
      {0.11558246f, 0.57658853f, 0.47569648f, 0.02271072f, 0.24709974f}
    };
    float[][] expected = {
      {0.13459972f, 0.43922312f, 0.75042395f, 0.22784713f, 0.17593417f},
      {0.06310898f, 0.41392788f, 0.32483157f, 0.01156111f, 0.14766297f}
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        GeLU instance = new GeLU(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of GeLU call method */
  @Test
  public void testCallDouble() {
    double[][] input = {
      {0.22805803, 0.60407318, 0.91519962, 0.35643331, 0.28702669},
      {0.11558246, 0.57658853, 0.47569648, 0.02271072, 0.24709974}
    };
    double[][] expected = {
      {0.13459972, 0.43922312, 0.75042395, 0.22784713, 0.17593417},
      {0.06310898, 0.41392788, 0.32483157, 0.01156111, 0.14766297}
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        GeLU instance = new GeLU(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of GeLU call method */
  @Test
  public void testCallFloatApproximate() {
    float[][] input = {
      {0.22805803f, 0.60407318f, 0.91519962f, 0.35643331f, 0.28702669f},
      {0.11558246f, 0.57658853f, 0.47569648f, 0.02271072f, 0.24709974f}
    };
    float[][] expected = {
      {0.13459886f, 0.43918941f, 0.75030122f, 0.22784227f, 0.17593207f},
      {0.06310892f, 0.41389921f, 0.32481722f, 0.01156111f, 0.14766179f}
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        GeLU instance = new GeLU(tf, true);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of GeLU call method */
  @Test
  public void testCallDoubleApproximate() {
    double[][] input = {
      {0.22805803, 0.60407318, 0.91519962, 0.35643331, 0.28702669},
      {0.11558246, 0.57658853, 0.47569648, 0.02271072, 0.24709974}
    };
    double[][] expected = {
      {0.13459886, 0.43918941, 0.75030122, 0.22784227, 0.17593207},
      {0.06310892, 0.41389921, 0.32481722, 0.01156111, 0.14766179}
    };
    // for (TestSession.Mode tfMode : tfModes)
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.GRAPH)) {
      Ops tf = session.getTF();
      GeLU instance = new GeLU(tf, true);
      Operand<TFloat64> result = instance.call(tf.constant(input));
      session.evaluate(tf.constant(expected), result);
    }
  }
}
