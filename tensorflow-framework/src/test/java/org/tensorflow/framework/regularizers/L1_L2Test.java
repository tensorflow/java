package org.tensorflow.framework.regularizers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L1_L2Test extends CommonTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void testCreate() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2 instance = new L1_L2(tf, 0.2f, 0.3f);
        assertEquals(0.2f, instance.getL1());
        assertEquals(0.3f, instance.getL2());

        instance = new L1_L2(tf, 0.5f, 0f);
        assertEquals(0.5f, instance.getL1());
        assertEquals(0f, instance.getL2());

        instance = new L1_L2(tf, 0f, 0.5f);
        assertEquals(0.f, instance.getL1());
        assertEquals(0.5f, instance.getL2());

        instance = new L1_L2(tf);
        assertEquals(Regularizer.DEFAULT_REGULARIZATION_PENALTY, instance.getL1());
        assertEquals(Regularizer.DEFAULT_REGULARIZATION_PENALTY, instance.getL2());
      }
  }

  @Test
  public void testCallZero() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2 instance = new L1_L2(tf, 0f, 0f);
        Operand<TFloat32> result = instance.call(tf.constant(555f));
        session.evaluate(0, result);
      }
  }

  @Test
  public void testCallDefaultTFloat32() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2 instance = new L1_L2(tf);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(weights);
        float expected =
            regularizeL1L2(
                w,
                Regularizer.DEFAULT_REGULARIZATION_PENALTY,
                Regularizer.DEFAULT_REGULARIZATION_PENALTY);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallDefaultTFloat64() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2 instance = new L1_L2(tf);
        double[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat64> weights = tf.constant(w);
        Operand<TFloat64> result = instance.call(weights);
        double expected =
            regularizeL1L2(
                w,
                Regularizer.DEFAULT_REGULARIZATION_PENALTY,
                Regularizer.DEFAULT_REGULARIZATION_PENALTY);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL1L2() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2 instance = new L1_L2(tf, 0.01f, 0.02f);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(weights);
        float expected = regularizeL1L2(w, 0.01f, 0.02f);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL20() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2 instance = new L1_L2(tf, 0.01f, 0f);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(weights);
        float expected = regularizeL1(w, 0.01f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL10() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2 instance = new L1_L2(tf, 0f, 0.02f);
        double[][] w = {{1.0, 0.9, 0.8}, {1.2, 0.7, 1.1}};
        Operand<TFloat64> weights = tf.constant(w);
        Operand<TFloat64> result = instance.call(weights);
        double expected = regularizeL2(w, 0.02f);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }
}
