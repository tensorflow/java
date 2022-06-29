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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Delete the tensor specified by its handle in the session.
 */
@OpMetadata(
    opType = DeleteSessionTensor.OP_NAME,
    inputsClass = DeleteSessionTensor.Inputs.class
)
@Operator
public final class DeleteSessionTensor extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DeleteSessionTensor";

  public DeleteSessionTensor(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DeleteSessionTensor operation.
   *
   * @param scope current scope
   * @param handle The handle for a tensor stored in the session state.
   * @return a new instance of DeleteSessionTensor
   */
  @Endpoint(
      describeByClass = true
  )
  public static DeleteSessionTensor create(Scope scope, Operand<TString> handle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DeleteSessionTensor");
    opBuilder.addInput(handle.asOutput());
    return new DeleteSessionTensor(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = DeleteSessionTensor.class
  )
  public static class Inputs extends RawOpInputs<DeleteSessionTensor> {
    /**
     * The handle for a tensor stored in the session state.
     */
    public final Operand<TString> handle;

    public Inputs(GraphOperation op) {
      super(new DeleteSessionTensor(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
