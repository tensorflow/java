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

package org.tensorflow.op.data;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * Converts the given variant tensor to an iterator and stores it in the given resource.
 */
@Operator(group = "data")
public final class DeserializeIterator extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new DeserializeIterator operation.
   * 
   * @param scope current scope
   * @param resourceHandle A handle to an iterator resource.
   * @param serialized A variant tensor storing the state of the iterator contained in the
   * resource.
   * @return a new instance of DeserializeIterator
   */
  @Endpoint(describeByClass = true)
  public static DeserializeIterator create(Scope scope, Operand<?> resourceHandle, Operand<?> serialized) {
    OperationBuilder opBuilder = scope.env().opBuilder("DeserializeIterator", scope.makeOpName("DeserializeIterator"));
    opBuilder.addInput(resourceHandle.asOutput());
    opBuilder.addInput(serialized.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DeserializeIterator(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DeserializeIterator";
  
  private DeserializeIterator(Operation operation) {
    super(operation);
  }
}
