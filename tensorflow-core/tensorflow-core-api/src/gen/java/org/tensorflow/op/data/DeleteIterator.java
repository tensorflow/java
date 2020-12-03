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
 * A container for an iterator resource.
 */
@Operator(group = "data")
public final class DeleteIterator extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new DeleteIterator operation.
   * 
   * @param scope current scope
   * @param handle A handle to the iterator to delete.
   * @param deleter A variant deleter.
   * @return a new instance of DeleteIterator
   */
  @Endpoint(describeByClass = true)
  public static DeleteIterator create(Scope scope, Operand<?> handle, Operand<?> deleter) {
    OperationBuilder opBuilder = scope.env().opBuilder("DeleteIterator", scope.makeOpName("DeleteIterator"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(deleter.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DeleteIterator(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DeleteIterator";
  
  private DeleteIterator(Operation operation) {
    super(operation);
  }
}
