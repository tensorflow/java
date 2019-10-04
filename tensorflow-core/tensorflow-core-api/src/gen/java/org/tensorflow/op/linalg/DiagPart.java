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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Returns the batched diagonal part of a batched tensor.
 * <p>
 * This operation returns a tensor with the `diagonal` part
 * of the batched `input`. The `diagonal` part is computed as follows:
 * <p>
 * Assume `input` has `k` dimensions `[I, J, K, ..., M, N]`, then the output is a
 * tensor of rank `k - 1` with dimensions `[I, J, K, ..., min(M, N)]` where:
 * <p>
 * `diagonal[i, j, k, ..., n] = input[i, j, k, ..., n, n]`.
 * <p>
 * The input must be at least a matrix.
 * <p>
 * For example:
 * <pre>{@code
 * # 'input' is [[[1, 0, 0, 0]
 *                [0, 2, 0, 0]
 *                [0, 0, 3, 0]
 *                [0, 0, 0, 4]],
 *               [[5, 0, 0, 0]
 *                [0, 6, 0, 0]
 *                [0, 0, 7, 0]
 *                [0, 0, 0, 8]]]
 * 
 * and input.shape = (2, 4, 4)
 * 
 * tf.matrix_diag_part(input) ==> [[1, 2, 3, 4], [5, 6, 7, 8]]
 * 
 * which has shape (2, 4)
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code diagonal()} output
 */
@Operator(group = "linalg")
public final class DiagPart<T> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new DiagPart operation.
   * 
   * @param scope current scope
   * @param input Rank `k` tensor where `k >= 2`.
   * @return a new instance of DiagPart
   */
  public static <T> DiagPart<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixDiagPart", scope.makeOpName("DiagPart"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new DiagPart<T>(opBuilder.build());
  }
  
  /**
   * The extracted diagonal(s) having shape
   * `diagonal.shape = input.shape[:-2] + [min(input.shape[-2:])]`.
   */
  public Output<T> diagonal() {
    return diagonal;
  }
  
  @Override
  public Output<T> asOutput() {
    return diagonal;
  }
  
  private Output<T> diagonal;
  
  private DiagPart(Operation operation) {
    super(operation);
    int outputIdx = 0;
    diagonal = operation.output(outputIdx++);
  }
}
