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
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

class CategoricalCrossentropyTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalCrossentropy<TFloat64> instance =
          new CategoricalCrossentropy<>("CCE_testUnweighted", false, 0, -1, 1001L, TFloat64.class);

      int[] trueArray = {0, 1, 0, 0, 0, 1};
      double[] predArray = {0.05, 0.95, 0, 0.1, 0.8, 0.1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf);
      session.evaluate(2.3538785, total);
      session.evaluate(2, count);
      session.evaluate(1.1769392, result);
    }
  }

  @Test
  public void testUnweightedLogits() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalCrossentropy<TFloat64> instance =
          new CategoricalCrossentropy<>(
              "CCE_testUnweightedLogits", true, 0, -1, 1001L, TFloat64.class);

      int[] trueArray = {0, 1, 0, 0, 0, 1};
      double[] predArray = {1, 9, 0, 1, 8, 1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf);
      session.evaluate(7.0022807, total);
      session.evaluate(2, count);
      session.evaluate(3.5011404, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalCrossentropy<TFloat64> instance =
          new CategoricalCrossentropy<>("CCE_testWeighted", false, 0, -1, 1001L, TFloat64.class);

      int[] trueArray = {0, 1, 0, 0, 0, 1};
      double[] predArray = {0.05f, 0.95f, 0f, 0.1f, 0.8f, 0.1f};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> sampleWeight = tf.constant(new double[] {1.5f, 2.});
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf);
      session.evaluate(4.6821095, total);
      session.evaluate(3.5, count);
      session.evaluate(1.3377455, result);
    }
  }

  @Test
  public void testWeightedLogits() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalCrossentropy<TFloat64> instance =
          new CategoricalCrossentropy<>("CCE_testWeighted", true, 0, -1, 1001L, TFloat64.class);

      int[] trueArray = {0, 1, 0, 0, 0, 1};
      double[] predArray = {1, 9, 0, 1, 8, 1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> sampleWeight = tf.constant(new double[] {1.5, 2.f});
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf);
      session.evaluate(14.004333, total);
      session.evaluate(3.5, count);
      session.evaluate(4.0012328, result);
    }
  }

  @Test
  public void testLabelSmoothing() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float labelSmoothing = 0.1F;
      CategoricalCrossentropy<TFloat64> instance =
          new CategoricalCrossentropy<>(
              "CCE_testWeighted", true, labelSmoothing, -1, 1001L, TFloat64.class);

      int[] trueArray = {0, 1, 0, 0, 0, 1};
      double[] predArray = {1, 9, 0, 1, 8, 1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf);
      session.evaluate(7.3356137, total);
      session.evaluate(2, count);
      session.evaluate(3.6678069, result);
    }
  }

  @Test
  public void testInitTF() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalCrossentropy<TFloat64> instance =
          new CategoricalCrossentropy<>(
              "CCE_testUnweightedLogits", true, 0, -1, 1001L, TFloat64.class);
      instance.init(tf);

      int[] trueArray = {0, 1, 0, 0, 0, 1};
      double[] predArray = {1, 9, 0, 1, 8, 1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf);
      session.evaluate(7.0022807, total);
      session.evaluate(2, count);
      session.evaluate(3.5011404, result);
    }
  }
}
