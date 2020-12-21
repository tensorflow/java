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

import java.util.Map;
import java.util.Set;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.proto.framework.TensorInfo;
import org.tensorflow.proto.framework.TensorShapeProto;
import org.tensorflow.proto.framework.TensorShapeProto.Dim;

/**
 * Describe the inputs and outputs of an executable entity, such as a {@link ConcreteFunction}, among
 * other useful metadata.
 */
public class Signature  {

  /** The default signature key, when not provided */
  public static final String DEFAULT_KEY = "serving_default";

  /**
   * Builds a new function signature.
   */
  public static class Builder {

    /**
     * Sets the unique key of this signature.
     *
     * <p>When not set explicitly, the default value is {@link #DEFAULT_KEY}.
     *
     * @param key signature key
     * @return this builder
     * @throws IllegalArgumentException if the key is invalid
     */
    public Builder key(String key) {
      if (key == null || key.isEmpty()) {
        throw new IllegalArgumentException("Invalid key: " + key);
      }
      this.key = key;
      return this;
    }

    /**
     * Register a tensor as an input of the function.
     *
     * @param inputName user-friendly name for this input tensor
     * @param input input tensor
     * @return this builder
     * @throws IllegalArgumentException if {@code inputName} is already mapped to another input
     */
    public Builder input(String inputName, Operand<?> input) {
      if (signatureBuilder.containsInputs(inputName)) {
        throw new IllegalArgumentException("\"" + inputName + "\" is already being mapped to another input");
      }
      signatureBuilder.putInputs(inputName, toTensorInfo(input.asOutput()));
      return this;
    }

    /**
     * Register a tensor as an output of the function.
     *
     * @param outputName user-friendly name for this output tensor
     * @param output output tensor
     * @return this builder
     * @throws IllegalArgumentException if {@code outputName} is already mapped to another output
     */
    public Builder output(String outputName, Operand<?> output) {
      if (signatureBuilder.containsOutputs(outputName)) {
        throw new IllegalArgumentException("\"" + outputName + "\" is already being mapped to another output");
      }
      signatureBuilder.putOutputs(outputName, toTensorInfo(output.asOutput()));
      return this;
    }

    /**
     * Provide extensible name information enabling third-party users to mark a signature as
     * supporting a particular method
     *
     * @param methodName method name or null for none (default)
     * @return this builder
     */
    public Builder methodName(String methodName) {
      signatureBuilder.setMethodName(methodName == null ? "" : methodName);
      return this;
    }

    /**
     * Returns a signature from the provided data.
     */
    public Signature build() {
      return new Signature(key, signatureBuilder.build());
    }

    private static TensorInfo toTensorInfo(Output<?> operand) {
      Shape shape = operand.shape();
      TensorShapeProto.Builder tensorShapeBuilder = TensorShapeProto.newBuilder();
      for (int i = 0; i < shape.numDimensions(); ++i) {
        tensorShapeBuilder.addDim(Dim.newBuilder().setSize(shape.size(i)));
      }
      return TensorInfo.newBuilder()
          .setDtype(operand.dataType())
          .setTensorShape(tensorShapeBuilder)
          .setName(operand.op().name() + ":" + operand.index())
          .build();
    }

    private String key = DEFAULT_KEY;
    private final SignatureDef.Builder signatureBuilder = SignatureDef.newBuilder();
  }

  /**
   * Returns a new builder for creating a signature
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Return the key of this signature
   */
  public String key() {
    return key;
  }

  /**
   * Returns the method name of this signature (e.g. as exposed by TF serving) or null if none
   */
  public String methodName() {
    return signatureDef.getMethodName().isEmpty() ? null : signatureDef.getMethodName();
  }

  /**
   * Returns the names of the inputs in this signature
   */
  public Set<String> inputNames() {
    return signatureDef.getInputsMap().keySet();
  }

  /**
   * Returns the names of the outputs in this signature
   */
  public Set<String> outputNames() {
    return signatureDef.getOutputsMap().keySet();
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder("Signature for \"" + key +"\":\n");
    if (!methodName().isEmpty()) {
      strBuilder.append("\tMethod: \"").append(methodName()).append("\"\n");
    }
    if (signatureDef.getInputsCount() > 0) {
      strBuilder.append("\tInputs:\n");
      printTensorInfo(signatureDef.getInputsMap(), strBuilder);
    }
    if (signatureDef.getOutputsCount() > 0) {
      strBuilder.append("\tOutputs:\n");
      printTensorInfo(signatureDef.getOutputsMap(), strBuilder);
    }
    return strBuilder.toString();
  }

  Signature(String key, SignatureDef signatureDef) {
    this.key = key;
    this.signatureDef = signatureDef;
  }

  SignatureDef asSignatureDef() {
    return signatureDef;
  }

  private final String key;
  private final SignatureDef signatureDef;

  private static void printTensorInfo(Map<String, TensorInfo> tensorMap, StringBuilder strBuilder) {
    tensorMap.forEach((key, tensorInfo) -> {
      strBuilder.append("\t\t\"")
          .append(key)
          .append("\": dtype=")
          .append(tensorInfo.getDtype().name())
          .append(", shape=(");
      for (int i = 0; i < tensorInfo.getTensorShape().getDimCount(); ++i) {
        strBuilder.append(tensorInfo.getTensorShape().getDim(i).getSize());
        if (i < tensorInfo.getTensorShape().getDimCount() - 1) {
          strBuilder.append(", ");
        }
      }
      strBuilder.append(")\n");
    });
  }
}
