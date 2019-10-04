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

package org.tensorflow.op.io;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Returns the number of work units this Reader has finished processing.
 */
@Operator(group = "io")
public final class ReaderNumWorkUnitsCompleted extends PrimitiveOp implements Operand<Long> {
  
  /**
   * Factory method to create a class wrapping a new ReaderNumWorkUnitsCompleted operation.
   * 
   * @param scope current scope
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderNumWorkUnitsCompleted
   */
  public static ReaderNumWorkUnitsCompleted create(Scope scope, Operand<?> readerHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("ReaderNumWorkUnitsCompletedV2", scope.makeOpName("ReaderNumWorkUnitsCompleted"));
    opBuilder.addInput(readerHandle.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new ReaderNumWorkUnitsCompleted(opBuilder.build());
  }
  
  /**
   */
  public Output<Long> unitsCompleted() {
    return unitsCompleted;
  }
  
  @Override
  public Output<Long> asOutput() {
    return unitsCompleted;
  }
  
  private Output<Long> unitsCompleted;
  
  private ReaderNumWorkUnitsCompleted(Operation operation) {
    super(operation);
    int outputIdx = 0;
    unitsCompleted = operation.output(outputIdx++);
  }
}
