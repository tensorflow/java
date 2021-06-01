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
import org.tensorflow.framework.losses.impl.AbstractLoss;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * A AbstractRegularizer call wrapped as a AbstractLoss instance
 *
 * <p>This class facilitates using a regularizer as a loss, only <code>sampleWeights</code> are
 * regularized.
 */
class RegularizerLoss extends AbstractLoss {

  private final AbstractRegularizer regularizer;

  /**
   * Creates a AbstractLoss using {@link Class#getSimpleName()} as the name and a AbstractLoss
   * Reduction of {@link AbstractLoss#REDUCTION_DEFAULT}
   *
   * @param regularizer the regularizer used to calculate the loss
   */
  public RegularizerLoss(AbstractRegularizer regularizer) {
    this(null, regularizer);
  }

  /**
   * Creates a AbstractLoss using a AbstractLoss Reduction of {@link AbstractLoss#REDUCTION_DEFAULT}
   *
   * @param name the name of this AbstractLoss, if null the name will be {@link
   *     Class#getSimpleName()}.
   * @param regularizer the regularizer used to calculate the loss
   */
  public RegularizerLoss(String name, AbstractRegularizer regularizer) {
    super(name);
    this.regularizer = regularizer;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    if (sampleWeights == null) {
      throw new IllegalArgumentException("sampleWeights cannot be null");
    }
    return regularizer.call(tf, sampleWeights);
  }
}
