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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Resizes the list.
 * input_handle: the input list
 * size: size of the output list
 */
@Operator
public final class TensorListResize extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListResize";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  private TensorListResize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListResize operation.
   *
   * @param scope current scope
   * @param inputHandle the inputHandle value
   * @param sizeOutput the sizeOutput value
   * @return a new instance of TensorListResize
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListResize create(Scope scope, Operand<? extends TType> inputHandle,
      Operand<TInt32> sizeOutput) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListResize", scope.makeOpName("TensorListResize"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorListResize(opBuilder.build());
  }

  /**
   * Gets outputHandle.
   *
   * @return outputHandle.
   */
  public Output<? extends TType> outputHandle() {
    return outputHandle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) outputHandle;
  }
}
