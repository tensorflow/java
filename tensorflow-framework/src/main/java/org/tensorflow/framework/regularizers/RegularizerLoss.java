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
 * A Regularizer call wrapped as a Loss instance
 *
 * <p>This class facilitates using a regularizer as a loss, only <code>sampleWeights</code> are
 * regularized.
 */
class RegularizerLoss extends Loss {

  private final Regularizer regularizer;

  /**
   * Creates a Loss using {@link Class#getSimpleName()} as the name and a Loss Reduction of {@link
   * Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   * @param regularizer the regularizer used to calculate the loss
   */
  public RegularizerLoss(Ops tf, Regularizer regularizer) {
    this(tf, null, regularizer);
  }

  /**
   * Creates a Loss using a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this Loss, if null the name will be {@link Class#getSimpleName()}.
   * @param regularizer the regularizer used to calculate the loss
   */
  public RegularizerLoss(Ops tf, String name, Regularizer regularizer) {
    super(tf, name);
    this.regularizer = regularizer;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(
      Operand<? extends TNumber> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    if (sampleWeights == null) {
      throw new IllegalArgumentException("sampleWeights cannot be null");
    }
    return regularizer.call(sampleWeights);
  }
}
