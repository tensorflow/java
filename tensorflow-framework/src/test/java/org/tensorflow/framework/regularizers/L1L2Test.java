package org.tensorflow.framework.regularizers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class L1L2Test extends CommonTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void testCreate() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2 instance = new L1L2(0.2f, 0.3f);
        assertEquals(0.2f, instance.getL1());
        assertEquals(0.3f, instance.getL2());

        instance = new L1L2(0, 0);
        assertEquals(0.f, instance.getL1());
        assertEquals(0.f, instance.getL2());

        instance = new L1L2(0.5f, 0);
        assertEquals(0.5f, instance.getL1());
        assertEquals(0.f, instance.getL2());

        instance = new L1L2(0, 0.5f);
        assertEquals(0.f, instance.getL1());
        assertEquals(0.5f, instance.getL2());

        instance = new L1L2();
        assertEquals(AbstractRegularizer.DEFAULT_REGULARIZATION_PENALTY, instance.getL1());
        assertEquals(AbstractRegularizer.DEFAULT_REGULARIZATION_PENALTY, instance.getL2());
      }
  }

  @Test
  public void testCallDefaultsConstant() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2 instance = new L1L2();
        Operand<TFloat32> result = instance.call(tf, tf.constant(555f));
        session.evaluate(3085.8f, result);
      }
  }

  @Test
  public void testCallL1L2_0() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2 instance = new L1L2(0, 0);
        Operand<TFloat32> weights =
            tf.constant(new float[][] {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}});
        Operand<TFloat32> result = instance.call(tf, weights);
        session.evaluate(0, result);
      }
  }

  @Test
  public void testCallL1L2TFloat32() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2 instance = new L1L2(0.01f, 0.02f);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(tf, weights);
        float expected = regularizeL1L2(w, 0.01f, 0.02f);
        session.setEpsilon(.09f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL1L2TFloat64() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2 instance = new L1L2(0.01f, 0.02f);
        double[][] w = {{1.0, 0.9, 0.8}, {1.2, 0.7, 1.1}};
        Operand<TFloat64> weights = tf.constant(w);
        Operand<TFloat64> result = instance.call(tf, weights);
        double expected = regularizeL1L2(w, 0.01f, 0.02f);
        session.setEpsilon(.09f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL2_0() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2 instance = new L1L2(0.01f, 0);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(tf, weights);
        float expected = regularizeL1(w, 0.01f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL1_0() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2 instance = new L1L2(0, 0.02f);
        double[][] w = {{1.0, 0.9, 0.8}, {1.2, 0.7, 1.1}};
        Operand<TFloat64> weights = tf.constant(w);
        Operand<TFloat64> result = instance.call(tf, weights);
        double expected = regularizeL2(w, 0.02f);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }
}
