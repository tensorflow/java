// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.linalg.sparse.CSRSparseMatrixComponents;
import org.tensorflow.op.linalg.sparse.CSRSparseMatrixToDense;
import org.tensorflow.op.linalg.sparse.CSRSparseMatrixToSparseTensor;
import org.tensorflow.op.linalg.sparse.DenseToCSRSparseMatrix;
import org.tensorflow.op.linalg.sparse.SparseMatrixAdd;
import org.tensorflow.op.linalg.sparse.SparseMatrixMatMul;
import org.tensorflow.op.linalg.sparse.SparseMatrixMul;
import org.tensorflow.op.linalg.sparse.SparseMatrixNNZ;
import org.tensorflow.op.linalg.sparse.SparseMatrixOrderingAMD;
import org.tensorflow.op.linalg.sparse.SparseMatrixSoftmax;
import org.tensorflow.op.linalg.sparse.SparseMatrixSoftmaxGrad;
import org.tensorflow.op.linalg.sparse.SparseMatrixSparseCholesky;
import org.tensorflow.op.linalg.sparse.SparseMatrixSparseMatMul;
import org.tensorflow.op.linalg.sparse.SparseMatrixTranspose;
import org.tensorflow.op.linalg.sparse.SparseMatrixZeros;
import org.tensorflow.op.linalg.sparse.SparseTensorToCSRSparseMatrix;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code linalg.sparse} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class LinalgSparseOps {
  private final Scope scope;

  private final Ops ops;

  LinalgSparseOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Reads out the CSR components at batch {@code index}.
   *  This op is meant only for debugging / testing, and its interface is not expected
   *  to be stable.
   *
   * @param <T> data type for {@code values} output
   * @param csrSparseMatrix A batched CSRSparseMatrix.
   * @param index The index in {@code csr_sparse_matrix}'s batch.
   * @param type The value of the type attribute
   * @param <T> data type for {@code CSRSparseMatrixComponents} output and operands
   * @return a new instance of CSRSparseMatrixComponents
   */
  public <T extends TType> CSRSparseMatrixComponents<T> cSRSparseMatrixComponents(
      Operand<? extends TType> csrSparseMatrix, Operand<TInt32> index, Class<T> type) {
    return CSRSparseMatrixComponents.create(scope, csrSparseMatrix, index, type);
  }

  /**
   * Convert a (possibly batched) CSRSparseMatrix to dense.
   *
   * @param <T> data type for {@code dense_output} output
   * @param sparseInput A batched CSRSparseMatrix.
   * @param type The value of the type attribute
   * @param <T> data type for {@code CSRSparseMatrixToDense} output and operands
   * @return a new instance of CSRSparseMatrixToDense
   */
  public <T extends TType> CSRSparseMatrixToDense<T> cSRSparseMatrixToDense(
      Operand<? extends TType> sparseInput, Class<T> type) {
    return CSRSparseMatrixToDense.create(scope, sparseInput, type);
  }

  /**
   * Converts a (possibly batched) CSRSparesMatrix to a SparseTensor.
   *
   * @param <T> data type for {@code values} output
   * @param sparseMatrix A (possibly batched) CSRSparseMatrix.
   * @param type The value of the type attribute
   * @param <T> data type for {@code CSRSparseMatrixToSparseTensor} output and operands
   * @return a new instance of CSRSparseMatrixToSparseTensor
   */
  public <T extends TType> CSRSparseMatrixToSparseTensor<T> cSRSparseMatrixToSparseTensor(
      Operand<? extends TType> sparseMatrix, Class<T> type) {
    return CSRSparseMatrixToSparseTensor.create(scope, sparseMatrix, type);
  }

  /**
   * Converts a dense tensor to a (possibly batched) CSRSparseMatrix.
   *
   * @param denseInput A Dense tensor.
   * @param indices Indices of nonzero elements.
   * @return a new instance of DenseToCSRSparseMatrix
   */
  public DenseToCSRSparseMatrix denseToCSRSparseMatrix(Operand<? extends TType> denseInput,
      Operand<TInt64> indices) {
    return DenseToCSRSparseMatrix.create(scope, denseInput, indices);
  }

  /**
   * Sparse addition of two CSR matrices, C = alpha * A + beta * B.
   *  The gradients of SparseMatrixAdd outputs with respect to alpha and beta are not
   *  currently defined (TensorFlow will return zeros for these entries).
   *
   * @param a A CSRSparseMatrix.
   * @param b A CSRSparseMatrix.
   * @param alpha A constant scalar.
   * @param beta A constant scalar.
   * @param <T> data type for {@code SparseMatrixAdd} output and operands
   * @return a new instance of SparseMatrixAdd
   */
  public <T extends TType> SparseMatrixAdd sparseMatrixAdd(Operand<? extends TType> a,
      Operand<? extends TType> b, Operand<T> alpha, Operand<T> beta) {
    return SparseMatrixAdd.create(scope, a, b, alpha, beta);
  }

  /**
   * Matrix-multiplies a sparse matrix with a dense matrix.
   *  Returns a dense matrix.
   *  For inputs A and B, where A is CSR and B is dense; this op returns a dense C;
   *  <p>If transpose_output is false, returns:
   *  <pre>
   *    C = A . B
   *  </pre>
   *  <p>If transpose_output is {@code true}, returns:
   *  <pre>
   *    C = transpose(A . B) = transpose(B) . transpose(A)
   *  </pre>
   *  <p>where the transposition is performed along the two innermost (matrix)
   *  dimensions.
   *  <p>If conjugate_output is {@code true}, returns:
   *  <pre>
   *    C = conjugate(A . B) = conjugate(A) . conjugate(B)
   *  </pre>
   *  <p>If both conjugate_output and transpose_output are {@code true}, returns:
   *  <pre>
   *    C = conjugate(transpose(A . B)) = conjugate(transpose(B)) .
   *                                      conjugate(transpose(A))
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param a A CSRSparseMatrix.
   * @param b A dense tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseMatrixMatMul} output and operands
   * @return a new instance of SparseMatrixMatMul
   */
  public <T extends TType> SparseMatrixMatMul<T> sparseMatrixMatMul(Operand<? extends TType> a,
      Operand<T> b, SparseMatrixMatMul.Options... options) {
    return SparseMatrixMatMul.create(scope, a, b, options);
  }

  /**
   * Element-wise multiplication of a sparse matrix with a dense tensor.
   *  Returns a sparse matrix.
   *  <p>The dense tensor {@code b} may be either a scalar; otherwise {@code a} must be a rank-3
   *  {@code SparseMatrix}; in this case {@code b} must be shaped {@code [batch_size, 1, 1]} and the
   *  multiply operation broadcasts.
   *  <p><strong>NOTE</strong> even if {@code b} is zero, the sparsity structure of the output does not
   *  change.
   *
   * @param a A CSRSparseMatrix.
   * @param b A dense tensor.
   * @return a new instance of SparseMatrixMul
   */
  public SparseMatrixMul sparseMatrixMul(Operand<? extends TType> a, Operand<? extends TType> b) {
    return SparseMatrixMul.create(scope, a, b);
  }

  /**
   * Returns the number of nonzeroes of {@code sparse_matrix}.
   *
   * @param sparseMatrix A CSRSparseMatrix.
   * @return a new instance of SparseMatrixNNZ
   */
  public SparseMatrixNNZ sparseMatrixNNZ(Operand<? extends TType> sparseMatrix) {
    return SparseMatrixNNZ.create(scope, sparseMatrix);
  }

  /**
   * Computes the Approximate Minimum Degree (AMD) ordering of {@code input}.
   *  Computes the Approximate Minimum Degree (AMD) ordering for a sparse matrix.
   *  <p>The returned permutation may be used to permute the rows and columns of the
   *  given sparse matrix. This typically results in permuted sparse matrix's sparse
   *  Cholesky (or other decompositions) in having fewer zero fill-in compared to
   *  decomposition of the original matrix.
   *  <p>The input sparse matrix may have rank 2 or rank 3. The output Tensor,
   *  representing would then have rank 1 or 2 respectively, with the same batch
   *  shape as the input.
   *  <p>Each component of the input sparse matrix must represent a square symmetric
   *  matrix; only the lower triangular part of the matrix is read. The values of the
   *  sparse matrix does not affect the returned permutation, only the sparsity
   *  pattern of the sparse matrix is used. Hence, a single AMD ordering may be
   *  reused for the Cholesky decompositions of sparse matrices with the same sparsity
   *  pattern but with possibly different values.
   *  <p>Each batch component of the output permutation represents a permutation of {@code N}
   *  elements, where the input sparse matrix components each have {@code N} rows. That is,
   *  the component contains each of the integers {@code {0, .. N-1}} exactly once. The
   *  {@code i}th element represents the row index that the {@code i}th row maps to.
   *  <p>Usage example:
   *  <pre>
   *      from tensorflow.python.ops.linalg.sparse import sparse_csr_matrix_ops
   *
   *      a_indices = np.array([[0, 0], [1, 1], [2, 1], [2, 2], [3, 3]])
   *      a_values = np.array([1.0, 2.0, 1.0, 3.0, 4.0], np.float32)
   *      a_dense_shape = [4, 4]
   *
   *      with tf.Session() as sess:
   *        # Define (COO format) SparseTensor over Numpy array.
   *        a_st = tf.sparse.SparseTensor(a_indices, a_values, a_dense_shape)
   *
   *        # Convert SparseTensors to CSR SparseMatrix.
   *        a_sm = sparse_csr_matrix_ops.sparse_tensor_to_csr_sparse_matrix(
   *            a_st.indices, a_st.values, a_st.dense_shape)
   *
   *        # Obtain the AMD Ordering for the CSR SparseMatrix.
   *        ordering_amd = sparse_csr_matrix_ops.sparse_matrix_ordering_amd(sparse_matrix)
   *
   *        ordering_amd_value = sess.run(ordering_amd)
   *  </pre>
   *  <p>{@code ordering_amd_value} stores the AMD ordering: {@code [1 2 3 0]}.
   *  <p>input: A {@code CSRSparseMatrix}.
   *
   * @param input A {@code CSRSparseMatrix}.
   * @return a new instance of SparseMatrixOrderingAMD
   */
  public SparseMatrixOrderingAMD sparseMatrixOrderingAMD(Operand<? extends TType> input) {
    return SparseMatrixOrderingAMD.create(scope, input);
  }

  /**
   * Calculates the softmax of a CSRSparseMatrix.
   *  Calculate the softmax of the innermost dimensions of a SparseMatrix.
   *  <p>Missing values are treated as {@code -inf} (i.e., logits of zero probability); and
   *  the output has the same sparsity structure as the input (though missing values
   *  in the output may now be treated as having probability zero).
   *
   * @param logits A CSRSparseMatrix.
   * @param type The value of the type attribute
   * @param <T> data type for {@code SparseMatrixSoftmax} output and operands
   * @return a new instance of SparseMatrixSoftmax
   */
  public <T extends TNumber> SparseMatrixSoftmax sparseMatrixSoftmax(
      Operand<? extends TType> logits, Class<T> type) {
    return SparseMatrixSoftmax.create(scope, logits, type);
  }

  /**
   * Calculates the gradient of the SparseMatrixSoftmax op.
   *
   * @param softmax A CSRSparseMatrix.
   * @param gradSoftmax The gradient of {@code softmax}.
   * @param type The value of the type attribute
   * @param <T> data type for {@code SparseMatrixSoftmaxGrad} output and operands
   * @return a new instance of SparseMatrixSoftmaxGrad
   */
  public <T extends TNumber> SparseMatrixSoftmaxGrad sparseMatrixSoftmaxGrad(
      Operand<? extends TType> softmax, Operand<? extends TType> gradSoftmax, Class<T> type) {
    return SparseMatrixSoftmaxGrad.create(scope, softmax, gradSoftmax, type);
  }

  /**
   * Computes the sparse Cholesky decomposition of {@code input}.
   *  Computes the Sparse Cholesky decomposition of a sparse matrix, with the given
   *  fill-in reducing permutation.
   *  <p>The input sparse matrix and the fill-in reducing permutation {@code permutation} must
   *  have compatible shapes. If the sparse matrix has rank 3; with the batch
   *  dimension {@code B}, then the {@code permutation} must be of rank 2; with the same batch
   *  dimension {@code B}. There is no support for broadcasting.
   *  <p>Furthermore, each component vector of {@code permutation} must be of length {@code N},
   *  containing each of the integers {0, 1, ..., N - 1} exactly once, where {@code N} is
   *  the number of rows of each component of the sparse matrix.
   *  <p>Each component of the input sparse matrix must represent a symmetric positive
   *  definite (SPD) matrix; although only the lower triangular part of the matrix is
   *  read. If any individual component is not SPD, then an InvalidArgument error is
   *  thrown.
   *  <p>The returned sparse matrix has the same dense shape as the input sparse matrix.
   *  For each component {@code A} of the input sparse matrix, the corresponding output
   *  sparse matrix represents {@code L}, the lower triangular Cholesky factor satisfying
   *  the following identity:
   *  <pre>
   *    A = L * Lt
   *  </pre>
   *  <p>where Lt denotes the transpose of L (or its conjugate transpose, if {@code type} is
   *  {@code complex64} or {@code complex128}).
   *  <p>The {@code type} parameter denotes the type of the matrix elements. The supported
   *  types are: {@code float32}, {@code float64}, {@code complex64} and {@code complex128}.
   *  <p>Usage example:
   *  <pre>
   *      from tensorflow.python.ops.linalg.sparse import sparse_csr_matrix_ops
   *
   *      a_indices = np.array([[0, 0], [1, 1], [2, 1], [2, 2], [3, 3]])
   *      a_values = np.array([1.0, 2.0, 1.0, 3.0, 4.0], np.float32)
   *      a_dense_shape = [4, 4]
   *
   *      with tf.Session() as sess:
   *        # Define (COO format) SparseTensor over Numpy array.
   *        a_st = tf.sparse.SparseTensor(a_indices, a_values, a_dense_shape)
   *
   *        # Convert SparseTensors to CSR SparseMatrix.
   *        a_sm = sparse_csr_matrix_ops.sparse_tensor_to_csr_sparse_matrix(
   *            a_st.indices, a_st.values, a_st.dense_shape)
   *
   *        # Obtain the Sparse Cholesky factor using AMD Ordering for reducing zero
   *        # fill-in (number of structural non-zeros in the sparse Cholesky factor).
   *        ordering_amd = sparse_csr_matrix_ops.sparse_matrix_ordering_amd(sparse_matrix)
   *        cholesky_sparse_matrices = (
   *            sparse_csr_matrix_ops.sparse_matrix_sparse_cholesky(
   *                sparse_matrix, ordering_amd, type=tf.float32))
   *
   *        # Convert the CSRSparseMatrix Cholesky factor to a dense Tensor
   *        dense_cholesky = sparse_csr_matrix_ops.csr_sparse_matrix_to_dense(
   *            cholesky_sparse_matrices, tf.float32)
   *
   *        # Evaluate the dense Tensor value.
   *        dense_cholesky_value = sess.run(dense_cholesky)
   *  </pre>
   *  <p>{@code dense_cholesky_value} stores the dense Cholesky factor:
   *  <pre>
   *      [[  1.  0.    0.    0.]
   *       [  0.  1.41  0.    0.]
   *       [  0.  0.70  1.58  0.]
   *       [  0.  0.    0.    2.]]
   *  </pre>
   *  <p>input: A {@code CSRSparseMatrix}.
   *  permutation: A {@code Tensor}.
   *  type: The type of {@code input}.
   *
   * @param input A {@code CSRSparseMatrix}.
   * @param permutation A fill-in reducing permutation matrix.
   * @param type The value of the type attribute
   * @param <T> data type for {@code SparseMatrixSparseCholesky} output and operands
   * @return a new instance of SparseMatrixSparseCholesky
   */
  public <T extends TType> SparseMatrixSparseCholesky sparseMatrixSparseCholesky(
      Operand<? extends TType> input, Operand<TInt32> permutation, Class<T> type) {
    return SparseMatrixSparseCholesky.create(scope, input, permutation, type);
  }

  /**
   * Sparse-matrix-multiplies two CSR matrices {@code a} and {@code b}.
   *  Performs a matrix multiplication of a sparse matrix {@code a} with a sparse matrix
   *  {@code b}; returns a sparse matrix {@code a * b}, unless either {@code a} or {@code b} is transposed or
   *  adjointed.
   *  <p>Each matrix may be transposed or adjointed (conjugated and transposed)
   *  according to the Boolean parameters {@code transpose_a}, {@code adjoint_a}, {@code transpose_b}
   *  and {@code adjoint_b}. At most one of {@code transpose_a} or {@code adjoint_a} may be True.
   *  Similarly, at most one of {@code transpose_b} or {@code adjoint_b} may be True.
   *  <p>The inputs must have compatible shapes. That is, the inner dimension of {@code a}
   *  must be equal to the outer dimension of {@code b}. This requirement is adjusted
   *  according to whether either {@code a} or {@code b} is transposed or adjointed.
   *  <p>The {@code type} parameter denotes the type of the matrix elements. Both {@code a} and {@code b}
   *  must have the same type. The supported types are: {@code float32}, {@code float64},
   *  {@code complex64} and {@code complex128}.
   *  <p>Both {@code a} and {@code b} must have the same rank. Broadcasting is not supported. If they
   *  have rank 3, each batch of 2D CSRSparseMatrices within {@code a} and {@code b} must have the
   *  same dense shape.
   *  <p>The sparse matrix product may have numeric (non-structural) zeros.
   *  TODO(anudhyan): Consider adding a boolean attribute to control whether to prune
   *  zeros.
   *  <p>Usage example:
   *  <pre>
   *      from tensorflow.python.ops.linalg.sparse import sparse_csr_matrix_ops
   *
   *      a_indices = np.array([[0, 0], [2, 3], [2, 4], [3, 0]])
   *      a_values = np.array([1.0, 5.0, -1.0, -2.0], np.float32)
   *      a_dense_shape = [4, 5]
   *
   *      b_indices = np.array([[0, 0], [3, 0], [3, 1]])
   *      b_values = np.array([2.0, 7.0, 8.0], np.float32)
   *      b_dense_shape = [5, 3]
   *
   *      with tf.Session() as sess:
   *        # Define (COO format) Sparse Tensors over Numpy arrays
   *        a_st = tf.sparse.SparseTensor(a_indices, a_values, a_dense_shape)
   *        b_st = tf.sparse.SparseTensor(b_indices, b_values, b_dense_shape)
   *
   *        # Convert SparseTensors to CSR SparseMatrix
   *        a_sm = sparse_csr_matrix_ops.sparse_tensor_to_csr_sparse_matrix(
   *            a_st.indices, a_st.values, a_st.dense_shape)
   *        b_sm = sparse_csr_matrix_ops.sparse_tensor_to_csr_sparse_matrix(
   *            b_st.indices, b_st.values, b_st.dense_shape)
   *
   *        # Compute the CSR SparseMatrix matrix multiplication
   *        c_sm = sparse_csr_matrix_ops.sparse_matrix_sparse_mat_mul(
   *            a=a_sm, b=b_sm, type=tf.float32)
   *
   *        # Convert the CSR SparseMatrix product to a dense Tensor
   *        c_sm_dense = sparse_csr_matrix_ops.csr_sparse_matrix_to_dense(
   *            c_sm, tf.float32)
   *        # Evaluate the dense Tensor value
   *        c_sm_dense_value = sess.run(c_sm_dense)
   *  </pre>
   *  <p>{@code c_sm_dense_value} stores the dense matrix product:
   *  <pre>
   *      [[  2.   0.   0.]
   *       [  0.   0.   0.]
   *       [ 35.  40.   0.]
   *       [ -4.   0.   0.]]
   *  </pre>
   *  <p>a: A {@code CSRSparseMatrix}.
   *  b: A {@code CSRSparseMatrix} with the same type and rank as {@code a}.
   *  type: The type of both {@code a} and {@code b}.
   *  transpose_a: If True, {@code a} transposed before multiplication.
   *  transpose_b: If True, {@code b} transposed before multiplication.
   *  adjoint_a: If True, {@code a} adjointed before multiplication.
   *  adjoint_b: If True, {@code b} adjointed before multiplication.
   *
   * @param a A CSRSparseMatrix.
   * @param b A CSRSparseMatrix.
   * @param type The value of the type attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseMatrixSparseMatMul} output and operands
   * @return a new instance of SparseMatrixSparseMatMul
   */
  public <T extends TType> SparseMatrixSparseMatMul sparseMatrixSparseMatMul(
      Operand<? extends TType> a, Operand<? extends TType> b, Class<T> type,
      SparseMatrixSparseMatMul.Options... options) {
    return SparseMatrixSparseMatMul.create(scope, a, b, type, options);
  }

  /**
   * Transposes the inner (matrix) dimensions of a CSRSparseMatrix.
   *  Transposes the inner (matrix) dimensions of a SparseMatrix and optionally
   *  conjugates its values.
   *
   * @param input A CSRSparseMatrix.
   * @param type The value of the type attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseMatrixTranspose} output and operands
   * @return a new instance of SparseMatrixTranspose
   */
  public <T extends TType> SparseMatrixTranspose sparseMatrixTranspose(
      Operand<? extends TType> input, Class<T> type, SparseMatrixTranspose.Options... options) {
    return SparseMatrixTranspose.create(scope, input, type, options);
  }

  /**
   * Creates an all-zeros CSRSparseMatrix with shape {@code dense_shape}.
   *
   * @param denseShape The desired matrix shape.
   * @param type The value of the type attribute
   * @param <T> data type for {@code SparseMatrixZeros} output and operands
   * @return a new instance of SparseMatrixZeros
   */
  public <T extends TType> SparseMatrixZeros sparseMatrixZeros(Operand<TInt64> denseShape,
      Class<T> type) {
    return SparseMatrixZeros.create(scope, denseShape, type);
  }

  /**
   * Converts a SparseTensor to a (possibly batched) CSRSparseMatrix.
   *
   * @param indices SparseTensor indices.
   * @param values SparseTensor values.
   * @param denseShape SparseTensor dense shape.
   * @return a new instance of SparseTensorToCSRSparseMatrix
   */
  public SparseTensorToCSRSparseMatrix sparseTensorToCSRSparseMatrix(Operand<TInt64> indices,
      Operand<? extends TType> values, Operand<TInt64> denseShape) {
    return SparseTensorToCSRSparseMatrix.create(scope, indices, values, denseShape);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
