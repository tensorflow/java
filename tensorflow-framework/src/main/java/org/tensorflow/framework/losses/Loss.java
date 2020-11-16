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
package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

public abstract class Loss {
  public static final Reduction REDUCTION_DEFAULT = Reduction.AUTO;

  protected final Ops tf;
  protected final Reduction reduction;

  /**
   * Creates a Loss using {@link Class#getSimpleName()}  as the name and a Loss Reduction of {@link
   * Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   */
  protected Loss(Ops tf) {
    this(tf, null, Reduction.AUTO);
  }

  /**
   * Creates a Loss using a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this Loss, if null the name will be {@link Class#getSimpleName()}.
   */
  protected Loss(Ops tf, String name) {
    this(tf, name, Reduction.AUTO);
  }

  /**
   * Creates a Loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss, if null the name will be {@link Class#getSimpleName()}.
   * @param reduction Type of Reduction to apply to the loss.
   */
  protected Loss(Ops tf, String name, Reduction reduction) {
    this.tf = name != null ? tf.withSubScope(name) : tf.withSubScope(getClass().getSimpleName());
    this.reduction = reduction;
  }

  /**
   * Calculates the loss
   *
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @param <T> The data type of the predictions and loss.
   * @param <U> The data type of the labels.
   * @return the loss
   */
  public <T extends TNumber, U extends TNumber> Operand<T> call(Operand<U> labels, Operand<T> predictions) {
    return call(labels, predictions, null);
  }

  /**
   * Generates an Operand that calculates the loss.
   *
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @param sampleWeights Optional sampleWeights acts as a coefficient for the loss. If a scalar is
   *     provided, then the loss is simply scaled by the given value. If SampleWeights is a tensor
   *     of size [batch_size], then the total loss for each sample of the batch is rescaled by the
   *     corresponding element in the SampleWeights vector. If the shape of SampleWeights is
   *     [batch_size, d0, .. dN-1] (or can be broadcasted to this shape), then each loss element of
   *     predictions is scaled by the corresponding value of SampleWeights. (Note on dN-1: all loss
   *     functions reduce by 1 dimension, usually axis=-1.)
   * @param <T> The data type of the predictions, sampleWeights and loss.
   * @param <U> The data type of the labels.
   * @return the loss
   */
  public abstract <T extends TNumber, U extends TNumber> Operand<T> call(
      Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights);

  /**
   * Gets the TensorFlow Ops
   *
   * @return the TensorFlow Ops
   */
  public Ops getTF() {
    return tf;
  }

  /**
   * Gets the loss reduction
   *
   * @return the loss reduction
   */
  public Reduction getReduction() {
    return reduction;
  }
}
