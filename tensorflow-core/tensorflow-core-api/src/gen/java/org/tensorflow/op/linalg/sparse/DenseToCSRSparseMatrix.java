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
 * Converts a dense tensor to a (possibly batched) CSRSparseMatrix.
 */
public final class DenseToCSRSparseMatrix extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new DenseToCSRSparseMatrix operation.
   * 
   * @param scope current scope
   * @param denseInput A Dense tensor.
   * @param indices Indices of nonzero elements.
   * @return a new instance of DenseToCSRSparseMatrix
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> DenseToCSRSparseMatrix create(Scope scope, Operand<T> denseInput, Operand<TInt64> indices) {
    OperationBuilder opBuilder = scope.env().opBuilder("DenseToCSRSparseMatrix", scope.makeOpName("DenseToCSRSparseMatrix"));
    opBuilder.addInput(denseInput.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DenseToCSRSparseMatrix(opBuilder.build());
  }
  
  /**
   * A (possibly batched) CSRSparseMatrix.
   */
  public Output<?> sparseOutput() {
    return sparseOutput;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) sparseOutput;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DenseToCSRSparseMatrix";
  
  private Output<?> sparseOutput;
  
  private DenseToCSRSparseMatrix(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseOutput = operation.output(outputIdx++);
  }
}
