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
import org.tensorflow.framework.optimizers.Optimizer.GradAndVar;
import org.tensorflow.framework.utils.TestSession;
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
import static org.tensorflow.framework.optimizers.AdaDelta.ACCUMULATOR;
import static org.tensorflow.framework.optimizers.AdaDelta.ACCUMULATOR_UPDATE;

/** Test cases for AdaDelta Optimizer */
public class AdaDeltaTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  public AdaDeltaTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  @Test
  public void testConstructAdadeltaWithLR() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        AdaDelta opt = new AdaDelta(session.getGraph(), "opt1", 1.0F, 0.9F, 1.F);
        AdaDelta opt2 = new AdaDelta(session.getGraph(), "opt2", 0.1F, 0.9F, 1.F);
        AdaDelta opt3 = new AdaDelta(session.getGraph(), "opt3", 0.1F, 0.9F, 1e-8F)) {
      String format = "AdaDelta{learningRate=%s, rho=%s, epsilon=%s}";
      String optExpected = String.format(format, 1.0F, 0.9F, 1.F);
      String opt2Expected = String.format(format, 0.1F, 0.9F, 1.F);
      String opt3Expected = String.format(format, 0.1F, 0.9F, 1e-8F);

      String optString = opt.toString();
      String opt2String = opt2.toString();
      String opt3String = opt3.toString();

      assertEquals(optExpected, optString);
      assertEquals(opt2Expected, opt2String);
      assertEquals(opt3Expected, opt3String);
    }
  }

  @Test
  public void testBasic() {
    int numUpdates = 4; // # number of ADADELTA steps to perform
    float[] grads = {0.2F, 0.1F, 0.01F};
    float[] lrs = {1.0F, 0.5F, 0.1F};

    float rho = 0.95F;
    float epsilon = 1e-8F;

    for (float grad : grads) {
      for (float lr : lrs) {
        try (TestSession session = TestSession.createTestSession(tfMode);
            AdaDelta instance = new AdaDelta(session.getGraph(), lr, rho, epsilon)) {
          Ops tf = session.getTF();
          float[] var0Init = {1.0F, 2.0F};
          float[] var1Init = {3.0F, 4.0F};
          float[] fgrads = {grad, grad};
          Shape shape = Shape.of(var0Init.length);
          Variable<TFloat32> var0 = tf.withName("var0").variable(shape, TFloat32.DTYPE);
          Variable<TFloat32> var1 = tf.withName("var1").variable(shape, TFloat32.DTYPE);

          Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
          Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

          Constant<TFloat32> cgrads = tf.constant(fgrads);
          float accum = 0.0F;
          float accumUpdate = 0.0F;

          /* build the GradsAnvVars */
          List<GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
          gradsAndVars.add(new GradAndVar<>(cgrads.asOutput(), var0.asOutput()));
          gradsAndVars.add(new GradAndVar<>(cgrads.asOutput(), var1.asOutput()));

          /*apply gradients */
          Op adadeltaUpdate = instance.applyGradients(gradsAndVars, "AdaDeltaTest");

          /* Create and validate the shapes of the slota */
          @SuppressWarnings("unchecked")
          Variable<TFloat32>[] slots = new Variable[2];
          @SuppressWarnings("unchecked")
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

          /* initialize the accumulators */
          session.run(tf.init());

          /* make sure the variables were initialized properly */
          session.evaluate(var0Init, var0);
          session.evaluate(var1Init, var1);

          float[] updates = new float[numUpdates];
          float totUpdate = 0;
          for (int step = 0; step < numUpdates; step++) {

            session.run(adadeltaUpdate, instance.getFeedMap());
            accum = accum * rho + (float) Math.pow(grad, 2) * (1.0F - rho);
            updates[step] =
                ((float) Math.sqrt(accumUpdate + epsilon)
                    * (float) (1 / Math.sqrt(accum + epsilon))
                    * grad);
            accumUpdate = (accumUpdate * rho + ((float) Math.pow(updates[step], 2) * (1.0F - rho)));
            totUpdate += updates[step] * lr;

            for (int i = 0; i < 2; i++) {
              session.evaluate(accum, slots[i]);
              session.evaluate(accumUpdate, slotUpdates[i]);
            }

            Float[] var0InitUpdate = {var0Init[0] - totUpdate, var0Init[1] - totUpdate};
            Float[] var1InitUpdate = {var1Init[0] - totUpdate, var1Init[1] - totUpdate};

            session.evaluate(var0InitUpdate, var0);
            session.evaluate(var1InitUpdate, var1);
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
      float learningRate = 1.0F;
      float rho = 0.95F;
      float epsilon = 1e-8F;
      try (TestSession session = TestSession.createTestSession(tfMode);
          AdaDelta instance = new AdaDelta(session.getGraph(), learningRate, rho, epsilon)) {
        Ops tf = session.getTF();

        float[] var0Init = {1.0F, 2.0F};
        float[] var1Init = {3.0F, 4.0F};
        float[] fgrads = {grad, grad};
        Shape shape = Shape.of(var0Init.length);
        Variable<TFloat32> var0 = tf.withName("var0").variable(shape, TFloat32.DTYPE);
        Variable<TFloat32> var1 = tf.withName("var1").variable(shape, TFloat32.DTYPE);

        Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
        Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

        Constant<TFloat32> cgrads = tf.constant(fgrads);

        float accum = 0.0F;
        float accumUpdate = 0.0F;

        /* build the GradsAnvVars */
        List<GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
        gradsAndVars.add(new GradAndVar<>(cgrads.asOutput(), var0.asOutput()));
        gradsAndVars.add(new GradAndVar<>(cgrads.asOutput(), var1.asOutput()));

        Op adadeltaUpdate = instance.applyGradients(gradsAndVars, "AdaDeltaTest");

        /* Create and validae the shapes of the slota */
        @SuppressWarnings("unchecked")
        Variable<TFloat32>[] slots = new Variable[2];
        @SuppressWarnings("unchecked")
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
        session.evaluate(var0Init, var0);
        session.evaluate(var1Init, var1);

        float[] updates = new float[numSteps];
        float totUpdate = 0;
        for (int step = 0; step < numSteps; step++) {
          assertEquals(learningRate, instance.getLearningRate(), epsilon);
          session.evaluate(
              learningRate, tf.identity(instance.getLearningRateOperand()), instance.getFeedMap());
          session.run(adadeltaUpdate, instance.getFeedMap());
          accum = accum * rho + (float) Math.pow(grad, 2) * (1.0F - rho);
          updates[step] =
              ((float) Math.sqrt(accumUpdate + epsilon)
                  * (float) (1 / Math.sqrt(accum + epsilon))
                  * grad);
          accumUpdate = (accumUpdate * rho + ((float) Math.pow(updates[step], 2) * (1.0F - rho)));
          totUpdate += updates[step] * learningRate;

          for (int i = 0; i < 2; i++) {
            session.evaluate(accum, slots[i]);
            session.evaluate(accumUpdate, slotUpdates[i]);
          }

          Float[] var0InitUpdate = {var0Init[0] - totUpdate, var0Init[1] - totUpdate};
          Float[] var1InitUpdate = {var1Init[0] - totUpdate, var1Init[1] - totUpdate};

          session.evaluate(var0InitUpdate, var0);
          session.evaluate(var1InitUpdate, var1);

          // Adjust learning rate
          learningRate *= 0.9F;
          instance.setLearningRate(learningRate);
        }
      }
    }
  }
}
