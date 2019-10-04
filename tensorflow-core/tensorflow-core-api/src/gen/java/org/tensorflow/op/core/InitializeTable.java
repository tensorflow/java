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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Table initializer that takes two tensors for keys and values respectively.
 */
@Operator
public final class InitializeTable extends PrimitiveOp {
  
  /**
   * Factory method to create a class wrapping a new InitializeTable operation.
   * 
   * @param scope current scope
   * @param tableHandle Handle to a table which will be initialized.
   * @param keys Keys of type Tkey.
   * @param values Values of type Tval.
   * @return a new instance of InitializeTable
   */
  public static <T, U> InitializeTable create(Scope scope, Operand<?> tableHandle, Operand<T> keys, Operand<U> values) {
    OperationBuilder opBuilder = scope.env().opBuilder("InitializeTableV2", scope.makeOpName("InitializeTable"));
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.addInput(keys.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new InitializeTable(opBuilder.build());
  }
  
  
  private InitializeTable(Operation operation) {
    super(operation);
  }
}
