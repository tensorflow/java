package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat64;

class ThresholdedReLUTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  double[][][] inputArray = {
    {
      {2.70857435f, 8.25453567f, 9.75479311f, 1.10273526f},
      {8.69836437f, 2.27818352f, 8.60856328f, 1.43265882f},
      {0.75845834f, 5.60463474f, 7.35998787f, 0.06365667f}
    },
    {
      {4.87355239f, 9.90221978f, 5.39014402f, 2.05263398f},
      {5.91652733f, 0.9186602f, 0.91375672f, 0.56053326f},
      {2.08046551f, 8.53763374f, 6.40378721f, 5.83284758f}
    }
  };

  @Test
  public void testCallThetaPoint5() {

    double[][][] expectedArray = {
      {
        {2.7085743, 8.254536, 9.754793, 1.1027353},
        {8.698364, 2.2781835, 8.608563, 1.4326588},
        {0.7584583, 5.604635, 7.3599877, 0.}
      },
      {
        {4.8735523, 9.90222, 5.390144, 2.052634},
        {5.9165273, 0.9186602, 0.9137567, 0.5605333},
        {2.0804656, 8.537634, 6.403787, 5.8328476}
      }
    };

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      float theta = 0.5f;
      ThresholdedReLU<TFloat64> instance =
          new ThresholdedReLU<>(
              theta, TFloat64.class, Layer.Options.create().inputShape(Shape.of(2, 3, 4)));

      Operand<TFloat64> result = instance.call(tf, tf.constant(inputArray), TFloat64.class);

      session.evaluate(tf.constant(expectedArray), result);
    }
  }
}
