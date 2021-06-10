package org.tensorflow.framework.layers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.Tensor;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TType;

class MultiplyTest {
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
  double[][][] x3 = {
    {
      {0.90545522, 0.55172128, 0.87254455, 0.1396359, 0.1538656},
      {0.04276304, 0.9315817, 0.91360492, 0.00604873, 0.04174153},
      {0.60856471, 0.37386072, 0.68937889, 0.21272655, 0.65082257},
      {0.44925012, 0.29825938, 0.20043074, 0.84906101, 0.78397795}
    },
    {
      {0.70855776, 0.17650269, 0.02422264, 0.84612297, 0.72450389},
      {0.05133022, 0.61175015, 0.56296539, 0.66780478, 0.63326012},
      {0.11212696, 0.50675282, 0.58170013, 0.21101392, 0.83090424},
      {0.91830915, 0.42113009, 0.49795942, 0.2814478, 0.11920788}
    }
  };
  double[][][] xmul = {
    {
      {0.10177144, 0.0887428, 0.04153505, 0.07615415, 0.03069209},
      {0.0058437, 0.06273996, 0.38919256, 0.00055383, 0.0210964},
      {0.07573484, 0.09007803, 0.33500089, 0.05456525, 0.16161162},
      {0.27173657, 0.00404111, 0.00997398, 0.05556795, 0.11125445}
    },
    {
      {0.05222982, 0.00741081, 0.01815711, 0.4374849, 0.04340629},
      {0.01536318, 0.06324873, 0.0969503, 0.05002163, 0.4328112},
      {0.03964879, 0.13257892, 0.21313739, 0.06077848, 0.39066002},
      {0.23341656, 0.14758288, 0.18685326, 0.07383407, 0.00906609}
    }
  };

  @Test
  public void testAdd() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Operand<TFloat64> input1 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));
      Operand<TFloat64> input2 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));
      Operand<TFloat64> input3 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));
      Multiply<TFloat64> instance = new Multiply<>(TFloat64.class);

      List<Operand<TFloat64>> resultList =
          instance.call(tf, Arrays.asList(input1, input2, input3), TFloat64.class);

      Operand<TFloat64> result = resultList.get(0);

      assertArrayEquals(new long[] {Shape.UNKNOWN_SIZE, 4, 5}, result.shape().asArray());

      Operand<TFloat64> x1Op = tf.constant(x1);
      Operand<TFloat64> x2Op = tf.constant(x2);
      Operand<TFloat64> x3Op = tf.constant(x3);

      try (TFloat64 x1Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x1Op).run().get(0);
          TFloat64 x2Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x2Op).run().get(0);
          TFloat64 x3Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x3Op).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
        feedMap.put(input1, x1Tensor);
        feedMap.put(input2, x2Tensor);
        feedMap.put(input3, x3Tensor);
        session.evaluate(tf.constant(xmul), result, feedMap);
      }
    }
  }
}
