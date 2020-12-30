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
  public void testSoftmaxOpsOperandFloat() {
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
  public void testSoftmaxOpsOperandDouble() {
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
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of Softmax method, of class Activations. */
  @Test
  public void testSoftmaxOpsOperandDoubleNegative() {
    double[][] input = {{1, -2, 3, -4}, {-5, 6, -7, 8}};
    double[][] expected = {
      {1.18405115e-01, 5.89504354e-03, 8.74902034e-01, 7.97807387e-04},
      {1.99088704e-06, 1.19202653e-01, 2.69437261e-07, 8.80795087e-01}
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softmax<TFloat64> instance = new Softmax<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of Softmax method, of class Activations. */
  @Test
  public void testSoftmax1D() {
    double[] input = {1, -2, 3, -4, -5, 6, 7, 8};
    double[] expected = {
            6.0352829e-04, 3.0047902e-05, 4.4595040e-03, 4.0665414e-06,
            1.4959969e-06, 8.9571528e-02, 2.4348068e-01, 6.6184908e-01
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softmax<TFloat64> instance = new Softmax<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of Softmax method, of class Activations. */
  @Test
  public void testSoftmax3D() {
    double[][][] input = {{{1, -2}, {3, -4}}, {{-5, 6}, {-7, 8}}};
    double[][][] expected = {
            {{9.5257413e-01, 4.7425874e-02}, {9.9908900e-01, 9.1105123e-04}},
            {{1.6701422e-05, 9.9998331e-01}, {3.0590220e-07, 9.9999964e-01}}
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softmax<TFloat64> instance = new Softmax<>(tf);
        Operand<TFloat64> result = instance.call(tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }
}
