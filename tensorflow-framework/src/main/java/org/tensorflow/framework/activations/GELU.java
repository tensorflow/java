/* Copyright 2022 The TensorFlow Authors. All Rights Reserved.

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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.tensorflow.Operand;
import org.tensorflow.framework.op.FrameworkOps;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Applies the Gaussian error linear unit (GELU) activation function.
 *
 * <p>Gaussian error linear unit (GELU) computes {@code x * P(X <= x)}, where {@code P(X) ~ N(0,
 * 1)}. The (GELU) nonlinearity weights inputs by their value, rather than gates inputs by their
 * sign as in ReLU.
 *
 * <p>For example:
 *
 * <pre>{@code
 * x = tf.constant(new float[] {-3.0f, -1.0f, 0.0f, 1.0f, 3.f});
 * GELU gelu = new GELU();
 * y = gelu.call(tf, x);
 * // output [-0.00404951f, -0.15865529f, 0.f , 0.8413447f , 2.9959507f ]
 *
 * }
 * }</pre>
 */
public class GELU extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "gelu";

  private static final Set<String> allowedConfigKeys =
      new HashSet<>(Arrays.asList(GELU.NAME_KEY, "approximate"));
  private final boolean approximate;

  /** Creates a Gaussian error linear unit (GELU) activation. */
  public GELU() {
    this(false);
  }

  /**
   * Creates a Gaussian error linear unit (GELU) activation.
   *
   * @param approximate whether to enable approximation.
   */
  public GELU(boolean approximate) {
    super();
    this.approximate = approximate;
  }

  /**
   * Creates a GELU activation from a config map.
   *
   * @param config the configuration map, if the map contains an entry for {@code approximate} that
   *     value is used, otherwise false is used.
   * @throws IllegalArgumentException if the configuration contains unsupported keys for this class
   *     or if the value for the name key does not match the name for the Activation
   */
  public GELU(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
    this.approximate = (Boolean) config.getOrDefault("approximate", false);
  }

  /**
   * Applies the Gaussian error linear unit (GELU) activation function with approximate set to
   * false.
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = Gelu.gelu(tf, input);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the exponential activation: {@code exp(x)}.
   */
  public static <T extends TNumber> Operand<T> gelu(Ops tf, Operand<T> input) {
    return gelu(tf, input, false);
  }
  /**
   * Applies the Gaussian error linear unit (GELU) activation function.
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = Gelu.gelu(tf, input, true);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param approximate whether to enable approximation.
   * @param <T> the data type for the input
   * @return the exponential activation: {@code exp(x)}.
   */
  public static <T extends TNumber> Operand<T> gelu(Ops tf, Operand<T> input, boolean approximate) {
    FrameworkOps fops = FrameworkOps.create(tf);
    return fops.nn.gelu(input, approximate);
  }

  /**
   * Gets a configuration map with entries
   *
   * <ul>
   *   <li>{@code approximate} and value set with {@link #approximate}.
   * </ul>
   *
   * @return config the configuration map
   */
  @Override
  public Map<String, Object> getConfig() {
    Map<String, Object> config = new HashMap<>();
    config.put("name", NAME);
    config.put("approximate", approximate);
    return config;
  }
  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return gelu(tf, input, approximate);
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }

  /**
   * Gets the flag whether to enable approximation.
   *
   * @return the flag whether to enable approximation.
   */
  public boolean isApproximate() {
    return approximate;
  }
}
