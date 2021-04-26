package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.Tensor;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AddTest {
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
  double[][][] xsum = {
    {
      {1.86943758, 1.39738503, 1.65498872, 1.65746556, 1.09127339},
      {0.9617033, 1.49612674, 2.22012576, 1.06387551, 1.48973533},
      {1.7272264, 1.39105585, 2.08711617, 1.33690942, 1.85540083},
      {2.00503786, 0.59602925, 1.00291149, 1.85067532, 1.56199948}
    },
    {
      {1.40478652, 0.67829538, 1.75703778, 2.28429285, 1.54299154},
      {1.1543616, 1.40308904, 1.59185462, 1.28059728, 2.30713144},
      {1.40050067, 1.59518573, 1.89868217, 1.43297007, 2.22691399},
      {1.98149274, 1.7430799, 1.72313981, 1.40494213, 0.87391746}
    }
  };

  @Test
  public void testAdd() {
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
      Input<TFloat64> i3 =
          new Input<>(
              tf,
              "l3",
              TFloat64.class,
              TFloat64.class,
              Layer.Options.create().inputShape(Shape.of(4, 5)));
      Add<TFloat64> instance = new Add<>(tf, TFloat64.class);
      List<Operand<TFloat64>> resultList =

          instance.call(
                  Arrays.asList(
                      i1.getOutput(TFloat64.class),
                      i2.getOutput(TFloat64.class),
                      i3.getOutput(TFloat64.class)),
                  TFloat64.class);

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
        feedMap.put(i1.getOutput(TFloat64.class), x1Tensor);
        feedMap.put(i2.getOutput(TFloat64.class), x2Tensor);
        feedMap.put(i3.getOutput(TFloat64.class), x3Tensor);
        session.evaluate(tf.constant(xsum), result, feedMap);
      }
    }
  }

  @Test
  public void testMask() {
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
      Input<TFloat64> i3 =
          new Input<>(
              tf,
              "l3",
              TFloat64.class,
              TFloat64.class,
              Layer.Options.create().inputShape(Shape.of(4, 5)));
      Add<TFloat64> instance = new Add<>(tf, TFloat64.class);
      List<Operand<? extends TType>> inputs =
          Arrays.asList(
              i1.getOutput(TFloat64.class),
              i2.getOutput(TFloat64.class),
              i3.getOutput(TFloat64.class));
      List<Operand<? extends TType>> mask = Arrays.asList(null, null, null);

      List<Operand<TBool>> result = instance.computeMask(inputs, mask);
      assertNull(result);

      Operand<TFloat64> x1Op = tf.constant(x1);
      Operand<TFloat64> x2Op = tf.constant(x2);
      Operand<TFloat64> x3Op = tf.constant(x3);
      mask = Arrays.asList(x1Op, x2Op, x3Op);

      try (TFloat64 x1Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x1Op).run().get(0);
          TFloat64 x2Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x2Op).run().get(0);
          TFloat64 x3Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x3Op).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
        feedMap.put(i1.getOutput(TFloat64.class), x1Tensor);
        feedMap.put(i2.getOutput(TFloat64.class), x2Tensor);
        feedMap.put(i3.getOutput(TFloat64.class), x3Tensor);
        result = instance.computeMask(inputs, mask);
        Boolean[] expected = new Boolean[(int) result.get(0).size()];
        Arrays.fill(expected, true);
        session.evaluate(expected, result.get(0), feedMap);
      }
    }
  }

  @Test
  public void testMaskInvalidLengths() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
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
            Input<TFloat64> i3 =
                new Input<>(
                    tf,
                    "l3",
                    TFloat64.class,
                    TFloat64.class,
                    Layer.Options.create().inputShape(Shape.of(4, 5)));
            Add<TFloat64> instance = new Add<>(tf, TFloat64.class);
            List<Operand<? extends TType>> inputs =
                Arrays.asList(
                    i1.getOutput(TFloat64.class),
                    i2.getOutput(TFloat64.class),
                    i3.getOutput(TFloat64.class));
            List<Operand<? extends TType>> mask = Arrays.asList(null, null);
            instance.computeMask(inputs, mask);
          }
        });
  }
}
