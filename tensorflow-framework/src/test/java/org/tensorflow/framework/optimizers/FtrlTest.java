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

/** Test cases for Ftrl Optimizer */
public class FtrlTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  public FtrlTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of getOptimizerName method, of class Ftrl. */
  @Test
  public void testGetOptimizerName() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Ftrl instance = new Ftrl(graph);
      String expResult = "Ftrl";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  @Test
  public void testFtrlWithL1L2L2Shrinkage() {
    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {4.0F, 3.0F};
    float[] grads0Init = {0.1F, 0.2F};
    float[] grads1Init = {0.01F, 0.02F};
    float learningRate = 3.0F;

    int numSteps = 10;

    try (TestSession session = TestSession.createTestSession(tfMode);
        Ftrl instance =
            new Ftrl(
                session.getGraph(),
                learningRate,
                -0.5F, // learningRatePower
                0.1F, // initialAccumulatorValue
                0.001F, // l1RegularizationStrength
                2.0F, // l2RegularizationStrength
                0.1F // l2ShrinkageRegularizationStrength
                )) {
      Ops tf = session.getTF();

      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op ftrlUpdate = instance.applyGradients(gradsAndVars, "FtrlTest");

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      for (int i = 0; i < numSteps; i++) {
        session.run(ftrlUpdate, instance.getFeedMap());
      }

      float[] expectedVar0 = {-0.22578995F, -0.44345796F};
      session.evaluate(expectedVar0, var0);
      float[] expectedVar1 = {-0.14378493F, -0.13229476F};
      session.evaluate(expectedVar1, var1);
    }
  }

  @Test
  public void testFtrlWithL1() {
    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {4.0F, 3.0F};
    float[] grads0Init = {0.1F, 0.2F};
    float[] grads1Init = {0.01F, 0.02F};

    int numSteps = 10;

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

      float learningRate = 3.0F;

      Ftrl instance =
          new Ftrl(
              graph,
              learningRate,
              Ftrl.LEARNING_RATE_POWER_DEFAULT, // learningRatePower
              0.1F, // initialAccumulatorValue
              0.001F, // l1RegularizationStrength
              0.0F, // l2RegularizationStrength
              Ftrl.L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT // l2ShrinkageRegularizationStrength
              );

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op ftrlUpdate = instance.applyGradients(gradsAndVars, "FtrlTest");

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      for (int i = 0; i < numSteps; i++) {
        session.run(ftrlUpdate, instance.getFeedMap());
      }

      float[] expectedVar0 = {-7.66718769F, -10.91273689F};
      session.evaluate(expectedVar0, var0);

      float[] expectedVar1 = {-0.93460727F, -1.86147261F};
      session.evaluate(expectedVar1, var1);
    }
  }

  @Test
  public void testFtrlWithL1L2() {
    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {4.0F, 3.0F};
    float[] grads0Init = {0.1F, 0.2F};
    float[] grads1Init = {0.01F, 0.02F};

    int numSteps = 10;

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

      float learningRate = 3.0F;

      Ftrl instance =
          new Ftrl(
              graph,
              learningRate,
              Ftrl.LEARNING_RATE_POWER_DEFAULT, // learningRatePower
              0.1F, // initialAccumulatorValue
              0.001F, // l1RegularizationStrength
              2.0F, // l2RegularizationStrength
              Ftrl.L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT // l2ShrinkageRegularizationStrength
              );

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op ftrlUpdate = instance.applyGradients(gradsAndVars, "FtrlTest");

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      for (int i = 0; i < numSteps; i++) {
        session.run(ftrlUpdate, instance.getFeedMap());
      }

      float[] expectedVar0 = {-0.24059935F, -0.46829352F};
      session.evaluate(expectedVar0, var0);

      float[] expectedVar1 = {-0.02406147F, -0.04830509F};
      session.evaluate(expectedVar1, var1);
    }
  }

  @Test
  public void testChangingLearningRate() {
    float learningRate = 3.0F;
    float epsilon = 1e-8f;
    try (TestSession session = TestSession.createTestSession(tfMode);
        Ftrl instance =
            new Ftrl(
                session.getGraph(),
                learningRate,
                Ftrl.LEARNING_RATE_POWER_DEFAULT,
                0.1F,
                0.001F,
                2.0F,
                Ftrl.L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT)) {
      Ops tf = session.getTF();
      int numSteps = 10;

      float[] var0Init = {1.0F, 2.0F};
      float[] var1Init = {4.0F, 3.0F};
      float[] grads0Init = {0.1F, 0.2F};
      float[] grads1Init = {0.01F, 0.02F};
      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op ftrlUpdate = instance.applyGradients(gradsAndVars, "FtrlTest");

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      // initialize the accumulators
      session.run(tf.init());
      float expected[][][] = {
        // Step: 0
        {{-0.022833f, -0.038881f}, {-0.002141f, -0.004474f}},
        // Step: 1
        {{-0.203440f, -0.336012f}, {-0.021145f, -0.042048f}},
        // Step: 2
        {{-0.667457f, -0.962730f}, {-0.074745f, -0.147685f}},
        // Step: 3
        {{-0.854973f, -1.163154f}, {-0.099466f, -0.196172f}},
        // Step: 4
        {{-0.878825f, -1.186153f}, {-0.102859f, -0.202808f}},
        // Step: 5
        {{-0.881205f, -1.188360f}, {-0.103211f, -0.203495f}},
        // Step: 6
        {{-0.881436f, -1.188569f}, {-0.103246f, -0.203564f}},
        // Step: 7
        {{-0.881459f, -1.188589f}, {-0.103250f, -0.203571f}},
        // Step: 8
        {{-0.881461f, -1.188591f}, {-0.103250f, -0.203572f}},
        // Step: 9
        {{-0.881462f, -1.188591f}, {-0.103250f, -0.203572f}},
      };
      for (int i = 0; i < numSteps; i++) {
        assertEquals(learningRate, instance.getLearningRate(), epsilon);
        session.evaluate(
            learningRate, tf.identity(instance.getLearningRateOperand()), instance.getFeedMap());
        session.run(ftrlUpdate, instance.getFeedMap());
        session.evaluate(expected[i][0], var0);
        session.evaluate(expected[i][1], var1);
        learningRate *= 0.1f;
        instance.setLearningRate(learningRate);
      }
    }
  }

  @Test
  public void doTestFtrlwithoutRegularization() {
    float[] var0Init = {0.0F, 0.0F};
    float[] var1Init = {0.0F, 0.0F};
    float[] grads0Init = {0.1F, 0.2F};
    float[] grads1Init = {0.01F, 0.02F};

    int numSteps = 3;
    float learningRate = 3.0f;

    try (TestSession session = TestSession.createTestSession(tfMode);
        Ftrl instance = new Ftrl(session.getGraph(), learningRate)) {
      Ops tf = session.getTF();

      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));
      Op ftrlUpdate = instance.applyGradients(gradsAndVars, "FtrlTest");

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      for (int i = 0; i < numSteps; i++) {
        session.run(ftrlUpdate, instance.getFeedMap());
      }

      float[] expectedVar0 = {-2.60260963F, -4.29698515F};
      float[] expectedVar1 = {-0.28432083F, -0.56694895F};

      session.evaluate(expectedVar0, var0);
      session.evaluate(expectedVar1, var1);
    }
  }
}
