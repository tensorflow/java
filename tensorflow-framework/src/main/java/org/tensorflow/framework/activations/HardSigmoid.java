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
 * Hard sigmoid activation.
 *
 * <p>A faster approximation of the sigmoid activation.
 *
 * <p>Defined as:
 *
 * <ul>
 *   <li><code>if x &lt; -2.5: return 0</code>
 *   <li><code>if x &gt; 2.5: return 1</code>
 *   <li><code>if -2.5 &lt;= x &lt;= 2.5: return 0.2 * x + 0.5</code>
 * </ul>
 *
 * <p>For example:
 *
 * <pre>
 *     Operand&lt;TFloat32&gt; input = tf.constant(
 *              new float[] {-3.0f,-1.0f, 0.0f,1.0f,3.0f});
 *     HardSigmoid&lt;TFloat32&gt; hardSigmoid = new HardSigmoid&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; result = hardSigmoid.call(input);
 *     // result is [0.f , 0.3f, 0.5f, 0.7f, 1.f]
 * </pre>
 *
 * @param <T> the data type of the result
 */
public class HardSigmoid<T extends TFloating> extends Activation<T> {

  /**
   * Creates Hard sigmoid activation.
   *
   * @param tf the TensorFlow Ops
   */
  public HardSigmoid(Ops tf) {
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
    Class<T> inputType = input.type();
    Operand<T> point2 = tf.dtypes.cast(tf.constant(0.2), inputType);
    Operand<T> point5 = tf.dtypes.cast(tf.constant(0.5), inputType);

    Operand<T> x = tf.math.add(tf.math.mul(input, point2), point5);
    return tf.clipByValue(
        x, tf.dtypes.cast(tf.constant(0), inputType), tf.dtypes.cast(tf.constant(1), inputType));
  }
}
