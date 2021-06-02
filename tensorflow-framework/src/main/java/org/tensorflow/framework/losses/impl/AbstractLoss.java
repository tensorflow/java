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
package org.tensorflow.framework.losses.impl;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.Loss;
import org.tensorflow.framework.losses.Reduction;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

public abstract class AbstractLoss implements Loss {
  public static final Reduction REDUCTION_DEFAULT = Reduction.AUTO;

  protected final Reduction reduction;
  private final String name;

  /**
   * Creates a AbstractLoss using {@link Class#getSimpleName()} as the name and a AbstractLoss
   * Reduction of {@link AbstractLoss#REDUCTION_DEFAULT}
   */
  protected AbstractLoss() {
    this(null, Reduction.AUTO);
  }

  /**
   * Creates a AbstractLoss using a AbstractLoss Reduction of {@link AbstractLoss#REDUCTION_DEFAULT}
   *
   * @param name the name of this AbstractLoss, if null the name will be {@link
   *     Class#getSimpleName()}.
   */
  protected AbstractLoss(String name) {
    this(name, Reduction.AUTO);
  }

  /**
   * Creates a AbstractLoss
   *
   * @param name the name of this loss, if null the name will be {@link Class#getSimpleName()}.
   * @param reduction Type of Reduction to apply to the loss.
   */
  protected AbstractLoss(String name, Reduction reduction) {
    this.name = name == null ? getClass().getSimpleName() : name;
    this.reduction = reduction;
  }

  /**
   * Calculates the loss
   *
   * @param tf the TensorFlow Ops
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @param <T> The data type of the predictions and loss.
   * @return the loss
   */
  public <T extends TNumber> Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    return call(tf, labels, predictions, null);
  }

  /**
   * Gets the loss reduction
   *
   * @return the loss reduction
   */
  public Reduction getReduction() {
    return reduction;
  }

  /**
   * Gets the name for this loss
   *
   * @return the name for this loss
   */
  public String getName() {
    return name;
  }
}
