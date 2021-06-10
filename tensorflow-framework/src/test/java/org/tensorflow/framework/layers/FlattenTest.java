package org.tensorflow.framework.layers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.layers.impl.TensorFormat;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

class FlattenTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testCall() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape inputShape = Shape.of(1, 3, 2);
      float[] a = {1F, 2F, 3F, 4F, 5F, 6F};
      Float[] expected = {1F, 2F, 3F, 4F, 5F, 6F};
      Shape expectedShape = Shape.of(1, 6);
      Operand<TFloat32> input = tf.reshape(tf.constant(a), tf.constant(inputShape));
      Flatten<TFloat32> layer = new Flatten<>(TFloat32.class);
      Operand<TFloat32> output = layer.call(tf, input, TFloat32.class);
      assertEquals(expectedShape, output.shape());
      session.evaluate(expected, output);
    }
  }

  @Test
  public void testCallChannelsFirst() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[] a = {
        0.12911275f,
        0.16172077f,
        0.7024991f,
        0.3936557f,
        0.8216052f,
        0.04838822f,
        0.96763366f,
        0.1477106f,
        0.03416549f,
        0.40088153f
      };
      Shape expectedShape = Shape.of(10, 1);
      Operand<TFloat32> input = tf.constant(a);
      Flatten<TFloat32> layer = new Flatten<>(TensorFormat.NCHW, TFloat32.class);
      Operand<TFloat32> output = layer.call(tf, input, TFloat32.class);
      assertEquals(expectedShape, output.asOutput().shape());
      Operand<TFloat32> expected = tf.expandDims(input, tf.constant(-1));
      session.evaluate(expected, output);
    }
  }

  /** Test of computeOutputShape method, of class Flatten. */
  @Test
  public void testComputeOutputShape() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape inputShape = Shape.of(1, 3, 2);
      Shape expectedShape = Shape.of(1, 6);
      Flatten<TFloat32> layer = new Flatten<>(TFloat32.class);
      layer.init(tf);
      List<Shape> computedShapes = layer.computeOutputShape(Collections.singletonList(inputShape));
      assertEquals(expectedShape, computedShapes.get(0));
    }
  }
}
