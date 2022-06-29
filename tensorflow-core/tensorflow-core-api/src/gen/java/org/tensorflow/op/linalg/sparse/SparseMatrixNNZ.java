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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Returns the number of nonzeroes of {@code sparse_matrix}.
 */
@OpMetadata(
    opType = SparseMatrixNNZ.OP_NAME,
    inputsClass = SparseMatrixNNZ.Inputs.class
)
public final class SparseMatrixNNZ extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixNNZ";

  private Output<TInt32> nnz;

  public SparseMatrixNNZ(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    nnz = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixNNZ operation.
   *
   * @param scope current scope
   * @param sparseMatrix A CSRSparseMatrix.
   * @return a new instance of SparseMatrixNNZ
   */
  @Endpoint(
      describeByClass = true
  )
  public static SparseMatrixNNZ create(Scope scope, Operand<? extends TType> sparseMatrix) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseMatrixNNZ");
    opBuilder.addInput(sparseMatrix.asOutput());
    return new SparseMatrixNNZ(opBuilder.build());
  }

  /**
   * Gets nnz.
   * The number of nonzeroes of {@code sparse_matrix}.
   * @return nnz.
   */
  public Output<TInt32> nnz() {
    return nnz;
  }

  @Override
  public Output<TInt32> asOutput() {
    return nnz;
  }

  @OpInputsMetadata(
      outputsClass = SparseMatrixNNZ.class
  )
  public static class Inputs extends RawOpInputs<SparseMatrixNNZ> {
    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> sparseMatrix;

    public Inputs(GraphOperation op) {
      super(new SparseMatrixNNZ(op), op, Arrays.asList());
      int inputIndex = 0;
      sparseMatrix = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
