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
 * Softsign activation function, <code>softsign(x) = x / (abs(x) + 1)</code>.
 *
 * <p>Example Usage:
 *
 * <pre>
 *     Operand&lt;TFloat32&gt; input = tf.constant(
 *              new float[] {-1.0f, 0.0f, 1.0f});
 *     Softsign&lt;TFloat32&gt; softsign = new Softsign&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; result = softsign.call(input);
 *     // result is [-0.5f, 0.f, 0.5f]
 * </pre>
 */
public class Softsign extends Activation<TFloating> {

  /**
   * Creates a Softsign activation.
   *
   * @param tf the TensorFlow Ops
   */
  public Softsign(Ops tf) {
    super(tf);
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TFloating> Operand<U> call(Operand<U> input) {
    return tf.nn.softsign(input);
  }
}
