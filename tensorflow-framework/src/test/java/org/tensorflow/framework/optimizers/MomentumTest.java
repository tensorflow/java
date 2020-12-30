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
import static org.tensorflow.framework.optimizers.Momentum.MOMENTUM;

/** Test cases for SGD Optimizer */
public class MomentumTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  public MomentumTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of getOptimizerName method, of class SGD. */
  @Test
  public void testGetOptimizerName() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Momentum instance = new Momentum(graph);
      String expResult = "Momentum";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  @Test
  public void testBasic() {
    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {3.0F, 4.0F};
    float[] grads0Init = {0.1F, 0.1F};
    float[] grads1Init = {0.01F, 0.01F};
    float learningRate = 3.0F;

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Graph graph = session.getGraph();

      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.class);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.class);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Momentum instance = new Momentum(graph, learningRate);
      Op update = instance.applyGradients(gradsAndVars, "SGDTest");

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      /* make sure the variables were initialized properly */
      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      session.run(update); // 1 step

      float[] expectedVar0 = {1.0F - 3.0F * 0.1F, 2.0F - 3.0F * 0.1F};
      float[] expectedVar1 = {3.0F - 3.0F * 0.01F, 4.0F - 3.0F * 0.01F};
      session.evaluate(expectedVar0, var0);
      session.evaluate(expectedVar1, var1);
    }
  }

  @Test
  public void testMomentum() {
    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {3.0F, 4.0F};
    float[] grads0Init = {0.1F, 0.1F};
    float[] grads1Init = {0.01F, 0.01F};

    float learningRate = 2.0F;
    float momentum = 0.9F;

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Graph graph = session.getGraph();

      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.class);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.class);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Momentum instance = new Momentum(graph, learningRate, momentum);
      Op update = instance.applyGradients(gradsAndVars, "SGDTest");

      Variable<TFloat32> momentumSlot0 = instance.getSlot(var0.asOutput(), MOMENTUM).get();
      assertEquals(momentumSlot0.shape(), var0.shape());
      Variable<TFloat32> momentumSlot1 = instance.getSlot(var1.asOutput(), MOMENTUM).get();
      assertEquals(momentumSlot1.shape(), var1.shape());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      /* make sure the variables were initialized properly */
      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      session.run(update); // 1 step

      float[] expectedMomentum0 = {0.1F, 0.1F};
      float[] expectedMomentum1 = {0.01F, 0.01F};
      session.evaluate(expectedMomentum0, momentumSlot0);
      session.evaluate(expectedMomentum1, momentumSlot1);

      float[] expectedVar0 = {1.0F - (0.1F * 2.0F), 2.0F - (0.1F * 2.0F)};
      float[] expectedVar1 = {3.0F - (0.01F * 2.0F), 4.0F - (0.01F * 2.0F)};
      session.evaluate(expectedVar0, var0);
      session.evaluate(expectedVar1, var1);

      session.run(update); // step 2

      float[] expectedMomentum02 = {(0.9f * 0.1f + 0.1f), (0.9f * 0.1f + 0.1f)};
      float[] expectedMomentum12 = {(0.9f * 0.01f + 0.01f), (0.9f * 0.01f + 0.01f)};
      session.evaluate(expectedMomentum02, momentumSlot0);
      session.evaluate(expectedMomentum12, momentumSlot1);

      float[] expectedVar02 = {
        1.0F - (0.1F * 2.0F) - ((0.9F * 0.1F + 0.1F) * 2.0F),
        2.0F - (0.1F * 2.0F) - ((0.9F * 0.1F + 0.1F) * 2.0F)
      };
      float[] expectedVar12 = {
        2.98F - ((0.9F * 0.01F + 0.01F) * 2.0F), 3.98F - ((0.9F * 0.01F + 0.01F) * 2.0F)
      };
      session.evaluate(expectedVar02, var0);
      session.evaluate(expectedVar12, var1);
    }
  }
}
