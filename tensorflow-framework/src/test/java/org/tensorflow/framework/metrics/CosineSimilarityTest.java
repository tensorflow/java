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

class CosineSimilarityTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CosineSimilarity<TFloat32> instance =
          new CosineSimilarity<>("CS_testUnweighted", 1001L, TFloat32.class);
      int[] trueArray = {1, 9, 2, -5, -2, 6};
      float[] predArray = {4, 8, 12, 8, 1, 3};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(0.3744381F, total);
      session.evaluate(2, count);
      session.evaluate(0.18721905F, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CosineSimilarity<TFloat32> instance =
          new CosineSimilarity<>("CS_testWeighted", 1001L, TFloat32.class);
      int[] trueArray = {1, 9, 2, -5, -2, 6};
      float[] predArray = {4, 8, 12, 8, 1, 3};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));

      Operand<TFloat32> sampleWeight = tf.constant(new float[] {1.2f, 3.4f});
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(-0.3119840621948241F, total);
      session.evaluate(4.6, count);
      session.evaluate(-0.06782262221626612F, result);
    }
  }

  @Test
  public void test_axis() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      int axis = 1;
      CosineSimilarity<TFloat32> instance =
          new CosineSimilarity<>("CS_testWeighted", axis, 1001L, TFloat32.class);
      int[] trueArray = {1, 9, 2, -5, -2, 6};
      float[] predArray = {4, 8, 12, 8, 1, 3};
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(0.3744381F, total);
      session.evaluate(2, count);
      session.evaluate(0.18721905F, result);
    }
  }

  /** Test that Eager mode throws IllegalArgument Exception */
  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      CosineSimilarity<TFloat32> instance = new CosineSimilarity<>(null, 1, 1001L, TFloat32.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
