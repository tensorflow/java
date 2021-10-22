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
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

public class RootMeanSquaredErrorTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      RootMeanSquaredError<TFloat32> instance = new RootMeanSquaredError<>(1001L, TFloat32.class);

      Operand<TFloat32> labels = tf.constant(new float[] {2, 4, 6});
      Operand<TFloat32> predictions = tf.constant(new float[] {1, 3, 2});

      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat32> total = instance.getTotal();
      Variable<TFloat32> count = instance.getCount();
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(18, total);
      session.evaluate(3, count);
      session.evaluate(Math.sqrt(6), result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      RootMeanSquaredError<TFloat64> instance = new RootMeanSquaredError<>(1001L, TFloat64.class);
      Operand<TFloat32> labels = tf.constant(new float[][] {{2, 4, 6, 8}});
      Operand<TFloat32> predictions = tf.constant(new float[][] {{1, 3, 2, 3}});
      Operand<TFloat64> sampleWeight = tf.constant(new double[][] {{0, 1, 0, 1}});

      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(26, total);
      session.evaluate(2, count);
      session.evaluate(Math.sqrt(13), result);
    }
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      RootMeanSquaredError<TFloat64> instance = new RootMeanSquaredError<>(1001L, TFloat64.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
