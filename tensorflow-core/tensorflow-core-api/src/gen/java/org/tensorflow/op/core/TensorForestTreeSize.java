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
import org.tensorflow.types.TInt32;

/**
 * Get the number of nodes in a tree
 */
public final class TensorForestTreeSize extends RawOp implements Operand<TInt32> {
  
  /**
   * Factory method to create a class wrapping a new TensorForestTreeSize operation.
   * 
   * @param scope current scope
   * @param treeHandle Handle to the tree resource.
   * @return a new instance of TensorForestTreeSize
   */
  @Endpoint(describeByClass = true)
  public static TensorForestTreeSize create(Scope scope, Operand<?> treeHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorForestTreeSize", scope.makeOpName("TensorForestTreeSize"));
    opBuilder.addInput(treeHandle.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorForestTreeSize(opBuilder.build());
  }
  
  /**
   * The size of the tree.
   */
  public Output<TInt32> treeSize() {
    return treeSize;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return treeSize;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorForestTreeSize";
  
  private Output<TInt32> treeSize;
  
  private TensorForestTreeSize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    treeSize = operation.output(outputIdx++);
  }
}
