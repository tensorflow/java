package org.tensorflow.framework.op;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

class NnOpsTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void testSigmoidCrossEntropyWithLogits() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        FrameworkOps fops = FrameworkOps.create(tf);
        float[] x = new float[] {-100, -2, -2, 0, 2, 2, 2, 100};
        float[] y = new float[] {0, 0, 1, 0, 0, 1, 0.5f, 1};

        Operand<TFloat32> logits = tf.constant(x);
        Operand<TFloat32> targets = tf.constant(y);
        Operand<TFloat32> loss = fops.nn.sigmoidCrossEntropyWithLogits(targets, logits);
        Operand<TFloat32> expected =
            tf.constant(
                new float[] {
                  0.f, 0.126928f, 2.126928f, 0.6931472f,
                  2.126928f, 0.126928f, 1.126928f, 0.f
                });
        session.evaluate(expected, loss);
      }
  }

  @Test
  public void testSoftmaxCrossEntropyWithLogits() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        FrameworkOps fops = FrameworkOps.create(tf);
        float[] x = new float[] {-100, -2, -2, 0, 2, 2, 2, 100};
        float[] y = new float[] {0, 0, 1, 0, 0, 1, 0.5f, 1};

        Operand<TFloat32> logits = tf.constant(x);
        Operand<TFloat32> targets = tf.constant(y);
        Operand<TFloat32> loss = fops.nn.softmaxCrossEntropyWithLogits(targets, logits, 0);

        session.evaluate(249.0f, loss);
      }
  }

  @Test
  public void testSparseSoftmaxCrossEntropyWithLogits() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        FrameworkOps fops = FrameworkOps.create(tf);
        float[][] x = new float[][] {{0, 0}};
        int[] y = new int[] {0};

        Operand<TFloat32> logits = tf.constant(x);
        Operand<TInt32> labels = tf.constant(y);
        Operand<TFloat32> loss = fops.nn.sparseSoftmaxCrossEntropyWithLogits(labels, logits);

        session.evaluate(0.69314718f, loss);
      }
  }
}
