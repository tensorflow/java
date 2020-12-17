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
import static org.tensorflow.framework.optimizers.Adam.FIRST_MOMENT;
import static org.tensorflow.framework.optimizers.Adam.SECOND_MOMENT;

/** Test cases for Adam Optimizer */
public class AdamTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  public AdamTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  @Test
  public void testBasic() {
    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {3.0F, 4.0F};
    float[] grads0Init = {0.1F, 0.1F};
    float[] grads1Init = {0.01F, 0.01F};
    FloatNdArray var0Np = NdArrays.vectorOf(var0Init);
    FloatNdArray var1Np = NdArrays.vectorOf(var1Init);
    FloatNdArray grads0Np = NdArrays.vectorOf(grads0Init);
    FloatNdArray grads1Np = NdArrays.vectorOf(grads1Init);

    float epsilon1 = 1e-3F;

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      float learningRate = 0.001F;
      float beta1 = 0.9F;
      float beta2 = 0.999F;
      Graph graph = session.getGraph();

      session.setEpsilon(epsilon1);

      Adam instance = new Adam(graph, learningRate);
      Ops tf = instance.getTF();
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

      /* Create and validate the shapes of the slots */
      @SuppressWarnings("unchecked")
      Variable<TFloat32>[] firstMomentSlots = new Variable[2];
      @SuppressWarnings("unchecked")
      Variable<TFloat32>[] secondMomentSlots = new Variable[2];

      firstMomentSlots[0] = instance.getSlot(var0.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[0].shape(), var0.shape());

      secondMomentSlots[0] = instance.getSlot(var0.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[0].shape(), var0.shape());

      firstMomentSlots[1] = instance.getSlot(var1.asOutput(), FIRST_MOMENT).get();
      assertEquals(firstMomentSlots[1].shape(), var1.shape());

      secondMomentSlots[1] = instance.getSlot(var1.asOutput(), SECOND_MOMENT).get();
      assertEquals(secondMomentSlots[1].shape(), var1.shape());

      /* initialize the accumulators */
      session.run(tf.init());

      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      FloatNdArray m0Np = NdArrays.ofFloats(shape0);
      FloatNdArray v0Np = NdArrays.ofFloats(shape0);
      FloatNdArray m1Np = NdArrays.ofFloats(shape1);
      FloatNdArray v1Np = NdArrays.ofFloats(shape1);

      for (int step = 0; step < 3; step++) {

        // Test powers
        final float[] powers = {
          (float) Math.pow(beta1, step + 1), (float) Math.pow(beta2, step + 1)
        };

        try (TFloat32 result =
            (TFloat32)session
                .getGraphSession()
                .runner()
                .fetch("beta1_power")
                .run()
                .get(0)) {
          result.scalars().forEach(f -> assertEquals(powers[0], f.getFloat(), epsilon1));
        }
        try (TFloat32 result =
            (TFloat32)session
                .getGraphSession()
                .runner()
                .fetch("beta2_power")
                .run()
                .get(0)) {
          result.scalars().forEach(f -> assertEquals(powers[1], f.getFloat(), epsilon1));
        }
        session.run(update);

        float lrT =
            learningRate
                * (float) Math.sqrt(1 - (float) Math.pow(beta2, (step + 1)))
                / (1 - (float) Math.pow(beta1, (step + 1)));

        m0Np = calculateM(m0Np, grads0Np, beta1);
        v0Np = calculateV(v0Np, grads0Np, beta2);
        var0Np = calculateParam(var0Np, lrT, m0Np, v0Np, 1e-7F);

        m1Np = calculateM(m1Np, grads1Np, beta1);
        v1Np = calculateV(v1Np, grads1Np, beta2);
        var1Np = calculateParam(var1Np, lrT, m1Np, v1Np, 1e-7F);

        // evaluate var 0 and var1
        session.evaluate(var0Np, var0);
        session.evaluate(var1Np, var1);

        // first moment
        session.evaluate(m0Np, firstMomentSlots[0]);
        session.evaluate(m1Np, firstMomentSlots[1]);

        // second moment
        session.evaluate(v0Np, secondMomentSlots[0]);
        session.evaluate(v1Np, secondMomentSlots[1]);
      }
    }
  }

  private FloatNdArray calculateM(FloatNdArray m, FloatNdArray gT, float beta) {
    // mT = beta1 * m + (1 - beta1) * gT
    return ND.add(ND.mul(m, beta), ND.mul(gT, (1 - beta)));
  }

  private FloatNdArray calculateV(FloatNdArray v, FloatNdArray gT, float beta) {
    // beta2 * v + (1 - beta2) * gT * gT
    FloatNdArray mul1 = ND.mul(v, beta);
    FloatNdArray squareG = ND.square(gT);
    FloatNdArray mul2 = ND.mul((1 - beta), squareG);
    return ND.add(mul1, mul2);
  }

  private FloatNdArray calculateParam(
      FloatNdArray param, float lrT, FloatNdArray m, FloatNdArray v, float epsilon) {
    //  param - lrT * mT / (np.sqrt(vT) + epsilon)
    FloatNdArray sqrt = ND.sqrt(v);
    FloatNdArray divisor = ND.add(sqrt, epsilon);
    FloatNdArray dividend = ND.mul(lrT, m);
    FloatNdArray quotient = ND.div(dividend, divisor);
    return ND.sub(param, quotient);
  }
}
