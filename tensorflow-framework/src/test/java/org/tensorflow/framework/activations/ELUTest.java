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

import org.junit.jupiter.api.*;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

import static org.junit.jupiter.api.Assertions.assertThrows;

/** @author Jim Clarke */
public class ELUTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public ELUTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}



  /** Test of ELU call method */
  @Test
  public void testCallFloat() {
    float[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    float[] expected = {1f, -0.86466473f, 3f, -0.9816844f, -0.63212055f, 2f, -0.95021296f, 4f};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ELU<TFloat32> instance = new ELU<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of ELU call method */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    double[] expected = {1, -0.86466473, 3, -0.9816844, -0.63212055, 2, -0.95021293, 4F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ELU<TFloat64> instance = new ELU<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of ELU call method */
  @Test
  public void testAlpha() {
    double[] input = {1, -2, 3, -4, -5, 6, 7, 8};
    double[] expected = {1, -1.7293295, 3, -1.9633688, -1.9865241, 6, 7, 8};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ELU<TFloat64> instance = new ELU<>(tf, 2.0f);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
