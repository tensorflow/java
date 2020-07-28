/*
 * Copyright 2020 The TensorFlow Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.List;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.Map;

/**
 * Invoke <a href="https://www.tensorflow.org/api_docs/python/tf/function">tf.function</a>
 * defined in a {@link SavedModelBundle}.
 *
 * <pre>{@code
 * TfFunction myFunction = savedModelBundle.function("myFunctionSignatureName");
 * Map<String, Tensor<?>> outputTensorMap = myFunction.call(inputTensorMap);
 * }</pre>
 *
 */
public class TfFunction {

  public TfFunction(
    String functionSignatureName,
    SavedModelBundle.SignatureToNodeName nameToNode, Session session) {
    this.nameToNode = nameToNode;
    this.session = session;
    this.functionSignatureName = functionSignatureName;
  }

  /**
   * Invokes a tf.function.
   * Caller is responsible for closing all Tensors.
   *
   * @param arguments map of input tensors
   * @return map of output tensors
   */
  public Map<String, Tensor<?>> call(
    Map<String, Tensor<?>> arguments) throws IllegalArgumentException {

    Session.Runner runner = this.session.runner();

    Map<String, String> inputToNode = this.nameToNode.inputNameToNode(this.functionSignatureName);

    if (inputToNode == null) {
      throw new IllegalArgumentException(
        String.format("Function [%s] is missing input", this.functionSignatureName));
    }

    // Join arguments.key, inputToNodeName.key
    for (Map.Entry<String, String> entry: inputToNode.entrySet()) {
      String argName = entry.getKey();
      Tensor<?> tensor = arguments.get(argName);

      if (tensor == null) {
        throw new IllegalArgumentException(String.format("Missing argument [%s]", argName));
      }

      // Node name in the tensorflow graph, corresponding to the tf.function argument
      runner = runner.feed(entry.getValue(), tensor);
    }

    Map<String, String> outputToNode = this.nameToNode.outputNameToNode(this.functionSignatureName);
    if (outputToNode == null) {
      throw new IllegalArgumentException(
        String.format("Function [%] is missing output", this.functionSignatureName));
    }

    for (String nodeName: outputToNode.values()) {
      // Node names corresponding to the return value
      runner = runner.fetch(nodeName);
    }

    List<Tensor<?>> resultTensors = runner.run();
    ListIterator<Tensor<?>> resultTensorIter = resultTensors.listIterator();

    Map<String, Tensor<?>> returnMap = new HashMap<String, Tensor<?>>();

    // Use the output names as present in the signature definition
    for (String nodeName: outputToNode.keySet()) {
      returnMap.put(nodeName, resultTensorIter.next());
    }

    return returnMap;
  }

  /**
   * Invokes a tf.function.
   * Caller is responsible for closing all Tensors.
   *
   * Throws IllegalArgumentException if there are multiple input or output parameters defined
   * in the tf.function
   *
   * @param tensor input tensor
   * @return output tensor
   */
  public Tensor<?> call(Tensor<?> tensor) throws IllegalArgumentException {
    Session.Runner runner = this.session.runner();

    Map<String, String> inputToNode = this.nameToNode.inputNameToNode(this.functionSignatureName);

    if (inputToNode == null) {
      throw new IllegalArgumentException(
        String.format("Function [%s] is missing input", this.functionSignatureName));
    }

    if (inputToNode.size() != 1) {
      throw new IllegalArgumentException(
        String.format("Function [%s] requires multiple inputs", this.functionSignatureName));
    }

    // Feed the single argument
    for (Map.Entry<String, String> entry: inputToNode.entrySet()) {
      // Node name in the tensorflow graph, corresponding to the tf.function argument
      runner = runner.feed(entry.getValue(), tensor);
    }

    Map<String, String> outputToNode = this.nameToNode.outputNameToNode(this.functionSignatureName);
    if (outputToNode == null) {
      throw new IllegalArgumentException(
        String.format("Function [%] is missing output", this.functionSignatureName));
    }

    if (outputToNode.size() != 1) {
      throw new IllegalArgumentException(
        String.format("Function [%s] has multiple outputs", this.functionSignatureName));
    }

    // Fetch the single return tensor
    for (String nodeName: outputToNode.values()) {
      // Node names corresponding to the return value
      runner = runner.fetch(nodeName);
    }

    List<Tensor<?>> resultTensors = runner.run();

    return resultTensors.get(0);
  }

  private final Session session;
  private final SavedModelBundle.SignatureToNodeName nameToNode;
  private final String functionSignatureName;
}
