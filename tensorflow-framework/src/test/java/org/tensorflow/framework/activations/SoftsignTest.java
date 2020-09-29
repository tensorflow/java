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
public class SoftsignTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public SoftsignTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of Softsign call method */
  @Test
  public void testCall_Int() {
    int[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    for (TestSession.Mode tfMode : tfModes)
      assertThrows(
          java.lang.IllegalArgumentException.class,
          () -> {
            try (TestSession session = TestSession.createTestSession(tfMode)) {
              Ops tf = session.getTF();
              Softsign<TInt32> instance = new Softsign<>(tf);
              Operand<TInt32> result = instance.call(tf.constant(input));
            }
          });
  }

  /** Test of Softsign call method */
  @Test
  public void testCall_Float() {
    float[] input = {1, 2, 3, 4, 5, 6, 7, 8};
    float[] expected = {0.5F, 0.6666667F, 0.75F, 0.8F, 0.8333333F, 0.85714287F, 0.875F, 0.8888889F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softsign<TFloat32> instance = new Softsign<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of Softsign call method */
  @Test
  public void testCall_Double() {
    double[] input = {1, 2, 3, 4, 5, 6, 7, 8};
    double[] expected = {
      0.5,
      0.6666666666666666,
      0.75,
      0.8,
      0.8333333333333334,
      0.8571428571428571,
      0.875,
      0.8888888888888888
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softsign<TFloat64> instance = new Softsign<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
