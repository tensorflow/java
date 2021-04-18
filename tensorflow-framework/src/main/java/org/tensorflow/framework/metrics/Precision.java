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
import org.tensorflow.framework.metrics.impl.ConfusionMatrixEnum;
import org.tensorflow.framework.metrics.impl.MetricsHelper;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Computes the precision of the predictions with respect to the labels.
 *
 * <p>The metric creates two local variables, {@code truePositives} and {@code falsePositives
 * } that are used to compute the precision. This value is ultimately returned as precision,
 * an idempotent operation that simply divides {@code truePositives} by the sum of {@code
 * truePositives} and {@code falsePositives}.
 *
 * <p>If {@code sampleWeights} is {@code null}, weights default to 1. Use sampleWeights of
 * 0 to mask values.
 *
 * <p>If {@code topK} is set, the metric calculates precision as how often on average a class
 * among the top-k classes with the highest predicted values of a batch entry is correct and can be
 * found in the label for that entry.
 *
 * <p>If {@code classId} is specified, the metric calculates precision by considering only the
 * entries in the batch for which {@code classId} is above the {@code thresholds} and/or
 * in the top-k highest predictions, and computing the fraction of them for which {@code classId
 * } is indeed a correct label.
 *
 * @param <T> The data type for the metric result
 */
public class Precision<T extends TNumber> extends Metric<T> {
  public static final String TRUE_POSITIVES = "TRUE_POSITIVES";
  public static final String FALSE_POSITIVES = "FALSE_POSITIVES";
  public static final float DEFAULT_THRESHOLD = 0.5f;

  private final float[] thresholds;
  private final Integer topK;
  private final Integer classId;
  private final String truePositivesName;
  private final String falsePositivesName;
  private final Class<T> type;
  private final List<Op> initializers = new ArrayList<>();
  private Variable<T> truePositives;
  private Variable<T> falsePositives;

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()} and no topK or classId
   * values and with a threshold of {@link #DEFAULT_THRESHOLD}.
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(Ops tf, long seed, Class<T> type) {
    this(tf, null, null, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with no topK or classId values with a threshold of {@link
   * #DEFAULT_THRESHOLD}.
   *
   * @param tf the TensorFlow Ops
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(Ops tf, String name, long seed, Class<T> type) {
    this(tf, name, null, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()} and no topK or classId
   * values.
   *
   * @param tf the TensorFlow Ops
   * @param threshold Optional threshold value in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(Ops tf, float threshold, long seed, Class<T> type) {
    this(tf, null, new float[] {threshold}, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()} and no topK or classId
   * values.
   *
   * @param tf the TensorFlow Ops
   * @param thresholds Optional threshold values in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(Ops tf, float[] thresholds, long seed, Class<T> type) {
    this(tf, null, thresholds, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with no topK or classId values.
   *
   * @param tf the TensorFlow Ops
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param threshold Optional threshold value in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(Ops tf, String name, float threshold, long seed, Class<T> type) {
    this(tf, name, new float[] {threshold}, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with no topK or classId values.
   *
   * @param tf the TensorFlow Ops
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param thresholds Optional threshold values in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(Ops tf, String name, float[] thresholds, long seed, Class<T> type) {
    this(tf, name, thresholds, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()}
   *
   * @param tf the TensorFlow Ops
   * @param threshold Optional threshold value in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(
      Ops tf, float threshold, Integer topK, Integer classId, long seed, Class<T> type) {
    this(tf, null, new float[] {threshold}, topK, classId, seed, type);
  }

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()}
   *
   * @param tf the TensorFlow Ops
   * @param thresholds Optional threshold values in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(
      Ops tf, float[] thresholds, Integer topK, Integer classId, long seed, Class<T> type) {
    this(tf, null, thresholds, topK, classId, seed, type);
  }

  /**
   * Creates a Precision Metric.
   *
   * @param tf the TensorFlow Ops
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param threshold Optional threshold value in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(
      Ops tf,
      String name,
      float threshold,
      Integer topK,
      Integer classId,
      long seed,
      Class<T> type) {
    this(tf, name, new float[] {threshold}, topK, classId, seed, type);
  }

  /**
   * Creates a Precision Metric.
   *
   * @param tf the TensorFlow Ops
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param thresholds Optional threshold values in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(
      Ops tf,
      String name,
      float[] thresholds,
      Integer topK,
      Integer classId,
      long seed,
      Class<T> type) {
    super(tf, name, seed);
    this.type = type;
    this.truePositivesName = this.getVariableName(TRUE_POSITIVES);
    this.falsePositivesName = this.getVariableName(FALSE_POSITIVES);
    float defaultThreshold = topK == null ? DEFAULT_THRESHOLD : MetricsHelper.NEG_INF;
    this.thresholds = thresholds == null ? new float[] {defaultThreshold} : thresholds;
    this.topK = topK;
    this.classId = classId;

    init();
  }

  /** Initializes the variables */
  private void init() {
    Ops tf = getTF();
    Zeros<T> zeros = new Zeros<>(tf);
    Operand<T> zero = zeros.call(tf.constant(Shape.of(thresholds.length)), type);

    if (this.truePositives == null) {
      this.truePositives = tf.withName(truePositivesName).variable(zero);
      initializers.add(tf.assign(truePositives, zero));
    }
    if (this.falsePositives == null) {
      this.falsePositives =
          tf.withName(falsePositivesName)
              .variable(zero);
      initializers.add(tf.assign(falsePositives, zero));
    }
  }

  /**
   * Accumulates true positive and false positive statistics.
   *
   * @param labels the labels The ground truth values, with the same dimensions as predictions. Will
   *     be cast to {@link TBool}.
   * @param predictions the predictions, each element must be in the range {@code [0, 1]}.
   * @param sampleWeights Optional weighting of each example. Defaults to 1. Rank is either 0, or *
   *     the same rank as labels, and must be broadcastable to labels.
   * @return a List of Operations to update the metric state.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<Op> updateStateList(
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    Ops tf = getTF();
    Map<ConfusionMatrixEnum, Variable<T>> confusionMatrix = new HashMap<>();
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_POSITIVES, truePositives);
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_POSITIVES, falsePositives);

    Operand<T> tPredictions = cast(tf, predictions, type);
    Operand<T> tLabels = cast(tf, labels, type);
    Operand<T> tSampleWeights = sampleWeights != null ? cast(tf, sampleWeights, type) : null;

    return new ArrayList<Op>(
        MetricsHelper.updateConfusionMatrixVariables(
            tf,
            confusionMatrix,
            Collections.EMPTY_MAP,
            tLabels,
            tPredictions,
            tf.constant(thresholds),
            topK,
            classId,
            tSampleWeights,
            false,
            null));
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> result() {
    Ops tf = getTF();
    Operand<T> result = tf.math.divNoNan(truePositives, tf.math.add(truePositives, falsePositives));
    return  thresholds.length == 1
        ? tf.reshape(tf.slice(
            result,
            tf.expandDims(tf.constant(0), tf.constant(0)),
            tf.expandDims(tf.constant(1), tf.constant(0))),
            tf.constant(Shape.scalar()))
        : result;
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates() {
    return getTF().withSubScope("resetStates").withControlDependencies(initializers).noOp();
  }

  /**
   * Gets the thresholds
   *
   * @return the thresholds
   */
  public float[] getThresholds() {
    return thresholds;
  }

  /**
   * Gets the topK value, may be null
   *
   * @return the topK value or null
   */
  public Integer getTopK() {
    return topK;
  }

  /**
   * Gets the classId, may be null
   *
   * @return the classId or null
   */
  public Integer getClassId() {
    return classId;
  }

  /**
   * Gets the truePositives variable
   *
   * @return the truePositives
   */
  public Variable<T> getTruePositives() {
    return truePositives;
  }

  /**
   * Gets the falsePositives variable
   *
   * @return the falsePositives
   */
  public Variable<T> getFalsePositives() {
    return falsePositives;
  }

  /**
   * Gets the name of the truePositives variable
   *
   * @return the truePositivesName
   */
  public String getTruePositivesName() {
    return truePositivesName;
  }

  /**
   * Gets the name of the falsePositives variable
   *
   * @return the falsePositivesName
   */
  public String getFalsePositivesName() {
    return falsePositivesName;
  }
}
