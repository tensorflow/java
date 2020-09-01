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
import static org.tensorflow.framework.optimizers.Momentum.MOMENTUM;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;
import static org.tensorflow.keras.optimizers.SGD.*;

/** Test cases for SGD Optimizer */
public class SGDTest {

  private TestSession.Mode tf_mode = TestSession.Mode.GRAPH;

  int index;

  public SGDTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of create method, of class SGD. */
  @Test
  public void testCreate() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      Map<String, Object> config = new HashMap<>();
      config.put(NAME_KEY, "Ftrl");
      config.put(LEARNING_RATE_KEY, 2.0F);
      config.put(MOMENTUM_KEY, MOMENTUM_DEFAULT);
      config.put(NESTEROV_KEY, NESTEROV_DEFAULT);
      SGD expResult = new SGD(tf, 2.0F);
      SGD result = SGD.create(tf, config);
      assertEquals(expResult.getConfig(), result.getConfig());
    }
  }

  /** Test of getOptimizerName method, of class SGD. */
  @Test
  public void testGetOptimizerName() {
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();
      SGD instance = new SGD(tf);
      String expResult = "SGD";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  @Test
  public void testBasic() {
    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};
    float learningRate = 3.0F;

    float epsilon = 1e-6F;
    float epsilon1 = 1e-2F;

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

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      SGD instance = new SGD(tf, learningRate);
      Op update = instance.applyGradients(gradsAndVars, "SGDTest");

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /** initialize the accumulators */
      session.run(tf.init());

      /** make sure the variables were initialized properly */
      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      session.run(update, instance.getFeedDict()); // 1 step

      float[] expectedVar0 = {1.0F - 3.0F * 0.1F, 2.0F - 3.0F * 0.1F};
      float[] expectedVar1 = {3.0F - 3.0F * 0.01F, 4.0F - 3.0F * 0.01F};
      session.evaluate(expectedVar0, var0);
      session.evaluate(expectedVar1, var1);
    }
  }

  @Test
  public void testMomentum() {
    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};

    float learningRate = 2.0F;
    float momentum = 0.9F;

    float epsilon = 1e-6F;
    float epsilon1 = 1e-2F;

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

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      SGD instance = new SGD(tf, learningRate, momentum);
      Op update = instance.applyGradients(gradsAndVars, "SGDTest");

      Variable<TFloat32> momentumSlot0 = instance.getSlot(var0.asOutput(), MOMENTUM).get();
      assertEquals(momentumSlot0.asOutput().shape(), var0.asOutput().shape());
      Variable<TFloat32> momentumSlot1 = instance.getSlot(var1.asOutput(), MOMENTUM).get();
      assertEquals(momentumSlot1.asOutput().shape(), var1.asOutput().shape());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /** initialize the accumulators */
      session.run(tf.init());

      /** make sure the variables were initialized properly */
      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      session.run(update, instance.getFeedDict()); // 1 step

      float[] expectedMomentum0 = {0.1F, 0.1F};
      float[] expectedMomentum1 = {0.01F, 0.01F};
      session.evaluate(expectedMomentum0, momentumSlot0);
      session.evaluate(expectedMomentum1, momentumSlot1);

      float[] expectedVar0 = {1.0F - (0.1F * 2.0F), 2.0F - (0.1F * 2.0F)};
      float[] expectedVar1 = {3.0F - (0.01F * 2.0F), 4.0F - (0.01F * 2.0F)};
      session.evaluate(expectedVar0, var0);
      session.evaluate(expectedVar1, var1);

      session.run(update, instance.getFeedDict()); // step 2

      float[] expectedMomentum0_2 = {(0.9f * 0.1f + 0.1f), (0.9f * 0.1f + 0.1f)};
      float[] expectedMomentum1_2 = {(0.9f * 0.01f + 0.01f), (0.9f * 0.01f + 0.01f)};
      session.evaluate(expectedMomentum0_2, momentumSlot0);
      session.evaluate(expectedMomentum1_2, momentumSlot1);

      float[] expectedVar0_2 = {
        1.0F - (0.1F * 2.0F) - ((0.9F * 0.1F + 0.1F) * 2.0F),
        2.0F - (0.1F * 2.0F) - ((0.9F * 0.1F + 0.1F) * 2.0F)
      };
      float[] expectedVar1_2 = {
        2.98F - ((0.9F * 0.01F + 0.01F) * 2.0F), 3.98F - ((0.9F * 0.01F + 0.01F) * 2.0F)
      };
      session.evaluate(expectedVar0_2, var0);
      session.evaluate(expectedVar1_2, var1);
    }
  }

  @Test
  public void testWithLearningRateDecay() {
    int numSteps = 2;
    float[] var0_init = {1.0F, 2.0F};
    float[] var1_init = {3.0F, 4.0F};
    float[] grads0_init = {0.1F, 0.1F};
    float[] grads1_init = {0.01F, 0.01F};

    float learningRate = 3.0F;

    float epsilon = 1e-6F;
    float epsilon1 = 1e-2F;
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

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      SGD instance = new SGD(tf, learningRate);
      Op update = instance.applyGradients(gradsAndVars, "SGDTest");

      Variable<TFloat32> momentumSlot0 = instance.getSlot(var0.asOutput(), MOMENTUM).get();
      assertEquals(momentumSlot0.asOutput().shape(), var0.asOutput().shape());
      Variable<TFloat32> momentumSlot1 = instance.getSlot(var1.asOutput(), MOMENTUM).get();
      assertEquals(momentumSlot1.asOutput().shape(), var1.asOutput().shape());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /** initialize the accumulators */
      session.run(tf.init());

      /** make sure the variables were initialized properly */
      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);

      float[][] expectedVar0 = {
        {0.7F, 1.7F},
        {0.66999996F, 1.6700001F},
        {0.66699994F, 1.667F},
        {0.66669995F, 1.6667F},
        {0.66666996F, 1.66667F}
      };
      float[][] expectedVar1 = {
        {2.97F, 3.97F},
        {2.967F, 3.967F},
        {2.9667F, 3.9667F},
        {2.96667F, 3.96667F},
        {2.966667F, 3.966667F}
      };
      for (int step = 0; step < numSteps; step++) {
        session.run(update, instance.getFeedDict());
        session.evaluate(expectedVar0[step], var0);
        session.evaluate(expectedVar1[step], var1);
        learningRate *= 0.1;
        instance.setLearningRate(learningRate);
      }
    }
  }
}
