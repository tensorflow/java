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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * Exponential linear unit.
 *
 * <p>The exponential linear unit (ELU) with {@code alpha > 0} is:
 *
 * <p>{@code x} if {@code x > 0} and {@code alpha * (exp(x) - 1)} if {@code x < 0}.
 *
 * <p>The ELU hyperparameter {@code alpha} controls the value to which an ELU saturates for negative
 * net inputs. ELUs diminish the vanishing gradient effect.
 *
 * <p>ELUs have negative values which pushes the mean of the activations closer to zero. Mean
 * activations that are closer to zero enable faster learning as they bring the gradient closer to
 * the natural gradient. ELUs saturate to a negative value when the argument gets smaller.
 * Saturation means a small derivative which decreases the variation and the information that is
 * propagated to the next layer.
 *
 * <p>Example Usage:
 *
 * <pre>{@code
 * Operand<TFloat32> input = ...;
 * ELU<TFloat32> elu = new ELU<>(tf, 2.0);
 * Operand<TFloat32> result = elu.call(input);
 * }
 * }</pre>
 *
 * @see <a href="https://arxiv.org/abs/1511.07289">Clevert et al, 2016, Fast and Accurate Deep
 *     Network Learning by Exponential Linear Units (ELUs)</a>
 */
public class ELU extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "elu";

  private static final Set<String> allowedConfigKeys =
      new HashSet<>(Arrays.asList(NAME_KEY, "alpha"));
  private static final double ALPHA_DEFAULT = 1.0;

  private final double alpha;

  /** Creates a new ELU with alpha={@link #ALPHA_DEFAULT}. */
  public ELU() {
    this(ALPHA_DEFAULT);
  }

  /**
   * Creates a new ELU
   *
   * @param alpha A scalar, slope of negative section. It controls the value to which an ELU
   *     saturates for negative net inputs.
   */
  public ELU(double alpha) {
    super();
    this.alpha = alpha;
  }

  /**
   * Creates a new ELU from a configuration Map
   *
   * @param config the configuration map, if the map contains an entry for {@code alpha} that value
   *     is used, otherwise {@link #ALPHA_DEFAULT} is used.
   * @throws IllegalArgumentException if the configuration contains unsupported keys for this class
   *     or if the value for the name key does not match the name for the Activation
   */
  public ELU(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
    this.alpha = ((Number) config.getOrDefault("alpha", ALPHA_DEFAULT)).doubleValue();
  }

  /**
   * Computes the Exponential linear unit.
   *
   * <p>The exponential linear unit (ELU) with {@code alpha > 0} is:
   *
   * <pre>{@code x} if {@code x > 0} and {@code alpha * (exp(x) - 1)} if {@code x < 0}.}</pre>
   *
   * <p>The ELU hyperparameter {@code alpha} controls the value to which an ELU saturates for
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
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = ELU.elu(tf, input, 2.0);
   * }
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param alpha scalar, slope of negative section. {@code alpha} controls the value to which an
   *     ELU saturates for negative net inputs.
   * @param <T> the data type for the input
   * @return The exponential linear unit (ELU) activation function: {@code x} if {@code x > 0} and
   *     {@code alpha * (exp(x) - 1)} if {@code x < 0} .
   */
  public static <T extends TNumber> Operand<T> elu(Ops tf, Operand<T> input, double alpha) {
    Operand<T> result = tf.nn.elu(input);
    if (alpha == 1.0) {
      return result;
    } else {
      Class<T> inputType = input.type();
      Operand<T> y = tf.math.mul(result, cast(tf, tf.constant(alpha), inputType));
      Operand<TBool> cond = tf.math.greater(result, cast(tf, tf.constant(0), inputType));
      return tf.select(cond, result, y);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    Map<String, Object> config = new HashMap<>();
    config.put("name", ELU.NAME);
    config.put("alpha", alpha);
    return config;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return elu(tf, input, alpha);
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }

  /**
   * Gets the slope of negative section.
   *
   * @return the slope of negative section.
   */
  public double getAlpha() {
    return alpha;
  }
}
