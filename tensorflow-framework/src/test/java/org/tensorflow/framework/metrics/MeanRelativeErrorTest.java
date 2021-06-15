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
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.tensorflow.framework.utils.CastHelper.cast;

public class MeanRelativeErrorTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[][] predArray = new float[][] {{2, 4, 6, 8}};
      float[][] trueArray = new float[][] {{1, 3, 2, 3}};
      Operand<TFloat32> predictions = tf.constant(predArray);
      Operand<TFloat32> labels = tf.constant(trueArray);

      MeanRelativeError<TFloat32> instance =
          new MeanRelativeError<>(tf, labels, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      double expected_result = 1.25;
      session.evaluate(expected_result, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[] predArray = new float[] {2, 4, 6, 8};
      float[] trueArray = new float[] {1, 3, 2, 3};
      float[] sampleWeightArray = new float[] {0.2f, 0.3f, 0.5f, 0f};

      Operand<TFloat32> predictions = tf.constant(predArray);
      Operand<TFloat32> labels = tf.constant(trueArray);
      Operand<TFloat32> sampleWeight = tf.constant(sampleWeightArray);

      MeanRelativeError<TFloat32> instance =
          new MeanRelativeError<>(tf, labels, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      double expectedResult = 1.3;
      session.evaluate(expectedResult, result);
    }
  }

  @Test
  public void testZeroNormalizer() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[] predArray = new float[] {2, 4};
      int[] trueArray = new int[] {1, 3};

      Operand<TFloat32> predictions = tf.constant(predArray);
      Operand<TInt32> labels = tf.constant(trueArray);

      MeanRelativeError<TFloat32> instance =
          new MeanRelativeError<>(
              tf, cast(tf, tf.zerosLike(labels), TFloat32.class), 1001L, TFloat32.class);
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      double expectedResult = 0;
      session.evaluate(expectedResult, result);
    }
  }

  @Test
  public void testInitTF() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      double[][] predArray = new double[][] {{2, 4, 6, 8}};
      double[][] trueArray = new double[][] {{1, 3, 2, 3}};
      Operand<TFloat64> predictions = tf.constant(predArray);
      Operand<TFloat64> labels = tf.constant(trueArray);

      DoubleNdArray normalizer = StdArrays.ndCopyOf(trueArray);

      MeanRelativeError<TFloat64> instance =
          new MeanRelativeError<>("testInitTF", normalizer, 1001L, TFloat64.class);
      instance.init(tf);
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat64> result = instance.result();

      double expected_result = 1.25;
      session.evaluate(expected_result, result);
    }
  }

  @Test
  public void testIllegalState() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          try (TestSession session = TestSession.createTestSession(tfMode)) {
            MeanRelativeError<TFloat32> instance =
                new MeanRelativeError<>(
                    "testIllegalState", new float[] {0, 0, 0}, 1001L, TFloat32.class);
            session.run(instance.resetStates());
          }
        });
  }
}
