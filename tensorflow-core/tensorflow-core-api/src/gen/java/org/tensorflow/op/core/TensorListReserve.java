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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * List of the given size with empty elements.
 * <p>
 * element_shape: the shape of the future elements of the list
 * num_elements: the number of elements to reserve
 * handle: the output list
 * element_dtype: the desired type of elements in the list.
 */
@Operator
public final class TensorListReserve extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new TensorListReserve operation.
   * 
   * @param scope current scope
   * @param elementShape 
   * @param numElements 
   * @param elementDtype 
   * @return a new instance of TensorListReserve
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TType> TensorListReserve create(Scope scope, Operand<T> elementShape, Operand<TInt32> numElements, DataType<U> elementDtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListReserve", scope.makeOpName("TensorListReserve"));
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.addInput(numElements.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("element_dtype", elementDtype);
    return new TensorListReserve(opBuilder.build());
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorListReserve";
  
  private Output<?> handle;
  
  private TensorListReserve(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
