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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Reads out the CSR components at batch {@code index}.
 * This op is meant only for debugging / testing, and its interface is not expected
 * to be stable.
 *
 * @param <T> data type for {@code values} output
 */
@OpMetadata(
    opType = CSRSparseMatrixComponents.OP_NAME,
    inputsClass = CSRSparseMatrixComponents.Inputs.class
)
public final class CSRSparseMatrixComponents<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CSRSparseMatrixComponents";

  private Output<TInt32> rowPtrs;

  private Output<TInt32> colInds;

  private Output<T> values;

  public CSRSparseMatrixComponents(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    rowPtrs = operation.output(outputIdx++);
    colInds = operation.output(outputIdx++);
    values = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CSRSparseMatrixComponents operation.
   *
   * @param scope current scope
   * @param csrSparseMatrix A batched CSRSparseMatrix.
   * @param index The index in {@code csr_sparse_matrix}'s batch.
   * @param type The value of the type attribute
   * @param <T> data type for {@code CSRSparseMatrixComponents} output and operands
   * @return a new instance of CSRSparseMatrixComponents
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> CSRSparseMatrixComponents<T> create(Scope scope,
      Operand<? extends TType> csrSparseMatrix, Operand<TInt32> index, Class<T> type) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CSRSparseMatrixComponents");
    opBuilder.addInput(csrSparseMatrix.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.setAttr("type", Operands.toDataType(type));
    return new CSRSparseMatrixComponents<>(opBuilder.build());
  }

  /**
   * Gets rowPtrs.
   * An array containing CSR matrix row pointers.
   * @return rowPtrs.
   */
  public Output<TInt32> rowPtrs() {
    return rowPtrs;
  }

  /**
   * Gets colInds.
   * An array containing CSR matrix column indices.
   * @return colInds.
   */
  public Output<TInt32> colInds() {
    return colInds;
  }

  /**
   * Gets values.
   * An array containing CSR matrix nonzero values.
   * @return values.
   */
  public Output<T> values() {
    return values;
  }

  @OpInputsMetadata(
      outputsClass = CSRSparseMatrixComponents.class
  )
  public static class Inputs extends RawOpInputs<CSRSparseMatrixComponents<?>> {
    /**
     * A batched CSRSparseMatrix.
     */
    public final Operand<? extends TType> csrSparseMatrix;

    /**
     * The index in {@code csr_sparse_matrix}'s batch.
     */
    public final Operand<TInt32> index;

    /**
     * The type attribute
     */
    public final DataType type;

    public Inputs(GraphOperation op) {
      super(new CSRSparseMatrixComponents<>(op), op, Arrays.asList("type"));
      int inputIndex = 0;
      csrSparseMatrix = (Operand<? extends TType>) op.input(inputIndex++);
      index = (Operand<TInt32>) op.input(inputIndex++);
      type = op.attributes().getAttrType("type");
    }
  }
}
