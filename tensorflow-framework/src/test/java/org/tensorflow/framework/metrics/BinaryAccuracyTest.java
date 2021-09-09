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

public class BinaryAccuracyTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testCorrect() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryAccuracy<TFloat32> instance = new BinaryAccuracy<>(1001L, TFloat32.class);
      int[] trueArray = {1, 0};
      float[] predArray = {1, 0};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 1)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 1)));
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
  public void testPredictionSqueeze() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryAccuracy<TFloat32> instance = new BinaryAccuracy<>(1001L, TFloat32.class);
      int[] trueArray = {1, 0};
      float[] predArray = {1, 1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 1)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 1, 1)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(2F, total);
      session.evaluate(4, count);
      session.evaluate(0.5F, result);
    }
  }

  @Test
  public void testSampleWeight() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryAccuracy<TFloat32> instance = new BinaryAccuracy<>(1001L, TFloat32.class);

      int[] trueArray = {1, 1};
      float[] predArray = {1, 0};

      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 1)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 1)));

      Operand<TFloat32> sampleWeight =
          tf.reshape(tf.constant(new float[] {.5F, .2F}), tf.constant(Shape.of(2, 1)));
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(0.5F, total);
      session.evaluate(.7, count);
      session.evaluate(0.71428573f, result);
    }
  }

  @Test
  public void testVariableState() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryAccuracy<TFloat32> instance = new BinaryAccuracy<>(1001L, TFloat32.class);

      float[] trueArray = {2, 1};
      Operand<TFloat32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 1)));

      Operand<TFloat32> sampleWeight =
          tf.reshape(tf.constant(new float[] {.5F, .2F}), tf.constant(Shape.of(2, 1)));
      Op op = instance.updateState(tf, labels, labels, sampleWeight);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(0.2F, total);
      session.evaluate(.7, count);
      session.evaluate(0.2857143F, result);

      // 2nd run
      op = instance.updateState(tf, labels, labels, sampleWeight);
      session.run(op);
      result = instance.result(tf, TFloat32.class);
      session.evaluate(0.4F, total);
      session.evaluate(1.4, count);
      session.evaluate(0.2857143F, result);

      // new instance same graph
      instance = new BinaryAccuracy<>(1001L, TFloat32.class);

      op = instance.updateState(tf, labels, labels, sampleWeight);
      session.run(op);
      total = instance.getTotal();
      count = instance.getCount();
      result = instance.result(tf, TFloat32.class);
      session.evaluate(0.2F, total);
      session.evaluate(.7, count);
      session.evaluate(0.2857143F, result);

      // reset variables
      session.run(instance.resetStates(tf));
      session.evaluate(0.0, total);
      session.evaluate(0.0, count);

      op = instance.updateState(tf, labels, labels, sampleWeight);
      session.run(op);
      result = instance.result(tf, TFloat32.class);
      session.evaluate(0.2F, total);
      session.evaluate(.7, count);
      session.evaluate(0.2857143F, result);
    }
  }

  @Test
  public void testBinaryAccuracyAThreshold() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryAccuracy<TFloat32> instance = new BinaryAccuracy<>(0.7f, 1001L, TFloat32.class);

      int[] trueArray = {1, 1, 0, 0};
      float[] predArray = {0.9f, 0.6f, 0.4f, 0.8f};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(4, 1)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(4, 1)));

      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(2F, total);
      session.evaluate(4, count);
      session.evaluate(0.5F, result);
    }
  }

  /** Test that Eager mode throws IllegalArgument Exception */
  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      BinaryAccuracy<TFloat32> instance = new BinaryAccuracy<>(1001L, TFloat32.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
