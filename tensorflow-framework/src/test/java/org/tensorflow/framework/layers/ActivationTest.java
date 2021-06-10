package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.activations.ReLU;
import org.tensorflow.framework.activations.Tanh;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class ActivationTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testReLU() {
    float[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    float[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      ReLU<TFloat32> relu = new ReLU<>();

      Activation<TFloat32> instance = new Activation<>(relu, TFloat32.class);
      Operand<TFloat32> result = instance.call(tf, tf.constant(input), true, TFloat32.class);

      session.evaluate(tf.constant(expected), result);
    }
  }

  /** Test of Tanh call method. */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -5, 6, -7, 8};
    double[] expected = {
      0.76159416,
      -0.96402758,
      0.99505475,
      -0.9993293,
      -0.9999092,
      0.99998771,
      -0.99999834,
      0.99999977
    };
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Tanh<TFloat64> tanh = new Tanh<>();
      Activation<TFloat64> instance = new Activation<>(tanh, TFloat64.class);
      Operand<TFloat64> result = instance.call(tf, tf.constant(input), false, TFloat64.class);
      session.evaluate(expected, result);
    }
  }
}
