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

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanBaseMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Metric that calculates how often predictions matches binary labels.
 *
 * <p>This metric creates two local variables, total and count that are used to compute the
 * frequency with which {@code predictions} matches {@code labels}. This frequency is ultimately
 * returned as binary accuracy: an idempotent operation that simply divides total by count.
 *
 * <p>If sampleWeights is {@code null}, weights default to 1. Use sampleWeights of 0 to mask values.
 *
 * @param <T> The data type for the metric result
 */
public class BinaryAccuracy<T extends TNumber> extends MeanBaseMetricWrapper<T>
    implements LossMetric {
  /** the default threshold value for deciding whether prediction values are 1 or 0 */
  public static final float DEFAULT_THRESHOLD = 0.5f;

  /** the threshold value for deciding whether prediction values are 1 or 0 */
  private final float threshold;

  /**
   * Creates a BinaryAccuracy Metric using {@link Class#getSimpleName()} for the metric name and
   * {@link #DEFAULT_THRESHOLD} for the threshold value.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public BinaryAccuracy(long seed, Class<T> type) {
    this(null, DEFAULT_THRESHOLD, seed, type);
  }

  /**
   * Creates a BinaryAccuracy Metric using {@link Class#getSimpleName()} for the metric name
   *
   * @param threshold a threshold for deciding whether prediction values are 1 or 0
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public BinaryAccuracy(float threshold, long seed, Class<T> type) {
    this(null, threshold, seed, type);
  }

  /**
   * Creates a BinaryAccuracy Metric
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param threshold a threshold for deciding whether prediction values are 1 or 0
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public BinaryAccuracy(String name, float threshold, long seed, Class<T> type) {
    super(name, seed, type);
    this.threshold = threshold;
    setLoss(this);
  }

  /**
   * Calculates how often predictions match binary labels.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the truth values or labels, shape = {@code [batch_size, d0, .. dN]}.
   * @param predictions the predictions, shape = {@code [batch_size, d0, .. dN]}.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return Binary accuracy values. shape = {@code [batch_size, d0, .. dN-1]}
   */
  @Override
  public <U extends TNumber> Operand<U> call(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Class<U> resultType) {
    init(tf);
    Operand<T> tPredictions = cast(tf, predictions, getInternalType());
    Operand<T> thresholdCast = cast(tf, tf.constant(threshold), getInternalType());
    tPredictions = cast(tf, tf.math.greater(tPredictions, thresholdCast), getInternalType());
    Operand<T> tLabels = cast(tf, labels, getInternalType());
    return cast(tf, tf.math.equal(tLabels, tPredictions), resultType);
  }
}
