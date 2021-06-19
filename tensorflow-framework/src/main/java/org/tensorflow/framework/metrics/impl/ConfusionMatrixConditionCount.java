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
package org.tensorflow.framework.metrics.impl;

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.Collections;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.metrics.Metric;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

/**
 * Abstract base class that calculates the value of the given confusion matrix condition based on
 * labels and predictions.
 *
 * @param <T> The data type for the metric result
 */
public abstract class ConfusionMatrixConditionCount<T extends TNumber> extends Metric<T> {
  public static final String ACCUMULATOR = "accumulator";
  public static final float DEFAULT_THRESHOLD = 0.5f;
  private final ConfusionMatrixEnum confusionMatrixCond;
  private final float[] thresholds;
  private final String accumulatorName;
  private Variable<T> accumulator;
  private Assign<T> initializer;

  /**
   * Creates a ConfusionMatrixConditionCount type of Metric, using a threshold of {@link
   * #DEFAULT_THRESHOLD}
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param confusionMatrixCond the confusion matrix condition to calculate
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param resultType the data type for the variables
   */
  public ConfusionMatrixConditionCount(
      String name, ConfusionMatrixEnum confusionMatrixCond, long seed, Class<T> resultType) {
    this(name, confusionMatrixCond, DEFAULT_THRESHOLD, seed, resultType);
  }
  /**
   * Creates a ConfusionMatrixConditionCount type of Metric
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param confusionMatrixCond the confusion matrix condition to calculate
   * @param threshold a threshold value in {@code [0, 1]}. A threshold is compared with prediction
   *     values to determine the truth value of predictions (i.e., above the threshold is {@code
   *     true}, below is {@code false}). One metric value is generated for each threshold value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param resultType the data type for the variables
   */
  public ConfusionMatrixConditionCount(
      String name,
      ConfusionMatrixEnum confusionMatrixCond,
      float threshold,
      long seed,
      Class<T> resultType) {
    this(name, confusionMatrixCond, new float[] {threshold}, seed, resultType);
  }

  /**
   * Creates a ConfusionMatrixConditionCount type of Metric
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param confusionMatrixCond the confusion matrix condition to calculate
   * @param thresholds threshold values in {@code [0, 1]}. A threshold is compared with prediction
   *     values to determine the truth value of predictions (i.e., above the threshold is {@code
   *     true}, below is {@code false}). One metric value is generated for each threshold value.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param resultType the data type for the variables
   */
  public ConfusionMatrixConditionCount(
      String name,
      ConfusionMatrixEnum confusionMatrixCond,
      float[] thresholds,
      long seed,
      Class<T> resultType) {
    super(name, seed, resultType);
    accumulatorName = this.getVariableName(ACCUMULATOR);
    this.confusionMatrixCond = confusionMatrixCond;
    this.thresholds = thresholds;
  }

  /** Initialize the metric */
  public Ops init(Ops tf) {
    if (this.tf == null) {
      setTensorFlowOps(tf);

      Shape variableShape = Shape.of(this.thresholds.length);

      Zeros<T> zeros = new Zeros<>();
      variablesNeedAssign = true;
      accumulator =
          getTF()
              .withName(getAccumulatorName())
              .variable(zeros.call(getTF(), getTF().constant(variableShape), getResultType()));
      initializer =
          getTF()
              .assign(
                  accumulator,
                  zeros.call(getTF(), getTF().constant(variableShape), getResultType()));
      applyOnInit();
    }
    return getTF();
  }

  /**
   * Gets the initializer for the accumulator variable
   *
   * @return the initializer for the accumulator variable
   */
  public Assign<T> getInitializer() {
    return initializer;
  }

  /**
   * Accumulates the metric statistics.
   *
   * @param tf the TensorFlow Ops
   * @param labels The ground truth values.
   * @param predictions the predictions
   * @param sampleWeights Optional weighting of each example. Defaults to 1. Rank is either 0, or
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
    Operand<T> tLabels = cast(tf, labels, getResultType());
    Operand<T> tPredictions = cast(tf, predictions, getResultType());
    Operand<T> tSampleWeights =
        sampleWeights != null ? cast(tf, sampleWeights, getResultType()) : null;
    List<Op> result =
        MetricsHelper.updateConfusionMatrixVariables(
            getTF(),
            variablesNeedAssign,
            Collections.singletonMap(confusionMatrixCond, accumulator),
            Collections.singletonMap(confusionMatrixCond, initializer),
            tLabels,
            tPredictions,
            tf.constant(thresholds),
            null,
            null,
            tSampleWeights,
            false,
            null);
    variablesNeedAssign = false;
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> result(Ops tf) {
    init(tf);
    if (accumulator == null || variablesNeedAssign) {
      return getResultZero();
    } else {
      return checkTF().identity(accumulator);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates(Ops tf) {
    init(tf);
    return initializer;
  }

  /**
   * get the thresholds
   *
   * @return the thresholds
   */
  public float[] getThresholds() {
    return this.thresholds;
  }

  /**
   * Gets the accumulatorName
   *
   * @return the accumulatorName
   */
  public String getAccumulatorName() {
    return accumulatorName;
  }
}
