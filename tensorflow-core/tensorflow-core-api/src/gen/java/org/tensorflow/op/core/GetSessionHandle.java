/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Store the input tensor in the state of the current session.
 */
@OpMetadata(
    opType = GetSessionHandle.OP_NAME,
    inputsClass = GetSessionHandle.Inputs.class
)
@Operator
public final class GetSessionHandle extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GetSessionHandleV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public GetSessionHandle(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GetSessionHandleV2 operation.
   *
   * @param scope current scope
   * @param value The tensor to be stored.
   * @return a new instance of GetSessionHandle
   */
  @Endpoint(
      describeByClass = true
  )
  public static GetSessionHandle create(Scope scope, Operand<? extends TType> value) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GetSessionHandle");
    opBuilder.addInput(value.asOutput());
    return new GetSessionHandle(opBuilder.build());
  }

  /**
   * Gets handle.
   * The handle for the tensor stored in the session state, represented
   * as a ResourceHandle object.
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  @OpInputsMetadata(
      outputsClass = GetSessionHandle.class
  )
  public static class Inputs extends RawOpInputs<GetSessionHandle> {
    /**
     * The tensor to be stored.
     */
    public final Operand<? extends TType> value;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new GetSessionHandle(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      value = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
