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
 * Converts a (possibly batched) CSRSparesMatrix to a SparseTensor.
 *
 * @param <T> data type for {@code values} output
 */
public final class CSRSparseMatrixToSparseTensor<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CSRSparseMatrixToSparseTensor";

  private Output<TInt64> indices;

  private Output<T> values;

  private Output<TInt64> denseShape;

  private CSRSparseMatrixToSparseTensor(Operation operation) {
    super(operation);
    int outputIdx = 0;
    indices = operation.output(outputIdx++);
    values = operation.output(outputIdx++);
    denseShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CSRSparseMatrixToSparseTensor operation.
   *
   * @param scope current scope
   * @param sparseMatrix A (possibly batched) CSRSparseMatrix.
   * @param type the value of the type property
   * @param <T> data type for {@code CSRSparseMatrixToSparseTensor} output and operands
   * @return a new instance of CSRSparseMatrixToSparseTensor
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> CSRSparseMatrixToSparseTensor<T> create(Scope scope,
      Operand<? extends TType> sparseMatrix, Class<T> type) {
    OperationBuilder opBuilder = scope.env().opBuilder("CSRSparseMatrixToSparseTensor", scope.makeOpName("CSRSparseMatrixToSparseTensor"));
    opBuilder.addInput(sparseMatrix.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("type", Operands.toDataType(type));
    return new CSRSparseMatrixToSparseTensor<>(opBuilder.build());
  }

  /**
   * Gets indices.
   * SparseTensor indices.
   * @return indices.
   */
  public Output<TInt64> indices() {
    return indices;
  }

  /**
   * Gets values.
   * SparseTensor values.
   * @return values.
   */
  public Output<T> values() {
    return values;
  }

  /**
   * Gets denseShape.
   * SparseTensor dense shape.
   * @return denseShape.
   */
  public Output<TInt64> denseShape() {
    return denseShape;
  }
}
