package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlphaDropoutTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testAlphaDropout() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      long seed = 1001L;
      TestSession.setEpsilon(1e-4F);
      Shape expectedShape = Shape.of(3, 2, 3);
      float[][][] x =
          new float[][][] {
            {{0.14517927f, 0.2574964f, 0.2291325f}, {0.9145494f, 0.9378068f, 0.6827883f}},
            {{0.27121753f, 0.08317473f, 0.3770739f}, {0.25451255f, 0.18511271f, 0.5620538f}},
            {{0.40101776f, 0.25205433f, 0.05103926f}, {0.08764106f, 0.00593294f, 0.37244815f}}
          };
      AlphaDropout<TFloat32> instance =
          new AlphaDropout<>(
              tf, 0.2f, seed, TFloat32.class, Layer.Options.create().inputShape(Shape.of(3, 2, 3)));
      Operand<TFloat32> input = tf.constant(x);

      Operand<TFloat32> result = instance.call(input, false, TFloat32.class);
      assertEquals(expectedShape, result.shape());
      session.evaluate(tf.identity(input), result);

      float[][][] exp = {
        {{-1.236160f, 0.535354f, 0.510425f}, {1.112841f, 1.133282f, 0.909145f}},
        {{0.547414f, 0.382143f, -1.236160f}, {-1.236160f, 0.471736f, -1.236160f}},
        {{0.661496f, 0.530571f, -1.236160f}, {0.386068f, 0.314254f, 0.636386f}}
      };

      Operand<TFloat32> expected = tf.constant(exp);
      result = instance.call(input, true, TFloat32.class);

      assertEquals(expectedShape, result.shape());

      // NOTE: result can only  be evaluated once, otherwise new random numbers
      // will be generated and won't match the expected
      session.evaluate(expected, result);
    }
  }
}
