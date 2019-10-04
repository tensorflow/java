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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Transforms a Tensor into a serialized TensorProto proto.
 */
@Operator(group = "io")
public final class SerializeTensor extends PrimitiveOp implements Operand<String> {
  
  /**
   * Factory method to create a class wrapping a new SerializeTensor operation.
   * 
   * @param scope current scope
   * @param tensor A Tensor of type `T`.
   * @return a new instance of SerializeTensor
   */
  public static <T> SerializeTensor create(Scope scope, Operand<T> tensor) {
    OperationBuilder opBuilder = scope.env().opBuilder("SerializeTensor", scope.makeOpName("SerializeTensor"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new SerializeTensor(opBuilder.build());
  }
  
  /**
   * A serialized TensorProto proto of the input tensor.
   */
  public Output<String> serialized() {
    return serialized;
  }
  
  @Override
  public Output<String> asOutput() {
    return serialized;
  }
  
  private Output<String> serialized;
  
  private SerializeTensor(Operation operation) {
    super(operation);
    int outputIdx = 0;
    serialized = operation.output(outputIdx++);
  }
}
