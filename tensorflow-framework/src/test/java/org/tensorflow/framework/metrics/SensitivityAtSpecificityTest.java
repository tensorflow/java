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
import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt64;

public class SensitivityAtSpecificityTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;
  private final Random random = new Random();

  @Test
  public void testValueIsIdempotent() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SensitivityAtSpecificity<TFloat32> instance =
          new SensitivityAtSpecificity<>(0.7f, 1001L, TFloat32.class);
      Operand<TFloat32> predictions =
          tf.random.statelessRandomUniform(
              tf.constant(Shape.of(10, 3)), tf.constant(new long[] {1L, 0L}), TFloat32.class);
      Operand<TFloat32> labels =
          tf.random.statelessRandomUniform(
              tf.constant(Shape.of(10, 3)), tf.constant(new long[] {1L, 0L}), TFloat32.class);
      labels = tf.math.mul(labels, tf.constant(2.0f));

      // instance.setDebug(session.getGraphSession());
      Op update = instance.updateState(tf, labels, predictions, null);

      for (int i = 0; i < 10; i++) session.run(update);

      Operand<TFloat32> initialSensitivity = instance.result(tf, TFloat32.class);

      for (int i = 0; i < 10; i++)
        session.evaluate(initialSensitivity, instance.result(tf, TFloat32.class));

      // instance.setDebug(null);

    }
  }

  private int[][] generateRandomArray(int dim1, int dim2) {
    int[][] result = new int[dim1][dim2];
    for (int i = 0; i < dim1; i++) {
      for (int j = 0; j < dim2; j++) {
        result[i][j] = random.nextInt(2);
      }
    }

    return result;
  }

  @Test
  public void testUnweightedAllCorrect() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SensitivityAtSpecificity<TFloat32> instance =
          new SensitivityAtSpecificity<>(0.7f, 1001L, TFloat32.class);
      int[][] predArray = generateRandomArray(100, 1);
      int[][] trueArray = new int[100][1]; // 100,1
      System.arraycopy(predArray, 0, trueArray, 0, predArray.length);
      Operand<TFloat32> predictions = cast(tf, tf.constant(predArray), TFloat32.class);
      Operand<TFloat32> labels = cast(tf, tf.constant(trueArray), TFloat32.class);

      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      Operand<TFloat32> precision = instance.result(tf, TFloat32.class);

      session.evaluate(1f, precision);
    }
  }

  @Test
  public void testUnweightedHighSpecificity() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SensitivityAtSpecificity<TFloat64> instance =
          new SensitivityAtSpecificity<>(0.8f, 1001L, TFloat64.class);
      Operand<TFloat32> predictions =
          tf.constant(new float[] {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.1f, 0.45f, 0.5f, 0.8f, 0.9f});
      Operand<TInt64> labels = tf.constant(new long[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1});

      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      Operand<TFloat64> precision = instance.result(tf, TFloat64.class);

      session.evaluate(0.8, precision);
    }
  }

  @Test
  public void testUnweightedLowSpecificity() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SensitivityAtSpecificity<TFloat32> instance =
          new SensitivityAtSpecificity<>(0.4f, 1001L, TFloat32.class);
      Operand<TFloat32> predictions =
          tf.constant(
              new float[] {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.01f, 0.02f, 0.25f, 0.26f, 0.26f});
      Operand<TInt64> labels = tf.constant(new long[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1});

      Op update = instance.updateState(tf, labels, predictions, null);
      session.run(update);

      Operand<TFloat32> precision = instance.result(tf, TFloat32.class);

      session.evaluate(0.6f, precision);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      SensitivityAtSpecificity<TFloat64> instance =
          new SensitivityAtSpecificity<>(0.4f, 1001L, TFloat64.class);
      Operand<TFloat32> predictions =
          tf.constant(
              new float[] {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.01f, 0.02f, 0.25f, 0.26f, 0.26f});
      Operand<TInt64> labels = tf.constant(new long[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1});
      Operand<TFloat64> sampleWeight = tf.constant(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

      Op update = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(update);

      Operand<TFloat64> precision = instance.result(tf, TFloat64.class);

      session.evaluate(0.675, precision);
    }
  }

  @Test
  public void testInvalidSensitivity() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new SensitivityAtSpecificity<>(-1f, 1001L, TFloat32.class));
  }

  @Test
  public void testInvalidNumThresholds() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new SensitivityAtSpecificity<>(0.7f, -1, 1001L, TFloat32.class));
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      SensitivityAtSpecificity<TFloat64> instance =
          new SensitivityAtSpecificity<>(0.4f, 1001L, TFloat64.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
