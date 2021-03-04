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
import org.tensorflow.types.family.TFloating;
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
 */
// TFloating
public class Exponential extends Activation {

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
   * @param <T> the data type of the input and result
   * @return an Operand for the exponential activation: <code>exp(x)</code>.
   */
  @Override
  public <T extends TNumber> Operand<T> call(Operand<T> input) {
    if (!TFloating.class.isAssignableFrom(input.type()) ) {
      throw new IllegalArgumentException(
              "Tensor type must be numeric or boolean: " + input.type().getSimpleName());
    }

    return tf.math.exp(input);
  }
}
