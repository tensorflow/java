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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
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
 * <p>where {@code d = n - m}, {@code diag_index = k[1] - d}, and {@code index_in_diag = n - max(d, 0)}.
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
 * # A band of diagonals.
 * diagonals = np.array([[[1, 2, 3],  # Input shape: (2, 2, 3)
 *                        [4, 5, 0]],
 *                       [[6, 7, 9],
 *                        [9, 1, 0]]])
 * tf.matrix_diag(diagonals, k = (-1, 0))
 *   ==&gt; [[[1, 0, 0],  # Output shape: (2, 3, 3)
 *         [4, 2, 0],
 *         [0, 5, 3]],
 *        [[6, 0, 0],
 *         [9, 7, 0],
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
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = MatrixDiag.OP_NAME,
    inputsClass = MatrixDiag.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class MatrixDiag<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatrixDiagV2";

  private Output<T> output;

  public MatrixDiag(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatrixDiagV2 operation.
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
   * @param <T> data type for {@code MatrixDiagV2} output and operands
   * @return a new instance of MatrixDiag
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> MatrixDiag<T> create(Scope scope, Operand<T> diagonal,
      Operand<TInt32> k, Operand<TInt32> numRows, Operand<TInt32> numCols,
      Operand<T> paddingValue) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MatrixDiag");
    opBuilder.addInput(diagonal.asOutput());
    opBuilder.addInput(k.asOutput());
    opBuilder.addInput(numRows.asOutput());
    opBuilder.addInput(numCols.asOutput());
    opBuilder.addInput(paddingValue.asOutput());
    return new MatrixDiag<>(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = MatrixDiag.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<MatrixDiag<T>> {
    /**
     * Rank {@code r}, where {@code r >= 1}
     */
    public final Operand<T> diagonal;

    /**
     * Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
     * diagonal, and negative value means subdiagonals. {@code k} can be a single integer
     * (for a single diagonal) or a pair of integers specifying the low and high ends
     * of a matrix band. {@code k[0]} must not be larger than {@code k[1]}.
     */
    public final Operand<TInt32> k;

    /**
     * The number of rows of the output matrix. If it is not provided, the op assumes
     * the output matrix is a square matrix and infers the matrix size from k and the
     * innermost dimension of {@code diagonal}.
     */
    public final Operand<TInt32> numRows;

    /**
     * The number of columns of the output matrix. If it is not provided, the op
     * assumes the output matrix is a square matrix and infers the matrix size from
     * k and the innermost dimension of {@code diagonal}.
     */
    public final Operand<TInt32> numCols;

    /**
     * The number to fill the area outside the specified diagonal band with.
     * Default is 0.
     */
    public final Operand<T> paddingValue;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new MatrixDiag<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      diagonal = (Operand<T>) op.input(inputIndex++);
      k = (Operand<TInt32>) op.input(inputIndex++);
      numRows = (Operand<TInt32>) op.input(inputIndex++);
      numCols = (Operand<TInt32>) op.input(inputIndex++);
      paddingValue = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
