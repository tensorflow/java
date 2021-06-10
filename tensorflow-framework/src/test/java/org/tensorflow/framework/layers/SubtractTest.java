package org.tensorflow.framework.layers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TType;

class SubtractTest {
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

  double[][][] xsub = {
    {
      {-0.69258186, 0.26787779, -0.64946903, -0.34971508, 0.28432377},
      {-0.54574656, 0.22208098, 0.05486762, -0.86761374, 0.27397776},
      {0.86810758, -0.26631865, 0.09942654, -0.48761712, 0.67655949},
      {-0.03174178, -0.18566368, 0.66702656, 0.86107248, 0.19410511}
    },
    {
      {-0.43575572, 0.28956546, 0.06540471, -0.01211101, 0.65595357},
      {0.13955201, 0.4611486, 0.60807778, -0.27549293, 0.26073884},
      {-0.49546127, -0.37173705, -0.51848316, 0.58399839, 0.26113823},
      {0.33709956, 0.58802363, 0.01073621, -0.46140325, -0.5151461}
    }
  };

  @Test
  public void testSubtract() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat64> input1 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));
      Operand<TFloat64> input2 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));
      Subtract<TFloat64> instance = new Subtract<>(TFloat64.class);
      List<Operand<TFloat64>> resultList =
          instance.call(tf, Arrays.asList(input1, input2), TFloat64.class);

      Operand<TFloat64> result = resultList.get(0);

      assertArrayEquals(new long[] {Shape.UNKNOWN_SIZE, 4, 5}, result.shape().asArray());

      Operand<TFloat64> x1Op = tf.constant(x1);
      Operand<TFloat64> x2Op = tf.constant(x2);

      try (TFloat64 x1Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x1Op).run().get(0);
          TFloat64 x2Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x2Op).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
        feedMap.put(input1, x1Tensor);
        feedMap.put(input2, x2Tensor);
        session.evaluate(tf.constant(xsub), result, feedMap);
      }
    }
  }

  @Test
  public void testSubtractInvalidInputsLength() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Operand<TFloat64> input1 =
                Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));
            Operand<TFloat64> input2 =
                Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));
            Subtract<TFloat64> instance = new Subtract<>(TFloat64.class);

            // not used, should throw exception
            List<Operand<TFloat64>> resultList =
                instance.call(tf, Arrays.asList(input1, input2, input2), TFloat64.class);
          }
        });
  }

  @Test
  public void testMask() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat64> input1 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));
      Operand<TFloat64> input2 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4, 5)));

      Subtract<TFloat64> instance = new Subtract<>(TFloat64.class);
      instance.init(tf);
      List<Operand<? extends TType>> inputs = Arrays.asList(input1, input2);
      List<Operand<? extends TType>> mask = Arrays.asList(null, null);
      List<Operand<TBool>> result = instance.computeMask(inputs, mask);
      assertNull(result);

      Operand<TFloat64> x1Op = tf.constant(x1);
      Operand<TFloat64> x2Op = tf.constant(x2);
      mask = Arrays.asList(x1Op, x2Op);

      try (TFloat64 x1Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x1Op).run().get(0);
          TFloat64 x2Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x2Op).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
        feedMap.put(input1, x1Tensor);
        feedMap.put(input2, x2Tensor);
        result = instance.computeMask(inputs, mask);
        Boolean[] expected = new Boolean[(int) result.get(0).size()];
        Arrays.fill(expected, true);
        session.evaluate(expected, result.get(0), feedMap);
      }
    }
  }
}
