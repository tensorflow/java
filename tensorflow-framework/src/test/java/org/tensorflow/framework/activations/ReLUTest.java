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
package org.tensorflow.framework.activations;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

/** @author Jim Clarke */
public class ReLUTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of ReLU call method */
  @Test
  public void testCallFloat() {
    float[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    float[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU<TFloat32> instance = new ReLU<>();
        Operand<TFloat32> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of ReLU call method */
  @Test
  public void testCallInt() {
    int[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    int[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU<TInt32> instance = new ReLU<>();
        Operand<TInt32> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of ReLU call method */
  @Test
  public void testCallLong() {
    long[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    long[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU<TInt64> instance = new ReLU<>();
        Operand<TInt64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of ReLU call method */
  @Test
  public void testCallFloat16() {
    float[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    float[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU<TFloat16> instance = new ReLU<>();
        Operand<TFloat16> result =
            instance.call(tf, tf.dtypes.cast(tf.constant(input), TFloat16.class));
        session.evaluate(tf.dtypes.cast(tf.constant(expected), TFloat16.class), result);
      }
  }

  /** Test of ReLU call method */
  @Test
  public void testCallDouble() {
    double[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    double[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU<TFloat64> instance = new ReLU<>();
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  @Test
  public void testAlpha() {
    double[] input = {-10., -5., 0.0, 5., 10.};
    double[] expected = {-5., -2.5, 0., 5., 10.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU<TFloat64> instance = new ReLU<>(0.5f, ReLU.MAX_VALUE_DEFAULT, ReLU.THRESHOLD_DEFAULT);
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  @Test
  public void testMaxValue() {
    double[] input = {-10., -5., 0.0, 5., 10.};
    double[] expected = {0., 0., 0., 5., 5.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU<TFloat64> instance = new ReLU<>(ReLU.ALPHA_DEFAULT, 5, ReLU.THRESHOLD_DEFAULT);
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  @Test
  public void testThreshold() {
    double[] input = {-10., -5., 0.0, 5., 10.};
    double[] expected = {-0., -0., 0., 0., 10.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU<TFloat64> instance = new ReLU<>(ReLU.ALPHA_DEFAULT, ReLU.MAX_VALUE_DEFAULT, 5.0f);
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }
}
