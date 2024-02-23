/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
 * Converts a dense tensor to a (possibly batched) CSRSparseMatrix.
 */
@OpMetadata(
    opType = DenseToCSRSparseMatrix.OP_NAME,
    inputsClass = DenseToCSRSparseMatrix.Inputs.class
)
public final class DenseToCSRSparseMatrix extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DenseToCSRSparseMatrix";

  private Output<? extends TType> sparseOutput;

  @SuppressWarnings("unchecked")
  public DenseToCSRSparseMatrix(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sparseOutput = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DenseToCSRSparseMatrix operation.
   *
   * @param scope current scope
   * @param denseInput A Dense tensor.
   * @param indices Indices of nonzero elements.
   * @return a new instance of DenseToCSRSparseMatrix
   */
  @Endpoint(
      describeByClass = true
  )
  public static DenseToCSRSparseMatrix create(Scope scope, Operand<? extends TType> denseInput,
      Operand<TInt64> indices) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DenseToCSRSparseMatrix");
    opBuilder.addInput(denseInput.asOutput());
    opBuilder.addInput(indices.asOutput());
    return new DenseToCSRSparseMatrix(opBuilder.build());
  }

  /**
   * Gets sparseOutput.
   * A (possibly batched) CSRSparseMatrix.
   * @return sparseOutput.
   */
  public Output<? extends TType> sparseOutput() {
    return sparseOutput;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) sparseOutput;
  }

  @OpInputsMetadata(
      outputsClass = DenseToCSRSparseMatrix.class
  )
  public static class Inputs extends RawOpInputs<DenseToCSRSparseMatrix> {
    /**
     * A Dense tensor.
     */
    public final Operand<? extends TType> denseInput;

    /**
     * Indices of nonzero elements.
     */
    public final Operand<TInt64> indices;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new DenseToCSRSparseMatrix(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      denseInput = (Operand<? extends TType>) op.input(inputIndex++);
      indices = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
