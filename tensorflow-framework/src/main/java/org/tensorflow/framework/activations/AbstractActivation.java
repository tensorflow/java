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
import org.tensorflow.op.Ops;

/** Abstract base class for Activations */
public abstract class AbstractActivation implements Activation {

  /** The TensorFlow Ops */
  protected Ops tf;

  /** Creates the abstract class for an AbstractActivation */
  protected AbstractActivation() {}

  /**
   * Gets a configuration map, this default implementation returns a singleton Map, with {@code
   * "name"} as the key, and the @code name} parameter as its value;
   *
   * @param name the name of the Activation as known by TensorFlow engine.
   * @return the configuration map
   */
  protected Map<String, Object> getConfig(String name) {
    return Collections.singletonMap("name", name);
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
}
