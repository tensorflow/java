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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Calculates the softmax of a CSRSparseMatrix.
 * <p>
 * Calculate the softmax of the innermost dimensions of a SparseMatrix.
 * <p>
 * Missing values are treated as `-inf` (i.e., logits of zero probability); and
 * the output has the same sparsity structure as the input (though missing values
 * in the output may now be treated as having probability zero).
 */
public final class SparseMatrixSoftmax extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new SparseMatrixSoftmax operation.
   * 
   * @param scope current scope
   * @param logits A CSRSparseMatrix.
   * @param type 
   * @return a new instance of SparseMatrixSoftmax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> SparseMatrixSoftmax create(Scope scope, Operand<?> logits, DataType<T> type) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseMatrixSoftmax", scope.makeOpName("SparseMatrixSoftmax"));
    opBuilder.addInput(logits.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("type", type);
    return new SparseMatrixSoftmax(opBuilder.build());
  }
  
  /**
   * A CSRSparseMatrix.
   */
  public Output<?> softmax() {
    return softmax;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) softmax;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseMatrixSoftmax";
  
  private Output<?> softmax;
  
  private SparseMatrixSoftmax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    softmax = operation.output(outputIdx++);
  }
}
