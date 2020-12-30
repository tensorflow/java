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
 * Returns a batched matrix tensor with new batched diagonal values.
 * <p>
 * Given `input` and `diagonal`, this operation returns a tensor with the
 * same shape and values as `input`, except for the specified diagonals of the
 * innermost matrices. These will be overwritten by the values in `diagonal`.
 * <p>
 * `input` has `r+1` dimensions `[I, J, ..., L, M, N]`. When `k` is scalar or
 * `k[0] == k[1]`, `diagonal` has `r` dimensions `[I, J, ..., L, max_diag_len]`.
 * Otherwise, it has `r+1` dimensions `[I, J, ..., L, num_diags, max_diag_len]`.
 * `num_diags` is the number of diagonals, `num_diags = k[1] - k[0] + 1`.
 * `max_diag_len` is the longest diagonal in the range `[k[0], k[1]]`,
 * `max_diag_len = min(M + min(k[1], 0), N + min(-k[0], 0))`
 * <p>
 * The output is a tensor of rank `k+1` with dimensions `[I, J, ..., L, M, N]`.
 * If `k` is scalar or `k[0] == k[1]`:
 * <pre>{@code
 * output[i, j, ..., l, m, n]
 *   = diagonal[i, j, ..., l, n-max(k[1], 0)] ; if n - m == k[1]
 *     input[i, j, ..., l, m, n]              ; otherwise
 * }</pre>
 * Otherwise,
 * <pre>{@code
 * output[i, j, ..., l, m, n]
 *   = diagonal[i, j, ..., l, diag_index, index_in_diag] ; if k[0] <= d <= k[1]
 *     input[i, j, ..., l, m, n]                         ; otherwise
 * }</pre>
 * where `d = n - m`, `diag_index = k[1] - d`, and
 * `index_in_diag = n - max(d, 0) + offset`.
 * <p>
 * `offset` is zero except when the alignment of the diagonal is to the right.
 * <pre>{@code
 * offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
 *                                            and `d >= 0`) or
 *                                          (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
 *                                            and `d <= 0`)
 *          0                          ; otherwise
 * }</pre>
 * where `diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))`.
 * <p>
 * For example:
 * <pre>{@code
 * # The main diagonal.
 * input = np.array([[[7, 7, 7, 7],              # Input shape: (2, 3, 4)
 *                    [7, 7, 7, 7],
 *                    [7, 7, 7, 7]],
 *                   [[7, 7, 7, 7],
 *                    [7, 7, 7, 7],
 *                    [7, 7, 7, 7]]])
 * diagonal = np.array([[1, 2, 3],               # Diagonal shape: (2, 3)
 *                      [4, 5, 6]])
 * tf.matrix_set_diag(input, diagonal)
 *   ==> [[[1, 7, 7, 7],  # Output shape: (2, 3, 4)
 *         [7, 2, 7, 7],
 *         [7, 7, 3, 7]],
 *        [[4, 7, 7, 7],
 *         [7, 5, 7, 7],
 *         [7, 7, 6, 7]]]
 * 
 * # A superdiagonal (per batch).
 * tf.matrix_set_diag(input, diagonal, k = 1)
 *   ==> [[[7, 1, 7, 7],  # Output shape: (2, 3, 4)
 *         [7, 7, 2, 7],
 *         [7, 7, 7, 3]],
 *        [[7, 4, 7, 7],
 *         [7, 7, 5, 7],
 *         [7, 7, 7, 6]]]
 * 
 * # A band of diagonals.
 * diagonals = np.array([[[0, 9, 1],  # Diagonal shape: (2, 4, 3)
 *                        [6, 5, 8],
 *                        [1, 2, 3],
 *                        [4, 5, 0]],
 *                       [[0, 1, 2],
 *                        [5, 6, 4],
 *                        [6, 1, 2],
 *                        [3, 4, 0]]])
 * tf.matrix_set_diag(input, diagonals, k = (-1, 2))
 *   ==> [[[1, 6, 9, 7],  # Output shape: (2, 3, 4)
 *         [4, 2, 5, 1],
 *         [7, 5, 3, 8]],
 *        [[6, 5, 1, 7],
 *         [3, 1, 6, 2],
 *         [7, 4, 2, 4]]]
 * 
 * # LEFT_RIGHT alignment.
 * diagonals = np.array([[[9, 1, 0],  # Diagonal shape: (2, 4, 3)
 *                        [6, 5, 8],
 *                        [1, 2, 3],
 *                        [0, 4, 5]],
 *                       [[1, 2, 0],
 *                        [5, 6, 4],
 *                        [6, 1, 2],
 *                        [0, 3, 4]]])
 * tf.matrix_set_diag(input, diagonals, k = (-1, 2), align="LEFT_RIGHT")
 *   ==> [[[1, 6, 9, 7],  # Output shape: (2, 3, 4)
 *         [4, 2, 5, 1],
 *         [7, 5, 3, 8]],
 *        [[6, 5, 1, 7],
 *         [3, 1, 6, 2],
 *         [7, 4, 2, 4]]]
 * 
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class MatrixSetDiag<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.MatrixSetDiag}
   */
  public static class Options {
    
    /**
     * @param align Some diagonals are shorter than `max_diag_len` and need to be padded. `align` is
     * a string specifying how superdiagonals and subdiagonals should be aligned,
     * respectively. There are four possible alignments: "RIGHT_LEFT" (default),
     * "LEFT_RIGHT", "LEFT_LEFT", and "RIGHT_RIGHT". "RIGHT_LEFT" aligns superdiagonals
     * to the right (left-pads the row) and subdiagonals to the left (right-pads the
     * row). It is the packing format LAPACK uses. cuSPARSE uses "LEFT_RIGHT", which is
     * the opposite alignment.
     */
    public Options align(String align) {
      this.align = align;
      return this;
    }
    
    private String align;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new MatrixSetDiag operation.
   * 
   * @param scope current scope
   * @param input Rank `r+1`, where `r >= 1`.
   * @param diagonal Rank `r` when `k` is an integer or `k[0] == k[1]`. Otherwise, it has rank `r+1`.
   * `k >= 1`.
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   * diagonal, and negative value means subdiagonals. `k` can be a single integer
   * (for a single diagonal) or a pair of integers specifying the low and high ends
   * of a matrix band. `k[0]` must not be larger than `k[1]`.
   * @param options carries optional attributes values
   * @return a new instance of MatrixSetDiag
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> MatrixSetDiag<T> create(Scope scope, Operand<T> input, Operand<T> diagonal, Operand<TInt32> k, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixSetDiagV3", scope.makeOpName("MatrixSetDiag"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(diagonal.asOutput());
    opBuilder.addInput(k.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.align != null) {
          opBuilder.setAttr("align", opts.align);
        }
      }
    }
    return new MatrixSetDiag<T>(opBuilder.build());
  }
  
  /**
   * @param align Some diagonals are shorter than `max_diag_len` and need to be padded. `align` is
   * a string specifying how superdiagonals and subdiagonals should be aligned,
   * respectively. There are four possible alignments: "RIGHT_LEFT" (default),
   * "LEFT_RIGHT", "LEFT_LEFT", and "RIGHT_RIGHT". "RIGHT_LEFT" aligns superdiagonals
   * to the right (left-pads the row) and subdiagonals to the left (right-pads the
   * row). It is the packing format LAPACK uses. cuSPARSE uses "LEFT_RIGHT", which is
   * the opposite alignment.
   */
  public static Options align(String align) {
    return new Options().align(align);
  }
  
  /**
   * Rank `r+1`, with `output.shape = input.shape`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MatrixSetDiagV3";
  
  private Output<T> output;
  
  private MatrixSetDiag(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
