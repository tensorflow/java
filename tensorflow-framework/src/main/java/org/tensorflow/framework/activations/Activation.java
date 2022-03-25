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
    Activations type = Activations.of(name);
    return type.getInstance();
  }

  /**
   * Creates an Activation getInstance based on a configuration as produced by TensorFlow.
   *
   * @param config a Map object containing the Activation's state. This Map object must contain at
   *     least a {@code name} key.
   *     <pre>{@code
   * "name" : String - this is the TensorFlow Engine's Activation name
   * }
   * }</pre>
   *
   * @return the Activation
   * @throws NullPointerException if config is null, or the activation name is missing from the Map.
   * @throws IllegalArgumentException if the name contained in the config map is not a known
   *     ActivationType
   */
  static Activation create(Map<String, Object> config) {
    String activationName = (String) config.get("name");
    Activations type = Activations.of(activationName);
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
}
