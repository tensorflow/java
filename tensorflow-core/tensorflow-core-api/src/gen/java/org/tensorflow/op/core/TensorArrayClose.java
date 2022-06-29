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
import org.tensorflow.types.family.TType;

/**
 * Delete the TensorArray from its resource container.
 * This enables the user to close and release the resource in the middle
 * of a step/run.
 */
@OpMetadata(
    opType = TensorArrayClose.OP_NAME,
    inputsClass = TensorArrayClose.Inputs.class
)
@Operator
public final class TensorArrayClose extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorArrayCloseV3";

  public TensorArrayClose(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new TensorArrayCloseV3 operation.
   *
   * @param scope current scope
   * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
   * @return a new instance of TensorArrayClose
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorArrayClose create(Scope scope, Operand<? extends TType> handle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorArrayClose");
    opBuilder.addInput(handle.asOutput());
    return new TensorArrayClose(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = TensorArrayClose.class
  )
  public static class Inputs extends RawOpInputs<TensorArrayClose> {
    /**
     * The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
     */
    public final Operand<? extends TType> handle;

    public Inputs(GraphOperation op) {
      super(new TensorArrayClose(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
