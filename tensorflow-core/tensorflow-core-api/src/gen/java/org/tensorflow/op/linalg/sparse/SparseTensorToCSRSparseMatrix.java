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

package org.tensorflow.op.linalg.sparse;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Converts a SparseTensor to a (possibly batched) CSRSparseMatrix.
 */
public final class SparseTensorToCSRSparseMatrix extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new SparseTensorToCSRSparseMatrix operation.
   * 
   * @param scope current scope
   * @param indices SparseTensor indices.
   * @param values SparseTensor values.
   * @param denseShape SparseTensor dense shape.
   * @return a new instance of SparseTensorToCSRSparseMatrix
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseTensorToCSRSparseMatrix create(Scope scope, Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseTensorToCSRSparseMatrix", scope.makeOpName("SparseTensorToCSRSparseMatrix"));
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(denseShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseTensorToCSRSparseMatrix(opBuilder.build());
  }
  
  /**
   * A (possibly batched) CSRSparseMatrix.
   */
  public Output<?> sparseMatrix() {
    return sparseMatrix;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) sparseMatrix;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseTensorToCSRSparseMatrix";
  
  private Output<?> sparseMatrix;
  
  private SparseTensorToCSRSparseMatrix(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseMatrix = operation.output(outputIdx++);
  }
}
