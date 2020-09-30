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
 * Exponential activation function.
 *
 * <p>For example:
 *
 * <pre>
 *   Operand&lt;TFloat32&gt; input = tf.constant(
 *          new float[] {-3.0f,-1.0f, 0.0f,1.0f,3.0f});
 *   Exponential&lt;TFloat32&gt; exp = new Exponential&lt;&gt;(tf);
 *   Operand&lt;TFloat32&gt; result = exp.call(input);
 *   // result is [0.04978707f,  0.36787945f,  1.f,  2.7182817f, 20.085537f]
 * </pre>
 *
 * @param <T> the data type of the activation
 */
public class Exponential<T extends TNumber> extends Activation<T> {

  /** Creates an Exponential activation. */
  public Exponential() {
    super();
  }

  /**
   * Creates an Exponential activation.
   *
   * @param tf the TensorFlow Ops
   */
  public Exponential(Ops tf) {
    super(tf);
  }

  /**
   * Calculates the Exponential activation.
   *
   * @param input the input tensor
   * @return an Operand for the exponential activation: <code>exp(x)</code>.
   * @throws IllegalArgumentException if the input is not a floating type
   */
  @Override
  public Operand<T> call(Operand<T> input) {
    if (!input.asOutput().dataType().isFloating()) {
      throw new IllegalArgumentException(
          "Must be a Floating Point DataType: " + input.asOutput().dataType());
    }
    return tf.math.exp(input);
  }
}
