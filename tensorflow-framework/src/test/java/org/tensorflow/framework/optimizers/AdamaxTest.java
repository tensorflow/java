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
package org.tensorflow.framework.optimizers;

import org.junit.jupiter.api.*;
import org.tensorflow.Tensor;
import org.tensorflow.framework.utils.ND;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tensorflow.framework.optimizers.Adamax.*;

/** Test cases for Adamax Optimizer */
public class AdamaxTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  private static final int VAR = 0;
  private static final int M = 1;
  private static final int V = 2;

  public AdamaxTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of getOptimizerName method, of class Adamax. */
  @Test
  public void testGetOptimizerName() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Adamax instance = new Adamax(session.getGraph())) {

      String expResult = DEFAULT_NAME;
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test of applyDense method, of class Adamax. */
  @Test
  public void testBasic() {

    int numSteps = 3;

    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {3.0F, 4.0F};
    float[] grads0Init = {0.1F, 0.1F};
    float[] grads1Init = {0.01F, 0.01F};

    float[] zeros = {0.0F, 0.0F};
    FloatNdArray m0 = NdArrays.vectorOf(zeros);
    FloatNdArray v0 = NdArrays.vectorOf(zeros);
    FloatNdArray m1 = NdArrays.vectorOf(zeros);
    FloatNdArray v1 = NdArrays.vectorOf(zeros);
    FloatNdArray var0Np = NdArrays.vectorOf(var0Init);
    FloatNdArray var1Np = NdArrays.vectorOf(var1Init);
    FloatNdArray grads0Np = NdArrays.vectorOf(grads0Init);
    FloatNdArray grads1Np = NdArrays.vectorOf(grads1Init);

    float epsilon1 = 1e-3F;

    try (TestSession session = TestSession.createTestSession(tfMode);
        Adamax instance = new Adamax(session.getGraph())) {
      Ops tf = session.getTF();

      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* build the GradsAnvVars */
      List<GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op update = instance.applyGradients(gradsAndVars, "AdamTest");

      /* Create and validae the shapes of the slota */
      @SuppressWarnings("unchecked")
      Variable<TFloat32>[] firstMomentSlots = new Variable[2];
      @SuppressWarnings("unchecked")
      Variable<TFloat32>[] secondMomentSlots = new Variable[2];

      firstMomentSlots[0] = instance.getSlot(var0.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

      secondMomentSlots[0] = instance.getSlot(var0.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

      firstMomentSlots[1] = instance.getSlot(var1.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

      secondMomentSlots[1] = instance.getSlot(var1.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

      /* initialize the accumulators */
      session.run(tf.init());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);
      session.setEpsilon(epsilon1);
      for (int step = 0; step < numSteps; step++) {
        // Test powers
        final float beta1Power = (float) Math.pow(BETA_ONE_DEFAULT, step + 1);

        try (Tensor<TFloat32> result =
            session
                .getGraphSession()
                .runner()
                .fetch("beta1_power")
                .run()
                .get(0)
                .expect(TFloat32.DTYPE)) {
          result.data().scalars().forEach(f -> assertEquals(beta1Power, f.getFloat(), epsilon1));
        }
        session.run(update, instance.getFeedMap());

        FloatNdArray[] resultNP = calculate(var0Np, grads0Np, step, m0, v0);
        var0Np = resultNP[VAR];
        m0 = resultNP[M];
        v0 = resultNP[V];

        resultNP = calculate(var1Np, grads1Np, step, m1, v1);
        var1Np = resultNP[VAR];
        m1 = resultNP[M];
        v1 = resultNP[V];

        // evaluate  var0 and var1

        session.evaluate(var0Np, var0);
        session.evaluate(var1Np, var1);
      }
    }
  }

  /** Test of applyDense method, of class Adamax. */
  @Test
  public void testBasicWithLROperand() {

    int numSteps = 3;

    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {3.0F, 4.0F};
    float[] grads0Init = {0.1F, 0.1F};
    float[] grads1Init = {0.01F, 0.01F};

    float[] zeros = {0.0F, 0.0F};
    FloatNdArray m0 = NdArrays.vectorOf(zeros);
    FloatNdArray v0 = NdArrays.vectorOf(zeros);
    FloatNdArray m1 = NdArrays.vectorOf(zeros);
    FloatNdArray v1 = NdArrays.vectorOf(zeros);
    FloatNdArray var0Np = NdArrays.vectorOf(var0Init);
    FloatNdArray var1Np = NdArrays.vectorOf(var1Init);
    FloatNdArray grads0Np = NdArrays.vectorOf(grads0Init);
    FloatNdArray grads1Np = NdArrays.vectorOf(grads1Init);

    float epsilon1 = 1e-3f;
    float learningRate = 1f;

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      try (Adamax instance =
          new Adamax(
              session.getGraph(), tf.math.mul(tf.constant(learningRate), tf.constant(1e-3f)))) {

        Shape shape0 = Shape.of(var0Init.length);
        Shape shape1 = Shape.of(var1Init.length);
        Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
        Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

        Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
        Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

        Constant<TFloat32> grads0 = tf.constant(grads0Init);
        Constant<TFloat32> grads1 = tf.constant(grads1Init);

        /* initialize the local variables */
        session.run(var0Initializer);
        session.run(var1Initializer);

        /* build the GradsAnvVars */
        List<GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
        gradsAndVars.add(new GradAndVar<>(grads0.asOutput(), var0.asOutput()));
        gradsAndVars.add(new GradAndVar<>(grads1.asOutput(), var1.asOutput()));

        Op update = instance.applyGradients(gradsAndVars, "AdamTest");

        /* Create and validae the shapes of the slota */
        @SuppressWarnings("unchecked")
        Variable<TFloat32>[] firstMomentSlots = new Variable[2];
        @SuppressWarnings("unchecked")
        Variable<TFloat32>[] secondMomentSlots = new Variable[2];

        firstMomentSlots[0] = instance.getSlot(var0.asOutput(), FIRST_MOMENT).get();
        assertEquals(firstMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

        secondMomentSlots[0] = instance.getSlot(var0.asOutput(), SECOND_MOMENT).get();
        assertEquals(secondMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

        firstMomentSlots[1] = instance.getSlot(var1.asOutput(), FIRST_MOMENT).get();
        assertEquals(firstMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

        secondMomentSlots[1] = instance.getSlot(var1.asOutput(), SECOND_MOMENT).get();
        assertEquals(secondMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

        /* initialize the accumulators */
        session.run(tf.init());

        /* initialize the local variables */
        session.run(var0Initializer);
        session.run(var1Initializer);
        session.setEpsilon(epsilon1);
        for (int step = 0; step < numSteps; step++) {
          // Test powers
          final float beta1Power = (float) Math.pow(BETA_ONE_DEFAULT, step + 1);

          try (Tensor<TFloat32> result =
              session
                  .getGraphSession()
                  .runner()
                  .fetch("beta1_power")
                  .run()
                  .get(0)
                  .expect(TFloat32.DTYPE)) {
            result.data().scalars().forEach(f -> assertEquals(beta1Power, f.getFloat(), epsilon1));
          }
          session.run(update, instance.getFeedMap());

          FloatNdArray[] resultNP = calculate(var0Np, grads0Np, step, m0, v0);
          var0Np = resultNP[VAR];
          m0 = resultNP[M];
          v0 = resultNP[V];

          resultNP = calculate(var1Np, grads1Np, step, m1, v1);
          var1Np = resultNP[VAR];
          m1 = resultNP[M];
          v1 = resultNP[V];

          // evaluate  var0 and var1

          session.evaluate(var0Np, var0);
          session.evaluate(var1Np, var1);
        }
      }
    }
  }

  @Test
  public void testWithLearningRateDecay() {

    float epsilon = 1e-6f;
    float epsilon1 = 1e-3F;
    int numSteps = 3;
    float learningRate = 0.001F;

    try (TestSession session = TestSession.createTestSession(tfMode);
        Adamax instance = new Adamax(session.getGraph(), learningRate)) {
      Ops tf = session.getTF();
      float[] zeros = {0.0F, 0.0F};
      FloatNdArray m0 = NdArrays.vectorOf(zeros);
      FloatNdArray v0 = NdArrays.vectorOf(zeros);
      FloatNdArray m1 = NdArrays.vectorOf(zeros);
      FloatNdArray v1 = NdArrays.vectorOf(zeros);
      float[] var0Init = {1.0F, 2.0F};
      float[] var1Init = {3.0F, 4.0F};
      float[] grads0Init = {0.1F, 0.1F};
      float[] grads1Init = {0.01F, 0.01F};
      FloatNdArray var0Np = NdArrays.vectorOf(var0Init);
      FloatNdArray var1Np = NdArrays.vectorOf(var1Init);
      FloatNdArray grads0Np = NdArrays.vectorOf(grads0Init);
      FloatNdArray grads1Np = NdArrays.vectorOf(grads1Init);
      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op update = instance.applyGradients(gradsAndVars, "AdamTest");

      /* Create and validae the shapes of the slota */
      @SuppressWarnings("unchecked")
      Variable<TFloat32>[] firstMomentSlots = new Variable[2];
      @SuppressWarnings("unchecked")
      Variable<TFloat32>[] secondMomentSlots = new Variable[2];

      firstMomentSlots[0] = instance.getSlot(var0.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

      secondMomentSlots[0] = instance.getSlot(var0.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[0].asOutput().shape(), var0.asOutput().shape());

      firstMomentSlots[1] = instance.getSlot(var1.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

      secondMomentSlots[1] = instance.getSlot(var1.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[1].asOutput().shape(), var1.asOutput().shape());

      /* initialize the accumulators */
      session.run(tf.init());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);
      session.setEpsilon(epsilon1);
      for (int step = 0; step < numSteps; step++) {
        final float betaPower = (float) Math.pow(BETA_ONE_DEFAULT, step + 1);

        try (Tensor<TFloat32> result =
            session
                .getGraphSession()
                .runner()
                .fetch("beta1_power")
                .run()
                .get(0)
                .expect(TFloat32.DTYPE)) {
          result.data().scalars().forEach(f -> assertEquals(betaPower, f.getFloat(), epsilon1));
        }
        assertEquals(learningRate, instance.getLearningRate(), epsilon);
        session.evaluate(
            learningRate, tf.identity(instance.getLearningRateOperand()), instance.getFeedMap());
        session.run(update, instance.getFeedMap());

        FloatNdArray[] resultNP = calculate(var0Np, grads0Np, step, m0, v0, learningRate);
        var0Np = resultNP[VAR];
        m0 = resultNP[M];
        v0 = resultNP[V];

        resultNP = calculate(var1Np, grads1Np, step, m1, v1, learningRate);
        var1Np = resultNP[VAR];
        m1 = resultNP[M];
        v1 = resultNP[V];

        // evaluate  var0 and var1
        session.evaluate(var0Np, var0);
        session.evaluate(var1Np, var1);

        learningRate *= 0.9F;
        instance.setLearningRate(learningRate);
      }
    }
  }

  private FloatNdArray[] calculate(
      FloatNdArray varNp, FloatNdArray gradsNp, int step, FloatNdArray m, FloatNdArray v) {
    return calculate(varNp, gradsNp, step, m, v, 0.001F);
  }

  private FloatNdArray[] calculate(
      FloatNdArray varNp,
      FloatNdArray gradsNp,
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
    m = ND.add(ND.mul(beta1, m), ND.mul(oneMinusBeta1, gradsNp));
    // np.maximum(beta2 * v, np.abs(g_t))
    v = ND.max(ND.mul(beta2, v), ND.abs(gradsNp));
    // param_t = param - (alpha / (1 - beta1**(t + 1))) * (m_t / (v_t + epsilon))
    varNp = ND.sub(varNp, ND.mul(alpha1, ND.div(m, ND.add(v, espilon))));

    FloatNdArray[] result = new FloatNdArray[3];
    result[VAR] = varNp;
    result[M] = m;
    result[V] = v;
    return result;
  }
}
