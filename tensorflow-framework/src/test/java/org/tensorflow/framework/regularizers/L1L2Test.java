package org.tensorflow.framework.regularizers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L1L2Test extends CommonTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void testCreate() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2<TFloat32> instance = new L1L2<>(tf, 0.2f, 0.3f, TFloat32.class);
        assertEquals(0.2f, instance.getL1());
        assertEquals(0.3f, instance.getL2());

        instance = new L1L2<>(tf, null, null, TFloat32.class);
        assertEquals(0.f, instance.getL1());
        assertEquals(0.f, instance.getL2());

        instance = new L1L2<>(tf, 0.5f, null, TFloat32.class);
        assertEquals(0.5f, instance.getL1());
        assertEquals(0.f, instance.getL2());

        instance = new L1L2<>(tf, null, 0.5f, TFloat32.class);
        assertEquals(0.f, instance.getL1());
        assertEquals(0.5f, instance.getL2());
      }
  }

  @Test
  public void testCallDefaultsConstant() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2<TFloat32> instance = new L1L2<>(tf, TFloat32.class);
        Operand<TFloat32> result = instance.call(tf.constant(555f));
        session.evaluate(0f, result);
      }
  }

  @Test
  public void testCallL1L20() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2<TFloat32> instance = new L1L2<>(tf, TFloat32.class);
        Operand<TFloat32> weights =
            tf.constant(new float[][] {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}});
        Operand<TFloat32> result = instance.call(weights);
        session.evaluate(0, result);
      }
  }

  @Test
  public void testCallL1L2TFloat32() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2<TFloat32> instance = new L1L2<>(tf, 0.01f, 0.02f, TFloat32.class);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(weights);
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
        L1L2<TFloat64> instance = new L1L2<>(tf, 0.01f, 0.02f, TFloat64.class);
        double[][] w = {{1.0, 0.9, 0.8}, {1.2, 0.7, 1.1}};
        Operand<TFloat64> weights = tf.constant(w);
        Operand<TFloat64> result = instance.call(weights);
        double expected = regularizeL1L2(w, 0.01f, 0.02f);
        session.setEpsilon(.09f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL2Null() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2<TFloat32> instance = new L1L2<>(tf, 0.01f, null, TFloat32.class);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> result = instance.call(weights);
        float expected = regularizeL1(w, 0.01f);
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testCallL1Null() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2<TFloat64> instance = new L1L2<>(tf, null, 0.02f, TFloat64.class);
        double[][] w = {{1.0, 0.9, 0.8}, {1.2, 0.7, 1.1}};
        Operand<TFloat64> weights = tf.constant(w);
        Operand<TFloat64> result = instance.call(weights);
        double expected = regularizeL2(w, 0.02f);
        session.setEpsilon(.01f);
        session.evaluate(expected, result);
      }
  }
}
