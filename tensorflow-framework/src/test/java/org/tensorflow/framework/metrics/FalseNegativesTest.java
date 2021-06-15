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
import org.tensorflow.types.TInt64;

public class FalseNegativesTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  long[][] trueArray = {
    {0, 1, 0, 1, 0}, {0, 0, 1, 1, 1},
    {1, 1, 1, 1, 0}, {0, 0, 0, 0, 1}
  };

  long[][] predArray = {
    {0, 0, 1, 1, 0}, {1, 1, 1, 1, 1},
    {0, 1, 0, 1, 0}, {1, 1, 1, 1, 1}
  };

  double[] sampleWeightArray = {1., 1.5, 2., 2.5};

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Operand<TInt64> predictions = tf.constant(this.predArray);
      Operand<TInt64> labels = tf.constant(this.trueArray);
      FalseNegatives<TFloat64> instance = new FalseNegatives<>(tf, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat64> result = instance.result();

      session.evaluate(3.0, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Operand<TInt64> predictions = tf.constant(this.predArray);
      Operand<TInt64> labels = tf.constant(this.trueArray);
      Operand<TFloat64> sampleWeight = tf.constant(this.sampleWeightArray);
      FalseNegatives<TFloat64> instance = new FalseNegatives<>(tf, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      Operand<TFloat64> result = instance.result();

      session.evaluate(5.0, result);
    }
  }

  @Test
  public void testUnweightedWithThresholds() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Operand<TFloat32> predictions =
          tf.constant(
              new float[][] {
                {0.9f, 0.2f, 0.8f, 0.1f},
                {0.2f, 0.9f, 0.7f, 0.6f},
                {0.1f, 0.2f, 0.4f, 0.3f},
                {0f, 1f, 0.7f, 0.3f}
              });
      Operand<TInt64> labels =
          tf.constant(
              new long[][] {
                {0, 1, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1}
              });
      FalseNegatives<TFloat32> instance =
          new FalseNegatives<>(tf, new float[] {0.15f, 0.5f, 0.85f}, 1001L, TFloat32.class);
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, null);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      float[] expected = new float[] {1.f, 4.f, 6.f};
      session.evaluate(expected, result);
    }
  }

  @Test
  public void testWeightedWithThresholds() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Operand<TFloat32> predictions =
          tf.constant(
              new float[][] {
                {0.9f, 0.2f, 0.8f, 0.1f},
                {0.2f, 0.9f, 0.7f, 0.6f},
                {0.1f, 0.2f, 0.4f, 0.3f},
                {0f, 1f, 0.7f, 0.3f}
              });
      Operand<TInt64> labels =
          tf.constant(
              new long[][] {
                {0, 1, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1}
              });

      Operand<TFloat64> sampleWeight = tf.constant(new double[][] {{3.0}, {5.0}, {7.0}, {4.0}});
      FalseNegatives<TFloat64> instance =
          new FalseNegatives<>(tf, new float[] {0.15f, 0.5f, 0.85f}, 1001L, TFloat64.class);
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      Operand<TFloat64> result = instance.result();
      double[] expected = new double[] {4., 16., 23.};
      session.evaluate(expected, result);
    }
  }

  @Test
  public void testInitTF() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TInt64> predictions = tf.constant(this.predArray);
      Operand<TInt64> labels = tf.constant(this.trueArray);
      Operand<TFloat64> sampleWeight = tf.constant(this.sampleWeightArray);
      FalseNegatives<TFloat64> instance = new FalseNegatives<>(1001L, TFloat64.class);
      instance.init(tf);
      session.run(instance.resetStates());
      Op update = instance.updateState(labels, predictions, sampleWeight);
      session.run(update);
      Operand<TFloat64> result = instance.result();

      session.evaluate(5.0, result);
    }
  }

  @Test
  public void testIllegalState() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          try (TestSession session = TestSession.createTestSession(tfMode)) {
            FalseNegatives<TFloat64> instance =
                new FalseNegatives<>(new float[] {0.15f, 0.5f, 0.85f}, 1001L, TFloat64.class);
            session.run(instance.resetStates());
          }
        });
  }
}
