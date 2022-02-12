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

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class RecallTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;
  private final Random random = new Random();

  @Test
  public void testValueIsIdempotent() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(new float[] {0.3f, 0.72f}, 1001L, TFloat32.class);

      Operand<TFloat32> predictions =
          tf.random.statelessRandomUniform(
              tf.constant(Shape.of(10, 3)), tf.constant(new long[] {1L, 0L}), TFloat32.class);
      Operand<TFloat32> labels =
          tf.random.statelessRandomUniform(
              tf.constant(Shape.of(10, 3)), tf.constant(new long[] {1L, 0L}), TFloat32.class);
      Op update = instance.updateState(tf, labels, predictions, null);

      for (int i = 0; i < 10; i++) session.run(update);

      Operand<TFloat32> initialRecall = instance.result(tf, TFloat32.class);
      for (int i = 0; i < 10; i++)
        session.evaluate(initialRecall, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(1001L, TFloat32.class);

      Operand<TFloat32> predictions = tf.constant(new float[][] {{1, 0, 1, 0}});
      Operand<TFloat32> labels = tf.constant(new float[][] {{0, 1, 1, 0}});
      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      session.evaluate(0.5f, instance.result(tf, TFloat32.class));
    }
  }

  private int[][] generateRandomArray(int dim1, int dim2, int maxInt) {
    int[][] result = new int[dim1][dim2];
    for (int i = 0; i < dim1; i++) {
      for (int j = 0; j < dim2; j++) {
        result[i][j] = random.nextInt(maxInt);
      }
    }

    return result;
  }

  @Test
  public void testUnweightedAllIncorrect() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(1001L, TFloat32.class);
      int[][] array = generateRandomArray(100, 1, 2);
      Operand<TFloat32> predictions = tf.dtypes.cast(tf.constant(array), TFloat32.class);
      Operand<TFloat32> labels =
          tf.dtypes.cast(tf.math.sub(tf.constant(1), tf.constant(array)), TFloat32.class);
      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      session.evaluate(0.f, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(1001L, TFloat32.class);
      Operand<TFloat32> predictions =
          tf.constant(
              new float[][] {
                {1, 0, 1, 0},
                {0, 1, 0, 1}
              });
      Operand<TFloat32> labels =
          tf.constant(
              new float[][] {
                {0, 1, 1, 0},
                {1, 0, 0, 1}
              });

      Operand<TFloat32> sampleWeights =
          tf.constant(
              new float[][] {
                {1, 2, 3, 4},
                {4, 3, 2, 1}
              });
      Op update = instance.updateState(tf, labels, predictions, sampleWeights);
      session.run(update);

      float weightedTp = 3.0f + 1.0f;
      float weightedT = (2.0f + 3.0f) + (4.0f + 1.0f);
      float expectedRecall = weightedTp / weightedT;

      session.evaluate(expectedRecall, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testDivByZero() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(1001L, TFloat32.class);

      Operand<TFloat32> predictions = tf.constant(new float[] {0, 0, 0, 0});
      Operand<TFloat32> labels = tf.constant(new float[] {0, 0, 0, 0});

      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      session.evaluate(0f, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testUnweightedWithThreshold() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(new float[] {0.5f, 0.7f}, 1001L, TFloat32.class);

      Operand<TFloat32> predictions = tf.constant(new float[][] {{1, 0, 0.6f, 0}});
      Operand<TFloat32> labels = tf.constant(new float[][] {{0, 1, 1, 0}});

      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      Float[] expected = new Float[] {0.5f, 0f};
      session.evaluate(expected, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testWeightedWithThreshold() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(new float[] {0.5f, 1.f}, 1001L, TFloat32.class);

      Operand<TFloat32> labels = tf.constant(new float[][] {{0, 1}, {1, 0}});
      Operand<TFloat32> predictions = tf.constant(new float[][] {{1, 0}, {0.6f, 0}});
      Operand<TFloat32> weights = tf.constant(new float[][] {{1, 4}, {3, 2}});

      Op update = instance.updateState(tf, labels, predictions, weights);
      session.run(update);

      float weightedTp = 0 + 3.f;
      float weightedPositives = (0 + 3.f) + (4.f + 0.f);
      float expectedRecall = weightedTp / weightedPositives;
      float[] expected = new float[] {expectedRecall, 0f};
      session.evaluate(expected, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testMultipleUpdates() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(new float[] {0.5f, 1.f}, 1001L, TFloat32.class);

      Operand<TFloat32> labels = tf.constant(new float[][] {{0, 1}, {1, 0}});
      Operand<TFloat32> predictions = tf.constant(new float[][] {{1, 0}, {0.6f, 0}});
      Operand<TFloat32> weights = tf.constant(new float[][] {{1, 4}, {3, 2}});

      Op update = instance.updateState(tf, labels, predictions, weights);
      for (int i = 0; i < 2; i++) session.run(update);

      float weightedTp = (0f + 3.f) + (0f + 3.f);
      float weightedPositives = ((0f + 3.f) + (4.f + 0.f)) + ((0f + 3.f) + (4.f + 0.f));
      float expectedRecall = weightedTp / weightedPositives;
      float[] expected = new float[] {expectedRecall, 0f};
      session.evaluate(expected, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testUnweightedTopK() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(null, null, 3, null, 1001L, TFloat32.class);

      Operand<TFloat32> labels = tf.constant(new float[][] {{0f, 1f, 1f, 0f, 0f}});
      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0.5f, 0f, 0.2f}});

      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      session.evaluate(0.5f, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testWeightedTopK() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(null, null, 3, null, 1001L, TFloat32.class);

      Operand<TFloat32> labels = tf.constant(new float[][] {{0, 1, 1, 0, 1}});
      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0.4f, 0f, 0.2f}});
      Operand<TFloat32> weights = tf.constant(new float[][] {{1, 4, 2, 3, 5}});

      Op update = instance.updateState(tf, labels, predictions, weights);
      session.run(update);

      labels = tf.constant(new float[][] {{1, 0, 1, 1, 1}});
      predictions = tf.constant(new float[][] {{0.2f, 0.6f, 0.4f, 0.2f, 0.2f}});
      weights = tf.constant(3.f);

      update = instance.updateState(tf, labels, predictions, weights);
      session.run(update);

      float weightedTp = (2 + 5) + (3 + 3);
      float weightedPositives = (4 + 2 + 5) + (3 + 3 + 3 + 3);
      float expectedRecall = weightedTp / weightedPositives;
      session.evaluate(expectedRecall, instance.result(tf, TFloat32.class));
    }
  }

  @Test
  public void testUnweightedClassId() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(null, null, null, 2, 1001L, TFloat32.class);

      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0.6f, 0f, 0.2f}});
      Operand<TFloat32> labels = tf.constant(new float[][] {{0, 1, 1, 0, 0}});
      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);
      session.evaluate(1f, instance.result(tf, TFloat32.class));
      session.evaluate(1f, instance.getTruePositives());
      session.evaluate(0f, instance.getFalseNegatives());

      predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0f, 0f, 0.2f}});
      labels = tf.constant(new float[][] {{0, 1, 1, 0, 0}});
      update = instance.updateState(tf, labels, predictions, null);
      session.run(update);
      session.evaluate(0.5f, instance.result(tf, TFloat32.class));
      session.evaluate(1f, instance.getTruePositives());
      session.evaluate(1f, instance.getFalseNegatives());

      predictions = tf.constant(new float[][] {{0.2f, 0.1f, 0.6f, 0f, 0.2f}});
      labels = tf.constant(new float[][] {{0, 1, 0, 0, 0}});
      update = instance.updateState(tf, labels, predictions, null);
      session.run(update);
      session.evaluate(0.5f, instance.result(tf, TFloat32.class));
      session.evaluate(1f, instance.getTruePositives());
      session.evaluate(1f, instance.getFalseNegatives());
    }
  }

  @Test
  public void testUnweightedTopKAndClassId() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(null, null, 2, 2, 1001L, TFloat32.class);

      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.6f, 0.3f, 0, 0.2f}});
      Operand<TFloat32> labels = tf.constant(new float[][] {{0, 1, 1, 0, 0}});
      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      session.evaluate(1f, instance.result(tf, TFloat32.class));
      session.evaluate(1f, instance.getTruePositives());
      session.evaluate(0f, instance.getFalseNegatives());

      predictions = tf.constant(new float[][] {{1, 1, 0.9f, 1, 1}});
      labels = tf.constant(new float[][] {{0, 1, 1, 0, 0}});

      update = instance.updateState(tf, labels, predictions, null);
      session.run(update);
      session.evaluate(0.5f, instance.result(tf, TFloat32.class));
      session.evaluate(1f, instance.getTruePositives());
      session.evaluate(1f, instance.getFalseNegatives());
    }
  }

  @Test
  public void testUnweightedTopKAndThreshold() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(null, 0.7f, 2, null, 1001L, TFloat32.class);

      Operand<TFloat32> predictions = tf.constant(new float[][] {{0.2f, 0.8f, 0.6f, 0f, 0.2f}});
      Operand<TFloat32> labels = tf.constant(new float[][] {{1, 1, 1, 0, 1}});
      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      session.evaluate(0.25f, instance.result(tf, TFloat32.class));
      session.evaluate(1f, instance.getTruePositives());
      session.evaluate(3f, instance.getFalseNegatives());
    }
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      Recall<TFloat32> instance = new Recall<>(null, 0.7f, 2, null, 1001L, TFloat32.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
