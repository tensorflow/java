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

package org.tensorflow.op.linalg;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the LU decomposition of one or more square matrices.
 * The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
 * form square matrices.
 * <p>The input has to be invertible.
 * <p>The output consists of two tensors LU and P containing the LU decomposition
 * of all input submatrices {@code [..., :, :]}. LU encodes the lower triangular and
 * upper triangular factors.
 * <p>For each input submatrix of shape {@code [M, M]}, L is a lower triangular matrix of
 * shape {@code [M, M]} with unit diagonal whose entries correspond to the strictly lower
 * triangular part of LU. U is a upper triangular matrix of shape {@code [M, M]} whose
 * entries correspond to the upper triangular part, including the diagonal, of LU.
 * <p>P represents a permutation matrix encoded as a list of indices each between {@code 0}
 * and {@code M-1}, inclusive. If P_mat denotes the permutation matrix corresponding to
 * P, then the L, U and P satisfies P_mat * input = L * U.
 *
 * @param <T> data type for {@code lu} output
 *
 * @param <U> data type for {@code p} output
 */
@OpMetadata(
    opType = Lu.OP_NAME,
    inputsClass = Lu.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class Lu<T extends TType, U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Lu";

  private Output<T> lu;

  private Output<U> p;

  public Lu(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    lu = operation.output(outputIdx++);
    p = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Lu operation.
   *
   * @param scope current scope
   * @param input A tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions form matrices of
   * size {@code [M, M]}.
   * @param outputIdxType The value of the outputIdxType attribute
   * @param <T> data type for {@code Lu} output and operands
   * @param <U> data type for {@code Lu} output and operands
   * @return a new instance of Lu
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> Lu<T, U> create(Scope scope, Operand<T> input,
      Class<U> outputIdxType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Lu");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("output_idx_type", Operands.toDataType(outputIdxType));
    return new Lu<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new Lu operation, with the default output types.
   *
   * @param scope current scope
   * @param input A tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions form matrices of
   * size {@code [M, M]}.
   * @param <T> data type for {@code Lu} output and operands
   * @return a new instance of Lu, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Lu<T, TInt32> create(Scope scope, Operand<T> input) {
    return create(scope, input, TInt32.class);
  }

  /**
   * Gets lu.
   * A tensor of shape {@code [..., M, M]} whose strictly lower triangular part denotes the
   * lower triangular factor {@code L} with unit diagonal, and whose upper triangular part
   * denotes the upper triangular factor {@code U}.
   * @return lu.
   */
  public Output<T> lu() {
    return lu;
  }

  /**
   * Gets p.
   * Permutation of the rows encoded as a list of indices in {@code 0..M-1}. Shape is
   * {@code [..., M]}.
   * {@literal @}compatibility(scipy)<br>
   * Similar to {@code scipy.linalg.lu}, except the triangular factors {@code L} and {@code U} are
   * packed into a single tensor, the permutation is applied to {@code input} instead of
   * the right hand side and the permutation {@code P} is returned as a list of indices
   * instead of a permutation matrix.
   * <br>{@literal @}end_compatibility
   * @return p.
   */
  public Output<U> p() {
    return p;
  }

  @OpInputsMetadata(
      outputsClass = Lu.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Lu<T, ?>> {
    /**
     * A tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions form matrices of
     * size {@code [M, M]}.
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The outputIdxType attribute
     */
    public final DataType outputIdxType;

    public Inputs(GraphOperation op) {
      super(new Lu<>(op), op, Arrays.asList("T", "output_idx_type"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      outputIdxType = op.attributes().getAttrType("output_idx_type");
    }
  }
}
