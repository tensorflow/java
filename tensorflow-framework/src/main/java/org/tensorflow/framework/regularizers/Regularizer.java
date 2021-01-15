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

/** @param <R> the data type of the result */
public abstract class Regularizer<R extends TNumber> {

  public static final float DEFAULT_REGULARIZATION_PENALTY = 0.01f;

  private final Ops tf;
  private final String name;
  protected Class<R> type;

  /**
   * Create a Regularizer
   *
   * @param tf the TensorFlow ops.
   */
  protected Regularizer(Ops tf, Class<R> type) {
    this(tf, null, type);
  }
  /**
   * Create a Regularizer
   *
   * @param tf the TensorFlow ops.
   */
  protected Regularizer(Ops tf, String name, Class<R> type) {
    this.tf = tf;
    this.type = type;
    this.name = name == null ? this.getClass().getSimpleName() : name;
  }

  /**
   * Returns this Regularizer as a Loss This is a convenience tp regularize a loss. Only
   * sampleWeights are applied to the regularizer.
   *
   * @return this Regularizer as a Loss
   */
  public Loss asLoss() {
    return new RegularizerLoss<R>(this.tf, this);
  }

  /**
   * Computes a regularization penalty from an input.
   *
   * @param input teh weighted input
   * @return the result of computing the regularization penalty
   */
  public abstract Operand<R> call(Operand<R> input);

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
