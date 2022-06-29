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
import org.tensorflow.types.TString;

/**
 * Computes the number of incomplete elements in the given barrier.
 */
@OpMetadata(
    opType = BarrierIncompleteSize.OP_NAME,
    inputsClass = BarrierIncompleteSize.Inputs.class
)
@Operator
public final class BarrierIncompleteSize extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BarrierIncompleteSize";

  private Output<TInt32> output;

  public BarrierIncompleteSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BarrierIncompleteSize operation.
   *
   * @param scope current scope
   * @param handle The handle to a barrier.
   * @return a new instance of BarrierIncompleteSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static BarrierIncompleteSize create(Scope scope, Operand<TString> handle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BarrierIncompleteSize");
    opBuilder.addInput(handle.asOutput());
    return new BarrierIncompleteSize(opBuilder.build());
  }

  /**
   * Gets output.
   * The number of incomplete elements (i.e. those with some of their value
   * components not set) in the barrier.
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
      outputsClass = BarrierIncompleteSize.class
  )
  public static class Inputs extends RawOpInputs<BarrierIncompleteSize> {
    /**
     * The handle to a barrier.
     */
    public final Operand<TString> handle;

    public Inputs(GraphOperation op) {
      super(new BarrierIncompleteSize(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
