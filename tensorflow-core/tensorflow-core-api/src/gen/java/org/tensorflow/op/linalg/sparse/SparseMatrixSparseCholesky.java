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

package org.tensorflow.op.linalg.sparse;

import org.tensorflow.DataType;
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
 * Computes the sparse Cholesky decomposition of `input`.
 * <p>
 * Computes the Sparse Cholesky decomposition of a sparse matrix, with the given
 * fill-in reducing permutation.
 * <p>
 * The input sparse matrix and the fill-in reducing permutation `permutation` must
 * have compatible shapes. If the sparse matrix has rank 3; with the batch
 * dimension `B`, then the `permutation` must be of rank 2; with the same batch
 * dimension `B`. There is no support for broadcasting.
 * <p>
 * Furthermore, each component vector of `permutation` must be of length `N`,
 * containing each of the integers {0, 1, ..., N - 1} exactly once, where `N` is
 * the number of rows of each component of the sparse matrix.
 * <p>
 * Each component of the input sparse matrix must represent a symmetric positive
 * definite (SPD) matrix; although only the lower triangular part of the matrix is
 * read. If any individual component is not SPD, then an InvalidArgument error is
 * thrown.
 * <p>
 * The returned sparse matrix has the same dense shape as the input sparse matrix.
 * For each component `A` of the input sparse matrix, the corresponding output
 * sparse matrix represents `L`, the lower triangular Cholesky factor satisfying
 * the following identity:
 * <pre>{@code
 *   A = L * Lt
 * }</pre>
 * where Lt denotes the transpose of L (or its conjugate transpose, if `type` is
 * `complex64` or `complex128`).
 * <p>
 * The `type` parameter denotes the type of the matrix elements. The supported
 * types are: `float32`, `float64`, `complex64` and `complex128`.
 * <p>
 * Usage example:
 * <pre>{@code
 *     from tensorflow.python.ops.linalg.sparse import sparse_csr_matrix_ops
 * 
 *     a_indices = np.array([[0, 0], [1, 1], [2, 1], [2, 2], [3, 3]])
 *     a_values = np.array([1.0, 2.0, 1.0, 3.0, 4.0], np.float32)
 *     a_dense_shape = [4, 4]
 * 
 *     with tf.Session() as sess:
 *       # Define (COO format) SparseTensor over Numpy array.
 *       a_st = tf.sparse.SparseTensor(a_indices, a_values, a_dense_shape)
 * 
 *       # Convert SparseTensors to CSR SparseMatrix.
 *       a_sm = sparse_csr_matrix_ops.sparse_tensor_to_csr_sparse_matrix(
 *           a_st.indices, a_st.values, a_st.dense_shape)
 * 
 *       # Obtain the Sparse Cholesky factor using AMD Ordering for reducing zero
 *       # fill-in (number of structural non-zeros in the sparse Cholesky factor).
 *       ordering_amd = sparse_csr_matrix_ops.sparse_matrix_ordering_amd(sparse_matrix)
 *       cholesky_sparse_matrices = (
 *           sparse_csr_matrix_ops.sparse_matrix_sparse_cholesky(
 *               sparse_matrix, ordering_amd, type=tf.float32))
 * 
 *       # Convert the CSRSparseMatrix Cholesky factor to a dense Tensor
 *       dense_cholesky = sparse_csr_matrix_ops.csr_sparse_matrix_to_dense(
 *           cholesky_sparse_matrices, tf.float32)
 * 
 *       # Evaluate the dense Tensor value.
 *       dense_cholesky_value = sess.run(dense_cholesky)
 * }</pre>
 * `dense_cholesky_value` stores the dense Cholesky factor:
 * <pre>{@code
 *     [[  1.  0.    0.    0.]
 *      [  0.  1.41  0.    0.]
 *      [  0.  0.70  1.58  0.]
 *      [  0.  0.    0.    2.]]
 * }</pre>
 * input: A `CSRSparseMatrix`.
 * permutation: A `Tensor`.
 * type: The type of `input`.
 */
public final class SparseMatrixSparseCholesky extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new SparseMatrixSparseCholesky operation.
   * 
   * @param scope current scope
   * @param input A `CSRSparseMatrix`.
   * @param permutation A fill-in reducing permutation matrix.
   * @param type 
   * @return a new instance of SparseMatrixSparseCholesky
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseMatrixSparseCholesky create(Scope scope, Operand<?> input, Operand<TInt32> permutation, DataType<T> type) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseMatrixSparseCholesky", scope.makeOpName("SparseMatrixSparseCholesky"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(permutation.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("type", type);
    return new SparseMatrixSparseCholesky(opBuilder.build());
  }
  
  /**
   * The sparse Cholesky decompsition of `input`.
   */
  public Output<?> output() {
    return output;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseMatrixSparseCholesky";
  
  private Output<?> output;
  
  private SparseMatrixSparseCholesky(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
