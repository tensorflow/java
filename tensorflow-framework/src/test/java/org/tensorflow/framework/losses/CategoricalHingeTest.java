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
import org.tensorflow.types.TInt32;

public class CategoricalHingeTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class CategoricalHinge. */
  @Test
  public void testReductionNone() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalHinge instance = new CategoricalHinge(Reduction.NONE);

        int[] trueArray = {1, 9, 2, -5};
        float[] predArray = {4f, 8f, 12f, 8f};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        Float[] expected = {0.0f, 65.0f};
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class CategoricalHinge. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalHinge instance = new CategoricalHinge();

        int[] trueArray = {1, 9, 2, -5};
        float[] predArray = {4f, 8f, 12f, 8f};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 2)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = 32.5f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class CategoricalHinge. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalHinge instance = new CategoricalHinge();

        int[] trueArray = {1, 9, 2, -5, -2, 6};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 83.95f;
        testSession.evaluate(expected, loss);

        Operand<TFloat32> loss2 = instance.call(tf, yTrue, yPred, sampleWeight);
        testSession.evaluate(loss, loss2);
      }
  }

  @Test
  public void testSampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalHinge instance = new CategoricalHinge();

        int[] trueArray = {1, 9, 2, -5, -2, 6};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] weightsNp = {1.2f, 3.4f};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(weightsNp), tf.constant(Shape.of(2, 1)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 124.1f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testZeroWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalHinge instance = new CategoricalHinge();

        int[] trueArray = {1, 9, 2, -5, -2, 6};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
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
        CategoricalHinge instance = new CategoricalHinge();

        int[] trueArray = {1, 9, 2, -5, -2, 6};
        float[] predArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] weightsNp = {3, 6, 5, 0, 4, 2};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3, 1)));
        Operand<TFloat32> yPred =
            tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3, 1)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(weightsNp), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 4.0f;
        testSession.evaluate(expected, loss);
      }
  }
}
