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
 * Computes best precision where recall is &gt;= specified value.
 *
 * <p>This metric creates four local variables, truePositives, trueNegatives, falsePositives and
 * falseNegatives that are used to compute the precision at the given recall. The threshold for the
 * given recall value is computed and used to evaluate the corresponding precision.
 *
 * <p>If {@code sampleWeights} is null, weights default to 1. Use {@code sampleWeights} of 0 to mask
 * values.
 *
 * @param <T> The data type for the metric result
 */
public class PrecisionAtRecall<T extends TNumber> extends SensitivitySpecificityBase<T> {

  private final float recall;

  /**
   * Creates a PrecisionRecall metric with a name of {@link Class#getSimpleName()} and {@link
   * #DEFAULT_NUM_THRESHOLDS} for the number of thresholds
   *
   * @param recall the recall. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if recall is not in the range
   *     [0-1].
   */
  public PrecisionAtRecall(float recall, long seed, Class<T> type) {
    this(null, recall, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric with {@link #DEFAULT_NUM_THRESHOLDS} for the number of
   * thresholds
   *
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param recall the recall. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if recall is not in the range
   *     [0-1].
   */
  public PrecisionAtRecall(String name, float recall, long seed, Class<T> type) {
    this(name, recall, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric with a name of {@link Class#getSimpleName()}.
   *
   * @param recall the recall. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     recall.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if recall is not in the range
   *     [0-1].
   */
  public PrecisionAtRecall(float recall, int numThresholds, long seed, Class<T> type) {
    this(null, recall, numThresholds, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric.
   *
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param recall the recall. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     recall.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0 or if recall is not in the range
   *     [0-1].
   */
  public PrecisionAtRecall(String name, float recall, int numThresholds, long seed, Class<T> type) {
    super(name, numThresholds, seed, type);
    if (recall < 0f || recall > 1f)
      throw new IllegalArgumentException("recall must be in the range [0, 1].");
    this.recall = recall;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> Operand<U> result(Ops tf, Class<U> resultType) {
    init(tf);
    Operand<T> div = tf.math.divNoNan(truePositives, tf.math.add(truePositives, falseNegatives));
    Operand<T> sub = tf.math.sub(div, cast(tf, tf.constant(recall), getType()));
    Operand<TInt32> minIndex = tf.math.argMin(tf.math.abs(sub), tf.constant(0), TInt32.class);
    minIndex = tf.expandDims(minIndex, tf.constant(0));

    Operand<T> trueSlice = tf.slice(truePositives, minIndex, tf.constant(new int[] {1}));
    Operand<T> falseSlice = tf.slice(falsePositives, minIndex, tf.constant(new int[] {1}));
    return cast(tf, tf.math.divNoNan(trueSlice, tf.math.add(trueSlice, falseSlice)), resultType);
  }

  /**
   * Gets the recall value
   *
   * @return the recall value
   */
  public float getRecall() {
    return recall;
  }
}
