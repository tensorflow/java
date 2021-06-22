package org.tensorflow.framework.op;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class LinalgOpsTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void test2D() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        FrameworkOps fops = FrameworkOps.create(tf);
        Operand<TFloat32> a = tf.constant(new float[][] {{3.7213619f}});
        Operand<TFloat32> b = tf.constant(new float[][] {{8.153921f}});

        Operand<TFloat32> ans = fops.linalg.matmul(a, b);
        Operand<TFloat32> expected = tf.constant(new float[][] {{30.34369f}});
        session.evaluate(expected, ans);

        Operand<TFloat64> a64 =
            tf.constant(new double[][] {{-8.944851}, {4.1711287}, {-0.22380222}});
        Operand<TFloat64> b64 =
            tf.constant(
                new double[][] {{-14.276086, -12.433481, -2.2447076, -1.5775859, 1.8588694}});

        Operand<TFloat64> ans64 = fops.linalg.matmul(a64, b64);
        Operand<TFloat64> expected64 =
            tf.constant(
                new double[][] {
                  {127.69746, 111.21564, 20.078575, 14.111271, -16.62731},
                  {-59.547394, -51.861652, -9.362965, -6.580314, 7.753584},
                  {3.1950197, 2.7826407, 0.50237054, 0.35306725, -0.4160191}
                });
        session.evaluate(expected64, ans64);

        a64 =
            tf.constant(
                new double[][] {
                  {-9.189821, -1.588742, -8.684379},
                  {-10.953391, -8.473055, -6.8909864},
                  {-11.712155, -6.6350083, -2.4441578},
                  {1.4037079, -11.279383, 0.9129576},
                  {0.11368857, 2.3792067, -11.218701},
                });
        b64 = tf.constant(new double[][] {{-4.933953}, {-12.692161}, {-10.192119}});
        ans64 = fops.linalg.matmul(a64, b64);
        expected64 =
            tf.constant(
                new double[][] {{154.01892}, {231.81863}, {166.91096}, {126.92895}, {83.58413}});
        session.setEpsilon(1e-4f);
        session.evaluate(expected64, ans64);
      }
  }
}
