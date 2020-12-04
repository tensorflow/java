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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;

/**
 * Computes the Approximate Minimum Degree (AMD) ordering of `input`.
 * <p>
 * Computes the Approximate Minimum Degree (AMD) ordering for a sparse matrix.
 * <p>
 * The returned permutation may be used to permute the rows and columns of the
 * given sparse matrix. This typically results in permuted sparse matrix's sparse
 * Cholesky (or other decompositions) in having fewer zero fill-in compared to
 * decomposition of the original matrix.
 * <p>
 * The input sparse matrix may have rank 2 or rank 3. The output Tensor,
 * representing would then have rank 1 or 2 respectively, with the same batch
 * shape as the input.
 * <p>
 * Each component of the input sparse matrix must represent a square symmetric
 * matrix; only the lower triangular part of the matrix is read. The values of the
 * sparse matrix does not affect the returned permutation, only the sparsity
 * pattern of the sparse matrix is used. Hence, a single AMD ordering may be
 * reused for the Cholesky decompositions of sparse matrices with the same sparsity
 * pattern but with possibly different values.
 * <p>
 * Each batch component of the output permutation represents a permutation of `N`
 * elements, where the input sparse matrix components each have `N` rows. That is,
 * the component contains each of the integers `{0, .. N-1}` exactly once. The
 * `i`th element represents the row index that the `i`th row maps to.
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
 *       # Obtain the AMD Ordering for the CSR SparseMatrix.
 *       ordering_amd = sparse_csr_matrix_ops.sparse_matrix_ordering_amd(sparse_matrix)
 * 
 *       ordering_amd_value = sess.run(ordering_amd)
 * }</pre>
 * `ordering_amd_value` stores the AMD ordering: `[1 2 3 0]`.
 * <p>
 * input: A `CSRSparseMatrix`.
 */
public final class SparseMatrixOrderingAMD extends RawOp implements Operand<TInt32> {
  
  /**
   * Factory method to create a class wrapping a new SparseMatrixOrderingAMD operation.
   * 
   * @param scope current scope
   * @param input A `CSRSparseMatrix`.
   * @return a new instance of SparseMatrixOrderingAMD
   */
  @Endpoint(describeByClass = true)
  public static SparseMatrixOrderingAMD create(Scope scope, Operand<?> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseMatrixOrderingAMD", scope.makeOpName("SparseMatrixOrderingAMD"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseMatrixOrderingAMD(opBuilder.build());
  }
  
  /**
   * The Approximate Minimum Degree (AMD) ordering of `input`.
   */
  public Output<TInt32> output() {
    return output;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseMatrixOrderingAMD";
  
  private Output<TInt32> output;
  
  private SparseMatrixOrderingAMD(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
