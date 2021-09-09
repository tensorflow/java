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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

class KLDivergenceTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      KLDivergence<TFloat64> instance =
          new KLDivergence<>("KLD_testUnweighted", 1001L, TFloat64.class);
      float[][] trueArray = {{.5f, .8f, .12f}, {.7f, .43f, .8f}};
      float[][] predArray = {{.4f, .9f, .12f}, {.36f, .3f, .4f}};
      Operand<TFloat32> labels = tf.constant(trueArray);
      Operand<TFloat32> predictions = tf.constant(predArray);

      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(1.1921477, total);
      session.evaluate(2, count);
      session.evaluate(0.5960738, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      KLDivergence<TFloat64> instance =
          new KLDivergence<>("KLD_testWeighted", 1001L, TFloat64.class);
      float[] trueArray = {
        .5f, .8f, .12f,
        .7f, .43f, .8f
      };
      float[] predArray = {
        .4f, .9f, .12f,
        .36f, .3f, .4f
      };
      Operand<TFloat32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
      Operand<TFloat32> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));

      Operand<TFloat64> sampleWeight = tf.constant(new double[][] {{1.2}, {3.4}});
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(4.015142, total);
      session.evaluate(4.6, count);
      session.evaluate(0.872857, result);
    }
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      KLDivergence<TFloat64> instance = new KLDivergence<>(null, 1001L, TFloat64.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
