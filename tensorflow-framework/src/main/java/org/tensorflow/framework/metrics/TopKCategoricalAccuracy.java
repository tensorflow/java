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
 * Computes the poisson loss metric between labels and predictions.
 *
 * @param <T> The data type for the metric result
 */
public class TopKCategoricalAccuracy<T extends TNumber> extends MeanMetricWrapper<T>
    implements LossMetric<T> {
  public static final int DEFAULT_K = 5;
  /** Number of top elements to look at for computing accuracy. */
  private final int k;

  /**
   * Creates a TopKCategoricalAccuracy metric using {@link #DEFAULT_K} for <code>k</code>, Number of
   * top elements to look at for computing accuracy.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type The data type for the metric result
   */
  public TopKCategoricalAccuracy(Ops tf, String name, long seed, Class<T> type) {
    this(tf, name, DEFAULT_K, seed, type);
  }

  /**
   * Creates a TopKCategoricalAccuracy metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param k Number of top elements to look at for computing accuracy.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type The data type for the metric result
   */
  public TopKCategoricalAccuracy(Ops tf, String name, int k, long seed, Class<T> type) {
    super(tf, name, seed, type);
    this.k = k;
    setLoss(this);
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(
      Operand<? extends TNumber> labels, Operand<? extends TNumber> predictions) {
    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    return Metrics.topKCategoricalAccuracy(getTF(), tLabels, tPredictions, k);
  }
}
