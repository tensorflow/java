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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

/** Test the LeCunUniform initializer */
public class LeCunUniformTest {

  private final TestSession.Mode tfMode = TestSession.Mode.EAGER;

  private static final long SEED = 1000L;

  public LeCunUniformTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of call method, of class LeCunUniform. */
  @Test
  public void testCall_Float() {
    float[] expected = {0.9266439F, 0.8190767F, 1.1268647F, 0.6596042F};
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(2, 2);
      LeCunUniform<TFloat32, TFloat32> instance = new LeCunUniform<>(tf, SEED);
      Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.DTYPE);
      session.evaluate(expected, operand);
    }
  }

  @Test
  public void testCall_Double() {
    double[] expected = {
      0.06468193804916589, 0.44170328686673477, 0.06711059208157763, 0.6278720842445181
    };
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Shape shape = Shape.of(2, 2);
      LeCunUniform<TFloat64, TFloat64> instance = new LeCunUniform<>(tf, SEED);
      Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.DTYPE);
      session.evaluate(expected, operand);
    }
  }
}
