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
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The RpcCall operation
 */
@OpMetadata(
    opType = RpcCall.OP_NAME,
    inputsClass = RpcCall.Inputs.class
)
@Operator
public final class RpcCall extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RpcCall";

  private Output<? extends TType> future;

  private Output<? extends TType> deleter;

  @SuppressWarnings("unchecked")
  public RpcCall(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    future = operation.output(outputIdx++);
    deleter = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RpcCall operation.
   *
   * @param scope current scope
   * @param client The client value
   * @param methodName The methodName value
   * @param args The args value
   * @param timeoutInMs The timeoutInMs value
   * @return a new instance of RpcCall
   */
  @Endpoint(
      describeByClass = true
  )
  public static RpcCall create(Scope scope, Operand<? extends TType> client,
      Operand<TString> methodName, Iterable<Operand<?>> args, Operand<TInt64> timeoutInMs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RpcCall");
    opBuilder.addInput(client.asOutput());
    opBuilder.addInput(methodName.asOutput());
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.addInput(timeoutInMs.asOutput());
    return new RpcCall(opBuilder.build());
  }

  /**
   * Gets future.
   *
   * @return future.
   */
  public Output<? extends TType> future() {
    return future;
  }

  /**
   * Gets deleter.
   *
   * @return deleter.
   */
  public Output<? extends TType> deleter() {
    return deleter;
  }

  @OpInputsMetadata(
      outputsClass = RpcCall.class
  )
  public static class Inputs extends RawOpInputs<RpcCall> {
    /**
     * The client input
     */
    public final Operand<? extends TType> client;

    /**
     * The methodName input
     */
    public final Operand<TString> methodName;

    /**
     * The args input
     */
    public final Iterable<Operand<?>> args;

    /**
     * The timeoutInMs input
     */
    public final Operand<TInt64> timeoutInMs;

    /**
     * The Tin attribute
     */
    public final DataType[] Tin;

    public Inputs(GraphOperation op) {
      super(new RpcCall(op), op, Arrays.asList("Tin"));
      int inputIndex = 0;
      client = (Operand<? extends TType>) op.input(inputIndex++);
      methodName = (Operand<TString>) op.input(inputIndex++);
      int argsLength = op.inputListLength("args");
      args = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argsLength));
      inputIndex += argsLength;
      timeoutInMs = (Operand<TInt64>) op.input(inputIndex++);
      Tin = op.attributes().getAttrTypeList("Tin");
    }
  }
}
