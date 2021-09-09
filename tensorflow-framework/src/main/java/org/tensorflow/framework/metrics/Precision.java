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

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 * Computes the precision of the predictions with respect to the labels.
 *
 * <p>The metric creates two local variables, {@code truePositives} and {@code falsePositives } that
 * are used to compute the precision. This value is ultimately returned as precision, an idempotent
 * operation that simply divides {@code truePositives} by the sum of {@code truePositives} and
 * {@code falsePositives}.
 *
 * <p>If {@code sampleWeights} is {@code null}, weights default to 1. Use sampleWeights of 0 to mask
 * values.
 *
 * <p>If {@code topK} is set, the metric calculates precision as how often on average a class among
 * the top-k classes with the highest predicted values of a batch entry is correct and can be found
 * in the label for that entry.
 *
 * <p>If {@code classId} is specified, the metric calculates precision by considering only the
 * entries in the batch for which {@code classId} is above the {@code thresholds} and/or in the
 * top-k highest predictions, and computing the fraction of them for which {@code classId } is
 * indeed a correct label.
 *
 * @param <T> The data type for the metric result
 */
public class Precision<T extends TNumber> extends BaseMetric {
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
  private final Zeros<T> zeros = new Zeros<>();
  private Variable<T> truePositives;
  private Variable<T> falsePositives;

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()} and no topK or classId
   * values and with a threshold of {@link #DEFAULT_THRESHOLD}.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(long seed, Class<T> type) {
    this(null, null, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with no topK or classId values with a threshold of {@link
   * #DEFAULT_THRESHOLD}.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(String name, long seed, Class<T> type) {
    this(name, null, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()} and no topK or classId
   * values.
   *
   * @param threshold Optional threshold value in the range {@code [0, 1]}. A threshold is compared
   *     with prediction values to determine the truth value of predictions (i.e., above the
   *     threshold is true, below is false). One metric value is generated for each threshold value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(float threshold, long seed, Class<T> type) {
    this(null, new float[] {threshold}, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()} and no topK or classId
   * values.
   *
   * @param thresholds Optional threshold values in the range {@code [0, 1]}. A threshold is
   *     compared with prediction values to determine the truth value of predictions (i.e., above
   *     the threshold is true, below is false). One metric value is generated for each threshold
   *     value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(float[] thresholds, long seed, Class<T> type) {
    this(null, thresholds, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with no topK or classId values.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param threshold Optional threshold value in the range {@code [0, 1]}. A threshold is compared
   *     with prediction values to determine the truth value of predictions (i.e., above the
   *     threshold is true, below is false). One metric value is generated for each threshold value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(String name, float threshold, long seed, Class<T> type) {
    this(name, new float[] {threshold}, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with no topK or classId values.
   *
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
  public Precision(String name, float[] thresholds, long seed, Class<T> type) {
    this(name, thresholds, null, null, seed, type);
  }

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()}
   *
   * @param threshold Optional threshold value in the range {@code [0, 1]}. A threshold is compared
   *     with prediction values to determine the truth value of predictions (i.e., above the
   *     threshold is true, below is false). One metric value is generated for each threshold value.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(float threshold, Integer topK, Integer classId, long seed, Class<T> type) {
    this(null, new float[] {threshold}, topK, classId, seed, type);
  }

  /**
   * Creates a Precision Metric with a name of {@link Class#getSimpleName()}
   *
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
  public Precision(float[] thresholds, Integer topK, Integer classId, long seed, Class<T> type) {
    this(null, thresholds, topK, classId, seed, type);
  }

  /**
   * Creates a Precision Metric.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param threshold Optional threshold value in the range {@code [0, 1]}. A threshold is compared
   *     with prediction values to determine the truth value of predictions (i.e., above the
   *     threshold is true, below is false). One metric value is generated for each threshold value.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Precision(
      String name, float threshold, Integer topK, Integer classId, long seed, Class<T> type) {
    this(name, new float[] {threshold}, topK, classId, seed, type);
  }

  /**
   * Creates a Precision Metric.
   *
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
      String name, float[] thresholds, Integer topK, Integer classId, long seed, Class<T> type) {
    super(name, seed);
    this.type = type;
    this.truePositivesName = this.getVariableName(TRUE_POSITIVES);
    this.falsePositivesName = this.getVariableName(FALSE_POSITIVES);
    float defaultThreshold = topK == null ? DEFAULT_THRESHOLD : MetricsHelper.NEG_INF;
    this.thresholds = thresholds == null ? new float[] {defaultThreshold} : thresholds;
    this.topK = topK;
    this.classId = classId;
  }

  /** {@inheritDoc} */
  @Override
  protected void init(Ops tf) {
    checkIsGraph(tf);
    if (!isInitialized()) {
      setTF(tf);
      Operand<T> zero = zeros.call(tf, tf.constant(Shape.of(thresholds.length)), type);

      if (this.truePositives == null) {
        this.truePositives = tf.withName(truePositivesName).withInitScope().variable(zero);
        initializers.add(tf.assign(truePositives, zero));
      }
      if (this.falsePositives == null) {
        this.falsePositives = tf.withName(falsePositivesName).withInitScope().variable(zero);
        initializers.add(tf.assign(falsePositives, zero));
      }
      setInitialized(true);
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
  public List<Op> updateStateList(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    init(tf);
    Map<ConfusionMatrixEnum, Variable<T>> confusionMatrix = new HashMap<>();
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_POSITIVES, truePositives);
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_POSITIVES, falsePositives);

    Operand<T> tPredictions = cast(tf, predictions, type);
    Operand<T> tLabels = cast(tf, labels, type);
    Operand<T> tSampleWeights = sampleWeights != null ? cast(tf, sampleWeights, type) : null;

    return new ArrayList<>(
        MetricsHelper.updateConfusionMatrixVariables(
            tf,
            confusionMatrix,
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
  public <U extends TNumber> Operand<U> result(Ops tf, Class<U> resultType) {
    init(tf);
    Operand<T> result = tf.math.divNoNan(truePositives, tf.math.add(truePositives, falsePositives));
    return cast(
        tf,
        thresholds.length == 1
            ? tf.reshape(
                tf.slice(
                    result,
                    tf.expandDims(tf.constant(0), tf.constant(0)),
                    tf.expandDims(tf.constant(1), tf.constant(0))),
                tf.constant(Shape.scalar()))
            : result,
        resultType);
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates(Ops tf) {
    init(tf);
    return tf.withSubScope("resetStates").withControlDependencies(initializers).noOp();
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
