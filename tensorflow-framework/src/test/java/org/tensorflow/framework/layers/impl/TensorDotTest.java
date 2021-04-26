package org.tensorflow.framework.layers.impl;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.Tensor;
import org.tensorflow.exceptions.TFInvalidArgumentException;
import org.tensorflow.framework.op.math.TensorDot;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TensorDotTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  float[][] aArray = {
    {1, 1, 1},
    {1, 1, 1},
    {1, 1, 1},
  };

  float[][][] bArray = {{{2, 3, 1}}};

  @Test
  public void testInvalidShape() {
    for (TestSession.Mode tfMode : tfModes)
      assertThrows(
          TFInvalidArgumentException.class,
          () -> {
            try (TestSession session = TestSession.createTestSession(tfMode)) {
              Ops tf = session.getTF();
              float[][] a = new float[][] {{1, 2}, {3, 4}};
              float[][] b = new float[][] {{1, 2}, {3, 4}, {5, 6}};

              Operand<TFloat32> aOp = tf.constant(a);
              Operand<TFloat32> bOp = tf.constant(b);

              TensorDot.tensordot(tf.scope(), aOp, bOp, new int[] {1, 0});
            }
          });
  }

  @Test
  public void testInvalidDynamicShape() {
    assertThrows(
        TFInvalidArgumentException.class,
        () -> {
          try (TestSession session = TestSession.createTestSession(TestSession.Mode.GRAPH)) {
            Ops tf = session.getTF();

            Operand<TFloat32> aPH = tf.placeholder(TFloat32.class);
            Operand<TFloat32> bPH = tf.placeholder(TFloat32.class);
            Operand<TInt32> axesPH = tf.placeholder(TInt32.class);

            float[][] a = new float[][] {{1, 2}, {3, 4}};
            float[][] b = new float[][] {{1, 2}, {3, 4}, {5, 6}};

            Operand<TFloat32> aOp = tf.constant(a);
            Operand<TFloat32> bOp = tf.constant(b);
            Operand<TInt32> axesOp = tf.constant(new int[] {1, 0});

            Operand<TFloat32> output = TensorDot.tensordot(tf.scope(), aPH, bPH, axesPH);

            try (TFloat32 aTensor =
                    (TFloat32) session.getGraphSession().runner().fetch(aOp).run().get(0);
                TFloat32 bTensor =
                    (TFloat32) session.getGraphSession().runner().fetch(bOp).run().get(0);
                TInt32 axesTensor =
                    (TInt32) session.getGraphSession().runner().fetch(axesOp).run().get(0)) {
              Map<Operand<? extends TType>, Tensor> feedMap = new HashMap<>();
              feedMap.put(aPH, aTensor);
              feedMap.put(bPH, bTensor);
              feedMap.put(axesPH, axesTensor);
              session.run(output, feedMap);
            }
          }
        });
  }

  @Test
  public void testInvalidAxes() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        float[][] a = new float[][] {{1, 2}, {3, 4}};
        float[][] b = new float[][] {{1, 2}, {3, 4}};

        Operand<TFloat32> aOp = tf.constant(a);
        Operand<TFloat32> bOp = tf.constant(b);
        assertThrows(
            IllegalArgumentException.class, () -> TensorDot.tensordot(tf.scope(), aOp, bOp, -1));
        assertThrows(
            IllegalArgumentException.class, () -> TensorDot.tensordot(tf.scope(), aOp, bOp, 3));
        assertThrows(
            IllegalArgumentException.class,
            () -> TensorDot.tensordot(tf.scope(), aOp, bOp, new int[] {1, 0, 1}));
        assertThrows(
            Exception.class, () -> TensorDot.tensordot(tf.scope(), aOp, bOp, new int[] {0, 7}));
      }
  }

  @Test
  public void testValidAxis1() {
    Shape expectedShape = Shape.of(3, 1, 1);

    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Operand<TFloat32> expected = tf.constant(new float[][][] {{{6}}, {{6}}, {{6}}});
        Operand<TFloat32> a = tf.constant(aArray);
        Operand<TFloat32> b = tf.constant(bArray);
        Operand<TFloat32> result = TensorDot.tensordot(tf.scope(), a, b, new int[] {1, 2});
        assertEquals(expectedShape, result.shape());
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testValidAxis2() {

    Shape expectedShape = Shape.of(3, 1, 1);

    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Operand<TFloat32> expected = tf.constant(new float[][][] {{{6}}, {{6}}, {{6}}});
        Operand<TFloat32> a = tf.constant(aArray);
        Operand<TFloat32> b = tf.constant(bArray);
        Operand<TFloat32> result = TensorDot.tensordot(tf.scope(), a, b, new int[][] {{1}, {2}});
        assertEquals(expectedShape, result.shape());
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testValidAxis3() {
    Shape expectedShape = Shape.of(3, 3, 1, 1, 3);
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Operand<TFloat32> expected =
            tf.constant(
                new float[][][][][] {
                  {{{{2, 3, 1}}}, {{{2, 3, 1}}}, {{{2, 3, 1}}}},
                  {{{{2, 3, 1}}}, {{{2, 3, 1}}}, {{{2, 3, 1}}}},
                  {{{{2, 3, 1}}}, {{{2, 3, 1}}}, {{{2, 3, 1}}}}
                });

        Operand<TFloat32> a = tf.constant(aArray);
        Operand<TFloat32> b = tf.constant(bArray);
        Operand<TFloat32> result = TensorDot.tensordot(tf.scope(), a, b, 0);
        assertEquals(expectedShape, result.shape());
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testValidAxis4() {
    Shape expectedShape = Shape.of(3, 3, 1, 1, 3);
    // for (TestSession.Mode tfMode : tfModes)
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.GRAPH)) {
      Ops tf = session.getTF();
      Operand<TFloat32> expected =
          tf.constant(
              new float[][][][][] {
                {{{{2, 3, 1}}}, {{{2, 3, 1}}}, {{{2, 3, 1}}}},
                {{{{2, 3, 1}}}, {{{2, 3, 1}}}, {{{2, 3, 1}}}},
                {{{{2, 3, 1}}}, {{{2, 3, 1}}}, {{{2, 3, 1}}}}
              });

      Operand<TFloat32> a = tf.constant(aArray);
      Operand<TFloat32> b = tf.constant(bArray);
      Operand<TFloat32> result = TensorDot.tensordot(tf.scope(), a, b, new int[][] {{}, {}});
      assertEquals(expectedShape, result.shape());
      session.evaluate(expected, result);
    }
  }
}
