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
import static org.tensorflow.keras.optimizers.Adamax.LEARNING_RATE_KEY;
import static org.tensorflow.keras.optimizers.Nadam.*;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;

/** Test cases for Nadam Optimizer */
public class NadamTest {
  private TestSession.Mode tf_mode = TestSession.Mode.GRAPH;

  private static final int VAR = 0;
  private static final int M = 1;
  private static final int V = 2;

  int index = 0;
  float momentum = 1;

  public NadamTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of create method, of class Nadam. */
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

  /** Test of getOptimizerName method, of class Nadam. */
  @Test
  public void testGetOptimizerName() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      Nadam instance = new Nadam(tf);
      String expResult = "Nadam";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test of applyDense method, of class Nadam. */
  @Test
  public void testBasic() {

    int numSteps = 3;

    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};

    float[] zeros = {0.0F, 0.0F};
    float[] ones = {1.0F, 1.0F};
    FloatNdArray m0 = NdArrays.vectorOf(zeros);
    FloatNdArray v0 = NdArrays.vectorOf(zeros);
    FloatNdArray m1 = NdArrays.vectorOf(zeros);
    FloatNdArray v1 = NdArrays.vectorOf(zeros);
    FloatNdArray mcache = NdArrays.vectorOf(ones);
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

      Nadam instance = new Nadam(tf);
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

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /** initialize the accumulators */
      session.run(tf.init());

      session.setEpsilon(epsilon1);

      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      try (Tensor<TFloat32> result =
          session
              .getGraphSession()
              .runner()
              .fetch("momentum")
              .run()
              .get(0)
              .expect(TFloat32.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(1F, f.getFloat(), epsilon1);
                });
      }
      momentum = 1F;

      for (int step = 0; step < numSteps; step++) {

        session.run(update, instance.getFeedDict());

        float mut =
            Nadam.BETA_ONE_DEFAULT * (1F - 0.5F * (float) Math.pow(0.96F, (0.004F * (step + 1))));
        momentum = momentum * mut;

        try (Tensor<TFloat32> result =
            session
                .getGraphSession()
                .runner()
                .fetch("momentum")
                .run()
                .get(0)
                .expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    assertEquals(momentum, f.getFloat(), epsilon1);
                  });
        }
        mcache = ND.mul(mcache, momentum);
        FloatNdArray[] resultsNP = nadam_update_numpy(var0_np, grads0_np, step, m0, v0, mcache);
        var0_np = resultsNP[VAR];
        m0 = resultsNP[M];
        v0 = resultsNP[V];

        resultsNP = nadam_update_numpy(var1_np, grads1_np, step, m1, v1, mcache);
        var1_np = resultsNP[VAR];
        m1 = resultsNP[M];
        v1 = resultsNP[V];

        // evaluate m0 and m1
        session.evaluate(m0, firstMomentSlots[0]);
        session.evaluate(m1, firstMomentSlots[1]);

        // evaluate v0 and v1
        session.evaluate(v0, secondMomentSlots[0]);
        session.evaluate(v1, secondMomentSlots[1]);

        // evaluate var0 and var1
        session.evaluate(var0_np, var0);
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

    float[] zeros = {0.0F, 0.0F};
    float[] ones = {1.0F, 1.0F};
    FloatNdArray m0 = NdArrays.vectorOf(zeros);
    FloatNdArray v0 = NdArrays.vectorOf(zeros);
    FloatNdArray m1 = NdArrays.vectorOf(zeros);
    FloatNdArray v1 = NdArrays.vectorOf(zeros);
    FloatNdArray mcache = NdArrays.vectorOf(ones);
    FloatNdArray var0_np = NdArrays.vectorOf(var0_init);
    FloatNdArray var1_np = NdArrays.vectorOf(var1_init);
    FloatNdArray grads0_np = NdArrays.vectorOf(grads0_init);
    FloatNdArray grads1_np = NdArrays.vectorOf(grads1_init);

    float epsilon = 1e-6f;
    float epsilon1 = 1e-3F;

    float learningRate = 0.001F;

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

      Nadam instance = new Nadam(tf, learningRate);
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

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /** initialize the accumulators */
      session.run(tf.init());

      session.setEpsilon(epsilon1);

      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      try (Tensor<TFloat32> result =
          session
              .getGraphSession()
              .runner()
              .fetch("momentum")
              .run()
              .get(0)
              .expect(TFloat32.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(1F, f.getFloat(), epsilon1);
                });
      }
      momentum = 1F;

      for (int step = 0; step < numSteps; step++) {

        session.run(update, instance.getFeedDict());

        float mut =
            Nadam.BETA_ONE_DEFAULT * (1F - 0.5F * (float) Math.pow(0.96F, (0.004F * (step + 1))));
        momentum = momentum * mut;

        try (Tensor<TFloat32> result =
            session
                .getGraphSession()
                .runner()
                .fetch("momentum")
                .run()
                .get(0)
                .expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    assertEquals(momentum, f.getFloat(), epsilon1);
                  });
        }
        mcache = ND.mul(mcache, momentum);
        FloatNdArray[] resultsNP =
            nadam_update_numpy(var0_np, grads0_np, step, m0, v0, mcache, learningRate);
        var0_np = resultsNP[VAR];
        m0 = resultsNP[M];
        v0 = resultsNP[V];

        resultsNP = nadam_update_numpy(var1_np, grads1_np, step, m1, v1, mcache, learningRate);
        var1_np = resultsNP[VAR];
        m1 = resultsNP[M];
        v1 = resultsNP[V];

        // evaluate m0 and m1
        session.evaluate(m0, firstMomentSlots[0]);
        session.evaluate(m1, firstMomentSlots[1]);

        // evaluate v0 and v1
        session.evaluate(v0, secondMomentSlots[0]);
        session.evaluate(v1, secondMomentSlots[1]);

        // evaluate var0 and var1
        session.evaluate(var0_np, var0);
        session.evaluate(var1_np, var1);

        learningRate *= 0.9;
        instance.setLearningRate(learningRate);
      }
    }
  }

  private FloatNdArray update_m_cache(FloatNdArray mcache, int t) {
    float mu_t = 0.9F * (1.0F - 0.5F * (float) Math.pow(0.96, (0.004 * (t + 1))));
    return ND.mul(mu_t, mcache);
  }

  private FloatNdArray[] nadam_update_numpy(
      FloatNdArray var_np,
      FloatNdArray grads_np,
      int t,
      FloatNdArray m,
      FloatNdArray v,
      FloatNdArray m_cache) {
    return nadam_update_numpy(var_np, grads_np, t, m, v, m_cache, 0.001F);
  }

  private FloatNdArray[] nadam_update_numpy(
      FloatNdArray var_np,
      FloatNdArray grads_np,
      int t,
      FloatNdArray m,
      FloatNdArray v,
      FloatNdArray m_cache,
      float alpha) {

    float beta1 = 0.9F;
    float beta2 = 0.999F;
    float epsilon = 1e-8F;
    float mu_t = beta1 * (1F - 0.5F * (float) Math.pow(0.96, 0.004 * (t + 1)));
    float mu_t_1 = beta1 * (1F - 0.5F * (float) Math.pow(0.96, (0.004 * (t + 2))));
    FloatNdArray m_cache_t_1 = ND.mul(m_cache, mu_t_1);
    FloatNdArray g_prime_t = ND.div(grads_np, ND.sub(1.0F, m_cache));
    FloatNdArray m_t = ND.add(ND.mul(beta1, m), ND.mul((1 - beta1), grads_np));
    FloatNdArray v_t = ND.add(ND.mul(beta2, v), ND.mul((1 - beta2), ND.square(grads_np)));

    FloatNdArray m_prime_t = ND.div(m_t, ND.sub(1.F, m_cache_t_1));
    FloatNdArray v_prime_t = ND.div(v_t, 1.F - (float) Math.pow(beta2, t + 1));
    FloatNdArray m_bar_t = ND.add(ND.mul((1 - mu_t), g_prime_t), ND.mul(mu_t_1, m_prime_t));
    FloatNdArray param_t =
        ND.sub(var_np, ND.div(ND.mul(alpha, m_bar_t), ND.add(ND.sqrt(v_prime_t), epsilon)));

    FloatNdArray[] results = new FloatNdArray[3];
    results[VAR] = param_t;
    results[M] = m_t;
    results[V] = v_t;
    return results;
  }
}
