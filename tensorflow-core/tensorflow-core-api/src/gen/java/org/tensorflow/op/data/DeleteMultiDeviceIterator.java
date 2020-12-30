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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * A container for an iterator resource.
 */
public final class DeleteMultiDeviceIterator extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new DeleteMultiDeviceIterator operation.
   * 
   * @param scope current scope
   * @param multiDeviceIterator A handle to the multi device iterator to delete.
   * @param iterators A list of iterator handles (unused). This is added so that automatic control dependencies get added during function tracing that ensure this op runs after all the dependent iterators are deleted.
   * @param deleter A variant deleter.
   * @return a new instance of DeleteMultiDeviceIterator
   */
  @Endpoint(describeByClass = true)
  public static DeleteMultiDeviceIterator create(Scope scope, Operand<?> multiDeviceIterator, Iterable<Operand<?>> iterators, Operand<?> deleter) {
    OperationBuilder opBuilder = scope.env().opBuilder("DeleteMultiDeviceIterator", scope.makeOpName("DeleteMultiDeviceIterator"));
    opBuilder.addInput(multiDeviceIterator.asOutput());
    opBuilder.addInputList(Operands.asOutputs(iterators));
    opBuilder.addInput(deleter.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DeleteMultiDeviceIterator(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DeleteMultiDeviceIterator";
  
  private DeleteMultiDeviceIterator(Operation operation) {
    super(operation);
  }
}
