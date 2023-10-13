/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The RpcServerRegister operation
 */
@OpMetadata(
    opType = RpcServerRegister.OP_NAME,
    inputsClass = RpcServerRegister.Inputs.class
)
@Operator
public final class RpcServerRegister extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RpcServerRegister";

  public RpcServerRegister(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new RpcServerRegister operation.
   *
   * @param scope current scope
   * @param server The server value
   * @param methodName The methodName value
   * @param capturedInputs The capturedInputs value
   * @param f The value of the f attribute
   * @param outputSpecs The value of the outputSpecs attribute
   * @param options carries optional attribute values
   * @return a new instance of RpcServerRegister
   */
  @Endpoint(
      describeByClass = true
  )
  public static RpcServerRegister create(Scope scope, Operand<? extends TType> server,
      Operand<TString> methodName, Iterable<Operand<?>> capturedInputs, ConcreteFunction f,
      String outputSpecs, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RpcServerRegister");
    opBuilder.addInput(server.asOutput());
    opBuilder.addInput(methodName.asOutput());
    opBuilder.addInputList(Operands.asOutputs(capturedInputs));
    opBuilder.setAttr("f", f);
    opBuilder.setAttr("output_specs", outputSpecs);
    if (options != null) {
      for (Options opts : options) {
        if (opts.inputSpecs != null) {
          opBuilder.setAttr("input_specs", opts.inputSpecs);
        }
      }
    }
    return new RpcServerRegister(opBuilder.build());
  }

  /**
   * Sets the inputSpecs option.
   *
   * @param inputSpecs the inputSpecs option
   * @return this Options instance.
   */
  public static Options inputSpecs(String inputSpecs) {
    return new Options().inputSpecs(inputSpecs);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.RpcServerRegister}
   */
  public static class Options {
    private String inputSpecs;

    private Options() {
    }

    /**
     * Sets the inputSpecs option.
     *
     * @param inputSpecs the inputSpecs option
     * @return this Options instance.
     */
    public Options inputSpecs(String inputSpecs) {
      this.inputSpecs = inputSpecs;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RpcServerRegister.class
  )
  public static class Inputs extends RawOpInputs<RpcServerRegister> {
    /**
     * The server input
     */
    public final Operand<? extends TType> server;

    /**
     * The methodName input
     */
    public final Operand<TString> methodName;

    /**
     * The capturedInputs input
     */
    public final Iterable<Operand<?>> capturedInputs;

    /**
     * The Tin attribute
     */
    public final DataType[] Tin;

    /**
     * The inputSpecs attribute
     */
    public final String inputSpecs;

    /**
     * The outputSpecs attribute
     */
    public final String outputSpecs;

    public Inputs(GraphOperation op) {
      super(new RpcServerRegister(op), op, Arrays.asList("Tin", "input_specs", "output_specs"));
      int inputIndex = 0;
      server = (Operand<? extends TType>) op.input(inputIndex++);
      methodName = (Operand<TString>) op.input(inputIndex++);
      int capturedInputsLength = op.inputListLength("captured_inputs");
      capturedInputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, capturedInputsLength));
      inputIndex += capturedInputsLength;
      Tin = op.attributes().getAttrTypeList("Tin");
      inputSpecs = op.attributes().getAttrString("input_specs");
      outputSpecs = op.attributes().getAttrString("output_specs");
    }
  }
}
