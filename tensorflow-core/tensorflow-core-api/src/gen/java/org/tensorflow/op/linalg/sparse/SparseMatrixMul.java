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
import org.tensorflow.types.family.TType;

/**
 * Element-wise multiplication of a sparse matrix with a dense tensor.
 * <p>
 * Returns a sparse matrix.
 * <p>
 * The dense tensor `b` may be either a scalar; otherwise `a` must be a rank-3
 * `SparseMatrix`; in this case `b` must be shaped `[batch_size, 1, 1]` and the
 * multiply operation broadcasts.
 * <p>
 * <b>NOTE</b> even if `b` is zero, the sparsity structure of the output does not
 * change.
 */
public final class SparseMatrixMul extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new SparseMatrixMul operation.
   * 
   * @param scope current scope
   * @param a A CSRSparseMatrix.
   * @param b A dense tensor.
   * @return a new instance of SparseMatrixMul
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseMatrixMul create(Scope scope, Operand<?> a, Operand<T> b) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseMatrixMul", scope.makeOpName("SparseMatrixMul"));
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseMatrixMul(opBuilder.build());
  }
  
  /**
   * A dense output tensor.
   */
  public Output<?> output() {
    return output;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseMatrixMul";
  
  private Output<?> output;
  
  private SparseMatrixMul(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
