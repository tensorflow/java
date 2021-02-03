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
import org.tensorflow.op.random.RandomUniform;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

public class PrecisionTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testValueIsIdempotent() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Precision<TFloat64> instance =
          new Precision<>(tf, new float[] {0.3f, 0.72f}, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      Operand<TFloat32> predictions =
          tf.random.randomUniform(
              tf.constant(Shape.of(10, 3)), TFloat32.class, RandomUniform.seed(1001L));
      Operand<TFloat32> labels =
          tf.random.randomUniform(
              tf.constant(Shape.of(10, 3)), TFloat32.class, RandomUniform.seed(1001L));

      Op update = instance.updateState(labels, predictions, null);

      for (int i = 0; i < 10; i++) {
        session.run(update);
      }

      Operand<TFloat64> initialPrecision = instance.result();

      for (int i = 0; i < 10; i++) {
        session.evaluate(initialPrecision, instance.result());
      }
    }
  }

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Precision<TFloat64> instance = new Precision<>(tf, 1001L, TFloat64.class);
      session.run(instance.resetStates());

      Operand<TInt64> predictions = tf.constant(new long[][] {{1, 0, 1, 0}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1, 1, 0}});
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat64> precision = instance.result();
      session.evaluate(0.5, precision);
    }
  }

  @Test
  public void testUnweightedAllIncorrect() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Precision<TFloat32> instance = new Precision<>(tf, 0.5f, 1001L, TFloat32.class);
      session.run(instance.resetStates());

      Operand<TInt32> predictions =
          tf.random.randomUniformInt(tf.constant(Shape.of(100, 1)), tf.constant(0), tf.constant(2));
      Operand<TInt32> labels = tf.math.sub(tf.constant(1), predictions);
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> precision = instance.result();
      session.evaluate(0.0f, precision);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Precision<TFloat64> instance = new Precision<>(tf, 1001L, TFloat64.class);
      session.run(instance.resetStates());

      Operand<TInt64> predictions = tf.constant(new long[][] {{1, 0, 1, 0}, {1, 0, 1, 0}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1, 1, 0}, {1, 0, 0, 1}});
      Operand<TFloat64> sampleWeight = tf.constant(new double[][] {{1, 2, 3, 4}, {4, 3, 2, 1}});
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      Operand<TFloat64> precision = instance.result();

      double weightedTP = 3.0f + 4.0f;
      double weightedPositives = (1.0f + 3.0f) + (4.0f + 2.0f);
      double expectedPrecision = weightedTP / weightedPositives;

      session.evaluate(expectedPrecision, precision);
    }
  }

  @Test
  public void testDivByZero() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Precision<TFloat64> instance = new Precision<>(tf, 1001L, TFloat64.class);
      session.run(instance.resetStates());

      Operand<TInt32> predictions = tf.constant(new int[] {0, 0, 0, 0});
      Operand<TInt32> labels = tf.constant(new int[] {0, 0, 0, 0});
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat64> precision = instance.result();

      session.evaluate(0, precision);
    }
  }

  @Test
  public void testUnweightedWithThreshold() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Precision<TFloat32> instance =
          new Precision<>(tf, new float[] {0.5f, 0.7f}, 1001L, TFloat32.class);
      session.run(instance.resetStates());

      Operand<TFloat32> predictions = tf.constant(new float[][] {{1f, 0f, 0.6f, 0f}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1, 1, 0}});
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> precision = instance.result();

      float[] expected = new float[] {0.5f, 0.f};

      session.evaluate(expected, precision);
    }
  }

  @Test
  public void testWeightedWithThreshold() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Precision<TFloat32> instance =
          new Precision<>(tf, new float[] {0.5f, 1.f}, 1001L, TFloat32.class);
      session.run(instance.resetStates());

      Operand<TFloat32> predictions = tf.constant(new float[][] {{1f, 0f}, {0.6f, 0f}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1}, {1, 0}});
      Operand<TFloat32> sampleWeight = tf.constant(new float[][] {{4, 0}, {3, 1}});
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      Operand<TFloat32> precision = instance.result();

      float weightedTP = 0f + 3.f;
      float weightedPositives = (0f + 3.f) + (4.f + 0.f);
      float expectedPrecision = weightedTP / weightedPositives;

      Float[] expected = new Float[] {expectedPrecision, 0f};
      session.evaluate(expected, precision);
    }
  }

  @Test
  public void testMultipleUpdates() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Precision<TFloat64> instance =
          new Precision<>(tf, new float[] {0.5f, 1.f}, 1001L, TFloat64.class);
      session.run(instance.resetStates());

      Operand<TFloat32> predictions = tf.constant(new float[][] {{1f, 0f}, {0.6f, 0f}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1}, {1, 0}});
      Operand<TFloat64> sampleWeight = tf.constant(new double[][] {{4, 0}, {3, 1}});
      Op update = instance.updateState(labels, predictions, sampleWeight);
      for (int i = 0; i < 2; i++) session.run(update);
      Operand<TFloat64> precision = instance.result();

      double weighted_tp = (0 + 3.) + (0 + 3.);
      double weighted_positives = ((0 + 3.) + (4. + 0.)) + ((0 + 3.) + (4. + 0.));
      double expected_precision = weighted_tp / weighted_positives;

      double[] expected = new double[] {expected_precision, 0f};
      session.evaluate(expected, precision);
    }
  }

  @Test
  public void testUnweightedTopK() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      // set topK to 3
      Precision<TFloat32> instance =
          new Precision<>(tf, null, 3, null, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0.5f, 0f, 0.2f}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1, 1, 0, 0}});
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> precision = instance.result();
      session.evaluate(1.0f / 3.0f, precision);
    }
  }

  @Test
  public void testWeightedTopK() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      // set topK to 3
      Precision<TFloat32> instance =
          new Precision<>(tf, null, 3, null, 1001L, TFloat32.class);
      session.run(instance.resetStates());

      Operand<TFloat32> predictions = tf.constant(new float[] {0.2f, 0.1f, 0.4f, 0f, 0.2f});
      Operand<TInt64> labels = tf.constant(new long[] {0, 1, 1, 0, 1});
      Operand<TFloat32> sampleWeight = tf.constant(new float[][] {{1, 4, 2, 3, 5}});
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);

      predictions = tf.constant(new float[][] {{0.2f, 0.6f, 0.4f, 0.2f, 0.2f}});
      labels = tf.constant(new long[][] {{1, 0, 1, 1, 1}});
      update = instance.updateState(labels, predictions, tf.constant(3.f));
      session.run(update);

      Operand<TFloat32> precision = instance.result();

      float tp = (2f + 5f) + (3f + 3f);
      float predicted_positives = (1f + 2f + 5f) + (3f + 3f + 3f);
      float expected_precision = tp / predicted_positives;
      session.evaluate(expected_precision, precision);
    }
  }

  @Test
  public void testUnweightedClassId() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      // set classId to 2
      Precision<TFloat32> instance =
          new Precision<>(tf, null, null, 2, 1001L, TFloat32.class);
      session.run(instance.resetStates());

      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0.6f, 0f, 0.2f}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1, 1, 0, 0}});
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> precision = instance.result();

      session.evaluate(1, precision);
      session.evaluate(1, instance.getTruePositives());
      session.evaluate(0, instance.getFalsePositives());

      predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0f, 0f, 0.2f}});
      labels = tf.constant(new long[][] {{0, 1, 1, 0, 0}});
      update = instance.updateState(labels, predictions, null);
      session.run(update);
      precision = instance.result();

      session.evaluate(1, precision);
      session.evaluate(1, instance.getTruePositives());
      session.evaluate(0, instance.getFalsePositives());

      predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0.6f, 0f, 0.2f}});
      labels = tf.constant(new long[][] {{0, 1, 0, 0, 0}});
      update = instance.updateState(labels, predictions, null);
      session.run(update);
      precision = instance.result();

      session.evaluate(0.5f, precision);
      session.evaluate(1, instance.getTruePositives());
      session.evaluate(1, instance.getFalsePositives());
    }
  }

  @Test
  public void testUnweightedTopKAndClassId() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      // set topK and classId to 2
      Precision<TFloat32> instance =
          new Precision<>(tf, null, 2, 2, 1001L, TFloat32.class);
      session.run(instance.resetStates());

      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.6f, 0.3f, 0f, 0.2f}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1, 1, 0, 0}});
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> precision = instance.result();

      session.evaluate(1, precision);
      session.evaluate(1, instance.getTruePositives());
      session.evaluate(0, instance.getFalsePositives());

      predictions = tf.constant(new float[][] {{1f, 1f, 0.9f, 1f, 1f}});
      labels = tf.constant(new long[][] {{0, 1, 1, 0, 0}});
      update = instance.updateState(labels, predictions, null);
      session.run(update);
      precision = instance.result();

      session.evaluate(1, precision);
      session.evaluate(1, instance.getTruePositives());
      session.evaluate(0, instance.getFalsePositives());
    }
  }

  @Test
  public void testUnweightedTopKAndThreshold() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      // set topK to 2
      Precision<TFloat32> instance =
          new Precision<>(tf, 0.7f, 2, null, 1001L, TFloat32.class);
      session.run(instance.resetStates());

      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.8f, 0.6f, 0f, 0.2f}});
      Operand<TInt64> labels = tf.constant(new long[][] {{0, 1, 1, 0, 1}});
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> precision = instance.result();

      session.evaluate(1, precision);
      session.evaluate(1, instance.getTruePositives());
      session.evaluate(0, instance.getFalsePositives());
    }
  }
}
