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

package org.tensorflow.op.estimator;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TBool;

/**
 * Checks whether a tree ensemble has been initialized.
 */
public final class IsBoostedTreesEnsembleInitialized extends PrimitiveOp implements Operand<TBool> {
  
  /**
   * Factory method to create a class wrapping a new IsBoostedTreesEnsembleInitialized operation.
   * 
   * @param scope current scope
   * @param treeEnsembleHandle Handle to the tree ensemble resouce.
   * @return a new instance of IsBoostedTreesEnsembleInitialized
   */
  public static IsBoostedTreesEnsembleInitialized create(Scope scope, Operand<?> treeEnsembleHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("IsBoostedTreesEnsembleInitialized", scope.makeOpName("IsBoostedTreesEnsembleInitialized"));
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new IsBoostedTreesEnsembleInitialized(opBuilder.build());
  }
  
  /**
   * output boolean on whether it is initialized or not.
   */
  public Output<TBool> isInitialized() {
    return isInitialized;
  }
  
  @Override
  public Output<TBool> asOutput() {
    return isInitialized;
  }
  
  private Output<TBool> isInitialized;
  
  private IsBoostedTreesEnsembleInitialized(Operation operation) {
    super(operation);
    int outputIdx = 0;
    isInitialized = operation.output(outputIdx++);
  }
}
