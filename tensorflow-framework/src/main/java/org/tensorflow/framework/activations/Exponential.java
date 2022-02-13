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
 * Exponential activation function.
 *
 * <p>For example:
 *
 * <pre>
 *   Operand&lt;TFloat32&gt; input = tf.constant(
 *          new float[] {-3.0f,-1.0f, 0.0f,1.0f,3.0f});
 *   Exponential&lt;TFloat32&gt; exp = new Exponential&lt;&gt;(tf);
 *   Operand&lt;TFloat32&gt; result = exp.call(input);
 *   // result is [0.04978707f,  0.36787945f,  1.f,  2.7182817f, 20.085537f]
 * </pre>
 */
public class Exponential extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "exponential";

  /** Creates an Exponential activation. */
  public Exponential() {
    super();
  }

  /**
   * Creates a new Exponential from a configuration Map
   *
   * @param config the configuration map, this class does not use any of the entries in the
   *     configuration map
   */
  public Exponential(Map<String, Object> config) {
    this();
  }

  /**
   * Computes the Exponential activation function.
   *
   * <p>Example Usage:
   *
   * <pre>
   *      Operand&lt;TFloat32&gt; input = &#46;&#46;&#46;;
   *      Operand&lt;TFloat32&gt; result = Exponential.exponential(tf, input);
   * </pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the exponential activation: {@code exp(x)}.
   */
  public static <T extends TNumber> Operand<T> exponential(Ops tf, Operand<T> input) {
    return tf.math.exp(input);
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    return super.getConfig(NAME);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return exponential(tf, input);
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }
}
