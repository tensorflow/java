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
 * A Metric that computes the binary cross-entropy loss between true labels and predicted labels.
 *
 * <p>This is the crossentropy metric class to be used when there are only two label classes (0 and
 * 1).
 *
 * @param <T> The data type for the metric result
 */
public class BinaryCrossentropy<T extends TNumber> extends MeanBaseMetricWrapper<T>
    implements LossMetric {

  private final boolean fromLogits;
  private final float labelSmoothing;

  /**
   * Creates a BinaryCrossentropy metric where name is {@link Class#getSimpleName()}.
   *
   * @param fromLogits Whether to interpret predictions as a tensor of logit values as opposed to a
   *     probability distribution.
   * @param labelSmoothing value used to smooth labels, When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of label_smoothing
   *     correspond to heavier smoothing.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public BinaryCrossentropy(boolean fromLogits, float labelSmoothing, long seed, Class<T> type) {
    this(null, fromLogits, labelSmoothing, seed, type);
  }
  /**
   * Creates a BinaryCrossentropy metric
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param fromLogits Whether to interpret predictions as a tensor of logit values as opposed to a
   *     probability distribution.
   * @param labelSmoothing value used to smooth labels, When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of label_smoothing
   *     correspond to heavier smoothing.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public BinaryCrossentropy(
      String name, boolean fromLogits, float labelSmoothing, long seed, Class<T> type) {
    super(name, seed, type);
    setLoss(this);
    this.fromLogits = fromLogits;
    this.labelSmoothing = labelSmoothing;
  }

  /**
   * Computes the binary crossentropy loss between labels and predictions.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the truth values or labels, has the same shape as predictions and shape = {@code
   *     [batch_size, d0, .. dN]}.
   * @param predictions the predictions, shape = {@code [batch_size, d0, .. dN]}.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return Binary crossentropy loss value. shape = {@code [batch_size, d0, .. dN-1]}.
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
    return Losses.binaryCrossentropy(tf, tLabels, tPredictions, fromLogits, labelSmoothing);
  }
}
