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
package org.tensorflow.keras.optimizers;

import org.junit.jupiter.api.*;
import org.tensorflow.framework.optimizers.Optimizer;
import org.tensorflow.keras.utils.ND;
import org.tensorflow.keras.utils.TestSession;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tensorflow.framework.optimizers.AdaGrad.ACCUMULATOR;
import static org.tensorflow.keras.optimizers.AdaGrad.INITIAL_ACCUM_KEY;
import static org.tensorflow.keras.optimizers.AdaGrad.LEARNING_RATE_KEY;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;

/** Test cases for AdaGrad Optimizer */
public class AdaGradTest {

  private TestSession.Mode tf_mode = TestSession.Mode.GRAPH;
  int index;

  public AdaGradTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of create method, of class AdaGrad. */
  @Test
  public void testCreate() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      Map<String, Object> config = new HashMap<>();
      config.put(NAME_KEY, "AdaDelta");
      config.put(LEARNING_RATE_KEY, 2.0F);
      config.put(INITIAL_ACCUM_KEY, 0.1F);
      AdaGrad expResult = new AdaGrad(tf, 2.0F, 0.1F);
      AdaGrad result = AdaGrad.create(tf, config);
      assertEquals(expResult.getConfig(), result.getConfig());
    }
  }

  @Test
  public void testBasic() {
    int numSteps = 3;
    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};
    float epsilon = 1e-8F;
    float epsilon1 = 1e-5F;
    float[] accum0 = {0.1f, 0.1f};
    float[] accum1 = {0.1f, 0.1f};

    FloatNdArray var0_np = NdArrays.vectorOf(var0_init);
    FloatNdArray var1_np = NdArrays.vectorOf(var1_init);
    FloatNdArray grads0_np = NdArrays.vectorOf(grads0_init);
    FloatNdArray grads1_np = NdArrays.vectorOf(grads1_init);
    FloatNdArray accum0_np = NdArrays.vectorOf(accum0);
    FloatNdArray accum1_np = NdArrays.vectorOf(accum1);

    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();

      Shape shape0 = Shape.of(var0_init.length);
      Shape shape1 = Shape.of(var1_init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0_init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1_init));

      Constant<TFloat32> grads0 = tf.constant(grads0_init);
      Constant<TFloat32> grads1 = tf.constant(grads1_init);

      float learningRate = 3.0F;

      AdaGrad instance = new AdaGrad(tf, learningRate);

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op ada_update = instance.applyGradients(gradsAndVars, "AdGradTest");

      Variable<TFloat32>[] accumulatorSlots = new Variable[2];
      accumulatorSlots[0] = instance.getSlot(var0.asOutput(), ACCUMULATOR).get();
      assertEquals(accumulatorSlots[0].asOutput().shape(), var0.asOutput().shape());

      accumulatorSlots[1] = instance.getSlot(var1.asOutput(), ACCUMULATOR).get();
      assertEquals(accumulatorSlots[1].asOutput().shape(), var1.asOutput().shape());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /** initialize the accumulators */
      session.run(tf.init());

      /** make sure the variables were initialized properly */
      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      for (int step = 0; step < numSteps; step++) {
        session.run(ada_update, instance.getFeedDict());

        accum0_np = caclulateAccum(accum0_np, grads0_np);
        var0_np = calculate(var0_np, accum0_np, grads0_np, learningRate);
        session.evaluate(var0_np, var0);

        accum1_np = caclulateAccum(accum1_np, grads1_np);
        var1_np = calculate(var1_np, accum1_np, grads1_np, learningRate);
        session.evaluate(var1_np, var1);
      }
    }
  }

  @Test
  public void testWithLearningRateDecay() {
    int numSteps = 3;
    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};
    float epsilon = 1e-8F;
    float epsilon1 = 1e-5F;
    float[] accum0 = {0.1f, 0.1f};
    float[] accum1 = {0.1f, 0.1f};

    FloatNdArray var0_np = NdArrays.vectorOf(var0_init);
    FloatNdArray var1_np = NdArrays.vectorOf(var1_init);
    FloatNdArray grads0_np = NdArrays.vectorOf(grads0_init);
    FloatNdArray grads1_np = NdArrays.vectorOf(grads1_init);
    FloatNdArray accum0_np = NdArrays.vectorOf(accum0);
    FloatNdArray accum1_np = NdArrays.vectorOf(accum1);

    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();

      Shape shape0 = Shape.of(var0_init.length);
      Shape shape1 = Shape.of(var1_init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0_init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1_init));

      Constant<TFloat32> grads0 = tf.constant(grads0_init);
      Constant<TFloat32> grads1 = tf.constant(grads1_init);

      float learningRate = 3.0F;

      AdaGrad instance = new AdaGrad(tf, learningRate);

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op ada_update = instance.applyGradients(gradsAndVars, "AdGradTest");

      Variable<TFloat32>[] accumulatorSlots = new Variable[2];
      accumulatorSlots[0] = instance.getSlot(var0.asOutput(), ACCUMULATOR).get();
      assertEquals(accumulatorSlots[0].asOutput().shape(), var0.asOutput().shape());

      accumulatorSlots[1] = instance.getSlot(var1.asOutput(), ACCUMULATOR).get();
      assertEquals(accumulatorSlots[1].asOutput().shape(), var1.asOutput().shape());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /** initialize the accumulators */
      session.run(tf.init());

      /** make sure the variables were initialized properly */
      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      for (int step = 0; step < numSteps; step++) {
        session.run(ada_update, instance.getFeedDict());

        accum0_np = caclulateAccum(accum0_np, grads0_np);
        var0_np = calculate(var0_np, accum0_np, grads0_np, learningRate);
        session.evaluate(var0_np, var0);

        accum1_np = caclulateAccum(accum1_np, grads1_np);
        var1_np = calculate(var1_np, accum1_np, grads1_np, learningRate);
        session.evaluate(var1_np, var1);

        learningRate *= 0.9;
        instance.setLearningRate(learningRate);
      }
    }
  }

  private FloatNdArray caclulateAccum(FloatNdArray accum, FloatNdArray grads) {
    // accum + g_t * g_t
    FloatNdArray squareG = ND.square(grads);
    FloatNdArray result = ND.add(accum, squareG);
    return result;
  }

  private FloatNdArray calculate(
      FloatNdArray param, FloatNdArray accum, FloatNdArray grads, float learningRate) {
    // param - lr * g_t / (np.sqrt(accum_t) + epsilon)
    FloatNdArray divisor = ND.add(ND.sqrt(accum), 1e-07f);
    FloatNdArray dividend = ND.mul(learningRate, grads);
    FloatNdArray quotient = ND.div(dividend, divisor);
    FloatNdArray result = ND.sub(param, quotient);
    return result;
  }
}
