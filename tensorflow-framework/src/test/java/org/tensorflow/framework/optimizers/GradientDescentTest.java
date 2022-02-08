package org.tensorflow.framework.optimizers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Result;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.framework.initializers.Glorot;
import org.tensorflow.framework.initializers.VarianceScaling;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Mean;
import org.tensorflow.op.nn.Relu;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.GraphDef;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

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

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Graph graph = session.getGraph();

      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.class);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.class);

      tf.withInitScope().assign(var0, tf.constant(var0Init));
      tf.withInitScope().assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      GradientDescent instance = new GradientDescent(graph, learningRate);
      Op update = instance.applyGradients(gradsAndVars, "SGDTest");

      /* initialize the accumulators */
      session.initialize();

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

  // This test fails due to incorrect gradients being generated some of the time, when
  // using an identical graph on identical data. It should not, but it seems to be a
  // problem in TF-core.
  @Disabled
  @Test
  public void testDeterminism() {
    ConfigProto config =
        ConfigProto.newBuilder()
            .setIntraOpParallelismThreads(1)
            .setInterOpParallelismThreads(1)
            .build();

    GraphDef def;
    String trainName;
    String lossName;

    String fcWeightName, fcBiasName, outputWeightName, outputBiasName;

    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Glorot<TFloat32> initializer =
          new Glorot<>(VarianceScaling.Distribution.TRUNCATED_NORMAL, 1L);
      // Inputs
      Placeholder<TFloat32> input =
          tf.withName("input").placeholder(TFloat32.class, Placeholder.shape(Shape.of(-1, 20)));

      // Fully connected layer
      Variable<TFloat32> fcWeights =
          tf.withInitScope().variable(initializer.call(tf, tf.array(20L, 200L), TFloat32.class));
      fcWeightName = fcWeights.op().name();
      Variable<TFloat32> fcBiases =
          tf.withInitScope().variable(tf.fill(tf.array(200), tf.constant(0.1f)));
      fcBiasName = fcBiases.op().name();
      Relu<TFloat32> relu = tf.nn.relu(tf.math.add(tf.linalg.matMul(input, fcWeights), fcBiases));

      // Output layer
      Variable<TFloat32> outputWeights =
          tf.withInitScope().variable(initializer.call(tf, tf.array(200L, 2L), TFloat32.class));
      outputWeightName = outputWeights.op().name();
      Variable<TFloat32> outputBiases =
          tf.withInitScope().variable(tf.fill(tf.array(2L), tf.constant(0.1f)));
      outputBiasName = outputBiases.op().name();
      Add<TFloat32> output = tf.math.add(tf.linalg.matMul(relu, outputWeights), outputBiases);

      // AbstractLoss
      Placeholder<TFloat32> placeholder =
          tf.withName("output").placeholder(TFloat32.class, Placeholder.shape(Shape.of(-1, 2)));
      Mean<TFloat32> loss =
          tf.math.mean(
              tf.nn.softmaxCrossEntropyWithLogits(output, placeholder).loss(), tf.constant(0));
      lossName = loss.op().name();

      GradientDescent gd = new GradientDescent(g, 10.0f);
      Op trainingOp = gd.minimize(loss);
      trainName = trainingOp.op().name();

      def = g.toGraphDef();
    }

    float[] data =
        new float[] {
          1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, -8.0f, -9.0f, 10.0f, 11.0f, 12.0f, 13.0f,
          -14.0f, -15.0f, 0.16f, 0.17f, 0.18f, 1.9f, 0.2f
        };
    TFloat32 dataTensor = TFloat32.tensorOf(Shape.of(1, 20), DataBuffers.of(data));
    float[] target = new float[] {0.2f, 0.8f};
    TFloat32 targetTensor = TFloat32.tensorOf(Shape.of(1, 2), DataBuffers.of(target));

    int numRuns = 20;
    List<List<Tensor>> initialized = new ArrayList<>(numRuns);
    List<List<Tensor>> trained = new ArrayList<>(numRuns);
    float[] initialLoss = new float[numRuns];
    float[] postTrainingLoss = new float[numRuns];

    for (int i = 0; i < numRuns; i++) {
      try (Graph g = new Graph();
          Session s = new Session(g, config)) {
        g.importGraphDef(def);
        s.initialize();

        Result initializationRes =
            s.runner()
                .fetch(fcWeightName)
                .fetch(fcBiasName)
                .fetch(outputWeightName)
                .fetch(outputBiasName)
                .run();
        List<Tensor> initializedRun = new ArrayList<>();
        for (Map.Entry<String, Tensor> e : initializationRes) {
          initializedRun.add(e.getValue());
        }
        initialized.add(initializedRun);

        TFloat32 lossVal =
            (TFloat32)
                s.runner()
                    .addTarget(trainName)
                    .feed("input", dataTensor)
                    .feed("output", targetTensor)
                    .fetch(lossName)
                    .run()
                    .get(0);
        initialLoss[i] = lossVal.getFloat();
        lossVal.close();

        Result trainedRes =
            s.runner()
                .fetch(fcWeightName)
                .fetch(fcBiasName)
                .fetch(outputWeightName)
                .fetch(outputBiasName)
                .run();
        List<Tensor> trainedRun = new ArrayList<>();
        for (Map.Entry<String, Tensor> e : trainedRes) {
          trainedRun.add(e.getValue());
        }
        trained.add(trainedRun);

        lossVal =
            (TFloat32)
                s.runner()
                    .addTarget(trainName)
                    .feed("input", dataTensor)
                    .feed("output", targetTensor)
                    .fetch(lossName)
                    .run()
                    .get(0);
        postTrainingLoss[i] = lossVal.getFloat();
        lossVal.close();
      }
    }

    for (int i = 1; i < numRuns; i++) {
      assertEquals(initialLoss[0], initialLoss[i]);
      assertEquals(postTrainingLoss[0], postTrainingLoss[i]);
      // Because the weights are references not copies.
      assertEquals(initialized.get(i), trained.get(i));
      assertEquals(
          initialized.get(0),
          initialized.get(i),
          "Variables not initialized identically (0," + i + ")");
      assertEquals(
          trained.get(0), trained.get(i), "Variables not trained identically (0," + i + ")");
    }

    for (List<Tensor> curInit : initialized) {
      for (Tensor t : curInit) {
        t.close();
      }
    }
    for (List<Tensor> curTrained : trained) {
      for (Tensor t : curTrained) {
        t.close();
      }
    }
  }
}
