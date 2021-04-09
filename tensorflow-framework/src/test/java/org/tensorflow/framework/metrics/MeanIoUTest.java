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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

public class MeanIoUTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;
  private final long numClasses = 2L;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF().withSubScope("testUnweighted");
      Operand<TInt64> predictions = tf.constant(new long[] {0, 1, 0, 1});
      Operand<TInt64> labels = tf.constant(new long[] {0, 0, 1, 1});
      MeanIoU<TFloat64> instance = new MeanIoU<>(tf, numClasses, 1001L, TFloat64.class);
      session.run(instance.getInitializer());
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat64> result = instance.result();
      double expected_result = (1. / (2. + 2. - 1.) + 1. / (2. + 2. - 1.)) / 2.;
      session.evaluate(expected_result, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF().withSubScope("testWeighted");
      Operand<TInt64> predictions = tf.constant(new long[] {0, 1, 0, 1});
      Operand<TInt64> labels = tf.constant(new long[] {0, 0, 1, 1});
      Operand<TFloat32> sampleWeight = tf.constant(new float[] {0.2f, 0.3f, 0.4f, 0.1f});
      MeanIoU<TFloat32> instance = new MeanIoU<>(tf, numClasses, 1001L, TFloat32.class);
      session.run(instance.getInitializer());
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      float expected_result = (0.2f / (0.6f + 0.5f - 0.2f) + 0.1f / (0.4f + 0.5f - 0.1f)) / 2f;
      session.evaluate(expected_result, result);
    }
  }

  @Test
  public void testMultiDimInput() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF().withSubScope("testMultiDimInput");

      Operand<TInt64> predictions = tf.constant(new long[][] {{0, 1}, {0, 1}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 0}, {1, 1}});
      Operand<TFloat32> sampleWeight = tf.constant(new float[][] {{0.2f, 0.3f}, {0.4f, 0.1f}});
      MeanIoU<TFloat32> instance = new MeanIoU<>(tf, numClasses, 1001L, TFloat32.class);
      session.run(instance.getInitializer());
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      float expected_result = (0.2f / (0.6f + 0.5f - 0.2f) + 0.1f / (0.4f + 0.5f - 0.1f)) / 2f;
      session.evaluate(expected_result, result);
    }
  }

  @Test
  public void testZeroValidEntries() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF().withSubScope("testZeroValidEntries");
      MeanIoU<TFloat32> instance = new MeanIoU<>(tf, numClasses, 1001L, TFloat32.class);
      session.run(instance.getInitializer());
      Operand<TFloat32> result = instance.result();
      session.evaluate(0.0f, result);
    }
  }

  @Test
  public void testZeroAndNonZeroEntries() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF().withSubScope("testZeroAndNonZeroEntries");
      Operand<TFloat32> predictions = tf.constant(new float[] {1});
      Operand<TInt32> labels = tf.constant(new int[] {1});

      MeanIoU<TFloat32> instance = new MeanIoU<>(tf, numClasses, 1001L, TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      float expected_result = (0f + 1f / (1f + 1f - 1f)) / 1f;
      session.evaluate(expected_result, result);
    }
  }
}
