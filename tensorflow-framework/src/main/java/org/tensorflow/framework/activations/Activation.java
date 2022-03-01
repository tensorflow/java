/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
import java.util.function.Function;
import java.util.function.Supplier;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** Interface for Activations */
@FunctionalInterface
public interface Activation {

  /**
   * Creates an Activation instance based on the name as known to the TensorFlow engine.
   *
   * @param name the activation name
   * @return the Activation
   * @throws NullPointerException if name is null
   * @throws IllegalArgumentException if the name is not a known ActivationType
   */
  static Activation create(String name) {
    ActivationType type = ActivationType.of(name);
    return type.getInstance();
  }

  /**
   * Creates an Activation getInstance based on a configuration as produced by TensorFlow.
   *
   * @param config a Map object containing the Activation's state. This Map object must contain at
   *     least a {@code name} key.
   *     <pre>{@code
   * "name" : String - this is the TensorFlow Engine's Activation name
   * }</pre>
   *
   * @return the Activation
   * @throws NullPointerException if config is null, or the activation name is missing from the Map.
   * @throws IllegalArgumentException if the name contained in the config map is not a known
   *     ActivationType
   */
  static Activation create(Map<String, Object> config) {
    String activationName = (String) config.get("name");
    ActivationType type = ActivationType.of(activationName);
    return type.getInstance(config);
  }

  /**
   * Gets the calculation operation for the activation.
   *
   * @param tf the TensorFlow Ops
   * @param input the input tensor
   * @return The operand for the activation
   * @param <T> the data type of the input and the result
   */
  <T extends TNumber> Operand<T> call(Ops tf, Operand<T> input);

  /**
   * The Enumerations for creating Activations based an activation name, with either an empty
   * constructor or a constructor that takes a Map object that contains the Activation's state.
   */
  enum ActivationType {
    ELU(ELU::new, ELU::new),
    EXPONENTIAL(Exponential::new, Exponential::new),
    GELU(GELU::new, GELU::new),
    HARD_SIGMOID(HardSigmoid::new, HardSigmoid::new),
    LINEAR(Linear::new, Linear::new),
    RELU(ReLU::new, ReLU::new),
    SELU(SELU::new, SELU::new),
    SIGMOID(Sigmoid::new, Sigmoid::new),
    SOFTMAX(Softmax::new, Softmax::new),
    SOFTPLUS(Softplus::new, Softplus::new),
    SOFTSIGN(Softsign::new, Softsign::new),
    SWISH(Swish::new, Swish::new),
    TANH(Tanh::new, Tanh::new);

    /** The constructor when no Config Map is available. */
    private final Supplier<Activation> emptyCtor;

    /** The constructor to use with a Map object containing the Activation's state */
    private final Function<Map<String, Object>, Activation> configCtor;

    /**
     * Creates an ActivationType
     *
     * @param emptyCtor The constructor when no Config Map is available.
     * @param configCtor The constructor to use with a Map object containing the Activation's state
     */
    ActivationType(
        Supplier<Activation> emptyCtor, Function<Map<String, Object>, Activation> configCtor) {
      this.emptyCtor = emptyCtor;
      this.configCtor = configCtor;
    }

    /**
     * Gets the ActivationType based on the TensorFlow name for the activation
     *
     * <p>NOTE: this is similar to valueOf, but name can be either case, upper or lower. The
     * TensorFlow engine produces names in lowwer case.
     *
     * @param name the TensorFlow name for the activation
     * @return the ActivationType
     */
    public static ActivationType of(final String name) {
      return valueOf(name.toUpperCase());
    }

    /**
     * Gets the activation name as known to the TensorFlow engine.
     *
     * @return the activation name as known to the TensorFlow engine.
     */
    public String getTensorFlowName() {
      return name().toLowerCase();
    }

    /**
     * Gets an Activation Instance
     *
     * @return the new Activation Instance
     */
    public Activation getInstance() {
      return emptyCtor.get();
    }

    /**
     * Gets an Activation Instance
     *
     * @param config a Map object containing the Activation's state
     * @return the new Activation Instance
     */
    public Activation getInstance(Map<String, Object> config) {
      return configCtor.apply(config);
    }
  }
}
