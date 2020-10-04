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
 * Scaled Exponential Linear Unit (SELU).
 *
 * <p>The Scaled Exponential Linear Unit (SELU) activation function is defined as:
 *
 * <ul>
 *   <li><code>if x &gt; 0: return scale * x</code>
 *   <li><code>if x &lt; 0: return scale * alpha * (exp(x) - 1)</code>
 * </ul>
 *
 * <p>where <code>alpha</code> and <code>scale</code> are pre-defined constants (<code>
 * alpha=1.67326324</code> and <code>scale=1.05070098</code>).
 *
 * <p>Basically, the SELU activation function multiplies <code>scale</code> (&gt; 1) with the output of
 * the elu function to ensure a slope larger than one for positive inputs.
 *
 * <p>The values of <code>alpha</code> and <code>scale</code> are chosen so that the mean and
 * variance of the inputs are preserved between two consecutive layers as long as the weights are
 * initialized correctly (see {@link org.tensorflow.framework.initializers.LeCun} with Normal
 * Distribution) and the number of input units is "large enough"
 *
 * <p><b>Notes: </b> To be used together with the {@link
 * org.tensorflow.framework.initializers.LeCun} initializer with Normal Distribution.
 *
 * @param <T> the data type of the activation
 * @see <a href="https://arxiv.org/abs/1706.02515">Klambauer et al., 2017</a>
 */
public class SELU<T extends TNumber> extends Activation<T> {

  /**
   * Creates a Scaled Exponential Linear Unit (SELU) activation.
   *
   * @param tf the TensorFlow Ops
   */
  public SELU(Ops tf) {
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
    if (!input.asOutput().dataType().isFloating()) {
      throw new IllegalArgumentException(
          "Must be a Floating Point DataType: " + input.asOutput().dataType());
    }
    return tf.nn.selu(input);
  }
}
