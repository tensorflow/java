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

import org.tensorflow.framework.losses.impl.AbstractLoss;

/**
 * Base class for Regularizers
 *
 * <p>Regularizers allow you to apply penalties on layer parameters or layer activity during
 * optimization. These penalties are summed into the loss function that the network optimizes.
 */
public abstract class AbstractRegularizer implements Regularizer {

  public static final float DEFAULT_REGULARIZATION_PENALTY = 0.01f;

  private final String name;

  /** Creates a AbstractRegularizer, using {@link Class#getSimpleName()} for the name */
  protected AbstractRegularizer() {
    this(null);
  }
  /**
   * Creates a AbstractRegularizer
   *
   * @param name the name of this regularizer, if null use {@link Class#getSimpleName()} for the
   *     name.
   */
  protected AbstractRegularizer(String name) {
    this.name = name == null ? this.getClass().getSimpleName() : name;
  }

  /**
   * Returns this AbstractRegularizer as a AbstractLoss This is a convenience to use regularize a
   * loss. Only sampleWeights are applied to the regularizer.
   *
   * @return this AbstractRegularizer as a AbstractLoss
   */
  public AbstractLoss asLoss() {
    return new RegularizerLoss(this);
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
