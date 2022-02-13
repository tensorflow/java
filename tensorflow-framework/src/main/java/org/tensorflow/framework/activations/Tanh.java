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

import java.util.Map;
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
public class Tanh extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "tanh";

  /** Creates a Hyperbolic tangent activation. */
  public Tanh() {
    super();
  }

  /**
   * Creates a new Tanh from a configuration Map
   *
   * @param config the configuration map, this class does not use any of the entries in the
   *     configuration map
   */
  @SuppressWarnings("unused")
  public Tanh(Map<String, Object> config) {
    this();
  }

  /**
   * Applies the Hyperbolic tangent activation function, {@code tanh(x) = sinh(x)/cosh(x) = ((exp(x)
   * - exp(-x))/(exp(x) + exp(-x)))}.
   *
   * <p>Example Usage:
   *
   * <pre>
   *      Operand&lt;TFloat32&gt; input = &#46;&#46;&#46;;
   *      Operand&lt;TFloat32&gt; result = Tanh.tanh(tf, input);
   * </pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the Hyperbolic tangent activation, {@code tanh(x) = sinh(x)/cosh(x) = ((exp(x) -
   *     exp(-x))/(exp(x) + exp(-x)))}.
   */
  public static <T extends TNumber> Operand<T> tanh(Ops tf, Operand<T> input) {
    return tf.math.tanh(input);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return tanh(tf, input);
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    return getConfig(NAME);
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }
}
