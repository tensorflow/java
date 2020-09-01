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
import org.tensorflow.framework.optimizers.Optimizer.GradAndVar;
import org.tensorflow.keras.utils.TestSession;
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
import static org.tensorflow.framework.optimizers.AdaDelta.ACCUMULATOR;
import static org.tensorflow.framework.optimizers.AdaDelta.ACCUMULATOR_UPDATE;
import static org.tensorflow.keras.optimizers.AdaDelta.*;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;

/** Test cases for AdaDelta Optimizer */
public class AdaDeltaTest {

  private TestSession.Mode tf_mode = TestSession.Mode.GRAPH;

  private int index;

  public AdaDeltaTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of create method, of class AdaDelta. */
  @Test
  public void testCreate() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      Map<String, Object> config = new HashMap<>();
      config.put(NAME_KEY, "AdaDelta");
      config.put(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
      config.put(RHO_RATE_KEY, RHO_DEFAULT);
      config.put(EPSILON_KEY, EPSILON_DEFAULT);
      AdaDelta expResult = new AdaDelta(tf);
      AdaDelta result = AdaDelta.create(tf, config);
      assertEquals(expResult.getConfig(), result.getConfig());
    }
  }

  @Test
  public void testConstructAdadeltaWithLR() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      AdaDelta opt = new AdaDelta(tf, 1.0F, 0.9F, 1.F);
      AdaDelta opt1 = new AdaDelta(tf, "AdaDelta_1", 0.1F, 0.9F, 1.F);
      AdaDelta opt2 = new AdaDelta(tf, "AdaDelta_2", 0.1F, 0.9F, 1e-8F);
      String format = "AdaDelta{learningRate=%s, rho=%s, epsilon=%s}";
      String optExpected = String.format(format, 1.0F, 0.9F, 1.F);
      String opt1Expected = String.format(format, 0.1F, 0.9F, 1.F);
      String opt2Expected = String.format(format, 0.1F, 0.9F, 1e-8F);

      String optString = opt.toString();
      String opt1String = opt1.toString();
      String opt2String = opt2.toString();

      assertEquals(optExpected, optString);
      assertEquals(opt1Expected, opt1String);
      assertEquals(opt2Expected, opt2String);
    }
  }

  @Test
  public void testConstructAdadeltaWithEpsilonValues() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      AdaDelta opt = new AdaDelta(tf);
      Map<String, Object> config = opt.getConfig();
      assertEquals(EPSILON_DEFAULT, (float) config.get(EPSILON_KEY));

      opt = new AdaDelta(tf, "AdaDelta_1", LEARNING_RATE_DEFAULT, RHO_DEFAULT, 1e-8F);
      config = opt.getConfig();
      assertEquals(1e-8F, (float) config.get(EPSILON_KEY));
    }
  }

  @Test
  public void testBasic() {
    int num_updates = 4; // # number of ADADELTA steps to perform
    float[] grads = {0.2F, 0.1F, 0.01F};
    float[] lrs = {1.0F, 0.5F, 0.1F};
    for (float grad : grads) {
      for (float lr : lrs) {
        try (TestSession session = TestSession.createTestSession(tf_mode)) {
          Ops tf = session.getTF();
          float[] var0_init = {1.0F, 2.0F};
          float[] var1_init = {3.0F, 4.0F};
          float[] fgrads = {grad, grad};
          Shape shape = Shape.of(var0_init.length);
          Variable<TFloat32> var0 = tf.withName("var0").variable(shape, TFloat32.DTYPE);
          Variable<TFloat32> var1 = tf.withName("var1").variable(shape, TFloat32.DTYPE);

          Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0_init));
          Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1_init));

          Constant<TFloat32> cgrads = tf.constant(fgrads);

          float accum = 0.0F;
          float accum_update = 0.0F;
          float rho = 0.95F;
          float epsilon = 1e-8F;
          float epsilon1 = 1e-5F;

          /* build the GradsAnvVars */
          List gradsAndVars = new ArrayList<>();
          gradsAndVars.add(new GradAndVar<>(cgrads.asOutput(), var0.asOutput()));
          gradsAndVars.add(new GradAndVar<>(cgrads.asOutput(), var1.asOutput()));

          /* get the Optimizer */
          AdaDelta adaDelta = new AdaDelta(tf, lr, rho, epsilon);

          /** apply gradients */
          Op adadelta_update = adaDelta.applyGradients(gradsAndVars, "AdaDeltaTest");

          /* Create and validae the shapes of the slota */
          Variable<TFloat32>[] slots = new Variable[2];
          Variable<TFloat32>[] slotUpdates = new Variable[2];

          slots[0] = adaDelta.getSlot(var0.asOutput(), ACCUMULATOR).get();
          assertEquals(slots[0].asOutput().shape(), var0.asOutput().shape());

          slotUpdates[0] = adaDelta.getSlot(var0.asOutput(), ACCUMULATOR_UPDATE).get();
          assertEquals(slotUpdates[0].asOutput().shape(), var0.asOutput().shape());

          slots[1] = adaDelta.getSlot(var1.asOutput(), ACCUMULATOR).get();
          assertEquals(slots[1].asOutput().shape(), var1.asOutput().shape());

          slotUpdates[1] = adaDelta.getSlot(var1.asOutput(), ACCUMULATOR_UPDATE).get();
          assertEquals(slotUpdates[1].asOutput().shape(), var1.asOutput().shape());

          /* initialize the local variables */
          session.run(var0Initializer);
          session.run(var1Initializer);

          /** initialize the accumulators */
          session.run(tf.init());

          /** make sure the variables were initialized properly */
          session.evaluate(var0_init, var0);
          session.evaluate(var1_init, var1);

          float[] updates = new float[num_updates];
          float tot_update = 0;
          for (int step = 0; step < num_updates; step++) {
            session.run(adadelta_update, adaDelta.getFeedDict());
            accum = accum * rho + (float) Math.pow(grad, 2) * (1.0F - rho);
            updates[step] =
                ((float) Math.sqrt(accum_update + epsilon)
                    * (float) (1 / Math.sqrt(accum + epsilon))
                    * grad);
            accum_update =
                (accum_update * rho + ((float) Math.pow(updates[step], 2) * (1.0F - rho)));
            tot_update += updates[step] * lr;

            for (int i = 0; i < 2; i++) {
              session.evaluate(accum, slots[i]);
              session.evaluate(accum_update, slotUpdates[i]);
            }

            Float[] var0_initUpdate = {var0_init[0] - tot_update, var0_init[1] - tot_update};
            Float[] var1_initUpdate = {var1_init[0] - tot_update, var1_init[1] - tot_update};

            session.evaluate(var0_initUpdate, var0);
            session.evaluate(var1_initUpdate, var1);
          }
        }
      }
    }
  }

  @Test
  public void testWithLearningRateDecay() {
    int numSteps = 4; // # number of ADADELTA steps to perform
    float[] grads = {0.2F, 0.1F, 0.01F};

    for (float grad : grads) {
      try (TestSession session = TestSession.createTestSession(tf_mode)) {
        Ops tf = session.getTF();
        float lr = 1.0F;
        float[] var0_init = {1.0F, 2.0F};
        float[] var1_init = {3.0F, 4.0F};
        float[] fgrads = {grad, grad};
        Shape shape = Shape.of(var0_init.length);
        Variable<TFloat32> var0 = tf.withName("var0").variable(shape, TFloat32.DTYPE);
        Variable<TFloat32> var1 = tf.withName("var1").variable(shape, TFloat32.DTYPE);

        Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0_init));
        Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1_init));

        Constant<TFloat32> cgrads = tf.constant(fgrads);

        float accum = 0.0F;
        float accum_update = 0.0F;
        float rho = 0.95F;
        float epsilon = 1e-8F;
        float epsilon1 = 1e-5F;

        /* build the GradsAnvVars */
        List gradsAndVars = new ArrayList<>();
        gradsAndVars.add(new GradAndVar<>(cgrads.asOutput(), var0.asOutput()));
        gradsAndVars.add(new GradAndVar<>(cgrads.asOutput(), var1.asOutput()));

        /* get the Optimizer */
        AdaDelta instance = new AdaDelta(tf, lr, rho, epsilon);

        Op adadelta_update = instance.applyGradients(gradsAndVars, "AdaDeltaTest");

        /* Create and validae the shapes of the slota */
        Variable<TFloat32>[] slots = new Variable[2];
        Variable<TFloat32>[] slotUpdates = new Variable[2];

        slots[0] = instance.getSlot(var0.asOutput(), ACCUMULATOR).get();
        assertEquals(slots[0].asOutput().shape(), var0.asOutput().shape());

        slotUpdates[0] = instance.getSlot(var0.asOutput(), ACCUMULATOR_UPDATE).get();
        assertEquals(slotUpdates[0].asOutput().shape(), var0.asOutput().shape());

        slots[1] = instance.getSlot(var1.asOutput(), ACCUMULATOR).get();
        assertEquals(slots[1].asOutput().shape(), var1.asOutput().shape());

        slotUpdates[1] = instance.getSlot(var1.asOutput(), ACCUMULATOR_UPDATE).get();
        assertEquals(slotUpdates[1].asOutput().shape(), var1.asOutput().shape());

        /* initialize the local variables */
        session.run(var0Initializer);
        session.run(var1Initializer);

        /** initialize the accumulators */
        session.run(tf.init());

        /** make sure the variables were initialized properly */
        session.evaluate(var0_init, var0);
        session.evaluate(var1_init, var1);

        float[] updates = new float[numSteps];
        float totUpdate = 0;
        for (int step = 0; step < numSteps; step++) {
          session.run(adadelta_update, instance.getFeedDict());
          accum = accum * rho + (float) Math.pow(grad, 2) * (1.0F - rho);
          updates[step] =
              ((float) Math.sqrt(accum_update + epsilon)
                  * (float) (1 / Math.sqrt(accum + epsilon))
                  * grad);
          accum_update = (accum_update * rho + ((float) Math.pow(updates[step], 2) * (1.0F - rho)));
          totUpdate += updates[step] * lr;

          for (int i = 0; i < 2; i++) {
            session.evaluate(accum, slots[i]);
            session.evaluate(accum_update, slotUpdates[i]);
          }

          Float[] var0_initUpdate = {var0_init[0] - totUpdate, var0_init[1] - totUpdate};
          Float[] var1_initUpdate = {var1_init[0] - totUpdate, var1_init[1] - totUpdate};

          session.evaluate(var0_initUpdate, var0);
          session.evaluate(var1_initUpdate, var1);

          // Adjust learning rate
          lr *= 0.9F;
          instance.setLearningRate(lr);
        }
      }
    }
  }
}
