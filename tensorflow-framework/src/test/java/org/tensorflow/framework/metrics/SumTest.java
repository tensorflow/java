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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

public class SumTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  /** Test of call method, of class Sum. */
  @Test
  public void testUnWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Sum<TFloat32> instance = new Sum<>(1001L, TFloat32.class);
      assertEquals(TFloat32.class, instance.getInternalType());
      assertNull(instance.getTotal());

      Op update = instance.updateState(tf, tf.constant(100f), null);
      session.run(update);
      session.evaluate(100f, instance.result(tf, TFloat64.class));
      session.evaluate(100f, instance.getTotal());

      update = instance.updateState(tf, tf.constant(new float[] {1, 5}), null);
      session.run(update);
      session.evaluate(106f, instance.result(tf, TFloat64.class));
      session.evaluate(106f, instance.getTotal());

      session.run(instance.resetStates(tf));
      session.evaluate(0f, instance.getTotal());
    }
  }

  @Test
  public void testSumWithSampleWeight() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Sum<TFloat64> instance = new Sum<>(1001L, TFloat64.class);

      // check scalar weight
      Op op = instance.updateState(tf, tf.constant(100f), tf.constant(0.5));
      session.run(op);
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(50.0, instance.getTotal());
      session.evaluate(50.0, result);

      //  check weights not scalar and weights rank matches values rank
      op =
          instance.updateState(
              tf, tf.constant(new float[] {1, 5}), tf.constant(new double[] {1, 0.2}));
      session.run(op);
      result = instance.result(tf, TFloat64.class);
      session.evaluate(52., instance.getTotal());
      session.evaluate(52., result);

      // check weights broadcast
      op = instance.updateState(tf, tf.constant(new float[] {1, 2}), tf.constant(0.5));
      session.run(op);
      result = instance.result(tf, TFloat64.class);
      session.evaluate(53.5, instance.getTotal());
      session.evaluate(53.5, result);

      // check weights squeeze
      op =
          instance.updateState(
              tf, tf.constant(new float[] {1, 5}), tf.constant(new double[][] {{1}, {0.2}}));
      session.run(op);
      result = instance.result(tf, TFloat64.class);
      session.evaluate(55.5, instance.getTotal());
      session.evaluate(55.5, result);

      // check weights expand
      op =
          instance.updateState(
              tf, tf.constant(new float[][] {{1}, {5}}), tf.constant(new double[] {1, 0.2}));
      session.run(op);
      result = instance.result(tf, TFloat64.class);
      session.evaluate(57.5, instance.getTotal());
      session.evaluate(57.5, result);

      // heck values reduced to the dimensions of weight
      op =
          instance.updateState(
              tf,
              tf.constant(new float[][][] {{{1.f, 2.f}, {3.f, 2.f}, {0.5f, 4.f}}}),
              tf.constant(new double[] {0.5}));
      session.run(op);
      result = instance.result(tf, TFloat64.class);
      session.evaluate(63.75, instance.getTotal());
      session.evaluate(63.75, result);
    }
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      Sum<TFloat64> instance = new Sum<>(1001L, TFloat64.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
