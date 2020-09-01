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
import org.tensorflow.Tensor;
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
import static org.tensorflow.keras.optimizers.Adamax.*;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;

/** Test cases for Adamax Optimizer */
public class AdamaxTest {

  private TestSession.Mode tf_mode = TestSession.Mode.GRAPH;

  private static final int VAR = 0;
  private static final int M = 1;
  private static final int V = 2;

  int index;

  public AdamaxTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of create method, of class Adamax. */
  @Test
  public void testCreate() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      Map<String, Object> config = new HashMap<>();
      config.put(NAME_KEY, "AdaDelta");
      config.put(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
      config.put(BETA_ONE_KEY, BETA_ONE_DEFAULT);
      config.put(BETA_TWO_KEY, BETA_TWO_DEFAULT);
      config.put(EPSILON_KEY, EPSILON_DEFAULT);
      AdaDelta expResult = new AdaDelta(tf);
      AdaDelta result = AdaDelta.create(tf, config);
      assertEquals(expResult.getConfig(), result.getConfig());
    }
  }

  /** Test of getOptimizerName method, of class Adamax. */
  @Test
  public void testGetOptimizerName() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      Adamax instance = new Adamax(tf);
      String expResult = "Adamax";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test of applyDense method, of class Adamax. */
  @Test
  public void testBasic() {

    int numSteps = 3;

    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};

    float[] zeros = {0.0F, 0.0F};
    FloatNdArray m0 = NdArrays.vectorOf(zeros);
    FloatNdArray v0 = NdArrays.vectorOf(zeros);
    FloatNdArray m1 = NdArrays.vectorOf(zeros);
    FloatNdArray v1 = NdArrays.vectorOf(zeros);
    FloatNdArray var0_np = NdArrays.vectorOf(var0_init);
    FloatNdArray var1_np = NdArrays.vectorOf(var1_init);
    FloatNdArray grads0_np = NdArrays.vectorOf(grads0_init);
    FloatNdArray grads1_np = NdArrays.vectorOf(grads1_init);

    float epsilon = 1e-6f;
    float epsilon1 = 1e-3F;

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

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      Adamax instance = new Adamax(tf);
      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op update = instance.applyGradients(gradsAndVars, "AdamTest");

      /* Create and validae the shapes of the slota */
      Variable<TFloat32>[] firstMomentSlots = new Variable[2];
      Variable<TFloat32>[] secondMomentSlots = new Variable[2];

      firstMomentSlots[0] = instance.getSlot(var0.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

      secondMomentSlots[0] = instance.getSlot(var0.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

      firstMomentSlots[1] = instance.getSlot(var1.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

      secondMomentSlots[1] = instance.getSlot(var1.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

      /** initialize the accumulators */
      session.run(tf.init());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);
      session.setEpsilon(epsilon1);
      for (int step = 0; step < numSteps; step++) {
        // Test powers
        final float beta1_power = (float) Math.pow(BETA_ONE_DEFAULT, step + 1);

        try (Tensor<TFloat32> result =
            session
                .getGraphSession()
                .runner()
                .fetch("beta1_power")
                .run()
                .get(0)
                .expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    assertEquals(beta1_power, f.getFloat(), epsilon1);
                  });
        }
        session.run(update, instance.getFeedDict());

        FloatNdArray[] resultNP = calculate(var0_np, grads0_np, step, m0, v0);
        var0_np = resultNP[VAR];
        m0 = resultNP[M];
        v0 = resultNP[V];

        resultNP = calculate(var1_np, grads1_np, step, m1, v1);
        var1_np = resultNP[VAR];
        m1 = resultNP[M];
        v1 = resultNP[V];

        // evaluate  var0 and var1
        session.evaluate(var0_np, var0);
        session.evaluate(var1_np, var1);
      }
    }
  }

  @Test
  public void testWithLearningRateDecay() {

    float epsilon = 1e-6f;
    float epsilon1 = 1e-3F;
    int numSteps = 3;

    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      float[] zeros = {0.0F, 0.0F};
      FloatNdArray m0 = NdArrays.vectorOf(zeros);
      FloatNdArray v0 = NdArrays.vectorOf(zeros);
      FloatNdArray m1 = NdArrays.vectorOf(zeros);
      FloatNdArray v1 = NdArrays.vectorOf(zeros);
      float[] var0_init = {1.0F, 2.0F};
      float[] var1_init = {3.0F, 4.0F};
      float[] grads0_init = {0.1F, 0.1F};
      float[] grads1_init = {0.01F, 0.01F};
      FloatNdArray var0_np = NdArrays.vectorOf(var0_init);
      FloatNdArray var1_np = NdArrays.vectorOf(var1_init);
      FloatNdArray grads0_np = NdArrays.vectorOf(grads0_init);
      FloatNdArray grads1_np = NdArrays.vectorOf(grads1_init);
      Shape shape0 = Shape.of(var0_init.length);
      Shape shape1 = Shape.of(var1_init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0_init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1_init));

      Constant<TFloat32> grads0 = tf.constant(grads0_init);
      Constant<TFloat32> grads1 = tf.constant(grads1_init);

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);
      float learningRate = 0.001F;

      Adamax instance = new Adamax(tf, learningRate);
      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op update = instance.applyGradients(gradsAndVars, "AdamTest");

      /* Create and validae the shapes of the slota */
      Variable<TFloat32>[] firstMomentSlots = new Variable[2];
      Variable<TFloat32>[] secondMomentSlots = new Variable[2];

      firstMomentSlots[0] = instance.getSlot(var0.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

      secondMomentSlots[0] = instance.getSlot(var0.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

      firstMomentSlots[1] = instance.getSlot(var1.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

      secondMomentSlots[1] = instance.getSlot(var1.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

      /** initialize the accumulators */
      session.run(tf.init());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);
      session.setEpsilon(epsilon1);
      for (int step = 0; step < numSteps; step++) {
        // Test powers
        final float beta1_power = (float) Math.pow(BETA_ONE_DEFAULT, step + 1);

        try (Tensor<TFloat32> result =
            session
                .getGraphSession()
                .runner()
                .fetch("beta1_power")
                .run()
                .get(0)
                .expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    assertEquals(beta1_power, f.getFloat(), epsilon1);
                  });
        }
        session.run(update, instance.getFeedDict());

        FloatNdArray[] resultNP = calculate(var0_np, grads0_np, step, m0, v0, learningRate);
        var0_np = resultNP[VAR];
        m0 = resultNP[M];
        v0 = resultNP[V];

        resultNP = calculate(var1_np, grads1_np, step, m1, v1, learningRate);
        var1_np = resultNP[VAR];
        m1 = resultNP[M];
        v1 = resultNP[V];

        // evaluate  var0 and var1
        session.evaluate(var0_np, var0);
        session.evaluate(var1_np, var1);

        learningRate *= 0.9F;
        instance.setLearningRate(learningRate);
      }
    }
  }

  private FloatNdArray[] calculate(
      FloatNdArray var_np, FloatNdArray grads_np, int step, FloatNdArray m, FloatNdArray v) {
    return calculate(var_np, grads_np, step, m, v, 0.001F);
  }

  private FloatNdArray[] calculate(
      FloatNdArray var_np,
      FloatNdArray grads_np,
      int step,
      FloatNdArray m,
      FloatNdArray v,
      float alpha) {
    float beta1 = BETA_ONE_DEFAULT;
    float beta2 = BETA_TWO_DEFAULT;
    float espilon = 1e-8F;

    float oneMinusBeta1 = 1.F - beta1;
    float oneMinusBeta1Pow = 1.F - (float) Math.pow(beta1, step + 1);
    float alpha1 = alpha / oneMinusBeta1Pow;

    // beta1 * m + (1 - beta1) * g_t;
    m = ND.add(ND.mul(beta1, m), ND.mul(oneMinusBeta1, grads_np));
    // np.maximum(beta2 * v, np.abs(g_t))
    v = ND.max(ND.mul(beta2, v), ND.abs(grads_np));
    // param_t = param - (alpha / (1 - beta1**(t + 1))) * (m_t / (v_t + epsilon))
    var_np = ND.sub(var_np, ND.mul(alpha1, ND.div(m, ND.add(v, espilon))));

    FloatNdArray[] result = new FloatNdArray[3];
    result[VAR] = var_np;
    result[M] = m;
    result[V] = v;
    return result;
  }
}
