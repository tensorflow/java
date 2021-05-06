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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConcatenateTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  double[][][] x1 = {
    {
      {5.67710153e-02, 5.68608495e-01, 6.94753423e-01, 7.06106392e-01, 9.55901476e-01},
      {1.16221311e-01, 2.77955841e-01, 8.48163908e-01, 6.65887805e-01, 8.48399407e-01},
      {1.32232733e-01, 6.07996978e-01, 5.04046847e-01, 9.79583238e-02, 6.71959629e-01},
      {9.69122927e-01, 2.65313461e-01, 7.25259997e-01, 2.95230608e-02, 2.68600949e-01}
    },
    {
      {9.26675552e-02, 9.11034266e-01, 9.42616405e-01, 1.76616001e-01, 4.35131783e-01},
      {3.42867908e-01, 4.42621793e-02, 1.86904412e-01, 2.30573118e-05, 1.40271865e-01},
      {9.92634263e-01, 3.50624173e-01, 9.53986246e-01, 6.98818650e-01, 9.82469750e-01},
      {7.84919140e-01, 5.03811516e-01, 2.99471974e-01, 4.13124006e-01, 1.67204622e-01}
    }
  };
  double[][][] x2 = {
    {
      {0.28151136, 0.99996448, 0.94123237, 0.92673981, 0.58165141},
      {0.41634875, 0.87652871, 0.52327084, 0.60899574, 0.97460049},
      {0.77076745, 0.46439171, 0.25499671, 0.18764164, 0.13748069},
      {0.19368776, 0.11778548, 0.55451791, 0.06335824, 0.63534461}
    },
    {
      {0.52078045, 0.85837043, 0.44845609, 0.69742864, 0.99834278},
      {0.23162816, 0.63328557, 0.24782906, 0.37476312, 0.16915018},
      {0.96264864, 0.97704619, 0.58534633, 0.87405632, 0.4750216},
      {0.73685149, 0.13915827, 0.23992944, 0.06455061, 0.30500096}
    }
  };

  double[][][] x = {
    {
      {5.67710153e-02, 5.68608495e-01, 6.94753423e-01, 7.06106392e-01, 9.55901476e-01},
      {1.16221311e-01, 2.77955841e-01, 8.48163908e-01, 6.65887805e-01, 8.48399407e-01},
      {1.32232733e-01, 6.07996978e-01, 5.04046847e-01, 9.79583238e-02, 6.71959629e-01},
      {9.69122927e-01, 2.65313461e-01, 7.25259997e-01, 2.95230608e-02, 2.68600949e-01},
      {2.81511360e-01, 9.99964484e-01, 9.41232373e-01, 9.26739808e-01, 5.81651412e-01},
      {4.16348754e-01, 8.76528710e-01, 5.23270835e-01, 6.08995742e-01, 9.74600488e-01},
      {7.70767447e-01, 4.64391706e-01, 2.54996707e-01, 1.87641636e-01, 1.37480691e-01},
      {1.93687759e-01, 1.17785480e-01, 5.54517906e-01, 6.33582392e-02, 6.35344611e-01}
    },
    {
      {9.26675552e-02, 9.11034266e-01, 9.42616405e-01, 1.76616001e-01, 4.35131783e-01},
      {3.42867908e-01, 4.42621793e-02, 1.86904412e-01, 2.30573118e-05, 1.40271865e-01},
      {9.92634263e-01, 3.50624173e-01, 9.53986246e-01, 6.98818650e-01, 9.82469750e-01},
      {7.84919140e-01, 5.03811516e-01, 2.99471974e-01, 4.13124006e-01, 1.67204622e-01},
      {5.20780455e-01, 8.58370427e-01, 4.48456095e-01, 6.97428643e-01, 9.98342781e-01},
      {2.31628161e-01, 6.33285571e-01, 2.47829057e-01, 3.74763124e-01, 1.69150184e-01},
      {9.62648639e-01, 9.77046190e-01, 5.85346335e-01, 8.74056318e-01, 4.75021602e-01},
      {7.36851488e-01, 1.39158268e-01, 2.39929436e-01, 6.45506139e-02, 3.05000963e-01}
    }
  };

  @Test
  public void testConcatenate() {
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
      Concatenate<TFloat64> instance = new Concatenate<>(tf, 1, TFloat64.class);
      List<Operand<TFloat64>> resultList =
          instance.call(
              Arrays.asList(i1.getOutput(TFloat64.class), i2.getOutput(TFloat64.class)),
              TFloat64.class);

      Operand<TFloat64> result = resultList.get(0);

      assertArrayEquals(new long[] {Shape.UNKNOWN_SIZE, 8, 5}, result.shape().asArray());

      Operand<TFloat64> x1Op = tf.constant(x1);
      Operand<TFloat64> x2Op = tf.constant(x2);

      try (TFloat64 x1Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x1Op).run().get(0);
          TFloat64 x2Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x2Op).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
        feedMap.put(i1.getOutput(TFloat64.class), x1Tensor);
        feedMap.put(i2.getOutput(TFloat64.class), x2Tensor);
        session.evaluate(tf.constant(x), result, feedMap);
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
      Concatenate<TFloat64> instance = new Concatenate<>(tf, TFloat64.class);
      List<Operand<? extends TType>> inputs =
          Arrays.asList(i1.getOutput(TFloat64.class), i2.getOutput(TFloat64.class));
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
        feedMap.put(i1.getOutput(TFloat64.class), x1Tensor);
        feedMap.put(i2.getOutput(TFloat64.class), x2Tensor);
        result = instance.computeMask(inputs, mask);
        Boolean[] expected = new Boolean[(int) result.get(0).size()];
        Arrays.fill(expected, true);
        session.evaluate(expected, result.get(0), feedMap);
      }
    }
  }

  @Test
  public void testMaskInvalidMaskSize() {
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
            Concatenate<TFloat64> instance = new Concatenate<>(tf, TFloat64.class);
            List<Operand<? extends TType>> inputs =
                Arrays.asList(i1.getOutput(TFloat64.class), i2.getOutput(TFloat64.class));
            List<Operand<? extends TType>> mask = Arrays.asList(null, null, null);

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
              feedMap.put(i1.getOutput(TFloat64.class), x1Tensor);
              feedMap.put(i2.getOutput(TFloat64.class), x2Tensor);
              result = instance.computeMask(inputs, mask);
              Boolean[] expected = new Boolean[(int) result.get(0).size()];
              Arrays.fill(expected, true);
              session.evaluate(expected, result.get(0), feedMap);
            }
          }
        });
  }
}
