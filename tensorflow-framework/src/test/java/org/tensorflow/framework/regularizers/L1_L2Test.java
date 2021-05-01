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
        L1_L2<TFloat32> instance = new L1_L2<>(tf, 0.2f, 0.3f, TFloat32.class);
        assertEquals(0.2f, instance.getL1());
        assertEquals(0.3f, instance.getL2());

        instance = new L1_L2<>(tf, 0.5f, 0f, TFloat32.class);
        assertEquals(0.5f, instance.getL1());
        assertEquals(0f, instance.getL2());

        instance = new L1_L2<>(tf, 0f, 0.5f, TFloat32.class);
        assertEquals(0.f, instance.getL1());
        assertEquals(0.5f, instance.getL2());

        instance = new L1_L2<>(tf, TFloat32.class);
        assertEquals(Regularizer.DEFAULT_REGULARIZATION_PENALTY, instance.getL1());
        assertEquals(Regularizer.DEFAULT_REGULARIZATION_PENALTY, instance.getL2());
      }
  }

  /** Test of call method, of class L1_L2<>. */
  @Test
  public void testCallZero() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2<TFloat32> instance = new L1_L2<>(tf, 0f, 0f, TFloat32.class);
        Operand<TFloat32> result = instance.call(tf.constant(555f));
        session.evaluate(0, result);
      }
  }

  /** Test of call method, of class L1_L2<>. */
  @Test
  public void testCallNO() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2<TFloat32> instance = new L1_L2<>(tf, TFloat32.class);
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

  /** Test of call method, of class L1_L2<>. */
  @Test
  public void testCallL1L2() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2<TFloat32> instance = new L1_L2<>(tf, 0.01f, 0.02f, TFloat32.class);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(weights);
        float expected = regularizeL1L2(w, 0.01f, 0.02f);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }

  /** Test of call method, of class L1_L2<>. */
  @Test
  public void testCallL1() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2<TFloat32> instance = new L1_L2<>(tf, 0.01f, 0f, TFloat32.class);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(weights);
        float expected = regularizeL1(w, 0.01f);
        session.evaluate(expected, result);
      }
  }

  /** Test of call method, of class L1_L2<>. */
  @Test
  public void testCallL2() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1_L2<TFloat64> instance = new L1_L2<>(tf, 0f, 0.02f, TFloat64.class);
        double[][] w = {{1.0, 0.9, 0.8}, {1.2, 0.7, 1.1}};
        Operand<TFloat64> weights = tf.constant(w);
        Operand<TFloat64> result = instance.call(weights);
        double expected = regularizeL2(w, 0.02f);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }
}
