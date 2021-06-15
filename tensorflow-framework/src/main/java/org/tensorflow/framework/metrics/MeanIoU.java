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
package org.tensorflow.framework.metrics;

import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.metrics.impl.MetricsHelper;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

import java.util.Collections;
import java.util.List;

import static org.tensorflow.framework.losses.impl.LossesHelper.allAxes;
import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Computes the mean Intersection-Over-Union metric.
 *
 * <p>Mean Intersection-Over-Union is a common evaluation metric for semantic image segmentation,
 * which first computes the IOU for each semantic class and then computes the average over classes.
 * IOU is defined as follows: {@code IOU = true_positive / (true_positive + false_positive +
 * false_negative)}. The predictions are accumulated in a confusion matrix, weighted by
 * sample_weight and the metric is then calculated from it.
 *
 * <p>If {@code sampleWeight} is {@code null}, weights default to 1. Use sample_weight of 0 to mask
 * values.
 *
 * @param <T> The data type for the metric result
 */
public class MeanIoU<T extends TNumber> extends Metric<T> {

  public static final String TOTAL_CONFUSION_MATRIX = "TOTAL_CONFUSION_MATRIX";
  private final String totalCMName;
  private final Class<T> type;
  /**
   * The possible number of labels the prediction task can have. This value must be provided, since
   * a confusion matrix of dimension = [numClasses, numClasses] will be allocated.
   */
  private final long numClasses;

  private Variable<T> totalConfusionMatrix;
  private Assign<T> initializer;

  /**
   * Creates a metric MeanIoU, using name as {@link Class#getSimpleName()}
   *
   * @param numClasses The possible number of labels the prediction task can have
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  protected MeanIoU(long numClasses, long seed, Class<T> type) {
    this((String) null, numClasses, seed, type);
  }

  /**
   * Creates a MeanIoU metric
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param numClasses The possible number of labels the prediction task can have
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  protected MeanIoU(String name, long numClasses, long seed, Class<T> type) {
    super(name, seed);
    this.type = type;
    this.totalCMName = this.getVariableName(TOTAL_CONFUSION_MATRIX);
    this.numClasses = numClasses;
  }

  /**
   * Creates a metric MeanIoU, using name as {@link Class#getSimpleName()}
   *
   * @param tf the TensorFlow Ops
   * @param numClasses The possible number of labels the prediction task can have
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  protected MeanIoU(Ops tf, long numClasses, long seed, Class<T> type) {
    this(tf, null, numClasses, seed, type);
  }

  /**
   * Creates a MeanIoU metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param numClasses The possible number of labels the prediction task can have
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  protected MeanIoU(Ops tf, String name, long numClasses, long seed, Class<T> type) {
    this(name, numClasses, seed, type);
    init(tf);
  }

  /** {@inheritDoc} */
  @Override
  public Ops init(Ops tf) {
    setTensorFlowOps(tf);
    Shape variableShape = Shape.of(numClasses, numClasses);

    if (totalConfusionMatrix == null) {
      Zeros<T> zeros = new Zeros<>();
      totalConfusionMatrix =
          getTF()
              .withName(totalCMName)
              .variable(zeros.call(getTF(), getTF().constant(variableShape), type));
      initializer =
          getTF()
              .assign(
                  totalConfusionMatrix, zeros.call(getTF(), getTF().constant(variableShape), type));
    }
    applyOnInit();
    return getTF();
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates() {
    checkTF();
    return initializer;
  }

  /**
   * Gets the initializer for the totalConfusionMatrix variable
   *
   * @return the initializer for the totalConfusionMatrix variable
   */
  public Assign<T> getInitializer() {
    return initializer;
  }

  /**
   * Accumulates the confusion matrix statistics.
   *
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights Optional weighting of each example. Defaults to 1, if null. Rank is either
   *     0, or the same rank as labels, and must be broadcastable to labels.
   * @return the Operands that updates totalConfusionMatrix variable
   * @throws IllegalArgumentException if the weights rank is not 0, and weights rank @{code !=}
   *     labels rank, and if the predictions size is not equal to the labels size
   */
  @Override
  public List<Op> updateStateList(
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    if (sampleWeights != null) {
      long weightsRank = sampleWeights.shape().numDimensions();
      long labelsRank = labels.shape().numDimensions();
      if (weightsRank != 0
          && weightsRank != Shape.UNKNOWN_SIZE
          && labelsRank != Shape.UNKNOWN_SIZE
          && weightsRank != labelsRank) {
        throw new IllegalArgumentException(
            String.format(
                "Weights must either have rank 0, or the same rank as labels, weights rank = %d, labels rank = %d",
                weightsRank, labelsRank));
      }
    }
    long labelsSize = labels.shape().size();
    long predictionsSize = predictions.shape().size();
    if (labelsSize != predictionsSize) {
      throw new IllegalArgumentException(
          String.format(
              "labels and predictions must have the same size, labels size = %d, predictions size = %d",
              labelsSize, predictionsSize));
    }

    Operand<T> tLabels = cast(getTF(), labels, type);
    if (tLabels.shape().numDimensions() > 1) {
      tLabels = getTF().shape.flatten(tLabels);
    }
    Operand<T> tPredictions = cast(getTF(), predictions, type);
    if (tPredictions.shape().numDimensions() > 1) {
      tPredictions = getTF().shape.flatten(tPredictions);
    }
    Operand<T> tSampleWeights = sampleWeights != null ? cast(getTF(), sampleWeights, type) : null;
    if (tSampleWeights != null && tSampleWeights.shape().numDimensions() > 1) {
      tSampleWeights = getTF().shape.flatten(tSampleWeights);
    }

    // Accumulate the prediction to current confusion matrix.
    Operand<T> currentCM =
        MetricsHelper.confusionMatrix(
            getTF(), tLabels, tPredictions, getTF().constant(numClasses), tSampleWeights, type);
    return Collections.singletonList(getTF().assignAdd(totalConfusionMatrix, currentCM));
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> result() {
    Ops tf = getTF();
    Operand<T> sumOverRow = tf.reduceSum(totalConfusionMatrix, tf.constant(0));
    Operand<T> sumOverCol = tf.reduceSum(totalConfusionMatrix, tf.constant(1));
    Operand<T> truePositives =
        tf.linalg.matrixDiagPart(
            totalConfusionMatrix,
            tf.constant(0),
            cast(tf, tf.constant(0), totalConfusionMatrix.type()));
    // for each class, the total predictions + total labels - true positives
    // Observe that total predictions = tp + fp
    //              total labels = tp + fn
    // So this is 2*tp + fp + fn - tp = tp + fp + fn
    Operand<T> denominator = tf.math.add(sumOverRow, tf.math.sub(sumOverCol, truePositives));
    Operand<T> numValidEntries =
        tf.reduceSum(
            tf.dtypes.cast(
                tf.math.notEqual(denominator, cast(tf, tf.constant(0), denominator.type())), type),
            allAxes(tf, denominator));
    Operand<T> iou = tf.math.divNoNan(truePositives, denominator);

    Operand<T> iouSum = tf.reduceSum(iou, allAxes(tf, iou));
    return tf.math.divNoNan(iouSum, numValidEntries);
  }
}
