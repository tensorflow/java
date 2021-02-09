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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.DefinedFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * Ops for calling {@link DefinedFunction}.  Even though the C API docs say the name of the Op needs to be the name of
 * the function, they mean the type.
 */
@Operator(name = "call")
public abstract class Function {

  @Endpoint(name = "call")
  public static List<Operand<?>> call(Scope scope, DefinedFunction function, List<Operand<?>> arguments) {
    scope.env().attachFunction(function);
    String name = function.getName();

    if (function.getNumInputs() != arguments.size()) {
      throw new IllegalArgumentException(
          "Expected " + function.getNumInputs() + " for function " + name + " but " + arguments.size()
              + " were passed");
    }

    OperationBuilder opBuilder = scope.env().opBuilder(name, scope.makeOpName(name));
    for (Operand<?> input : arguments) {
      opBuilder.addInput(input.asOutput());
    }
    opBuilder = scope.apply(opBuilder);
    Operation op = opBuilder.build();

    int numOutputs1 = op.numOutputs();
    List<Operand<?>> outputs = new ArrayList<>(numOutputs1);

    for (int i = 0; i < numOutputs1; i++) {
      outputs.add(op.output(i));
    }

    return outputs;
  }

  @Endpoint(name = "call")
  public static Map<String, Operand<?>> call(Scope scope, ConcreteFunction function,
      Map<String, Operand<?>> arguments) {
    List<Operand<?>> inputList = new ArrayList<>();

    for (String inputName : function.signature().inputNames()) {
      Operand<?> input = arguments.get(inputName);
      if (input == null) {
        throw new IllegalArgumentException(
            "Function " + function.signature().methodName() + " has parameter " + inputName
                + ", but no argument was passed for it.");
      }
      inputList.add(input);
    }

    List<Operand<?>> outputList = call(scope, function.function(), inputList);
    Map<String, Operand<?>> namedOutputs = new LinkedHashMap<>();

    List<String> outputNames = new ArrayList<>(function.signature().outputNames());
    for (int i = 0; i < outputNames.size(); i++) {
      String outputName = outputNames.get(i);

      if (i > outputList.size()) {
        throw new IllegalStateException("Somehow, not all required outputs were returned from the function");
      }

      Operand<?> output = outputList.get(i);
      namedOutputs.put(outputName, output);
    }

    return Collections.unmodifiableMap(namedOutputs);
  }

}
