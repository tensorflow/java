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
import org.tensorflow.framework.losses.Losses;
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * A Metric that computes the binary cross-entropy loss between true labels and predicted labels.
 *
 * <p>This is the crossentropy metric class to be used when there are only two label classes (0 and
 * 1).
 *
 * @param <U> the data type for the predictions.
 * @param <T> The data type for the metric result
 */
public class BinaryCrossentropy<U extends TNumber, T extends TNumber>
    extends MeanMetricWrapper<U, T> implements LossMetric<T> {

  private final boolean fromLogits;
  private final float labelSmoothing;

  /**
   * Creates a BinaryCrossentropy metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param fromLogits Whether to interpret predictions as a tensor of logit values or not.
   * @param labelSmoothing value used to smooth labels, When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of label_smoothing
   *     correspond to heavier smoothing.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public BinaryCrossentropy(
      Ops tf, String name, boolean fromLogits, float labelSmoothing, long seed, Class<T> type) {
    super(tf, name, seed, type);
    setLoss(this);
    this.fromLogits = fromLogits;
    this.labelSmoothing = labelSmoothing;
  }

  /** {@inheritDoc} */
  @Override
  public <V extends TNumber> Operand<T> call(Operand<V> labels, Operand<T> predictions) {
    return Losses.binaryCrossentropy(getTF(), labels, predictions, fromLogits, labelSmoothing);
  }
}
