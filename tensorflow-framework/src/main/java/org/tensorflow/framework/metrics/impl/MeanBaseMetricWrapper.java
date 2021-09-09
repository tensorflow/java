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
package org.tensorflow.framework.metrics.impl;

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.framework.metrics.Mean;
import org.tensorflow.framework.metrics.MetricReduction;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * A class that bridges a stateless loss function with the {@link Mean} metric using a reduction of
 * {@link MetricReduction#WEIGHTED_MEAN}.
 *
 * <p>The loss function calculates the loss between the {@code labels} and {@code predictions } then
 * passes this loss to the {@link Mean} metric to calculate the weighted mean of the loss over many
 * iterations or epochs
 *
 * @param <T> The data type for the metric result
 */
public class MeanBaseMetricWrapper<T extends TNumber> extends Mean<T> {

  /** The loss function interface */
  protected LossMetric loss;

  /**
   * Creates a Reducible Metric with a metric reductions of {@link MetricReduction#WEIGHTED_MEAN}
   *
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanBaseMetricWrapper(String name, long seed, Class<T> type) {
    super(name, seed, type);
  }

  /**
   * Gets the loss function.
   *
   * @return the loss function.
   */
  public LossMetric getLoss() {
    return loss;
  }

  /**
   * Sets the AbstractLoss function for this wrapper.
   *
   * @param loss the loss function.
   */
  protected void setLoss(LossMetric loss) {
    this.loss = loss;
  }

  /**
   * Creates Operations that update the state of the mean metric, by calling the loss function and
   * passing the loss to the Mean metric to calculate the weighted mean of the loss over many
   * iterations.
   *
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @param sampleWeights Optional sampleWeights acts as a coefficient for the loss. If a scalar is
   *     provided, then the loss is simply scaled by the given value. If sampleWeights is a tensor
   *     of size [batch_size], then the total loss for each sample of the batch is rescaled by the
   *     corresponding element in the sampleWeights vector. If the shape of sampleWeights is
   *     [batch_size, d0, .. dN-1] (or can be broadcasted to this shape), then each loss element of
   *     predictions is scaled by the corresponding value of sampleWeights. (Note on dN-1: all loss
   *     functions reduce by 1 dimension, usually axis=-1.)
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return a List of control operations that updates the Mean state variables.
   */
  public List<Op> updateStateList(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    if (labels == null || predictions == null) {
      throw new IllegalArgumentException("missing required inputs for labels and predictions");
    }

    init(tf);
    Operand<T> tLabels = cast(tf, labels, getInternalType());
    Operand<T> tPredictions = cast(tf, predictions, getInternalType());

    Operand<T> losses = loss.call(tf, tLabels, tPredictions, getInternalType());

    return super.updateStateList(tf, cast(tf, losses, predictions.type()), sampleWeights);
  }
}
