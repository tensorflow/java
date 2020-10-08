package org.tensorflow.framework.losses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class MeanAbsoluteErrorTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class MeanAbsoluteError. */
  @Test
  public void testAllCorrectUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        MeanAbsoluteError instance = new MeanAbsoluteError(tf);
        float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> loss = instance.call(yTrue, yTrue);
        float expected = 0.0f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class MeanAbsoluteError. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        MeanAbsoluteError instance = new MeanAbsoluteError(tf);
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> loss = instance.call(yTrue, yPred);
        float expected = 5.5f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class MeanAbsoluteError. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        MeanAbsoluteError instance = new MeanAbsoluteError(tf);
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        float expected = 12.65f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testSampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        MeanAbsoluteError instance = new MeanAbsoluteError(tf);
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] sampleArray = {1.2f, 3.4f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 1)));
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        float expected = 81.4f / 6f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testZeroWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        MeanAbsoluteError instance = new MeanAbsoluteError(tf);
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(0.F);
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        float expected = 0f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testTimestepWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        MeanAbsoluteError instance = new MeanAbsoluteError(tf, Reduction.AUTO);
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] sampleArray = {3f, 6f, 5f, 0f, 4f, 2f};
        Operand<TFloat32> yTrue =
            tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3, 1)));
        Operand<TFloat32> yPred =
            tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3, 1)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);

        float expected = 83f / 6f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testInvalidSampleWeight() {
    for (TestSession.Mode tfMode : tfModes)
      Assertions.assertThrows(
          IllegalArgumentException.class,
          () -> {
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
              Ops tf = testSession.getTF();
              MeanAbsoluteError instance = new MeanAbsoluteError(tf);
              float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
              float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
              float[] sampleArray = {3f, 6f, 5f, 0f};
              Operand<TFloat32> yTrue =
                  tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3, 1)));
              Operand<TFloat32> yPred =
                  tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3, 1)));
              Operand<TFloat32> sampleWeight =
                  tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 2)));
              Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
              float expected = 83f / 6f;
              testSession.evaluate(expected, loss);
            }
          });
  }

  @Test
  public void testNoReduction() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        MeanAbsoluteError instance = new MeanAbsoluteError(tf, Reduction.NONE);
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        Float[] expected = {10.733333f, 14.566667f};
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testSumReduction() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        MeanAbsoluteError instance = new MeanAbsoluteError(tf, Reduction.SUM);
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
        Float[] expected = {25.29999f};
        testSession.evaluate(expected, loss);
      }
  }
}
