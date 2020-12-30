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
import org.tensorflow.types.TInt32;

/** Test the RandomUniform initializer */
public class RandomUniformTest {

  private static final long SEED = 1000L;
  private static final double MIN_VALUE = 0.0;
  private static final double MAX_VALUE = 10.0;
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public RandomUniformTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of call method, of class RandomUniform. */
  @Test
  public void testCallInt() {
    int[] expected = {6, 1, 4, 1};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        RandomUniform<TInt32> instance =
            new RandomUniform<>(tf, MIN_VALUE, MAX_VALUE, SEED);
        Operand<TInt32> operand = instance.call(tf.constant(shape), TInt32.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class RandomUniform. */
  @Test
  public void testCallFloat() {
    float[] expected = {7.5660157f, 6.6877327f, 9.200811f, 5.385646F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        RandomUniform<TFloat32> instance =
            new RandomUniform<>(tf, MIN_VALUE, MAX_VALUE, SEED);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testCallDouble() {
    double[] expected = {
      0.5281258126492294, 3.6064922351122752, 0.5479556897864346, 5.126554100456142
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        RandomUniform<TFloat64> instance =
            new RandomUniform<>(tf, MIN_VALUE, MAX_VALUE, SEED);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testReproducible() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        RandomUniform<TFloat64> instance =
            new RandomUniform<>(tf, MIN_VALUE, MAX_VALUE, SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }
}
