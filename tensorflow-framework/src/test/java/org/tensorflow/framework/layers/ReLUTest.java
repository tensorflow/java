package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

@SuppressWarnings("FloatingPointLiteralPrecision")
class ReLUTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  float[][][] inputArray = {
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
  public void testCallMaxValue10() {

    float[][][] expectedArray = {
      {
        {2.7085743f, 8.254536f, 9.754793f, 1.1027353f},
        {8.698364f, 2.2781835f, 8.608563f, 1.4326588f},
        {0.7584583f, 5.604635f, 7.3599877f, 0.06365667f}
      },
      {
        {4.8735523f, 9.90222f, 5.390144f, 2.052634f},
        {5.9165273f, 0.9186602f, 0.9137567f, 0.5605333f},
        {2.0804656f, 8.537634f, 6.403787f, 5.8328476f}
      }
    };

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      float maxValue = 10f;
      ReLU<TFloat32> instance =
          new ReLU<>(
              ReLU.DEFAULT_NEGATIVE_SLOPE,
              maxValue,
              ReLU.DEFAULT_THRESHOLD,
              TFloat32.class,
              Layer.Options.create().inputShape(Shape.of(2, 3, 4)));

      Operand<TFloat32> result = instance.call(tf, tf.constant(inputArray), TFloat32.class);

      session.evaluate(tf.constant(expectedArray), result);
    }
  }

  @Test
  public void testCallNegativeSlope() {

    float[][][] expectedArray = {
      {
        {2.7085743f, 8.254536f, 9.754793f, 1.1027353f},
        {8.698364f, 2.2781835f, 8.608563f, 1.4326588f},
        {0.7584583f, 5.604635f, 7.3599877f, 0.06365667f}
      },
      {
        {4.8735523f, 9.90222f, 5.390144f, 2.052634f},
        {5.9165273f, 0.9186602f, 0.9137567f, 0.5605333f},
        {2.0804656f, 8.537634f, 6.403787f, 5.8328476f}
      }
    };

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      float negativeSlope = 0.2f;
      ReLU<TFloat32> instance =
          new ReLU<>(
              negativeSlope, TFloat32.class, Layer.Options.create().inputShape(Shape.of(2, 3, 4)));

      Operand<TFloat32> result = instance.call(tf, tf.constant(inputArray), TFloat32.class);

      session.evaluate(tf.constant(expectedArray), result);
    }
  }

  @Test
  public void testCallMaxValue6() {

    double[][][] expectedArray = {
      {
        {2.7085743, 6., 6., 1.1027353},
        {6., 2.2781835, 6., 1.4326588},
        {0.7584583, 5.604635, 6., 0.06365667}
      },
      {
        {4.8735523, 6., 5.390144, 2.052634},
        {5.9165273, 0.9186602, 0.9137567, 0.5605333},
        {2.0804656, 6., 6., 5.8328476}
      }
    };

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      float maxValue = 6f;
      ReLU<TFloat32> instance =
          new ReLU<>(
              ReLU.DEFAULT_NEGATIVE_SLOPE,
              maxValue,
              ReLU.DEFAULT_THRESHOLD,
              TFloat32.class,
              Layer.Options.create().inputShape(Shape.of(2, 3, 4)));

      Operand<TFloat64> result =
          instance.call(
              tf, tf.dtypes.cast(tf.constant(inputArray), TFloat64.class), TFloat64.class);

      session.evaluate(tf.constant(expectedArray), result);
    }
  }
}
