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
package org.tensorflow.keras.utils;

import java.util.function.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.types.TBool;

/**
 *
 * @author Jim Clarke
 */
public class SmartCondTest {

    public SmartCondTest() {
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
     * Test of cond method, of class SmartCond.
     */
    @Test
    public void testCondOp() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            Constant pred = tf.constant(true);
            sess.run(pred);

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = true;
            Operand resultOp = SmartCond.select(tf, pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }

        }
    }

    @Test
    public void testCondOp2() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            Constant pred = tf.constant(true);
            sess.run(pred);

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = true;
            Operand resultOp = SmartCond.select(tf, pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }

        }
    }

    @Test
    public void testCondOpPoint1() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            Operand pred = tf.math.equal(tf.constant(0.1), tf.constant(1.0));
            sess.run(pred);

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = false;
            Operand resultOp = SmartCond.select(tf, pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }
        }
    }

    @Test
    public void testCondOpString() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            Operand pred = tf.math.equal(tf.constant("TRUE"), tf.constant("TRUE"));

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = true;
            Operand resultOp = SmartCond.select(tf, pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }
        }
    }

    /**
     * Test of cond method, of class SmartCond.
     */
    @Test
    public void testCondBoolean() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            boolean pred = false;

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = false;
            Operand resultOp = SmartCond.select(pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }

        }
    }

    /**
     * Test of cond method, of class SmartCond.
     */
    @Test
    public void testCondInt() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            int pred = 1;

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = true;
            Operand resultOp = SmartCond.select(pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }

        }
    }

    /**
     * Test of cond method, of class SmartCond.
     */
    @Test
    public void testCondFloat1() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            float pred = 1.0F;

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = true;
            Operand resultOp = SmartCond.select(pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }

        }
    }

    /**
     * Test of cond method, of class SmartCond.
     */
    @Test
    public void testCondFloat0_1() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            float pred = 0.1F;

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = false;
            Operand resultOp = SmartCond.select(pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }

        }
    }

    /**
     * Test of cond method, of class SmartCond.
     */
    @Test
    public void testCondString() {
        try (Graph graph = new Graph(); Session sess = new Session(graph)) {
            Ops tf = Ops.create(graph).withName("test");
            String pred = "true";

            Supplier<Operand> true_fn = () -> tf.constant(true);
            Supplier<Operand> false_fn = () -> tf.constant(false);
            boolean expResult = true;
            Operand resultOp = SmartCond.select(pred, true_fn, false_fn);
            boolean actualResult;
            try (Tensor<TBool> result = sess.runner().fetch(resultOp).run().get(0).expect(TBool.DTYPE)) {
                actualResult = result.data().getBoolean();
                assertEquals(expResult, actualResult);
            }

        }
    }

}
