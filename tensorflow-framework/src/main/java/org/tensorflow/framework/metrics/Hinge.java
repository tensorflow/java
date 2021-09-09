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
import org.tensorflow.framework.losses.Losses;
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanBaseMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * A metric that computes the hinge loss metric between labels and predictions.
 *
 * @param <T> The data type for the metric result.
 */
public class Hinge<T extends TNumber> extends MeanBaseMetricWrapper<T> implements LossMetric {

  /**
   * Creates a Hinge metric using {@link Class#getSimpleName()} for the metric name.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public Hinge(long seed, Class<T> type) {
    this(null, seed, type);
  }
  /**
   * Creates a Hinge metric
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public Hinge(String name, long seed, Class<T> type) {
    super(name, seed, type);
    setLoss(this);
  }

  /**
   * Computes the hinge loss between labels and predictions.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the truth values or labels, shape = {@code [batch_size, d0, .. dN]}.
   * @param predictions the predictions, shape = {@code [batch_size, d0, .. dN]}.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return the hinge loss between labels and predictions.
   */
  @Override
  public <U extends TNumber> Operand<U> call(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Class<U> resultType) {
    init(tf);
    Operand<U> tLabels = cast(tf, labels, resultType);
    Operand<U> tPredictions = cast(tf, predictions, resultType);
    return Losses.hinge(tf, tLabels, tPredictions);
  }
}
