package org.tensorflow.framework.layers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TType;

class DotTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  double[][] x1 = {
    {0.04867243, 0.42833055, 0.57495679, 0.04191259}, {0.48993384, 0.80122145, 0.8199583, 0.0552641}
  };
  double[][] x2 = {
    {0.37530763, 0.65938955, 0.69901548, 0.87864686},
    {0.79027356, 0.29017831, 0.62662979, 0.34575866}
  };

  double[][] xdot = {{0.73943388}, {1.15259719}};

  @Test
  public void testDot() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat64> input1 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4)));
      Operand<TFloat64> input2 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4)));
      Dot<TFloat64> instance = new Dot<>(1, TFloat64.class);
      List<Operand<TFloat64>> resultList =
          instance.call(tf, Arrays.asList(input1, input2), TFloat64.class);

      Operand<TFloat64> result = resultList.get(0);

      assertArrayEquals(new long[] {Shape.UNKNOWN_SIZE, 1}, result.shape().asArray());

      Operand<TFloat64> x1Op = tf.constant(x1);
      Operand<TFloat64> x2Op = tf.constant(x2);

      try (TFloat64 x1Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x1Op).run().get(0);
          TFloat64 x2Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x2Op).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
        feedMap.put(input1, x1Tensor);
        feedMap.put(input2, x2Tensor);
        session.evaluate(tf.constant(xdot), result, feedMap);
      }
    }
  }

  @Test
  public void testDotNegativeAxis() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat64> input1 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4)));
      Operand<TFloat64> input2 =
          Input.input(tf, TFloat64.class, Layer.Options.create().inputShape(Shape.of(4)));
      Dot<TFloat64> instance = new Dot<>(new int[] {-1, -1}, TFloat64.class);
      List<Operand<TFloat64>> resultList =
          instance.call(tf, Arrays.asList(input1, input2), TFloat64.class);

      Operand<TFloat64> result = resultList.get(0);

      assertArrayEquals(new long[] {Shape.UNKNOWN_SIZE, 1}, result.shape().asArray());

      Operand<TFloat64> x1Op = tf.constant(x1);
      Operand<TFloat64> x2Op = tf.constant(x2);

      try (TFloat64 x1Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x1Op).run().get(0);
          TFloat64 x2Tensor =
              (TFloat64) session.getGraphSession().runner().fetch(x2Op).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
        feedMap.put(input1, x1Tensor);
        feedMap.put(input2, x2Tensor);
        session.evaluate(tf.constant(xdot), result, feedMap);
      }
    }
  }

  @Test
  public void testDotComputeOutputShape() {
    Dot<TFloat32> dot = new Dot<>(-1, TFloat32.class);

    List<Shape> outputShapes =
        dot.computeOutputShape(Arrays.asList(Shape.of(4, 5), Shape.of(4, 5)));
    assertFalse(outputShapes.isEmpty());
    assertArrayEquals(new long[] {4, 1}, outputShapes.get(0).asArray());
  }
}
