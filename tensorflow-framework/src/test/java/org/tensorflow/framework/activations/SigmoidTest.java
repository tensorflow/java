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
public class SigmoidTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public SigmoidTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}


  /** Test of Sigmoid call method */
  @Test
  public void testCallFloat() {
    float[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    float[] expected = {
      0.7310586F,
      0.11920291F,
      0.95257413F,
      0.017986238F,
      0.26894143F,
      0.8807971F,
      0.047425866F,
      0.98201376F,
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Sigmoid<TFloat32> instance = new Sigmoid<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of Sigmoid call method */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    double[] expected = {
      0.7310585786300049, 0.11920292202211755, 0.9525741268224334,
      0.01798620996209156, 0.2689414213699951, 0.8807970779778823,
      0.04742587317756678, 0.9820137900379085
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Sigmoid<TFloat64> instance = new Sigmoid<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
