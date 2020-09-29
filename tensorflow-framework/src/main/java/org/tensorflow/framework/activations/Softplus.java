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
 * Softplus activation function, <code>softplus(x) = log(exp(x) + 1)</code>.
 *
 * <p>Example Usage:
 *
 * <pre>
 *     Operand&lt;TFloat32&gt; input = tf.constant(
 *              new float[] {-20f, -1.0f, 0.0f, 1.0f, 20f});
 *     Softplus&lt;TFloat32&gt; softplus = new Softplus&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; result = softplus.call(input);
 *     // result is [2.0611537e-09f, 3.1326166e-01f, 6.9314718e-01f,
 *     //                 1.3132616e+00f, 2.0000000e+01f]
 * </pre>
 */
public class Softplus<T extends TNumber> extends Activation<T> {

  /** Creates a Softplus activation function. */
  public Softplus() {
    super();
  }

  /**
   * Creates a Softplus activation function.
   *
   * @param tf the TensorFlow Ops
   */
  public Softplus(Ops tf) {
    super(tf);
  }

  /**
   * Gets the calculation operation for the activation.
   *
   * @param input the input tensor
   * @return The operand for the activation
   * @throws IllegalArgumentException if the data type is not a floating data type.
   */
  @Override
  public Operand<T> call(Operand<T> input) {
    if (!input.asTensor().dataType().isFloating()) {
      throw new IllegalArgumentException(
          "Must be a Floating Point DataType: " + input.asOutput().dataType());
    }
    return tf.math.softplus(input);
  }
}
