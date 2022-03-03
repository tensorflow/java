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

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Hard sigmoid activation.
 *
 * <p>A faster approximation of the sigmoid activation.
 *
 * <p>Defined as:
 *
 * <ul>
 *   <li>{@code if x < -2.5: return 0}
 *   <li>{@code if x > 2.5: return 1}
 *   <li>{@code if -2.5 <= x <= 2.5: return 0.2 * x + 0.5}
 * </ul>
 *
 * <p>For example:
 *
 * <pre>{@code
 * Operand<TFloat32> input = tf.constant(
 *          new float[] {-3.0f,-1.0f, 0.0f,1.0f,3.0f});
 * HardSigmoid<TFloat32> hardSigmoid = new HardSigmoid<>(tf);
 * Operand<TFloat32> result = hardSigmoid.call(input);
 * // result is [0.f , 0.3f, 0.5f, 0.7f, 1.f]
 * }</pre>
 */
public class HardSigmoid extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "hard_sigmoid";

  private static final Set<String> allowedConfigKeys = Collections.singleton(NAME_KEY);

  /** Creates Hard sigmoid activation. */
  public HardSigmoid() {
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
  public HardSigmoid(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
  }

  /**
   * Computes the hard sigmoid activation function.
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = HardSigmoid.hardSigmoid(tf, input);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the exponential activation: {@code exp(x)}.
   */
  public static <T extends TNumber> Operand<T> hardSigmoid(Ops tf, Operand<T> input) {
    Class<T> inputType = input.type();
    Operand<T> point2 = cast(tf, tf.constant(0.2), inputType);
    Operand<T> point5 = cast(tf, tf.constant(0.5), inputType);

    Operand<T> x = tf.math.add(tf.math.mul(input, point2), point5);
    return tf.clipByValue(
        x, cast(tf, tf.constant(0), inputType), cast(tf, tf.constant(1), inputType));
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    return getDefaultConfig(getName());
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return hardSigmoid(tf, input);
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }
}
