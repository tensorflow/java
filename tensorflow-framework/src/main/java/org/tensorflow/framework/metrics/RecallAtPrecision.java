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

import static org.tensorflow.framework.losses.impl.LossesHelper.allAxes;
import static org.tensorflow.framework.utils.CastHelper.cast;

import org.tensorflow.Operand;
import org.tensorflow.framework.metrics.impl.SensitivitySpecificityBase;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Where;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * Computes best recall where precision is &gt;= specified value.
 *
 * <p>For a given score-label-distribution the required precision might not be achievable, in this
 * case 0.0 is returned as recall.
 *
 * <p>This metric creates four local variables, truePositives, trueNegatives, falsePositives and
 * falseNegatives that are used to compute the recall at the given precision. The threshold for the
 * given precision value is computed and used to evaluate the corresponding recall.
 *
 * <p>If {@code sampleWeights} is null, weights default to 1. Use {@code sampleWeights} of 0 to mask
 * values.
 *
 * @param <T> The data type for the metric result
 */
public class RecallAtPrecision<T extends TNumber> extends SensitivitySpecificityBase<T> {

  private final float precision;

  /**
   * Creates a PrecisionRecall metric with a name of {@link Class#getSimpleName()} and {@link
   * #DEFAULT_NUM_THRESHOLDS} for the number of thresholds
   *
   * @param precision the precision. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if recall is not in the range
   *     [0-1].
   */
  public RecallAtPrecision(float precision, long seed, Class<T> type) {
    this(null, precision, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric with {@link #DEFAULT_NUM_THRESHOLDS} for the number of
   * thresholds
   *
   * @param name the name of the metric. If null, defaults to {@link Class#getSimpleName()}
   * @param precision the precision. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if recall is not in the range
   *     [0-1].
   */
  public RecallAtPrecision(String name, float precision, long seed, Class<T> type) {
    this(name, precision, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric with a name of {@link Class#getSimpleName()}.
   *
   * @param precision the precision. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     recall.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if recall is not in the range
   *     [0-1].
   */
  public RecallAtPrecision(float precision, int numThresholds, long seed, Class<T> type) {
    this(null, precision, numThresholds, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric.
   *
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param precision the precision. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     recall.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if recall is not in the range
   *     [0-1].
   */
  public RecallAtPrecision(
      String name, float precision, int numThresholds, long seed, Class<T> type) {
    super(name, numThresholds, seed, type);
    if (precision < 0f || precision > 1f)
      throw new IllegalArgumentException("recall must be in the range [0, 1].");
    this.precision = precision;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> Operand<U> result(Ops tf, Class<U> resultType) {
    init(tf);
    Operand<T> precisions =
        tf.math.divNoNan(truePositives, tf.math.add(truePositives, falsePositives));
    Operand<T> recalls =
        tf.math.divNoNan(truePositives, tf.math.add(truePositives, falseNegatives));
    Operand<TBool> isFeasible =
        tf.math.greaterEqual(precisions, cast(tf, tf.constant(precision), getType()));
    Where feasible = tf.where(isFeasible);
    Operand<TBool> feasibleExists = tf.math.greater(tf.size(feasible), tf.constant(0));

    Operand<T> gather = tf.expandDims(tf.gather(recalls, feasible, tf.constant(0)), tf.constant(0));
    return cast(
        tf,
        tf.select(
            feasibleExists,
            tf.reduceMax(gather, allAxes(tf, gather)),
            cast(tf, tf.constant(0), getType())),
        resultType);
  }

  /**
   * Gets the precision
   *
   * @return the precision
   */
  public float getPrecision() {
    return precision;
  }
}
