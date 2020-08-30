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
import static org.tensorflow.framework.optimizers.RMSProp.*;
import static org.tensorflow.keras.optimizers.Ftrl.LEARNING_RATE_KEY;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;
import static org.tensorflow.keras.optimizers.RMSProp.*;

/** Test cases for RMSProp Optimizer */
public class RMSPropTest {
  private TestSession.Mode tf_mode = TestSession.Mode.GRAPH;

  final int VAR_T = 0;
  final int MG_T = 1;
  final int RMS_T = 2;
  final int MOM_T = 3;

  int index;

  public RMSPropTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of create method, of class RMSProp. */
  @Test
  public void testCreate() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      Map<String, Object> config = new HashMap<>();
      config.put(NAME_KEY, "Ftrl");
      config.put(LEARNING_RATE_KEY, 2.0F);
      config.put(DECAY_KEY, DECAY_DEFAULT);
      config.put(MOMENTUM_KEY, MOMENTUM_DEFAULT);
      config.put(EPSILON_KEY, EPSILON_DEFAULT);
      config.put(CENTERED_KEY, CENTERED_DEFAULT);
      Ftrl expResult = new Ftrl(tf, 2.0F);
      Ftrl result = Ftrl.create(tf, config);
      assertEquals(expResult.getConfig(), result.getConfig());
    }
  }

  Object[][] _test_param_values = {
    // learning_rate, rho (decay), momentum, epsilon, centered
    {0.05F, 0.9F, 0.0F, 1e-3F, true},
    {0.05F, 0.9F, 0.0F, 1e-3F, false},
    {0.1F, 0.9F, 0.0F, 1e-3F, true},
    {0.01F, 0.9F, 0.0F, 1e-5F, true},
    {0.01F, 0.9F, 0.9F, 1e-5F, true}
  };

  @Test
  public void testDense() {

    int numSteps = 3;

    for (int run = 0; run < _test_param_values.length; run++) {
      try (TestSession session = TestSession.createTestSession(tf_mode)) {
        Ops tf = session.getTF();
        session.setEpsilon(1e-2f);
        float[] var0_init = {1.0F, 2.0F};
        float[] var1_init = {3.0F, 4.0F};
        float[] grads0_init = {0.1F, 0.2F};
        float[] grads1_init = {0.01F, 0.2F};
        final float epsilon1 = 1e-2F;

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

        // learning_rate, rho (decay), momentum, epsilon, centered
        float learningRate = (float) (float) _test_param_values[run][0];
        float decay = (float) _test_param_values[run][1];
        float momentum = (float) _test_param_values[run][2];
        float epsilon = (float) _test_param_values[run][3];
        boolean centered = (boolean) _test_param_values[run][4];

        RMSProp instance = new RMSProp(tf, learningRate, decay, momentum, epsilon, centered);

        /* build the GradsAnvVars */
        List gradsAndVars = new ArrayList<>();
        gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
        gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

        Op update = instance.applyGradients(gradsAndVars, "RMSPropTest");

        /* initialize the local variables */
        session.run(var0Initializer);
        session.run(var1Initializer);

        /** initialize the accumulators */
        session.run(tf.init());

        /** make sure the variables were initialized properly */
        session.evaluate(var0_init, var0);
        session.evaluate(var1_init, var1);

        Variable<TFloat32> mg0 = centered ? instance.getSlot(var0.asOutput(), MG).get() : null;
        Variable<TFloat32> mg1 = centered ? instance.getSlot(var1.asOutput(), MG).get() : null;
        Variable<TFloat32> mom0 =
            momentum > 0.F ? instance.getSlot(var0.asOutput(), MOMENTUM).get() : null;
        Variable<TFloat32> mom1 =
            momentum > 0.F ? instance.getSlot(var1.asOutput(), MOMENTUM).get() : null;
        Variable<TFloat32> rms0 = instance.getSlot(var0.asOutput(), RMS).get();
        Variable<TFloat32> rms1 = instance.getSlot(var1.asOutput(), RMS).get();

        float[] zeros = {0.0F, 0.0F};
        float[] ones = {1.0F, 1.0F}; // temp to match RMSProp
        FloatNdArray mg0_np = NdArrays.vectorOf(zeros);
        FloatNdArray mg1_np = NdArrays.vectorOf(zeros);
        FloatNdArray rms0_np = NdArrays.vectorOf(ones);
        FloatNdArray rms1_np = NdArrays.vectorOf(ones);
        FloatNdArray mom0_np = NdArrays.vectorOf(zeros);
        FloatNdArray mom1_np = NdArrays.vectorOf(zeros);

        for (int i = 0; i < numSteps; i++) {
          session.run(update, instance.getFeedDict());
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
            session.evaluate(mg0_np, mg0);
            session.evaluate(mg0_np, mg0);
          }
          if (momentum > 0.F) {
            session.evaluate(mom0_np, mom0);
            session.evaluate(mom1_np, mom1);
          }

          /*     TODO the values returned from rms slot, do not match what I see in the python test */
          session.evaluate(rms0_np, rms0);
          session.evaluate(rms1_np, rms1);

          session.evaluate(var0_np, var0);
          session.evaluate(var1_np, var1);
        }
      }
    }
  }

  FloatNdArray[] calc(
      FloatNdArray var_np,
      FloatNdArray grad_np,
      FloatNdArray mg_np,
      FloatNdArray rms_np,
      FloatNdArray mom,
      float lr,
      float decay,
      float momentum,
      float epsilon,
      boolean centered) {

    FloatNdArray[] result = new FloatNdArray[4]; // var_t, mg_t, rms_t, mom_t
    result[RMS_T] = calcRMS(rms_np, grad_np, decay); // RMS

    FloatNdArray denom_t;
    if (centered) {
      result[MG_T] = calcMG(mg_np, grad_np, decay);
      // rms_t - mg_t * mg_t
      denom_t = ND.sub(result[RMS_T], ND.square(result[MG_T]));
    } else {
      result[MG_T] = mg_np;
      denom_t = rms_np;
    }
    if (momentum > 0.F) {
      // momentum * mom + lr * g / (np.sqrt(denom_t + epsilon))
      result[MOM_T] = calcMom(momentum, mom, lr, grad_np, denom_t, epsilon);
      // var_t = var - mom_t
      result[VAR_T] = ND.sub(var_np, result[MOM_T]);
    } else {
      result[MOM_T] = mom;
      result[VAR_T] = calcVar(var_np, grad_np, lr, denom_t, epsilon);
    }

    return result;
  }

  private FloatNdArray calcRMS(FloatNdArray rms_np, FloatNdArray grad_np, float decay) {
    // rms * rho + (1 - rho) * g * g
    FloatNdArray rms_rho = ND.mul(rms_np, decay);
    FloatNdArray squareG = ND.square(grad_np);
    float oneRHO = 1.0F - decay;
    FloatNdArray decayG2 = ND.mul(oneRHO, squareG);
    FloatNdArray result = ND.add(rms_rho, decayG2);
    return result;
  }

  private FloatNdArray calcMG(FloatNdArray mg_np, FloatNdArray grad_np, float decay) {
    // mg_t = mg * rho + (1 - rho) * g
    FloatNdArray mg_rho = ND.mul(mg_np, decay);
    float oneRHO = 1.0F - decay;
    FloatNdArray decayG = ND.mul(oneRHO, grad_np);
    FloatNdArray result = ND.add(mg_rho, decayG);
    return result;
  }

  private FloatNdArray calcMom(
      float momentum,
      FloatNdArray mom,
      float lr,
      FloatNdArray grad_np,
      FloatNdArray denom_t,
      float epsilon) {
    // momentum * mom + lr * g / (np.sqrt(denom_t + epsilon))
    FloatNdArray moMo = ND.mul(momentum, mom);
    FloatNdArray dividend = ND.mul(lr, grad_np);
    FloatNdArray divisor = ND.sqrt(ND.add(denom_t, epsilon));
    FloatNdArray quotient = ND.div(dividend, divisor);
    FloatNdArray result = ND.add(moMo, quotient);
    return result;
  }

  private FloatNdArray calcVar(
      FloatNdArray var_np, FloatNdArray grad_np, float lr, FloatNdArray denom_t, float epsilon) {
    // var - lr * g / (np.sqrt(denom_t) + epsilon)
    FloatNdArray dividend = ND.mul(lr, grad_np);
    FloatNdArray divisor = ND.add(ND.sqrt(denom_t), epsilon);
    FloatNdArray quotient = ND.div(dividend, divisor);
    FloatNdArray result = ND.sub(var_np, quotient);
    return result;
  }
}
