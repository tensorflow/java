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
 * Scatters tensor at indices in an input list.
 * <p>
 * Each member of the TensorList corresponds to one row of the input tensor,
 * specified by the given index (see `tf.gather`).
 * <p>
 * input_handle: The list to scatter into.
 * tensor: The input tensor.
 * indices: The indices used to index into the list.
 * output_handle: The TensorList.
 */
@Operator
public final class TensorListScatterIntoExistingList extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new TensorListScatterIntoExistingList operation.
   * 
   * @param scope current scope
   * @param inputHandle 
   * @param tensor 
   * @param indices 
   * @return a new instance of TensorListScatterIntoExistingList
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorListScatterIntoExistingList create(Scope scope, Operand<?> inputHandle, Operand<T> tensor, Operand<TInt32> indices) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListScatterIntoExistingList", scope.makeOpName("TensorListScatterIntoExistingList"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorListScatterIntoExistingList(opBuilder.build());
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
  public static final String OP_NAME = "TensorListScatterIntoExistingList";
  
  private Output<?> outputHandle;
  
  private TensorListScatterIntoExistingList(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }
}
