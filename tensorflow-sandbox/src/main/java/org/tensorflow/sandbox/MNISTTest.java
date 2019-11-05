/*
 * Copyright © 2019, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.sandbox;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.OneHot;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Mean;
import org.tensorflow.op.nn.Conv2d;
import org.tensorflow.op.nn.MaxPool;
import org.tensorflow.op.nn.Relu;
import org.tensorflow.op.nn.Softmax;
import org.tensorflow.op.nn.SoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.random.TruncatedNormal;
import org.tensorflow.sandbox.optimizers.AdaDelta;
import org.tensorflow.sandbox.optimizers.AdaGrad;
import org.tensorflow.sandbox.optimizers.AdaGradDA;
import org.tensorflow.sandbox.optimizers.Adam;
import org.tensorflow.sandbox.optimizers.GradientDescent;
import org.tensorflow.sandbox.optimizers.Momentum;
import org.tensorflow.sandbox.optimizers.Optimizer;
import org.tensorflow.sandbox.optimizers.RMSProp;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.TInt32;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Builds a LeNet-5 style CNN for MNIST.
 */
public class MNISTTest {

  private static final Logger logger = Logger.getLogger(MNISTTest.class.getName());

  private static final int PIXEL_DEPTH = 255;
  private static final int NUM_CHANNELS = 1;
  private static final int IMAGE_SIZE = 28;
  private static final int NUM_LABELS = 10;
  private static final long SEED = 123456789L;

  private static final String PADDING_TYPE = "SAME";

  public static final String INPUT_NAME = "input";
  public static final String OUTPUT_NAME = "output";
  public static final String TARGET = "target";
  public static final String TRAIN = "train";
  public static final String TRAINING_LOSS = "training_loss";
  public static final String EPOCH = "epoch";
  public static final String INIT = "init";

  public static Graph build(String optimizerName) {
    Graph graph = new Graph();

    Ops tf = Ops.create(graph);

    // Inputs
    Placeholder<TFloat> input = tf.withName(INPUT_NAME).placeholder(TFloat.DTYPE, Placeholder.shape(Shape.make(-1, IMAGE_SIZE, IMAGE_SIZE, NUM_CHANNELS)));
    Placeholder<TInt32> labels = tf.withName(TARGET).placeholder(TInt32.DTYPE);

    // Scaling the features
    Constant<TFloat> centeringFactor = tf.constant(PIXEL_DEPTH / 2.0f);
    Constant<TFloat> scalingFactor = tf.constant((float) PIXEL_DEPTH);
    Operand<TFloat> scaledInput = tf.math.div(tf.math.add(input, centeringFactor), scalingFactor);

    // First conv layer
    Variable<TFloat> conv1Weights = tf.variable(Shape.make(5, 5, NUM_CHANNELS, 32), TFloat.DTYPE);
    Assign<TFloat> weights1Init = tf.assign(conv1Weights, tf.math.mul(tf.random.truncatedNormal(tf.shape(conv1Weights), TFloat.DTYPE, TruncatedNormal.seed(SEED)), tf.constant(0.1f)));
    graph.addInitializer(weights1Init);
    Conv2d<TFloat> conv1 = tf.nn.conv2d(scaledInput, conv1Weights, Arrays.asList(1L, 1L, 1L, 1L), PADDING_TYPE);
    Variable<TFloat> conv1Biases = tf.variable(Shape.make(32), TFloat.DTYPE);
    Assign<TFloat> biases1Init = tf.assign(conv1Biases, tf.fill(tf.shape(conv1Biases), tf.constant(0.0f)));
    graph.addInitializer(biases1Init);
    Relu<TFloat> relu1 = tf.nn.relu(tf.nn.biasAdd(conv1, conv1Biases));

    // First pooling layer
    MaxPool<TFloat> pool1 = tf.nn.maxPool(relu1, tf.constant(new int[]{1, 2, 2, 1}), tf.constant(new int[]{1, 2, 2, 1}), PADDING_TYPE);

    // Second conv layer
    Variable<TFloat> conv2Weights = tf.variable(Shape.make(5, 5, 32, 64), TFloat.DTYPE);
    Assign<TFloat> weights2Init = tf.assign(conv2Weights, tf.math.mul(tf.random.truncatedNormal(tf.shape(conv2Weights), TFloat.DTYPE, TruncatedNormal.seed(SEED)), tf.constant(0.1f)));
    graph.addInitializer(weights2Init);
    Conv2d<TFloat> conv2 = tf.nn.conv2d(pool1, conv2Weights, Arrays.asList(1L, 1L, 1L, 1L), PADDING_TYPE);
    Variable<TFloat> conv2Biases = tf.variable(Shape.make(64), TFloat.DTYPE);
    Assign<TFloat> biases2Init = tf.assign(conv2Biases, tf.fill(tf.shape(conv2Biases), tf.constant(0.1f)));
    graph.addInitializer(biases2Init);
    Relu<TFloat> relu2 = tf.nn.relu(tf.nn.biasAdd(conv2, conv2Biases));

    // Second pooling layer
    MaxPool<TFloat> pool2 = tf.nn.maxPool(relu2, tf.constant(new int[]{1, 2, 2, 1}), tf.constant(new int[]{1, 2, 2, 1}), PADDING_TYPE);

    // Flatten inputs
    Reshape<TFloat> flatten = tf.reshape(pool2, tf.concat(Arrays.asList(tf.slice(tf.shape(pool2), tf.constant(new int[]{0}), tf.constant(new int[]{1})), tf.constant(new int[]{-1})), tf.constant(0)));

    // Fully connected layer
    Variable<TFloat> fc1Weights = tf.variable(Shape.make(IMAGE_SIZE * IMAGE_SIZE * 4, 512), TFloat.DTYPE);
    Assign<TFloat> weights3Init = tf.assign(fc1Weights, tf.math.mul(tf.random.truncatedNormal(tf.shape(fc1Weights), TFloat.DTYPE, TruncatedNormal.seed(SEED)), tf.constant(0.1f)));
    graph.addInitializer(weights3Init);
    Variable<TFloat> fc1Biases = tf.variable(Shape.make(512), TFloat.DTYPE);
    Assign<TFloat> biases3Init = tf.assign(fc1Biases, tf.broadcastTo(tf.constant(0.1f), tf.shape(fc1Biases)));
    graph.addInitializer(biases3Init);
    Relu<TFloat> relu3 = tf.nn.relu(tf.math.add(tf.linalg.matMul(flatten, fc1Weights), fc1Biases));

    // Softmax layer
    Variable<TFloat> fc2Weights = tf.variable(Shape.make(512, NUM_LABELS), TFloat.DTYPE);
    Assign<TFloat> weights4Init = tf.assign(fc2Weights, tf.math.mul(tf.random.truncatedNormal(tf.shape(fc2Weights), TFloat.DTYPE, TruncatedNormal.seed(SEED)), tf.constant(0.1f)));
    graph.addInitializer(weights4Init);
    Variable<TFloat> fc2Biases = tf.variable(Shape.make(NUM_LABELS), TFloat.DTYPE);
    Assign<TFloat> biases4Init = tf.assign(fc2Biases, tf.broadcastTo(tf.constant(0.1f), tf.shape(fc2Biases)));
    graph.addInitializer(biases4Init);

    Add<TFloat> logits = tf.math.add(tf.linalg.matMul(relu3, fc2Weights), fc2Biases);

    // Predicted outputs
    Softmax<TFloat> prediction = tf.withName(OUTPUT_NAME).nn.softmax(logits);

    // Loss function & regularization
    OneHot<TFloat> oneHot = tf.oneHot(labels, tf.constant(10), tf.constant(1.0f), tf.constant(0.0f));
    SoftmaxCrossEntropyWithLogits<TFloat> batchLoss = tf.nn.softmaxCrossEntropyWithLogits(logits, oneHot);
    Mean<TFloat> labelLoss = tf.math.mean(batchLoss.loss(), tf.constant(0));
    Add<TFloat> regularizers = tf.math.add(tf.nn.l2Loss(fc1Weights), tf.math.add(tf.nn.l2Loss(fc1Biases), tf.math.add(tf.nn.l2Loss(fc2Weights), tf.nn.l2Loss(fc2Biases))));
    Add<TFloat> loss = tf.withName(TRAINING_LOSS).math.add(labelLoss, tf.math.mul(regularizers, tf.constant(5e-4f)));

    // Optimizer
    Optimizer optimizer;
    switch (optimizerName) {
      case "AdaDelta":
      case "Adadelta":
      case "adadelta":
        optimizer = new AdaDelta(graph, 1f, 0.95f, 1e-8f);
        break;
      case "AdaGradDA":
      case "AdagradDA":
      case "adagradda":
        optimizer = new AdaGradDA(graph, 0.01f);
        break;
      case "AdaGrad":
      case "Adagrad":
      case "adagrad":
        optimizer = new AdaGrad(graph, 0.01f);
        break;
      case "Adam":
      case "adam":
        optimizer = new Adam(graph,0.001f,0.9f,0.999f,1e-8f);
        break;
      case "SGD":
      case "sgd":
        optimizer = new GradientDescent(graph,0.01f);
        break;
      case "Momentum":
      case "momentum":
        optimizer = new Momentum(graph, 0.01f, 0.9f, false);
        break;
      case "RMSProp":
      case "rmsprop":
        optimizer = new RMSProp(graph,0.01f, 0.9f, 0.0f, 1e-10f, false);
        break;
      default:
        throw new IllegalArgumentException("Unknown optimizer " + optimizerName);
    }
    logger.info("Optimizer = " + optimizer.toString());
    Op minimize = optimizer.minimize(loss, TRAIN);

    Op init = graph.variablesInitializer();

    return graph;
  }

  public static void train(Session session, int epochs, int minibatchSize, float[][][][] data, int[] labels) {
    // Initialises the parameters.
    session.runner().addTarget(INIT).run();
    logger.info("Initialised the model parameters");

    float[][][][] featureBatch = new float[minibatchSize][][][];
    int[] labelBatch = new int[minibatchSize];

    int interval = 0;
    for (int i = 0; i < epochs; i++) {
      logger.log(Level.INFO, "Starting epoch " + i);
      //Tensor<?> epoch = Tensor.create(i);
      for (int j = 0; j < data.length; j += minibatchSize) {
        for (int k = j, m = 0; k < (j + minibatchSize) && k < data.length; k++, m++) {
          featureBatch[m] = data[k];
          labelBatch[m] = labels[k];
        }
        //logger.info("Batch = " + batch.size());
        Tensor<?> input = Tensor.create(featureBatch);
        Tensor<?> target = Tensor.create(labelBatch);
        Tensor<?> loss = session.runner()
            .feed(INPUT_NAME, input)
            .feed(TARGET, target)
            .addTarget(TRAIN)
            .fetch(TRAINING_LOSS)
            .run().get(0);
        if (interval % 100 == 0) {
          logger.log(Level.INFO, "Iteration = " + interval + ", training loss = " + loss.floatValue());
        }
        input.close();
        target.close();
        loss.close();
        interval++;
      }
      //epoch.close();
    }
  }

  /**
   * Find the maximum probability and return it's index.
   *
   * @param probabilities The probabilites.
   * @return The index of the max.
   */
  public static int pred(float[] probabilities) {
    float maxVal = Float.NEGATIVE_INFINITY;
    int idx = 0;
    for (int i = 0; i < probabilities.length; i++) {
      if (probabilities[i] > maxVal) {
        maxVal = probabilities[i];
        idx = i;
      }
    }
    return idx;
  }

  public static DataTuple loadData(String path) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))) {
      float[][][][] data = (float[][][][]) ois.readObject();
      int[] labels = (int[]) ois.readObject();
      return new DataTuple(data, labels);
    }
  }

  private static class DataTuple {
    public final float[][][][] features;
    public final int[] labels;

    public DataTuple(float[][][][] features, int[] labels) {
      this.features = features;
      this.labels = labels;
    }
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    logger.info("Usage: MNISTTest <num-epochs> <minibatch-size> <optimizer-name> <train-data-path> <test-data-path>");

    logger.info("Loading training data");
    DataTuple train = loadData(args[3]);
    logger.info("Loading testing data");
    DataTuple test = loadData(args[4]);

    logger.info("Loaded data.");

    float[][][][] trainData = train.features;
    int[] trainLabels = train.labels;

    float[][][][] testData = test.features;
    int[] testLabels = test.labels;

    logger.info("Loaded " + trainLabels.length + " training labels");
    logger.info("Loaded " + testLabels.length + " testing labels");

    int epochs = Integer.parseInt(args[0]);
    int minibatchSize = Integer.parseInt(args[1]);

    Graph graph = build(args[2]);

    int correctCount = 0;
    int[][] confusionMatrix = new int[10][10];

    try (Session session = new Session(graph)) {
      train(session, epochs, minibatchSize, trainData, trainLabels);

      logger.info("Trained model");

      float[][][][] featureBatch = new float[minibatchSize][][][];
      int[] labelBatch = new int[minibatchSize];
      float[][] prediction;

      for (int j = 0; j < testData.length; j += minibatchSize) {
        for (int k = j, m = 0; k < (j + minibatchSize) && k < testData.length; k++, m++) {
          featureBatch[m] = testData[k];
          labelBatch[m] = testLabels[k];
        }
        try (Tensor<?> transformedInput = Tensor.create(featureBatch);
             Tensor<?> outputTensor = session.runner()
                 .feed(INPUT_NAME, transformedInput)
                 .fetch(OUTPUT_NAME).run().get(0)) {
          prediction = outputTensor.copyTo(new float[minibatchSize][NUM_LABELS]);
        }

        for (int k = 0; k < labelBatch.length; k++) {
          int predLabel;

          predLabel = pred(prediction[k]);
          if (predLabel == labelBatch[k]) {
            correctCount++;
          }

          confusionMatrix[labelBatch[k]][predLabel]++;
        }

        if (j % 1000 == 0) {
          logger.log(Level.INFO, "Cur accuracy = " + ((float) correctCount) / (j + minibatchSize));
        }
      }

      logger.info("Final accuracy = " + ((float) correctCount) / testLabels.length);

      StringBuilder sb = new StringBuilder();
      sb.append("Label");
      for (int i = 0; i < confusionMatrix.length; i++) {
        sb.append(String.format("%1$5s", "" + i));
      }
      sb.append("\n");

      for (int i = 0; i < confusionMatrix.length; i++) {
        sb.append(String.format("%1$5s", "" + i));
        for (int j = 0; j < confusionMatrix[i].length; j++) {
          sb.append(String.format("%1$5s", "" + confusionMatrix[i][j]));
        }
        sb.append("\n");
      }

      System.out.println(sb.toString());
    }

  }
}
