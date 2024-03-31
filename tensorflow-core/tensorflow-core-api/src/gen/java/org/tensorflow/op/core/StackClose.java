/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.types.family.TType;

/**
 * Delete the stack from its resource container.
 */
@OpMetadata(
    opType = StackClose.OP_NAME,
    inputsClass = StackClose.Inputs.class
)
@Operator
public final class StackClose extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StackCloseV2";

  public StackClose(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new StackCloseV2 operation.
   *
   * @param scope current scope
   * @param handle The handle to a stack.
   * @return a new instance of StackClose
   */
  @Endpoint(
      describeByClass = true
  )
  public static StackClose create(Scope scope, Operand<? extends TType> handle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StackClose");
    opBuilder.addInput(handle.asOutput());
    return new StackClose(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = StackClose.class
  )
  public static class Inputs extends RawOpInputs<StackClose> {
    /**
     * The handle to a stack.
     */
    public final Operand<? extends TType> handle;

    public Inputs(GraphOperation op) {
      super(new StackClose(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
