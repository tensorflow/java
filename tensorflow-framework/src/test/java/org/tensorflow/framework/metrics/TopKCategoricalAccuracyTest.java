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
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class TopKCategoricalAccuracyTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testCorrectness() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      TopKCategoricalAccuracy<TFloat64> instance =
          new TopKCategoricalAccuracy<>("TopK_testUnweighted", 5, 1001L, TFloat64.class);
      Operand<TFloat32> labels = tf.constant(new float[][] {{0, 0, 1}, {0, 1, 0}});
      Operand<TFloat64> predictions =
          tf.constant(new double[][] {{0.1f, 0.9f, 0.8f}, {0.05f, 0.95f, 0f}});

      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);
      session.evaluate(1., instance.result(tf, TFloat64.class));

      // With `k` < 5.
      instance = new TopKCategoricalAccuracy<>("TopK_testUnweighted1", 1, 1001L, TFloat64.class);
      update = instance.updateState(tf, labels, predictions, null);
      session.run(update);
      session.evaluate(0.5, instance.result(tf, TFloat64.class));

      // With `k` > 5.
      labels =
          tf.constant(
              new float[][] {
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0}
              });
      predictions =
          tf.constant(
              new double[][] {
                {0.5f, 0.9f, 0.1f, 0.7f, 0.6f, 0.5f, 0.4f},
                {0.05f, 0.95f, 0f, 0f, 0f, 0f, 0f}
              });
      instance = new TopKCategoricalAccuracy<>("TopK_testUnweighted6", 6, 1001L, TFloat64.class);
      update = instance.updateState(tf, labels, predictions, null);
      session.run(update);
      session.evaluate(0.5, instance.result(tf, TFloat64.class));
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      TopKCategoricalAccuracy<TFloat64> instance =
          new TopKCategoricalAccuracy<>("TopK_testWeighted", 5, 1001L, TFloat64.class);

      Operand<TFloat64> labels =
          tf.constant(
              new double[][] {
                {1, 0, 2},
                {1, 0, 0},
                {0, 0, 1}
              });
      Operand<TFloat64> predictions =
          tf.constant(
              new double[][] {
                {0f, 0.9f, 0.1f},
                {0f, 0.9f, 0.1f},
                {0f, 0.9f, 0.1f}
              });

      Operand<TFloat64> sampleWeight = tf.constant(new double[] {1, 0, 1});

      Op update = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(update);
      session.evaluate(1., instance.result(tf, TFloat64.class));
    }
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      TopKCategoricalAccuracy<TFloat64> instance =
          new TopKCategoricalAccuracy<>("TopK", 5, 1001L, TFloat64.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
