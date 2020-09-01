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
import static org.tensorflow.framework.optimizers.Adam.FIRST_MOMENT;
import static org.tensorflow.framework.optimizers.Adam.SECOND_MOMENT;
import static org.tensorflow.keras.optimizers.Adam.*;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;

/** Test cases for Adam Optimizer */
public class AdamTest {

  private TestSession.Mode tf_mode = TestSession.Mode.GRAPH;

  int index;

  public AdamTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of create method, of class Adam. */
  @Test
  public void testCreate() {
    try (TestSession testSession = TestSession.createTestSession(tf_mode)) {
      Ops tf = testSession.getTF();
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

  @Test
  public void testBasic() {
    float m0 = 0.0F;
    float v0 = 0.0F;
    float m1 = 0.0F;
    float v1 = 0.0F;
    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};
    FloatNdArray var0_np = NdArrays.vectorOf(var0_init);
    FloatNdArray var1_np = NdArrays.vectorOf(var1_init);
    FloatNdArray grads0_np = NdArrays.vectorOf(grads0_init);
    FloatNdArray grads1_np = NdArrays.vectorOf(grads1_init);

    float epsilon1 = 1e-3F;

    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();

      session.setEpsilon(epsilon1);

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
      float beta1 = 0.9F;
      float beta2 = 0.999F;
      float epsilon = 1e-8F;

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Adam instance = new Adam(tf, learningRate);

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

      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      FloatNdArray m0_np = NdArrays.ofFloats(shape1);
      FloatNdArray v0_np = NdArrays.ofFloats(shape1);
      FloatNdArray m1_np = NdArrays.ofFloats(shape1);
      FloatNdArray v1_np = NdArrays.ofFloats(shape1);

      for (int step = 0; step < 3; step++) {

        // Test powers
        final float[] powers = {
          (float) Math.pow(beta1, step + 1), (float) Math.pow(beta2, step + 1)
        };

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
                    assertEquals(powers[0], f.getFloat(), epsilon1);
                  });
        }
        try (Tensor<TFloat32> result =
            session
                .getGraphSession()
                .runner()
                .fetch("beta2_power")
                .run()
                .get(0)
                .expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    assertEquals(powers[1], f.getFloat(), epsilon1);
                  });
        }
        session.run(update, instance.getFeedDict());

        float lr_t =
            learningRate
                * (float) Math.sqrt(1 - (float) Math.pow(beta2, (step + 1)))
                / (1 - (float) Math.pow(beta1, (step + 1)));

        m0_np = calculateM(m0_np, grads0_np, beta1);
        v0_np = calculateV(v0_np, grads0_np, beta2);
        var0_np = calculateParam(var0_np, lr_t, m0_np, v0_np, 1e-7F);

        m1_np = calculateM(m1_np, grads1_np, beta1);
        v1_np = calculateV(v1_np, grads1_np, beta2);
        var1_np = calculateParam(var1_np, lr_t, m1_np, v1_np, 1e-7F);

        // evaluate var 0 and var1
        session.evaluate(var0_np, var0);
        session.evaluate(var1_np, var1);

        // first moment
        session.evaluate(m0_np, firstMomentSlots[0]);
        session.evaluate(m1_np, firstMomentSlots[1]);

        // second moment
        session.evaluate(v0_np, secondMomentSlots[0]);
        session.evaluate(v1_np, secondMomentSlots[1]);
      }
    }
  }

  @Test
  public void testWithLearningRateDecay() {
    float m0 = 0.0F;
    float v0 = 0.0F;
    float m1 = 0.0F;
    float v1 = 0.0F;
    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};
    FloatNdArray var0_np = NdArrays.vectorOf(var0_init);
    FloatNdArray var1_np = NdArrays.vectorOf(var1_init);
    FloatNdArray grads0_np = NdArrays.vectorOf(grads0_init);
    FloatNdArray grads1_np = NdArrays.vectorOf(grads1_init);

    float epsilon1 = 1e-3F;

    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();

      session.setEpsilon(epsilon1);

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
      float beta1 = 0.9F;
      float beta2 = 0.999F;
      float epsilon = 1e-8F;

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Adam instance = new Adam(tf, learningRate);

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

      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      FloatNdArray m0_np = NdArrays.ofFloats(shape1);
      FloatNdArray v0_np = NdArrays.ofFloats(shape1);
      FloatNdArray m1_np = NdArrays.ofFloats(shape1);
      FloatNdArray v1_np = NdArrays.ofFloats(shape1);

      for (int step = 0; step < 3; step++) {

        // Test powers
        final float[] powers = {
          (float) Math.pow(beta1, step + 1), (float) Math.pow(beta2, step + 1)
        };

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
                    assertEquals(powers[0], f.getFloat(), epsilon1);
                  });
        }
        try (Tensor<TFloat32> result =
            session
                .getGraphSession()
                .runner()
                .fetch("beta2_power")
                .run()
                .get(0)
                .expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    assertEquals(powers[1], f.getFloat(), epsilon1);
                  });
        }
        session.run(update, instance.getFeedDict());

        float lr_t =
            learningRate
                * (float) Math.sqrt(1 - (float) Math.pow(beta2, (step + 1)))
                / (1 - (float) Math.pow(beta1, (step + 1)));

        m0_np = calculateM(m0_np, grads0_np, beta1);
        v0_np = calculateV(v0_np, grads0_np, beta2);
        var0_np = calculateParam(var0_np, lr_t, m0_np, v0_np, 1e-7F);

        m1_np = calculateM(m1_np, grads1_np, beta1);
        v1_np = calculateV(v1_np, grads1_np, beta2);
        var1_np = calculateParam(var1_np, lr_t, m1_np, v1_np, 1e-7F);

        // evaluate var 0 and var1
        session.evaluate(var0_np, var0);
        session.evaluate(var1_np, var1);

        // first moment
        session.evaluate(m0_np, firstMomentSlots[0]);
        session.evaluate(m1_np, firstMomentSlots[1]);

        // second moment
        session.evaluate(v0_np, secondMomentSlots[0]);
        session.evaluate(v1_np, secondMomentSlots[1]);

        learningRate *= 0.9;
        instance.setLearningRate(learningRate);
      }
    }
  }

  private FloatNdArray calculateM(FloatNdArray m, FloatNdArray g_t, float beta) {
    // m_t = beta1 * m + (1 - beta1) * g_t
    return ND.add(ND.mul(m, beta), ND.mul(g_t, (1 - beta)));
  }

  private FloatNdArray calculateV(FloatNdArray v, FloatNdArray g_t, float beta) {
    // beta2 * v + (1 - beta2) * g_t * g_t
    FloatNdArray mul1 = ND.mul(v, beta);
    FloatNdArray squareG = ND.square(g_t);
    FloatNdArray mul2 = ND.mul((1 - beta), squareG);
    FloatNdArray add = ND.add(mul1, mul2);
    return add;

    // return ND.add(ND.mul(v, beta),
    //     ND.mul((1-beta), ND.square(g_t)));
  }

  private FloatNdArray calculateParam(
      FloatNdArray param, float lr_t, FloatNdArray m, FloatNdArray v, float epsilon) {
    //  param - lr_t * m_t / (np.sqrt(v_t) + epsilon)
    FloatNdArray sqrt = ND.sqrt(v);
    FloatNdArray divisor = ND.add(sqrt, epsilon);
    FloatNdArray dividend = ND.mul(lr_t, m);
    FloatNdArray quotient = ND.div(dividend, divisor);
    FloatNdArray result = ND.sub(param, quotient);
    return result;
  }
}
