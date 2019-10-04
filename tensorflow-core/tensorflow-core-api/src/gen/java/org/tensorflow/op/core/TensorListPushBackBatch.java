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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 */
@Operator
public final class TensorListPushBackBatch extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class wrapping a new TensorListPushBackBatch operation.
   * 
   * @param scope current scope
   * @param inputHandles 
   * @param tensor 
   * @return a new instance of TensorListPushBackBatch
   */
  public static <T> TensorListPushBackBatch create(Scope scope, Operand<?> inputHandles, Operand<T> tensor) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListPushBackBatch", scope.makeOpName("TensorListPushBackBatch"));
    opBuilder.addInput(inputHandles.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new TensorListPushBackBatch(opBuilder.build());
  }
  
  /**
   */
  public Output<?> outputHandles() {
    return outputHandles;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<Object> asOutput() {
    return (Output<Object>) outputHandles;
  }
  
  private Output<?> outputHandles;
  
  private TensorListPushBackBatch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandles = operation.output(outputIdx++);
  }
}
