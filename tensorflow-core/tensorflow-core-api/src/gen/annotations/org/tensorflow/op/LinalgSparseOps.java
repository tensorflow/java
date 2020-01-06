package org.tensorflow.op;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
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

  LinalgSparseOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link SparseMatrixMul} operation
   *
   * @param a A CSRSparseMatrix.
   * @param b A dense tensor.
   * @return a new instance of SparseMatrixMul
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixMul
   */
  public <T extends TType> SparseMatrixMul sparseMatrixMul(Operand<?> a, Operand<T> b) {
    return SparseMatrixMul.create(scope, a, b);
  }

  /**
   * Builds an {@link SparseMatrixSoftmaxGrad} operation
   *
   * @param softmax A CSRSparseMatrix.
   * @param gradSoftmax The gradient of `softmax`.
   * @param type 
   * @return a new instance of SparseMatrixSoftmaxGrad
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixSoftmaxGrad
   */
  public <T extends TNumber> SparseMatrixSoftmaxGrad sparseMatrixSoftmaxGrad(Operand<?> softmax,
      Operand<?> gradSoftmax, DataType<T> type) {
    return SparseMatrixSoftmaxGrad.create(scope, softmax, gradSoftmax, type);
  }

  /**
   * Builds an {@link SparseMatrixZeros} operation
   *
   * @param denseShape The desired matrix shape.
   * @param type 
   * @return a new instance of SparseMatrixZeros
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixZeros
   */
  public <T extends TType> SparseMatrixZeros sparseMatrixZeros(Operand<TInt64> denseShape,
      DataType<T> type) {
    return SparseMatrixZeros.create(scope, denseShape, type);
  }

  /**
   * Builds an {@link SparseMatrixMatMul} operation
   *
   * @param a A CSRSparseMatrix.
   * @param b A dense tensor.
   * @param options carries optional attributes values
   * @return a new instance of SparseMatrixMatMul
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixMatMul
   */
  public <T extends TType> SparseMatrixMatMul<T> sparseMatrixMatMul(Operand<?> a, Operand<T> b,
      SparseMatrixMatMul.Options... options) {
    return SparseMatrixMatMul.create(scope, a, b, options);
  }

  /**
   * Builds an {@link SparseMatrixNNZ} operation
   *
   * @param sparseMatrix A CSRSparseMatrix.
   * @return a new instance of SparseMatrixNNZ
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixNNZ
   */
  public SparseMatrixNNZ sparseMatrixNNZ(Operand<?> sparseMatrix) {
    return SparseMatrixNNZ.create(scope, sparseMatrix);
  }

  /**
   * Builds an {@link DenseToCSRSparseMatrix} operation
   *
   * @param denseInput A Dense tensor.
   * @param indices Indices of nonzero elements.
   * @return a new instance of DenseToCSRSparseMatrix
   * @see org.tensorflow.op.linalg.sparse.DenseToCSRSparseMatrix
   */
  public <T extends TType> DenseToCSRSparseMatrix denseToCSRSparseMatrix(Operand<T> denseInput,
      Operand<TInt64> indices) {
    return DenseToCSRSparseMatrix.create(scope, denseInput, indices);
  }

  /**
   * Builds an {@link SparseMatrixSparseCholesky} operation
   *
   * @param input A `CSRSparseMatrix`.
   * @param permutation A fill-in reducing permutation matrix.
   * @param type 
   * @return a new instance of SparseMatrixSparseCholesky
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixSparseCholesky
   */
  public <T extends TType> SparseMatrixSparseCholesky sparseMatrixSparseCholesky(Operand<?> input,
      Operand<TInt32> permutation, DataType<T> type) {
    return SparseMatrixSparseCholesky.create(scope, input, permutation, type);
  }

  /**
   * Builds an {@link SparseTensorToCSRSparseMatrix} operation
   *
   * @param indices SparseTensor indices.
   * @param values SparseTensor values.
   * @param denseShape SparseTensor dense shape.
   * @return a new instance of SparseTensorToCSRSparseMatrix
   * @see org.tensorflow.op.linalg.sparse.SparseTensorToCSRSparseMatrix
   */
  public <T extends TType> SparseTensorToCSRSparseMatrix sparseTensorToCSRSparseMatrix(
      Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape) {
    return SparseTensorToCSRSparseMatrix.create(scope, indices, values, denseShape);
  }

  /**
   * Builds an {@link SparseMatrixOrderingAMD} operation
   *
   * @param input A `CSRSparseMatrix`.
   * @return a new instance of SparseMatrixOrderingAMD
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixOrderingAMD
   */
  public SparseMatrixOrderingAMD sparseMatrixOrderingAMD(Operand<?> input) {
    return SparseMatrixOrderingAMD.create(scope, input);
  }

  /**
   * Builds an {@link SparseMatrixSoftmax} operation
   *
   * @param logits A CSRSparseMatrix.
   * @param type 
   * @return a new instance of SparseMatrixSoftmax
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixSoftmax
   */
  public <T extends TNumber> SparseMatrixSoftmax sparseMatrixSoftmax(Operand<?> logits,
      DataType<T> type) {
    return SparseMatrixSoftmax.create(scope, logits, type);
  }

  /**
   * Builds an {@link SparseMatrixSparseMatMul} operation
   *
   * @param a A CSRSparseMatrix.
   * @param b A CSRSparseMatrix.
   * @param type 
   * @param options carries optional attributes values
   * @return a new instance of SparseMatrixSparseMatMul
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixSparseMatMul
   */
  public <T extends TType> SparseMatrixSparseMatMul sparseMatrixSparseMatMul(Operand<?> a,
      Operand<?> b, DataType<T> type, SparseMatrixSparseMatMul.Options... options) {
    return SparseMatrixSparseMatMul.create(scope, a, b, type, options);
  }

  /**
   * Builds an {@link SparseMatrixAdd} operation
   *
   * @param a A CSRSparseMatrix.
   * @param b A CSRSparseMatrix.
   * @param alpha A constant scalar.
   * @param beta A constant scalar.
   * @return a new instance of SparseMatrixAdd
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixAdd
   */
  public <T extends TType> SparseMatrixAdd sparseMatrixAdd(Operand<?> a, Operand<?> b,
      Operand<T> alpha, Operand<T> beta) {
    return SparseMatrixAdd.create(scope, a, b, alpha, beta);
  }

  /**
   * Builds an {@link SparseMatrixTranspose} operation
   *
   * @param input A CSRSparseMatrix.
   * @param type 
   * @param options carries optional attributes values
   * @return a new instance of SparseMatrixTranspose
   * @see org.tensorflow.op.linalg.sparse.SparseMatrixTranspose
   */
  public <T extends TType> SparseMatrixTranspose sparseMatrixTranspose(Operand<?> input,
      DataType<T> type, SparseMatrixTranspose.Options... options) {
    return SparseMatrixTranspose.create(scope, input, type, options);
  }

  /**
   * Builds an {@link CSRSparseMatrixToSparseTensor} operation
   *
   * @param sparseMatrix A (possibly batched) CSRSparseMatrix.
   * @param type 
   * @return a new instance of CSRSparseMatrixToSparseTensor
   * @see org.tensorflow.op.linalg.sparse.CSRSparseMatrixToSparseTensor
   */
  public <T extends TType> CSRSparseMatrixToSparseTensor<T> cSRSparseMatrixToSparseTensor(
      Operand<?> sparseMatrix, DataType<T> type) {
    return CSRSparseMatrixToSparseTensor.create(scope, sparseMatrix, type);
  }
}
