package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.Tensor;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AverageTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  double[][][] x1 = {
    {
      {0.13570025, 0.55677077, 0.06648757, 0.58405729, 0.61086578},
      {0.18659685, 0.39331301, 0.68069423, 0.09510652, 0.86098578},
      {0.99338463, 0.37543824, 0.74858191, 0.31828287, 0.94056888},
      {0.76202298, 0.05605309, 0.73475366, 0.9313434, 0.48606332}
    },
    {
      {0.13023652, 0.39567908, 0.89910993, 0.71302943, 0.73722061},
      {0.6212917, 0.62624375, 0.8184835, 0.16864979, 0.96730508},
      {0.39645622, 0.35834793, 0.39924944, 0.90297727, 0.82857399},
      {0.70014157, 0.95498672, 0.6179583, 0.33104554, 0.11978174}
    }
  };
  double[][][] x2 = {
    {
      {0.82828211, 0.28889298, 0.7159566, 0.93377237, 0.32654201},
      {0.73234341, 0.17123203, 0.62582661, 0.96272026, 0.58700802},
      {0.12527705, 0.64175689, 0.64915537, 0.80589999, 0.26400939},
      {0.79376476, 0.24171677, 0.0677271, 0.07027092, 0.29195821}
    },
    {
      {0.56599224, 0.10611362, 0.83370522, 0.72514044, 0.08126704},
      {0.48173969, 0.16509515, 0.21040572, 0.44414272, 0.70656624},
      {0.89191749, 0.73008498, 0.9177326, 0.31897888, 0.56743576},
      {0.36304201, 0.36696309, 0.60722209, 0.79244879, 0.63492784}
    }
  };

  double[][][] xavg = {
    {
      {0.48199118, 0.42283187, 0.39122208, 0.75891483, 0.46870389},
      {0.45947013, 0.28227252, 0.65326042, 0.52891339, 0.7239969},
      {0.55933084, 0.50859756, 0.69886864, 0.56209143, 0.60228914},
      {0.77789387, 0.14888493, 0.40124038, 0.50080716, 0.38901076}
    },
    {
      {0.34811438, 0.25089635, 0.86640757, 0.71908493, 0.40924383},
      {0.55151569, 0.39566945, 0.51444461, 0.30639626, 0.83693566},
      {0.64418686, 0.54421645, 0.65849102, 0.61097808, 0.69800487},
      {0.53159179, 0.66097491, 0.6125902, 0.56174716, 0.37735479}
    }
  };

  @Test
  public void testAverage() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Input<TFloat64> i1 =
          new Input<>(
              tf,
              "l1",
              TFloat64.class,
              TFloat64.class,
              Layer.Options.create().inputShape(Shape.of(4, 5)));
      Input<TFloat64> i2 =
          new Input<>(
              tf,
              "l2",
              TFloat64.class,
              TFloat64.class,
              Layer.Options.create().inputShape(Shape.of(4, 5)));
      Average<TFloat64> instance = new Average<>(tf, TFloat64.class);
      List<Operand<TFloat64>> resultList =
          instance.call(
              Arrays.asList(i1.getOutput(TFloat64.class), i2.getOutput(TFloat64.class)),
              TFloat64.class);

      Operand<TFloat64> result = resultList.get(0);

      assertArrayEquals(new long[] {Shape.UNKNOWN_SIZE, 4, 5}, result.shape().asArray());

      Operand<TFloat64> x1Op = tf.constant(x1);
      Operand<TFloat64> x2Op = tf.constant(x2);

      try (TFloat64 x1Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x1Op).run().get(0);
          TFloat64 x2Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x2Op).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
        feedMap.put(i1.getOutput(TFloat64.class), x1Tensor);
        feedMap.put(i2.getOutput(TFloat64.class), x2Tensor);
        session.evaluate(tf.constant(xavg), result, feedMap);
      }
    }
  }
}
