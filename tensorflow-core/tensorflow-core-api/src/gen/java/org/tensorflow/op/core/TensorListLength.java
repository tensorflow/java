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

/**
 * Returns the number of tensors in the input tensor list.
 * <p>
 * input_handle: the input list
 * length: the number of tensors in the list
 */
@Operator
public final class TensorListLength extends RawOp implements Operand<TInt32> {
  
  /**
   * Factory method to create a class wrapping a new TensorListLength operation.
   * 
   * @param scope current scope
   * @param inputHandle 
   * @return a new instance of TensorListLength
   */
  @Endpoint(describeByClass = true)
  public static TensorListLength create(Scope scope, Operand<?> inputHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListLength", scope.makeOpName("TensorListLength"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorListLength(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt32> length() {
    return length;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return length;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorListLength";
  
  private Output<TInt32> length;
  
  private TensorListLength(Operation operation) {
    super(operation);
    int outputIdx = 0;
    length = operation.output(outputIdx++);
  }
}
