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

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccuracyTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testCorrect() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Accuracy<TFloat32> instance = new Accuracy<>(tf, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      int[] trueArray = {1, 2, 3, 4};
      float[] predArray = {1, 2, 3, 4};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(4, 1)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(4, 1)));
      Op op = instance.updateState(labels, predictions, null);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result();
      session.evaluate(4F, total);
      session.evaluate(4, count);
      session.evaluate(1F, result);

      // test init(tf)
      instance = new Accuracy<>(1001L, TFloat32.class);
      instance.init(tf);
      session.run(instance.resetStates());

      op = instance.updateState(labels, predictions, null);
      session.run(op);
      total = instance.getTotal();
      count = instance.getCount();
      result = instance.result();
      session.evaluate(4F, total);
      session.evaluate(4, count);
      session.evaluate(1F, result);
    }
  }

  @Test
  public void testSampleWeight() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Accuracy<TFloat32> instance = new Accuracy<>(tf, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      float[] trueArray = {2, 1};
      float[] predArray = {2, 0};

      Operand<TFloat32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 1)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 1)));

      Operand<TFloat32> sampleWeight =
          tf.reshape(tf.constant(new float[] {.5F, .2F}), tf.constant(Shape.of(2, 1)));
      Op op = instance.updateState(labels, predictions, sampleWeight);
      session.run(op);

      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result();
      session.evaluate(.5F, total);
      session.evaluate(.7, count);
      session.evaluate(0.71428573f, result);

      // test init(tf)
      instance = new Accuracy<>(1001L, TFloat32.class);
      instance.init(tf);
      session.run(instance.resetStates());

      op = instance.updateState(labels, predictions, sampleWeight);
      session.run(op);

      total = instance.getTotal();
      count = instance.getCount();
      result = instance.result();
      session.evaluate(.5F, total);
      session.evaluate(.7, count);
      session.evaluate(0.71428573f, result);
    }
  }

  @Test
  public void testVariableState() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Accuracy<TFloat32> instance = new Accuracy<>(tf, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      float[] trueArray = {2, 1};

      Operand<TFloat32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 1)));

      Operand<TFloat32> sampleWeight =
          tf.reshape(tf.constant(new float[] {.5F, .2F}), tf.constant(Shape.of(2, 1)));
      Op op = instance.updateState(labels, labels, sampleWeight);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result();
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);

      // 2nd run
      op = instance.updateState(labels, labels, sampleWeight);
      session.run(op);
      result = instance.result();
      session.evaluate(1.4F, total);
      session.evaluate(1.4, count);
      session.evaluate(1.0F, result);

      // new instance same graph
      instance = new Accuracy<>(tf, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      op = instance.updateState(labels, labels, sampleWeight);
      session.run(op);
      total = instance.getTotal();
      count = instance.getCount();
      result = instance.result();
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);

      // reset variables
      session.run(instance.resetStates());
      result = instance.result();
      op = instance.updateState(labels, labels, sampleWeight);
      session.run(op);
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);

      // test init(tf)
      instance = new Accuracy<>(1001L, TFloat32.class);
      instance.init(tf);
      session.run(instance.resetStates());

      op = instance.updateState(labels, labels, sampleWeight);
      session.run(op);
      total = instance.getTotal();
      count = instance.getCount();
      result = instance.result();
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);

      // 2nd run
      op = instance.updateState(labels, labels, sampleWeight);
      session.run(op);
      result = instance.result();
      session.evaluate(1.4F, total);
      session.evaluate(1.4, count);
      session.evaluate(1.0F, result);

      // new instance same graph
      instance = new Accuracy<>(tf, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      op = instance.updateState(labels, labels, sampleWeight);
      session.run(op);
      total = instance.getTotal();
      count = instance.getCount();
      result = instance.result();
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);

      // reset variables
      session.run(instance.resetStates());
      result = instance.result();
      op = instance.updateState(labels, labels, sampleWeight);
      session.run(op);
      session.evaluate(0.7F, total);
      session.evaluate(.7, count);
      session.evaluate(1.0F, result);
    }
  }

  @Test
  public void testIllegalState() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          try (TestSession session = TestSession.createTestSession(tfMode)) {
            Accuracy<TFloat32> instance = new Accuracy<>(1001L, TFloat32.class);
            session.run(instance.resetStates());
          }
        });
  }
}
