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
package org.tensorflow.framework.activations;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Abstract base class for Activations
 *
 * @param <T> the data type of the activation
 */
public abstract class Activation<T extends TNumber> {

  protected final Ops tf;

  /**
   * Create the abstract class for an Activation
   *
   * @param tf the TensorFlow Ops
   */
  public Activation(Ops tf) {
    this.tf = tf;
  }

  /**
   * Gets the calculation operation for the activation.
   *
   * @param input the input tensor
   * @return The operand for the activation
   */
  public abstract Operand<T> call(Operand<T> input);
}
