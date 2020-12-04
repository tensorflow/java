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

package org.tensorflow.op.tpu;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * A placeholder op for a value that will be fed into the computation.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class InfeedDequeue<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new InfeedDequeue operation.
   * 
   * @param scope current scope
   * @param dtype The type of elements in the tensor.
   * @param shape The shape of the tensor.
   * @return a new instance of InfeedDequeue
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> InfeedDequeue<T> create(Scope scope, DataType<T> dtype, Shape shape) {
    OperationBuilder opBuilder = scope.env().opBuilder("InfeedDequeue", scope.makeOpName("InfeedDequeue"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
    opBuilder.setAttr("shape", shape);
    return new InfeedDequeue<T>(opBuilder.build());
  }
  
  /**
   * A tensor that will be provided using the infeed mechanism.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "InfeedDequeue";
  
  private Output<T> output;
  
  private InfeedDequeue(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
