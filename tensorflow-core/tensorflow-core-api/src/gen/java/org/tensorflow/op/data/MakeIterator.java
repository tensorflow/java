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
 * Makes a new iterator from the given `dataset` and stores it in `iterator`.
 * <p>
 * This operation may be executed multiple times. Each execution will reset the
 * iterator in `iterator` to the first element of `dataset`.
 */
@Operator(group = "data")
public final class MakeIterator extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new MakeIterator operation.
   * 
   * @param scope current scope
   * @param dataset 
   * @param iterator 
   * @return a new instance of MakeIterator
   */
  @Endpoint(describeByClass = true)
  public static MakeIterator create(Scope scope, Operand<?> dataset, Operand<?> iterator) {
    OperationBuilder opBuilder = scope.env().opBuilder("MakeIterator", scope.makeOpName("MakeIterator"));
    opBuilder.addInput(dataset.asOutput());
    opBuilder.addInput(iterator.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new MakeIterator(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MakeIterator";
  
  private MakeIterator(Operation operation) {
    super(operation);
  }
}
