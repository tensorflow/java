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
 ==============================================================================
 */
package org.tensorflow.op.core;

import java.util.Map;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * Ops for calling {@link ConcreteFunction}.  Even though the C API docs say the name of the Op needs to be the name of
 * the function, they mean the type.
 */
@Operator(name = "call")
public abstract class Function {

  @Endpoint
  public static Map<String, Operand<?>> call(Scope scope, ConcreteFunction function,
      Map<String, Operand<?>> arguments) {
    return function.call(scope, arguments);
  }

  @Endpoint
  public static Operand<?> call(Scope scope, ConcreteFunction function,
      Operand<?> argument) {
    return function.call(scope, argument);
  }

}
