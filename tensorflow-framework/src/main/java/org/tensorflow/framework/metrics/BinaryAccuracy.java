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
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Metric that calculates how often predictions matches binary labels.
 *
 * <p>This metric creates two local variables, total and count that are used to compute the
 * frequency with which {@code predictions} matches {@code labels}. This frequency is
 * ultimately returned as binary accuracy: an idempotent operation that simply divides total by
 * count.
 *
 * <p>If sampleWeights is {@code null}, weights default to 1. Use sampleWeights of 0 to mask
 * values.
 *
 * @param <T> The data type for the metric result
 */
public class BinaryAccuracy<T extends TNumber> extends MeanMetricWrapper<T>
    implements LossMetric<T> {
  /** the default threshold value for deciding whether prediction values are 1 or 0 */
  public static final float DEFAULT_THRESHOLD = 0.5f;

  /** the threshold value for deciding whether prediction values are 1 or 0 */
  private final float threshold;

  /**
   * Creates a BinaryAccuracy Metric using {@link Class#getSimpleName()} for the metric name and
   * {@link #DEFAULT_THRESHOLD} for the threshold value.
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public BinaryAccuracy(Ops tf, long seed, Class<T> type) {
    this(tf, null, DEFAULT_THRESHOLD, seed, type);
  }

  /**
   * Creates a BinaryAccuracy Metric using {@link Class#getSimpleName()} for the metric name
   *
   * @param tf the TensorFlow Ops
   * @param threshold a threshold for deciding whether prediction values are 1 or 0
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public BinaryAccuracy(Ops tf, float threshold, long seed, Class<T> type) {
    this(tf, null, threshold, seed, type);
  }

  /**
   * Creates a BinaryAccuracy Metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param threshold a threshold for deciding whether prediction values are 1 or 0
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public BinaryAccuracy(Ops tf, String name, float threshold, long seed, Class<T> type) {
    super(tf, name, seed, type);
    this.threshold = threshold;
    setLoss(this);
  }

  /**
   * Calculates how often predictions match binary labels.
   *
   * @param labels the truth values or labels, shape = {@code [batch_size, d0, .. dN]}.
   * @param predictions the predictions, shape = {@code [batch_size, d0, .. dN]}.
   * @return Binary accuracy values. shape = {@code [batch_size, d0, .. dN-1]}
   */
  @Override
  public Operand<T> call(
      Operand<? extends TNumber> labels, Operand<? extends TNumber> predictions) {

    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    Operand<T> thresholdCast = cast(getTF(), getTF().constant(threshold), getResultType());
    tPredictions =
        cast(getTF(), getTF().math.greater(tPredictions, thresholdCast), getResultType());
    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    return cast(getTF(), getTF().math.equal(tLabels, tPredictions), getResultType());
  }
}
