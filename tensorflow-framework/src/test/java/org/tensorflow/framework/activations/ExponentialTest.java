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
public class ExponentialTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public ExponentialTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}



  /** Test of Exponential call method. */
  @Test
  public void testCallFloat() {
    float[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    float[] expected = {
      2.7182817F,
      0.13533528F,
      20.085537F,
      0.01831564F,
      0.36787945F,
      7.389056F,
      0.049787067F,
      54.598152F
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Exponential<TFloat32> instance = new Exponential<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** TTest of Exponential call method. */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    double[] expected = {
      2.7182818284590455, 0.1353352832366127, 20.085536923187668,
      0.018315638888734182, 0.3678794411714423, 7.38905609893065,
      0.049787068367863944, 54.598150033144236,
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Exponential<TFloat64> instance = new Exponential<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
