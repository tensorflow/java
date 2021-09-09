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

import org.tensorflow.Operand;
import org.tensorflow.framework.metrics.impl.SensitivitySpecificityBase;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes best sensitivity where sensitivity is &gt;= specified value.
 *
 * <p>{@code Sensitivity} measures the proportion of actual positives that are correctly identified
 * as such {@code (tp / (tp + fn))}.
 *
 * <p>{@code Specificity} measures the proportion of actual negatives that are correctly identified
 * as such {@code (tn / (tn + fp))}.
 *
 * <p>This metric creates four local variables, {@code truePositives}, {@code trueNegatives },
 * {@code falsePositives} and {@code falseNegatives} that are used to compute the sensitivity at the
 * given specificity. The threshold for the given specificity value is computed and used to evaluate
 * the corresponding sensitivity.
 *
 * <p>If {@code sampleWeights} is {@code null}, weights default to 1. Use sample_weight of 0 to mask
 * values.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Sensitivity_and_specificity">Additional information
 *     about specificity and sensitivity</a>
 * @param <T> The data type for the metric result
 */
public class SensitivityAtSpecificity<T extends TNumber> extends SensitivitySpecificityBase<T> {

  private final float specificity;

  /**
   * Creates a SpecificityAtSensitivity metric with a name of {@link Class#getSimpleName()} and
   * {@link #DEFAULT_NUM_THRESHOLDS} for the number of thresholds
   *
   * @param specificity the specificity. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if specificity is not in the range
   *     [0-1].
   */
  public SensitivityAtSpecificity(float specificity, long seed, Class<T> type) {
    this(null, specificity, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a SpecificityAtSensitivity metric with {@link #DEFAULT_NUM_THRESHOLDS} for the number
   * of thresholds
   *
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param specificity the specificity. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if specificity is not in the range
   *     [0-1].
   */
  public SensitivityAtSpecificity(String name, float specificity, long seed, Class<T> type) {
    this(name, specificity, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric with a name of {@link Class#getSimpleName()}.
   *
   * @param specificity the specificity. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     specificity.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if specificity is not in the range
   *     [0-1].
   */
  public SensitivityAtSpecificity(float specificity, int numThresholds, long seed, Class<T> type) {
    this(null, specificity, numThresholds, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric.
   *
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param specificity the specificity. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     specificity.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if specificity is not in the range
   *     [0-1].
   */
  public SensitivityAtSpecificity(
      String name, float specificity, int numThresholds, long seed, Class<T> type) {
    super(name, numThresholds, seed, type);
    if (specificity < 0f || specificity > 1f)
      throw new IllegalArgumentException("specificity must be in the range [0, 1].");
    this.specificity = specificity;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> Operand<U> result(Ops tf, Class<U> resultType) {
    init(tf);
    Operand<T> specificities =
        tf.math.divNoNan(trueNegatives, tf.math.add(trueNegatives, falsePositives));
    Operand<T> sub = tf.math.sub(specificities, cast(tf, tf.constant(specificity), getType()));
    Operand<TInt32> minIndex = tf.math.argMin(tf.math.abs(sub), tf.constant(0), TInt32.class);
    minIndex = tf.expandDims(minIndex, tf.constant(0));

    Operand<T> trueSlice = tf.slice(truePositives, minIndex, tf.constant(new int[] {1}));
    Operand<T> falseSlice = tf.slice(falseNegatives, minIndex, tf.constant(new int[] {1}));
    return cast(tf, tf.math.divNoNan(trueSlice, tf.math.add(trueSlice, falseSlice)), resultType);
  }

  /**
   * Gets the specificity
   *
   * @return the specificity
   */
  public float getSpecificity() {
    return specificity;
  }
}
