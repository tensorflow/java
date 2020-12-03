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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Creates a TensorList which, when stacked, has the value of `tensor`.
 * <p>
 * Each tensor in the result list corresponds to one row of the input tensor.
 * <p>
 * tensor: The input tensor.
 * output_handle: The list.
 */
@Operator
public final class TensorListFromTensor extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new TensorListFromTensor operation.
   * 
   * @param scope current scope
   * @param tensor 
   * @param elementShape 
   * @return a new instance of TensorListFromTensor
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> TensorListFromTensor create(Scope scope, Operand<T> tensor, Operand<U> elementShape) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListFromTensor", scope.makeOpName("TensorListFromTensor"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorListFromTensor(opBuilder.build());
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
  public static final String OP_NAME = "TensorListFromTensor";
  
  private Output<?> outputHandle;
  
  private TensorListFromTensor(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }
}
