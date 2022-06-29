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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Returns the number of tensors in the input tensor map.
 * input_handle: the input map
 * size: the number of tensors in the map
 */
@OpMetadata(
    opType = TensorMapSize.OP_NAME,
    inputsClass = TensorMapSize.Inputs.class
)
@Operator
public final class TensorMapSize extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorMapSize";

  private Output<TInt32> output;

  public TensorMapSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorMapSize operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @return a new instance of TensorMapSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorMapSize create(Scope scope, Operand<? extends TType> inputHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorMapSize");
    opBuilder.addInput(inputHandle.asOutput());
    return new TensorMapSize(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = TensorMapSize.class
  )
  public static class Inputs extends RawOpInputs<TensorMapSize> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    public Inputs(GraphOperation op) {
      super(new TensorMapSize(op), op, Arrays.asList());
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
