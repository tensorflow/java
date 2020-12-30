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
import org.tensorflow.framework.initializers.VarianceScaling.Distribution;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

/** Test the LeCun initializer */
public class LeCunTest {

  private static final long SEED = 1000L;
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public LeCunTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of call method, of class LeCun. */
  @Test
  public void testCallNormalFloat() {
    float[] expected = {-0.52388954f, -0.29329166f, -0.07872587f, -0.31851602f};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        LeCun<TFloat32> instance = new LeCun<>(tf, Distribution.TRUNCATED_NORMAL, SEED);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testCallNormalDouble() {
    double[] expected = {
      1.4971264721246893, -1.2488522307109322, -0.5409677352523339, 0.4871390504288623
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        LeCun<TFloat64> instance = new LeCun<>(tf, Distribution.TRUNCATED_NORMAL, SEED);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class LeCun. */
  @Test
  public void testCallUniformFloat() {
    float[] expected = {0.9266439F, 0.8190767F, 1.1268647F, 0.6596042F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        LeCun<TFloat32> instance = new LeCun<>(tf, Distribution.UNIFORM, SEED);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testCallUniformDouble() {
    double[] expected = {
      0.06468193804916589, 0.44170328686673477, 0.06711059208157763, 0.6278720842445181
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        LeCun<TFloat64> instance = new LeCun<>(tf, Distribution.UNIFORM, SEED);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testCallNormalReproducible() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        LeCun<TFloat64> instance = new LeCun<>(tf, Distribution.TRUNCATED_NORMAL, SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }

  @Test
  public void testCallUniformReproducible() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        LeCun<TFloat64> instance = new LeCun<>(tf, Distribution.UNIFORM, SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }

  @Test
  public void testCallNORMALReproducible() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        LeCun<TFloat64> instance = new LeCun<>(tf, Distribution.NORMAL, SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }
}
