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

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class CosineSimilarityTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class CosineSimilarity. */
  @Test
  public void testReductionNone() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        CosineSimilarity instance = new CosineSimilarity(Reduction.NONE);

        Shape shape = Shape.of(2, 3);
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(shape));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(shape));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        Float[] expected = {-0.720488f, 0.3460499f};
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class CosineSimilarity. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] expectedLoss = {0.720488f, -0.3460499f};
        CosineSimilarity instance = new CosineSimilarity();

        Shape shape = Shape.of(2, 3);
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(shape));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(shape));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = -mean(expectedLoss);
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class CosineSimilarity. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] expectedLoss = {0.720488f, -0.3460499f};
        CosineSimilarity instance = new CosineSimilarity();

        Shape shape = Shape.of(2, 3);
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(shape));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(shape));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = -mean(mul(expectedLoss, 2.3f));
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testSampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] expectedLoss = {0.720488f, -0.3460499f};
        CosineSimilarity instance = new CosineSimilarity();

        float[] weightsArray = {1.2f, 3.4f};
        Shape shape = Shape.of(2, 3);
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(shape));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(shape));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(weightsArray), tf.constant(Shape.of(2, 1)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = -mean(mul(expectedLoss, weightsArray));
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testZeroWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CosineSimilarity instance = new CosineSimilarity();

        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Shape shape = Shape.of(2, 3);
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(shape));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(shape));
        Operand<TFloat32> sampleWeight = tf.constant(0f);
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 0f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testTimestepWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        CosineSimilarity instance = new CosineSimilarity();

        Shape shape = Shape.of(2, 3, 1);
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(shape));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(shape));
        float[] weightsArray = {3, 6, 5, 0, 4, 2};
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(weightsArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = -2.0f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testAxis() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] trueArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] expectedLoss = {0.720488f, -0.3460499f};
        CosineSimilarity instance = new CosineSimilarity(1);

        Shape shape = Shape.of(2, 3);
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(shape));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(shape));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = -mean(expectedLoss);
        testSession.evaluate(expected, loss);
      }
  }

  private float mean(float[] v) {
    float sum = 0;
    for (float value : v) {
      sum += value;
    }
    return sum / v.length;
  }

  private float[] mul(float[] v, float scalar) {
    float[] result = new float[v.length];
    for (int i = 0; i < v.length; i++) {
      result[i] = v[i] * scalar;
    }
    return result;
  }

  private float[] mul(float[] v, float[] b) {
    float[] result = new float[v.length];
    for (int i = 0; i < v.length; i++) {
      result[i] = v[i] * b[i];
    }
    return result;
  }
}
