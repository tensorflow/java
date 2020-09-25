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

/** Test cases for AdaGradDA Optimizer */
public class AdaGradDATest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  public AdaGradDATest() {}

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
    float[] var0Init = {0.0F, 0.0F};
    float[] var1Init = {0.0F, 0.0F};
    float[] grads0Init = {0.1F, 0.2F};
    float[] grads1Init = {0.01F, 0.02F};
    float learningRate = 3.0F;
    try (TestSession session = TestSession.createTestSession(tfMode);
        AdaGradDA instance = new AdaGradDA(session.getGraph(), learningRate)) {

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
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op adaUpdate = instance.applyGradients(gradsAndVars, "AdGradDATest");

      /* initialize the accumulators */
      session.run(tf.init());

      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);
      session.run(adaUpdate, instance.getFeedMap());
      float[] expected0 = {-0.904534F, -1.603567F};
      session.evaluate(expected0, var0);
      float[] expected1 = {-0.094821f, -0.189358f};
      session.evaluate(expected1, var1);
    }
  }

  @Test
  public void testBasicWithLROperand() {
    float[] var0Init = {0.0F, 0.0F};
    float[] var1Init = {0.0F, 0.0F};
    float[] grads0Init = {0.1F, 0.2F};
    float[] grads1Init = {0.01F, 0.02F};
    float learningRate = 1.5F;
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      try (AdaGradDA instance =
          new AdaGradDA(
              session.getGraph(), tf.math.mul(tf.constant(learningRate), tf.constant(2.f)))) {

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

        Op adaUpdate = instance.applyGradients(gradsAndVars, "AdGradDATest");

        /* initialize the accumulators */
        session.run(tf.init());

        session.evaluate(var0Init, var0);
        session.evaluate(var1Init, var1);
        session.run(adaUpdate, instance.getFeedMap());
        float[] expected0 = {-0.904534F, -1.603567F};
        session.evaluate(expected0, var0);
        float[] expected1 = {-0.094821f, -0.189358f};
        session.evaluate(expected1, var1);
      }
    }
  }

  @Test
  public void testWithLearningRateDecay() {
    float[] var0Init = {0.0F, 0.0F};
    float[] var1Init = {0.0F, 0.0F};
    float[] grads0Init = {0.1F, 0.2F};
    float[] grads1Init = {0.01F, 0.02F};
    float epsilon = 1e-8F;
    int numSteps = 4;
    float learningRate = 3.0F;
    try (TestSession session = TestSession.createTestSession(tfMode);
        AdaGrad instance = new AdaGrad(session.getGraph(), learningRate)) {
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
      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op update = instance.applyGradients(gradsAndVars, "AdGradDATest");

      /** initialize the accumulators */
      session.run(tf.init());

      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      float[][][] expected = {
        {{-2.121320f, -2.683281f}, {-0.298511f, -0.588348f}},
        {{-3.680166f, -4.483282f}, {-0.565851f, -1.107964f}},
        {{-4.895166f, -5.831203f}, {-0.805286f, -1.567190f}},
        {{-5.873222f, -6.892054f}, {-1.019739f, -1.973306f}}
      };
      for (int i = 0; i < numSteps; i++) {
        assertEquals(learningRate, instance.getLearningRate(), epsilon);
        session.evaluate(
            learningRate, tf.identity(instance.getLearningRateOperand()), instance.getFeedMap());
        session.run(update, instance.getFeedMap());
        session.evaluate(expected[i][0], var0);
        session.evaluate(expected[i][1], var1);
        learningRate *= 0.9;
        instance.setLearningRate(learningRate);
      }
    }
  }
}
