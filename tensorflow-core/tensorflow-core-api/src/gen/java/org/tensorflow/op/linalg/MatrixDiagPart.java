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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Returns the batched diagonal part of a batched tensor.
 * <p>
 * Returns a tensor with the `k[0]`-th to `k[1]`-th diagonals of the batched
 * `input`.
 * <p>
 * Assume `input` has `r` dimensions `[I, J, ..., L, M, N]`.
 * Let `max_diag_len` be the maximum length among all diagonals to be extracted,
 * `max_diag_len = min(M + min(k[1], 0), N + min(-k[0], 0))`
 * Let `num_diags` be the number of diagonals to extract,
 * `num_diags = k[1] - k[0] + 1`.
 * <p>
 * If `num_diags == 1`, the output tensor is of rank `r - 1` with shape
 * `[I, J, ..., L, max_diag_len]` and values:
 * <pre>{@code
 * diagonal[i, j, ..., l, n]
 *   = input[i, j, ..., l, n+y, n+x] ; if 0 <= n+y < M and 0 <= n+x < N,
 *     padding_value                 ; otherwise.
 * }</pre>
 * where `y = max(-k[1], 0)`, `x = max(k[1], 0)`.
 * <p>
 * Otherwise, the output tensor has rank `r` with dimensions
 * `[I, J, ..., L, num_diags, max_diag_len]` with values:
 * <pre>{@code
 * diagonal[i, j, ..., l, m, n]
 *   = input[i, j, ..., l, n+y, n+x] ; if 0 <= n+y < M and 0 <= n+x < N,
 *     padding_value                 ; otherwise.
 * }</pre>
 * where `d = k[1] - m`, `y = max(-d, 0)`, and `x = max(d, 0)`.
 * <p>
 * The input must be at least a matrix.
 * <p>
 * For example:
 * <pre>{@code
 * input = np.array([[[1, 2, 3, 4],  # Input shape: (2, 3, 4)
 *                    [5, 6, 7, 8],
 *                    [9, 8, 7, 6]],
 *                   [[5, 4, 3, 2],
 *                    [1, 2, 3, 4],
 *                    [5, 6, 7, 8]]])
 * 
 * # A main diagonal from each batch.
 * tf.matrix_diag_part(input) ==> [[1, 6, 7],  # Output shape: (2, 3)
 *                                 [5, 2, 7]]
 * 
 * # A superdiagonal from each batch.
 * tf.matrix_diag_part(input, k = 1)
 *   ==> [[2, 7, 6],  # Output shape: (2, 3)
 *        [4, 3, 8]]
 * 
 * # A tridiagonal band from each batch.
 * tf.matrix_diag_part(input, k = (-1, 1))
 *   ==> [[[2, 7, 6],  # Output shape: (2, 3, 3)
 *         [1, 6, 7],
 *         [5, 8, 0]],
 *        [[4, 3, 8],
 *         [5, 2, 7],
 *         [1, 6, 0]]]
 * 
 * # Padding value = 9
 * tf.matrix_diag_part(input, k = (1, 3), padding_value = 9)
 *   ==> [[[4, 9, 9],  # Output shape: (2, 3, 3)
 *         [3, 8, 9],
 *         [2, 7, 6]],
 *        [[2, 9, 9],
 *         [3, 4, 9],
 *         [4, 3, 8]]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code diagonal()} output
 */
@Operator(group = "linalg")
public final class MatrixDiagPart<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new MatrixDiagPart operation.
   * 
   * @param scope current scope
   * @param input Rank `r` tensor where `r >= 2`.
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   * diagonal, and negative value means subdiagonals. `k` can be a single integer
   * (for a single diagonal) or a pair of integers specifying the low and high ends
   * of a matrix band. `k[0]` must not be larger than `k[1]`.
   * @param paddingValue The value to fill the area outside the specified diagonal band with.
   * Default is 0.
   * @return a new instance of MatrixDiagPart
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> MatrixDiagPart<T> create(Scope scope, Operand<T> input, Operand<TInt32> k, Operand<T> paddingValue) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixDiagPartV2", scope.makeOpName("MatrixDiagPart"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(k.asOutput());
    opBuilder.addInput(paddingValue.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new MatrixDiagPart<T>(opBuilder.build());
  }
  
  /**
   * The extracted diagonal(s).
   */
  public Output<T> diagonal() {
    return diagonal;
  }
  
  @Override
  public Output<T> asOutput() {
    return diagonal;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MatrixDiagPartV2";
  
  private Output<T> diagonal;
  
  private MatrixDiagPart(Operation operation) {
    super(operation);
    int outputIdx = 0;
    diagonal = operation.output(outputIdx++);
  }
}
