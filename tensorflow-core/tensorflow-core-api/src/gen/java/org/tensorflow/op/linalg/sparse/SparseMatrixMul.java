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
import org.tensorflow.types.family.TType;

/**
 * Element-wise multiplication of a sparse matrix with a dense tensor.
 * Returns a sparse matrix.
 * <p>The dense tensor {@code b} may be either a scalar; otherwise {@code a} must be a rank-3
 * {@code SparseMatrix}; in this case {@code b} must be shaped {@code [batch_size, 1, 1]} and the
 * multiply operation broadcasts.
 * <p><strong>NOTE</strong> even if {@code b} is zero, the sparsity structure of the output does not
 * change.
 */
@OpMetadata(
    opType = SparseMatrixMul.OP_NAME,
    inputsClass = SparseMatrixMul.Inputs.class
)
public final class SparseMatrixMul extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixMul";

  private Output<? extends TType> output;

  @SuppressWarnings("unchecked")
  public SparseMatrixMul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixMul operation.
   *
   * @param scope current scope
   * @param a A CSRSparseMatrix.
   * @param b A dense tensor.
   * @return a new instance of SparseMatrixMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static SparseMatrixMul create(Scope scope, Operand<? extends TType> a,
      Operand<? extends TType> b) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseMatrixMul");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    return new SparseMatrixMul(opBuilder.build());
  }

  /**
   * Gets output.
   * A dense output tensor.
   * @return output.
   */
  public Output<? extends TType> output() {
    return output;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }

  @OpInputsMetadata(
      outputsClass = SparseMatrixMul.class
  )
  public static class Inputs extends RawOpInputs<SparseMatrixMul> {
    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> a;

    /**
     * A dense tensor.
     */
    public final Operand<? extends TType> b;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseMatrixMul(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      a = (Operand<? extends TType>) op.input(inputIndex++);
      b = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
