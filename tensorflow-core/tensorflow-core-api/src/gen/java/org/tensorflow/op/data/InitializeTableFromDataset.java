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
import org.tensorflow.types.family.TType;

/**
 * The InitializeTableFromDataset operation
 */
public final class InitializeTableFromDataset extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InitializeTableFromDataset";

  private InitializeTableFromDataset(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new InitializeTableFromDataset operation.
   *
   * @param scope current scope
   * @param tableHandle the tableHandle value
   * @param dataset the dataset value
   * @return a new instance of InitializeTableFromDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static InitializeTableFromDataset create(Scope scope, Operand<? extends TType> tableHandle,
      Operand<? extends TType> dataset) {
    OperationBuilder opBuilder = scope.env().opBuilder("InitializeTableFromDataset", scope.makeOpName("InitializeTableFromDataset"));
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.addInput(dataset.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new InitializeTableFromDataset(opBuilder.build());
  }
}
