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
package org.tensorflow.framework.initializers;

import org.junit.jupiter.api.*;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.*;

/** Test the Zeros initializer */
public class ZerosTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public ZerosTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of call method, of class Zeros. */
  @Test
  public void testCallUInt() {
    byte[] expected = {0, 0, 0, 0};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Zeros<TUint8> instance = new Zeros<>(tf);
        Operand<TUint8> operand = instance.call(tf.constant(shape), TUint8.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Zeros. */
  @Test
  public void testCallInt() {
    int[] expected = {0, 0, 0, 0};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Zeros<TInt32> instance = new Zeros<>(tf);
        Operand<TInt32> operand = instance.call(tf.constant(shape), TInt32.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Zeros. */
  @Test
  public void testCallLong() {
    long[] expected = {0, 0, 0, 0};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Zeros<TInt64> instance = new Zeros<>(tf);
        Operand<TInt64> operand = instance.call(tf.constant(shape), TInt64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Zeros. */
  @Test
  public void testCallFloat() {
    float[] expected = {0.F, 0.F, 0.F, 0.F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Zeros<TFloat32> instance = new Zeros<>(tf);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Zeros. */
  @Test
  public void testCallDouble() {
    double[] expected = {0., 0., 0., 0.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Zeros<TFloat64> instance = new Zeros<>(tf);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Zeros. */
  @Test
  public void testCallString() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Zeros<TString> instance = new Zeros<>(tf);
        Operand<TString> operand = instance.call(tf.constant(shape), TString.class);
        session.evaluateString(operand, String::isEmpty);
      }
  }

  /** Test of call method, of class Zeros. */
  @Test
  public void testCallBool() {
    Boolean[] expected = {false, false, false, false};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Zeros<TBool> instance = new Zeros<>(tf);
        Operand<TBool> operand = instance.call(tf.constant(shape), TBool.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testReproducible() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Zeros<TFloat64> instance = new Zeros<>(tf);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }
}
