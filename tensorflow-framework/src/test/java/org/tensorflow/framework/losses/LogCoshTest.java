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

public class LogCoshTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class LogCosh. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        LogCosh instance = new LogCosh();

        float[] predArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = 4.829245330860459f;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class LogCosh. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        LogCosh instance = new LogCosh();

        float[] predArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3f);
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 11.107264260979056f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testSampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        LogCosh instance = new LogCosh();

        float[] predArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] sampleArray = {1.2f, 3.4f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 1)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 12.001114667519486f;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testZeroWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        LogCosh instance = new LogCosh();

        float[] predArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
        Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
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
        LogCosh instance = new LogCosh(Reduction.AUTO);

        float[] predArray = {1f, 9f, 2f, -5f, -2f, 6f};
        float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
        float[] sampleArray = {3f, 6f, 5f, 0f, 4f, 2f};
        Operand<TFloat32> yTrue =
            tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3, 1)));
        Operand<TFloat32> yPred =
            tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3, 1)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 3)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);

        float expected = 11.653484271934046f;
        testSession.evaluate(expected, loss);
      }
  }
}
