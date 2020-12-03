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
 */
@Operator
public final class TensorListSetItem extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new TensorListSetItem operation.
   * 
   * @param scope current scope
   * @param inputHandle 
   * @param index 
   * @param item 
   * @return a new instance of TensorListSetItem
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorListSetItem create(Scope scope, Operand<?> inputHandle, Operand<TInt32> index, Operand<T> item) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListSetItem", scope.makeOpName("TensorListSetItem"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.addInput(item.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorListSetItem(opBuilder.build());
  }
  
  /**
   */
  public Output<?> outputHandle() {
    return outputHandle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) outputHandle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorListSetItem";
  
  private Output<?> outputHandle;
  
  private TensorListSetItem(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }
}
