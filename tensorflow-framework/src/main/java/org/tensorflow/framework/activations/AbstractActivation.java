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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.tensorflow.op.Ops;

/** Abstract base class for Activations */
public abstract class AbstractActivation implements Activation {
  protected static final String NAME_KEY = "name";

  /** The TensorFlow Ops */
  protected Ops tf;

  /** Creates the abstract class for an AbstractActivation */
  protected AbstractActivation() {}

  /**
   * Gets a configuration map, this default implementation returns a singleton Map, with {@link
   * #NAME_KEY} as the key, and the {@code name} parameter as its value;
   *
   * @param name the name of the Activation as known by TensorFlow engine.
   * @return the configuration map
   */
  protected Map<String, Object> getDefaultConfig(String name) {
    return Collections.singletonMap(NAME_KEY, name);
  }

  /**
   * Gets a configuration map
   *
   * @return the configuration map
   */
  public abstract Map<String, Object> getConfig();

  /**
   * Get the name of the activation as known by the TensorFlow Engine
   *
   * @return the name of the activation as known by the TensorFlow Engine
   */
  public abstract String getName();

  /**
   * Gets the TensorFlow Ops
   *
   * @return the TensorFlow Ops
   */
  protected Ops getTF() {
    return this.tf;
  }

  /**
   * Sets the TensorFlow Ops
   *
   * @param tf the TensorFlow Ops
   */
  protected void setTF(Ops tf) {
    this.tf = tf;
  }

  /**
   * Verifies that any key in keysToCheck is also in the allowedKeys set.
   *
   * @param keysToCheck the set with keys to check
   * @param allowedKeys the set to check against.
   * @throws IllegalArgumentException if there is an entry in set1 that is not in set 2.
   */
  protected void checkConfigKeys(Set<String> keysToCheck, Set<String> allowedKeys) {
    List<String> mismatch =
        keysToCheck.stream().filter(e -> !allowedKeys.contains(e)).collect(Collectors.toList());
    if (!mismatch.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("Activation: Illegal Configuration keys: %s", mismatch));
    }
  }

  /**
   * Verifies that the configuration is for the same Activation class.
   *
   * @param config the configuration
   * @throws IllegalArgumentException if the value for the name key does not match the name for the
   *     Activation
   */
  protected void checkClassName(Map<String, Object> config) {
    if (!config.get(NAME_KEY).equals(getName())) {
      throw new IllegalArgumentException(
          String.format(
              "Configuration name: %s, does not match this class: %s",
              config.get(NAME_KEY), getName()));
    }
  }
}
