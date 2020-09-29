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
import org.tensorflow.types.TInt32;

import static org.junit.jupiter.api.Assertions.assertThrows;

/** @author Jim Clarke */
public class HardSigmoidTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public HardSigmoidTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of HardSigmoid call method. */
  @Test
  public void testCall__Int() {
    int[] input = {1, -2, 3, -4, -1, 2, -3, 4};

    for (TestSession.Mode tfMode : tfModes)
      assertThrows(
          java.lang.IllegalArgumentException.class,
          () -> {
            try (TestSession session = TestSession.createTestSession(tfMode)) {
              Ops tf = session.getTF();
              HardSigmoid<TInt32> instance = new HardSigmoid<>(tf);
              Operand<TInt32> result = instance.call(tf.constant(input));
            }
          });
  }

  /** Test of HardSigmoid call method. */
  @Test
  public void testCall__Float() {
    float[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    float[] expected = {0.7F, 0.099999994F, 1.1F, -0.3F, 0.3F, 0.9F, -0.100000024F, 1.3F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        HardSigmoid<TFloat32> instance = new HardSigmoid<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of HardSigmoid call method. */
  @Test
  public void testCall__Double() {
    double[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    double[] expected = {
      0.7, 0.09999999999999998, 1.1, -0.30000000000000004, 0.3, 0.9, -0.10000000000000009, 1.3
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        HardSigmoid<TFloat64> instance = new HardSigmoid<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
