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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.GraphFunction;
import org.tensorflow.NamedGraphFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * Ops for calling {@link GraphFunction}.  Even though the C API docs say the name of the Op needs to be the name of the
 * function, they mean the type.
 */
@Operator
public class Function {

  /**
   * Call {@code function}, adding it to the execution environment if it isn't already present.
   *
   * @param function the function to call
   * @param inputs the inputs to the function
   * @return the outputs of the function
   */
  @Endpoint(name = "callFunction")
  public static List<Operand<?>> callFunction(Scope scope, GraphFunction function, List<Operand<?>> inputs) {
    scope.env().attachFunction(function);
    String name = function.getName();

    if (function.getNumInputs() != inputs.size()) {
      throw new IllegalArgumentException(
          "Expected " + function.getNumInputs() + " for function " + name + " but " + inputs.size() + " were passed");
    }

    OperationBuilder opBuilder = scope.env().opBuilder(name, scope.makeOpName(name));
    for (Operand<?> input : inputs) {
      opBuilder.addInput(input.asOutput());
    }
    opBuilder = scope.apply(opBuilder);
    Operation op = opBuilder.build();

    int numOutputs = op.numOutputs();
    List<Operand<?>> outputs = new ArrayList<>(numOutputs);

    for (int i = 0; i < numOutputs; i++) {
      outputs.add(op.output(i));
    }

    return outputs;
  }

  /**
   * Call {@code function}, adding it to the execution environment if it isn't already present.
   *
   * @param function the function to call
   * @param inputs the inputs to the function
   * @return the outputs of the function
   */
  @Endpoint(name = "callFunction")
  public static List<Operand<?>> callFunction(Scope scope, GraphFunction function, Operand<?>... inputs) {
    return callFunction(scope, function, Arrays.asList(inputs));
  }

  private static Map<String, Operand<?>> resolveOutputs(NamedGraphFunction function, List<Operand<?>> outputs) {
    Map<String, Operand<?>> namedOutputs = new LinkedHashMap<>();

    for (int i = 0; i < function.getOutputNames().size(); i++) {
      String outputName = function.getOutputNames().get(i);

      if (i > outputs.size()) {
        throw new IllegalStateException("Somehow, not all required outputs were returned from the function");
      }

      Operand<?> output = outputs.get(i);
      namedOutputs.put(outputName, output);
    }

    return namedOutputs;
  }

  /**
   * Call {@code function}, adding it to the execution environment if it isn't already present.
   *
   * @param function the function to call
   * @param inputs the inputs to the function
   * @return the outputs of the function
   */
  @Endpoint(name = "callNamedFunction")
  public static Map<String, Operand<?>> callNamedFunction(Scope scope, NamedGraphFunction function,
      List<Operand<?>> inputs) {
    return resolveOutputs(function, callFunction(scope, function, inputs));
  }

  /**
   * Call {@code function}, adding it to the execution environment if it isn't already present.
   *
   * @param function the function to call
   * @param inputs the inputs to the function
   * @return the outputs of the function
   */
  @Endpoint(name = "callNamedFunction")
  public static Map<String, Operand<?>> callNamedFunction(Scope scope, NamedGraphFunction function,
      Operand<?>... inputs) {
    return resolveOutputs(function, callFunction(scope, function, inputs));
  }

  /**
   * Call {@code function}, adding it to the execution environment if it isn't already present.
   *
   * @param function the function to call
   * @param inputs the inputs to the function
   * @return the outputs of the function
   */
  @Endpoint(name = "callNamedFunction")
  public static Map<String, Operand<?>> callNamedFunction(Scope scope, NamedGraphFunction function,
      Map<String, Operand<?>> inputs) {
    List<Operand<?>> inputList = new ArrayList<>();
    for (String inputName : function.getInputNames()) {
      Operand<?> input = inputs.get(inputName);
      if (input == null) {
        throw new IllegalArgumentException(
            "Function " + function.getName() + " has parameter " + inputName + ", but no argument was passed for it.");
      }
      inputList.add(input);
    }

    return resolveOutputs(function, callFunction(scope, function, inputList));
  }

  /**
   * Call {@code function}, adding it to the execution environment if it isn't already present. The inputs and outputs
   * are keyed by the names set in the {@code ConcreteFunction}'s {@code Signature}.
   *
   * @param function the function to call
   * @param inputs the inputs to the function
   * @return the outputs of the function
   */
  @Endpoint(name = "callConcreteFunction")
  public static Map<String, Operand<?>> callConcreteFunction(Scope scope, ConcreteFunction function,
      List<Operand<?>> inputs) {
    return callNamedFunction(scope, function.function(), inputs);
  }

  /**
   * Call {@code function}, adding it to the execution environment if it isn't already present. The inputs and outputs
   * are keyed by the names set in the {@code ConcreteFunction}'s {@code Signature}.
   *
   * @param function the function to call
   * @param inputs the inputs to the function
   * @return the outputs of the function
   */
  @Endpoint(name = "callConcreteFunction")
  public static Map<String, Operand<?>> callConcreteFunction(Scope scope, ConcreteFunction function,
      Operand<?>... inputs) {
    return callNamedFunction(scope, function.function(), inputs);
  }

  /**
   * Call {@code function}, adding it to the execution environment if it isn't already present. The inputs and outputs
   * are keyed by the names set in the {@code ConcreteFunction}'s {@code Signature}.
   *
   * @param function the function to call
   * @param inputs the inputs to the function
   * @return the outputs of the function
   */
  @Endpoint(name = "callConcreteFunction")
  public static Map<String, Operand<?>> callConcreteFunction(Scope scope, ConcreteFunction function,
      Map<String, Operand<?>> inputs) {
    return callNamedFunction(scope, function.function(), inputs);
  }
}
