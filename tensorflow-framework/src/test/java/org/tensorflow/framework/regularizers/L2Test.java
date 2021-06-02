package org.tensorflow.framework.regularizers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class L2Test extends CommonTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void testCreate() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L2 instance = new L2(0.2f);
        assertEquals(0.2f, instance.getL2());
        assertEquals(0.f, instance.getL1());

        instance = new L2(0f);
        assertEquals(0.f, instance.getL2());
        assertEquals(0.f, instance.getL1());

        L2 instance64 = new L2();
        assertEquals(AbstractRegularizer.DEFAULT_REGULARIZATION_PENALTY, instance64.getL2());
        assertEquals(0.f, instance64.getL1());
      }
  }

  @Test
  public void testCallL20() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L2 instance = new L2(0.0f);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(tf, weights);
        session.evaluate(0, result);
      }
  }

  @Test
  public void testCallL2TFloat32() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L2 instance = new L2();
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(tf, weights);
        float expected = regularizeL2(w, AbstractRegularizer.DEFAULT_REGULARIZATION_PENALTY);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL2TFloat64() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L2 instance = new L2(0.02f);
        double[][] w = {{1.0, 0.9, 0.8}, {1.2, 0.7, 1.1}};
        Operand<TFloat64> weights = tf.constant(w);
        Operand<TFloat64> result = instance.call(tf, weights);
        double expected = regularizeL2(w, 0.02f);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }
}
