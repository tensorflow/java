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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Get the current size of the TensorArray.
 */
@OpMetadata(
    opType = TensorArraySize.OP_NAME,
    inputsClass = TensorArraySize.Inputs.class
)
@Operator
public final class TensorArraySize extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorArraySizeV3";

  private Output<TInt32> output;

  public TensorArraySize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorArraySizeV3 operation.
   *
   * @param scope current scope
   * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArraySize
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorArraySize create(Scope scope, Operand<? extends TType> handle,
      Operand<TFloat32> flowIn) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorArraySize");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    return new TensorArraySize(opBuilder.build());
  }

  /**
   * Gets output.
   * The current size of the TensorArray.
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = TensorArraySize.class
  )
  public static class Inputs extends RawOpInputs<TensorArraySize> {
    /**
     * The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
     */
    public final Operand<? extends TType> handle;

    /**
     * A float scalar that enforces proper chaining of operations.
     */
    public final Operand<TFloat32> flowIn;

    public Inputs(GraphOperation op) {
      super(new TensorArraySize(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      flowIn = (Operand<TFloat32>) op.input(inputIndex++);
    }
  }
}
