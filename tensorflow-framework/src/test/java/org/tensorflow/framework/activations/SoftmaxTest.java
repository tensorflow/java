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
public class SoftmaxTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public SoftmaxTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of Softmax method, of class Activations. */
  @Test
  public void testIntThrowsIAE() {
    int[][] input = {{1, -2, 3, -4}, {-1, 2, -3, 4}};
    for (TestSession.Mode tfMode : tfModes)
      assertThrows(
          java.lang.IllegalArgumentException.class,
          () -> {
            try (TestSession session = TestSession.createTestSession(tfMode)) {
              Ops tf = session.getTF();
              Softmax<TInt32> instance = new Softmax<>(tf);
              Operand<TInt32> result = instance.call(tf.constant(input));
            }
          });
  }

  /** Test of Softmax method, of class Activations. */
  @Test
  public void testSoftmax_Ops_Operand_Float() {
    float[][] input = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    float[][] expected = {
            {0.032059f, 0.087144f, 0.236883f, 0.643914f},
            {0.032059f, 0.087144f, 0.236883f, 0.643914f}
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softmax<TFloat32> instance = new Softmax<>(tf);
        Operand<TFloat32> result = instance.call(tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of Softmax method, of class Activations. */
  @Test
  public void testSoftmax_Ops_Operand_Double() {
    double[][] input = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    double[][] expected = {
      {0.032059, 0.087144, 0.236883, 0.643914},
      {0.032059, 0.087144, 0.236883, 0.643914}
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softmax<TFloat64> instance = new Softmax<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.print(System.out, result);
        session.evaluate(tf.constant(expected), result);
      }
  }
}
