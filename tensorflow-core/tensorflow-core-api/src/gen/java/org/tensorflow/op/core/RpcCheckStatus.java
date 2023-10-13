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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The RpcCheckStatus operation
 */
@OpMetadata(
    opType = RpcCheckStatus.OP_NAME,
    inputsClass = RpcCheckStatus.Inputs.class
)
@Operator
public final class RpcCheckStatus extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RpcCheckStatus";

  private Output<TInt64> errorCode;

  private Output<TString> error;

  public RpcCheckStatus(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    errorCode = operation.output(outputIdx++);
    error = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RpcCheckStatus operation.
   *
   * @param scope current scope
   * @param statusOr The statusOr value
   * @return a new instance of RpcCheckStatus
   */
  @Endpoint(
      describeByClass = true
  )
  public static RpcCheckStatus create(Scope scope, Operand<? extends TType> statusOr) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RpcCheckStatus");
    opBuilder.addInput(statusOr.asOutput());
    return new RpcCheckStatus(opBuilder.build());
  }

  /**
   * Gets errorCode.
   *
   * @return errorCode.
   */
  public Output<TInt64> errorCode() {
    return errorCode;
  }

  /**
   * Gets error.
   *
   * @return error.
   */
  public Output<TString> error() {
    return error;
  }

  @OpInputsMetadata(
      outputsClass = RpcCheckStatus.class
  )
  public static class Inputs extends RawOpInputs<RpcCheckStatus> {
    /**
     * The statusOr input
     */
    public final Operand<? extends TType> statusOr;

    public Inputs(GraphOperation op) {
      super(new RpcCheckStatus(op), op, Arrays.asList());
      int inputIndex = 0;
      statusOr = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
