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
 * Computes the poisson loss metric between labels and predictions.
 *
 * @param <T> The data type for the metric result
 */
public class TopKCategoricalAccuracy<T extends TNumber> extends MeanBaseMetricWrapper<T>
    implements LossMetric {
  public static final int DEFAULT_K = 5;
  /** Number of top elements to look at for computing accuracy. */
  private final int k;

  /**
   * Creates a TopKCategoricalAccuracy metric using {@link #DEFAULT_K} for {@code k}, Number of top
   * elements to look at for computing accuracy and using {@link Class#getSimpleName()} for the
   * metric name.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type The data type for the metric result
   */
  public TopKCategoricalAccuracy(long seed, Class<T> type) {
    this(null, DEFAULT_K, seed, type);
  }

  /**
   * Creates a TopKCategoricalAccuracy metric using {@link Class#getSimpleName()} for the metric
   * name.
   *
   * @param k Number of top elements to look at for computing accuracy.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type The data type for the metric result
   */
  public TopKCategoricalAccuracy(int k, long seed, Class<T> type) {
    this(null, k, seed, type);
  }

  /**
   * Creates a TopKCategoricalAccuracy metric using {@link #DEFAULT_K} for {@code k}, Number of top
   * elements to look at for computing accuracy.
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type The data type for the metric result
   */
  public TopKCategoricalAccuracy(String name, long seed, Class<T> type) {
    this(name, DEFAULT_K, seed, type);
  }

  /**
   * Creates a TopKCategoricalAccuracy metric
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param k Number of top elements to look at for computing accuracy.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type The data type for the metric result
   */
  public TopKCategoricalAccuracy(String name, int k, long seed, Class<T> type) {
    super(name, seed, type);
    this.k = k;
    setLoss(this);
  }

  /**
   * Computes how often targets are in the top {@code K} predictions.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return Top K categorical accuracy value.
   */
  @Override
  public <U extends TNumber> Operand<U> call(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Class<U> resultType) {
    init(tf);
    Operand<T> tLabels = cast(tf, labels, getInternalType());
    Operand<T> tPredictions = cast(tf, predictions, getInternalType());
    return cast(tf, Metrics.topKCategoricalAccuracy(tf, tLabels, tPredictions, k), resultType);
  }
}
