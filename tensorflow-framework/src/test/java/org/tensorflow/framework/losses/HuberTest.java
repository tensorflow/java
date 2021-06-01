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

public class HuberTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void testAllCorrect() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] trueArray = {.9f, .2f, .2f, .8f, .4f, .6f};

        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Huber instance = new Huber();

        Operand<TFloat32> loss = instance.call(tf, yTrue, yTrue);
        float expected = 0.0f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class Huber. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        float[] trueArray = {.9f, .2f, .2f, .8f, .4f, .6f};
        float[] predArray = {1.f, 0.f, 1.f, 1.f, 0.f, 0.f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Huber instance = new Huber();

        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = 0.10416666666666669f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class Huber. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] trueArray = {.9f, .2f, .2f, .8f, .4f, .6f};
        float[] predArray = {1.f, 0.f, 1.f, 1.f, 0.f, 0.f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Huber instance = new Huber();

        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 0.23958333333333337f;
        testSession.evaluate(expected, loss);

        // todo Verify we get the same output when the same input is given
      }
  }

  @Test
  public void testSampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] sampleArray = {1.2f, 3.4f};
        float[] trueArray = {.9f, .2f, .2f, .8f, .4f, .6f};
        float[] predArray = {1.f, 0.f, 1.f, 1.f, 0.f, 0.f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Huber instance = new Huber();

        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 1)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 0.22766666666666668f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testZeroWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float[] trueArray = {.9f, .2f, .2f, .8f, .4f, .6f};
        float[] predArray = {1.f, 0.f, 1.f, 1.f, 0.f, 0.f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Huber instance = new Huber();

        Operand<TFloat32> sampleWeight = tf.constant(0.F);
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
        float[] sampleArray = {3f, 6f, 5f, 0f, 4f, 2f};
        float[] trueArray = {.9f, .2f, .2f, .8f, .4f, .6f};
        float[] predArray = {1.f, 0.f, 1.f, 1.f, 0.f, 0.f};
        Operand<TFloat32> yTrue =
            tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3, 1)));
        Operand<TFloat32> yPred =
            tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3, 1)));
        Huber instance = new Huber();

        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);

        float expected = .4025f;
        testSession.evaluate(expected, loss);
      }
  }
}
