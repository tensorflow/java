/* Copyright 2019-2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow;

import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;
import org.tensorflow.op.WithOps;

/** Defines an environment for creating and executing TensorFlow {@link Operation}s. */
public interface ExecutionEnvironment extends WithOps {

  enum Types {
    GRAPH,
    EAGER
  }

  /**
   * Returns a builder to create a new {@link Operation}.
   *
   * @param type of the Operation (i.e., identifies the computation to be performed)
   * @param name to refer to the created Operation in this environment scope.
   * @return an {@link OperationBuilder} to create an Operation when {@link
   *     OperationBuilder#build()} is invoked. If {@link OperationBuilder#build()} is not invoked,
   *     then some resources may leak.
   */
  OperationBuilder opBuilder(String type, String name);

  /**
   * Attach the function and its dependencies to this execution environment, allowing it to be
   * called.
   *
   * <p>Done automatically in the {@link org.tensorflow.op.Ops#call(ConcreteFunction,
   * java.util.Map)} ops.
   */
  void attachFunction(ConcreteFunction function);

  /**
   * Returns true if the given operation is valid in this execution environment.
   *
   * @param opType The op to check.
   * @return Whether the given operation is valid in this execution environment.
   */
  default boolean isOpEnabled(String opType) {
    return true;
  }

  /**
   * Checks that {@code input} is valid to use as an input in this execution environment. Throws
   * {@link IllegalArgumentException} if not.
   *
   * @param input The op to check
   * @throws IllegalArgumentException if input can't be used as an input in this execution
   *     environment.
   */
  void checkInput(Op input);

  /**
   * Get the type of this environment (from the `Environments` enumeration.
   *
   * @return An `Environments` value indicating the type of execution environment.
   */
  Types environmentType();

  default boolean isEager() {
    return environmentType() == Types.EAGER;
  }

  default boolean isGraph() {
    return environmentType() == Types.GRAPH;
  }

  /**
   * Get the top level scope for this execution environment. Is cached, which is necessary to
   * prevent name collisions.
   */
  Scope baseScope();

  @Override
  default Ops tf() {
    return Ops.create(this);
  }
}
