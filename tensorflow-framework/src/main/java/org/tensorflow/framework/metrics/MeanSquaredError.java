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
 * A metric that computes the mean of absolute difference between labels and predictions.
 *
 * <p>The {@code MeanSquaredError} class creates two local variables, {@code total} and {@code
 * count} that are used to compute the mean squared error. This average is weighted by {@code
 * weights}, and it is ultimately returned as the mean squared error: an idempotent operation that
 * simply divides {@code total} by {@code count}.
 *
 * <p>For estimation of the metric over a stream of data, the function creates an update operation
 * that updates these variables. Internally, a squared error operation computes the element-wise
 * square of the difference between {@code predictions} and {@code labels}. Then the update
 * operation increments {@code total} with the reduced sum of the product of {@code weights} and the
 * squared error, and it increments {@code count} with the reduced sum of {@code weights}.
 *
 * <p>If {@code weights} is null, weights default to 1. Use weights of 0 to mask values.
 *
 * @param <T> The data type for the metric result.
 */
public class MeanSquaredError<T extends TNumber> extends MeanBaseMetricWrapper<T>
    implements LossMetric {

  /**
   * Creates a Mean Absolute Error metric using {@link Class#getSimpleName()} for the metric name.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public MeanSquaredError(long seed, Class<T> type) {
    this(null, seed, type);
  }

  /**
   * Creates a Mean Absolute Error metric
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public MeanSquaredError(String name, long seed, Class<T> type) {
    super(name, seed, type);
    setLoss(this);
  }

  /**
   * Computes the mean squared error between the labels and predictions.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the truth values or labels. Must be the same shape as predictions.
   * @param predictions the predictions
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return Computes the mean squared error between the labels and predictions.
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
    return Losses.meanSquaredError(tf, tLabels, tPredictions);
  }
}
