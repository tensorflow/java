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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the LU decomposition of one or more square matrices.
 * <p>
 * The input is a tensor of shape `[..., M, M]` whose inner-most 2 dimensions
 * form square matrices.
 * <p>
 * The input has to be invertible.
 * <p>
 * The output consists of two tensors LU and P containing the LU decomposition
 * of all input submatrices `[..., :, :]`. LU encodes the lower triangular and
 * upper triangular factors.
 * <p>
 * For each input submatrix of shape `[M, M]`, L is a lower triangular matrix of
 * shape `[M, M]` with unit diagonal whose entries correspond to the strictly lower
 * triangular part of LU. U is a upper triangular matrix of shape `[M, M]` whose
 * entries correspond to the upper triangular part, including the diagonal, of LU.
 * <p>
 * P represents a permutation matrix encoded as a list of indices each between `0`
 * and `M-1`, inclusive. If P_mat denotes the permutation matrix corresponding to
 * P, then the L, U and P satisfies P_mat * input = L * U.
 * 
 * @param <T> data type for {@code lu()} output
 * @param <U> data type for {@code p()} output
 */
@Operator(group = "linalg")
public final class Lu<T extends TType, U extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new Lu operation.
   * 
   * @param scope current scope
   * @param input A tensor of shape `[..., M, M]` whose inner-most 2 dimensions form matrices of
   * size `[M, M]`.
   * @param outputIdxType 
   * @return a new instance of Lu
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> Lu<T, U> create(Scope scope, Operand<T> input, DataType<U> outputIdxType) {
    OperationBuilder opBuilder = scope.env().opBuilder("Lu", scope.makeOpName("Lu"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_idx_type", outputIdxType);
    return new Lu<T, U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new Lu operation using default output types.
   * 
   * @param scope current scope
   * @param input A tensor of shape `[..., M, M]` whose inner-most 2 dimensions form matrices of
   * size `[M, M]`.
   * @return a new instance of Lu
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Lu<T, TInt32> create(Scope scope, Operand<T> input) {
    return create(scope, input, TInt32.DTYPE);
  }
  
  /**
   * A tensor of shape `[..., M, M]` whose strictly lower triangular part denotes the
   * lower triangular factor `L` with unit diagonal, and whose upper triangular part
   * denotes the upper triangular factor `U`.
   */
  public Output<T> lu() {
    return lu;
  }
  
  /**
   * Permutation of the rows encoded as a list of indices in `0..M-1`. Shape is
   * `[..., M]`.
   * @compatibility(scipy)
   * Similar to `scipy.linalg.lu`, except the triangular factors `L` and `U` are
   * packed into a single tensor, the permutation is applied to `input` instead of
   * the right hand side and the permutation `P` is returned as a list of indices
   * instead of a permutation matrix.
   * @end_compatibility
   */
  public Output<U> p() {
    return p;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Lu";
  
  private Output<T> lu;
  private Output<U> p;
  
  private Lu(Operation operation) {
    super(operation);
    int outputIdx = 0;
    lu = operation.output(outputIdx++);
    p = operation.output(outputIdx++);
  }
}
