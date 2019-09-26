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
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

/**
 * Returns a serialized GraphDef representing `input_dataset`.
 * <p>
 * Returns a graph representation for `input_dataset`.
 */
public final class DatasetToGraph extends PrimitiveOp implements Operand<String> {
  
  /**
   * Factory method to create a class wrapping a new DatasetToGraph operation.
   * 
   * @param scope current scope
   * @param inputDataset A variant tensor representing the dataset to return the graph representation for.
   * @return a new instance of DatasetToGraph
   */
  public static DatasetToGraph create(Scope scope, Operand<?> inputDataset) {
    OperationBuilder opBuilder = scope.env().opBuilder("DatasetToGraph", scope.makeOpName("DatasetToGraph"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new DatasetToGraph(opBuilder.build());
  }
  
  /**
   * The graph representation of the dataset (as serialized GraphDef).
   */
  public Output<String> graph() {
    return graph;
  }
  
  @Override
  public Output<String> asOutput() {
    return graph;
  }
  
  private Output<String> graph;
  
  private DatasetToGraph(Operation operation) {
    super(operation);
    int outputIdx = 0;
    graph = operation.output(outputIdx++);
  }
}
