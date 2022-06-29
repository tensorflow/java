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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Converts a SparseTensor to a (possibly batched) CSRSparseMatrix.
 */
@OpMetadata(
    opType = SparseTensorToCSRSparseMatrix.OP_NAME,
    inputsClass = SparseTensorToCSRSparseMatrix.Inputs.class
)
public final class SparseTensorToCSRSparseMatrix extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseTensorToCSRSparseMatrix";

  private Output<? extends TType> sparseMatrix;

  @SuppressWarnings("unchecked")
  public SparseTensorToCSRSparseMatrix(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sparseMatrix = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseTensorToCSRSparseMatrix operation.
   *
   * @param scope current scope
   * @param indices SparseTensor indices.
   * @param values SparseTensor values.
   * @param denseShape SparseTensor dense shape.
   * @return a new instance of SparseTensorToCSRSparseMatrix
   */
  @Endpoint(
      describeByClass = true
  )
  public static SparseTensorToCSRSparseMatrix create(Scope scope, Operand<TInt64> indices,
      Operand<? extends TType> values, Operand<TInt64> denseShape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseTensorToCSRSparseMatrix");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(denseShape.asOutput());
    return new SparseTensorToCSRSparseMatrix(opBuilder.build());
  }

  /**
   * Gets sparseMatrix.
   * A (possibly batched) CSRSparseMatrix.
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

  @OpInputsMetadata(
      outputsClass = SparseTensorToCSRSparseMatrix.class
  )
  public static class Inputs extends RawOpInputs<SparseTensorToCSRSparseMatrix> {
    /**
     * SparseTensor indices.
     */
    public final Operand<TInt64> indices;

    /**
     * SparseTensor values.
     */
    public final Operand<? extends TType> values;

    /**
     * SparseTensor dense shape.
     */
    public final Operand<TInt64> denseShape;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseTensorToCSRSparseMatrix(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      indices = (Operand<TInt64>) op.input(inputIndex++);
      values = (Operand<? extends TType>) op.input(inputIndex++);
      denseShape = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
