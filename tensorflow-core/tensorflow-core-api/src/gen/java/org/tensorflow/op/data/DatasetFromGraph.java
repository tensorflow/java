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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset from the given `graph_def`.
 * <p>
 * Creates a dataset from the provided `graph_def`.
 */
public final class DatasetFromGraph extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new DatasetFromGraph operation.
   * 
   * @param scope current scope
   * @param graphDef The graph representation of the dataset (as serialized GraphDef).
   * @return a new instance of DatasetFromGraph
   */
  @Endpoint(describeByClass = true)
  public static DatasetFromGraph create(Scope scope, Operand<TString> graphDef) {
    OperationBuilder opBuilder = scope.env().opBuilder("DatasetFromGraph", scope.makeOpName("DatasetFromGraph"));
    opBuilder.addInput(graphDef.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DatasetFromGraph(opBuilder.build());
  }
  
  /**
   * A variant tensor representing the dataset.
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DatasetFromGraph";
  
  private Output<?> handle;
  
  private DatasetFromGraph(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
