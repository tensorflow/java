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
 * Swish activation function. {@code swish(x) = x * sigmoid(x)}.
 *
 * <p>Swish activation function which returns {@code x*sigmoid(x)}. It is a smooth, non-monotonic
 * function that consistently matches or outperforms {@code ReLU} on deep networks, it is unbounded
 * above and bounded below.
 *
 * <p>Example Usage:
 *
 * <pre>{@code
 * Operand<TFloat32> input = tf.constant(new float[]
 *                                    {-20, -1.0, 0.0, 1.0, 20});
 * Swish<TFloat32> swish = new Swish<>(tf);
 * Operand<TFloat32> result = swish.call(input);
 * // result = [-4.1223075e-08f, -2.6894143e-01f,  0.0000000e+00f,
 * //          7.3105860e-01f,  2.0000000e+01f ]
 *
 * }</pre>
 *
 * @see <a href="https://arxiv.org/abs/1710.05941">Ramachandran et al., 2017</a>
 */
public class Swish extends AbstractActivation {
  /** The activation name as known by TensorFlow */
  public static final String NAME = "swish";

  private static final Set<String> allowedConfigKeys = Collections.singleton(NAME_KEY);

  /**
   * Creates a Swish activation, {@code swish(x) = x * sigmoid(x)}.
   *
   * <p>Swish activation function which returns {@code x*sigmoid(x)}. It is a smooth, non-monotonic
   * function that consistently matches or outperforms ReLU on deep networks, it is unbounded above
   * and bounded below.
   */
  public Swish() {
    super();
  }

  /**
   * Creates a new Swish from a configuration Map
   *
   * @param config the configuration map, this class does not use any of the entries in the
   *     configuration map
   * @throws IllegalArgumentException if the configuration contains unsupported keys for this class
   *     or if the value for the name key does not match the name for the Activation
   */
  public Swish(Map<String, Object> config) {
    checkConfigKeys(config.keySet(), allowedConfigKeys);
    checkClassName(config);
  }

  /**
   * Applies the Swish activation function, {@code swish(x) = x * sigmoid(x)}.
   *
   * <p>Example Usage:
   *
   * <pre>{@code
   * Operand<TFloat32> input = ...;
   * Operand<TFloat32> result = Swish.swish(tf, input);
   * }</pre>
   *
   * @param tf the TensorFlow Ops
   * @param input the input
   * @param <T> the data type for the input
   * @return the Swish activation , @code swish(x) = x * sigmoid(x)}.
   */
  public static <T extends TNumber> Operand<T> swish(Ops tf, Operand<T> input) {
    return tf.math.mul(input, tf.math.sigmoid(input));
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input) {
    // TODO Python Keras returns a "grad", which is an optimization not implemented in Java.
    return swish(tf, input);
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
