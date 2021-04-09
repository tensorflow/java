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
 * Computes best precision where recall is &gt;= specified value.
 * @param <T> The data type for the metric result
 */
public class PrecisionAtRecall<T extends TNumber>
    extends SensitivitySpecificityBase<T> {

  private final float recall;

  /**
   * Creates a PrecisionRecall metric with a name of {@link Class#getSimpleName()} and {@link
   * #DEFAULT_NUM_THRESHOLDS} for the number of thresholds
   *
   * @param tf The TensorFlow Ops
   * @param recall the recall. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds <= 0 or if recall is not in the range [0-1].
   */
  public PrecisionAtRecall(Ops tf, float recall, long seed, Class<T> type) {
    this(tf, null, recall, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric with {@link #DEFAULT_NUM_THRESHOLDS} for the number of
   * thresholds
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param recall the recall. A scalar value in range [0, 1]
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds <= 0 or if recall is not in the range [0-1].
   */
  public PrecisionAtRecall(Ops tf, String name, float recall, long seed, Class<T> type) {
    this(tf, name, recall, DEFAULT_NUM_THRESHOLDS, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric with a name of {@link Class#getSimpleName()}.
   *
   * @param tf The TensorFlow Ops
   * @param recall the recall. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     recall.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds <= 0 or if recall is not in the range [0-1].
   */
  public PrecisionAtRecall(Ops tf, float recall, int numThresholds, long seed, Class<T> type) {
    this(tf, null, recall, numThresholds, seed, type);
  }

  /**
   * Creates a PrecisionRecall metric.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if null defaults to {@link Class#getSimpleName()}
   * @param recall the recall. A scalar value in range [0, 1]
   * @param numThresholds Defaults to 200. The number of thresholds to use for matching the given
   *     recall.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds <= 0 or if recall is not in the range [0-1].
   */
  public PrecisionAtRecall(
      Ops tf, String name, float recall, int numThresholds, long seed, Class<T> type) {
    super(tf, name, recall, numThresholds, seed, type);
    if (recall < 0f || recall > 1f)
      throw new IllegalArgumentException("recall must be in the range [0, 1].");
    this.recall = recall;
  }

  @Override
  public Operand<T> result() {
    Ops tf = getTF();

    Operand<T> recall =
        tf.math.divNoNan(
            this.truePositives, tf.math.add(this.truePositives, this.falseNegatives));
    Operand<T> sub = tf.math.sub(recall, cast(tf, tf.constant(value), getType()));
    Operand<TInt32> minIndex = tf.math.argMin(tf.math.abs(sub), tf.constant(0), TInt32.class);
    minIndex = tf.expandDims(minIndex, tf.constant(0));

    Operand<T> trueSlice = tf.slice(this.truePositives, minIndex, tf.constant(new int[] {1}));
    Operand<T> falseSlice = tf.slice(this.falsePositives, minIndex, tf.constant(new int[] {1}));
    return tf.math.divNoNan(trueSlice, tf.math.add(trueSlice, falseSlice));
  }

  /** @return the recall */
  public float getRecall() {
    return recall;
  }
}
