package org.tensorflow.framework.constraints;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class UnitNormTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class UnitNorm. */
  @Test
  public void testTFloat32() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        float[][][] array = {
          {{0.14517927f, 0.2574964f, 0.2291325f}, {0.9145494f, 0.9378068f, 0.6827883f}},
          {{0.27121753f, 0.08317473f, 0.3770739f}, {0.25451255f, 0.18511271f, 0.5620538f}},
          {{0.40101776f, 0.25205433f, 0.05103926f}, {0.08764106f, 0.00593294f, 0.37244815f}}
        };
        float[][][] expectedArray = {
          {{0.1567809f, 0.2647736f, 0.31814702f}, {0.9876333f, 0.9643105f, 0.94804124f}},
          {{0.72920675f, 0.40984813f, 0.55712338f}, {0.68429305f, 0.91215323f, 0.83042956f}},
          {{0.97694125f, 0.99972269f, 0.13576831f}, {0.21350717f, 0.02353181f, 0.99074035f}}
        };

        Operand<TFloat32> weights = tf.constant(array);
        UnitNorm instance = new UnitNorm(1);
        Operand<TFloat32> result = instance.call(tf, weights);
        Operand<TFloat32> expected = tf.constant(expectedArray);
        session.evaluate(expected, result);
      }
  }
  /** Test of call method, of class UnitNorm. */
  @Test
  public void testCallTFloat64() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        double[][][] array = {
          {{0.14517927, 0.2574964, 0.2291325}, {0.9145494, 0.9378068, 0.6827883}},
          {{0.27121753, 0.08317473, 0.3770739}, {0.25451255, 0.18511271, 0.5620538}},
          {{0.40101776, 0.25205433, 0.05103926}, {0.08764106, 0.00593294, 0.37244815}}
        };
        double[][][] expectedArray = {
          {{0.1567809, 0.2647736, 0.31814702}, {0.9876333, 0.9643105, 0.94804124}},
          {{0.72920675, 0.40984813, 0.55712338}, {0.68429305, 0.91215323, 0.83042956}},
          {{0.97694125, 0.99972269, 0.13576831}, {0.21350717, 0.02353181, 0.99074035}}
        };
        UnitNorm instance = new UnitNorm(1);
        Operand<TFloat64> weights = tf.constant(array);
        Operand<TFloat64> result = instance.call(tf, weights);
        Operand<TFloat64> expected = tf.constant(expectedArray);
        session.evaluate(expected, result);
      }
  }
}
