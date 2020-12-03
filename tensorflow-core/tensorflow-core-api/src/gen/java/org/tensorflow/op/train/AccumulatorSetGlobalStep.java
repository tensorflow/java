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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Updates the accumulator with a new value for global_step.
 * <p>
 * Logs warning if the accumulator's value is already higher than
 * new_global_step.
 */
@Operator(group = "train")
public final class AccumulatorSetGlobalStep extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new AccumulatorSetGlobalStep operation.
   * 
   * @param scope current scope
   * @param handle The handle to an accumulator.
   * @param newGlobalStep The new global_step value to set.
   * @return a new instance of AccumulatorSetGlobalStep
   */
  @Endpoint(describeByClass = true)
  public static AccumulatorSetGlobalStep create(Scope scope, Operand<TString> handle, Operand<TInt64> newGlobalStep) {
    OperationBuilder opBuilder = scope.env().opBuilder("AccumulatorSetGlobalStep", scope.makeOpName("AccumulatorSetGlobalStep"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(newGlobalStep.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new AccumulatorSetGlobalStep(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "AccumulatorSetGlobalStep";
  
  private AccumulatorSetGlobalStep(Operation operation) {
    super(operation);
  }
}
