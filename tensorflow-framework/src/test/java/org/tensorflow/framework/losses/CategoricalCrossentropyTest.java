package org.tensorflow.framework.losses;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

public class CategoricalCrossentropyTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class CategoricalCrossentropy. */
  @Test
  public void testAllCorrectUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        long[] trueArray = {
          1L, 0L, 0L,
          0L, 1L, 0L,
          0L, 0L, 1L
        };
        float[] predArray = {
          1.f, 0.f, 0.f,
          0.f, 1.f, 0.f,
          0.f, 0.f, 1.F
        };
        Operand<TInt64> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        CategoricalCrossentropy instance = new CategoricalCrossentropy(tf);
        Operand<TFloat32> loss = instance.call(yTrue, yPred);
        float expected = 0f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {
          10.f, 0.f, 0.f,
          0.f, 10.f, 0.f,
          0.f, 0.f, 10.F
        };
        yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new CategoricalCrossentropy(tf, true);
        loss = instance.call(yTrue, logits);
        testSession.setEpsilon(1e-3f);
        testSession.evaluate(0.0f, loss);
      }
  }

  /** Test of call method, of class CategoricalCrossentropy. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalCrossentropy instance = new CategoricalCrossentropy(tf);
        int[] trueArray = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        float[] predArray = {
          .9f, .05f, .05f,
          .5f, .89f, .6f,
          .05f, .01f, .94f
        };
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> loss = instance.call(yTrue, yPred);
        float expected = 0.32396814f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {
          8.f, 1.f, 1.f,
          0.f, 9.f, 1.f,
          2.f, 3.f, 5.F
        };
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new CategoricalCrossentropy(tf, true);
        loss = instance.call(yTrue, logits);
        expected = 0.0573755f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class CategoricalCrossentropy. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        int[] trueArray = {
          1, 0, 0,
          0, 1, 0,
          0, 0, 1
        };
        float[] predArray = {
          .9f, .05f, .05f,
          .5f, .89f, .6f,
          .05f, .01f, .94f
        };
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);

        CategoricalCrossentropy instance = new CategoricalCrossentropy(tf);
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        float expected = .7451267f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {
          8.f, 1.f, 1.f,
          0.f, 9.f, 1.f,
          2.f, 3.f, 5.F
        };
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new CategoricalCrossentropy(tf, true);
        loss = instance.call(yTrue, logits, sampleWeight);
        expected = 0.13196386f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testSsampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalCrossentropy instance = new CategoricalCrossentropy(tf);
        float[] sampeWeightArray = {1.2f, 3.4f, 5.6f};
        int[] trueArray = {
          1, 0, 0,
          0, 1, 0,
          0, 0, 1
        };
        float[] predArray = {
          .9f, .05f, .05f,
          .5f, .89f, .6f,
          .05f, .01f, .94f
        };
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampeWeightArray), tf.constant(Shape.of(3, 1)));
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        float expected = 1.0696f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {
          8.f, 1.f, 1.f,
          0.f, 9.f, 1.f,
          2.f, 3.f, 5.F
        };
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new CategoricalCrossentropy(tf, true);
        loss = instance.call(yTrue, logits, sampleWeight);
        expected = 0.31829f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testNoReduction() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        // Test with logits.
        int[] trueArray = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        float[] logitsArray = {
          8.f, 1.f, 1.f,
          0.f, 9.f, 1.f,
          2.f, 3.f, 5.F
        };
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        CategoricalCrossentropy instance =
            new CategoricalCrossentropy(tf, true, 0.0f, Reduction.NONE);
        Operand<TFloat32> loss = instance.call(yTrue, logits);
        Float[] expected = {0.001822f, 0.000459f, 0.169846f};
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testLabelSmoothing() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float labelSmoothing = 0.1f;
        int[] trueArray = {1, 0, 0};
        float[] logitsArray = {100.0f, -100.0f, -100.0f};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(1, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(1, 3)));

        CategoricalCrossentropy instance = new CategoricalCrossentropy(tf, true, labelSmoothing);
        Operand<TFloat32> loss = instance.call(yTrue, logits);
        float expected = 400.0f * labelSmoothing / 3.0f;
        testSession.evaluate(expected, loss);
      }
  }
}
