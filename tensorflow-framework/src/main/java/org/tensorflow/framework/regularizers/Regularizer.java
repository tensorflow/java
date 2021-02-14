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
package org.tensorflow.framework.regularizers;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.Loss;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Base class for Regularizers
 *
 * <p>Regularizers allow you to apply penalties on layer parameters or layer activity during
 * optimization. These penalties are summed into the loss function that the network optimizes.
 */
public abstract class Regularizer {

  public static final float DEFAULT_REGULARIZATION_PENALTY = 0.01f;

  private final Ops tf;
  private final String name;

  /**
   * Creates a Regularizer, using {@link Class#getSimpleName()} for the name
   *
   * @param tf the TensorFlow ops.
   */
  protected Regularizer(Ops tf) {
    this(tf, null);
  }
  /**
   * Creates a Regularizer
   *
   * @param tf the TensorFlow ops.
   * @param name the name of this regularizer, if null use {@link Class#getSimpleName()} for the
   *     name.
   */
  protected Regularizer(Ops tf, String name) {
    this.tf = tf;
    this.name = name == null ? this.getClass().getSimpleName() : name;
  }

  /**
   * Returns this Regularizer as a Loss This is a convenience to use regularize a loss. Only
   * sampleWeights are applied to the regularizer.
   *
   * @return this Regularizer as a Loss
   */
  public Loss asLoss() {
    return new RegularizerLoss(this.tf, this);
  }

  /**
   * Computes a regularization penalty from an input.
   *
   * @param input the weighted input
   * @return the result of computing the regularization penalty
   * @param <R> the data type of the input and result
   */
  public abstract <R extends TNumber> Operand<R> call(Operand<R> input);

  /**
   * Gets the TensorFlow Ops
   *
   * @return the TensorFlow Ops
   */
  public Ops getTF() {
    return tf;
  }

  /**
   * Gets the name for this regularizer
   *
   * @return the name for this regularizer
   */
  public String getName() {
    return name;
  }
}
