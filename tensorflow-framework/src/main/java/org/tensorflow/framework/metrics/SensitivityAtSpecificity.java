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
import org.tensorflow.framework.metrics.impl.SensitivitySpecificityBase;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Computes best sensitivity where sensitivity is >= specified value.
 *
 * <p><code>Sensitivity</code> measures the proportion of actual positives that are correctly
 * identified as such <code>(tp / (tp + fn))</code>.
 *
 * <p><code>Specificity</code> measures the proportion of actual negatives that are correctly
 * identified as such <code>(tn / (tn + fp))</code>.
 *
 * <p>This metric creates four local variables, <code>truePositives</code>, <code>trueNegatives
 * </code>, <code>falsePositives</code> and <code>falseNegatives</code> that are used to compute the
 * sensitivity at the given specificity. The threshold for the given specificity value is computed
 * and used to evaluate the corresponding sensitivity.
 *
 * <p>If <code>sampleWeights</code> is <code>null</code>>, weights default to 1. Use sample_weight
 * of 0 to mask values.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Sensitivity_and_specificity">Additional information
 *     about specificity and sensitivity</a>
 * @param <T> The data type for the metric result
 */
public class SensitivityAtSpecificity<T extends TNumber>
    extends SensitivitySpecificityBase<T> {

  private final float specificity;

  /**
   * Creates a SpecificityAtSensitivity metric with a name of {@link Class#getSimpleName()} and
   * {@link #DEFAULT_NUM_THRESHOLDS} for the number of thresholds
   *
   * @param tf The TensorFlow Ops
   * @param specificity the specificity. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds <= 0 or if specificity is not in the range
   *     [0-1].
   */
  public SensitivityAtSpecificity(Ops tf, float specificity, long seed, Class<T> type) {
    this(tf, null, specificity, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a SpecificityAtSensitivity metric with {@link #DEFAULT_NUM_THRESHOLDS} for the number
   * of thresholds
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param specificity the specificity. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds <= 0 or if specificity is not in the range
   *     [0-1].
   */
  public SensitivityAtSpecificity(
      Ops tf, String name, float specificity, long seed, Class<T> type) {
    this(tf, name, specificity, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric with a name of {@link Class#getSimpleName()}.
   *
   * @param tf The TensorFlow Ops
   * @param specificity the specificity. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     specificity.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds <= 0 or if specificity is not in the range
   *     [0-1].
   */
  public SensitivityAtSpecificity(
      Ops tf, float specificity, int numThresholds, long seed, Class<T> type) {
    this(tf, null, specificity, numThresholds, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param specificity the specificity. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     specificity.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds <= 0 or if specificity is not in the range
   *     [0-1].
   */
  public SensitivityAtSpecificity(
      Ops tf, String name, float specificity, int numThresholds, long seed, Class<T> type) {
    super(tf, name, specificity, numThresholds, seed, type);
    if (specificity < 0f || specificity > 1f)
      throw new IllegalArgumentException("specificity must be in the range [0, 1].");
    this.specificity = specificity;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> result() {
    Ops tf = getTF();
    Operand<T> specificities =
        tf.math.divNoNan(
            this.trueNegatives, tf.math.add(this.trueNegatives, this.falsePositives));
    Operand<T> sub =
        tf.math.sub(specificities, cast(tf, tf.constant(this.getValue()), getType()));
    Operand<TInt32> minIndex = tf.math.argMin(tf.math.abs(sub), tf.constant(0), TInt32.class);
    minIndex = tf.expandDims(minIndex, tf.constant(0));

    Operand<T> trueSlice = tf.slice(this.truePositives, minIndex, tf.constant(new int[] {1}));
    Operand<T> falseSlice = tf.slice(this.falseNegatives, minIndex, tf.constant(new int[] {1}));
    return tf.math.divNoNan(trueSlice, tf.math.add(trueSlice, falseSlice));
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
