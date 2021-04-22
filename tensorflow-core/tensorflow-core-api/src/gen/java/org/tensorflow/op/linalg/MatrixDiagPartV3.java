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
 * Returns a tensor with the {@code k[0]}-th to {@code k[1]}-th diagonals of the batched
 * {@code input}.
 * <p>Assume {@code input} has {@code r} dimensions {@code [I, J, ..., L, M, N]}.
 * Let {@code max_diag_len} be the maximum length among all diagonals to be extracted,
 * {@code max_diag_len = min(M + min(k[1], 0), N + min(-k[0], 0))}
 * Let {@code num_diags} be the number of diagonals to extract,
 * {@code num_diags = k[1] - k[0] + 1}.
 * <p>If {@code num_diags == 1}, the output tensor is of rank {@code r - 1} with shape
 * {@code [I, J, ..., L, max_diag_len]} and values:
 * <pre>
 * diagonal[i, j, ..., l, n]
 *   = input[i, j, ..., l, n+y, n+x] ; if 0 &lt;= n+y &lt; M and 0 &lt;= n+x &lt; N,
 *     padding_value                 ; otherwise.
 * </pre>
 * <p>where {@code y = max(-k[1], 0)}, {@code x = max(k[1], 0)}.
 * <p>Otherwise, the output tensor has rank {@code r} with dimensions
 * {@code [I, J, ..., L, num_diags, max_diag_len]} with values:
 * <pre>
 * diagonal[i, j, ..., l, m, n]
 *   = input[i, j, ..., l, n+y, n+x] ; if 0 &lt;= n+y &lt; M and 0 &lt;= n+x &lt; N,
 *     padding_value                 ; otherwise.
 * </pre>
 * <p>where {@code d = k[1] - m}, {@code y = max(-d, 0) - offset}, and {@code x = max(d, 0) - offset}.
 * <p>{@code offset} is zero except when the alignment of the diagonal is to the right.
 * <pre>
 * offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
 *                                            and `d &gt;= 0`) or
 *                                          (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
 *                                            and `d &lt;= 0`)
 *          0                          ; otherwise
 * </pre>
 * <p>where {@code diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))}.
 * <p>The input must be at least a matrix.
 * <p>For example:
 * <pre>
 * input = np.array([[[1, 2, 3, 4],  # Input shape: (2, 3, 4)
 *                    [5, 6, 7, 8],
 *                    [9, 8, 7, 6]],
 *                   [[5, 4, 3, 2],
 *                    [1, 2, 3, 4],
 *                    [5, 6, 7, 8]]])
 *
 * # A main diagonal from each batch.
 * tf.matrix_diag_part(input) ==&gt; [[1, 6, 7],  # Output shape: (2, 3)
 *                                 [5, 2, 7]]
 *
 * # A superdiagonal from each batch.
 * tf.matrix_diag_part(input, k = 1)
 *   ==&gt; [[2, 7, 6],  # Output shape: (2, 3)
 *        [4, 3, 8]]
 *
 * # A band from each batch.
 * tf.matrix_diag_part(input, k = (-1, 2))
 *   ==&gt; [[[0, 3, 8],  # Output shape: (2, 4, 3)
 *         [2, 7, 6],
 *         [1, 6, 7],
 *         [5, 8, 0]],
 *        [[0, 3, 4],
 *         [4, 3, 8],
 *         [5, 2, 7],
 *         [1, 6, 0]]]
 *
 * # LEFT_RIGHT alignment.
 * tf.matrix_diag_part(input, k = (-1, 2), align=&quot;LEFT_RIGHT&quot;)
 *   ==&gt; [[[3, 8, 0],  # Output shape: (2, 4, 3)
 *         [2, 7, 6],
 *         [1, 6, 7],
 *         [0, 5, 8]],
 *        [[3, 4, 0],
 *         [4, 3, 8],
 *         [5, 2, 7],
 *         [0, 1, 6]]]
 *
 * # max_diag_len can be shorter than the main diagonal.
 * tf.matrix_diag_part(input, k = (-2, -1))
 *   ==&gt; [[[5, 8],
 *         [9, 0]],
 *        [[1, 6],
 *         [5, 0]]]
 *
 * # padding_value = 9
 * tf.matrix_diag_part(input, k = (1, 3), padding_value = 9)
 *   ==&gt; [[[9, 9, 4],  # Output shape: (2, 3, 3)
 *         [9, 3, 8],
 *         [2, 7, 6]],
 *        [[9, 9, 2],
 *         [9, 3, 4],
 *         [4, 3, 8]]]
 *
 * </pre>
 *
 * @param <T> data type for {@code diagonal} output
 */
@Operator(
    group = "linalg"
)
public final class MatrixDiagPartV3<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatrixDiagPartV3";

  private Output<T> diagonal;

  private MatrixDiagPartV3(Operation operation) {
    super(operation);
    int outputIdx = 0;
    diagonal = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatrixDiagPartV3 operation.
   *
   * @param scope current scope
   * @param input Rank {@code r} tensor where {@code r >= 2}.
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   * diagonal, and negative value means subdiagonals. {@code k} can be a single integer
   * (for a single diagonal) or a pair of integers specifying the low and high ends
   * of a matrix band. {@code k[0]} must not be larger than {@code k[1]}.
   * @param paddingValue The value to fill the area outside the specified diagonal band with.
   * Default is 0.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixDiagPartV3} output and operands
   * @return a new instance of MatrixDiagPartV3
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> MatrixDiagPartV3<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> k, Operand<T> paddingValue, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixDiagPartV3", scope.makeOpName("MatrixDiagPartV3"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(k.asOutput());
    opBuilder.addInput(paddingValue.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.align != null) {
          opBuilder.setAttr("align", opts.align);
        }
      }
    }
    return new MatrixDiagPartV3<>(opBuilder.build());
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
   * Gets diagonal.
   * The extracted diagonal(s).
   * @return diagonal.
   */
  public Output<T> diagonal() {
    return diagonal;
  }

  @Override
  public Output<T> asOutput() {
    return diagonal;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.MatrixDiagPartV3}
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
