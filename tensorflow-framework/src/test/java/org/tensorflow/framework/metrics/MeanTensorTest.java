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

public class MeanTensorTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TInt64> values = tf.constant(new long[] {100, 40});
      MeanTensor<TFloat64> instance = new MeanTensor<>(1001L, TFloat64.class);
      session.initialize();
      Op update = instance.updateState(tf, values, null);
      session.run(update);
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      double[] expected_result = new double[] {100, 40};
      session.evaluate(expected_result, result);

      session.evaluate(expected_result, instance.getTotal());
      session.evaluate(new double[] {1, 1}, instance.getCount());

      session.run(instance.resetStates(tf));
      session.evaluate(new double[] {0, 0}, instance.getTotal());
      session.evaluate(new double[] {0, 0}, instance.getCount());
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TInt64> values = tf.constant(new long[] {100, 30});
      MeanTensor<TFloat64> instance = new MeanTensor<>(1001L, TFloat64.class);
      session.initialize();

      // check scalar weight
      Op update = instance.updateState(tf, values, tf.constant(0.5f));
      session.run(update);
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      double[] expected_result = new double[] {100, 30};
      session.evaluate(expected_result, result);
      session.evaluate(new double[] {50, 15}, instance.getTotal());
      session.evaluate(new double[] {0.5, 0.5}, instance.getCount());

      // check weights not scalar and weights rank matches values rank
      values = tf.constant(new long[] {1, 5});
      update = instance.updateState(tf, values, tf.constant(new double[] {1f, 0.2f}));
      session.run(update);
      result = instance.result(tf, TFloat64.class);
      expected_result = new double[] {51 / 1.5, 16 / 0.7};
      session.evaluate(expected_result, result);
      session.evaluate(new double[] {51, 16}, instance.getTotal());
      session.evaluate(new double[] {1.5, .7}, instance.getCount());

      // check weights broadcast
      values = tf.constant(new long[] {1, 2});
      update = instance.updateState(tf, values, tf.constant(0.5f));
      session.run(update);
      result = instance.result(tf, TFloat64.class);
      expected_result = new double[] {51.5 / 2, 17 / 1.2};
      session.evaluate(expected_result, result);
      session.evaluate(new double[] {51.5, 17}, instance.getTotal());
      session.evaluate(new double[] {2, 1.2}, instance.getCount());

      // check weights squeeze
      values = tf.constant(new long[] {1, 5});
      Operand<TFloat64> sampleWeight = tf.constant(new double[][] {{1}, {0.2}});
      update = instance.updateState(tf, values, sampleWeight);
      session.run(update);
      result = instance.result(tf, TFloat64.class);
      expected_result = new double[] {52.5 / 3, 18 / 1.4};
      session.evaluate(expected_result, result);
      session.evaluate(new double[] {52.5, 18}, instance.getTotal());
      session.evaluate(new double[] {3, 1.4}, instance.getCount());
    }
  }

  @Test
  public void testWeightedExpand() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      // check weights expand
      MeanTensor<TFloat32> instance = new MeanTensor<>(1001L, TFloat32.class);

      Operand<TInt64> values = tf.constant(new long[][] {{1}, {5}});
      Operand<TFloat32> sampleWeight = tf.constant(new float[] {1f, 0.2f});
      Op update = instance.updateState(tf, values, sampleWeight);
      session.run(update);
      Operand<TFloat32> result = instance.result(tf, TFloat32.class);
      session.evaluate(tf.constant(new float[][] {{1f}, {5f}}), result);
      session.evaluate(tf.constant(new float[][] {{1f}, {1f}}), instance.getTotal());
      session.evaluate(tf.constant(new float[][] {{1f}, {0.2f}}), instance.getCount());
    }
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      MeanTensor<TFloat32> instance = new MeanTensor<>(1001L, TFloat32.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
