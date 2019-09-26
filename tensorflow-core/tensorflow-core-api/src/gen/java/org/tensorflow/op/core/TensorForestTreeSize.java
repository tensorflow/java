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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

/**
 * Get the number of nodes in a tree
 */
public final class TensorForestTreeSize extends PrimitiveOp implements Operand<Integer> {
  
  /**
   * Factory method to create a class wrapping a new TensorForestTreeSize operation.
   * 
   * @param scope current scope
   * @param treeHandle Handle to the tree resource.
   * @return a new instance of TensorForestTreeSize
   */
  public static TensorForestTreeSize create(Scope scope, Operand<?> treeHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorForestTreeSize", scope.makeOpName("TensorForestTreeSize"));
    opBuilder.addInput(treeHandle.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new TensorForestTreeSize(opBuilder.build());
  }
  
  /**
   * The size of the tree.
   */
  public Output<Integer> treeSize() {
    return treeSize;
  }
  
  @Override
  public Output<Integer> asOutput() {
    return treeSize;
  }
  
  private Output<Integer> treeSize;
  
  private TensorForestTreeSize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    treeSize = operation.output(outputIdx++);
  }
}
