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

import static org.junit.jupiter.api.Assertions.assertNull;
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
import org.tensorflow.types.TInt32;

class MeanAbsoluteErrorTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      MeanAbsoluteError<TFloat64> instance =
          new MeanAbsoluteError<>("MAE_testUnweighted", 1001L, TFloat64.class);
      assertNull(instance.getTotal());
      assertNull(instance.getCount());

      int[] trueArray = {
        0, 1, 0, 1, 0,
        0, 0, 1, 1, 1,
        1, 1, 1, 1, 0,
        0, 0, 0, 0, 1
      };
      float[] predictionArray = {
        0, 0, 1, 1, 0,
        1, 1, 1, 1, 1,
        0, 1, 0, 1, 0,
        1, 1, 1, 1, 1
      };
      Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(4, 5)));
      Operand<TFloat32> yPrediction =
          tf.reshape(tf.constant(predictionArray), tf.constant(Shape.of(4, 5)));
      Op op = instance.updateState(tf, yTrue, yPrediction, null);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(2.0, total);
      session.evaluate(4, count);
      session.evaluate(0.5, result);

      session.run(instance.resetStates(tf));
      session.evaluate(0.0, instance.getTotal());
      session.evaluate(0, instance.getCount());
      session.evaluate(0., instance.getCount());
    }
  }

  @Test
  public void testWeighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      MeanAbsoluteError<TFloat64> instance =
          new MeanAbsoluteError<>("MAE_testWeighted", 1001L, TFloat64.class);
      assertNull(instance.getTotal());
      assertNull(instance.getCount());

      int[] trueArray = {
        0, 1, 0, 1, 0,
        0, 0, 1, 1, 1,
        1, 1, 1, 1, 0,
        0, 0, 0, 0, 1
      };
      double[] predictionArray = {
        0, 0, 1, 1, 0,
        1, 1, 1, 1, 1,
        0, 1, 0, 1, 0,
        1, 1, 1, 1, 1
      };
      Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(4, 5)));
      Operand<TFloat64> yPrediction =
          tf.reshape(tf.constant(predictionArray), tf.constant(Shape.of(4, 5)));

      Operand<TFloat64> sampleWeight = tf.constant(new double[] {1., 1.5, 2., 2.5});
      Op op = instance.updateState(tf, yTrue, yPrediction, sampleWeight);
      session.run(op);
      Variable<TFloat64> total = instance.getTotal();
      Variable<TFloat64> count = instance.getCount();
      Operand<TFloat64> result = instance.result(tf, TFloat64.class);
      session.evaluate(3.8, total);
      session.evaluate(7, count);
      session.evaluate(0.54285, result);

      session.run(instance.resetStates(tf));
      session.evaluate(0.0, instance.getTotal());
      session.evaluate(0, instance.getCount());
      session.evaluate(0., instance.getCount());
    }
  }

  @Test
  public void testEagerEnvironment() {
    try (TestSession session = TestSession.createTestSession(TestSession.Mode.EAGER)) {
      Ops tf = session.getTF();
      MeanAbsoluteError<TFloat64> instance = new MeanAbsoluteError<>("MAE", 1001L, TFloat64.class);
      assertThrows(IllegalArgumentException.class, () -> instance.updateState(tf, null, null));
    }
  }
}
