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
 * Swish activation function. <code>swish(x) = x * sigmoid(x)</code>.
 *
 * <p>Swish activation function which returns <code>x*sigmoid(x)</code>. It is a smooth,
 * non-monotonic function that consistently matches or outperforms <code>ReLU</code> on deep
 * networks, it is unbounded above and bounded below.
 *
 * <p>Example Usage:
 *
 * <pre>
 *     Operand&lt;TFloat32&gt; input = tf.constant(new float[]
 *                                        {-20, -1.0, 0.0, 1.0, 20});
 *     Swish&lt;TFloat32&gt; swish = new Swish&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; result = swish.call(input);
 *     // result = [-4.1223075e-08f, -2.6894143e-01f,  0.0000000e+00f,
 *     //          7.3105860e-01f,  2.0000000e+01f ]
 *
 * </pre>
 *
 * @param <T> the data type of the activation
 * @see <a href="https://arxiv.org/abs/1710.05941">Ramachandran et al., 2017</a>.
 */
public class Swish<T extends TNumber> extends Activation<T> {

  /**
   * Creates a Swish activation, <code>swish(x) = x * sigmoid(x)</code>.
   *
   * <p>Swish activation function which returns <code>x*sigmoid(x)</code>. It is a smooth,
   * non-monotonic function that consistently matches or outperforms ReLU on deep networks, it is
   * unbounded above and bounded below.
   */
  public Swish() {
    super();
  }

  /**
   * Creates a Swish activation, <code>swish(x) = x * sigmoid(x)</code>.
   *
   * <p>Swish activation function which returns <code>x*sigmoid(x)</code>. It is a smooth,
   * non-monotonic function that consistently matches or outperforms ReLU on deep networks, it is
   * unbounded above and bounded below.
   *
   * @param tf the TensorFlow Ops
   */
  public Swish(Ops tf) {
    super(tf);
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<T> input) {
    if (!input.asOutput().dataType().isFloating()) {
      throw new IllegalArgumentException(
          "Must be a Floating Point DataType: " + input.asOutput().dataType());
    }
    // TODO What about the "grad" return from python tensorflow impl?
    return tf.math.mul(input, tf.math.sigmoid(input));
  }
}
