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

import org.tensorflow.DataType;
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
public final class TensorListConcatLists extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class wrapping a new TensorListConcatLists operation.
   * 
   * @param scope current scope
   * @param inputA 
   * @param inputB 
   * @param elementDtype 
   * @return a new instance of TensorListConcatLists
   */
  public static <T> TensorListConcatLists create(Scope scope, Operand<?> inputA, Operand<?> inputB, DataType<T> elementDtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListConcatLists", scope.makeOpName("TensorListConcatLists"));
    opBuilder.addInput(inputA.asOutput());
    opBuilder.addInput(inputB.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("element_dtype", elementDtype);
    return new TensorListConcatLists(opBuilder.build());
  }
  
  /**
   */
  public Output<?> output() {
    return output;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<Object> asOutput() {
    return (Output<Object>) output;
  }
  
  private Output<?> output;
  
  private TensorListConcatLists(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
