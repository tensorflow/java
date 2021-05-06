package org.tensorflow.framework.op;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
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

  @Test
  public void testSoftmax() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        FrameworkOps fops = FrameworkOps.create(tf);

        double[][] x = {
          {
            1.53975978e-01,
            -5.55871308e-01,
            1.06272554e+00,
            -7.75577792e-05,
            -1.07574403e+00,
            -1.70856595e+00,
            6.31895363e-01,
            2.69239008e-01,
            5.44192731e-01,
            6.31500483e-01
          },
          {
            -1.15359895e-01,
            -2.49849468e-01,
            8.04671764e-01,
            6.24943256e-01,
            -4.80956525e-01,
            5.99363089e-01,
            7.44674265e-01,
            1.03888428e+00,
            -2.00478077e-01,
            5.33391297e-01
          },
          {
            3.77073050e-01,
            -4.92661327e-01,
            -8.23421478e-01,
            -6.53828621e-01,
            2.00867987e+00,
            2.94002771e-01,
            1.70056212e+00,
            5.50198834e-04,
            6.12767756e-01,
            -2.29190066e-01
          },
          {
            -1.60519981e+00,
            -3.48692238e-01,
            -3.25094163e-03,
            4.39969897e-01,
            1.50762582e+00,
            9.69331264e-01,
            -1.18115306e+00,
            1.34852254e+00,
            -1.24402285e+00,
            -3.12961072e-01
          },
          {
            -1.40357280e+00,
            -1.08287978e+00,
            -3.79449308e-01,
            1.51061141e+00,
            7.71783948e-01,
            5.29040515e-01,
            8.77655566e-01,
            -1.53738844e+00,
            9.32778895e-01,
            3.69026303e-01
          }
        };

        double[][] expected = {
          {
            0.09007322,
            0.04429074,
            0.2234913,
            0.07721311,
            0.0263351,
            0.01398634,
            0.14526248,
            0.10107733,
            0.13306525,
            0.14520513
          },
          {
            0.05687012,
            0.04971369,
            0.14270815,
            0.11923224,
            0.0394555,
            0.11622094,
            0.13439782,
            0.1803707,
            0.05222973,
            0.10880107
          },
          {
            0.06962293,
            0.02917639,
            0.02095966,
            0.02483347,
            0.35591814,
            0.06407304,
            0.26153892,
            0.04777828,
            0.08812784,
            0.03797131
          },
          {
            0.01272309,
            0.0446979,
            0.06314083,
            0.0983555,
            0.28607225,
            0.16699266,
            0.01944258,
            0.24399339,
            0.01825786,
            0.04632387
          },
          {
            0.0151052,
            0.02081621,
            0.04206274,
            0.2784457,
            0.13300617,
            0.10433973,
            0.1478602,
            0.01321329,
            0.15623957,
            0.08891118
          }
        };

        Operand<TFloat64> input = tf.constant(x);
        Operand<TFloat64> expectedResult = tf.constant(expected);
        Operand<TFloat64> result = fops.nn.softmax(input, 1);
        session.evaluate(expectedResult, result);
      }
  }

  @Test
  public void testSoftmaxAxes() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        FrameworkOps fops = FrameworkOps.create(tf);

        double[][] arr = {
          {0., 0.09090909, 0.18181818, 0.27272727},
          {0.36363636, 0.45454545, 0.54545455, 0.63636364},
          {0.72727273, 0.81818182, 0.90909091, 1.}
        };

        Operand<TFloat64> arrTF = tf.constant(arr);

        Operand<TFloat64> xNegAxisResult = fops.nn.softmax(arrTF, -2);
        Operand<TFloat64> yPosAxisResult = fops.nn.softmax(arrTF, 0);
        Operand<TFloat64> zGtAxisResult = fops.nn.softmax(arrTF, 0);
        session.evaluate(xNegAxisResult, yPosAxisResult);
        session.evaluate(yPosAxisResult, zGtAxisResult);
      }
  }
}
