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

/**
 * Sigmoid activation. <code>sigmoid(x) = 1 / (1 + exp(-x))</code>.
 *
 * <p>Applies the sigmoid activation function. For small values (&lt;-5), <code>sigmoid</code>
 * returns a value close to zero, and for large values (&gt;5) the result of the function gets close
 * to 1.
 *
 * <p>Sigmoid is equivalent to a 2-element Softmax, where the second element is assumed to be zero.
 * The sigmoid function always returns a value between 0 and 1.
 *
 * <p>For example:
 *
 * <pre>
 *     Operand&lt;TFloat32&gt; input = tf.constant(
 *              new float[] {-20f, -1.0f, 0.0f, 1.0f, 20f});
 *     Sigmoid&lt;TFloat32&gt; sigmoid = new Sigmoid&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; result = sigmoid.call(input);
 *     // result is [2.0611537e-09f, 2.6894143e-01f,
 *     //                 5.0000000e-01f,7.3105860e-01f, 1.f]
 * </pre>
 *
 * @param <T> the data type of the activation
 */
public class Sigmoid<T extends TFloating> extends Activation<T> {

  /**
   * Creates a Sigmoid activation.
   *
   * @param tf the TensorFlow Ops
   */
  public Sigmoid(Ops tf) {
    super(tf);
  }

  /**
   * Gets the calculation operation for the activation.
   *
   * @param input the input tensor
   * @return The operand for the activation
   */
  @Override
  public Operand<T> call(Operand<T> input) {
    return tf.math.sigmoid(input);
  }
}
