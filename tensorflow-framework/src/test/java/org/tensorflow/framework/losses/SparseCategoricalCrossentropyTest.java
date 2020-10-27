package org.tensorflow.framework.losses;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SparseCategoricalCrossentropyTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class SparseCategoricalCrossentropy. */
  @Test
  public void testAllCorrectUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        testSession.setEpsilon(1e-3f);
        Ops tf = testSession.getTF();

        int[] trueArray = {0, 1, 2};
        float[] predArray = {
          1.F, 0.F, 0.F,
          0.F, 1.F, 0.F,
          0.F, 0.F, 1.F
        };
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 1)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        SparseCategoricalCrossentropy instance = new SparseCategoricalCrossentropy(tf);
        Operand<TFloat32> loss = instance.call(yTrue, yPred);
        float expected = 0.0f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {
          10.F, 0.F, 0.F,
          0.F, 10.F, 0.F,
          0.F, 0.F, 10.F
        };
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new SparseCategoricalCrossentropy(tf, true);
        loss = instance.call(yTrue, logits);
        testSession.evaluate(0.0f, loss);
      }
  }

  @Test
  public void testInvalidPredictionsRange() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Class<? extends Throwable> catchClass =
                tfMode == TestSession.Mode.EAGER
                        ? IllegalArgumentException.class
                        : org.tensorflow.exceptions.TFInvalidArgumentException.class;
        assertThrows(
                catchClass,
                () -> {
                  Ops tf = testSession.getTF();
                  SparseCategoricalCrossentropy instance = new SparseCategoricalCrossentropy(tf);
                  int[] trueArray = {0, 1, 2};
                  float[] predArray = {
                          1.9f, .05f, .05f,
                          .5f, .89f, .6f,
                          .05f, .01f, .94f
                  };
                  Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 1)));
                  Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
                  Operand<TFloat32> loss = instance.call(yTrue, yPred);
                  testSession.run(loss);
                });
      }
  }

  /** Test of call method, of class SparseCategoricalCrossentropy. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        SparseCategoricalCrossentropy instance = new SparseCategoricalCrossentropy(tf);
        int[] trueArray = {0, 1, 2};
        float[] predArray = {
          .9f, .05f, .05f,
          .5f, .89f, .6f,
          .05f, .01f, .94f
        };
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 1)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> loss = instance.call(yTrue, yPred);
        float expected = 0.32396814f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {
          8.F, 1.F, 1.F,
          0.F, 9.F, 1.F,
          2.F, 3.F, 5.F
        };
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new SparseCategoricalCrossentropy(tf, true);
        loss = instance.call(yTrue, logits);
        expected = 0.05737559f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class SparseCategoricalCrossentropy. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        int[] trueArray = {0, 1, 2};
        float[] predArray = {
          .9f, .05f, .05f,
          .5f, .89f, .6f,
          .05f, .01f, .94f
        };
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 1)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);

        SparseCategoricalCrossentropy instance = new SparseCategoricalCrossentropy(tf);
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        float expected = .7451267f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {
          8.F, 1.F, 1.F,
          0.F, 9.F, 1.F,
          2.F, 3.F, 5.F
        };
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new SparseCategoricalCrossentropy(tf, true);
        loss = instance.call(yTrue, logits, sampleWeight);
        expected = 0.13196386f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testSampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        SparseCategoricalCrossentropy instance = new SparseCategoricalCrossentropy(tf);
        float[] sampleWeightArray = {1.2f, 3.4f, 5.6f};
        int[] trueArray = {0, 1, 2};
        float[] predArray = {
          .9f, .05f, .05f,
          .5f, .89f, .6f,
          .05f, .01f, .94f
        };
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 1)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleWeightArray), tf.constant(Shape.of(3, 1)));
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        float expected = 1.0696f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {
          8.F, 1.F, 1.F,
          0.F, 9.F, 1.F,
          2.F, 3.F, 5.F
        };
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new SparseCategoricalCrossentropy(tf, true);
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
        long[] trueArray = {0L, 1L, 2L};
        float[] logitsArray = {
          8.F, 1.F, 1.F,
          0.F, 9.F, 1.F,
          2.F, 3.F, 5.F
        };
        Operand<TInt64> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 1)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        SparseCategoricalCrossentropy instance =
            new SparseCategoricalCrossentropy(tf, true, Reduction.NONE);
        Operand<TFloat32> loss = instance.call(yTrue, logits);
        Float[] expected = {0.001822f, 0.000459f, 0.169846f};
        testSession.evaluate(expected, loss);
      }
  }
}
