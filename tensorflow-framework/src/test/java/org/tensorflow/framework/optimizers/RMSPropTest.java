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
import static org.junit.jupiter.api.Assertions.fail;
import static org.tensorflow.framework.optimizers.RMSProp.*;

/** Test cases for RMSProp Optimizer */
public class RMSPropTest {
  final int VAR_T = 0;
  final int MG_T = 1;
  final int RMS_T = 2;
  final int MOM_T = 3;
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  Object[][] testParamValues = {
    // learningRate, rho (decay), momentum, epsilon, centered
    {0.05F, 0.9F, 0.0F, 1e-3F, true},
    {0.05F, 0.9F, 0.0F, 1e-3F, false},
    {0.1F, 0.9F, 0.0F, 1e-3F, true},
    {0.01F, 0.9F, 0.0F, 1e-5F, true},
    {0.01F, 0.9F, 0.9F, 1e-5F, true}
  };

  public RMSPropTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  @Test
  public void testDense() {

    int numSteps = 3;

    for (Object[] testParamValue : testParamValues) {
      // learningRate, rho (decay), momentum, epsilon, centered
      float learningRate = (float) testParamValue[0];
      float decay = (float) testParamValue[1];
      float momentum = (float) testParamValue[2];
      float epsilon = (float) testParamValue[3];
      boolean centered = (boolean) testParamValue[4];
      try (TestSession session = TestSession.createTestSession(tfMode);
          RMSProp instance =
              new RMSProp(session.getGraph(), learningRate, decay, momentum, epsilon, centered)) {
        Ops tf = session.getTF();

        session.setEpsilon(1e-2f);
        float[] var0Init = {1.0F, 2.0F};
        float[] var1Init = {3.0F, 4.0F};
        float[] grads0Init = {0.1F, 0.2F};
        float[] grads1Init = {0.01F, 0.2F};

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

        /* build the GradsAnvVars */
        List<GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
        gradsAndVars.add(new GradAndVar<>(grads0.asOutput(), var0.asOutput()));
        gradsAndVars.add(new GradAndVar<>(grads1.asOutput(), var1.asOutput()));

        Op update = instance.applyGradients(gradsAndVars, "RMSPropTest");

        /* initialize the local variables */
        session.run(var0Initializer);
        session.run(var1Initializer);

        /* initialize the accumulators */
        session.run(tf.init());

        /* make sure the variables were initialized properly */
        session.evaluate(var0Init, var0);
        session.evaluate(var1Init, var1);

        Variable<TFloat32> mg0 =
            centered && instance.getSlot(var0.asOutput(), MG).isPresent()
                ? instance.getSlot(var0.asOutput(), MG).get()
                : null;
        Variable<TFloat32> mg1 =
            centered && instance.getSlot(var1.asOutput(), MG).isPresent()
                ? instance.getSlot(var1.asOutput(), MG).get()
                : null;
        Variable<TFloat32> mom0 =
            momentum > 0.F && instance.getSlot(var0.asOutput(), MOMENTUM).isPresent()
                ? instance.getSlot(var0.asOutput(), MOMENTUM).get()
                : null;
        Variable<TFloat32> mom1 =
            momentum > 0.F && instance.getSlot(var1.asOutput(), MOMENTUM).isPresent()
                ? instance.getSlot(var1.asOutput(), MOMENTUM).get()
                : null;
        Variable<TFloat32> rms0 =
            instance.getSlot(var0.asOutput(), RMS).isPresent()
                ? instance.getSlot(var0.asOutput(), RMS).get()
                : null;
        Variable<TFloat32> rms1 =
            instance.getSlot(var1.asOutput(), RMS).isPresent()
                ? instance.getSlot(var1.asOutput(), RMS).get()
                : null;

        float[] zeros = {0.0F, 0.0F};
        float[] ones = {1.0F, 1.0F}; // temp to match RMSProp
        FloatNdArray mg0Np = NdArrays.vectorOf(zeros);
        FloatNdArray mg1Np = NdArrays.vectorOf(zeros);
        FloatNdArray rms0Np = NdArrays.vectorOf(ones);
        FloatNdArray rms1Np = NdArrays.vectorOf(ones);
        FloatNdArray mom0Np = NdArrays.vectorOf(zeros);
        FloatNdArray mom1Np = NdArrays.vectorOf(zeros);

        for (int i = 0; i < numSteps; i++) {
          session.run(update, instance.getFeedMap());
          FloatNdArray[] result0 =
              calc(
                  var0Np,
                  grads0Np,
                  mg0Np,
                  rms0Np,
                  mom0Np,
                  learningRate,
                  decay,
                  momentum,
                  epsilon,
                  centered);
          var0Np = result0[VAR_T];
          mg0Np = result0[MG_T];
          rms0Np = result0[RMS_T];
          mom0Np = result0[MOM_T];

          FloatNdArray[] result1 =
              calc(
                  var1Np,
                  grads1Np,
                  mg1Np,
                  rms1Np,
                  mom1Np,
                  learningRate,
                  decay,
                  momentum,
                  epsilon,
                  centered);

          var1Np = result1[VAR_T];
          mg1Np = result1[MG_T];
          rms1Np = result1[RMS_T];
          mom1Np = result1[MOM_T];

          if (centered) {
            if (mg0 != null) session.evaluate(mg0Np, mg0);
            if (mg1 != null) session.evaluate(mg1Np, mg1);
          }

          if (mom0 != null) session.evaluate(mom0Np, mom0);
          if (mom1 != null) session.evaluate(mom1Np, mom1);

          /*     TODO the values returned from rms slot, do not match what I see in the python test */
          if (rms0 != null) session.evaluate(rms0Np, rms0);
          else fail("rms0 is null");
          if (rms1 != null) session.evaluate(rms1Np, rms1);
          else fail("rms1 is null");

          session.evaluate(var0Np, var0);
          session.evaluate(var1Np, var1);
        }
      }
    }
  }

  @Test
  public void testDenseWithLROperand() {

    int numSteps = 3;

    for (Object[] testParamValue : testParamValues) {
      // learningRate, rho (decay), momentum, epsilon, centered
      float learningRate = (float) testParamValue[0];
      float decay = (float) testParamValue[1];
      float momentum = (float) testParamValue[2];
      float epsilon = (float) testParamValue[3];
      boolean centered = (boolean) testParamValue[4];
      try (TestSession session = TestSession.createTestSession(tfMode)) {

        Ops tf = session.getTF();
        try (RMSProp instance =
            new RMSProp(
                session.getGraph(),
                tf.math.add(tf.constant(learningRate), tf.constant(0f)),
                decay,
                momentum,
                epsilon,
                centered)) {

          session.setEpsilon(1e-2f);
          float[] var0Init = {1.0F, 2.0F};
          float[] var1Init = {3.0F, 4.0F};
          float[] grads0Init = {0.1F, 0.2F};
          float[] grads1Init = {0.01F, 0.2F};

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

          /* build the GradsAnvVars */
          List<GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
          gradsAndVars.add(new GradAndVar<>(grads0.asOutput(), var0.asOutput()));
          gradsAndVars.add(new GradAndVar<>(grads1.asOutput(), var1.asOutput()));

          Op update = instance.applyGradients(gradsAndVars, "RMSPropTest");

          /* initialize the local variables */
          session.run(var0Initializer);
          session.run(var1Initializer);

          /* initialize the accumulators */
          session.run(tf.init());

          /* make sure the variables were initialized properly */
          session.evaluate(var0Init, var0);
          session.evaluate(var1Init, var1);

          Variable<TFloat32> mg0 =
              centered && instance.getSlot(var0.asOutput(), MG).isPresent()
                  ? instance.getSlot(var0.asOutput(), MG).get()
                  : null;
          Variable<TFloat32> mg1 =
              centered && instance.getSlot(var1.asOutput(), MG).isPresent()
                  ? instance.getSlot(var1.asOutput(), MG).get()
                  : null;
          Variable<TFloat32> mom0 =
              momentum > 0.F && instance.getSlot(var0.asOutput(), MOMENTUM).isPresent()
                  ? instance.getSlot(var0.asOutput(), MOMENTUM).get()
                  : null;
          Variable<TFloat32> mom1 =
              momentum > 0.F && instance.getSlot(var1.asOutput(), MOMENTUM).isPresent()
                  ? instance.getSlot(var1.asOutput(), MOMENTUM).get()
                  : null;
          Variable<TFloat32> rms0 =
              instance.getSlot(var0.asOutput(), RMS).isPresent()
                  ? instance.getSlot(var0.asOutput(), RMS).get()
                  : null;
          Variable<TFloat32> rms1 =
              instance.getSlot(var1.asOutput(), RMS).isPresent()
                  ? instance.getSlot(var1.asOutput(), RMS).get()
                  : null;

          float[] zeros = {0.0F, 0.0F};
          float[] ones = {1.0F, 1.0F}; // temp to match RMSProp
          FloatNdArray mg0Np = NdArrays.vectorOf(zeros);
          FloatNdArray mg1Np = NdArrays.vectorOf(zeros);
          FloatNdArray rms0Np = NdArrays.vectorOf(ones);
          FloatNdArray rms1Np = NdArrays.vectorOf(ones);
          FloatNdArray mom0Np = NdArrays.vectorOf(zeros);
          FloatNdArray mom1Np = NdArrays.vectorOf(zeros);

          for (int i = 0; i < numSteps; i++) {
            session.run(update, instance.getFeedMap());
            FloatNdArray[] result0 =
                calc(
                    var0Np,
                    grads0Np,
                    mg0Np,
                    rms0Np,
                    mom0Np,
                    learningRate,
                    decay,
                    momentum,
                    epsilon,
                    centered);
            var0Np = result0[VAR_T];
            mg0Np = result0[MG_T];
            rms0Np = result0[RMS_T];
            mom0Np = result0[MOM_T];

            FloatNdArray[] result1 =
                calc(
                    var1Np,
                    grads1Np,
                    mg1Np,
                    rms1Np,
                    mom1Np,
                    learningRate,
                    decay,
                    momentum,
                    epsilon,
                    centered);

            var1Np = result1[VAR_T];
            mg1Np = result1[MG_T];
            rms1Np = result1[RMS_T];
            mom1Np = result1[MOM_T];

            if (centered) {
              if (mg0 != null) session.evaluate(mg0Np, mg0);
              if (mg1 != null) session.evaluate(mg1Np, mg1);
            }

            if (mom0 != null) session.evaluate(mom0Np, mom0);
            if (mom1 != null) session.evaluate(mom1Np, mom1);

            /*     TODO the values returned from rms slot, do not match what I see in the python test */
            if (rms0 != null) session.evaluate(rms0Np, rms0);
            else fail("rms0 is null");
            if (rms1 != null) session.evaluate(rms1Np, rms1);
            else fail("rms1 is null");

            session.evaluate(var0Np, var0);
            session.evaluate(var1Np, var1);
          }
        }
      }
    }
  }

  @Test
  public void testWithLearningRateDecay() {
    int numSteps = 3;

    for (Object[] testParamValue : testParamValues) {
      float learningRate = (float) testParamValue[0];
      float decay = (float) testParamValue[1];
      float momentum = (float) testParamValue[2];
      float epsilon = (float) testParamValue[3];
      boolean centered = (boolean) testParamValue[4];

      try (TestSession session = TestSession.createTestSession(tfMode);
          RMSProp instance =
              new RMSProp(session.getGraph(), learningRate, decay, momentum, epsilon, centered)) {
        Ops tf = session.getTF();
        session.setEpsilon(1e-2f);
        float[] var0_init = {1.0F, 2.0F};
        float[] var1_init = {3.0F, 4.0F};
        float[] grads0_init = {0.1F, 0.2F};
        float[] grads1_init = {0.01F, 0.2F};

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

        /* build the GradsAnvVars */
        List<GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
        gradsAndVars.add(new GradAndVar<>(grads0.asOutput(), var0.asOutput()));
        gradsAndVars.add(new GradAndVar<>(grads1.asOutput(), var1.asOutput()));

        Op update = instance.applyGradients(gradsAndVars, "RMSPropTest");

        /* initialize the local variables */
        session.run(var0Initializer);
        session.run(var1Initializer);

        // initialize the accumulators
        session.run(tf.init());

        // make sure the variables were initialized properly
        session.evaluate(var0_init, var0);
        session.evaluate(var1_init, var1);

        Variable<TFloat32> mg0 =
            centered && instance.getSlot(var0.asOutput(), MG).isPresent()
                ? instance.getSlot(var0.asOutput(), MG).get()
                : null;
        Variable<TFloat32> mg1 =
            centered && instance.getSlot(var1.asOutput(), MG).isPresent()
                ? instance.getSlot(var1.asOutput(), MG).get()
                : null;
        Variable<TFloat32> mom0 =
            momentum > 0.F && instance.getSlot(var0.asOutput(), MOMENTUM).isPresent()
                ? instance.getSlot(var0.asOutput(), MOMENTUM).get()
                : null;
        Variable<TFloat32> mom1 =
            momentum > 0.F && instance.getSlot(var1.asOutput(), MOMENTUM).isPresent()
                ? instance.getSlot(var1.asOutput(), MOMENTUM).get()
                : null;
        Variable<TFloat32> rms0 =
            instance.getSlot(var0.asOutput(), RMS).isPresent()
                ? instance.getSlot(var0.asOutput(), RMS).get()
                : null;
        Variable<TFloat32> rms1 =
            instance.getSlot(var1.asOutput(), RMS).isPresent()
                ? instance.getSlot(var1.asOutput(), RMS).get()
                : null;

        float[] zeros = {0.0F, 0.0F};
        float[] ones = {1.0F, 1.0F}; // temp to match RMSProp
        FloatNdArray mg0_np = NdArrays.vectorOf(zeros);
        FloatNdArray mg1_np = NdArrays.vectorOf(zeros);
        FloatNdArray rms0_np = NdArrays.vectorOf(ones);
        FloatNdArray rms1_np = NdArrays.vectorOf(ones);
        FloatNdArray mom0_np = NdArrays.vectorOf(zeros);
        FloatNdArray mom1_np = NdArrays.vectorOf(zeros);

        for (int i = 0; i < numSteps; i++) {
          assertEquals(learningRate, instance.getLearningRate(), epsilon);
          session.evaluate(
              learningRate, tf.identity(instance.getLearningRateOperand()), instance.getFeedMap());
          session.run(update, instance.getFeedMap());
          FloatNdArray[] result0 =
              calc(
                  var0_np,
                  grads0_np,
                  mg0_np,
                  rms0_np,
                  mom0_np,
                  learningRate,
                  decay,
                  momentum,
                  epsilon,
                  centered);
          var0_np = result0[VAR_T];
          mg0_np = result0[MG_T];
          rms0_np = result0[RMS_T];
          mom0_np = result0[MOM_T];

          FloatNdArray[] result1 =
              calc(
                  var1_np,
                  grads1_np,
                  mg1_np,
                  rms1_np,
                  mom1_np,
                  learningRate,
                  decay,
                  momentum,
                  epsilon,
                  centered);

          var1_np = result1[VAR_T];
          mg1_np = result1[MG_T];
          rms1_np = result1[RMS_T];
          mom1_np = result1[MOM_T];

          if (centered) {
            if (mg0 != null) session.evaluate(mg0_np, mg0);
            else fail("mg0 is null");
            if (mg1 != null) session.evaluate(mg1_np, mg1);
            else fail("mg1 is null");
          }
          if (momentum > 0.F) {
            if (mom0 != null) session.evaluate(mom0_np, mom0);
            if (mom1 != null) session.evaluate(mom1_np, mom1);
          }

          /*     TODO the values returned from rms slot, do not match what I see in the python test */
          if (rms0 != null) session.evaluate(rms0_np, rms0);
          else fail("rms0 is null");
          if (rms1 != null) session.evaluate(rms1_np, rms1);
          else fail("rms1 is null");

          session.evaluate(var0_np, var0);
          session.evaluate(var1_np, var1);

          learningRate *= 0.9F;
          instance.setLearningRate(learningRate);
        }
      }
    }
  }

  FloatNdArray[] calc(
      FloatNdArray varNp,
      FloatNdArray gradNp,
      FloatNdArray mgNp,
      FloatNdArray rmsNp,
      FloatNdArray mom,
      float lr,
      float decay,
      float momentum,
      float epsilon,
      boolean centered) {

    FloatNdArray[] result = new FloatNdArray[4]; // varT, mgT, rmsT, momT
    result[RMS_T] = calcRMS(rmsNp, gradNp, decay); // RMS

    FloatNdArray denomT;
    if (centered) {
      result[MG_T] = calcMG(mgNp, gradNp, decay);
      // rmsT - mgT * mgT
      denomT = ND.sub(result[RMS_T], ND.square(result[MG_T]));
    } else {
      result[MG_T] = mgNp;
      denomT = rmsNp;
    }
    if (momentum > 0.F) {
      // momentum * mom + lr * g / (np.sqrt(denomT + epsilon))
      result[MOM_T] = calcMom(momentum, mom, lr, gradNp, denomT, epsilon);
      // varT = var - momT
      result[VAR_T] = ND.sub(varNp, result[MOM_T]);
    } else {
      result[MOM_T] = mom;
      result[VAR_T] = calcVar(varNp, gradNp, lr, denomT, epsilon);
    }

    return result;
  }

  private FloatNdArray calcRMS(FloatNdArray rmsNp, FloatNdArray gradNp, float decay) {
    // rms * rho + (1 - rho) * g * g
    FloatNdArray rmsRho = ND.mul(rmsNp, decay);
    FloatNdArray squareG = ND.square(gradNp);
    float oneRHO = 1.0F - decay;
    FloatNdArray decayG2 = ND.mul(oneRHO, squareG);
    return ND.add(rmsRho, decayG2);
  }

  private FloatNdArray calcMG(FloatNdArray mgNp, FloatNdArray gradNp, float decay) {
    // mgT = mg * rho + (1 - rho) * g
    FloatNdArray mgRho = ND.mul(mgNp, decay);
    float oneRHO = 1.0F - decay;
    FloatNdArray decayG = ND.mul(oneRHO, gradNp);
    return ND.add(mgRho, decayG);
  }

  private FloatNdArray calcMom(
      float momentum,
      FloatNdArray mom,
      float lr,
      FloatNdArray gradNp,
      FloatNdArray denomT,
      float epsilon) {
    // momentum * mom + lr * g / (np.sqrt(denomT + epsilon))
    FloatNdArray moMo = ND.mul(momentum, mom);
    FloatNdArray dividend = ND.mul(lr, gradNp);
    FloatNdArray divisor = ND.sqrt(ND.add(denomT, epsilon));
    FloatNdArray quotient = ND.div(dividend, divisor);
    return ND.add(moMo, quotient);
  }

  private FloatNdArray calcVar(
      FloatNdArray varNp, FloatNdArray gradNp, float lr, FloatNdArray denomT, float epsilon) {
    // var - lr * g / (np.sqrt(denomT) + epsilon)
    FloatNdArray dividend = ND.mul(lr, gradNp);
    FloatNdArray divisor = ND.add(ND.sqrt(denomT), epsilon);
    FloatNdArray quotient = ND.div(dividend, divisor);
    return ND.sub(varNp, quotient);
  }
}
