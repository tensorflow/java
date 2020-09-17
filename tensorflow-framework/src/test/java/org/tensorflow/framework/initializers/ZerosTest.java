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

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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

/**
 * Test the Zeros initializer
 */
public class ZerosTest {

    private final TestSession.Mode tfMode = TestSession.Mode.EAGER;

    public ZerosTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of call method, of class Zeros.
     */
    @Test
    public void testCallUInt() {
        byte[] expected = {0, 0, 0, 0};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);
            Zeros<TUint8> instance = new Zeros<>(tf);
            Operand<TUint8> operand = instance.call(tf.constant(shape), TUint8.DTYPE);
            session.evaluate(expected, operand);
        }
    }

    /**
     * Test of call method, of class Zeros.
     */
    @Test
    public void testCallInt() {
        int[] expected = {0, 0, 0, 0};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);
            Zeros<TInt32> instance = new Zeros<>(tf);
            Operand<TInt32> operand = instance.call(tf.constant(shape), TInt32.DTYPE);
            session.evaluate(expected, operand);
        }
    }

    /**
     * Test of call method, of class Zeros.
     */
    @Test
    public void testCallLong() {
        long[] expected = {0, 0, 0, 0};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);
            Zeros<TInt64> instance = new Zeros<>(tf);
            Operand<TInt64> operand = instance.call(tf.constant(shape), TInt64.DTYPE);
            session.evaluate(expected, operand);
        }
    }

    /**
     * Test of call method, of class Zeros.
     */
    @Test
    public void testCallFloat() {
        float[] expected = {0.F, 0.F, 0.F, 0.F};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);
            Zeros<TFloat32> instance = new Zeros<>(tf);
            Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.DTYPE);
            session.evaluate(expected, operand);
        }
    }

    /**
     * Test of call method, of class Zeros.
     */
    @Test
    public void testCallDouble() {
        double[] expected = {0., 0., 0., 0.};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);

            Zeros<TFloat64> instance = new Zeros<>(tf);
            Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.DTYPE);
            session.evaluate(expected, operand);
        }
    }

    /**
     * Test of call method, of class Zeros.
     */
    @Test
    public void testCallString() {
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);

            Zeros<TString> instance = new Zeros<>(tf);
            Operand<TString> operand = instance.call(tf.constant(shape), TString.DTYPE);
            AtomicInteger counter = new AtomicInteger();
            operand.asTensor().data().scalars().forEach(s -> {
                counter.getAndIncrement();
                assertTrue(s.getObject().isEmpty());
            });
            assertEquals(counter.get(), 2 * 2);
        }
    }

    /**
     * Test of call method, of class Zeros.
     */
    @Test
    public void testCallBool() {
        Boolean[] expected = {false, false, false, false};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);

            Zeros<TBool> instance = new Zeros<>(tf);
            Operand<TBool> operand = instance.call(tf.constant(shape), TBool.DTYPE);
            session.evaluate(expected, operand);
        }
    }

}
