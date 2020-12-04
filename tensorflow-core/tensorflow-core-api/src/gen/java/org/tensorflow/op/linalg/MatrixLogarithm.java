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
 * Computes the matrix logarithm of one or more square matrices:
 * <p>
 * 
 * \\(log(exp(A)) = A\\)
 * <p>
 * This op is only defined for complex matrices. If A is positive-definite and
 * real, then casting to a complex matrix, taking the logarithm and casting back
 * to a real matrix will give the correct result.
 * <p>
 * This function computes the matrix logarithm using the Schur-Parlett algorithm.
 * Details of the algorithm can be found in Section 11.6.2 of:
 * Nicholas J. Higham, Functions of Matrices: Theory and Computation, SIAM 2008.
 * ISBN 978-0-898716-46-7.
 * <p>
 * The input is a tensor of shape `[..., M, M]` whose inner-most 2 dimensions
 * form square matrices. The output is a tensor of the same shape as the input
 * containing the exponential for all input submatrices `[..., :, :]`.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class MatrixLogarithm<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new MatrixLogarithm operation.
   * 
   * @param scope current scope
   * @param input Shape is `[..., M, M]`.
   * @return a new instance of MatrixLogarithm
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> MatrixLogarithm<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixLogarithm", scope.makeOpName("MatrixLogarithm"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new MatrixLogarithm<T>(opBuilder.build());
  }
  
  /**
   * Shape is `[..., M, M]`.
   * <p>
   * @compatibility(scipy)
   * Equivalent to scipy.linalg.logm
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
  public static final String OP_NAME = "MatrixLogarithm";
  
  private Output<T> output;
  
  private MatrixLogarithm(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
