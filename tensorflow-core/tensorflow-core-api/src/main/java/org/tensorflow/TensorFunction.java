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
=======================================================================
*/
package org.tensorflow;

import java.util.LinkedHashMap;
import java.util.Map;
import org.tensorflow.Signature.TensorDescription;

/** A function that can be called with tensors. */
public interface TensorFunction {

  /** Returns the signature of this function */
  Signature signature();

  /**
   * Invokes a function using the default eager session.
   *
   * <p>Caller is responsible for close the result object.
   *
   * @param arguments list of tensors to pass in input to the function, mapped by their signature
   *     name
   * @return output tensors resulting from the execution of the function, mapped by their signature
   *     name
   * @throws IllegalArgumentException if the passed arguments don't match up to the function's
   *     parameters.
   */
  Result call(Map<String, Tensor> arguments);

  /**
   * Invokes a function with a single input and output using the default eager session.
   *
   * <p>Caller is responsible for closing all Tensors.
   *
   * @param tensor input tensor
   * @return output tensor
   * @throws IllegalArgumentException if there are multiple input or output parameters defined in
   *     the function
   */
  default Tensor call(Tensor tensor) {
    if (signature().inputNames().size() > 1) {
      throw new IllegalArgumentException(
          "Can't use call(Tensor) on function \""
              + signature().methodName()
              + "\" with more than one input.");
    }
    if (signature().inputNames().size() < 1) {
      throw new IllegalArgumentException(
          "Can't use call(Tensor) on function \""
              + signature().methodName()
              + "\" with no inputs.");
    }
    if (signature().outputNames().size() > 1) {
      throw new IllegalArgumentException(
          "Can't use call(Tensor) on function \""
              + signature().methodName()
              + "\" with more than one output.");
    }
    if (signature().outputNames().size() < 1) {
      throw new IllegalArgumentException(
          "Can't use call(Tensor) on function \""
              + signature().methodName()
              + "\" with no outputs.");
    }

    String inputName = signature().inputNames().iterator().next();

    Map<String, Tensor> inputMap = new LinkedHashMap<>();
    inputMap.put(inputName, tensor);

    return call(inputMap).get(0);
  }

  static Operand<?> validateDescription(
      TensorDescription description, Graph graph, String name, String prefix) {
    Output<?> operand = graph.output(description.name);
    if (operand == null) {
      throw new IllegalArgumentException(
          prefix
              + " \""
              + name
              + "\"'s operand \""
              + description.name
              + "\" does not exist on the graph.");
    }

    if (operand.dataType() != description.dataType) {
      throw new IllegalArgumentException(
          prefix
              + " \""
              + name
              + "\"'s operand \""
              + description.name
              + "\" has data type "
              + operand.dataType()
              + " in the graph, but the signature requires data type "
              + description.dataType
              + ".");
    }

    if (!operand.shape().isCompatibleWith(description.shape)) {
      throw new IllegalArgumentException(
          prefix
              + " \""
              + name
              + "\"'s operand \""
              + description.name
              + "\" has shape "
              + operand.shape()
              + ", which is incompatible with the signature's required shape of "
              + description.shape
              + ".");
    }
    return operand;
  }
}
