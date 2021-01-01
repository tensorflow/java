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
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

class BinaryCrossentropyTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryCrossentropy<TFloat32, TFloat64> instance =
          new BinaryCrossentropy<>(tf, "BCE_testUnweighted", false, 0, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      float[] trueArray = {1, 0, 1, 0};
      float[] predictionArray = {1, 1, 1, 0};
      Operand<TFloat32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 2)));
      Operand<TFloat32> yPrediction =
          tf.reshape(tf.constant(predictionArray), tf.constant(Shape.of(2, 2)));
      Op op = instance.updateState(labels, yPrediction, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result();
      session.evaluate(7.666619F, total);
      session.evaluate(2, count);
      session.evaluate(3.833309F, result);
    }
  }

  @Test
  public void testUnweightedLogits() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryCrossentropy<TFloat64, TFloat64> instance =
          new BinaryCrossentropy<>(tf, "BCE_testUnweightedLogits", true, 0, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      float[] trueArray = {1, 0, 1, 0, 1, 1};
      double[] logitsArray = {100.0, -100.0, 100.0, 100.0, 100.0, -100.0};
      Operand<TFloat32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> logits = tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(labels, logits, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result();
      session.evaluate(66.66667, total);
      session.evaluate(2, count);
      session.evaluate(33.333332, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryCrossentropy<TFloat32, TFloat32> instance =
          new BinaryCrossentropy<>(tf, "BCE_testWeighted", false, 0, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      int[] trueArray = {1, 0, 1, 0};
      float[] predictionArray = {1, 1, 1, 0};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 2)));
      Operand<TFloat32> yPrediction =
          tf.reshape(tf.constant(predictionArray), tf.constant(Shape.of(2, 2)));
      Operand<TFloat32> sampleWeight = tf.constant(new float[] {1.5f, 2.f});
      Op op = instance.updateState(labels, yPrediction, sampleWeight);
      session.run(op);

      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result();
      session.evaluate(11.499929f, total);
      session.evaluate(3.5f, count);
      session.evaluate(3.285694f, result);
    }
  }

  @Test
  public void testWeightedLogits() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      BinaryCrossentropy<TFloat64, TFloat64> instance =
          new BinaryCrossentropy<>(tf, "BCE_testWeightedLogits", true, 0, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      float[] trueArray = {1, 0, 1, 0, 1, 1};
      double[] logitsArray = {100.0, -100.0, 100.0, 100.0, 100.0, -100.0};
      Operand<TFloat32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> logits = tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat64> sampleWeight = tf.constant(new double[] {2, 2.5});

      Op op = instance.updateState(labels, logits, sampleWeight);
      session.run(op);

      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result();
      session.evaluate(166.66666, total);
      session.evaluate(4.5, count);
      session.evaluate(37.037033, result);
    }
  }

  @Test
  public void testLabelSmoothing() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float labelSmoothing = 0.1F;
      BinaryCrossentropy<TFloat64, TFloat64> instance =
          new BinaryCrossentropy<>(
              tf, "BCE_testWeightedLabS", true, labelSmoothing, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      float[] trueArray = {1, 0, 1};
      double[] logitsArray = {100., -100., -100.};
      Operand<TFloat32> labels = tf.constant(trueArray);
      Operand<TFloat64> logits = tf.constant(logitsArray);

      Op op = instance.updateState(labels, logits, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result();

      session.evaluate(35, total);
      session.evaluate(1, count);
      session.evaluate(35, result);
    }
  }
}
