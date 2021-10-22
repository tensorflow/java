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
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

class SparseCategoricalCrossentropyTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SparseCategoricalCrossentropy<TFloat64> instance =
          new SparseCategoricalCrossentropy<>(
              "SCE_testUnweighted", false, -1, 1001L, TFloat64.class);
      int[] trueArray = {1, 2};
      double[] predictionArray = {0.05, 0.95, 0, 0.1, 0.8, 0.1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predictionArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(2.3538785, total);
      session.evaluate(2, count);
      session.evaluate(1.1769392, result);
    }
  }

  @Test
  public void testUnweightedLogits() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SparseCategoricalCrossentropy<TFloat64> instance =
          new SparseCategoricalCrossentropy<>("SCE_testWeighted", true, -1, 1001L, TFloat64.class);
      int[] trueArray = {1, 2};
      double[] logitsArray = {1, 9, 0, 1, 8, 1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2)));
      Operand<TFloat64> logits = tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, logits, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(7.002277, total);
      session.evaluate(2, count);
      session.evaluate(3.501135, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SparseCategoricalCrossentropy<TFloat32> instance =
          new SparseCategoricalCrossentropy<>("SCE_testWeighted", false, -1, 1001L, TFloat32.class);
      int[] trueArray = {1, 2};
      double[] predictionArray = {0.05, 0.95, 0, 0.1, 0.8, 0.1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predictionArray), tf.constant(Shape.of(2, 3)));

      Operand<TFloat32> sampleWeight = tf.constant(new float[] {1.5F, 2.F});
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(4.6821103f, total);
      session.evaluate(3.5f, count);
      session.evaluate(1.3377458f, result);
    }
  }

  @Test
  public void testWeightedLogits() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SparseCategoricalCrossentropy<TFloat64> instance =
          new SparseCategoricalCrossentropy<>("SCE_testWeighted", true, -1, 1001L, TFloat64.class);
      int[] trueArray = {1, 2};
      double[] predictionArray = {1, 9, 0, 1, 8, 1};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predictionArray), tf.constant(Shape.of(2, 3)));

      Operand<TFloat64> sampleWeight = tf.constant(new double[] {1.5, 2});
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(14.004333, total);
      session.evaluate(3.5, count);
      session.evaluate(4.001232, result);
    }
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      SparseCategoricalCrossentropy<TFloat64> instance =
          new SparseCategoricalCrossentropy<>("SCE", true, -1, 1001L, TFloat64.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
