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

import java.util.List;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.proto.framework.TensorInfo;
import org.tensorflow.proto.framework.TensorShapeProto;
import org.tensorflow.proto.framework.TensorShapeProto.Dim;

/**
 * Invoke <a href="https://www.tensorflow.org/api_docs/python/tf/function">tf.function</a>
 * defined in a {@link SavedModelBundle}.
 *
 * <pre>{@code
 * FunctionGraph myFunction = savedModelBundle.function("myFunctionSignatureName");
 * Map<String, Tensor<?>> outputTensorMap = myFunction.call(session, inputTensorMap);
 * }</pre>
 *
 */
public class FunctionGraph implements AutoCloseable {

  public static class SignatureBuilder {

    public SignatureBuilder addInput(String inputName, Operand<?> input) {
      signatureBuilder.putInputs(inputName, toTensorInfo(input.asOutput()));
      return this;
    }

    public SignatureBuilder addOutput(String outputName, Operand<?> output) {
      signatureBuilder.putOutputs(outputName, toTensorInfo(output.asOutput()));
      return this;
    }

    public SignatureBuilder methodName(String methodName) {
      signatureBuilder.setMethodName(methodName);
      return this;
    }

    private SignatureDef build() {
      return signatureBuilder.build();
    }

    private final SignatureDef.Builder signatureBuilder = SignatureDef.newBuilder();

    private static TensorInfo toTensorInfo(Output<?> operand) {
      Shape shape = operand.shape();
      TensorShapeProto.Builder tensorShapeBuilder = TensorShapeProto.newBuilder();
      for (int i = 0; i < shape.numDimensions(); ++i) {
        tensorShapeBuilder.addDim(Dim.newBuilder().setSize(shape.size(i)));
      }
      return TensorInfo.newBuilder()
          .setDtype(DataType.forNumber(operand.dataType().nativeCode()))
          .setTensorShape(tensorShapeBuilder)
          .setName(operand.op().name() + ":" + operand.index())
          .build();
    }
  }

  public static FunctionGraph create(BiConsumer<Map<String, SignatureDef>, Graph> function) {
    Graph graph = new Graph();
    Map<String, SignatureDef> signatures = new HashMap<>();
    function.accept(signatures, graph);
    return new FunctionGraph(signatures, graph);
  }

  public static FunctionGraph create(SignatureDef signature, Graph graph) {
    return new FunctionGraph(signature, graph);
  }

  /**
   * Returns the method name of this function
   */
  public String methodName() {
    return signature.getMethodName();
  }

  /**
   * Returns the names of the inputs of this function.
   */
  public Set<String> inputNames() {
    return signature.getInputsMap().keySet();
  }

  /**
   * Returns the names of the outputs of this function.
   */
  public Set<String> outputNames() {
    return signature.getOutputsMap().keySet();
  }

  /**
   * Invokes a function.
   *
   * <p>Caller is responsible for closing all Tensors.
   *
   * @param tensor input tensor
   * @return output tensor
   */
  public Map<String, Tensor<?>> call(Map<String, Tensor<?>> arguments)
      throws IllegalArgumentException {

    final Session.Runner runner = session.runner();

    signature.getInputsMap().forEach((argName, t) -> {
      Tensor<?> tensor = arguments.get(argName);
      if (tensor == null) {
        throw new IllegalArgumentException(String.format("Missing argument [%s]", argName));
      }
      runner.feed(t.getName(), tensor);
    });

    Map<String, TensorInfo> outputToNode = signature.getOutputsMap();
    outputToNode.values().forEach(t -> runner.fetch(t.getName()));

    List<Tensor<?>> resultTensors = runner.run();
    try {
      ListIterator<Tensor<?>> resultTensorIter = resultTensors.listIterator();
      Map<String, Tensor<?>> returnMap = new HashMap<String, Tensor<?>>();

      // Use the output names as present in the signature definition
      for (String nodeName: outputToNode.keySet()) {
        returnMap.put(nodeName, resultTensorIter.next());
      }
      return returnMap;

    } catch (Exception e) {
      // Release tensors before throwing exception
      for (Tensor<?> t : resultTensors) {
        t.close();
      }
      throw e;
    }
  }

  /**
   * Invokes a function with a single input and output.
   *
   * <p>Caller is responsible for closing all Tensors.
   *
   * @param tensor input tensor
   * @return output tensor
   * @throws IllegalArgumentException if there are multiple input or output parameters defined
   *                                  in the function
   */
  public Tensor<?> call(Tensor<?> tensor) throws IllegalArgumentException {
    if (signature.getInputsCount() != 1) {
      throw new IllegalArgumentException(
        String.format("Function [%s] requires multiple inputs", signature.getMethodName()));
    }
    String inputNodeName = signature.getInputsMap().values().iterator().next().getName();

    if (signature.getOutputsCount() != 1) {
      throw new IllegalArgumentException(
        String.format("Function [%s] has multiple outputs", signature.getMethodName()));
    }
    String outputNodeName = signature.getOutputsMap().values().iterator().next().getName();

    return session.runner().feed(inputNodeName, tensor).fetch(outputNodeName).run().get(0);
  }

  /**
   * Returns the signature of this function
   */
  public SignatureDef signature() {
    return signature;
  }

  @Override
  public void close() {
    session.close();
    graph.close();
  }

  FunctionGraph(SignatureDef signature, Graph graph) {
    this.graph = graph;
    this.session = new Session(graph);
    this.signature = signature;
  }

  FunctionGraph(Session session, SignatureDef signature) {
    this.graph = session.graph();
    this.session = session;
    this.signature = signature;
  }

  private final Map<String, SignatureDef> signatures;
  private final Graph graph;
  private final Session session;
  private final SignatureDef signature;
}
