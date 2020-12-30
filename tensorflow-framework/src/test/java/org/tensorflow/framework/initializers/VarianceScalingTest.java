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

  private static final long SEED = 1000L;
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

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
  public void testCallFloat1FanInTruncatedNormal() {
    float[] expected = {-0.52388954F, -0.29329166F, -0.07872587F, -0.31851602F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        VarianceScaling<TFloat32> instance =
            new VarianceScaling<>(
                tf,
                1.0,
                VarianceScaling.Mode.FAN_IN,
                VarianceScaling.Distribution.TRUNCATED_NORMAL,
                SEED);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testCallDouble1FanInTruncatedNormal() {
    double[] expected = {
      1.4971264721246893, -1.2488522307109322, -0.5409677352523339, 0.4871390504288623
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        VarianceScaling<TFloat64> instance =
            new VarianceScaling<>(
                tf,
                1.0,
                VarianceScaling.Mode.FAN_IN,
                VarianceScaling.Distribution.TRUNCATED_NORMAL,
                SEED);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class VarianceScaling. */
  @Test
  public void testCallFloat1FanInNormal() {
    float[] expected = {-0.46082667F, -0.25798687F, -0.06924929F, -0.28017485F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        VarianceScaling<TFloat32> instance =
            new VarianceScaling<>(
                tf,
                1.0,
                VarianceScaling.Mode.FAN_IN,
                VarianceScaling.Distribution.NORMAL,
                SEED);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testCalltestSoftmaxDouble1FanInNormal() {
    double[] expected = {
      1.3169108626945392, -1.0985224689731887, -0.13536536217837225, -1.698770780615686
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        VarianceScaling<TFloat64> instance =
            new VarianceScaling<>(
                tf,
                1.0,
                VarianceScaling.Mode.FAN_IN,
                VarianceScaling.Distribution.NORMAL,
                SEED);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class VarianceScaling. */
  @Test
  public void testCalltestSoftmaxFloat1FanInUNIFORM() {
    float[] expected = {0.9266439F, 0.8190767F, 1.1268647F, 0.6596042F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        VarianceScaling<TFloat32> instance =
            new VarianceScaling<>(
                tf, 1.0, VarianceScaling.Mode.FAN_IN, VarianceScaling.Distribution.UNIFORM, SEED);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testCalltestSoftmaxDouble1FanInUNIFORM() {
    double[] expected = {
      0.06468193804916589, 0.44170328686673477, 0.06711059208157763, 0.6278720842445181
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        VarianceScaling<TFloat64> instance =
            new VarianceScaling<>(
                tf, 1.0, VarianceScaling.Mode.FAN_IN, VarianceScaling.Distribution.UNIFORM, SEED);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testReproducible1() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        VarianceScaling<TFloat64> instance =
            new VarianceScaling<>(
                tf, 1.0, VarianceScaling.Mode.FAN_IN, VarianceScaling.Distribution.UNIFORM, SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }

  @Test
  public void testReproducible2() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        VarianceScaling<TFloat64> instance =
            new VarianceScaling<>(
                tf,
                1.0,
                VarianceScaling.Mode.FAN_IN,
                VarianceScaling.Distribution.NORMAL,
                SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }

  @Test
  public void testReproducible3() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        VarianceScaling<TFloat64> instance =
            new VarianceScaling<>(
                tf,
                1.0,
                VarianceScaling.Mode.FAN_OUT,
                VarianceScaling.Distribution.TRUNCATED_NORMAL,
                SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }

  @Test
  public void testReproducible4() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        VarianceScaling<TFloat64> instance =
            new VarianceScaling<>(
                tf, 1.0, VarianceScaling.Mode.FAN_AVG, VarianceScaling.Distribution.UNIFORM, SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }
}
