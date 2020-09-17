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
 * Test the LeCunNormal initializer
 */
public class LeCunNormalTest {

    private final TestSession.Mode tfMode = TestSession.Mode.EAGER;

    private static final long SEED = 1000L;

    public LeCunNormalTest() {
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
     * Test of call method, of class LeCunNormal.
     */
    @Test
    public void testCall_Float() {
        float[] expected = {-0.52388954f, -0.29329166f, -0.07872587f, -0.31851602f};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);
            LeCunNormal<TFloat32, TFloat32> instance
                    = new LeCunNormal<>(tf, SEED);
            Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.DTYPE);
            session.evaluate(expected, operand);
        }
    }

    @Test
    public void testCall_Double() {
        double[] expected = {1.4971264721246893, -1.2488522307109322, -0.5409677352523339, 0.4871390504288623};
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape shape = Shape.of(2, 2);
            LeCunNormal<TFloat64, TFloat64> instance
                    = new LeCunNormal<>(tf, SEED);
            Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.DTYPE);
            session.evaluate(expected, operand);
        }
    }

}
