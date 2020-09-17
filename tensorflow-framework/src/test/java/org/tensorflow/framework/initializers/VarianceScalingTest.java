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
package org.tensorflow.framework.initializers;

import org.junit.jupiter.api.*;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

/** Test the VarianceScaling initializer */
public class VarianceScalingTest {

  private final TestSession.Mode tfMode = TestSession.Mode.EAGER;

  private static final long SEED = 1000L;

  public VarianceScalingTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of call method, of class VarianceScaling. */
  @Test
  public void testCall_Float_1_FAN_IN_TRUNCATED_NORMAL() {
    float[] expected = {-0.52388954F, -0.29329166F, -0.07872587F, -0.31851602F};
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(2, 2);
      VarianceScaling<TFloat32, TFloat32> instance =
          new VarianceScaling<>(
              tf,
              1.0,
              VarianceScaling.Mode.FAN_IN,
              VarianceScaling.Distribution.TRUNCATED_NORMAL,
              SEED);
      Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.DTYPE);
      session.evaluate(expected, operand);
    }
  }

  @Test
  public void testCall_Double_1_FAN_IN_TRUNCATED_NORMAL() {
    double[] expected = {
      1.4971264721246893, -1.2488522307109322, -0.5409677352523339, 0.4871390504288623
    };
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(2, 2);
      VarianceScaling<TFloat64, TFloat64> instance =
          new VarianceScaling<>(
              tf,
              1.0,
              VarianceScaling.Mode.FAN_IN,
              VarianceScaling.Distribution.TRUNCATED_NORMAL,
              SEED);
      Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.DTYPE);
      session.evaluate(expected, operand);
    }
  }

  /** Test of call method, of class VarianceScaling. */
  @Test
  public void testCall_Float_1_FAN_IN_UNTRUNCATED_NORMAL() {
    float[] expected = {-0.46082667F, -0.25798687F, -0.06924929F, -0.28017485F};
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(2, 2);
      VarianceScaling<TFloat32, TFloat32> instance =
          new VarianceScaling<>(
              tf,
              1.0,
              VarianceScaling.Mode.FAN_IN,
              VarianceScaling.Distribution.UNTRUNCATED_NORMAL,
              SEED);
      Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.DTYPE);
      session.evaluate(expected, operand);
    }
  }

  @Test
  public void testCall_Double_1_FAN_IN_UNTRUNCATED_NORMAL() {
    double[] expected = {
      1.3169108626945392, -1.0985224689731887, -0.13536536217837225, -1.698770780615686
    };
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(2, 2);
      VarianceScaling<TFloat64, TFloat64> instance =
          new VarianceScaling<>(
              tf,
              1.0,
              VarianceScaling.Mode.FAN_IN,
              VarianceScaling.Distribution.UNTRUNCATED_NORMAL,
              SEED);
      Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.DTYPE);
      session.evaluate(expected, operand);
    }
  }

  /** Test of call method, of class VarianceScaling. */
  @Test
  public void testCall_Float_1_FAN_IN_UNIFORM() {
    float[] expected = {0.9266439F, 0.8190767F, 1.1268647F, 0.6596042F};
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(2, 2);
      VarianceScaling<TFloat32, TFloat32> instance =
          new VarianceScaling<>(
              tf, 1.0, VarianceScaling.Mode.FAN_IN, VarianceScaling.Distribution.UNIFORM, SEED);
      Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.DTYPE);
      session.evaluate(expected, operand);
    }
  }

  @Test
  public void testCall_Double_1_FAN_IN_UNIFORM() {
    double[] expected = {
      0.06468193804916589, 0.44170328686673477, 0.06711059208157763, 0.6278720842445181
    };
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(2, 2);
      VarianceScaling<TFloat64, TFloat64> instance =
          new VarianceScaling<>(
              tf, 1.0, VarianceScaling.Mode.FAN_IN, VarianceScaling.Distribution.UNIFORM, SEED);
      Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.DTYPE);
      session.evaluate(expected, operand);
    }
  }
}
