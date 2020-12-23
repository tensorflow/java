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
public class SwishTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public SwishTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}



  /** Test of Swish call method */
  @Test
  public void testCallFloat() {
    float[] input = {1, -2, 3, -4, -5, 6, -7, 8};
    float[] expected = {
      0.7310585786300049f,
      -.238405844f,
      2.8577223804673f,
      -7.19448398e-02f,
      -3.34642546e-02f,
      5.985164261060192f,
      -6.37735836e-03f,
      7.997317198956269f
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Swish<TFloat32> instance = new Swish<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of Swish call method */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -5, 6, -7, 8};
    double[] expected = {
      0.7310585786300049,
      -.238405844,
      2.8577223804673,
      -7.19448398e-02,
      -3.34642546e-02,
      5.985164261060192,
      -6.37735836e-03,
      7.997317198956269
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Swish<TFloat64> instance = new Swish<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
