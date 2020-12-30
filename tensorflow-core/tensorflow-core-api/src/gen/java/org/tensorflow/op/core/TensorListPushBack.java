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
import org.tensorflow.types.family.TType;

/**
 * Returns a list which has the passed-in `Tensor` as last element and the other elements of the given list in `input_handle`.
 * <p>
 * tensor: The tensor to put on the list.
 * input_handle: The old list.
 * output_handle: A list with the elements of the old list followed by tensor.
 * element_dtype: the type of elements in the list.
 * element_shape: a shape compatible with that of elements in the list.
 */
@Operator
public final class TensorListPushBack extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new TensorListPushBack operation.
   * 
   * @param scope current scope
   * @param inputHandle 
   * @param tensor 
   * @return a new instance of TensorListPushBack
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorListPushBack create(Scope scope, Operand<?> inputHandle, Operand<T> tensor) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListPushBack", scope.makeOpName("TensorListPushBack"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorListPushBack(opBuilder.build());
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
  public static final String OP_NAME = "TensorListPushBack";
  
  private Output<?> outputHandle;
  
  private TensorListPushBack(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }
}
