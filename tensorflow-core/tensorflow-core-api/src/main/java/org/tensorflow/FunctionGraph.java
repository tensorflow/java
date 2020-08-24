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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.proto.framework.TensorInfo;
import org.tensorflow.proto.framework.TensorShapeProto;
import org.tensorflow.proto.framework.TensorShapeProto.Dim;

/**
 * A graph that can be invoked as a single function, with an input and output signature.
 *
 * <p>Note that the lifetime of a function is coupled with the lifetime of its graph or session, i.e.
 * the function will failed to be invoked after the graph or session is released, which ever comes
 * first. e.g.
 *
 * <pre>{@code
 * FunctionGraph function;
 * try (Graph g = new Graph()) {
 *   Ops tf = Ops.create(g);
 *   Placeholder<TFloat32> x = tf.placeholder(TFloat32.DTYPE);
 *   Add<TFloat32> y = tf.math.add(x, tf.constant(2.0f));
 *   try (Session s = new Session(s)) {
 *     function = FunctionGraph.builder("myFunction").input("x", x).output("y", y).build(s);
 *     try (Tensor<TFloat32> xValue = TFloat32.scalarOf(10.0f);
 *          Tensor<TFloat32> yValue = function.call(xValue).expect(TFloat32.DTYPE)) {
 *       assertEquals(12.0f, yValue.data().getFloat());
 *     }
 *   }
 * }
 * try (Tensor<TFloat32> xValue = TFloat32.scalarOf(10.0f)) {
 *   function.call(xValue); // fails, graph has been closed
 * }
 * }</pre>
 *
 * <p>A function can also invoke a
 * <a href="https://www.tensorflow.org/api_docs/python/tf/function">tf.function</a>
 * defined in a {@link SavedModelBundle}.
 *
 * <pre>{@code
 * FunctionGraph myFunction = savedModelBundle.function("myFunctionSignatureName");
 * Map<String, Tensor<?>> outputTensorMap = myFunction.call(inputTensorMap);
 * }</pre>
 */
public class FunctionGraph {

  /** The default signature name, when not provided */
  public static final String DEFAULT_NAME = "serving_default";

  /**
   * Builds a new function signature.
   */
  public static class Builder {

    /**
     * Register a tensor as an input of the function.
     *
     * @param inputName user-friendly name for this input tensor
     * @param input input tensor
     * @return this builder
     */
    public Builder input(String inputName, Operand<?> input) {
      signatureBuilder.putInputs(inputName, toTensorInfo(input.asOutput()));
      return this;
    }

    /**
     * Register a tensor as an output of the function.
     *
     * @param inputName user-friendly name for this input tensor
     * @param input input tensor
     * @return this builder
     */
    public Builder output(String outputName, Operand<?> output) {
      signatureBuilder.putOutputs(outputName, toTensorInfo(output.asOutput()));
      return this;
    }

    /**
     * Provide extensible name information enabling third-party users to mark a signature as
     * supporting a particular method
     *
     * @param methodName method name
     * @return this builder
     */
    public Builder methodName(String methodName) {
      signatureBuilder.setMethodName(methodName);
      return this;
    }

    /**
     * Creates a function from a graph session.
     *
     * <p>The provided session will be used for running or saving this function.
     *
     * @param signature signature of the function
     * @param session a graph session
     * @return a function
     */
    public FunctionGraph build(Session session) {
      return new FunctionGraph(name, signatureBuilder.build(), session);
    }

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

    private final String name;
    private final SignatureDef.Builder signatureBuilder = SignatureDef.newBuilder();

    private Builder(String name) {
      this.name = name;
    }
  }

  /**
   * Returns a new builder for creating a function
   *
   * <p>"serving_default" will be used as the default function signature name.
   */
  public static Builder builder() {
    return new Builder(DEFAULT_NAME);
  }

  /**
   * Returns a new builder for creating a function.
   *
   * @param name function signature name
   */
  public static Builder builder(String name) {
    return new Builder(name);
  }

  /**
   * Return the name of this function
   */
  public String name() {
    return name;
  }

  /**
   * Returns the method name of this function (e.g. as exposed by a server)
   */
  public String methodName() {
    return signatureDef.getMethodName();
  }

  /**
   * Returns the names of the inputs of this function.
   */
  public Set<String> inputNames() {
    return signatureDef.getInputsMap().keySet();
  }

  /**
   * Returns the names of the outputs of this function.
   */
  public Set<String> outputNames() {
    return signatureDef.getOutputsMap().keySet();
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

    signatureDef.getInputsMap().forEach((argName, t) -> {
      Tensor<?> tensor = arguments.get(argName);
      if (tensor == null) {
        throw new IllegalArgumentException(String.format("Missing argument [%s]", argName));
      }
      runner.feed(t.getName(), tensor);
    });

    Map<String, TensorInfo> outputToNode = signatureDef.getOutputsMap();
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
    if (signatureDef.getInputsCount() != 1) {
      throw new IllegalArgumentException(
        String.format("Function [%s] requires multiple inputs", signatureDef.getMethodName()));
    }
    String inputNodeName = signatureDef.getInputsMap().values().iterator().next().getName();

    if (signatureDef.getOutputsCount() != 1) {
      throw new IllegalArgumentException(
        String.format("Function [%s] has multiple outputs", signatureDef.getMethodName()));
    }
    String outputNodeName = signatureDef.getOutputsMap().values().iterator().next().getName();

    return session.runner().feed(inputNodeName, tensor).fetch(outputNodeName).run().get(0);
  }

  Session session() {
    return session;
  }

  SignatureDef signatureDef() {
    return signatureDef;
  }

  private final String name;
  private final Session session;
  private final SignatureDef signatureDef;

  FunctionGraph(String name, SignatureDef signatureDef, Session session) {
    this.name = name;
    this.session = session;
    this.signatureDef = signatureDef;
  }
}
