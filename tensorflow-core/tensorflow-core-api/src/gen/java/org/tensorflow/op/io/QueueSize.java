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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;

/**
 * Computes the number of elements in the given queue.
 */
@Operator(group = "io")
public final class QueueSize extends RawOp implements Operand<TInt32> {
  
  /**
   * Factory method to create a class wrapping a new QueueSize operation.
   * 
   * @param scope current scope
   * @param handle The handle to a queue.
   * @return a new instance of QueueSize
   */
  @Endpoint(describeByClass = true)
  public static QueueSize create(Scope scope, Operand<?> handle) {
    OperationBuilder opBuilder = scope.env().opBuilder("QueueSizeV2", scope.makeOpName("QueueSize"));
    opBuilder.addInput(handle.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new QueueSize(opBuilder.build());
  }
  
  /**
   * The number of elements in the given queue.
   */
  public Output<TInt32> output() {
    return output;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QueueSizeV2";
  
  private Output<TInt32> output;
  
  private QueueSize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
