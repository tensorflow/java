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
 * A metric that computes the sparse categorical cross-entropy loss between true labels and
 * predicted labels.
 *
 * <p>You can provide logits of classes as predictions, since argmax of logits and probabilities are
 * same.
 *
 * @param <T> The data type for the metric result.
 */
public class SparseCategoricalCrossentropy<T extends TNumber> extends MeanMetricWrapper<T>
    implements LossMetric<T> {

  private final boolean fromLogits;
  private final int axis;

  /**
   * Creates a SparseCategoricalCrossentropy metric using a name based on {@link
   * Class#getSimpleName()}.
   *
   * @param fromLogits Whether to interpret predictions as a tensor of logit values as opposed to a
   *     probability distribution.
   * @param axis The dimension along which the entropy is computed.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public SparseCategoricalCrossentropy(boolean fromLogits, int axis, long seed, Class<T> type) {
    this(null, fromLogits, axis, seed, type);
  }

  /**
   * Creates a SparseCategoricalCrossentropy metric
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param fromLogits Whether to interpret predictions as a tensor of logit values as opposed to a
   *     probability distribution.
   * @param axis The dimension along which the entropy is computed.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public SparseCategoricalCrossentropy(
      String name, boolean fromLogits, int axis, long seed, Class<T> type) {
    super(name, seed, type);
    setLoss(this);
    this.fromLogits = fromLogits;
    this.axis = axis;
  }

  /**
   * Calculates how often predictions matches integer labels.
   *
   * @param labels Integer ground truth values.
   * @param predictions the predictions
   * @return Sparse categorical accuracy values.
   */
  @Override
  public Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<? extends TNumber> predictions) {
    init(tf);
    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    return Losses.sparseCategoricalCrossentropy(getTF(), tLabels, tPredictions, fromLogits, axis);
  }
}
