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

import java.util.HashMap;
import java.util.Map;
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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

/**
 * Test the RandomNormal initializer
 */
public class RandomNormalTest {

    private final TestSession.Mode tfMode = TestSession.Mode.EAGER;

    private static final long SEED = 1000L;
    private static final double MEAN_VALUE = 0.0;
    private static final double STDDEV_VALUE = 3.0;

    public RandomNormalTest() {
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
     * Test of call method, of class RandomNormal.
     */
    @Test
    public void testCall_Float() {
        float[] expected = {-1.955122f, -1.0945456f, -0.29379985f, -1.1886811f};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);
            RandomNormal<TFloat32, TFloat32> instance
                    = new RandomNormal(tf, MEAN_VALUE, STDDEV_VALUE, SEED);
            Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.DTYPE);
            session.evaluate(expected, operand);
        }
    }

    @Test
    public void testCall_Double() {
        double[] expected = {5.58717960737721, -4.6606361225803825,
            -0.5743065932046001, -7.207274031929497};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);
            RandomNormal<TFloat64, TFloat64> instance
                    = new RandomNormal(tf, MEAN_VALUE, STDDEV_VALUE, SEED);
            Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.DTYPE);
            session.evaluate(expected, operand);
        }
    }

}
