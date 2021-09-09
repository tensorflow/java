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
package org.tensorflow.framework.metrics;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

public class CategoricalAccuracyTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testCorrect() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalAccuracy<TFloat32> instance = new CategoricalAccuracy<>(1001L, TFloat32.class);
      int[] trueArray = {
        0, 0, 1,
        0, 1, 0
      };
      float[] predArray = {
        0.1f, 0.1f, 0.8f,
        0.05f, 0.95f, 0f
      };
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(2F, total);
      session.evaluate(2, count);
      session.evaluate(1F, result);
    }
  }

  @Test
  public void testSampleWeight() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalAccuracy<TFloat32> instance = new CategoricalAccuracy<>(1001L, TFloat32.class);
      int[] trueArray = {
        0, 0, 1,
        0, 1, 0
      };
      float[] predArray = {
        0.1f, 0.1f, 0.8f,
        0.05f, 0.95f, 0f
      };
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));

      Operand<TFloat32> sampleWeight =
          tf.reshape(tf.constant(new float[] {.5F, .2F}), tf.constant(Shape.of(2, 1)));
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);
    }
  }

  @Test
  public void testVariableState() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalAccuracy<TFloat32> instance = new CategoricalAccuracy<>(1001L, TFloat32.class);
      int[] trueArray = {
        0, 0, 1,
        0, 1, 0
      };
      float[] predArray = {
        0.1f, 0.1f, 0.8f,
        0.05f, 0.95f, 0f
      };

      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));

      Operand<TFloat32> sampleWeight =
          tf.reshape(tf.constant(new float[] {.5F, .2F}), tf.constant(Shape.of(2, 1)));
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);

      // 2nd run
      op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      result = instance.result(tf, TFloat32.class);
      session.evaluate(1.4F, total);
      session.evaluate(1.4, count);
      session.evaluate(1.0F, result);

      // new instance same graph
      instance = new CategoricalAccuracy<>(1001L, TFloat32.class);
      op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      result = instance.result(tf, TFloat32.class);
      total = instance.getTotal();
      count = instance.getCount();
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);

      // reset variables
      session.run(instance.resetStates(tf));
      session.evaluate(0, total);
      session.evaluate(0, count);
      session.evaluate(0, result);

      op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      result = instance.result(tf, TFloat32.class);
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);
    }
  }

  /** Test that Eager mode throws IllegalArgument Exception */
  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      float labelSmoothing = 0.1F;
      CategoricalAccuracy<TFloat32> instance = new CategoricalAccuracy<>(1001L, TFloat32.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
