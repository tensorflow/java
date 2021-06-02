/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.losses;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class BinaryCrossentropyTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class BinaryCrossentropy. */
  @Test
  public void testAllCorrectUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        BinaryCrossentropy instance = new BinaryCrossentropy();

        float[] trueArray = {1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));

        Operand<TFloat32> loss = instance.call(tf, yTrue, yTrue);

        float expected = 0.0f;
        testSession.evaluate(expected, loss);
        // Test with logits.
        float[] logitsArray = {
          100.0f, -100.0f, -100.0f, -100.0f, 100.0f, -100.0f, -100.0f, -100.0f, 100.0f
        };
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new BinaryCrossentropy(true);

        loss = instance.call(tf, yTrue, logits);
        testSession.evaluate(expected, loss);
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
              BinaryCrossentropy instance = new BinaryCrossentropy();

              float[] trueArray = {1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
              float[] predArray = {2f, 1f, -1f, 0f};
              Operand<TFloat32> yTrue =
                  tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
              Operand<TFloat32> yPred =
                  tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 2)));

              Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
              testSession.run(loss);
            });
      }
  }

  /** Test of call method, of class BinaryCrossentropy. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        BinaryCrossentropy instance = new BinaryCrossentropy();

        float[] trueArray = {1f, 0f, 1f, 0f};
        float[] predArray = {1f, 1f, 1f, 0f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = 3.83331f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] trueArray1 = {1f, 0f, 1f, 0f, 1f, 1f};
        float[] logitsArray = {100.0f, -100.0f, 100.0f, 100.0f, 100.0f, -100.0f};
        Operand<TFloat32> yTrue1 = tf.reshape(tf.constant(trueArray1), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(2, 3)));
        instance = new BinaryCrossentropy(true);

        loss = instance.call(tf, yTrue1, logits);
        expected = 33.33333f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class BinaryCrossentropy. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        BinaryCrossentropy instance = new BinaryCrossentropy();

        float[] trueArray = {1f, 0f, 1f, 0f};
        float[] predArray = {1f, 1f, 1f, 0f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 8.816612f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] trueArray1 = {1f, 0f, 1f, 0f, 1f, 1f};
        float[] logitsArray = {100.0f, -100.0f, 100.0f, 100.0f, 100.0f, -100.0f};
        Operand<TFloat32> yTrue1 = tf.reshape(tf.constant(trueArray1), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(2, 3)));
        instance = new BinaryCrossentropy(true);

        loss = instance.call(tf, yTrue1, logits, sampleWeight);
        expected = 76.66667f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testSampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        BinaryCrossentropy instance = new BinaryCrossentropy();

        float[] trueArray = {1f, 0f, 1f, 0f};
        float[] predArray = {1f, 1f, 1f, 0f};
        float[] sampleWeightArray = {1.2f, 3.4f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleWeightArray), tf.constant(Shape.of(2, 1)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 4.59997f;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] trueArray1 = {1f, 0f, 1f, 0f, 1f, 1f};
        float[] logitsArray = {100.0f, -100.0f, 100.0f, 100.0f, 100.0f, -100.0f};
        float[] sampleWeightArray1 = {4f, 3f};
        Operand<TFloat32> yTrue1 = tf.reshape(tf.constant(trueArray1), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight1 = tf.constant(sampleWeightArray1);
        instance = new BinaryCrossentropy(true);

        loss = instance.call(tf, yTrue1, logits, sampleWeight1);
        expected = 100f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testNoReduction() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        // Test with logits.
        float[] trueArray = {1f, 0f, 1f, 0f, 1f, 1f};
        float[] logitsArray = {100.0f, -100.0f, 100.0f, 100.0f, 100.0f, -100.0f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(2, 3)));
        BinaryCrossentropy instance =
            new BinaryCrossentropy(
                true, BinaryCrossentropy.LABEL_SMOOTHING_DEFAULT, Reduction.NONE);

        Operand<TFloat32> loss = instance.call(tf, yTrue, logits);
        Float[] expected = {0.f, 66.666664f};
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testLabelSmoothing() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float labelSmoothing = 0.1f;
        float[] trueArray = {1f, 0f, 1f};
        float[] logitsArray = {100.0f, -100.0f, -100.0f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(1, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(1, 3)));

        BinaryCrossentropy instance = new BinaryCrossentropy(true, labelSmoothing);

        Operand<TFloat32> loss = instance.call(tf, yTrue, logits);
        float expected = (100.0f + 50.0f * labelSmoothing) / 3.0f;
        testSession.evaluate(expected, loss);
      } catch (Exception expected) {

      }
  }
}
