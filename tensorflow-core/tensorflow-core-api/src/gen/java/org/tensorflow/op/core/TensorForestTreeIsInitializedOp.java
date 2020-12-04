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
import org.tensorflow.types.TBool;

/**
 * Checks whether a tree has been initialized.
 */
public final class TensorForestTreeIsInitializedOp extends RawOp implements Operand<TBool> {
  
  /**
   * Factory method to create a class wrapping a new TensorForestTreeIsInitializedOp operation.
   * 
   * @param scope current scope
   * @param treeHandle Handle to the tree.
   * @return a new instance of TensorForestTreeIsInitializedOp
   */
  @Endpoint(describeByClass = true)
  public static TensorForestTreeIsInitializedOp create(Scope scope, Operand<?> treeHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorForestTreeIsInitializedOp", scope.makeOpName("TensorForestTreeIsInitializedOp"));
    opBuilder.addInput(treeHandle.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorForestTreeIsInitializedOp(opBuilder.build());
  }
  
  /**
   * Whether the tree is initialized.
   */
  public Output<TBool> isInitialized() {
    return isInitialized;
  }
  
  @Override
  public Output<TBool> asOutput() {
    return isInitialized;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorForestTreeIsInitializedOp";
  
  private Output<TBool> isInitialized;
  
  private TensorForestTreeIsInitializedOp(Operation operation) {
    super(operation);
    int outputIdx = 0;
    isInitialized = operation.output(outputIdx++);
  }
}
