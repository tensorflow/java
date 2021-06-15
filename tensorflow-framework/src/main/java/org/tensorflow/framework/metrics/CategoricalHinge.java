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
import org.tensorflow.framework.losses.Losses;
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * A Metric that computes the categorical hinge loss metric between labels and predictions.
 *
 * @param <T> The data type for the metric result
 */
public class CategoricalHinge<T extends TNumber> extends MeanMetricWrapper<T>
    implements LossMetric<T> {

  /**
   * Creates a CategoricalHinge metric
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CategoricalHinge(String name, long seed, Class<T> type) {
    super(name, seed, type);
    setLoss(this);
  }

  /**
   * Creates a CategoricalHinge metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CategoricalHinge(Ops tf, String name, long seed, Class<T> type) {
    this(name, seed, type);
    init(tf);
  }

  /**
   * Computes the categorical hinge metric between {@code labels} and @{code predictions}.
   *
   * @param labels the truth values or labels, labels values are expected to be 0 or 1.
   * @param predictions the predictions
   * @return Categorical hinge loss values.
   */
  @Override
  public Operand<T> call(
      Operand<? extends TNumber> labels, Operand<? extends TNumber> predictions) {
    Ops tf = checkTF();
    Operand<T> tLabels = cast(tf, labels, getResultType());
    Operand<T> tPredictions = cast(tf, predictions, getResultType());
    return Losses.categoricalHinge(tf, tLabels, tPredictions);
  }
}
