/* Copyright 2020-2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.op.core;

import java.util.Map;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/** Ops for calling {@link ConcreteFunction}. */
@Operator(name = "call")
public abstract class Function {

  /**
   * Calls the function in an execution environment, adding its graph as a function if it isn't
   * already present. The inputs and outputs are keyed by the names set in the {@code Signature}.
   *
   * @param scope the scope to call the function in
   * @param arguments the arguments to the call
   * @return the outputs of the function
   * @see ConcreteFunction#call(Ops, Map)
   */
  @Endpoint
  public static Map<String, Operand<?>> call(
      Scope scope, ConcreteFunction function, Map<String, Operand<?>> arguments) {
    return function.call(scope, arguments);
  }

  /**
   * Calls the function in an execution environment, adding its graph as a function if it isn't
   * already present. Only works for functions with a single input and output.
   *
   * @param scope the scope to call the function in
   * @param argument the argument to the call
   * @return the output of the function
   * @see ConcreteFunction#call(Ops, Operand)
   */
  @Endpoint
  public static Operand<?> call(Scope scope, ConcreteFunction function, Operand<?> argument) {
    return function.call(scope, argument);
  }
}
