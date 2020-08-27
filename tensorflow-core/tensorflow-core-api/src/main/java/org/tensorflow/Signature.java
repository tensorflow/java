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
     * Returns a signature from the provided data.
     */
    public Signature build() {
      return new Signature(name, signatureBuilder.build());
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
   * Returns a new builder for creating a signature
   *
   * <p>"serving_default" will be used as the default signature name.
   */
  public static Builder builder() {
    return new Builder(DEFAULT_NAME);
  }

  /**
   * Returns a new builder for creating a signature.
   *
   * @param name signature name
   */
  public static Builder builder(String name) {
    return new Builder(name);
  }

  /**
   * Return the name of this signature
   */
  public String name() {
    return name;
  }

  /**
   * Returns the method name of this signature (e.g. as exposed by TF serving)
   */
  public String methodName() {
    return signatureDef.getMethodName();
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

  SignatureDef asSignatureDef() {
    return signatureDef;
  }

  private final String name;
  private final SignatureDef signatureDef;

  Signature(String name, SignatureDef signatureDef) {
    this.name = name;
    this.signatureDef = signatureDef;
  }
}
