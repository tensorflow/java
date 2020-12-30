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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;

/**
 * Exponential linear unit.
 *
 * <p>The exponential linear unit (ELU) with <code>alpha &gt; 0</code> is:
 *
 * <p><code>x</code> if <code>x &gt; 0</code> and <code>alpha * (exp(x) -
 * 1)</code> if <code>x &lt; 0</code>.
 *
 * <p>The ELU hyperparameter <code>alpha</code> controls the value to which an ELU saturates for
 * negative net inputs. ELUs diminish the vanishing gradient effect.
 *
 * <p>ELUs have negative values which pushes the mean of the activations closer to zero. Mean
 * activations that are closer to zero enable faster learning as they bring the gradient closer to
 * the natural gradient. ELUs saturate to a negative value when the argument gets smaller.
 * Saturation means a small derivative which decreases the variation and the information that is
 * propagated to the next layer.
 *
 * <p>Example Usage:
 *
 * <pre>
 *     Operand&lt;TFloat32&gt; input = &#46;&#46;&#46;;
 *     ELU&lt;TFloat32&gt; elu = new ELU&lt;&gt;(tf, 2.0f);
 *     Operand&lt;TFloat32&gt; result = elu.call(input);
 * </pre>
 *
 * @param <T> the data type of the activation
 * @see <a href="https://arxiv.org/abs/1511.07289">Clevert et al, 2016, Fast and Accurate Deep
 *     Network Learning by Exponential Linear Units (ELUs)</a>
 */
public class ELU<T extends TFloating> extends Activation<T> {

  private static final double ALPHA_DEFAULT = 1.0;

  /** A scalar, slope of negative section. */
  private final double alpha;

  /**
   * Creates a new ELU with alpha={@link #ALPHA_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   */
  public ELU(Ops tf) {
    this(tf, ALPHA_DEFAULT);
  }

  /**
   * Creates a new ELU
   *
   * @param tf the TensorFlow Ops
   * @param alpha A scalar, slope of negative section. It controls the value to which an ELU
   *     saturates for negative net inputs.
   */
  public ELU(Ops tf, double alpha) {
    super(tf);
    this.alpha = alpha;
  }

  /**
   * Gets the calculation operation for the activation.
   *
   * @param input the input tensor
   * @return The operand for the activation
   */
  @Override
  public Operand<T> call(Operand<T> input) {

    Operand<T> result = tf.nn.elu(input);
    if (alpha == 1.0) return result;
    else {
      Class<T> inputType = input.type();
      Operand<T> y = tf.math.mul(result, tf.dtypes.cast(tf.constant(alpha), inputType));
      Operand<TBool> cond = tf.math.greater(result, tf.dtypes.cast(tf.constant(0), inputType));
      return tf.select(cond, result, y);
    }
  }
}
