package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepeatVectorTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testCall3_2() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(3, 2);
      RepeatVector<TFloat32> instance = new RepeatVector(tf, 3, TFloat32.class);

      double[][] array = {
        {0.41448207, 0.71509451}, {0.21307868, 0.76890945}, {0.37533432, 0.7761148}
      };

      Shape expectedShape = Shape.of(3, 3, 2);

      double[][][] expected = {
        {{0.41448206, 0.7150945}, {0.41448206, 0.7150945}, {0.41448206, 0.7150945}},
        {{0.21307868, 0.76890945}, {0.21307868, 0.76890945}, {0.21307868, 0.76890945}},
        {{0.37533432, 0.7761148}, {0.37533432, 0.7761148}, {0.37533432, 0.7761148}}
      };

      Operand<TFloat64> result =
          instance.call(tf.dtypes.cast(tf.constant(array), TFloat64.class), TFloat64.class);

      assertEquals(expectedShape, result.shape());
      session.evaluate(tf.constant(expected), result);
    }
  }
}
