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
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

class SparseTopKCategoricalAccuracyTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testCorrectness() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SparseTopKCategoricalAccuracy<TFloat64, TFloat64> instance =
          new SparseTopKCategoricalAccuracy<>(
              tf, "SparseTopK_testCorrectness", 5, 1001L, TFloat64.class);
      session.run(instance.resetStates());

      Operand<TFloat64> labels = tf.constant(new double[] {2, 1});
      Operand<TFloat64> predictions =
          tf.constant(new double[][] {{0.1, 0.9, 0.8}, {0.05, 0.95, 0}});

      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      session.evaluate(1., instance.result());

      // With `k` < 5.
      instance =
          new SparseTopKCategoricalAccuracy<>(
              tf, "SparseTopK_testCorrectness", 1, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      update = instance.updateState(labels, predictions, null);
      session.run(update);
      session.evaluate(0.5, instance.result());

      // With `k` > 5.
      predictions =
          tf.constant(
              new double[][] {
                {0.5, 0.9, 0.1, 0.7, 0.6, 0.5, 0.4},
                {0.05, 0.95, 0, 0, 0, 0, 0}
              });
      instance =
          new SparseTopKCategoricalAccuracy<>(
              tf, "SparseTopK_testCorrectness", 6, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      update = instance.updateState(labels, predictions, null);
      session.run(update);
      session.evaluate(0.5, instance.result());
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SparseTopKCategoricalAccuracy<TFloat64, TFloat64> instance =
          new SparseTopKCategoricalAccuracy<>(
              tf, "SparseTopK_testWeighted", 5, 1001L, TFloat64.class);
      session.run(instance.resetStates());

      Operand<TInt32> labels = tf.constant(new int[] {1, 0, 2});
      Operand<TFloat64> predictions =
          tf.constant(
              new double[][] {
                {0, 0.9, 0.1},
                {0, 0.9, 0.1},
                {0, 0.9, 0.1}
              });

      Operand<TFloat64> sampleWeight = tf.constant(new double[] {1, 0, 1});

      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      session.evaluate(1., instance.result());
    }
  }
}
