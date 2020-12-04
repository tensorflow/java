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
 * Computes the sign and the log of the absolute value of the determinant of
 * <p>
 * one or more square matrices.
 * <p>
 * The input is a tensor of shape `[N, M, M]` whose inner-most 2 dimensions
 * form square matrices. The outputs are two tensors containing the signs and
 * absolute values of the log determinants for all N input submatrices
 * `[..., :, :]` such that the determinant = sign*exp(log_abs_determinant).
 * The log_abs_determinant is computed as det(P)*sum(log(diag(LU))) where LU
 * is the LU decomposition of the input and P is the corresponding
 * permutation matrix.
 * 
 * @param <T> data type for {@code sign()} output
 */
@Operator(group = "linalg")
public final class LogMatrixDeterminant<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new LogMatrixDeterminant operation.
   * 
   * @param scope current scope
   * @param input Shape is `[N, M, M]`.
   * @return a new instance of LogMatrixDeterminant
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> LogMatrixDeterminant<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("LogMatrixDeterminant", scope.makeOpName("LogMatrixDeterminant"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new LogMatrixDeterminant<T>(opBuilder.build());
  }
  
  /**
   * The signs of the log determinants of the inputs. Shape is `[N]`.
   */
  public Output<T> sign() {
    return sign;
  }
  
  /**
   * The logs of the absolute values of the determinants
   * of the N input matrices.  Shape is `[N]`.
   */
  public Output<T> logAbsDeterminant() {
    return logAbsDeterminant;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LogMatrixDeterminant";
  
  private Output<T> sign;
  private Output<T> logAbsDeterminant;
  
  private LogMatrixDeterminant(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sign = operation.output(outputIdx++);
    logAbsDeterminant = operation.output(outputIdx++);
  }
}
