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

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * A Metric that computes the categorical cross-entropy loss between true labels and predicted
 * labels.
 *
 * <p>This is the crossentropy metric class to be used when there are multiple label classes (2 or
 * more). The labels should be given as a one_hot representation. eg., When labels values are <code>
 * [2, 0, 1]</code>, the labels Operand contains = <code>[[0, 0, 1], [1, 0, 0], [0, 1, 0]]
 * </code>.
 *
 * @param <T> The data type for the metric result
 */
public class CategoricalCrossentropy<T extends TNumber> extends MeanMetricWrapper<T>
    implements LossMetric<T> {

  private final boolean fromLogits;
  private final float labelSmoothing;
  private final int axis;

  /**
   * Creates a CategoricalCrossentropy metric that computes the crossentropy metric between the
   * labels and predictions.
   *
   * <p>Uses a {@link Losses#CHANNELS_LAST} for the channel axis.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param fromLogits Whether to interpret predictions as a tensor of logit values oras opposed to
   *     a probability distribution.
   * @param labelSmoothing value used to smooth labels, When &gt; 0, label values are smoothed,
   *     meaning the confidence on label values are relaxed. e.g. <code>labelSmoothing=0.2</code>
   *     means that we will use a value of <code>0.1</code> for label <code>0</code> and <code>0.9
   *     </code> for label <code>1</code>
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CategoricalCrossentropy(
      Ops tf, String name, boolean fromLogits, float labelSmoothing, long seed, Class<T> type) {
    this(tf, name, fromLogits, labelSmoothing, Losses.CHANNELS_LAST, seed, type);
  }

  /**
   * Creates a CategoricalCrossentropy metric that computes the crossentropy metric between the
   * labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param fromLogits Whether to interpret predictions as a tensor of logit values as opposed to a
   *     probability distribution.
   * @param labelSmoothing value used to smooth labels, When &gt; 0, label values are smoothed,
   *     meaning the confidence on label values are relaxed. e.g. <code>labelSmoothing=0.2</code>
   *     means that we will use a value of <code>0.1</code> for label <code>0</code> and <code>0.9
   *     </code> for label <code>1</code>
   * @param axis Int specifying the channels axis. <code>axis={@link Losses#CHANNELS_LAST}</code>
   *     corresponds to data format <code>channels_last</code>, and <code>
   *     axis={@link Losses#CHANNELS_FIRST}</code> corresponds to data format <code>
   *     channels_first</code>.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CategoricalCrossentropy(
      Ops tf,
      String name,
      boolean fromLogits,
      float labelSmoothing,
      int axis,
      long seed,
      Class<T> type) {
    super(tf, name, seed, type);
    setLoss(this);
    this.fromLogits = fromLogits;
    this.labelSmoothing = labelSmoothing;
    this.axis = axis;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<? extends TNumber> labels, Operand<? extends TNumber> predictions) {
    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    return Losses.categoricalCrossentropy(
        getTF(), tLabels, tPredictions, fromLogits, labelSmoothing, axis);
  }
}
