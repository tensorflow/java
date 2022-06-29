// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.op.linalg.MatrixDiagPartV3;
import org.tensorflow.op.linalg.MatrixDiagV3;
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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
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
  private final Scope scope;

  private final Ops ops;

  LinalgOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Copy a tensor setting everything outside a central band in each innermost matrix to zero.
   *  The {@code band} part is computed as follows:
   *  Assume {@code input} has {@code k} dimensions {@code [I, J, K, ..., M, N]}, then the output is a
   *  tensor with the same shape where
   *  <p>{@code band[i, j, k, ..., m, n] = in_band(m, n) * input[i, j, k, ..., m, n]}.
   *  <p>The indicator function
   *  <p>{@code in_band(m, n) = (num_lower < 0 || (m-n) <= num_lower)) && (num_upper < 0 || (n-m) <= num_upper)}.
   *  <p>For example:
   *  <pre>
   *  # if 'input' is [[ 0,  1,  2, 3]
   *  #                [-1,  0,  1, 2]
   *  #                [-2, -1,  0, 1]
   *  #                [-3, -2, -1, 0]],
   *
   *  tf.linalg.band_part(input, 1, -1) ==&gt; [[ 0,  1,  2, 3]
   *                                         [-1,  0,  1, 2]
   *                                         [ 0, -1,  0, 1]
   *                                         [ 0,  0, -1, 0]],
   *
   *  tf.linalg.band_part(input, 2, 1) ==&gt; [[ 0,  1,  0, 0]
   *                                        [-1,  0,  1, 0]
   *                                        [-2, -1,  0, 1]
   *                                        [ 0, -2, -1, 0]]
   *  </pre>
   *  <p>Useful special cases:
   *  <pre>
   *   tf.linalg.band_part(input, 0, -1) ==&gt; Upper triangular part.
   *   tf.linalg.band_part(input, -1, 0) ==&gt; Lower triangular part.
   *   tf.linalg.band_part(input, 0, 0) ==&gt; Diagonal.
   *  </pre>
   *
   * @param <T> data type for {@code band} output
   * @param input Rank {@code k} tensor.
   * @param numLower 0-D tensor. Number of subdiagonals to keep. If negative, keep entire
   *  lower triangle.
   * @param numUpper 0-D tensor. Number of superdiagonals to keep. If negative, keep
   *  entire upper triangle.
   * @param <T> data type for {@code MatrixBandPart} output and operands
   * @param <U> data type for {@code MatrixBandPart} output and operands
   * @return a new instance of BandPart
   */
  public <T extends TType, U extends TNumber> BandPart<T> bandPart(Operand<T> input,
      Operand<U> numLower, Operand<U> numUpper) {
    return BandPart.create(scope, input, numLower, numUpper);
  }

  /**
   * The BatchCholesky operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param <T> data type for {@code BatchCholesky} output and operands
   * @return a new instance of BatchCholesky
   */
  public <T extends TNumber> BatchCholesky<T> batchCholesky(Operand<T> input) {
    return BatchCholesky.create(scope, input);
  }

  /**
   * The BatchCholeskyGrad operation
   *
   * @param <T> data type for {@code output} output
   * @param l The l value
   * @param grad The grad value
   * @param <T> data type for {@code BatchCholeskyGrad} output and operands
   * @return a new instance of BatchCholeskyGrad
   */
  public <T extends TNumber> BatchCholeskyGrad<T> batchCholeskyGrad(Operand<T> l, Operand<T> grad) {
    return BatchCholeskyGrad.create(scope, l, grad);
  }

  /**
   * The BatchMatrixBandPart operation
   *
   * @param <T> data type for {@code band} output
   * @param input The input value
   * @param numLower The numLower value
   * @param numUpper The numUpper value
   * @param <T> data type for {@code BatchMatrixBandPart} output and operands
   * @return a new instance of BatchMatrixBandPart
   */
  public <T extends TType> BatchMatrixBandPart<T> batchMatrixBandPart(Operand<T> input,
      Operand<TInt64> numLower, Operand<TInt64> numUpper) {
    return BatchMatrixBandPart.create(scope, input, numLower, numUpper);
  }

  /**
   * The BatchMatrixDeterminant operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param <T> data type for {@code BatchMatrixDeterminant} output and operands
   * @return a new instance of BatchMatrixDeterminant
   */
  public <T extends TType> BatchMatrixDeterminant<T> batchMatrixDeterminant(Operand<T> input) {
    return BatchMatrixDeterminant.create(scope, input);
  }

  /**
   * The BatchMatrixDiag operation
   *
   * @param <T> data type for {@code output} output
   * @param diagonal The diagonal value
   * @param <T> data type for {@code BatchMatrixDiag} output and operands
   * @return a new instance of BatchMatrixDiag
   */
  public <T extends TType> BatchMatrixDiag<T> batchMatrixDiag(Operand<T> diagonal) {
    return BatchMatrixDiag.create(scope, diagonal);
  }

  /**
   * The BatchMatrixDiagPart operation
   *
   * @param <T> data type for {@code diagonal} output
   * @param input The input value
   * @param <T> data type for {@code BatchMatrixDiagPart} output and operands
   * @return a new instance of BatchMatrixDiagPart
   */
  public <T extends TType> BatchMatrixDiagPart<T> batchMatrixDiagPart(Operand<T> input) {
    return BatchMatrixDiagPart.create(scope, input);
  }

  /**
   * The BatchMatrixInverse operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchMatrixInverse} output and operands
   * @return a new instance of BatchMatrixInverse
   */
  public <T extends TNumber> BatchMatrixInverse<T> batchMatrixInverse(Operand<T> input,
      BatchMatrixInverse.Options... options) {
    return BatchMatrixInverse.create(scope, input, options);
  }

  /**
   * The BatchMatrixSetDiag operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param diagonal The diagonal value
   * @param <T> data type for {@code BatchMatrixSetDiag} output and operands
   * @return a new instance of BatchMatrixSetDiag
   */
  public <T extends TType> BatchMatrixSetDiag<T> batchMatrixSetDiag(Operand<T> input,
      Operand<T> diagonal) {
    return BatchMatrixSetDiag.create(scope, input, diagonal);
  }

  /**
   * The BatchMatrixSolve operation
   *
   * @param <T> data type for {@code output} output
   * @param matrix The matrix value
   * @param rhs The rhs value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchMatrixSolve} output and operands
   * @return a new instance of BatchMatrixSolve
   */
  public <T extends TNumber> BatchMatrixSolve<T> batchMatrixSolve(Operand<T> matrix, Operand<T> rhs,
      BatchMatrixSolve.Options... options) {
    return BatchMatrixSolve.create(scope, matrix, rhs, options);
  }

  /**
   * The BatchMatrixSolveLs operation
   *
   * @param <T> data type for {@code output} output
   * @param matrix The matrix value
   * @param rhs The rhs value
   * @param l2Regularizer The l2Regularizer value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchMatrixSolveLs} output and operands
   * @return a new instance of BatchMatrixSolveLs
   */
  public <T extends TNumber> BatchMatrixSolveLs<T> batchMatrixSolveLs(Operand<T> matrix,
      Operand<T> rhs, Operand<TFloat64> l2Regularizer, BatchMatrixSolveLs.Options... options) {
    return BatchMatrixSolveLs.create(scope, matrix, rhs, l2Regularizer, options);
  }

  /**
   * The BatchMatrixTriangularSolve operation
   *
   * @param <T> data type for {@code output} output
   * @param matrix The matrix value
   * @param rhs The rhs value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchMatrixTriangularSolve} output and operands
   * @return a new instance of BatchMatrixTriangularSolve
   */
  public <T extends TNumber> BatchMatrixTriangularSolve<T> batchMatrixTriangularSolve(
      Operand<T> matrix, Operand<T> rhs, BatchMatrixTriangularSolve.Options... options) {
    return BatchMatrixTriangularSolve.create(scope, matrix, rhs, options);
  }

  /**
   * The BatchSelfAdjointEigV2 operation
   *
   * @param <T> data type for {@code e} output
   * @param input The input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchSelfAdjointEigV2} output and operands
   * @return a new instance of BatchSelfAdjointEig
   */
  public <T extends TNumber> BatchSelfAdjointEig<T> batchSelfAdjointEig(Operand<T> input,
      BatchSelfAdjointEig.Options... options) {
    return BatchSelfAdjointEig.create(scope, input, options);
  }

  /**
   * The BatchSvd operation
   *
   * @param <T> data type for {@code s} output
   * @param input The input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchSvd} output and operands
   * @return a new instance of BatchSvd
   */
  public <T extends TType> BatchSvd<T> batchSvd(Operand<T> input, BatchSvd.Options... options) {
    return BatchSvd.create(scope, input, options);
  }

  /**
   * Computes the Cholesky decomposition of one or more square matrices.
   *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
   *  form square matrices.
   *  <p>The input has to be symmetric and positive definite. Only the lower-triangular
   *  part of the input will be used for this operation. The upper-triangular part
   *  will not be read.
   *  <p>The output is a tensor of the same shape as the input
   *  containing the Cholesky decompositions for all input submatrices {@code [..., :, :]}.
   *  <p><strong>Note</strong>: The gradient computation on GPU is faster for large matrices but
   *  not for large batch dimensions when the submatrices are small. In this
   *  case it might be faster to use the CPU.
   *
   * @param <T> data type for {@code output} output
   * @param input Shape is {@code [..., M, M]}.
   * @param <T> data type for {@code Cholesky} output and operands
   * @return a new instance of Cholesky
   */
  public <T extends TType> Cholesky<T> cholesky(Operand<T> input) {
    return Cholesky.create(scope, input);
  }

  /**
   * Computes the reverse mode backpropagated gradient of the Cholesky algorithm.
   *  For an explanation see &quot;Differentiation of the Cholesky algorithm&quot; by
   *  Iain Murray http://arxiv.org/abs/1602.07527.
   *
   * @param <T> data type for {@code output} output
   * @param l Output of batch Cholesky algorithm l = cholesky(A). Shape is {@code [..., M, M]}.
   *  Algorithm depends only on lower triangular part of the innermost matrices of
   *  this tensor.
   * @param grad df/dl where f is some scalar function. Shape is {@code [..., M, M]}.
   *  Algorithm depends only on lower triangular part of the innermost matrices of
   *  this tensor.
   * @param <T> data type for {@code CholeskyGrad} output and operands
   * @return a new instance of CholeskyGrad
   */
  public <T extends TNumber> CholeskyGrad<T> choleskyGrad(Operand<T> l, Operand<T> grad) {
    return CholeskyGrad.create(scope, l, grad);
  }

  /**
   * Shuffle dimensions of x according to a permutation and conjugate the result.
   *  The output {@code y} has the same rank as {@code x}. The shapes of {@code x} and {@code y} satisfy:
   *  {@code y.shape[i] == x.shape[perm[i]] for i in [0, 1, ..., rank(x) - 1]}
   *  {@code y[i,j,k,...,s,t,u] == conj(x[perm[i], perm[j], perm[k],...,perm[s], perm[t], perm[u]])}
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param perm The perm value
   * @param <T> data type for {@code ConjugateTranspose} output and operands
   * @return a new instance of ConjugateTranspose
   */
  public <T extends TType> ConjugateTranspose<T> conjugateTranspose(Operand<T> x,
      Operand<? extends TNumber> perm) {
    return ConjugateTranspose.create(scope, x, perm);
  }

  /**
   * Compute the pairwise cross product.
   *  {@code a} and {@code b} must be the same shape; they can either be simple 3-element vectors,
   *  or any shape where the innermost dimension is 3. In the latter case, each pair
   *  of corresponding 3-element vectors is cross-multiplied independently.
   *
   * @param <T> data type for {@code product} output
   * @param a A tensor containing 3-element vectors.
   * @param b Another tensor, of same type and shape as {@code a}.
   * @param <T> data type for {@code Cross} output and operands
   * @return a new instance of Cross
   */
  public <T extends TNumber> Cross<T> cross(Operand<T> a, Operand<T> b) {
    return Cross.create(scope, a, b);
  }

  /**
   * Computes the determinant of one or more square matrices.
   *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
   *  form square matrices. The output is a tensor containing the determinants
   *  for all input submatrices {@code [..., :, :]}.
   *
   * @param <T> data type for {@code output} output
   * @param input Shape is {@code [..., M, M]}.
   * @param <T> data type for {@code MatrixDeterminant} output and operands
   * @return a new instance of Det
   */
  public <T extends TType> Det<T> det(Operand<T> input) {
    return Det.create(scope, input);
  }

  /**
   * Computes the eigen decomposition of one or more square matrices.
   *  Computes the eigenvalues and (optionally) right eigenvectors of each inner matrix in
   *  {@code input} such that {@code input[..., :, :] = v[..., :, :] * diag(e[..., :])}. The eigenvalues
   *  are sorted in non-decreasing order.
   *  <pre>
   *  # a is a tensor.
   *  # e is a tensor of eigenvalues.
   *  # v is a tensor of eigenvectors.
   *  e, v = eig(a)
   *  e = eig(a, compute_v=False)
   *  </pre>
   *
   * @param <U> data type for {@code e} output
   * @param input {@code Tensor} input of shape {@code [N, N]}.
   * @param Tout The value of the Tout attribute
   * @param options carries optional attribute values
   * @param <U> data type for {@code Eig} output and operands
   * @return a new instance of Eig
   */
  public <U extends TType> Eig<U> eig(Operand<? extends TType> input, Class<U> Tout,
      Eig.Options... options) {
    return Eig.create(scope, input, Tout, options);
  }

  /**
   * Tensor contraction according to Einstein summation convention.
   *  Implements generalized Tensor contraction and reduction. Each input Tensor must
   *  have a corresponding input subscript appearing in the comma-separated left-hand
   *  side of the equation. The right-hand side of the equation consists of the
   *  output subscript. The input subscripts and the output subscript should consist
   *  of zero or more named axis labels and at most one ellipsis ({@code ...}).
   *  <p>The named axis labels may be any single character other than those having
   *  special meaning, namely {@code ,.->}. The behavior of this Op is undefined if it
   *  receives an ill-formatted equation; since the validation is done at
   *  graph-building time, we omit format validation checks at runtime.
   *  <p>Note: This Op is <em>not</em> intended to be called by the user; instead users should
   *  call {@code tf.einsum} directly. It is a hidden Op used by {@code tf.einsum}.
   *  <p>Operations are applied to the input(s) according to the following rules:
   *  <p>(a) Generalized Diagonals: For input dimensions corresponding to axis labels
   *  appearing more than once in the same input subscript, we take the
   *  generalized ({@code k}-dimensional) diagonal.
   *  For example, in the equation {@code iii->i} with input shape {@code [3, 3, 3]}, the
   *  generalized diagonal would consist of {@code 3} elements at indices {@code (0, 0, 0)},
   *  {@code (1, 1, 1)} and {@code (2, 2, 2)} to create a Tensor of shape {@code [3]}.
   *  <p>(b) Reduction: Axes corresponding to labels appearing only in one input
   *  subscript but not in the output subscript are summed over prior to Tensor
   *  contraction.
   *  For example, in the equation {@code ab,bc->b}, the axis labels {@code a} and {@code c} are
   *  the reduction axis labels.
   *  <p>(c) Batch Dimensions: Axes corresponding to labels appearing in each of the
   *  input subscripts and also in the output subscript make up the batch
   *  dimensions in Tensor contraction. Unnamed axis labels corresponding to
   *  ellipsis ({@code ...}) also correspond to batch dimensions.
   *  For example, for the equation denoting batch matrix multiplication,
   *  {@code bij,bjk->bik}, the axis label {@code b} corresponds to a batch dimension.
   *  <p>(d) Contraction: In case of binary einsum, axes corresponding to labels
   *  appearing in two different inputs (and not in the output) are contracted
   *  against each other.
   *  Considering the batch matrix multiplication equation again
   *  ({@code bij,bjk->bik}), the contracted axis label is {@code j}.
   *  <p>(e) Expand Diagonal: If the output subscripts contain repeated (explicit) axis
   *  labels, the opposite operation of (a) is applied. For example, in the
   *  equation {@code i->iii}, and input shape {@code [3]}, the output of shape {@code [3, 3, 3]}
   *  are all zeros, except for the (generalized) diagonal which is populated
   *  with values from the input.
   *  Note: This operation is not supported by {@code np.einsum} or {@code tf.einsum}; it is
   *  provided to enable computing the symbolic gradient of {@code tf.einsum}.
   *  <p>The output subscripts must contain only labels appearing in at least one of the
   *  input subscripts. Furthermore, all dimensions mapping to the same axis label
   *  must be equal.
   *  <p>Any of the input and output subscripts may contain at most a single ellipsis
   *  ({@code ...}). These ellipsis are mapped against dimensions not corresponding to any
   *  named axis label. If two inputs contain ellipsis, then they are broadcasted
   *  according to standard NumPy broadcasting
   *   <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">rules</a> .
   *  <p>The broadcasted dimensions are placed in the corresponding location of the
   *  ellipsis in the output subscript. If the broadcasted dimensions are non-empty
   *  and the output subscripts do not contain ellipsis, then an InvalidArgument error
   *  is raised.
   *  <p>{@literal @}compatibility(numpy)<br>
   *  Similar to  <a href="https://docs.scipy.org/doc/numpy/reference/generated/numpy.einsum.html">{@code numpy.einsum}</a> .
   *  <p>Comparison with {@code numpy.einsum}:
   *  <ul>
   *  <li>This Op only supports unary and binary forms of {@code numpy.einsum}.</li>
   *  <li>This Op does not support implicit form. (i.e. equations without {@code ->}).</li>
   *  <li>This Op also supports repeated indices in the output subscript, which is not
   *  supported by {@code numpy.einsum}.
   *  <br>{@literal @}end_compatibility</li>
   *  </ul>
   *
   * @param <T> data type for {@code output} output
   * @param inputs List of 1 or 2 Tensors.
   * @param equation String describing the Einstein Summation operation; in the format of np.einsum.
   * @param <T> data type for {@code Einsum} output and operands
   * @return a new instance of Einsum
   */
  public <T extends TType> Einsum<T> einsum(Iterable<Operand<T>> inputs, String equation) {
    return Einsum.create(scope, inputs, equation);
  }

  /**
   * Computes the euclidean norm of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code EuclideanNorm} output and operands
   * @return a new instance of EuclideanNorm
   */
  public <T extends TType> EuclideanNorm<T> euclideanNorm(Operand<T> input,
      Operand<? extends TNumber> axis, EuclideanNorm.Options... options) {
    return EuclideanNorm.create(scope, input, axis, options);
  }

  /**
   * Computes the inverse of one or more square invertible matrices or their adjoints (conjugate transposes).
   *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
   *  form square matrices. The output is a tensor of the same shape as the input
   *  containing the inverse for all input submatrices {@code [..., :, :]}.
   *  <p>The op uses LU decomposition with partial pivoting to compute the inverses.
   *  <p>If a matrix is not invertible there is no guarantee what the op does. It
   *  may detect the condition and raise an exception or it may simply return a
   *  garbage result.
   *
   * @param <T> data type for {@code output} output
   * @param input Shape is {@code [..., M, M]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixInverse} output and operands
   * @return a new instance of Inv
   */
  public <T extends TType> Inv<T> inv(Operand<T> input, Inv.Options... options) {
    return Inv.create(scope, input, options);
  }

  /**
   * Loads a 2-D (matrix) {@code Tensor} with name {@code old_tensor_name} from the checkpoint
   *  at {@code ckpt_path} and potentially reorders its rows and columns using the
   *  specified remappings.
   *  <p>Most users should use one of the wrapper initializers (such as
   *  {@code tf.contrib.framework.load_and_remap_matrix_initializer}) instead of this
   *  function directly.
   *  <p>The remappings are 1-D tensors with the following properties:
   *  <ul>
   *  <li>{@code row_remapping} must have exactly {@code num_rows} entries. Row {@code i} of the output
   *  matrix will be initialized from the row corresponding to index
   *  {@code row_remapping[i]} in the old {@code Tensor} from the checkpoint.</li>
   *  <li>{@code col_remapping} must have either 0 entries (indicating that no column
   *  reordering is needed) or {@code num_cols} entries. If specified, column {@code j} of the
   *  output matrix will be initialized from the column corresponding to index
   *  {@code col_remapping[j]} in the old {@code Tensor} from the checkpoint.</li>
   *  <li>A value of -1 in either of the remappings signifies a &quot;missing&quot; entry. In that
   *  case, values from the {@code initializing_values} tensor will be used to fill that
   *  missing row or column. If {@code row_remapping} has {@code r} missing entries and
   *  {@code col_remapping} has {@code c} missing entries, then the following condition must be
   *  true:</li>
   *  </ul>
   *  <p>{@code (r * num_cols) + (c * num_rows) - (r * c) == len(initializing_values)}
   *  <p>The remapping tensors can be generated using the GenerateVocabRemapping op.
   *  <p>As an example, with row_remapping = [1, 0, -1], col_remapping = [0, 2, -1],
   *  initializing_values = [0.5, -0.5, 0.25, -0.25, 42], and w(i, j) representing
   *  the value from row i, column j of the old tensor in the checkpoint, the output
   *  matrix will look like the following:
   *  <p>[[w(1, 0),  w(1, 2),  0.5],
   *  [w(0, 0),  w(0, 2), -0.5],
   *  [0.25,    -0.25,      42]]
   *
   * @param ckptPath Path to the TensorFlow checkpoint (version 2, {@code TensorBundle}) from
   *  which the old matrix {@code Tensor} will be loaded.
   * @param oldTensorName Name of the 2-D {@code Tensor} to load from checkpoint.
   * @param rowRemapping An int {@code Tensor} of row remappings (generally created by
   *  {@code generate_vocab_remapping}).  Even if no row remapping is needed, this must
   *  still be an index-valued Tensor (e.g. [0, 1, 2, ...]), or a shifted
   *  index-valued {@code Tensor} (e.g. [8, 9, 10, ...], for partitioned {@code Variables}).
   * @param colRemapping An int {@code Tensor} of column remappings (generally created by
   *  {@code generate_vocab_remapping}).  May be a size-0 {@code Tensor} if only row remapping
   *  is to be done (e.g. column ordering is the same).
   * @param initializingValues A float {@code Tensor} containing  values to fill in for cells
   *  in the output matrix that are not loaded from the checkpoint. Length must be
   *  exactly the same as the number of missing / new cells.
   * @param numRows Number of rows (length of the 1st dimension) in the output matrix.
   * @param numCols Number of columns (length of the 2nd dimension) in the output matrix.
   * @param options carries optional attribute values
   * @return a new instance of LoadAndRemapMatrix
   */
  public LoadAndRemapMatrix loadAndRemapMatrix(Operand<TString> ckptPath,
      Operand<TString> oldTensorName, Operand<TInt64> rowRemapping, Operand<TInt64> colRemapping,
      Operand<TFloat32> initializingValues, Long numRows, Long numCols,
      LoadAndRemapMatrix.Options... options) {
    return LoadAndRemapMatrix.create(scope, ckptPath, oldTensorName, rowRemapping, colRemapping, initializingValues, numRows, numCols, options);
  }

  /**
   * Computes the sign and the log of the absolute value of the determinant of
   *  one or more square matrices.
   *  <p>The input is a tensor of shape {@code [N, M, M]} whose inner-most 2 dimensions
   *  form square matrices. The outputs are two tensors containing the signs and
   *  absolute values of the log determinants for all N input submatrices
   *  {@code [..., :, :]} such that {@code determinant = sign*exp(log_abs_determinant)}.
   *  The {@code log_abs_determinant} is computed as {@code det(P)*sum(log(diag(LU)))} where {@code LU}
   *  is the {@code LU} decomposition of the input and {@code P} is the corresponding
   *  permutation matrix.
   *
   * @param <T> data type for {@code sign} output
   * @param input Shape is {@code [N, M, M]}.
   * @param <T> data type for {@code LogMatrixDeterminant} output and operands
   * @return a new instance of LogMatrixDeterminant
   */
  public <T extends TType> LogMatrixDeterminant<T> logMatrixDeterminant(Operand<T> input) {
    return LogMatrixDeterminant.create(scope, input);
  }

  /**
   * Computes the LU decomposition of one or more square matrices.
   *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
   *  form square matrices.
   *  <p>The input has to be invertible.
   *  <p>The output consists of two tensors LU and P containing the LU decomposition
   *  of all input submatrices {@code [..., :, :]}. LU encodes the lower triangular and
   *  upper triangular factors.
   *  <p>For each input submatrix of shape {@code [M, M]}, L is a lower triangular matrix of
   *  shape {@code [M, M]} with unit diagonal whose entries correspond to the strictly lower
   *  triangular part of LU. U is a upper triangular matrix of shape {@code [M, M]} whose
   *  entries correspond to the upper triangular part, including the diagonal, of LU.
   *  <p>P represents a permutation matrix encoded as a list of indices each between {@code 0}
   *  and {@code M-1}, inclusive. If P_mat denotes the permutation matrix corresponding to
   *  P, then the L, U and P satisfies P_mat * input = L * U.
   *
   * @param <T> data type for {@code lu} output
   * @param <U> data type for {@code p} output
   * @param input A tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions form matrices of
   *  size {@code [M, M]}.
   * @param <T> data type for {@code Lu} output and operands
   * @return a new instance of Lu, with default output types
   */
  public <T extends TType> Lu<T, TInt32> lu(Operand<T> input) {
    return Lu.create(scope, input);
  }

  /**
   * Computes the LU decomposition of one or more square matrices.
   *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
   *  form square matrices.
   *  <p>The input has to be invertible.
   *  <p>The output consists of two tensors LU and P containing the LU decomposition
   *  of all input submatrices {@code [..., :, :]}. LU encodes the lower triangular and
   *  upper triangular factors.
   *  <p>For each input submatrix of shape {@code [M, M]}, L is a lower triangular matrix of
   *  shape {@code [M, M]} with unit diagonal whose entries correspond to the strictly lower
   *  triangular part of LU. U is a upper triangular matrix of shape {@code [M, M]} whose
   *  entries correspond to the upper triangular part, including the diagonal, of LU.
   *  <p>P represents a permutation matrix encoded as a list of indices each between {@code 0}
   *  and {@code M-1}, inclusive. If P_mat denotes the permutation matrix corresponding to
   *  P, then the L, U and P satisfies P_mat * input = L * U.
   *
   * @param <T> data type for {@code lu} output
   * @param <U> data type for {@code p} output
   * @param input A tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions form matrices of
   *  size {@code [M, M]}.
   * @param outputIdxType The value of the outputIdxType attribute
   * @param <T> data type for {@code Lu} output and operands
   * @param <U> data type for {@code Lu} output and operands
   * @return a new instance of Lu
   */
  public <T extends TType, U extends TNumber> Lu<T, U> lu(Operand<T> input,
      Class<U> outputIdxType) {
    return Lu.create(scope, input, outputIdxType);
  }

  /**
   * Multiply the matrix &quot;a&quot; by the matrix &quot;b&quot;.
   *  The inputs must be two-dimensional matrices and the inner dimension of
   *  &quot;a&quot; (after being transposed if transpose_a is true) must match the
   *  outer dimension of &quot;b&quot; (after being transposed if transposed_b is
   *  true).
   *  <p><em>Note</em>: The default kernel implementation for MatMul on GPUs uses
   *  cublas.
   *
   * @param <T> data type for {@code product} output
   * @param a The a value
   * @param b The b value
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatMul} output and operands
   * @return a new instance of MatMul
   */
  public <T extends TType> MatMul<T> matMul(Operand<T> a, Operand<T> b, MatMul.Options... options) {
    return MatMul.create(scope, a, b, options);
  }

  /**
   * Returns a batched diagonal tensor with given batched diagonal values.
   *  Returns a tensor with the contents in {@code diagonal} as {@code k[0]}-th to {@code k[1]}-th
   *  diagonals of a matrix, with everything else padded with {@code padding}. {@code num_rows}
   *  and {@code num_cols} specify the dimension of the innermost matrix of the output. If
   *  both are not specified, the op assumes the innermost matrix is square and infers
   *  its size from {@code k} and the innermost dimension of {@code diagonal}. If only one of them
   *  is specified, the op assumes the unspecified value is the smallest possible
   *  based on other criteria.
   *  <p>Let {@code diagonal} have {@code r} dimensions {@code [I, J, ..., L, M, N]}. The output tensor has
   *  rank {@code r+1} with shape {@code [I, J, ..., L, M, num_rows, num_cols]} when only one
   *  diagonal is given ({@code k} is an integer or {@code k[0] == k[1]}). Otherwise, it has rank
   *  {@code r} with shape {@code [I, J, ..., L, num_rows, num_cols]}.
   *  <p>The second innermost dimension of {@code diagonal} has double meaning.
   *  When {@code k} is scalar or {@code k[0] == k[1]}, {@code M} is part of the batch size
   *  [I, J, ..., M], and the output tensor is:
   *  <pre>
   *  output[i, j, ..., l, m, n]
   *    = diagonal[i, j, ..., l, n-max(d_upper, 0)] ; if n - m == d_upper
   *      padding_value                             ; otherwise
   *  </pre>
   *  <p>Otherwise, {@code M} is treated as the number of diagonals for the matrix in the
   *  same batch ({@code M = k[1]-k[0]+1}), and the output tensor is:
   *  <pre>
   *  output[i, j, ..., l, m, n]
   *    = diagonal[i, j, ..., l, diag_index, index_in_diag] ; if k[0] &lt;= d &lt;= k[1]
   *      padding_value                                     ; otherwise
   *  </pre>
   *  <p>where {@code d = n - m}, {@code diag_index = k[1] - d}, and {@code index_in_diag = n - max(d, 0)}.
   *  <p>For example:
   *  <pre>
   *  # The main diagonal.
   *  diagonal = np.array([[1, 2, 3, 4],            # Input shape: (2, 4)
   *                       [5, 6, 7, 8]])
   *  tf.matrix_diag(diagonal) ==&gt; [[[1, 0, 0, 0],  # Output shape: (2, 4, 4)
   *                                 [0, 2, 0, 0],
   *                                 [0, 0, 3, 0],
   *                                 [0, 0, 0, 4]],
   *                                [[5, 0, 0, 0],
   *                                 [0, 6, 0, 0],
   *                                 [0, 0, 7, 0],
   *                                 [0, 0, 0, 8]]]
   *
   *  # A superdiagonal (per batch).
   *  diagonal = np.array([[1, 2, 3],  # Input shape: (2, 3)
   *                       [4, 5, 6]])
   *  tf.matrix_diag(diagonal, k = 1)
   *    ==&gt; [[[0, 1, 0, 0],  # Output shape: (2, 4, 4)
   *          [0, 0, 2, 0],
   *          [0, 0, 0, 3],
   *          [0, 0, 0, 0]],
   *         [[0, 4, 0, 0],
   *          [0, 0, 5, 0],
   *          [0, 0, 0, 6],
   *          [0, 0, 0, 0]]]
   *
   *  # A band of diagonals.
   *  diagonals = np.array([[[1, 2, 3],  # Input shape: (2, 2, 3)
   *                         [4, 5, 0]],
   *                        [[6, 7, 9],
   *                         [9, 1, 0]]])
   *  tf.matrix_diag(diagonals, k = (-1, 0))
   *    ==&gt; [[[1, 0, 0],  # Output shape: (2, 3, 3)
   *          [4, 2, 0],
   *          [0, 5, 3]],
   *         [[6, 0, 0],
   *          [9, 7, 0],
   *          [0, 1, 9]]]
   *
   *  # Rectangular matrix.
   *  diagonal = np.array([1, 2])  # Input shape: (2)
   *  tf.matrix_diag(diagonal, k = -1, num_rows = 3, num_cols = 4)
   *    ==&gt; [[0, 0, 0, 0],  # Output shape: (3, 4)
   *         [1, 0, 0, 0],
   *         [0, 2, 0, 0]]
   *
   *  # Rectangular matrix with inferred num_cols and padding_value = 9.
   *  tf.matrix_diag(diagonal, k = -1, num_rows = 3, padding_value = 9)
   *    ==&gt; [[9, 9],  # Output shape: (3, 2)
   *         [1, 9],
   *         [9, 2]]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param diagonal Rank {@code r}, where {@code r >= 1}
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   *  diagonal, and negative value means subdiagonals. {@code k} can be a single integer
   *  (for a single diagonal) or a pair of integers specifying the low and high ends
   *  of a matrix band. {@code k[0]} must not be larger than {@code k[1]}.
   * @param numRows The number of rows of the output matrix. If it is not provided, the op assumes
   *  the output matrix is a square matrix and infers the matrix size from k and the
   *  innermost dimension of {@code diagonal}.
   * @param numCols The number of columns of the output matrix. If it is not provided, the op
   *  assumes the output matrix is a square matrix and infers the matrix size from
   *  k and the innermost dimension of {@code diagonal}.
   * @param paddingValue The number to fill the area outside the specified diagonal band with.
   *  Default is 0.
   * @param <T> data type for {@code MatrixDiagV2} output and operands
   * @return a new instance of MatrixDiag
   */
  public <T extends TType> MatrixDiag<T> matrixDiag(Operand<T> diagonal, Operand<TInt32> k,
      Operand<TInt32> numRows, Operand<TInt32> numCols, Operand<T> paddingValue) {
    return MatrixDiag.create(scope, diagonal, k, numRows, numCols, paddingValue);
  }

  /**
   * Returns the batched diagonal part of a batched tensor.
   *  Returns a tensor with the {@code k[0]}-th to {@code k[1]}-th diagonals of the batched
   *  {@code input}.
   *  <p>Assume {@code input} has {@code r} dimensions {@code [I, J, ..., L, M, N]}.
   *  Let {@code max_diag_len} be the maximum length among all diagonals to be extracted,
   *  {@code max_diag_len = min(M + min(k[1], 0), N + min(-k[0], 0))}
   *  Let {@code num_diags} be the number of diagonals to extract,
   *  {@code num_diags = k[1] - k[0] + 1}.
   *  <p>If {@code num_diags == 1}, the output tensor is of rank {@code r - 1} with shape
   *  {@code [I, J, ..., L, max_diag_len]} and values:
   *  <pre>
   *  diagonal[i, j, ..., l, n]
   *    = input[i, j, ..., l, n+y, n+x] ; if 0 &lt;= n+y &lt; M and 0 &lt;= n+x &lt; N,
   *      padding_value                 ; otherwise.
   *  </pre>
   *  <p>where {@code y = max(-k[1], 0)}, {@code x = max(k[1], 0)}.
   *  <p>Otherwise, the output tensor has rank {@code r} with dimensions
   *  {@code [I, J, ..., L, num_diags, max_diag_len]} with values:
   *  <pre>
   *  diagonal[i, j, ..., l, m, n]
   *    = input[i, j, ..., l, n+y, n+x] ; if 0 &lt;= n+y &lt; M and 0 &lt;= n+x &lt; N,
   *      padding_value                 ; otherwise.
   *  </pre>
   *  <p>where {@code d = k[1] - m}, {@code y = max(-d, 0)}, and {@code x = max(d, 0)}.
   *  <p>The input must be at least a matrix.
   *  <p>For example:
   *  <pre>
   *  input = np.array([[[1, 2, 3, 4],  # Input shape: (2, 3, 4)
   *                     [5, 6, 7, 8],
   *                     [9, 8, 7, 6]],
   *                    [[5, 4, 3, 2],
   *                     [1, 2, 3, 4],
   *                     [5, 6, 7, 8]]])
   *
   *  # A main diagonal from each batch.
   *  tf.matrix_diag_part(input) ==&gt; [[1, 6, 7],  # Output shape: (2, 3)
   *                                  [5, 2, 7]]
   *
   *  # A superdiagonal from each batch.
   *  tf.matrix_diag_part(input, k = 1)
   *    ==&gt; [[2, 7, 6],  # Output shape: (2, 3)
   *         [4, 3, 8]]
   *
   *  # A tridiagonal band from each batch.
   *  tf.matrix_diag_part(input, k = (-1, 1))
   *    ==&gt; [[[2, 7, 6],  # Output shape: (2, 3, 3)
   *          [1, 6, 7],
   *          [5, 8, 0]],
   *         [[4, 3, 8],
   *          [5, 2, 7],
   *          [1, 6, 0]]]
   *
   *  # Padding value = 9
   *  tf.matrix_diag_part(input, k = (1, 3), padding_value = 9)
   *    ==&gt; [[[4, 9, 9],  # Output shape: (2, 3, 3)
   *          [3, 8, 9],
   *          [2, 7, 6]],
   *         [[2, 9, 9],
   *          [3, 4, 9],
   *          [4, 3, 8]]]
   *  </pre>
   *
   * @param <T> data type for {@code diagonal} output
   * @param input Rank {@code r} tensor where {@code r >= 2}.
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   *  diagonal, and negative value means subdiagonals. {@code k} can be a single integer
   *  (for a single diagonal) or a pair of integers specifying the low and high ends
   *  of a matrix band. {@code k[0]} must not be larger than {@code k[1]}.
   * @param paddingValue The value to fill the area outside the specified diagonal band with.
   *  Default is 0.
   * @param <T> data type for {@code MatrixDiagPartV2} output and operands
   * @return a new instance of MatrixDiagPart
   */
  public <T extends TType> MatrixDiagPart<T> matrixDiagPart(Operand<T> input, Operand<TInt32> k,
      Operand<T> paddingValue) {
    return MatrixDiagPart.create(scope, input, k, paddingValue);
  }

  /**
   * Returns the batched diagonal part of a batched tensor.
   *  Returns a tensor with the {@code k[0]}-th to {@code k[1]}-th diagonals of the batched
   *  {@code input}.
   *  <p>Assume {@code input} has {@code r} dimensions {@code [I, J, ..., L, M, N]}.
   *  Let {@code max_diag_len} be the maximum length among all diagonals to be extracted,
   *  {@code max_diag_len = min(M + min(k[1], 0), N + min(-k[0], 0))}
   *  Let {@code num_diags} be the number of diagonals to extract,
   *  {@code num_diags = k[1] - k[0] + 1}.
   *  <p>If {@code num_diags == 1}, the output tensor is of rank {@code r - 1} with shape
   *  {@code [I, J, ..., L, max_diag_len]} and values:
   *  <pre>
   *  diagonal[i, j, ..., l, n]
   *    = input[i, j, ..., l, n+y, n+x] ; if 0 &lt;= n+y &lt; M and 0 &lt;= n+x &lt; N,
   *      padding_value                 ; otherwise.
   *  </pre>
   *  <p>where {@code y = max(-k[1], 0)}, {@code x = max(k[1], 0)}.
   *  <p>Otherwise, the output tensor has rank {@code r} with dimensions
   *  {@code [I, J, ..., L, num_diags, max_diag_len]} with values:
   *  <pre>
   *  diagonal[i, j, ..., l, m, n]
   *    = input[i, j, ..., l, n+y, n+x] ; if 0 &lt;= n+y &lt; M and 0 &lt;= n+x &lt; N,
   *      padding_value                 ; otherwise.
   *  </pre>
   *  <p>where {@code d = k[1] - m}, {@code y = max(-d, 0) - offset}, and {@code x = max(d, 0) - offset}.
   *  <p>{@code offset} is zero except when the alignment of the diagonal is to the right.
   *  <pre>
   *  offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
   *                                             and `d &gt;= 0`) or
   *                                           (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
   *                                             and `d &lt;= 0`)
   *           0                          ; otherwise
   *  </pre>
   *  <p>where {@code diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))}.
   *  <p>The input must be at least a matrix.
   *  <p>For example:
   *  <pre>
   *  input = np.array([[[1, 2, 3, 4],  # Input shape: (2, 3, 4)
   *                     [5, 6, 7, 8],
   *                     [9, 8, 7, 6]],
   *                    [[5, 4, 3, 2],
   *                     [1, 2, 3, 4],
   *                     [5, 6, 7, 8]]])
   *
   *  # A main diagonal from each batch.
   *  tf.matrix_diag_part(input) ==&gt; [[1, 6, 7],  # Output shape: (2, 3)
   *                                  [5, 2, 7]]
   *
   *  # A superdiagonal from each batch.
   *  tf.matrix_diag_part(input, k = 1)
   *    ==&gt; [[2, 7, 6],  # Output shape: (2, 3)
   *         [4, 3, 8]]
   *
   *  # A band from each batch.
   *  tf.matrix_diag_part(input, k = (-1, 2))
   *    ==&gt; [[[0, 3, 8],  # Output shape: (2, 4, 3)
   *          [2, 7, 6],
   *          [1, 6, 7],
   *          [5, 8, 0]],
   *         [[0, 3, 4],
   *          [4, 3, 8],
   *          [5, 2, 7],
   *          [1, 6, 0]]]
   *
   *  # LEFT_RIGHT alignment.
   *  tf.matrix_diag_part(input, k = (-1, 2), align=&quot;LEFT_RIGHT&quot;)
   *    ==&gt; [[[3, 8, 0],  # Output shape: (2, 4, 3)
   *          [2, 7, 6],
   *          [1, 6, 7],
   *          [0, 5, 8]],
   *         [[3, 4, 0],
   *          [4, 3, 8],
   *          [5, 2, 7],
   *          [0, 1, 6]]]
   *
   *  # max_diag_len can be shorter than the main diagonal.
   *  tf.matrix_diag_part(input, k = (-2, -1))
   *    ==&gt; [[[5, 8],
   *          [9, 0]],
   *         [[1, 6],
   *          [5, 0]]]
   *
   *  # padding_value = 9
   *  tf.matrix_diag_part(input, k = (1, 3), padding_value = 9)
   *    ==&gt; [[[9, 9, 4],  # Output shape: (2, 3, 3)
   *          [9, 3, 8],
   *          [2, 7, 6]],
   *         [[9, 9, 2],
   *          [9, 3, 4],
   *          [4, 3, 8]]]
   *
   *  </pre>
   *
   * @param <T> data type for {@code diagonal} output
   * @param input Rank {@code r} tensor where {@code r >= 2}.
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   *  diagonal, and negative value means subdiagonals. {@code k} can be a single integer
   *  (for a single diagonal) or a pair of integers specifying the low and high ends
   *  of a matrix band. {@code k[0]} must not be larger than {@code k[1]}.
   * @param paddingValue The value to fill the area outside the specified diagonal band with.
   *  Default is 0.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixDiagPartV3} output and operands
   * @return a new instance of MatrixDiagPartV3
   */
  public <T extends TType> MatrixDiagPartV3<T> matrixDiagPartV3(Operand<T> input, Operand<TInt32> k,
      Operand<T> paddingValue, MatrixDiagPartV3.Options... options) {
    return MatrixDiagPartV3.create(scope, input, k, paddingValue, options);
  }

  /**
   * Returns a batched diagonal tensor with given batched diagonal values.
   *  Returns a tensor with the contents in {@code diagonal} as {@code k[0]}-th to {@code k[1]}-th
   *  diagonals of a matrix, with everything else padded with {@code padding}. {@code num_rows}
   *  and {@code num_cols} specify the dimension of the innermost matrix of the output. If
   *  both are not specified, the op assumes the innermost matrix is square and infers
   *  its size from {@code k} and the innermost dimension of {@code diagonal}. If only one of them
   *  is specified, the op assumes the unspecified value is the smallest possible
   *  based on other criteria.
   *  <p>Let {@code diagonal} have {@code r} dimensions {@code [I, J, ..., L, M, N]}. The output tensor has
   *  rank {@code r+1} with shape {@code [I, J, ..., L, M, num_rows, num_cols]} when only one
   *  diagonal is given ({@code k} is an integer or {@code k[0] == k[1]}). Otherwise, it has rank
   *  {@code r} with shape {@code [I, J, ..., L, num_rows, num_cols]}.
   *  <p>The second innermost dimension of {@code diagonal} has double meaning.
   *  When {@code k} is scalar or {@code k[0] == k[1]}, {@code M} is part of the batch size
   *  [I, J, ..., M], and the output tensor is:
   *  <pre>
   *  output[i, j, ..., l, m, n]
   *    = diagonal[i, j, ..., l, n-max(d_upper, 0)] ; if n - m == d_upper
   *      padding_value                             ; otherwise
   *  </pre>
   *  <p>Otherwise, {@code M} is treated as the number of diagonals for the matrix in the
   *  same batch ({@code M = k[1]-k[0]+1}), and the output tensor is:
   *  <pre>
   *  output[i, j, ..., l, m, n]
   *    = diagonal[i, j, ..., l, diag_index, index_in_diag] ; if k[0] &lt;= d &lt;= k[1]
   *      padding_value                                     ; otherwise
   *  </pre>
   *  <p>where {@code d = n - m}, {@code diag_index = [k] - d}, and
   *  {@code index_in_diag = n - max(d, 0) + offset}.
   *  <p>{@code offset} is zero except when the alignment of the diagonal is to the right.
   *  <pre>
   *  offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
   *                                             and `d &gt;= 0`) or
   *                                           (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
   *                                             and `d &lt;= 0`)
   *           0                          ; otherwise
   *  </pre>
   *  <p>where {@code diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))}.
   *  <p>For example:
   *  <pre>
   *  # The main diagonal.
   *  diagonal = np.array([[1, 2, 3, 4],            # Input shape: (2, 4)
   *                       [5, 6, 7, 8]])
   *  tf.matrix_diag(diagonal) ==&gt; [[[1, 0, 0, 0],  # Output shape: (2, 4, 4)
   *                                 [0, 2, 0, 0],
   *                                 [0, 0, 3, 0],
   *                                 [0, 0, 0, 4]],
   *                                [[5, 0, 0, 0],
   *                                 [0, 6, 0, 0],
   *                                 [0, 0, 7, 0],
   *                                 [0, 0, 0, 8]]]
   *
   *  # A superdiagonal (per batch).
   *  diagonal = np.array([[1, 2, 3],  # Input shape: (2, 3)
   *                       [4, 5, 6]])
   *  tf.matrix_diag(diagonal, k = 1)
   *    ==&gt; [[[0, 1, 0, 0],  # Output shape: (2, 4, 4)
   *          [0, 0, 2, 0],
   *          [0, 0, 0, 3],
   *          [0, 0, 0, 0]],
   *         [[0, 4, 0, 0],
   *          [0, 0, 5, 0],
   *          [0, 0, 0, 6],
   *          [0, 0, 0, 0]]]
   *
   *  # A tridiagonal band (per batch).
   *  diagonals = np.array([[[0, 8, 9],  # Input shape: (2, 2, 3)
   *                         [1, 2, 3],
   *                         [4, 5, 0]],
   *                        [[0, 2, 3],
   *                         [6, 7, 9],
   *                         [9, 1, 0]]])
   *  tf.matrix_diag(diagonals, k = (-1, 1))
   *    ==&gt; [[[1, 8, 0],  # Output shape: (2, 3, 3)
   *          [4, 2, 9],
   *          [0, 5, 3]],
   *         [[6, 2, 0],
   *          [9, 7, 3],
   *          [0, 1, 9]]]
   *
   *  # LEFT_RIGHT alignment.
   *  diagonals = np.array([[[8, 9, 0],  # Input shape: (2, 2, 3)
   *                         [1, 2, 3],
   *                         [0, 4, 5]],
   *                        [[2, 3, 0],
   *                         [6, 7, 9],
   *                         [0, 9, 1]]])
   *  tf.matrix_diag(diagonals, k = (-1, 1), align=&quot;LEFT_RIGHT&quot;)
   *    ==&gt; [[[1, 8, 0],  # Output shape: (2, 3, 3)
   *          [4, 2, 9],
   *          [0, 5, 3]],
   *         [[6, 2, 0],
   *          [9, 7, 3],
   *          [0, 1, 9]]]
   *
   *  # Rectangular matrix.
   *  diagonal = np.array([1, 2])  # Input shape: (2)
   *  tf.matrix_diag(diagonal, k = -1, num_rows = 3, num_cols = 4)
   *    ==&gt; [[0, 0, 0, 0],  # Output shape: (3, 4)
   *         [1, 0, 0, 0],
   *         [0, 2, 0, 0]]
   *
   *  # Rectangular matrix with inferred num_cols and padding_value = 9.
   *  tf.matrix_diag(diagonal, k = -1, num_rows = 3, padding_value = 9)
   *    ==&gt; [[9, 9],  # Output shape: (3, 2)
   *         [1, 9],
   *         [9, 2]]
   *
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param diagonal Rank {@code r}, where {@code r >= 1}
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   *  diagonal, and negative value means subdiagonals. {@code k} can be a single integer
   *  (for a single diagonal) or a pair of integers specifying the low and high ends
   *  of a matrix band. {@code k[0]} must not be larger than {@code k[1]}.
   * @param numRows The number of rows of the output matrix. If it is not provided, the op assumes
   *  the output matrix is a square matrix and infers the matrix size from k and the
   *  innermost dimension of {@code diagonal}.
   * @param numCols The number of columns of the output matrix. If it is not provided, the op
   *  assumes the output matrix is a square matrix and infers the matrix size from
   *  k and the innermost dimension of {@code diagonal}.
   * @param paddingValue The number to fill the area outside the specified diagonal band with.
   *  Default is 0.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixDiagV3} output and operands
   * @return a new instance of MatrixDiagV3
   */
  public <T extends TType> MatrixDiagV3<T> matrixDiagV3(Operand<T> diagonal, Operand<TInt32> k,
      Operand<TInt32> numRows, Operand<TInt32> numCols, Operand<T> paddingValue,
      MatrixDiagV3.Options... options) {
    return MatrixDiagV3.create(scope, diagonal, k, numRows, numCols, paddingValue, options);
  }

  /**
   * Returns a batched matrix tensor with new batched diagonal values.
   *  Given {@code input} and {@code diagonal}, this operation returns a tensor with the
   *  same shape and values as {@code input}, except for the specified diagonals of the
   *  innermost matrices. These will be overwritten by the values in {@code diagonal}.
   *  <p>{@code input} has {@code r+1} dimensions {@code [I, J, ..., L, M, N]}. When {@code k} is scalar or
   *  {@code k[0] == k[1]}, {@code diagonal} has {@code r} dimensions {@code [I, J, ..., L, max_diag_len]}.
   *  Otherwise, it has {@code r+1} dimensions {@code [I, J, ..., L, num_diags, max_diag_len]}.
   *  {@code num_diags} is the number of diagonals, {@code num_diags = k[1] - k[0] + 1}.
   *  {@code max_diag_len} is the longest diagonal in the range {@code [k[0], k[1]]},
   *  {@code max_diag_len = min(M + min(k[1], 0), N + min(-k[0], 0))}
   *  <p>The output is a tensor of rank {@code k+1} with dimensions {@code [I, J, ..., L, M, N]}.
   *  If {@code k} is scalar or {@code k[0] == k[1]}:
   *  <pre>
   *  output[i, j, ..., l, m, n]
   *    = diagonal[i, j, ..., l, n-max(k[1], 0)] ; if n - m == k[1]
   *      input[i, j, ..., l, m, n]              ; otherwise
   *  </pre>
   *  <p>Otherwise,
   *  <pre>
   *  output[i, j, ..., l, m, n]
   *    = diagonal[i, j, ..., l, diag_index, index_in_diag] ; if k[0] &lt;= d &lt;= k[1]
   *      input[i, j, ..., l, m, n]                         ; otherwise
   *  </pre>
   *  <p>where {@code d = n - m}, {@code diag_index = k[1] - d}, and
   *  {@code index_in_diag = n - max(d, 0) + offset}.
   *  <p>{@code offset} is zero except when the alignment of the diagonal is to the right.
   *  <pre>
   *  offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
   *                                             and `d &gt;= 0`) or
   *                                           (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
   *                                             and `d &lt;= 0`)
   *           0                          ; otherwise
   *  </pre>
   *  <p>where {@code diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))}.
   *  <p>For example:
   *  <pre>
   *  # The main diagonal.
   *  input = np.array([[[7, 7, 7, 7],              # Input shape: (2, 3, 4)
   *                     [7, 7, 7, 7],
   *                     [7, 7, 7, 7]],
   *                    [[7, 7, 7, 7],
   *                     [7, 7, 7, 7],
   *                     [7, 7, 7, 7]]])
   *  diagonal = np.array([[1, 2, 3],               # Diagonal shape: (2, 3)
   *                       [4, 5, 6]])
   *  tf.matrix_set_diag(input, diagonal)
   *    ==&gt; [[[1, 7, 7, 7],  # Output shape: (2, 3, 4)
   *          [7, 2, 7, 7],
   *          [7, 7, 3, 7]],
   *         [[4, 7, 7, 7],
   *          [7, 5, 7, 7],
   *          [7, 7, 6, 7]]]
   *
   *  # A superdiagonal (per batch).
   *  tf.matrix_set_diag(input, diagonal, k = 1)
   *    ==&gt; [[[7, 1, 7, 7],  # Output shape: (2, 3, 4)
   *          [7, 7, 2, 7],
   *          [7, 7, 7, 3]],
   *         [[7, 4, 7, 7],
   *          [7, 7, 5, 7],
   *          [7, 7, 7, 6]]]
   *
   *  # A band of diagonals.
   *  diagonals = np.array([[[0, 9, 1],  # Diagonal shape: (2, 4, 3)
   *                         [6, 5, 8],
   *                         [1, 2, 3],
   *                         [4, 5, 0]],
   *                        [[0, 1, 2],
   *                         [5, 6, 4],
   *                         [6, 1, 2],
   *                         [3, 4, 0]]])
   *  tf.matrix_set_diag(input, diagonals, k = (-1, 2))
   *    ==&gt; [[[1, 6, 9, 7],  # Output shape: (2, 3, 4)
   *          [4, 2, 5, 1],
   *          [7, 5, 3, 8]],
   *         [[6, 5, 1, 7],
   *          [3, 1, 6, 2],
   *          [7, 4, 2, 4]]]
   *
   *  # LEFT_RIGHT alignment.
   *  diagonals = np.array([[[9, 1, 0],  # Diagonal shape: (2, 4, 3)
   *                         [6, 5, 8],
   *                         [1, 2, 3],
   *                         [0, 4, 5]],
   *                        [[1, 2, 0],
   *                         [5, 6, 4],
   *                         [6, 1, 2],
   *                         [0, 3, 4]]])
   *  tf.matrix_set_diag(input, diagonals, k = (-1, 2), align=&quot;LEFT_RIGHT&quot;)
   *    ==&gt; [[[1, 6, 9, 7],  # Output shape: (2, 3, 4)
   *          [4, 2, 5, 1],
   *          [7, 5, 3, 8]],
   *         [[6, 5, 1, 7],
   *          [3, 1, 6, 2],
   *          [7, 4, 2, 4]]]
   *
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input Rank {@code r+1}, where {@code r >= 1}.
   * @param diagonal Rank {@code r} when {@code k} is an integer or {@code k[0] == k[1]}. Otherwise, it has rank {@code r+1}.
   *  {@code k >= 1}.
   * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
   *  diagonal, and negative value means subdiagonals. {@code k} can be a single integer
   *  (for a single diagonal) or a pair of integers specifying the low and high ends
   *  of a matrix band. {@code k[0]} must not be larger than {@code k[1]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixSetDiagV3} output and operands
   * @return a new instance of MatrixSetDiag
   */
  public <T extends TType> MatrixSetDiag<T> matrixSetDiag(Operand<T> input, Operand<T> diagonal,
      Operand<TInt32> k, MatrixSetDiag.Options... options) {
    return MatrixSetDiag.create(scope, input, diagonal, k, options);
  }

  /**
   * Solves one or more linear least-squares problems.
   *  {@code matrix} is a tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
   *  form real or complex matrices of size {@code [M, N]}. {@code Rhs} is a tensor of the same
   *  type as {@code matrix} and shape {@code [..., M, K]}.
   *  The output is a tensor shape {@code [..., N, K]} where each output matrix solves
   *  each of the equations
   *  {@code matrix[..., :, :]} * {@code output[..., :, :]} = {@code rhs[..., :, :]}
   *  in the least squares sense.
   *  <p>We use the following notation for (complex) matrix and right-hand sides
   *  in the batch:
   *  <p>{@code matrix}=\(A \in \mathbb{C}^{m \times n}\),
   *  {@code rhs}=\(B  \in \mathbb{C}^{m \times k}\),
   *  {@code output}=\(X  \in \mathbb{C}^{n \times k}\),
   *  {@code l2_regularizer}=\(\lambda \in \mathbb{R}\).
   *  <p>If {@code fast} is {@code True}, then the solution is computed by solving the normal
   *  equations using Cholesky decomposition. Specifically, if \(m \ge n\) then
   *  \(X = (A^H A + \lambda I)^{-1} A^H B\), which solves the least-squares
   *  problem \(X = \mathrm{argmin}_{Z \in \Re^{n \times k} } ||A Z - B||_F^2 + \lambda ||Z||<em>F^2\).
   *  If \(m \lt n\) then {@code output} is computed as
   *  \(X = A^H (A A^H + \lambda I)^{-1} B\), which (for \(\lambda = 0\)) is the
   *  minimum-norm solution to the under-determined linear system, i.e.
   *  \(X = \mathrm{argmin}</em>{Z \in \mathbb{C}^{n \times k} } ||Z||<em>F^2 \),
   *  subject to \(A Z = B\). Notice that the fast path is only numerically stable
   *  when \(A\) is numerically full rank and has a condition number
   *  \(\mathrm{cond}(A) \lt \frac{1}{\sqrt{\epsilon</em>{mach} } }\) or \(\lambda\) is
   *  sufficiently large.
   *  <p>If {@code fast} is {@code False} an algorithm based on the numerically robust complete
   *  orthogonal decomposition is used. This computes the minimum-norm
   *  least-squares solution, even when \(A\) is rank deficient. This path is
   *  typically 6-7 times slower than the fast path. If {@code fast} is {@code False} then
   *  {@code l2_regularizer} is ignored.
   *
   * @param <T> data type for {@code output} output
   * @param matrix Shape is {@code [..., M, N]}.
   * @param rhs Shape is {@code [..., M, K]}.
   * @param l2Regularizer Scalar tensor.
   *  <p>{@literal @}compatibility(numpy)<br>
   *  Equivalent to np.linalg.lstsq
   *  <br>{@literal @}end_compatibility
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixSolveLs} output and operands
   * @return a new instance of MatrixSolveLs
   */
  public <T extends TType> MatrixSolveLs<T> matrixSolveLs(Operand<T> matrix, Operand<T> rhs,
      Operand<TFloat64> l2Regularizer, MatrixSolveLs.Options... options) {
    return MatrixSolveLs.create(scope, matrix, rhs, l2Regularizer, options);
  }

  /**
   * Computes the QR decompositions of one or more matrices.
   *  Computes the QR decomposition of each inner matrix in {@code tensor} such that
   *  {@code tensor[..., :, :] = q[..., :, :] * r[..., :,:])}
   *  <p>Currently, the gradient for the QR decomposition is well-defined only when
   *  the first {@code P} columns of the inner matrix are linearly independent, where
   *  {@code P} is the minimum of {@code M} and {@code N}, the 2 inner-most dimmensions of {@code tensor}.
   *  <pre>
   *  # a is a tensor.
   *  # q is a tensor of orthonormal matrices.
   *  # r is a tensor of upper triangular matrices.
   *  q, r = qr(a)
   *  q_full, r_full = qr(a, full_matrices=True)
   *  </pre>
   *
   * @param <T> data type for {@code q} output
   * @param input A tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
   *  form matrices of size {@code [M, N]}. Let {@code P} be the minimum of {@code M} and {@code N}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Qr} output and operands
   * @return a new instance of Qr
   */
  public <T extends TType> Qr<T> qr(Operand<T> input, Qr.Options... options) {
    return Qr.create(scope, input, options);
  }

  /**
   * Perform a quantized matrix multiplication of  {@code a} by the matrix {@code b}.
   *  The inputs must be two-dimensional matrices and the inner dimension of
   *  {@code a} (after being transposed if {@code transpose_a} is non-zero) must match the
   *  outer dimension of {@code b} (after being transposed if {@code transposed_b} is
   *  non-zero).
   *
   * @param <V> data type for {@code out} output
   * @param a Must be a two-dimensional tensor.
   * @param b Must be a two-dimensional tensor.
   * @param minA The float value that the lowest quantized {@code a} value represents.
   * @param maxA The float value that the highest quantized {@code a} value represents.
   * @param minB The float value that the lowest quantized {@code b} value represents.
   * @param maxB The float value that the highest quantized {@code b} value represents.
   * @param Toutput The value of the Toutput attribute
   * @param Tactivation The type of output produced by activation function
   *  following this operation.
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedMatMul} output and operands
   * @param <W> data type for {@code QuantizedMatMul} output and operands
   * @return a new instance of QuantizedMatMul
   */
  public <V extends TNumber, W extends TNumber> QuantizedMatMul<V> quantizedMatMul(
      Operand<? extends TNumber> a, Operand<? extends TNumber> b, Operand<TFloat32> minA,
      Operand<TFloat32> maxA, Operand<TFloat32> minB, Operand<TFloat32> maxB, Class<V> Toutput,
      Class<W> Tactivation, QuantizedMatMul.Options... options) {
    return QuantizedMatMul.create(scope, a, b, minA, maxA, minB, maxB, Toutput, Tactivation, options);
  }

  /**
   * Computes the eigen decomposition of one or more square self-adjoint matrices.
   *  Computes the eigenvalues and (optionally) eigenvectors of each inner matrix in
   *  {@code input} such that {@code input[..., :, :] = v[..., :, :] * diag(e[..., :])}. The eigenvalues
   *  are sorted in non-decreasing order.
   *  <pre>
   *  # a is a tensor.
   *  # e is a tensor of eigenvalues.
   *  # v is a tensor of eigenvectors.
   *  e, v = self_adjoint_eig(a)
   *  e = self_adjoint_eig(a, compute_v=False)
   *  </pre>
   *
   * @param <T> data type for {@code e} output
   * @param input {@code Tensor} input of shape {@code [N, N]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SelfAdjointEigV2} output and operands
   * @return a new instance of SelfAdjointEig
   */
  public <T extends TType> SelfAdjointEig<T> selfAdjointEig(Operand<T> input,
      SelfAdjointEig.Options... options) {
    return SelfAdjointEig.create(scope, input, options);
  }

  /**
   * Solves systems of linear equations.
   *  {@code Matrix} is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
   *  form square matrices. {@code Rhs} is a tensor of shape {@code [..., M, K]}. The {@code output} is
   *  a tensor shape {@code [..., M, K]}.  If {@code adjoint} is {@code False} then each output matrix
   *  satisfies {@code matrix[..., :, :] * output[..., :, :] = rhs[..., :, :]}.
   *  If {@code adjoint} is {@code True} then each output matrix satisfies
   *  {@code adjoint(matrix[..., :, :]) * output[..., :, :] = rhs[..., :, :]}.
   *
   * @param <T> data type for {@code output} output
   * @param matrix Shape is {@code [..., M, M]}.
   * @param rhs Shape is {@code [..., M, K]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixSolve} output and operands
   * @return a new instance of Solve
   */
  public <T extends TType> Solve<T> solve(Operand<T> matrix, Operand<T> rhs,
      Solve.Options... options) {
    return Solve.create(scope, matrix, rhs, options);
  }

  /**
   * Computes the matrix square root of one or more square matrices:
   *  matmul(sqrtm(A), sqrtm(A)) = A
   *  <p>The input matrix should be invertible. If the input matrix is real, it should
   *  have no eigenvalues which are real and negative (pairs of complex conjugate
   *  eigenvalues are allowed).
   *  <p>The matrix square root is computed by first reducing the matrix to
   *  quasi-triangular form with the real Schur decomposition. The square root
   *  of the quasi-triangular matrix is then computed directly. Details of
   *  the algorithm can be found in: Nicholas J. Higham, &quot;Computing real
   *  square roots of a real matrix&quot;, Linear Algebra Appl., 1987.
   *  <p>The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
   *  form square matrices. The output is a tensor of the same shape as the input
   *  containing the matrix square root for all input submatrices {@code [..., :, :]}.
   *
   * @param <T> data type for {@code output} output
   * @param input Shape is {@code [..., M, M]}.
   * @param <T> data type for {@code MatrixSquareRoot} output and operands
   * @return a new instance of Sqrtm
   */
  public <T extends TType> Sqrtm<T> sqrtm(Operand<T> input) {
    return Sqrtm.create(scope, input);
  }

  /**
   * Computes the singular value decompositions of one or more matrices.
   *  Computes the SVD of each inner matrix in {@code input} such that
   *  {@code input[..., :, :] = u[..., :, :] * diag(s[..., :, :]) * transpose(v[..., :, :])}
   *  <pre>
   *  # a is a tensor containing a batch of matrices.
   *  # s is a tensor of singular values for each matrix.
   *  # u is the tensor containing the left singular vectors for each matrix.
   *  # v is the tensor containing the right singular vectors for each matrix.
   *  s, u, v = svd(a)
   *  s, _, _ = svd(a, compute_uv=False)
   *  </pre>
   *
   * @param <T> data type for {@code s} output
   * @param input A tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
   *  form matrices of size {@code [M, N]}. Let {@code P} be the minimum of {@code M} and {@code N}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Svd} output and operands
   * @return a new instance of Svd
   */
  public <T extends TType> Svd<T> svd(Operand<T> input, Svd.Options... options) {
    return Svd.create(scope, input, options);
  }

  /**
   * Returns a diagonal tensor with a given diagonal values.
   *  Given a {@code diagonal}, this operation returns a tensor with the {@code diagonal} and
   *  everything else padded with zeros. The diagonal is computed as follows:
   *  <p>Assume {@code diagonal} has dimensions [D1,..., Dk], then the output is a tensor of
   *  rank 2k with dimensions [D1,..., Dk, D1,..., Dk] where:
   *  <p>{@code output[i1,..., ik, i1,..., ik] = diagonal[i1, ..., ik]} and 0 everywhere else.
   *  <p>For example:
   *  <pre>
   *  # 'diagonal' is [1, 2, 3, 4]
   *  tf.diag(diagonal) ==&gt; [[1, 0, 0, 0]
   *                         [0, 2, 0, 0]
   *                         [0, 0, 3, 0]
   *                         [0, 0, 0, 4]]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param diagonal Rank k tensor where k is at most 1.
   * @param <T> data type for {@code Diag} output and operands
   * @return a new instance of TensorDiag
   */
  public <T extends TType> TensorDiag<T> tensorDiag(Operand<T> diagonal) {
    return TensorDiag.create(scope, diagonal);
  }

  /**
   * Returns the diagonal part of the tensor.
   *  This operation returns a tensor with the {@code diagonal} part
   *  of the {@code input}. The {@code diagonal} part is computed as follows:
   *  <p>Assume {@code input} has dimensions {@code [D1,..., Dk, D1,..., Dk]}, then the output is a
   *  tensor of rank {@code k} with dimensions {@code [D1,..., Dk]} where:
   *  <p>{@code diagonal[i1,..., ik] = input[i1, ..., ik, i1,..., ik]}.
   *  <p>For example:
   *  <pre>
   *  # 'input' is [[1, 0, 0, 0]
   *                [0, 2, 0, 0]
   *                [0, 0, 3, 0]
   *                [0, 0, 0, 4]]
   *
   *  tf.diag_part(input) ==&gt; [1, 2, 3, 4]
   *  </pre>
   *
   * @param <T> data type for {@code diagonal} output
   * @param input Rank k tensor where k is even and not zero.
   * @param <T> data type for {@code DiagPart} output and operands
   * @return a new instance of TensorDiagPart
   */
  public <T extends TType> TensorDiagPart<T> tensorDiagPart(Operand<T> input) {
    return TensorDiagPart.create(scope, input);
  }

  /**
   * Shuffle dimensions of x according to a permutation.
   *  The output {@code y} has the same rank as {@code x}. The shapes of {@code x} and {@code y} satisfy:
   *  {@code y.shape[i] == x.shape[perm[i]] for i in [0, 1, ..., rank(x) - 1]}
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param perm The perm value
   * @param <T> data type for {@code Transpose} output and operands
   * @return a new instance of Transpose
   */
  public <T extends TType> Transpose<T> transpose(Operand<T> x, Operand<? extends TNumber> perm) {
    return Transpose.create(scope, x, perm);
  }

  /**
   * Solves systems of linear equations with upper or lower triangular matrices by backsubstitution.
   *  {@code matrix} is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions form
   *  square matrices. If {@code lower} is {@code True} then the strictly upper triangular part
   *  of each inner-most matrix is assumed to be zero and not accessed.
   *  If {@code lower} is False then the strictly lower triangular part of each inner-most
   *  matrix is assumed to be zero and not accessed.
   *  {@code rhs} is a tensor of shape {@code [..., M, N]}.
   *  <p>The output is a tensor of shape {@code [..., M, N]}. If {@code adjoint} is
   *  {@code True} then the innermost matrices in {@code output} satisfy matrix equations
   *  {@code matrix[..., :, :] * output[..., :, :] = rhs[..., :, :]}.
   *  If {@code adjoint} is {@code False} then the strictly then the  innermost matrices in
   *  {@code output} satisfy matrix equations
   *  {@code adjoint(matrix[..., i, k]) * output[..., k, j] = rhs[..., i, j]}.
   *  <p>Note, the batch shapes for the inputs only need to broadcast.
   *  <p>Example:
   *  <pre>
   *
   *  a = tf.constant([[3,  0,  0,  0],
   *                   [2,  1,  0,  0],
   *                   [1,  0,  1,  0],
   *                   [1,  1,  1,  1]], dtype=tf.float32)
   *
   *  b = tf.constant([[4],
   *                   [2],
   *                   [4],
   *                   [2]], dtype=tf.float32)
   *
   *  x = tf.linalg.triangular_solve(a, b, lower=True)
   *  x
   *  # &lt;tf.Tensor: shape=(4, 1), dtype=float32, numpy=
   *  # array([[ 1.3333334 ],
   *  #        [-0.66666675],
   *  #        [ 2.6666665 ],
   *  #        [-1.3333331 ]], dtype=float32)&gt;
   *
   *  # in python3 one can use `a@x`
   *  tf.matmul(a, x)
   *  # &lt;tf.Tensor: shape=(4, 1), dtype=float32, numpy=
   *  # array([[4.       ],
   *  #        [2.       ],
   *  #        [4.       ],
   *  #        [1.9999999]], dtype=float32)&gt;
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param matrix Shape is {@code [..., M, M]}.
   * @param rhs Shape is {@code [..., M, K]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixTriangularSolve} output and operands
   * @return a new instance of TriangularSolve
   */
  public <T extends TType> TriangularSolve<T> triangularSolve(Operand<T> matrix, Operand<T> rhs,
      TriangularSolve.Options... options) {
    return TriangularSolve.create(scope, matrix, rhs, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
