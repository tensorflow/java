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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** Interface for Activations */
@FunctionalInterface
public interface Activation {
  Map<String, Supplier<Activation>> createMap =
      new HashMap<String, Supplier<Activation>>() {
        {
          put("elu", ELU::new);
          put("exponential", Exponential::new);
          put("gelu", GELU::new);
          put("hard_sigmoid", HardSigmoid::new);
          put("linear", Linear::new);
          put("relu", ReLU::new);
          put("selu", SELU::new);
          put("sigmoid", Sigmoid::new);
          put("softmax", Softmax::new);
          put("softplus", Softplus::new);
          put("softsign", Softsign::new);
          put("swish", Swish::new);
          put("tanh", Tanh::new);
        }
      };

  Map<String, Function<Map<String, Object>, Activation>> createMapConfig =
      new HashMap<String, Function<Map<String, Object>, Activation>>() {
        {
          put("elu", ELU::new);
          put("exponential", Exponential::new);
          put("gelu", GELU::new);
          put("hard_sigmoid", HardSigmoid::new);
          put("linear", Linear::new);
          put("relu", ReLU::new);
          put("selu", SELU::new);
          put("sigmoid", Sigmoid::new);
          put("softmax", Softmax::new);
          put("softplus", Softplus::new);
          put("softsign", Softsign::new);
          put("swish", Swish::new);
          put("tanh", Tanh::new);
        }
      };

  /**
   * Creates an Activation based on a name as known to the TensorFlow engine.
   *
   * @param name the activation name
   * @return the Activation
   */
  static Activation create(String name) {
    if (name == null) {
      return new Linear();
    }
    if (createMap.containsKey(name)) {
      return createMap.get(name).get();
    } else {
      throw new IllegalArgumentException("Unknown conversion for Activation " + name);
    }
  }

  /**
   * Creates an Activation based on a configuration as produced by TensorFLow.
   *
   * @param config the constraint configuration, the config format is
   *     <pre>{@code
   * "name" : String - this is used to locate the class, this is the TensorFlow Engine's class name
   *
   * }</pre>
   *
   * @return the Activation
   */
  static Activation create(Map<String, Object> config) {
    String className = config == null ? null : (String) config.get("name");
    if (className == null) {
      return new Linear();
    }
    Function<Map<String, Object>, Activation> creator = createMapConfig.get(className);

    if (creator != null) {
      return creator.apply(config);
    } else {
      throw new IllegalArgumentException("Unknown conversion for Activation " + className);
    }
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
}
