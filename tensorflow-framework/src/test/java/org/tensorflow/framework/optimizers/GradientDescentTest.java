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

/** Test cases for GradientDescent Optimizer */
public class GradientDescentTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  public GradientDescentTest() {}

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
      GradientDescent instance = new GradientDescent(graph);
      String expResult = "GradientDescent";
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

    try (TestSession session = TestSession.createTestSession(tfMode);
        GradientDescent instance = new GradientDescent(session.getGraph(), learningRate); ) {
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

      Op update = instance.applyGradients(gradsAndVars, "SGDTest");

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      /* make sure the variables were initialized properly */
      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      session.run(update, instance.getFeedMap()); // 1 step

      float[] expectedVar0 = {1.0F - 3.0F * 0.1F, 2.0F - 3.0F * 0.1F};
      float[] expectedVar1 = {3.0F - 3.0F * 0.01F, 4.0F - 3.0F * 0.01F};
      session.evaluate(expectedVar0, var0);
      session.evaluate(expectedVar1, var1);
    }
  }

  @Test
  public void testWithLearningRateDecay() {
    int numSteps = 2;
    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {3.0F, 4.0F};
    float[] grads0Init = {0.1F, 0.1F};
    float[] grads1Init = {0.01F, 0.01F};

    float learningRate = 3.0F;

    try (TestSession session = TestSession.createTestSession(tfMode);
        GradientDescent instance = new GradientDescent(session.getGraph(), learningRate)) {
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

      Op update = instance.applyGradients(gradsAndVars, "GradientDescentTest");


      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      // initialize the accumulators
      session.run(tf.init());

      // make sure the variables were initialized properly
      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

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
        assertEquals(learningRate, instance.getLearningRate(), 1e-6f);
        session.evaluate(learningRate, tf.identity(instance.getLearningRateOperand()), instance.getFeedMap());
        session.run(update, instance.getFeedMap());
        session.evaluate(expectedVar0[step], var0);
        session.evaluate(expectedVar1[step], var1);
        learningRate *= 0.1;
        instance.setLearningRate(learningRate);
      }
    }
  }
}
