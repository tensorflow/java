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

package org.tensorflow.op.train;

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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Updates the accumulator with a new value for global_step.
 * Logs warning if the accumulator's value is already higher than
 * new_global_step.
 */
@OpMetadata(
    opType = AccumulatorSetGlobalStep.OP_NAME,
    inputsClass = AccumulatorSetGlobalStep.Inputs.class
)
@Operator(
    group = "train"
)
public final class AccumulatorSetGlobalStep extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AccumulatorSetGlobalStep";

  public AccumulatorSetGlobalStep(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new AccumulatorSetGlobalStep operation.
   *
   * @param scope current scope
   * @param handle The handle to an accumulator.
   * @param newGlobalStep The new global_step value to set.
   * @return a new instance of AccumulatorSetGlobalStep
   */
  @Endpoint(
      describeByClass = true
  )
  public static AccumulatorSetGlobalStep create(Scope scope, Operand<TString> handle,
      Operand<TInt64> newGlobalStep) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AccumulatorSetGlobalStep");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(newGlobalStep.asOutput());
    return new AccumulatorSetGlobalStep(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = AccumulatorSetGlobalStep.class
  )
  public static class Inputs extends RawOpInputs<AccumulatorSetGlobalStep> {
    /**
     * The handle to an accumulator.
     */
    public final Operand<TString> handle;

    /**
     * The new global_step value to set.
     */
    public final Operand<TInt64> newGlobalStep;

    public Inputs(GraphOperation op) {
      super(new AccumulatorSetGlobalStep(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
      newGlobalStep = (Operand<TInt64>) op.input(inputIndex++);
    }
  }
}
