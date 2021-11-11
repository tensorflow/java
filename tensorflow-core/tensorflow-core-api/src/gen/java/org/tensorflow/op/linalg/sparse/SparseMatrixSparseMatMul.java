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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Sparse-matrix-multiplies two CSR matrices {@code a} and {@code b}.
 * Performs a matrix multiplication of a sparse matrix {@code a} with a sparse matrix
 * {@code b}; returns a sparse matrix {@code a * b}, unless either {@code a} or {@code b} is transposed or
 * adjointed.
 * <p>Each matrix may be transposed or adjointed (conjugated and transposed)
 * according to the Boolean parameters {@code transpose_a}, {@code adjoint_a}, {@code transpose_b}
 * and {@code adjoint_b}. At most one of {@code transpose_a} or {@code adjoint_a} may be True.
 * Similarly, at most one of {@code transpose_b} or {@code adjoint_b} may be True.
 * <p>The inputs must have compatible shapes. That is, the inner dimension of {@code a}
 * must be equal to the outer dimension of {@code b}. This requirement is adjusted
 * according to whether either {@code a} or {@code b} is transposed or adjointed.
 * <p>The {@code type} parameter denotes the type of the matrix elements. Both {@code a} and {@code b}
 * must have the same type. The supported types are: {@code float32}, {@code float64},
 * {@code complex64} and {@code complex128}.
 * <p>Both {@code a} and {@code b} must have the same rank. Broadcasting is not supported. If they
 * have rank 3, each batch of 2D CSRSparseMatrices within {@code a} and {@code b} must have the
 * same dense shape.
 * <p>The sparse matrix product may have numeric (non-structural) zeros.
 * TODO(anudhyan): Consider adding a boolean attribute to control whether to prune
 * zeros.
 * <p>Usage example:
 * <pre>
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
 * </pre>
 * <p>{@code c_sm_dense_value} stores the dense matrix product:
 * <pre>
 *     [[  2.   0.   0.]
 *      [  0.   0.   0.]
 *      [ 35.  40.   0.]
 *      [ -4.   0.   0.]]
 * </pre>
 * <p>a: A {@code CSRSparseMatrix}.
 * b: A {@code CSRSparseMatrix} with the same type and rank as {@code a}.
 * type: The type of both {@code a} and {@code b}.
 * transpose_a: If True, {@code a} transposed before multiplication.
 * transpose_b: If True, {@code b} transposed before multiplication.
 * adjoint_a: If True, {@code a} adjointed before multiplication.
 * adjoint_b: If True, {@code b} adjointed before multiplication.
 */
@OpMetadata(
    opType = SparseMatrixSparseMatMul.OP_NAME,
    inputsClass = SparseMatrixSparseMatMul.Inputs.class
)
public final class SparseMatrixSparseMatMul extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixSparseMatMul";

  private Output<? extends TType> c;

  @SuppressWarnings("unchecked")
  public SparseMatrixSparseMatMul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    c = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixSparseMatMul operation.
   *
   * @param scope current scope
   * @param a A CSRSparseMatrix.
   * @param b A CSRSparseMatrix.
   * @param type The value of the type attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseMatrixSparseMatMul} output and operands
   * @return a new instance of SparseMatrixSparseMatMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseMatrixSparseMatMul create(Scope scope,
      Operand<? extends TType> a, Operand<? extends TType> b, Class<T> type, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseMatrixSparseMatMul");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.setAttr("type", Operands.toDataType(type));
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
   * Sets the transposeA option.
   *
   * @param transposeA Indicates whether {@code a} should be transposed.
   * @return this Options instance.
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }

  /**
   * Sets the transposeB option.
   *
   * @param transposeB Indicates whether {@code b} should be transposed.
   * @return this Options instance.
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }

  /**
   * Sets the adjointA option.
   *
   * @param adjointA Indicates whether {@code a} should be conjugate-transposed.
   * @return this Options instance.
   */
  public static Options adjointA(Boolean adjointA) {
    return new Options().adjointA(adjointA);
  }

  /**
   * Sets the adjointB option.
   *
   * @param adjointB Indicates whether {@code b} should be conjugate-transposed.
   * @return this Options instance.
   */
  public static Options adjointB(Boolean adjointB) {
    return new Options().adjointB(adjointB);
  }

  /**
   * Gets c.
   * A CSRSparseMatrix.
   * @return c.
   */
  public Output<? extends TType> c() {
    return c;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) c;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.sparse.SparseMatrixSparseMatMul}
   */
  public static class Options {
    private Boolean transposeA;

    private Boolean transposeB;

    private Boolean adjointA;

    private Boolean adjointB;

    private Options() {
    }

    /**
     * Sets the transposeA option.
     *
     * @param transposeA Indicates whether {@code a} should be transposed.
     * @return this Options instance.
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }

    /**
     * Sets the transposeB option.
     *
     * @param transposeB Indicates whether {@code b} should be transposed.
     * @return this Options instance.
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }

    /**
     * Sets the adjointA option.
     *
     * @param adjointA Indicates whether {@code a} should be conjugate-transposed.
     * @return this Options instance.
     */
    public Options adjointA(Boolean adjointA) {
      this.adjointA = adjointA;
      return this;
    }

    /**
     * Sets the adjointB option.
     *
     * @param adjointB Indicates whether {@code b} should be conjugate-transposed.
     * @return this Options instance.
     */
    public Options adjointB(Boolean adjointB) {
      this.adjointB = adjointB;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseMatrixSparseMatMul.class
  )
  public static class Inputs extends RawOpInputs<SparseMatrixSparseMatMul> {
    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> a;

    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> b;

    /**
     * The type attribute
     */
    public final DataType type;

    /**
     * Indicates whether `a` should be transposed.
     */
    public final boolean transposeA;

    /**
     * Indicates whether `b` should be transposed.
     */
    public final boolean transposeB;

    /**
     * Indicates whether `a` should be conjugate-transposed.
     */
    public final boolean adjointA;

    /**
     * Indicates whether `b` should be conjugate-transposed.
     */
    public final boolean adjointB;

    public Inputs(GraphOperation op) {
      super(new SparseMatrixSparseMatMul(op), op, Arrays.asList("type", "transpose_a", "transpose_b", "adjoint_a", "adjoint_b"));
      int inputIndex = 0;
      a = (Operand<? extends TType>) op.input(inputIndex++);
      b = (Operand<? extends TType>) op.input(inputIndex++);
      type = op.attributes().getAttrType("type");
      transposeA = op.attributes().getAttrBool("transpose_a");
      transposeB = op.attributes().getAttrBool("transpose_b");
      adjointA = op.attributes().getAttrBool("adjoint_a");
      adjointB = op.attributes().getAttrBool("adjoint_b");
    }
  }
}
