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
 * Linear activation function (pass-through).
 *
 * <p>The linear activation returns its input. It is also known as the Identity activation function.
 *
 * <p>For example:
 *
 * <pre>{@code
 * Operand<TFloat32> input = tf.constant(
 *           new float[] {-3.0f,-1.0f, 0.0f,1.0f,3.0f});
 * Linear<TFloat32> linear = new Linear<>(tf);
 * Operand<TFloat32> result = linear.call(input);
 * // result is [-3.0f,-1.0f, 0.0f,1.0f,3.0f]
 * }</pre>
 */
public class Linear extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "linear";

  private static final Set<String> allowedConfigKeys = Collections.singleton(NAME_KEY);

  /** Creates a linear activation. */
  public Linear() {
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
  public Linear(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
  }

  /**
   * Computes the linear activation function (pass-through).
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = Linear.linear(tf, input);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the input, unmodified.
   */
  @SuppressWarnings("unused")
  public static <T extends TNumber> Operand<T> linear(Ops tf, Operand<T> input) {
    return input;
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    return getDefaultConfig(getName());
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> Operand<U> call(Ops tf, Operand<U> input) {
    return linear(tf, input);
  }
  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }
}
