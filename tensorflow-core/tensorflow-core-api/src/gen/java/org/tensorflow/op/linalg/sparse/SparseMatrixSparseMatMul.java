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
import org.tensorflow.types.family.TType;

/**
 * Sparse-matrix-multiplies two CSR matrices `a` and `b`.
 * <p>
 * Performs a matrix multiplication of a sparse matrix `a` with a sparse matrix
 * `b`; returns a sparse matrix `a * b`, unless either `a` or `b` is transposed or
 * adjointed.
 * <p>
 * Each matrix may be transposed or adjointed (conjugated and transposed)
 * according to the Boolean parameters `transpose_a`, `adjoint_a`, `transpose_b`
 * and `adjoint_b`. At most one of `transpose_a` or `adjoint_a` may be True.
 * Similarly, at most one of `transpose_b` or `adjoint_b` may be True.
 * <p>
 * The inputs must have compatible shapes. That is, the inner dimension of `a`
 * must be equal to the outer dimension of `b`. This requirement is adjusted
 * according to whether either `a` or `b` is transposed or adjointed.
 * <p>
 * The `type` parameter denotes the type of the matrix elements. Both `a` and `b`
 * must have the same type. The supported types are: `float32`, `float64`,
 * `complex64` and `complex128`.
 * <p>
 * Both `a` and `b` must have the same rank. Broadcasting is not supported. If they
 * have rank 3, each batch of 2D CSRSparseMatrices within `a` and `b` must have the
 * same dense shape.
 * <p>
 * The sparse matrix product may have numeric (non-structural) zeros.
 * TODO(anudhyan): Consider adding a boolean attribute to control whether to prune
 * zeros.
 * <p>
 * Usage example:
 * <pre>{@code
 *     from tensorflow.python.ops.linalg.sparse import sparse_csr_matrix_ops
 * 
 *     a_indices = np.array([[0, 0], [2, 3], [2, 4], [3, 0]])
 *     a_values = np.array([1.0, 5.0, -1.0, -2.0], np.float32)
 *     a_dense_shape = [4, 5]
 * 
 *     b_indices = np.array([[0, 0], [3, 0], [3, 1]])
 *     b_values = np.array([2.0, 7.0, 8.0], np.float32)
 *     b_dense_shape = [5, 3]
 * 
 *     with tf.Session() as sess:
 *       # Define (COO format) Sparse Tensors over Numpy arrays
 *       a_st = tf.sparse.SparseTensor(a_indices, a_values, a_dense_shape)
 *       b_st = tf.sparse.SparseTensor(b_indices, b_values, b_dense_shape)
 * 
 *       # Convert SparseTensors to CSR SparseMatrix
 *       a_sm = sparse_csr_matrix_ops.sparse_tensor_to_csr_sparse_matrix(
 *           a_st.indices, a_st.values, a_st.dense_shape)
 *       b_sm = sparse_csr_matrix_ops.sparse_tensor_to_csr_sparse_matrix(
 *           b_st.indices, b_st.values, b_st.dense_shape)
 * 
 *       # Compute the CSR SparseMatrix matrix multiplication
 *       c_sm = sparse_csr_matrix_ops.sparse_matrix_sparse_mat_mul(
 *           a=a_sm, b=b_sm, type=tf.float32)
 * 
 *       # Convert the CSR SparseMatrix product to a dense Tensor
 *       c_sm_dense = sparse_csr_matrix_ops.csr_sparse_matrix_to_dense(
 *           c_sm, tf.float32)
 *       # Evaluate the dense Tensor value
 *       c_sm_dense_value = sess.run(c_sm_dense)
 * }</pre>
 * `c_sm_dense_value` stores the dense matrix product:
 * <pre>{@code
 *     [[  2.   0.   0.]
 *      [  0.   0.   0.]
 *      [ 35.  40.   0.]
 *      [ -4.   0.   0.]]
 * }</pre>
 * a: A `CSRSparseMatrix`.
 * b: A `CSRSparseMatrix` with the same type and rank as `a`.
 * type: The type of both `a` and `b`.
 * transpose_a: If True, `a` transposed before multiplication.
 * transpose_b: If True, `b` transposed before multiplication.
 * adjoint_a: If True, `a` adjointed before multiplication.
 * adjoint_b: If True, `b` adjointed before multiplication.
 */
public final class SparseMatrixSparseMatMul extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.sparse.SparseMatrixSparseMatMul}
   */
  public static class Options {
    
    /**
     * @param transposeA Indicates whether `a` should be transposed.
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }
    
    /**
     * @param transposeB Indicates whether `b` should be transposed.
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }
    
    /**
     * @param adjointA Indicates whether `a` should be conjugate-transposed.
     */
    public Options adjointA(Boolean adjointA) {
      this.adjointA = adjointA;
      return this;
    }
    
    /**
     * @param adjointB Indicates whether `b` should be conjugate-transposed.
     */
    public Options adjointB(Boolean adjointB) {
      this.adjointB = adjointB;
      return this;
    }
    
    private Boolean transposeA;
    private Boolean transposeB;
    private Boolean adjointA;
    private Boolean adjointB;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseMatrixSparseMatMul operation.
   * 
   * @param scope current scope
   * @param a A CSRSparseMatrix.
   * @param b A CSRSparseMatrix.
   * @param type 
   * @param options carries optional attributes values
   * @return a new instance of SparseMatrixSparseMatMul
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseMatrixSparseMatMul create(Scope scope, Operand<?> a, Operand<?> b, DataType<T> type, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseMatrixSparseMatMul", scope.makeOpName("SparseMatrixSparseMatMul"));
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("type", type);
    if (options != null) {
      for (Options opts : options) {
        if (opts.transposeA != null) {
          opBuilder.setAttr("transpose_a", opts.transposeA);
        }
        if (opts.transposeB != null) {
          opBuilder.setAttr("transpose_b", opts.transposeB);
        }
        if (opts.adjointA != null) {
          opBuilder.setAttr("adjoint_a", opts.adjointA);
        }
        if (opts.adjointB != null) {
          opBuilder.setAttr("adjoint_b", opts.adjointB);
        }
      }
    }
    return new SparseMatrixSparseMatMul(opBuilder.build());
  }
  
  /**
   * @param transposeA Indicates whether `a` should be transposed.
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }
  
  /**
   * @param transposeB Indicates whether `b` should be transposed.
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }
  
  /**
   * @param adjointA Indicates whether `a` should be conjugate-transposed.
   */
  public static Options adjointA(Boolean adjointA) {
    return new Options().adjointA(adjointA);
  }
  
  /**
   * @param adjointB Indicates whether `b` should be conjugate-transposed.
   */
  public static Options adjointB(Boolean adjointB) {
    return new Options().adjointB(adjointB);
  }
  
  /**
   * A CSRSparseMatrix.
   */
  public Output<?> c() {
    return c;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) c;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseMatrixSparseMatMul";
  
  private Output<?> c;
  
  private SparseMatrixSparseMatMul(Operation operation) {
    super(operation);
    int outputIdx = 0;
    c = operation.output(outputIdx++);
  }
}
