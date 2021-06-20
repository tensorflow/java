/*
  Copyright 2021 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================

 */
package org.tensorflow.op;

import java.util.Arrays;
import org.tensorflow.DeviceSpec;

/**
 * A context that provides a TensorFlow op builder.
 */
public interface WithOps {

  /**
   * Get the op builder for this context.
   */
  Ops tf();

  /**
   * Returns an API that builds operations with the provided name prefix.
   *
   * @see Scope#withSubScope(String)
   */
  default WithOps withSubScope(String childScopeName) {
    return tf().withSubScope(childScopeName);
  }

  /**
   * Returns an API that uses the provided name for an op.
   *
   * @see Scope#withName(String)
   */
  default WithOps withName(String opName) {
    return tf().withName(opName);
  }

  /**
   * Returns an API that places the created operations on the device(s) matching the provided spec.
   *
   * @see Scope#withDevice(DeviceSpec)
   */
  default WithOps withDevice(DeviceSpec deviceSpec) {
    return tf().withDevice(deviceSpec);
  }

  /**
   * Returns an API that adds operations to the graph with the provided control dependencies.
   *
   * @see Scope#withControlDependencies(Iterable)
   */
  default WithOps withControlDependencies(Iterable<Op> controls){
    return tf().withControlDependencies(controls);
  }

  /**
   * Returns an API that adds operations to the graph with the provided control dependencies.
   *
   * @see Scope#withControlDependencies(Iterable)
   */
  default WithOps withControlDependencies(Op... controls){
    return withControlDependencies(Arrays.asList(controls));
  }

}
