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
public class TanhTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public TanhTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of Tanh call method. */
  @Test
  public void testCall_Int() {
    int[] input = {1, -2, 3, -4, -1, 2, -3, 4};

    for (TestSession.Mode tfMode : tfModes)
      assertThrows(
          java.lang.IllegalArgumentException.class,
          () -> {
            try (TestSession session = TestSession.createTestSession(tfMode)) {
              Ops tf = session.getTF();
              Tanh<TInt32> instance = new Tanh<>(tf);
              Operand<TInt32> result = instance.call(tf.constant(input));
            }
          });
  }

  /** Test of Tanh call method. */
  @Test
  public void testCall__Float() {
    float[] input = {1, 2, 3, 4, 5, 6, 7, 8};
    float[] expected = {
      0.7615942F, 0.9640276F, 0.9950547F, 0.9993292F, 0.99990916F, 0.99998784F, 0.99999833F, 1.0F
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Tanh<TFloat32> instance = new Tanh<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of Tanh call method. */
  @Test
  public void testCall__Double() {
    double[] input = {1, 2, 3, 4, 5, 6, 7, 8};
    double[] expected = {
      0.7615941559557649, 0.9640275800758169, 0.9950547536867305,
      0.999329299739067, 0.9999092042625951, 0.9999877116507956,
      0.9999983369439447, 0.9999997749296758
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Tanh<TFloat64> instance = new Tanh<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
