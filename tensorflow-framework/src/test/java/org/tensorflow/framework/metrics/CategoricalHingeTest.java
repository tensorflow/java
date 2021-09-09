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
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

class CategoricalHingeTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalHinge<TFloat64> instance =
          new CategoricalHinge<>("CH_testUnweighted", 1001L, TFloat64.class);
      int[] trueArray = {
        0, 1, 0, 1, 0,
        0, 0, 1, 1, 1,
        1, 1, 1, 1, 0,
        0, 0, 0, 0, 1
      };
      double[] predArray = {
        0, 0, 1, 1, 0,
        1, 1, 1, 1, 1,
        0, 1, 0, 1, 0,
        1, 1, 1, 1, 1
      };
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(4, 5)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(4, 5)));
      Op op = instance.updateState(tf, labels, predictions, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(2., total);
      session.evaluate(4, count);
      session.evaluate(0.5, result);
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      CategoricalHinge<TFloat64> instance =
          new CategoricalHinge<>("CH_testWeighted", 1001L, TFloat64.class);
      int[] trueArray = {
        0, 1, 0, 1, 0,
        0, 0, 1, 1, 1,
        1, 1, 1, 1, 0,
        0, 0, 0, 0, 1
      };
      double[] predArray = {
        0, 0, 1, 1, 0,
        1, 1, 1, 1, 1,
        0, 1, 0, 1, 0,
        1, 1, 1, 1, 1
      };
      Operand<TInt32> labels = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(4, 5)));
      Operand<TFloat64> predictions =
          tf.reshape(tf.constant(predArray), tf.constant(Shape.of(4, 5)));

      Operand<TFloat64> sampleWeight = tf.constant(new double[] {1., 1.5, 2., 2.5});
      Op op = instance.updateState(tf, labels, predictions, sampleWeight);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(3.5F, total);
      session.evaluate(7, count);
      session.evaluate(0.5, result);
    }
  }

  /** Test that Eager mode throws IllegalArgument Exception */
  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      CategoricalHinge<TFloat64> instance = new CategoricalHinge<>(null, 1001L, TFloat64.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
