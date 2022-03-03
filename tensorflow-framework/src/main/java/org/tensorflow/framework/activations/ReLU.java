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
import org.tensorflow.op.math.Greater;
import org.tensorflow.op.nn.LeakyRelu;
import org.tensorflow.types.family.TNumber;

/**
 * Rectified Linear Unit(ReLU) activation.
 *
 * <p>With default values, this returns the standard ReLU activation: {@code max(x, 0)}, the
 * element-wise maximum of 0 and the input tensor.
 *
 * <p>Modifying default parameters allows you to use non-zero thresholds, change the max value of
 * the activation, and to use a non-zero multiple of the input for values below the threshold.
 *
 * <p>For example:
 *
 * <pre>{@code
 * Operand<TFloat32> input = tf.constant(
 *          new float[] {-10f, -5f, 0.0f, 5f, 10f});
 *
 * // With default parameters
 * ReLU<TFloat32> relu = new ReLU<>(tf);
 * Operand<TFloat32> result = relu.call(input);
 * // result is [0.f,  0.f,  0.f,  5.f, 10.f]
 *
 * // With alpha = 0.5
 * relu = new ReLU<>(tf, 0.5f, ReLU.MAX_VALUE_DEFAULT, ReLU.THRESHOLD_DEFAULT);
 * result = relu.call(input);
 * // result is [-5.f , -2.5f,  0.f ,  5.f , 10.f]
 *
 * // With maxValue = 5
 * relu = new ReLU<>(tf, ReLU.ALPHA_DEFAULT, 5f, ReLU.THRESHOLD_DEFAULT);
 * result = relu.call(input);
 * // result is [0.f, 0.f, 0.f, 5.f, 5.f]
 *
 * // With threshold = 5
 * relu = new ReLU<>(tf, ReLU.ALPHA_DEFAULT, ReLU.MAX_VALUE_DEFAULT, 5f);
 * result = relu.call(input);
 * // result is [-0.f, -0.f,  0.f,  0.f, 10.f]
 * }</pre>
 */
public class ReLU extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "relu";

  public static final float ALPHA_DEFAULT = 0.0f;
  public static final float MAX_VALUE_DEFAULT = Float.NaN;
  public static final float THRESHOLD_DEFAULT = 0.0f;
  private static final Set<String> allowedConfigKeys =
      new HashSet<>(Arrays.asList(ReLU.NAME_KEY, "alpha", "max_value", "threshold"));
  private final float alpha;
  private final float maxValue;
  private final float threshold;

  /**
   * Creates a new ReLU with alpha={@link #ALPHA_DEFAULT}, maxValue={@link #MAX_VALUE_DEFAULT},
   * threshold={@link #THRESHOLD_DEFAULT},
   */
  public ReLU() {
    this(ALPHA_DEFAULT, MAX_VALUE_DEFAULT, THRESHOLD_DEFAULT);
  }

  /**
   * Creates a new ReLU
   *
   * @param alpha governs the slope for values lower than the threshold.
   * @param maxValue sets the saturation threshold (the largest value the function will return).
   * @param threshold the threshold value of the activation function below which values will be
   *     damped or set to zero.
   */
  public ReLU(float alpha, float maxValue, float threshold) {
    super();
    this.alpha = alpha;
    this.maxValue = maxValue;
    this.threshold = threshold;
  }

  /**
   * Creates a ReLU activation from a config map.
   *
   * @param config the configuration map,
   *     <ul>
   *       <li>if the map contains an entry for {@code alpha} that value is used, otherwise {@link
   *           #ALPHA_DEFAULT} is used.
   *       <li>if the map contains an entry for {@code max_value} that value is used, otherwise
   *           {@link #MAX_VALUE_DEFAULT} is used.
   *       <li>if the map contains an entry for {@code threshold} that value is used, otherwise
   *           {@link #THRESHOLD_DEFAULT} is used.
   *     </ul>
   *
   * @throws IllegalArgumentException if the configuration contains unsupported keys for this class
   *     or if the value for the name key does not match the name for the Activation
   */
  public ReLU(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
    this.alpha = ((Number) config.getOrDefault("alpha", ALPHA_DEFAULT)).floatValue();
    this.maxValue = ((Number) config.getOrDefault("max_value", MAX_VALUE_DEFAULT)).floatValue();
    this.threshold = ((Number) config.getOrDefault("threshold", THRESHOLD_DEFAULT)).floatValue();
  }

  /**
   * Applies the rectified linear unit activation function with default values.
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = ReLU.relu(tf, input);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the input, unmodified.
   */
  public static <T extends TNumber> Operand<T> relu(Ops tf, Operand<T> input) {
    return relu(tf, input, ALPHA_DEFAULT, MAX_VALUE_DEFAULT, THRESHOLD_DEFAULT);
  }

  /**
   * Applies the rectified linear unit activation function.
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = ReLU.relu(tf, input);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param alpha governs the slope for values lower than the threshold.
   * @param maxValue sets the saturation threshold (the largest value the function will return).
   * @param threshold the threshold value of the activation function below which values will be
   *     damped or set to zero.
   * @param <T> the data type for the input
   * @return the input, unmodified.
   */
  public static <T extends TNumber> Operand<T> relu(
      Ops tf, Operand<T> input, float alpha, float maxValue, float threshold) {
    Class<T> inputType = input.type();

    boolean clipMax = !Float.isNaN(maxValue);
    Operand<T> negativePart = null;
    if (alpha != 0) {
      if (Float.isNaN(maxValue) && threshold == 0) {
        return tf.nn.leakyRelu(input, LeakyRelu.alpha(alpha));
      }
      if (threshold != 0) {
        negativePart =
            tf.nn.relu(
                tf.math.add(tf.math.neg(input), cast(tf, tf.constant(threshold), inputType)));
      } else {
        negativePart = tf.nn.relu(tf.math.neg(input));
      }
    }

    Operand<T> lInput;
    if (threshold != 0) {
      // computes input for input > threshold else 0
      Greater greater = tf.math.greater(input, cast(tf, tf.constant(threshold), inputType));
      lInput = tf.math.mul(input, cast(tf, greater, inputType));
    } else if (maxValue == 6) {
      // if no threshold, then can use nn.relu6 native TF op for performance
      lInput = tf.nn.relu6(input);
      clipMax = false;
    } else {
      lInput = tf.nn.relu(input);
    }
    if (clipMax) {
      Operand<T> lmaxValue = cast(tf, tf.constant(maxValue), inputType);
      Operand<T> zero = cast(tf, tf.constant(0), inputType);
      lInput = tf.clipByValue(lInput, zero, lmaxValue);
    }

    if (alpha != 0.) {
      lInput =
          tf.math.sub(lInput, tf.math.mul(cast(tf, tf.constant(alpha), inputType), negativePart));
    }
    return lInput;
  }

  /**
   * Gets a configuration map with entries
   *
   * <ul>
   *   <li>{@code alpha} and value set with {@link #alpha}.
   *   <li>{@code max_value} and value set with {@link #maxValue}.
   *   <li>{@code threshold} and value set with {@link #threshold}.
   * </ul>
   *
   * @return config the configuration map
   */
  @Override
  public Map<String, Object> getConfig() {
    Map<String, Object> config = new HashMap<>();
    config.put("name", NAME);
    config.put("alpha", alpha);
    config.put("max_value", maxValue);
    config.put("threshold", threshold);
    return config;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return relu(tf, input, alpha, maxValue, threshold);
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }

  /**
   * Gets the value that governs the slope for values lower than the threshold.
   *
   * @return the value that governs the slope for values lower than the threshold.
   */
  public float getAlpha() {
    return alpha;
  }

  /**
   * Gets the saturation threshold (the largest value the function will return).
   *
   * @return the saturation threshold (the largest value the function will return). public float
   *     getMaxValue() { return maxValue; }
   *     <p>/** Gets the threshold value of the activation function below which values will be
   *     damped or set to zero.
   */
  public float getThreshold() {
    return threshold;
  }

  /**
   * Gets the saturation threshold (the largest value the function will return).
   *
   * @return the saturation threshold (the largest value the function will return).
   */
  public float getMaxValue() {
    return maxValue;
  }
}
