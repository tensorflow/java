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

public class HingeTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class Hinge. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        Hinge instance = new Hinge();

        float[] trueArray = {0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f};
        float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = 0.50625f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testInvalidLabelValue() {
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
              Hinge instance = new Hinge();

              float[] trueArray = {2f, 1f, 0f, 1f, 0f, 0f, 1f, 1f};
              float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
              Operand<TFloat32> yTrue =
                  tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
              Operand<TFloat32> yPred =
                  tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
              Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
              testSession.run(loss);
            });
      }
  }

  /** Test of call method, of class Hinge. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        Hinge instance = new Hinge();

        float[] trueArray = {0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f};
        float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 1.164375f;
        testSession.evaluate(expected, loss);

        // todo Verify we get the same output when the same input is given
      }
  }

  @Test
  public void testSampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        Hinge instance = new Hinge();

        float[] sampleArray = {1.2f, 3.4f};
        float[] trueArray = {0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f};
        float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 1)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 1.06125f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testZeroWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        Hinge instance = new Hinge();

        float[] trueArray = {0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f};
        float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
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
        Hinge instance = new Hinge(Reduction.AUTO);

        float[] sampleArray = {3f, 6f, 5f, 0f, 4f, 2f, 1f, 3f};
        float[] trueArray = {0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f};
        float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
        Operand<TFloat32> yTrue =
            tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4, 1)));
        Operand<TFloat32> yPred =
            tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4, 1)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 4)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);

        float expected = 2.0125f;
        testSession.evaluate(expected, loss);
      }
  }
}
