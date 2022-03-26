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
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;

/**
 * Exponential activation function.
 *
 * <p>For example:
 *
 * <pre>{@code
 *   Operand<TFloat32> input = tf.constant(
 *          new float[] {-3.0f,-1.0f, 0.0f,1.0f,3.0f});
 *   Exponential<TFloat32> exp = new Exponential<>(tf);
 *   Operand<TFloat32> result = exp.call(input);
 *   // result is [0.04978707f,  0.36787945f,  1.f,  2.7182817f, 20.085537f]
 * }
 * }</pre>
 */
public class Exponential extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "exponential";

  private static final Set<String> allowedConfigKeys = Collections.singleton(NAME_KEY);

  /** Creates an Exponential activation. */
  public Exponential() {
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
  public Exponential(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
  }

  /**
   * Computes the Exponential activation function.
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   *      Operand<TFloat32> input = ...;
   *      Operand<TFloat32> result = Exponential.exponential(tf, input);
   * }
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the exponential activation: {@code exp(x)}.
   */
  public static <T extends TFloating> Operand<T> exponential(Ops tf, Operand<T> input) {
    return tf.math.exp(input);
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    return super.getDefaultConfig(NAME);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return tf.math.exp(input);
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }
}
