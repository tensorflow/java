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

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Scaled Exponential Linear Unit (SELU).
 *
 * <p>The Scaled Exponential Linear Unit (SELU) activation function is defined as:
 *
 * <ul>
 *   <li>{@code if x > 0: return scale * x}
 *   <li>{@code if x < 0: return scale * alpha * (exp(x) - 1)}
 * </ul>
 *
 * <p>where {@code alpha} and {@code scale} are pre-defined constants ({@code alpha=1.67326324} and
 * {@code scale=1.05070098}).
 *
 * <p>Basically, the SELU activation function multiplies {@code scale} (> 1) with the output of the
 * elu function to ensure a slope larger than one for positive inputs.
 *
 * <p>The values of {@code alpha} and {@code scale} are chosen so that the mean and variance of the
 * inputs are preserved between two consecutive layers as long as the weights are initialized
 * correctly (see {@link org.tensorflow.framework.initializers.LeCun} with Normal Distribution) and
 * the number of input units is "large enough"
 *
 * <p><b>Notes: </b> To be used together with the {@link
 * org.tensorflow.framework.initializers.LeCun} initializer with Normal Distribution.
 *
 * @see <a href="https://arxiv.org/abs/1706.02515">Klambauer et al., 2017</a>
 */
public class SELU extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "selu";

  private static final Set<String> allowedConfigKeys = Collections.singleton(NAME_KEY);

  /** Creates a Scaled Exponential Linear Unit (SELU) activation. */
  public SELU() {
    super();
  }

  /**
   * Creates a new Exponential from a configuration Map
   *
   * @param config the configuration map, this class does not use any of the entries in the
   *     configuration map
   * @throws IllegalArgumentException if the configuration contains unsupported keys for this class
   *     or if the value for the name key does not match the name for the Activation
   */
  public SELU(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
  }

  /**
   * Applies Scaled Exponential Linear Unit (SELU) activation function
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = SELU.selu(tf, input);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the input, unmodified.
   */
  public static <T extends TNumber> Operand<T> selu(Ops tf, Operand<T> input) {
    return tf.nn.selu(input);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return selu(tf, input);
  }

  /** {@inheritDoc} */
  public Map<String, Object> getConfig() {
    return getDefaultConfig(getName());
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }
}
