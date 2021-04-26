package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tensorflow.framework.utils.CastHelper.cast;

class LambdaTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testCallLambda() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(3, 2);

      Lambda<TFloat32> instance = new Lambda<>(tf, TFloat32.class);
      instance.setLamda((t, y) -> t.math.mul(cast(t, t.constant(2), y.type()), y));

      double[][] array = {
        {0.41448207, 0.71509451}, {0.21307868, 0.76890945}, {0.37533432, 0.7761148}
      };
      double[][] expected = new double[array.length][array[0].length];
      for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[0].length; j++) {
          expected[i][j] = array[i][j] * 2;
        }
      }

      Operand<TFloat64> result =
          instance.call(tf.dtypes.cast(tf.constant(array), TFloat64.class), TFloat64.class);

      assertEquals(shape, result.shape());
      session.evaluate(tf.constant(expected), result);
    }
  }
}
