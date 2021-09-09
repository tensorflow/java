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
 * A Metric that computes the categorical cross-entropy loss between true labels and predicted
 * labels.
 *
 * <p>This is the crossentropy metric class to be used when there are multiple label classes (2 or
 * more). The labels should be given as a one_hot representation. eg., When labels values are {@code
 * [2, 0, 1]}, the labels Operand contains = {@code [[0, 0, 1], [1, 0, 0], [0, 1, 0]] }.
 *
 * @param <T> The data type for the metric result
 */
public class CategoricalCrossentropy<T extends TNumber> extends MeanBaseMetricWrapper<T>
    implements LossMetric {

  private final boolean fromLogits;
  private final float labelSmoothing;
  private final int axis;
  /**
   * Creates a CategoricalCrossentropy metric that computes the crossentropy metric between the
   * labels and predictions using {@link Class#getSimpleName()} for the metric name
   *
   * <p>Uses a {@link Losses#CHANNELS_LAST} for the channel axis.
   *
   * @param fromLogits Whether to interpret predictions as a tensor of logit values oras opposed to
   *     a probability distribution.
   * @param labelSmoothing value used to smooth labels, When &gt; 0, label values are smoothed,
   *     meaning the confidence on label values are relaxed. e.g. {@code labelSmoothing=0.2} means
   *     that we will use a value of {@code 0.1} for label {@code 0} and {@code 0.9 } for label
   *     {@code 1}
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CategoricalCrossentropy(
      boolean fromLogits, float labelSmoothing, long seed, Class<T> type) {
    this(null, fromLogits, labelSmoothing, Losses.CHANNELS_LAST, seed, type);
  }

  /**
   * Creates a CategoricalCrossentropy metric that computes the crossentropy metric between the
   * labels and predictions using {@link Class#getSimpleName()} for the metric name.
   *
   * @param fromLogits Whether to interpret predictions as a tensor of logit values as opposed to a
   *     probability distribution.
   * @param labelSmoothing value used to smooth labels, When &gt; 0, label values are smoothed,
   *     meaning the confidence on label values are relaxed. e.g. {@code labelSmoothing=0.2} means
   *     that we will use a value of {@code 0.1} for label {@code 0} and {@code 0.9 } for label
   *     {@code 1}
   * @param axis Int specifying the channels axis. {@code axis={@link Losses#CHANNELS_LAST}}
   *     corresponds to data format {@code channels_last}, and {@code axis={@link
   *     Losses#CHANNELS_FIRST}} corresponds to data format {@code channels_first}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CategoricalCrossentropy(
      boolean fromLogits, float labelSmoothing, int axis, long seed, Class<T> type) {
    this(null, fromLogits, labelSmoothing, axis, seed, type);
  }

  /**
   * Creates a CategoricalCrossentropy metric that computes the crossentropy metric between the
   * labels and predictions.
   *
   * <p>Uses a {@link Losses#CHANNELS_LAST} for the channel axis.
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param fromLogits Whether to interpret predictions as a tensor of logit values oras opposed to
   *     a probability distribution.
   * @param labelSmoothing value used to smooth labels, When &gt; 0, label values are smoothed,
   *     meaning the confidence on label values are relaxed. e.g. {@code labelSmoothing=0.2} means
   *     that we will use a value of {@code 0.1} for label {@code 0} and {@code 0.9 } for label
   *     {@code 1}
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CategoricalCrossentropy(
      String name, boolean fromLogits, float labelSmoothing, long seed, Class<T> type) {
    this(name, fromLogits, labelSmoothing, Losses.CHANNELS_LAST, seed, type);
  }

  /**
   * Creates a CategoricalCrossentropy metric that computes the crossentropy metric between the
   * labels and predictions.
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param fromLogits Whether to interpret predictions as a tensor of logit values as opposed to a
   *     probability distribution.
   * @param labelSmoothing value used to smooth labels, When &gt; 0, label values are smoothed,
   *     meaning the confidence on label values are relaxed. e.g. {@code labelSmoothing=0.2} means
   *     that we will use a value of {@code 0.1} for label {@code 0} and {@code 0.9 } for label
   *     {@code 1}
   * @param axis Int specifying the channels axis. {@code axis={@link Losses#CHANNELS_LAST}}
   *     corresponds to data format {@code channels_last}, and {@code axis={@link
   *     Losses#CHANNELS_FIRST}} corresponds to data format {@code channels_first}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CategoricalCrossentropy(
      String name, boolean fromLogits, float labelSmoothing, int axis, long seed, Class<T> type) {
    super(name, seed, type);
    setLoss(this);
    this.fromLogits = fromLogits;
    this.labelSmoothing = labelSmoothing;
    this.axis = axis;
  }

  /**
   * Computes the crossentropy loss between the labels and predictions.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the truth values or labels, of one-hot true targets, same shape as predictions
   * @param predictions the predictions
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return Categorical crossentropy loss value.
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
    return Losses.categoricalCrossentropy(
        tf, tLabels, tPredictions, fromLogits, labelSmoothing, axis);
  }
}
