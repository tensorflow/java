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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Get the current size of the TensorArray.
 */
@Operator
public final class TensorArraySize extends RawOp implements Operand<TInt32> {
  
  /**
   * Factory method to create a class wrapping a new TensorArraySize operation.
   * 
   * @param scope current scope
   * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArraySize
   */
  @Endpoint(describeByClass = true)
  public static TensorArraySize create(Scope scope, Operand<?> handle, Operand<TFloat32> flowIn) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorArraySizeV3", scope.makeOpName("TensorArraySize"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorArraySize(opBuilder.build());
  }
  
  /**
   * The current size of the TensorArray.
   */
  public Output<TInt32> output() {
    return output;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorArraySizeV3";
  
  private Output<TInt32> output;
  
  private TensorArraySize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
