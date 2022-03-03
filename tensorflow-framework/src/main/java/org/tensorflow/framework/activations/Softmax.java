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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.ReduceMax;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Softmax converts a real vector to a vector of categorical probabilities.
 *
 * <p>The elements of the output vector are in range (0, 1) and sum to 1.
 *
 * <p>Each vector is handled independently. The {@code axis} argument sets which axis of the input
 * the function is applied along.
 *
 * <p>Softmax is often used as the activation for the last layer of a classification network because
 * the result could be interpreted as a probability distribution.
 *
 * <p>The softmax of each vector x is computed as: {@code exp(x) / tf.sum(exp(x))}.
 *
 * <p>The input values in are the log-odds of the resulting probability.
 */
public class Softmax extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "softmax";

  private static final Set<String> allowedConfigKeys =
      new HashSet<>(Arrays.asList(Softmax.NAME_KEY, "axis"));
  private static final int AXIS_DEFAULT = -1;

  private final int axis;

  /**
   * Creates a softmax activation where the default axis is {@link #AXIS_DEFAULT} which indicates
   * the last dimension.
   */
  public Softmax() {
    this(AXIS_DEFAULT);
  }

  /**
   * Creates a Softmax activation
   *
   * @param axis The dimension softmax would be performed on.
   */
  public Softmax(int axis) {
    super();
    this.axis = axis;
  }

  /**
   * Creates a Softmax activation from a config map.
   *
   * @param config the configuration map, if the map contains an entry for {@code axis} that value
   *     is used, otherwise {@link #AXIS_DEFAULT} is used.
   * @throws IllegalArgumentException if the configuration contains unsupported keys for this class
   *     or if the value for the name key does not match the name for the Activation
   */
  public Softmax(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
    this.axis = (Integer) config.getOrDefault("axis", AXIS_DEFAULT);
  }

  /**
   * Converts a vector of values to a probability distribution along the last axis.
   *
   * <p>The elements of the output vector are in range (0, 1) and sum to 1.
   *
   * <p>Each vector is handled independently. The {@code axis} argument sets which axis of the input
   * the function is applied along.
   *
   * <p>Softmax is often used as the activation for the last layer of a classification network
   * because the result could be interpreted as a probability distribution.
   *
   * <p>The softmax of each vector x is computed as {@code exp(x) / tf.reduce_sum(exp(x))}. The
   * input values in are the log-odds of the resulting probability.
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> teh data type of the input
   * @return the output of softmax transformation (all values are non-negative and sum to 1).
   */
  public static <T extends TNumber> Operand<T> softmax(Ops tf, Operand<T> input) {
    return softmax(tf, input, tf.constant(AXIS_DEFAULT));
  }

  /**
   * Converts a vector of values to a probability distribution.
   *
   * <p>The elements of the output vector are in range (0, 1) and sum to 1.
   *
   * <p>Each vector is handled independently. The {@code axis} argument sets which axis of the input
   * the function is applied along.
   *
   * <p>Softmax is often used as the activation for the last layer of a classification network
   * because the result could be interpreted as a probability distribution.
   *
   * <p>The softmax of each vector x is computed as {@code exp(x) / tf.reduce_sum(exp(x))}. The
   * input values in are the log-odds of the resulting probability.
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param axis Integer, axis along which the softmax normalization is applied.
   * @param <T> teh data type of the input
   * @return the output of softmax transformation (all values are non-negative and sum to 1).
   */
  public static <T extends TNumber> Operand<T> softmax(
      Ops tf, Operand<T> input, Operand<TInt32> axis) {
    Shape shape = input.shape();
    int numDimensions = shape.numDimensions();
    if (numDimensions == 2) {
      return tf.nn.softmax(input);
    } else {
      Operand<T> e =
          tf.math.exp(tf.math.sub(input, tf.reduceMax(input, axis, ReduceMax.keepDims(true))));
      Operand<T> s = tf.reduceSum(e, axis, ReduceSum.keepDims(true));
      return tf.math.div(e, s);
    }
  }

  /**
   * Gets a configuration map with entries
   *
   * <ul>
   *   <li>{@code axis} and value set with {@link #axis}.
   * </ul>
   *
   * @return config the configuration map
   */
  @Override
  public Map<String, Object> getConfig() {
    Map<String, Object> config = new HashMap<>();
    config.put("name", NAME);
    config.put("axis", axis);
    return config;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return softmax(tf, input, tf.constant(axis));
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }

  /**
   * Gets the axis along which the softmax normalization is applied.
   *
   * @return the axis along which the softmax normalization is applied.
   */
  public int getAxis() {
    return axis;
  }
}
