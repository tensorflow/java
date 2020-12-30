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
public class SELUTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public SELUTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}



  /** Test of SELU call method */
  @Test
  public void testCallFloat() {
    float[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    float[] expected = {
      1.050701F, -1.5201665F, 3.152103F, -1.7258986F, -1.1113307F, 2.101402F, -1.6705687F, 4.202804F
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        SELU<TFloat32> instance = new SELU<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of SELU call method */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    double[] expected = {
      1.0507009873554805, -1.520166468595695, 3.1521029620664414,
      -1.7258986281898947, -1.1113307378125628, 2.101401974710961,
      -1.670568728767112, 4.202803949421922,
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        SELU<TFloat64> instance = new SELU<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
