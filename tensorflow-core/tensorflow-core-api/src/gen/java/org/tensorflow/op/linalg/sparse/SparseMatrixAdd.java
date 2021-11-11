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
 * Sparse addition of two CSR matrices, C = alpha * A + beta * B.
 * The gradients of SparseMatrixAdd outputs with respect to alpha and beta are not
 * currently defined (TensorFlow will return zeros for these entries).
 */
@OpMetadata(
    opType = SparseMatrixAdd.OP_NAME,
    inputsClass = SparseMatrixAdd.Inputs.class
)
public final class SparseMatrixAdd extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixAdd";

  private Output<? extends TType> c;

  @SuppressWarnings("unchecked")
  public SparseMatrixAdd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    c = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixAdd operation.
   *
   * @param scope current scope
   * @param a A CSRSparseMatrix.
   * @param b A CSRSparseMatrix.
   * @param alpha A constant scalar.
   * @param beta A constant scalar.
   * @param <T> data type for {@code SparseMatrixAdd} output and operands
   * @return a new instance of SparseMatrixAdd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseMatrixAdd create(Scope scope, Operand<? extends TType> a,
      Operand<? extends TType> b, Operand<T> alpha, Operand<T> beta) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseMatrixAdd");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.addInput(alpha.asOutput());
    opBuilder.addInput(beta.asOutput());
    return new SparseMatrixAdd(opBuilder.build());
  }

  /**
   * Gets c.
   * A CSRSparseMatrix.
   * @return c.
   */
  public Output<? extends TType> c() {
    return c;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) c;
  }

  @OpInputsMetadata(
      outputsClass = SparseMatrixAdd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseMatrixAdd> {
    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> a;

    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> b;

    /**
     * A constant scalar.
     */
    public final Operand<T> alpha;

    /**
     * A constant scalar.
     */
    public final Operand<T> beta;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseMatrixAdd(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      a = (Operand<? extends TType>) op.input(inputIndex++);
      b = (Operand<? extends TType>) op.input(inputIndex++);
      alpha = (Operand<T>) op.input(inputIndex++);
      beta = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
