/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.sparse;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Component-wise multiplies a SparseTensor by a dense Tensor.
 * The output locations corresponding to the implicitly zero elements in the sparse
 * tensor will be zero (i.e., will not take up storage space), regardless of the
 * contents of the dense tensor (even if it's +/-INF and that INF*0 == NaN).
 * <p><em>Limitation</em>: this Op only broadcasts the dense side to the sparse side, but not
 * the other direction.
 */
@OpMetadata(
    opType = SparseDenseCwiseMul.OP_NAME,
    inputsClass = SparseDenseCwiseMul.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseDenseCwiseMul<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseDenseCwiseMul";

  private Output<T> output;

  public SparseDenseCwiseMul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseDenseCwiseMul operation.
   *
   * @param scope current scope
   * @param spIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   * SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  {@code N} non-empty values corresponding to {@code sp_indices}.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense {@code R}-D.  The dense Tensor operand.
   * @param <T> data type for {@code SparseDenseCwiseMul} output and operands
   * @return a new instance of SparseDenseCwiseMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseDenseCwiseMul<T> create(Scope scope,
      Operand<TInt64> spIndices, Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseDenseCwiseMul");
    opBuilder.addInput(spIndices.asOutput());
    opBuilder.addInput(spValues.asOutput());
    opBuilder.addInput(spShape.asOutput());
    opBuilder.addInput(dense.asOutput());
    return new SparseDenseCwiseMul<>(opBuilder.build());
  }

  /**
   * Gets output.
   * 1-D.  The {@code N} values that are operated on.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = SparseDenseCwiseMul.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseDenseCwiseMul<T>> {
    /**
     * 2-D.  {@code N x R} matrix with the indices of non-empty values in a
     * SparseTensor, possibly not in canonical ordering.
     */
    public final Operand<TInt64> spIndices;

    /**
     * 1-D.  {@code N} non-empty values corresponding to {@code sp_indices}.
     */
    public final Operand<T> spValues;

    /**
     * 1-D.  Shape of the input SparseTensor.
     */
    public final Operand<TInt64> spShape;

    /**
     * {@code R}-D.  The dense Tensor operand.
     */
    public final Operand<T> dense;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseDenseCwiseMul<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      spIndices = (Operand<TInt64>) op.input(inputIndex++);
      spValues = (Operand<T>) op.input(inputIndex++);
      spShape = (Operand<TInt64>) op.input(inputIndex++);
      dense = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
