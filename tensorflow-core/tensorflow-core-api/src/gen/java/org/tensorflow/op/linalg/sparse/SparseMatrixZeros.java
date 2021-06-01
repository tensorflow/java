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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates an all-zeros CSRSparseMatrix with shape {@code dense_shape}.
 */
public final class SparseMatrixZeros extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixZeros";

  private Output<? extends TType> sparseMatrix;

  @SuppressWarnings("unchecked")
  private SparseMatrixZeros(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseMatrix = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixZeros operation.
   *
   * @param scope current scope
   * @param denseShape The desired matrix shape.
   * @param type the value of the type property
   * @param <T> data type for {@code SparseMatrixZeros} output and operands
   * @return a new instance of SparseMatrixZeros
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseMatrixZeros create(Scope scope, Operand<TInt64> denseShape,
      Class<T> type) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("SparseMatrixZeros"));
    opBuilder.addInput(denseShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("type", Operands.toDataType(type));
    return new SparseMatrixZeros(opBuilder.build());
  }

  /**
   * Gets sparseMatrix.
   * An empty CSR matrix with shape {@code dense_shape}.
   * @return sparseMatrix.
   */
  public Output<? extends TType> sparseMatrix() {
    return sparseMatrix;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) sparseMatrix;
  }
}
