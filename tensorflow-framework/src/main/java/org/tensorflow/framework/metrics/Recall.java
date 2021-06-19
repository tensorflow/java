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
import java.util.Collections;
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
 * Computes the recall of the predictions with respect to the labels.
 *
 * <p>This metric creates two local variables, {@code truePositives} and {@code falseNegatives },
 * that are used to compute the recall. This value is ultimately returned as recall, an idempotent
 * operation that simply divides {@code truePositives} by the sum of {@code truePositives} and
 * {@code falseNegatives}.
 *
 * <p>If {@code sampleWeights} is {@code null}, weights default to 1. Use sampleWeights of 0 to mask
 * values.
 *
 * <p>If {@code topK} is set, the metric calculates recall as how often on average a class among the
 * labels of a batch entry is in the top-k predictions.
 *
 * <p>If {@code classId} is specified, the metric calculates recall by considering only the entries
 * in the batch for which {@code classId} is in the label, and computing the fraction of them for
 * which {@code classId} is above the threshold and/or in the top-k predictions.
 *
 * @param <T> The data type for the metric result
 */
public class Recall<T extends TNumber> extends Metric<T> {
  /** The default threshold value to calculate the precision */
  public static final float DEFAULT_THRESHOLD = 0.5f;
  /** The name of the truePositives variable */
  public static final String TRUE_POSITIVES = "TRUE_POSITIVES";
  /** The name of the falseNegatives variable */
  public static final String FALSE_NEGATIVES = "FALSE_NEGATIVES";

  private final float[] thresholds;
  private final Integer topK;
  private final Integer classId;
  private final String truePositivesName;
  private final String falseNegativesName;
  private final Class<T> type;
  private final List<Op> initializers = new ArrayList<>();
  private Variable<T> truePositives;
  private Variable<T> falseNegatives;

  /**
   * Creates a Recall metric with a name of {@link Class#getSimpleName()}, and topK and classId set
   * to null, and thresholds set to {@link #DEFAULT_THRESHOLD}
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(long seed, Class<T> type) {
    this(null, null, null, null, seed, type);
  }

  /**
   * Creates a Recall metric with topK and classId set to null and thresholds set to {@link
   * #DEFAULT_THRESHOLD}.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(String name, long seed, Class<T> type) {
    this(name, null, null, null, seed, type);
  }

  /**
   * Creates a Recall metric with a name of {@link Class#getSimpleName()}, and topK and classId set
   * to null.
   *
   * @param threshold A threshold is compared with prediction values to determine the truth value of
   *     predictions (i.e., above the threshold is `true`, below is `false`). If null, defaults to
   *     {@link #DEFAULT_THRESHOLD}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(float threshold, long seed, Class<T> type) {
    this(null, threshold, null, null, seed, type);
  }

  /**
   * Creates a Recall metric with a name of {@link Class#getSimpleName()}, and topK and classId set
   * to null.
   *
   * @param thresholds A threshold is compared with prediction values to determine the truth value
   *     of predictions (i.e., above the threshold is `true`, below is `false`). If null, defaults
   *     to {@link #DEFAULT_THRESHOLD}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(float[] thresholds, long seed, Class<T> type) {
    this(null, thresholds, null, null, seed, type);
  }

  /**
   * Creates a Recall metric with topK and classId set to null.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param threshold A threshold is compared with prediction values to determine the truth value of
   *     predictions (i.e., above the threshold is `true`, below is `false`). If null, defaults to
   *     {@link #DEFAULT_THRESHOLD}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(String name, float threshold, long seed, Class<T> type) {
    this(name, threshold, null, null, seed, type);
  }

  /**
   * Creates a Recall metric with topK and classId set to null.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param thresholds A threshold is compared with prediction values to determine the truth value
   *     of predictions (i.e., above the threshold is `true`, below is `false`). If null, defaults
   *     to {@link #DEFAULT_THRESHOLD}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(String name, float[] thresholds, long seed, Class<T> type) {
    this(name, thresholds, null, null, seed, type);
  }

  /**
   * Creates a Recall metric with a name of {@link Class#getSimpleName()} and using a threshold
   * value of {@link #DEFAULT_THRESHOLD}.
   *
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(Integer topK, Integer classId, long seed, Class<T> type) {
    this(null, null, topK, classId, seed, type);
  }

  /**
   * Creates a Recall metric using a threshold value of {@link #DEFAULT_THRESHOLD}.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(String name, Integer topK, Integer classId, long seed, Class<T> type) {
    this(name, null, topK, classId, seed, type);
  }

  /**
   * Creates a Recall metric with a name of {@link Class#getSimpleName()}
   *
   * @param threshold A threshold is compared with prediction values to determine the truth value of
   *     predictions (i.e., above the threshold is `true`, below is `false`). If null, defaults to
   *     {@link #DEFAULT_THRESHOLD}.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(float threshold, Integer topK, Integer classId, long seed, Class<T> type) {
    this(null, new float[] {threshold}, topK, classId, seed, type);
  }

  /**
   * Creates a Recall metric with a name of {@link Class#getSimpleName()}
   *
   * @param thresholds A threshold is compared with prediction values to determine the truth value
   *     of predictions (i.e., above the threshold is `true`, below is `false`). If null, defaults
   *     to {@link #DEFAULT_THRESHOLD}.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(float[] thresholds, Integer topK, Integer classId, long seed, Class<T> type) {
    this(null, thresholds, topK, classId, seed, type);
  }

  /**
   * Creates a Recall metric.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param threshold A threshold is compared with prediction values to determine the truth value of
   *     predictions (i.e., above the threshold is `true`, below is `false`). If null, defaults to
   *     {@link #DEFAULT_THRESHOLD}.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(
      String name, float threshold, Integer topK, Integer classId, long seed, Class<T> type) {
    this(name, new float[] {threshold}, topK, classId, seed, type);
  }

  /**
   * Creates a Recall metric.
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param thresholds A threshold is compared with prediction values to determine the truth value
   *     of predictions (i.e., above the threshold is `true`, below is `false`). If null, defaults
   *     to {@link #DEFAULT_THRESHOLD}.
   * @param topK An optional value specifying the top-k predictions to consider when calculating
   *     precision.
   * @param classId Optional Integer class ID for which we want binary metrics. This must be in the
   *     half-open interval [0, numClasses], where numClasses is the last dimension of predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Recall(
      String name, float[] thresholds, Integer topK, Integer classId, long seed, Class<T> type) {
    super(name, seed, type);
    this.type = type;
    this.truePositivesName = this.getVariableName(TRUE_POSITIVES);
    this.falseNegativesName = this.getVariableName(FALSE_NEGATIVES);
    float defaultThreshold = topK == null ? DEFAULT_THRESHOLD : MetricsHelper.NEG_INF;

    this.thresholds = thresholds == null ? new float[] {defaultThreshold} : thresholds;
    this.topK = topK;
    this.classId = classId;
  }

  /** {@inheritDoc} */
  @Override
  public Ops init(Ops tf) {
    if (this.tf == null) {
      setTensorFlowOps(tf);

      Zeros<T> zeros = new Zeros<>();
      Operand<T> zero = zeros.call(tf, tf.constant(Shape.of(this.thresholds.length)), type);
      if (truePositives == null) {
        variablesNeedAssign = true;
        truePositives = getTF().withName(truePositivesName).variable(zero);
        initializers.add(getTF().assign(truePositives, zero));
      }

      if (this.falseNegatives == null) {
        variablesNeedAssign = true;
        falseNegatives = getTF().withName(falseNegativesName).variable(zero);
        initializers.add(getTF().assign(falseNegatives, zero));
      }
      applyOnInit();
    }
    return getTF();
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates(Ops tf) {
    init(tf);
    return getTF().withSubScope("resetStates").withControlDependencies(initializers).noOp();
  }

  /**
   * Accumulates true positive and false negative statistics.
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
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    init(tf);
    Map<ConfusionMatrixEnum, Variable<T>> confusionMatrix = new HashMap<>();
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_POSITIVES, this.truePositives);
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_NEGATIVES, this.falseNegatives);

    Operand<T> tPredictions = cast(getTF(), predictions, type);
    Operand<T> tLabels = cast(getTF(), labels, type);
    Operand<T> tSampleWeights = sampleWeights != null ? cast(getTF(), sampleWeights, type) : null;

    List<Op> result =
        MetricsHelper.updateConfusionMatrixVariables(
            getTF(),
            variablesNeedAssign,
            confusionMatrix,
            Collections.EMPTY_MAP,
            tLabels,
            tPredictions,
            getTF().constant(thresholds),
            topK,
            classId,
            tSampleWeights,
            false,
            null);
    variablesNeedAssign = false;
    return result;
  }

  @Override
  public Operand<T> result(Ops tf) {
    init(tf);
    if (truePositives == null || falseNegatives == null || variablesNeedAssign) {
      return getResultZero();
    }
    Operand<T> result =
        getTF().math.divNoNan(truePositives, getTF().math.add(truePositives, falseNegatives));
    return thresholds.length == 1
        ? getTF().slice(result, getTF().constant(new int[] {0}), getTF().constant(new int[1]))
        : result;
  }

  /**
   * Gets the thresholds
   *
   * @return the thresholds
   */
  public float[] getThresholds() {
    return this.thresholds;
  }

  /**
   * Gets the topK value
   *
   * @return the topK value
   */
  public Integer getTopK() {
    return this.topK;
  }

  /**
   * Gets the class id
   *
   * @return the class id
   */
  public Integer getClassId() {
    return this.classId;
  }

  /**
   * Gets the truePositives variable
   *
   * @return the truePositives variable
   */
  public Variable<T> getTruePositives() {
    return this.truePositives;
  }

  /**
   * Gets the falseNegatives variable
   *
   * @return the falseNegatives variable
   */
  public Variable<T> getFalseNegatives() {
    return this.falseNegatives;
  }

  /**
   * Gets the truePositives variable name
   *
   * @return the truePositives variable name
   */
  public String getTruePositivesName() {
    return truePositivesName;
  }

  /**
   * Gets the falseNegatives variable name
   *
   * @return the falseNegatives variable name
   */
  public String getFalseNegativesName() {
    return falseNegativesName;
  }
}
