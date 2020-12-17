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
import org.tensorflow.Graph;
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

/** Test cases for Nadam Optimizer */
public class NadamTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  private static final int VAR = 0;
  private static final int M = 1;
  private static final int V = 2;

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

  /** Test of getOptimizerName method, of class Nadam. */
  @Test
  public void testGetOptimizerName() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Nadam instance = new Nadam(graph);
      String expResult = "Nadam";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test of applyDense method, of class Nadam. */
  @Test
  public void testBasic() {

    int numSteps = 3;

    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {3.0F, 4.0F};
    float[] grads0Init = {0.1F, 0.1F};
    float[] grads1Init = {0.01F, 0.01F};

    float[] zeros = {0.0F, 0.0F};
    float[] ones = {1.0F, 1.0F};
    FloatNdArray m0 = NdArrays.vectorOf(zeros);
    FloatNdArray v0 = NdArrays.vectorOf(zeros);
    FloatNdArray m1 = NdArrays.vectorOf(zeros);
    FloatNdArray v1 = NdArrays.vectorOf(zeros);
    FloatNdArray mcache = NdArrays.vectorOf(ones);
    FloatNdArray var0Np = NdArrays.vectorOf(var0Init);
    FloatNdArray var1Np = NdArrays.vectorOf(var1Init);
    FloatNdArray grads0Np = NdArrays.vectorOf(grads0Init);
    FloatNdArray grads1Np = NdArrays.vectorOf(grads1Init);

    float epsilon1 = 1e-3F;

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Graph graph = session.getGraph();

      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      Nadam instance = new Nadam(graph);
      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op update = instance.applyGradients(gradsAndVars, "AdamTest");

      /* Create and validae the shapes of the slota */
      Variable<TFloat32>[] firstMomentSlots = new Variable[2];
      Variable<TFloat32>[] secondMomentSlots = new Variable[2];

      firstMomentSlots[0] = instance.getSlot(var0.asOutput(), Nadam.FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[0].shape(), var0.shape());

      secondMomentSlots[0] = instance.getSlot(var0.asOutput(), Nadam.SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[0].shape(), var0.shape());

      firstMomentSlots[1] = instance.getSlot(var1.asOutput(), Nadam.FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[1].shape(), var1.shape());

      secondMomentSlots[1] = instance.getSlot(var1.asOutput(), Nadam.SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[1].shape(), var1.shape());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      session.setEpsilon(epsilon1);

      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      try (TFloat32 result =
          (TFloat32)session
              .getGraphSession()
              .runner()
              .fetch("momentum")
              .run()
              .get(0)) {
        result.scalars().forEach(f -> assertEquals(1F, f.getFloat(), epsilon1));
      }
      momentum = 1F;

      for (int step = 0; step < numSteps; step++) {

        session.run(update);

        float mut =
            Nadam.BETA_ONE_DEFAULT * (1F - 0.5F * (float) Math.pow(0.96F, (0.004F * (step + 1))));
        momentum = momentum * mut;

        try (TFloat32 result =
            (TFloat32)session
                .getGraphSession()
                .runner()
                .fetch("momentum")
                .run()
                .get(0)) {
          result.scalars().forEach(f -> assertEquals(momentum, f.getFloat(), epsilon1));
        }
        mcache = ND.mul(mcache, momentum);
        FloatNdArray[] resultsNP = nadamUpdateNdArray(var0Np, grads0Np, step, m0, v0, mcache);
        var0Np = resultsNP[VAR];
        m0 = resultsNP[M];
        v0 = resultsNP[V];

        resultsNP = nadamUpdateNdArray(var1Np, grads1Np, step, m1, v1, mcache);
        var1Np = resultsNP[VAR];
        m1 = resultsNP[M];
        v1 = resultsNP[V];

        // evaluate m0 and m1
        session.evaluate(m0, firstMomentSlots[0]);
        session.evaluate(m1, firstMomentSlots[1]);

        // evaluate v0 and v1
        session.evaluate(v0, secondMomentSlots[0]);
        session.evaluate(v1, secondMomentSlots[1]);

        // evaluate var0 and var1
        session.evaluate(var0Np, var0);
        session.evaluate(var1Np, var1);
      }
    }
  }

  private FloatNdArray[] nadamUpdateNdArray(
      FloatNdArray varNp,
      FloatNdArray gradsNp,
      int t,
      FloatNdArray m,
      FloatNdArray v,
      FloatNdArray mCache) {

    float alpha = 0.001F;
    float beta1 = 0.9F;
    float beta2 = 0.999F;
    float epsilon = 1e-8F;
    float muT = beta1 * (1F - 0.5F * (float) Math.pow(0.96, 0.004 * (t + 1)));
    float muT1 = beta1 * (1F - 0.5F * (float) Math.pow(0.96, (0.004 * (t + 2))));
    FloatNdArray mCacheT1 = ND.mul(mCache, muT1);
    FloatNdArray gPrimeT = ND.div(gradsNp, ND.sub(1.0F, mCache));
    FloatNdArray mT = ND.add(ND.mul(beta1, m), ND.mul((1 - beta1), gradsNp));
    FloatNdArray vT = ND.add(ND.mul(beta2, v), ND.mul((1 - beta2), ND.square(gradsNp)));

    FloatNdArray mPrimeT = ND.div(mT, ND.sub(1.F, mCacheT1));
    FloatNdArray vPrimeT = ND.div(vT, 1.F - (float) Math.pow(beta2, t + 1));
    FloatNdArray mBarT = ND.add(ND.mul((1 - muT), gPrimeT), ND.mul(muT1, mPrimeT));
    FloatNdArray paramT =
        ND.sub(varNp, ND.div(ND.mul(alpha, mBarT), ND.add(ND.sqrt(vPrimeT), epsilon)));

    FloatNdArray[] results = new FloatNdArray[3];
    results[VAR] = paramT;
    results[M] = mT;
    results[V] = vT;
    return results;
  }
}
