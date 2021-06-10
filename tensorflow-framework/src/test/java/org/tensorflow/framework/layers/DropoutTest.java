package org.tensorflow.framework.layers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

class DropoutTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testShape3_2() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      long seed = 1001L;
      Shape expectedShape = Shape.of(3, 2);
      Operand<TFloat32> input =
          tf.constant(
              new float[][] {
                {1.3463433f, 7.2481093f},
                {5.4018216f, 0.6772865f},
                {3.4442706f, 0.95697135f}
              });

      Dropout<TFloat32> instance = new Dropout<>(0.5f, seed, TFloat32.class);

      // first pass, trainable is false, so there should be no dropout
      Operand<TFloat32> result = instance.call(tf, input, false, TFloat32.class);
      assertEquals(expectedShape, result.shape());
      session.evaluate(tf.identity(input), result);

      Operand<TFloat32> expected =
          tf.constant(
              new float[][] {
                {0f, 14.496219f},
                {0f, 0f},
                {0f, 0f}
              });

      // second pass, trainable is true, so there should be  dropout
      result = instance.call(tf, input, true, TFloat32.class);
      assertEquals(expectedShape, result.shape());
      session.evaluate(expected, result);
    }
  }

  @Test
  public void testShape3_2Noise() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      long seed = 1001L;
      Shape expectedShape = Shape.of(3, 2);
      Operand<TFloat32> input =
          tf.constant(
              new float[][] {
                {1.3463433f, 7.2481093f},
                {5.4018216f, 0.6772865f},
                {3.4442706f, 0.95697135f}
              });

      Dropout<TFloat32> instance = new Dropout<>(0.5f, Shape.of(3, 1), seed, TFloat32.class);

      Float[] expected = new Float[] {0f, 0f, 10.803643f, 1.354573f, 0f, 0f};

      // trainable is true, so there should be  dropout
      Operand<TFloat32> result = instance.call(tf, input, true, TFloat32.class);
      assertEquals(expectedShape, result.shape());
      // Note: this can only be evaluated once, or else the result will be updated with
      // new values and will not match expected.
      session.evaluate(expected, result);
    }
  }

  @Test
  public void testSupportsMasking() {
    long seed = 1001L;
    Dropout<TFloat32> instance = new Dropout<>(0.5f, seed, TFloat32.class);
    assertTrue(instance.isSupportsMasking());
  }
}
