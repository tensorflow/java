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
 * Returns a batched diagonal tensor with given batched diagonal values.
 * Returns a tensor with the contents in {@code diagonal} as {@code k[0]}-th to {@code k[1]}-th
 * diagonals of a matrix, with everything else padded with {@code padding}. {@code num_rows}
 * and {@code num_cols} specify the dimension of the innermost matrix of the output. If
 * both are not specified, the op assumes the innermost matrix is square and infers
 * its size from {@code k} and the innermost dimension of {@code diagonal}. If only one of them
 * is specified, the op assumes the unspecified value is the smallest possible
 * based on other criteria.
 * <p>Let {@code diagonal} have {@code r} dimensions {@code [I, J, ..., L, M, N]}. The output tensor has
 * rank {@code r+1} with shape {@code [I, J, ..., L, M, num_rows, num_cols]} when only one
 * diagonal is given ({@code k} is an integer or {@code k[0] == k[1]}). Otherwise, it has rank
 * {@code r} with shape {@code [I, J, ..., L, num_rows, num_cols]}.
 * <p>The second innermost dimension of {@code diagonal} has double meaning.
 * When {@code k} is scalar or {@code k[0] == k[1]}, {@code M} is part of the batch size
 * [I, J, ..., M], and the output tensor is:
 * <pre>
 * output[i, j, ..., l, m, n]
 *   = diagonal[i, j, ..., l, n-max(d_upper, 0)] ; if n - m == d_upper
 *     padding_value                             ; otherwise
 * </pre>
 * <p>Otherwise, {@code M} is treated as the number of diagonals for the matrix in the
 * same batch ({@code M = k[1]-k[0]+1}), and the output tensor is:
 * <pre>
 * output[i, j, ..., l, m, n]
 *   = diagonal[i, j, ..., l, diag_index, index_in_diag] ; if k[0] &lt;= d &lt;= k[1]
 *     padding_value                                     ; otherwise
 * </pre>
 * <p>where {@code d = n - m}, {@code diag_index = [k] - d}, and
 * {@code index_in_diag = n - max(d, 0) + offset}.
 * <p>{@code offset} is zero except when the alignment of the diagonal is to the right.
 * <pre>
 * offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
 *                                            and `d &gt;= 0`) or
 *                                          (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
 *                                            and `d &lt;= 0`)
 *          0                          ; otherwise
 * </pre>
 * <p>where {@code diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))}.
 * <p>For example:
 * <pre>
 * # The main diagonal.
 * diagonal = np.array([[1, 2, 3, 4],            # Input shape: (2, 4)
 *                      [5, 6, 7, 8]])
 * tf.matrix_diag(diagonal) ==&gt; [[[1, 0, 0, 0],  # Output shape: (2, 4, 4)
 *                                [0, 2, 0, 0],
 *                                [0, 0, 3, 0],
 *                                [0, 0, 0, 4]],
 *                               [[5, 0, 0, 0],
 *                                [0, 6, 0, 0],
 *                                [0, 0, 7, 0],
 *                                [0, 0, 0, 8]]]
 *
 * # A superdiagonal (per batch).
 * diagonal = np.array([[1, 2, 3],  # Input shape: (2, 3)
 *                      [4, 5, 6]])
 * tf.matrix_diag(diagonal, k = 1)
 *   ==&gt; [[[0, 1, 0, 0],  # Output shape: (2, 4, 4)
 *         [0, 0, 2, 0],
 *         [0, 0, 0, 3],
 *         [0, 0, 0, 0]],
 *        [[0, 4, 0, 0],
 *         [0, 0, 5, 0],
 *         [0, 0, 0, 6],
 *         [0, 0, 0, 0]]]
 *
 * # A tridiagonal band (per batch).
 * diagonals = np.array([[[0, 8, 9],  # Input shape: (2, 2, 3)
 *                        [1, 2, 3],
 *                        [4, 5, 0]],
 *                       [[0, 2, 3],
 *                        [6, 7, 9],
 *                        [9, 1, 0]]])
 * tf.matrix_diag(diagonals, k = (-1, 1))
 *   ==&gt; [[[1, 8, 0],  # Output shape: (2, 3, 3)
 *         [4, 2, 9],
 *         [0, 5, 3]],
 *        [[6, 2, 0],
 *         [9, 7, 3],
 *         [0, 1, 9]]]
 *
 * # LEFT_RIGHT alignment.
 * diagonals = np.array([[[8, 9, 0],  # Input shape: (2, 2, 3)
 *                        [1, 2, 3],
 *                        [0, 4, 5]],
 *                       [[2, 3, 0],
 *                        [6, 7, 9],
 *                        [0, 9, 1]]])
 * tf.matrix_diag(diagonals, k = (-1, 1), align=&quot;LEFT_RIGHT&quot;)
 *   ==&gt; [[[1, 8, 0],  # Output shape: (2, 3, 3)
 *         [4, 2, 9],
 *         [0, 5, 3]],
 *        [[6, 2, 0],
 *         [9, 7, 3],
 *         [0, 1, 9]]]
 *
 * # Rectangular matrix.
 * diagonal = np.array([1, 2])  # Input shape: (2)
 * tf.matrix_diag(diagonal, k = -1, num_rows = 3, num_cols = 4)
 *   ==&gt; [[0, 0, 0, 0],  # Output shape: (3, 4)
 *        [1, 0, 0, 0],
 *        [0, 2, 0, 0]]
 *
 * # Rectangular matrix with inferred num_cols and padding_value = 9.
 * tf.matrix_diag(diagonal, k = -1, num_rows = 3, padding_value = 9)
 *   ==&gt; [[9, 9],  # Output shape: (3, 2)
 *        [1, 9],
 *        [9, 2]]
 *
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "linalg"
)
public final class MatrixDiagV3<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatrixDiagV3";

  private Output<T> output;

  private MatrixDiagV3(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatrixDiagV3 operation.
   *
   * @param scope current scope
   * @param diagonal Rank {@code r}, where {@code r >= 1}
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   * diagonal, and negative value means subdiagonals. {@code k} can be a single integer
   * (for a single diagonal) or a pair of integers specifying the low and high ends
   * of a matrix band. {@code k[0]} must not be larger than {@code k[1]}.
   * @param numRows The number of rows of the output matrix. If it is not provided, the op assumes
   * the output matrix is a square matrix and infers the matrix size from k and the
   * innermost dimension of {@code diagonal}.
   * @param numCols The number of columns of the output matrix. If it is not provided, the op
   * assumes the output matrix is a square matrix and infers the matrix size from
   * k and the innermost dimension of {@code diagonal}.
   * @param paddingValue The number to fill the area outside the specified diagonal band with.
   * Default is 0.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixDiagV3} output and operands
   * @return a new instance of MatrixDiagV3
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> MatrixDiagV3<T> create(Scope scope, Operand<T> diagonal,
      Operand<TInt32> k, Operand<TInt32> numRows, Operand<TInt32> numCols, Operand<T> paddingValue,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixDiagV3", scope.makeOpName("MatrixDiagV3"));
    opBuilder.addInput(diagonal.asOutput());
    opBuilder.addInput(k.asOutput());
    opBuilder.addInput(numRows.asOutput());
    opBuilder.addInput(numCols.asOutput());
    opBuilder.addInput(paddingValue.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.align != null) {
          opBuilder.setAttr("align", opts.align);
        }
      }
    }
    return new MatrixDiagV3<>(opBuilder.build());
  }

  /**
   * Sets the align option.
   *
   * @param align Some diagonals are shorter than {@code max_diag_len} and need to be padded. {@code align} is
   * a string specifying how superdiagonals and subdiagonals should be aligned,
   * respectively. There are four possible alignments: &quot;RIGHT_LEFT&quot; (default),
   * &quot;LEFT_RIGHT&quot;, &quot;LEFT_LEFT&quot;, and &quot;RIGHT_RIGHT&quot;. &quot;RIGHT_LEFT&quot; aligns superdiagonals
   * to the right (left-pads the row) and subdiagonals to the left (right-pads the
   * row). It is the packing format LAPACK uses. cuSPARSE uses &quot;LEFT_RIGHT&quot;, which is
   * the opposite alignment.
   * @return this Options instance.
   */
  public static Options align(String align) {
    return new Options().align(align);
  }

  /**
   * Gets output.
   * Has rank {@code r+1} when {@code k} is an integer or {@code k[0] == k[1]}, rank {@code r} otherwise.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.MatrixDiagV3}
   */
  public static class Options {
    private String align;

    private Options() {
    }

    /**
     * Sets the align option.
     *
     * @param align Some diagonals are shorter than {@code max_diag_len} and need to be padded. {@code align} is
     * a string specifying how superdiagonals and subdiagonals should be aligned,
     * respectively. There are four possible alignments: &quot;RIGHT_LEFT&quot; (default),
     * &quot;LEFT_RIGHT&quot;, &quot;LEFT_LEFT&quot;, and &quot;RIGHT_RIGHT&quot;. &quot;RIGHT_LEFT&quot; aligns superdiagonals
     * to the right (left-pads the row) and subdiagonals to the left (right-pads the
     * row). It is the packing format LAPACK uses. cuSPARSE uses &quot;LEFT_RIGHT&quot;, which is
     * the opposite alignment.
     * @return this Options instance.
     */
    public Options align(String align) {
      this.align = align;
      return this;
    }
  }
}
