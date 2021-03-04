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
 * Hyperbolic tangent activation function.
 *
 * <p>For example:
 *
 * <pre>
 *     Operand&lt;TFloat32&gt; input = tf.constant(new float[]
 *                                        {-3.0f,-1.0f, 0.0f, 1.0f, 3.0f});
 *     Tanh&lt;TFloat32&gt; tanh = new Tanh&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; result = tanh.call(input);
 *     // result = [-0.9950547f, -0.7615942f,  0.f,  0.7615942f,  0.9950547f]
 * </pre>
 */
// TFloating
public class Tanh extends Activation {

  /**
   * Creates a Hyperbolic tangent activation.
   *
   * @param tf the TensorFlow Ops
   */
  public Tanh(Ops tf) {
    super(tf);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Operand<T> input) {
    return tf.math.tanh(input);
  }
}
