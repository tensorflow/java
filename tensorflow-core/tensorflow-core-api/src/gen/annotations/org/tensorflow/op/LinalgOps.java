package org.tensorflow.op;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.linalg.BandPart;
import org.tensorflow.op.linalg.BatchCholesky;
import org.tensorflow.op.linalg.BatchCholeskyGrad;
import org.tensorflow.op.linalg.BatchMatrixBandPart;
import org.tensorflow.op.linalg.BatchMatrixDeterminant;
import org.tensorflow.op.linalg.BatchMatrixDiag;
import org.tensorflow.op.linalg.BatchMatrixDiagPart;
import org.tensorflow.op.linalg.BatchMatrixInverse;
import org.tensorflow.op.linalg.BatchMatrixSetDiag;
import org.tensorflow.op.linalg.BatchMatrixSolve;
import org.tensorflow.op.linalg.BatchMatrixSolveLs;
import org.tensorflow.op.linalg.BatchMatrixTriangularSolve;
import org.tensorflow.op.linalg.BatchSelfAdjointEig;
import org.tensorflow.op.linalg.BatchSvd;
import org.tensorflow.op.linalg.Cholesky;
import org.tensorflow.op.linalg.CholeskyGrad;
import org.tensorflow.op.linalg.ConjugateTranspose;
import org.tensorflow.op.linalg.Cross;
import org.tensorflow.op.linalg.Det;
import org.tensorflow.op.linalg.Eig;
import org.tensorflow.op.linalg.Einsum;
import org.tensorflow.op.linalg.EuclideanNorm;
import org.tensorflow.op.linalg.Inv;
import org.tensorflow.op.linalg.LoadAndRemapMatrix;
import org.tensorflow.op.linalg.LogMatrixDeterminant;
import org.tensorflow.op.linalg.Lu;
import org.tensorflow.op.linalg.MatMul;
import org.tensorflow.op.linalg.MatrixDiag;
import org.tensorflow.op.linalg.MatrixDiagPart;
import org.tensorflow.op.linalg.MatrixSetDiag;
import org.tensorflow.op.linalg.MatrixSolveLs;
import org.tensorflow.op.linalg.Qr;
import org.tensorflow.op.linalg.QuantizedMatMul;
import org.tensorflow.op.linalg.SelfAdjointEig;
import org.tensorflow.op.linalg.Solve;
import org.tensorflow.op.linalg.Sqrtm;
import org.tensorflow.op.linalg.Svd;
import org.tensorflow.op.linalg.TensorDiag;
import org.tensorflow.op.linalg.TensorDiagPart;
import org.tensorflow.op.linalg.Transpose;
import org.tensorflow.op.linalg.TriangularSolve;
import org.tensorflow.types.TDouble;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code linalg} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class LinalgOps {
  public final LinalgSparseOps sparse;

  private final Scope scope;

  LinalgOps(Scope scope) {
    this.scope = scope;
    sparse = new LinalgSparseOps(scope);
  }

  /**
   * Builds an {@link Transpose} operation
   *
   * @param x 
   * @param perm 
   * @return a new instance of Transpose
   * @see org.tensorflow.op.linalg.Transpose
   */
  public <T extends TType, U extends TNumber> Transpose<T> transpose(Operand<T> x,
      Operand<U> perm) {
    return Transpose.create(scope, x, perm);
  }

  /**
   * Builds an {@link BatchMatrixSolveLs} operation
   *
   * @param matrix 
   * @param rhs 
   * @param l2Regularizer 
   * @param options carries optional attributes values
   * @return a new instance of BatchMatrixSolveLs
   * @see org.tensorflow.op.linalg.BatchMatrixSolveLs
   */
  public <T extends TNumber> BatchMatrixSolveLs<T> batchMatrixSolveLs(Operand<T> matrix,
      Operand<T> rhs, Operand<TDouble> l2Regularizer, BatchMatrixSolveLs.Options... options) {
    return BatchMatrixSolveLs.create(scope, matrix, rhs, l2Regularizer, options);
  }

  /**
   * Builds an {@link LogMatrixDeterminant} operation
   *
   * @param input Shape is `[N, M, M]`.
   * @return a new instance of LogMatrixDeterminant
   * @see org.tensorflow.op.linalg.LogMatrixDeterminant
   */
  public <T extends TType> LogMatrixDeterminant<T> logMatrixDeterminant(Operand<T> input) {
    return LogMatrixDeterminant.create(scope, input);
  }

  /**
   * Builds an {@link TriangularSolve} operation
   *
   * @param matrix Shape is `[..., M, M]`.
   * @param rhs Shape is `[..., M, K]`.
   * @param options carries optional attributes values
   * @return a new instance of TriangularSolve
   * @see org.tensorflow.op.linalg.TriangularSolve
   */
  public <T extends TType> TriangularSolve<T> triangularSolve(Operand<T> matrix, Operand<T> rhs,
      TriangularSolve.Options... options) {
    return TriangularSolve.create(scope, matrix, rhs, options);
  }

  /**
   * Builds an {@link Svd} operation
   *
   * @param input A tensor of shape `[..., M, N]` whose inner-most 2 dimensions
   * @param options carries optional attributes values
   * @return a new instance of Svd
   * @see org.tensorflow.op.linalg.Svd
   */
  public <T extends TType> Svd<T> svd(Operand<T> input, Svd.Options... options) {
    return Svd.create(scope, input, options);
  }

  /**
   * Builds an {@link Lu} operation
   *
   * @param input A tensor of shape `[..., M, M]` whose inner-most 2 dimensions form matrices of
   * @param outputIdxType 
   * @return a new instance of Lu
   * @see org.tensorflow.op.linalg.Lu
   */
  public <T extends TType, U extends TNumber> Lu<T, U> lu(Operand<T> input,
      DataType<U> outputIdxType) {
    return Lu.create(scope, input, outputIdxType);
  }

  /**
   * Builds an {@link BatchMatrixInverse} operation
   *
   * @param input 
   * @param options carries optional attributes values
   * @return a new instance of BatchMatrixInverse
   * @see org.tensorflow.op.linalg.BatchMatrixInverse
   */
  public <T extends TNumber> BatchMatrixInverse<T> batchMatrixInverse(Operand<T> input,
      BatchMatrixInverse.Options... options) {
    return BatchMatrixInverse.create(scope, input, options);
  }

  /**
   * Builds an {@link Solve} operation
   *
   * @param matrix Shape is `[..., M, M]`.
   * @param rhs Shape is `[..., M, K]`.
   * @param options carries optional attributes values
   * @return a new instance of Solve
   * @see org.tensorflow.op.linalg.Solve
   */
  public <T extends TType> Solve<T> solve(Operand<T> matrix, Operand<T> rhs,
      Solve.Options... options) {
    return Solve.create(scope, matrix, rhs, options);
  }

  /**
   * Builds an {@link BatchMatrixDiagPart} operation
   *
   * @param input 
   * @return a new instance of BatchMatrixDiagPart
   * @see org.tensorflow.op.linalg.BatchMatrixDiagPart
   */
  public <T extends TType> BatchMatrixDiagPart<T> batchMatrixDiagPart(Operand<T> input) {
    return BatchMatrixDiagPart.create(scope, input);
  }

  /**
   * Builds an {@link Cross} operation
   *
   * @param a A tensor containing 3-element vectors.
   * @param b Another tensor, of same type and shape as `a`.
   * @return a new instance of Cross
   * @see org.tensorflow.op.linalg.Cross
   */
  public <T extends TNumber> Cross<T> cross(Operand<T> a, Operand<T> b) {
    return Cross.create(scope, a, b);
  }

  /**
   * Builds an {@link MatrixSolveLs} operation
   *
   * @param matrix Shape is `[..., M, N]`.
   * @param rhs Shape is `[..., M, K]`.
   * @param l2Regularizer Scalar tensor.
   * @param options carries optional attributes values
   * @return a new instance of MatrixSolveLs
   * @see org.tensorflow.op.linalg.MatrixSolveLs
   */
  public <T extends TType> MatrixSolveLs<T> matrixSolveLs(Operand<T> matrix, Operand<T> rhs,
      Operand<TDouble> l2Regularizer, MatrixSolveLs.Options... options) {
    return MatrixSolveLs.create(scope, matrix, rhs, l2Regularizer, options);
  }

  /**
   * Builds an {@link BatchMatrixSetDiag} operation
   *
   * @param input 
   * @param diagonal 
   * @return a new instance of BatchMatrixSetDiag
   * @see org.tensorflow.op.linalg.BatchMatrixSetDiag
   */
  public <T extends TType> BatchMatrixSetDiag<T> batchMatrixSetDiag(Operand<T> input,
      Operand<T> diagonal) {
    return BatchMatrixSetDiag.create(scope, input, diagonal);
  }

  /**
   * Builds an {@link CholeskyGrad} operation
   *
   * @param l Output of batch Cholesky algorithm l = cholesky(A). Shape is `[..., M, M]`.
   * @param grad df/dl where f is some scalar function. Shape is `[..., M, M]`.
   * @return a new instance of CholeskyGrad
   * @see org.tensorflow.op.linalg.CholeskyGrad
   */
  public <T extends TNumber> CholeskyGrad<T> choleskyGrad(Operand<T> l, Operand<T> grad) {
    return CholeskyGrad.create(scope, l, grad);
  }

  /**
   * Builds an {@link Qr} operation
   *
   * @param input A tensor of shape `[..., M, N]` whose inner-most 2 dimensions
   * @param options carries optional attributes values
   * @return a new instance of Qr
   * @see org.tensorflow.op.linalg.Qr
   */
  public <T extends TType> Qr<T> qr(Operand<T> input, Qr.Options... options) {
    return Qr.create(scope, input, options);
  }

  /**
   * Builds an {@link BatchSelfAdjointEig} operation
   *
   * @param input 
   * @param options carries optional attributes values
   * @return a new instance of BatchSelfAdjointEig
   * @see org.tensorflow.op.linalg.BatchSelfAdjointEig
   */
  public <T extends TNumber> BatchSelfAdjointEig<T> batchSelfAdjointEig(Operand<T> input,
      BatchSelfAdjointEig.Options... options) {
    return BatchSelfAdjointEig.create(scope, input, options);
  }

  /**
   * Builds an {@link Cholesky} operation
   *
   * @param input Shape is `[..., M, M]`.
   * @return a new instance of Cholesky
   * @see org.tensorflow.op.linalg.Cholesky
   */
  public <T extends TType> Cholesky<T> cholesky(Operand<T> input) {
    return Cholesky.create(scope, input);
  }

  /**
   * Builds an {@link MatrixSetDiag} operation
   *
   * @param input Rank `r+1`, where `r >= 1`.
   * @param diagonal Rank `r` when `k` is an integer or `k[0] == k[1]`. Otherwise, it has rank `r+1`.
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   * @return a new instance of MatrixSetDiag
   * @see org.tensorflow.op.linalg.MatrixSetDiag
   */
  public <T extends TType> MatrixSetDiag<T> matrixSetDiag(Operand<T> input, Operand<T> diagonal,
      Operand<TInt32> k) {
    return MatrixSetDiag.create(scope, input, diagonal, k);
  }

  /**
   * Builds an {@link BatchMatrixDiag} operation
   *
   * @param diagonal 
   * @return a new instance of BatchMatrixDiag
   * @see org.tensorflow.op.linalg.BatchMatrixDiag
   */
  public <T extends TType> BatchMatrixDiag<T> batchMatrixDiag(Operand<T> diagonal) {
    return BatchMatrixDiag.create(scope, diagonal);
  }

  /**
   * Builds an {@link LoadAndRemapMatrix} operation
   *
   * @param ckptPath Path to the TensorFlow checkpoint (version 2, `TensorBundle`) from
   * @param oldTensorName Name of the 2-D `Tensor` to load from checkpoint.
   * @param rowRemapping An int `Tensor` of row remappings (generally created by
   * @param colRemapping An int `Tensor` of column remappings (generally created by
   * @param initializingValues A float `Tensor` containing  values to fill in for cells
   * @param numRows Number of rows (length of the 1st dimension) in the output matrix.
   * @param numCols Number of columns (length of the 2nd dimension) in the output matrix.
   * @param options carries optional attributes values
   * @return a new instance of LoadAndRemapMatrix
   * @see org.tensorflow.op.linalg.LoadAndRemapMatrix
   */
  public LoadAndRemapMatrix loadAndRemapMatrix(Operand<TString> ckptPath,
      Operand<TString> oldTensorName, Operand<TInt64> rowRemapping, Operand<TInt64> colRemapping,
      Operand<TFloat> initializingValues, Long numRows, Long numCols,
      LoadAndRemapMatrix.Options... options) {
    return LoadAndRemapMatrix.create(scope, ckptPath, oldTensorName, rowRemapping, colRemapping, initializingValues, numRows, numCols, options);
  }

  /**
   * Builds an {@link Sqrtm} operation
   *
   * @param input Shape is `[..., M, M]`.
   * @return a new instance of Sqrtm
   * @see org.tensorflow.op.linalg.Sqrtm
   */
  public <T extends TType> Sqrtm<T> sqrtm(Operand<T> input) {
    return Sqrtm.create(scope, input);
  }

  /**
   * Builds an {@link MatrixDiagPart} operation
   *
   * @param input Rank `r` tensor where `r >= 2`.
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   * @param paddingValue The value to fill the area outside the specified diagonal band with.
   * @return a new instance of MatrixDiagPart
   * @see org.tensorflow.op.linalg.MatrixDiagPart
   */
  public <T extends TType> MatrixDiagPart<T> matrixDiagPart(Operand<T> input, Operand<TInt32> k,
      Operand<T> paddingValue) {
    return MatrixDiagPart.create(scope, input, k, paddingValue);
  }

  /**
   * Builds an {@link TensorDiag} operation
   *
   * @param diagonal Rank k tensor where k is at most 1.
   * @return a new instance of TensorDiag
   * @see org.tensorflow.op.linalg.TensorDiag
   */
  public <T extends TType> TensorDiag<T> tensorDiag(Operand<T> diagonal) {
    return TensorDiag.create(scope, diagonal);
  }

  /**
   * Builds an {@link BatchCholesky} operation
   *
   * @param input 
   * @return a new instance of BatchCholesky
   * @see org.tensorflow.op.linalg.BatchCholesky
   */
  public <T extends TNumber> BatchCholesky<T> batchCholesky(Operand<T> input) {
    return BatchCholesky.create(scope, input);
  }

  /**
   * Builds an {@link BatchMatrixSolve} operation
   *
   * @param matrix 
   * @param rhs 
   * @param options carries optional attributes values
   * @return a new instance of BatchMatrixSolve
   * @see org.tensorflow.op.linalg.BatchMatrixSolve
   */
  public <T extends TNumber> BatchMatrixSolve<T> batchMatrixSolve(Operand<T> matrix, Operand<T> rhs,
      BatchMatrixSolve.Options... options) {
    return BatchMatrixSolve.create(scope, matrix, rhs, options);
  }

  /**
   * Builds an {@link BatchMatrixDeterminant} operation
   *
   * @param input 
   * @return a new instance of BatchMatrixDeterminant
   * @see org.tensorflow.op.linalg.BatchMatrixDeterminant
   */
  public <T extends TType> BatchMatrixDeterminant<T> batchMatrixDeterminant(Operand<T> input) {
    return BatchMatrixDeterminant.create(scope, input);
  }

  /**
   * Builds an {@link Lu} operation
   *
   * @param input A tensor of shape `[..., M, M]` whose inner-most 2 dimensions form matrices of
   * @return a new instance of Lu
   * @see org.tensorflow.op.linalg.Lu
   */
  public <T extends TType> Lu<T, TInt32> lu(Operand<T> input) {
    return Lu.create(scope, input);
  }

  /**
   * Builds an {@link QuantizedMatMul} operation
   *
   * @param a Must be a two-dimensional tensor.
   * @param b Must be a two-dimensional tensor.
   * @param minA The float value that the lowest quantized `a` value represents.
   * @param maxA The float value that the highest quantized `a` value represents.
   * @param minB The float value that the lowest quantized `b` value represents.
   * @param maxB The float value that the highest quantized `b` value represents.
   * @param Toutput 
   * @param Tactivation The type of output produced by activation function
   * @param options carries optional attributes values
   * @return a new instance of QuantizedMatMul
   * @see org.tensorflow.op.linalg.QuantizedMatMul
   */
  public <V extends TType, T extends TType, U extends TType, W extends TType> QuantizedMatMul<V> quantizedMatMul(
      Operand<T> a, Operand<U> b, Operand<TFloat> minA, Operand<TFloat> maxA, Operand<TFloat> minB,
      Operand<TFloat> maxB, DataType<V> Toutput, DataType<W> Tactivation,
      QuantizedMatMul.Options... options) {
    return QuantizedMatMul.create(scope, a, b, minA, maxA, minB, maxB, Toutput, Tactivation, options);
  }

  /**
   * Builds an {@link EuclideanNorm} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of EuclideanNorm
   * @see org.tensorflow.op.linalg.EuclideanNorm
   */
  public <T extends TType, U extends TNumber> EuclideanNorm<T> euclideanNorm(Operand<T> input,
      Operand<U> axis, EuclideanNorm.Options... options) {
    return EuclideanNorm.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link SelfAdjointEig} operation
   *
   * @param input `Tensor` input of shape `[N, N]`.
   * @param options carries optional attributes values
   * @return a new instance of SelfAdjointEig
   * @see org.tensorflow.op.linalg.SelfAdjointEig
   */
  public <T extends TType> SelfAdjointEig<T> selfAdjointEig(Operand<T> input,
      SelfAdjointEig.Options... options) {
    return SelfAdjointEig.create(scope, input, options);
  }

  /**
   * Builds an {@link BandPart} operation
   *
   * @param input Rank `k` tensor.
   * @param numLower 0-D tensor. Number of subdiagonals to keep. If negative, keep entire
   * @param numUpper 0-D tensor. Number of superdiagonals to keep. If negative, keep
   * @return a new instance of BandPart
   * @see org.tensorflow.op.linalg.BandPart
   */
  public <T extends TType, U extends TNumber> BandPart<T> bandPart(Operand<T> input,
      Operand<U> numLower, Operand<U> numUpper) {
    return BandPart.create(scope, input, numLower, numUpper);
  }

  /**
   * Builds an {@link ConjugateTranspose} operation
   *
   * @param x 
   * @param perm 
   * @return a new instance of ConjugateTranspose
   * @see org.tensorflow.op.linalg.ConjugateTranspose
   */
  public <T extends TType, U extends TNumber> ConjugateTranspose<T> conjugateTranspose(Operand<T> x,
      Operand<U> perm) {
    return ConjugateTranspose.create(scope, x, perm);
  }

  /**
   * Builds an {@link Inv} operation
   *
   * @param input Shape is `[..., M, M]`.
   * @param options carries optional attributes values
   * @return a new instance of Inv
   * @see org.tensorflow.op.linalg.Inv
   */
  public <T extends TType> Inv<T> inv(Operand<T> input, Inv.Options... options) {
    return Inv.create(scope, input, options);
  }

  /**
   * Builds an {@link BatchMatrixBandPart} operation
   *
   * @param input 
   * @param numLower 
   * @param numUpper 
   * @return a new instance of BatchMatrixBandPart
   * @see org.tensorflow.op.linalg.BatchMatrixBandPart
   */
  public <T extends TType> BatchMatrixBandPart<T> batchMatrixBandPart(Operand<T> input,
      Operand<TInt64> numLower, Operand<TInt64> numUpper) {
    return BatchMatrixBandPart.create(scope, input, numLower, numUpper);
  }

  /**
   * Builds an {@link TensorDiagPart} operation
   *
   * @param input Rank k tensor where k is even and not zero.
   * @return a new instance of TensorDiagPart
   * @see org.tensorflow.op.linalg.TensorDiagPart
   */
  public <T extends TType> TensorDiagPart<T> tensorDiagPart(Operand<T> input) {
    return TensorDiagPart.create(scope, input);
  }

  /**
   * Builds an {@link BatchCholeskyGrad} operation
   *
   * @param l 
   * @param grad 
   * @return a new instance of BatchCholeskyGrad
   * @see org.tensorflow.op.linalg.BatchCholeskyGrad
   */
  public <T extends TNumber> BatchCholeskyGrad<T> batchCholeskyGrad(Operand<T> l, Operand<T> grad) {
    return BatchCholeskyGrad.create(scope, l, grad);
  }

  /**
   * Builds an {@link MatrixDiag} operation
   *
   * @param diagonal Rank `r`, where `r >= 1`
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   * @param numRows The number of rows of the output matrix. If it is not provided, the op assumes
   * @param numCols The number of columns of the output matrix. If it is not provided, the op
   * @param paddingValue The number to fill the area outside the specified diagonal band with.
   * @return a new instance of MatrixDiag
   * @see org.tensorflow.op.linalg.MatrixDiag
   */
  public <T extends TType> MatrixDiag<T> matrixDiag(Operand<T> diagonal, Operand<TInt32> k,
      Operand<TInt32> numRows, Operand<TInt32> numCols, Operand<T> paddingValue) {
    return MatrixDiag.create(scope, diagonal, k, numRows, numCols, paddingValue);
  }

  /**
   * Builds an {@link BatchSvd} operation
   *
   * @param input 
   * @param options carries optional attributes values
   * @return a new instance of BatchSvd
   * @see org.tensorflow.op.linalg.BatchSvd
   */
  public <T extends TType> BatchSvd<T> batchSvd(Operand<T> input, BatchSvd.Options... options) {
    return BatchSvd.create(scope, input, options);
  }

  /**
   * Builds an {@link MatMul} operation
   *
   * @param a 
   * @param b 
   * @param options carries optional attributes values
   * @return a new instance of MatMul
   * @see org.tensorflow.op.linalg.MatMul
   */
  public <T extends TType> MatMul<T> matMul(Operand<T> a, Operand<T> b, MatMul.Options... options) {
    return MatMul.create(scope, a, b, options);
  }

  /**
   * Builds an {@link Det} operation
   *
   * @param input Shape is `[..., M, M]`.
   * @return a new instance of Det
   * @see org.tensorflow.op.linalg.Det
   */
  public <T extends TType> Det<T> det(Operand<T> input) {
    return Det.create(scope, input);
  }

  /**
   * Builds an {@link Einsum} operation
   *
   * @param inputs List of 1 or 2 Tensors.
   * @param equation String describing the Einstein Summation operation; in the format of np.einsum.
   * @return a new instance of Einsum
   * @see org.tensorflow.op.linalg.Einsum
   */
  public <T extends TType> Einsum<T> einsum(Iterable<Operand<T>> inputs, String equation) {
    return Einsum.create(scope, inputs, equation);
  }

  /**
   * Builds an {@link BatchMatrixTriangularSolve} operation
   *
   * @param matrix 
   * @param rhs 
   * @param options carries optional attributes values
   * @return a new instance of BatchMatrixTriangularSolve
   * @see org.tensorflow.op.linalg.BatchMatrixTriangularSolve
   */
  public <T extends TNumber> BatchMatrixTriangularSolve<T> batchMatrixTriangularSolve(
      Operand<T> matrix, Operand<T> rhs, BatchMatrixTriangularSolve.Options... options) {
    return BatchMatrixTriangularSolve.create(scope, matrix, rhs, options);
  }

  /**
   * Builds an {@link Eig} operation
   *
   * @param input `Tensor` input of shape `[N, N]`.
   * @param Tout 
   * @param options carries optional attributes values
   * @return a new instance of Eig
   * @see org.tensorflow.op.linalg.Eig
   */
  public <U extends TType, T extends TType> Eig<U> eig(Operand<T> input, DataType<U> Tout,
      Eig.Options... options) {
    return Eig.create(scope, input, Tout, options);
  }
}
