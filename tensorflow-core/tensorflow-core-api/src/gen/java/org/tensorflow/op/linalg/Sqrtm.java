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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Computes the matrix square root of one or more square matrices:
 * <p>
 * matmul(sqrtm(A), sqrtm(A)) = A
 * <p>
 * The input matrix should be invertible. If the input matrix is real, it should
 * have no eigenvalues which are real and negative (pairs of complex conjugate
 * eigenvalues are allowed).
 * <p>
 * The matrix square root is computed by first reducing the matrix to
 * quasi-triangular form with the real Schur decomposition. The square root
 * of the quasi-triangular matrix is then computed directly. Details of
 * the algorithm can be found in: Nicholas J. Higham, "Computing real
 * square roots of a real matrix", Linear Algebra Appl., 1987.
 * <p>
 * The input is a tensor of shape `[..., M, M]` whose inner-most 2 dimensions
 * form square matrices. The output is a tensor of the same shape as the input
 * containing the matrix square root for all input submatrices `[..., :, :]`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class Sqrtm<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Sqrtm operation.
   * 
   * @param scope current scope
   * @param input Shape is `[..., M, M]`.
   * @return a new instance of Sqrtm
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Sqrtm<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixSquareRoot", scope.makeOpName("Sqrtm"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Sqrtm<T>(opBuilder.build());
  }
  
  /**
   * Shape is `[..., M, M]`.
   * <p>
   * @compatibility(scipy)
   * Equivalent to scipy.linalg.sqrtm
   * @end_compatibility
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MatrixSquareRoot";
  
  private Output<T> output;
  
  private Sqrtm(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
