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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

/** Test the Ones initializer */
public class OnesTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class Ones. */
  @Test
  public void testCallUInt() {
    Byte[] expected = {1, 1, 1, 1}; // init to ones to make sure they all changed to zero
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Ones<TUint8> instance = new Ones<>();
        Operand<TUint8> operand = instance.call(tf, tf.constant(shape), TUint8.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Ones. */
  @Test
  public void testCallInt() {
    int[] expected = {1, 1, 1, 1}; // init to ones to make sure they all changed to zero
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Ones<TInt32> instance = new Ones<>();
        Operand<TInt32> operand = instance.call(tf, tf.constant(shape), TInt32.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Ones. */
  @Test
  public void testCallLong() {
    long[] expected = {1, 1, 1, 1}; // init to ones to make sure they all changed to zero
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Ones<TInt64> instance = new Ones<>();
        Operand<TInt64> operand = instance.call(tf, tf.constant(shape), TInt64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Ones. */
  @Test
  public void testCallFloat() {
    float[] expected = {1.f, 1.f, 1.f, 1.f};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);
        Ones<TFloat32> instance = new Ones<>();
        Operand<TFloat32> operand = instance.call(tf, tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Ones. */
  @Test
  public void testCallDouble() {
    double[] expected = {1., 1., 1., 1.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Ones<TFloat64> instance = new Ones<>();
        Operand<TFloat64> operand = instance.call(tf, tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Ones. */
  @Test
  public void testCallString() {
    for (TestSession.Mode tfMode : tfModes)
      assertThrows(
          java.lang.IllegalArgumentException.class,
          () -> {
            try (TestSession session = TestSession.createTestSession(tfMode)) {
              Ops tf = session.getTF();
              Shape shape = Shape.of(2, 2);

              Ones<TString> instance = new Ones<>();
              instance.call(tf, tf.constant(shape), TString.class);
              fail("IllegalArgumentException should have been thrown for TString");
            }
          });
  }

  @Test
  public void testCallBool() {
    Boolean[] expected = {true, true, true, true};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Ones<TBool> instance = new Ones<>();
        Operand<TBool> operand = instance.call(tf, tf.constant(shape), TBool.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testReproducible() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Ones<TFloat64> instance = new Ones<>();
        Operand<TFloat64> operand1 = instance.call(tf, tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf, tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }

  @Test
  public void testFunctional() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Initializer<TFloat64> instance = (ltf, dims, type) -> ltf.ones(dims, type);
        Operand<TFloat64> operand1 = instance.call(tf, tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf, tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }
}
