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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/** Test the Constant initializer */
public class ConstantTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public ConstantTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of call method, of class Constant. */
  @Test
  public void testCallUInt() {
    Byte[] expected = {0xf, 0xf, 0xf, 0xf}; // init to constant to make sure they all change to zero
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Constant<TUint8> instance = new Constant<>(tf, 0xf);
        Operand<TUint8> operand = instance.call(tf.constant(shape), TUint8.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Constant. */
  @Test
  public void testCallInt() {
    Integer[] expected = {
      0xf, 0xf, 0xf, 0xf
    }; // init to constant to make sure they all change to zero
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Constant<TInt32> instance = new Constant<>(tf, 0xf);
        Operand<TInt32> operand = instance.call(tf.constant(shape), TInt32.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Constant. */
  @Test
  public void testCallLong() {
    long[] expected = {
      0xffL, 0xffL, 0xffL, 0xffL
    }; // init to constant to make sure they all change to zero
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Constant<TInt64> instance = new Constant<>(tf, 0xffL);
        Operand<TInt64> operand = instance.call(tf.constant(shape), TInt64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Constant. */
  @Test
  public void testCallFloat() {
    float[] expected = {12.f, 12.f, 12.f, 12.f};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Constant<TFloat32> instance = new Constant<>(tf, 12.F);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Constant. */
  @Test
  public void testCallDouble() {
    double[] expected = {11., 11., 11., 11.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Constant<TFloat64> instance = new Constant<>(tf, 11.);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Constant. */
  @Test
  public void testCallString() {
    for (TestSession.Mode tfMode : tfModes)
      assertThrows(
          java.lang.IllegalArgumentException.class,
          () -> {
            try (TestSession session = TestSession.createTestSession(tfMode)) {
              Ops tf = session.getTF();
              Shape shape = Shape.of(2, 2);

              Constant<TString> instance = new Constant<>(tf, 22);
              instance.call(tf.constant(shape), TString.class);
              fail("IllegalArgumentException  should have been thrown for TString");
            }
          });
  }

  /** Test of call method, of class Constant. */
  @Test
  public void testCallBool() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Boolean[] expected = {true, true, true, true};

        Constant<TBool> instance = new Constant<>(tf, true);
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

        Constant<TFloat64> instance = new Constant<>(tf, 11.);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }
}
