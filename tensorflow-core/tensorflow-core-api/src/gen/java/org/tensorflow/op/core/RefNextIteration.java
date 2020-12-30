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
 * Makes its input available to the next iteration.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class RefNextIteration<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new RefNextIteration operation.
   * 
   * @param scope current scope
   * @param data The tensor to be made available to the next iteration.
   * @return a new instance of RefNextIteration
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> RefNextIteration<T> create(Scope scope, Operand<T> data) {
    OperationBuilder opBuilder = scope.env().opBuilder("RefNextIteration", scope.makeOpName("RefNextIteration"));
    opBuilder.addInput(data.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RefNextIteration<T>(opBuilder.build());
  }
  
  /**
   * The same tensor as `data`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RefNextIteration";
  
  private Output<T> output;
  
  private RefNextIteration(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
