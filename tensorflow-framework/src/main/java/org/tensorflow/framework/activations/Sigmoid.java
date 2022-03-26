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
 * Sigmoid activation. {@code sigmoid(x) = 1 / (1 + exp(-x))}.
 *
 * <p>Applies the sigmoid activation function. For small values {@code (<-5)}, {@code sigmoid}
 * returns a value close to zero, and for large values (>5) the result of the function gets close to
 * 1.
 *
 * <p>Sigmoid is equivalent to a 2-element Softmax, where the second element is assumed to be zero.
 * The sigmoid function always returns a value between 0 and 1.
 *
 * <p>For example:
 *
 * <pre>{@code
 * Operand<TFloat32> input = tf.constant(
 *          new float[] {-20f, -1.0f, 0.0f, 1.0f, 20f});
 * Sigmoid<TFloat32> sigmoid = new Sigmoid<>(tf);
 * Operand<TFloat32> result = sigmoid.call(input);
 * // result is [2.0611537e-09f, 2.6894143e-01f,
 * //                 5.0000000e-01f,7.3105860e-01f, 1.f]
 * }</pre>
 */
public class Sigmoid extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "sigmoid";

  private static final Set<String> allowedConfigKeys = Collections.singleton(NAME_KEY);
  /** Creates a Sigmoid activation. */
  public Sigmoid() {
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
  public Sigmoid(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
  }

  /**
   * Applies the Sigmoid activation function, {@code sigmoid(x) = 1 / (1 + exp(-x))}.
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = Sigmoid.sigmoid(tf, input);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the input, unmodified.
   */
  public static <T extends TNumber> Operand<T> sigmoid(Ops tf, Operand<T> input) {
    return tf.math.sigmoid(input);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    return sigmoid(tf, input);
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    return getDefaultConfig(getName());
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return NAME;
  }
}
