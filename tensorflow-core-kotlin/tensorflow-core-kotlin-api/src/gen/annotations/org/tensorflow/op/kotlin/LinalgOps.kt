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
package org.tensorflow.op.kotlin

import kotlin.jvm.JvmName
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.linalg.BandPart
import org.tensorflow.op.linalg.BatchCholesky
import org.tensorflow.op.linalg.BatchCholeskyGrad
import org.tensorflow.op.linalg.BatchMatrixBandPart
import org.tensorflow.op.linalg.BatchMatrixDeterminant
import org.tensorflow.op.linalg.BatchMatrixDiag
import org.tensorflow.op.linalg.BatchMatrixDiagPart
import org.tensorflow.op.linalg.BatchMatrixInverse
import org.tensorflow.op.linalg.BatchMatrixSetDiag
import org.tensorflow.op.linalg.BatchMatrixSolve
import org.tensorflow.op.linalg.BatchMatrixSolveLs
import org.tensorflow.op.linalg.BatchMatrixTriangularSolve
import org.tensorflow.op.linalg.BatchSelfAdjointEig
import org.tensorflow.op.linalg.BatchSvd
import org.tensorflow.op.linalg.Cholesky
import org.tensorflow.op.linalg.CholeskyGrad
import org.tensorflow.op.linalg.ConjugateTranspose
import org.tensorflow.op.linalg.Cross
import org.tensorflow.op.linalg.Det
import org.tensorflow.op.linalg.Eig
import org.tensorflow.op.linalg.Einsum
import org.tensorflow.op.linalg.EuclideanNorm
import org.tensorflow.op.linalg.Inv
import org.tensorflow.op.linalg.LoadAndRemapMatrix
import org.tensorflow.op.linalg.LogMatrixDeterminant
import org.tensorflow.op.linalg.Lu
import org.tensorflow.op.linalg.MatMul
import org.tensorflow.op.linalg.MatrixDiag
import org.tensorflow.op.linalg.MatrixDiagPart
import org.tensorflow.op.linalg.MatrixDiagPartV3
import org.tensorflow.op.linalg.MatrixDiagV3
import org.tensorflow.op.linalg.MatrixSetDiag
import org.tensorflow.op.linalg.MatrixSolveLs
import org.tensorflow.op.linalg.Qr
import org.tensorflow.op.linalg.QuantizedMatMul
import org.tensorflow.op.linalg.SelfAdjointEig
import org.tensorflow.op.linalg.Solve
import org.tensorflow.op.linalg.Sqrtm
import org.tensorflow.op.linalg.Svd
import org.tensorflow.op.linalg.TensorDiag
import org.tensorflow.op.linalg.TensorDiagPart
import org.tensorflow.op.linalg.Transpose
import org.tensorflow.op.linalg.TriangularSolve
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TFloat64
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `linalg` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class LinalgOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.LinalgOps = ops.java.linalg

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Copy a tensor setting everything outside a central band in each innermost matrix to zero.
     *  
     *  The `band` part is computed as follows:
     *  Assume `input` has `k` dimensions `&#91;I, J, K, ..., M, N]`, then the output is a
     *  tensor with the same shape where
     *  
     *  `band&#91;i, j, k, ..., m, n] = in_band(m, n) * input&#91;i, j, k, ..., m, n]`.
     *  
     *  The indicator function
     *  
     *  `in_band(m, n) = (num_lower < 0 || (m-n) <= num_lower)) &&
     *                   (num_upper < 0 || (n-m) <= num_upper)`.
     *  
     *  For example:
     *  ```
     *  # if 'input' is [[ 0,  1,  2, 3]
     *                   [-1,  0,  1, 2]
     *                   [-2, -1,  0, 1]
     *                   [-3, -2, -1, 0]],
     * 
     *  tf.matrix_band_part(input, 1, -1) ==> [[ 0,  1,  2, 3]
     *                                         [-1,  0,  1, 2]
     *                                         [ 0, -1,  0, 1]
     *                                         [ 0,  0, -1, 0]],
     * 
     *  tf.matrix_band_part(input, 2, 1) ==> [[ 0,  1,  0, 0]
     *                                        [-1,  0,  1, 0]
     *                                        [-2, -1,  0, 1]
     *                                        [ 0, -2, -1, 0]]
     *  ```
     * 
     *  Useful special cases:
     *  ```
     *   tf.matrix_band_part(input, 0, -1) ==> Upper triangular part.
     *   tf.matrix_band_part(input, -1, 0) ==> Lower triangular part.
     *   tf.matrix_band_part(input, 0, 0) ==> Diagonal.
     *  ```
     * 
     * 
     * @param T data type for ` band()` output
     * @param input Rank `k` tensor.
     * @param numLower 0-D tensor. Number of subdiagonals to keep. If negative, keep entire
     *  lower triangle.
     * @param numUpper 0-D tensor. Number of superdiagonals to keep. If negative, keep
     *  entire upper triangle.
     * @return a new instance of BandPart
     * @see org.tensorflow.op.LinalgOps.bandPart
     */
    public fun <T : TType, U : TNumber> bandPart(
        input: Operand<T>,
        numLower: Operand<U>,
        numUpper: Operand<U>
    ): BandPart<T> = java.bandPart<T, U>(    
        input,
        numLower,
        numUpper
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param input
     * @return a new instance of BatchCholesky
     * @see org.tensorflow.op.LinalgOps.batchCholesky
     */
    public fun <T : TNumber> batchCholesky(input: Operand<T>): BatchCholesky<T> =
            java.batchCholesky<T>(    
        input
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param l
     * @param grad
     * @return a new instance of BatchCholeskyGrad
     * @see org.tensorflow.op.LinalgOps.batchCholeskyGrad
     */
    public fun <T : TNumber> batchCholeskyGrad(l: Operand<T>, grad: Operand<T>):
            BatchCholeskyGrad<T> = java.batchCholeskyGrad<T>(    
        l,
        grad
        )

    /**
     * 
     * @param T data type for ` band()` output
     * @param input
     * @param numLower
     * @param numUpper
     * @return a new instance of BatchMatrixBandPart
     * @see org.tensorflow.op.LinalgOps.batchMatrixBandPart
     */
    public fun <T : TType> batchMatrixBandPart(
        input: Operand<T>,
        numLower: Operand<TInt64>,
        numUpper: Operand<TInt64>
    ): BatchMatrixBandPart<T> = java.batchMatrixBandPart<T>(    
        input,
        numLower,
        numUpper
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param input
     * @return a new instance of BatchMatrixDeterminant
     * @see org.tensorflow.op.LinalgOps.batchMatrixDeterminant
     */
    public fun <T : TType> batchMatrixDeterminant(input: Operand<T>): BatchMatrixDeterminant<T> =
            java.batchMatrixDeterminant<T>(    
        input
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param diagonal
     * @return a new instance of BatchMatrixDiag
     * @see org.tensorflow.op.LinalgOps.batchMatrixDiag
     */
    public fun <T : TType> batchMatrixDiag(diagonal: Operand<T>): BatchMatrixDiag<T> =
            java.batchMatrixDiag<T>(    
        diagonal
        )

    /**
     * 
     * @param T data type for ` diagonal()` output
     * @param input
     * @return a new instance of BatchMatrixDiagPart
     * @see org.tensorflow.op.LinalgOps.batchMatrixDiagPart
     */
    public fun <T : TType> batchMatrixDiagPart(input: Operand<T>): BatchMatrixDiagPart<T> =
            java.batchMatrixDiagPart<T>(    
        input
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param input
     * @param options carries optional attributes values
     * @return a new instance of BatchMatrixInverse
     * @see org.tensorflow.op.LinalgOps.batchMatrixInverse
     * @param adjoint @param adjoint
     */
    public fun <T : TNumber> batchMatrixInverse(input: Operand<T>, adjoint: Boolean? = null):
            BatchMatrixInverse<T> = java.batchMatrixInverse<T>(    
        input,
        *listOfNotNull(
            adjoint?.let{ org.tensorflow.op.linalg.BatchMatrixInverse.adjoint(it) }
        ).toTypedArray()
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param input
     * @param diagonal
     * @return a new instance of BatchMatrixSetDiag
     * @see org.tensorflow.op.LinalgOps.batchMatrixSetDiag
     */
    public fun <T : TType> batchMatrixSetDiag(input: Operand<T>, diagonal: Operand<T>):
            BatchMatrixSetDiag<T> = java.batchMatrixSetDiag<T>(    
        input,
        diagonal
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param matrix
     * @param rhs
     * @param options carries optional attributes values
     * @return a new instance of BatchMatrixSolve
     * @see org.tensorflow.op.LinalgOps.batchMatrixSolve
     * @param adjoint @param adjoint
     */
    public fun <T : TNumber> batchMatrixSolve(
        matrix: Operand<T>,
        rhs: Operand<T>,
        adjoint: Boolean? = null
    ): BatchMatrixSolve<T> = java.batchMatrixSolve<T>(    
        matrix,
        rhs,
        *listOfNotNull(
            adjoint?.let{ org.tensorflow.op.linalg.BatchMatrixSolve.adjoint(it) }
        ).toTypedArray()
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param matrix
     * @param rhs
     * @param l2Regularizer
     * @param options carries optional attributes values
     * @return a new instance of BatchMatrixSolveLs
     * @see org.tensorflow.op.LinalgOps.batchMatrixSolveLs
     * @param fast @param fast
     */
    public fun <T : TNumber> batchMatrixSolveLs(
        matrix: Operand<T>,
        rhs: Operand<T>,
        l2Regularizer: Operand<TFloat64>,
        fast: Boolean? = null
    ): BatchMatrixSolveLs<T> = java.batchMatrixSolveLs<T>(    
        matrix,
        rhs,
        l2Regularizer,
        *listOfNotNull(
            fast?.let{ org.tensorflow.op.linalg.BatchMatrixSolveLs.fast(it) }
        ).toTypedArray()
        )

    /**
     * 
     * @param T data type for ` output()` output
     * @param matrix
     * @param rhs
     * @param options carries optional attributes values
     * @return a new instance of BatchMatrixTriangularSolve
     * @see org.tensorflow.op.LinalgOps.batchMatrixTriangularSolve
     * @param lower @param lower
     * @param adjoint @param adjoint
     */
    public fun <T : TNumber> batchMatrixTriangularSolve(
        matrix: Operand<T>,
        rhs: Operand<T>,
        lower: Boolean? = null,
        adjoint: Boolean? = null
    ): BatchMatrixTriangularSolve<T> = java.batchMatrixTriangularSolve<T>(    
        matrix,
        rhs,
        *listOfNotNull(
            lower?.let{ org.tensorflow.op.linalg.BatchMatrixTriangularSolve.lower(it) },
            adjoint?.let{ org.tensorflow.op.linalg.BatchMatrixTriangularSolve.adjoint(it) }
        ).toTypedArray()
        )

    /**
     * 
     * @param T data type for ` e()` output
     * @param input
     * @param options carries optional attributes values
     * @return a new instance of BatchSelfAdjointEig
     * @see org.tensorflow.op.LinalgOps.batchSelfAdjointEig
     * @param computeV @param computeV
     */
    public fun <T : TNumber> batchSelfAdjointEig(input: Operand<T>, computeV: Boolean? = null):
            BatchSelfAdjointEig<T> = java.batchSelfAdjointEig<T>(    
        input,
        *listOfNotNull(
            computeV?.let{ org.tensorflow.op.linalg.BatchSelfAdjointEig.computeV(it) }
        ).toTypedArray()
        )

    /**
     * 
     * @param T data type for ` s()` output
     * @param input
     * @param options carries optional attributes values
     * @return a new instance of BatchSvd
     * @see org.tensorflow.op.LinalgOps.batchSvd
     * @param computeUv @param computeUv
     * @param fullMatrices @param fullMatrices
     */
    public fun <T : TType> batchSvd(
        input: Operand<T>,
        computeUv: Boolean? = null,
        fullMatrices: Boolean? = null
    ): BatchSvd<T> = java.batchSvd<T>(    
        input,
        *listOfNotNull(
            computeUv?.let{ org.tensorflow.op.linalg.BatchSvd.computeUv(it) },
            fullMatrices?.let{ org.tensorflow.op.linalg.BatchSvd.fullMatrices(it) }
        ).toTypedArray()
        )

    /**
     * Computes the Cholesky decomposition of one or more square matrices.
     *  
     *  The input is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions
     *  form square matrices.
     *  
     *  The input has to be symmetric and positive definite. Only the lower-triangular
     *  part of the input will be used for this operation. The upper-triangular part
     *  will not be read.
     *  
     *  The output is a tensor of the same shape as the input
     *  containing the Cholesky decompositions for all input submatrices `&#91;..., :, :]`.
     *  
     *  <b>Note</b>: The gradient computation on GPU is faster for large matrices but
     *  not for large batch dimensions when the submatrices are small. In this
     *  case it might be faster to use the CPU.
     * 
     * @param T data type for ` output()` output
     * @param input Shape is `&#91;..., M, M]`.
     * @return a new instance of Cholesky
     * @see org.tensorflow.op.LinalgOps.cholesky
     */
    public fun <T : TType> cholesky(input: Operand<T>): Cholesky<T> = java.cholesky<T>(    
        input
        )

    /**
     * Computes the reverse mode backpropagated gradient of the Cholesky algorithm.
     *  
     *  For an explanation see "Differentiation of the Cholesky algorithm" by
     *  Iain Murray http://arxiv.org/abs/1602.07527.
     * 
     * @param T data type for ` output()` output
     * @param l Output of batch Cholesky algorithm l = cholesky(A). Shape is `&#91;..., M, M]`.
     *  Algorithm depends only on lower triangular part of the innermost matrices of
     *  this tensor.
     * @param grad df/dl where f is some scalar function. Shape is `&#91;..., M, M]`.
     *  Algorithm depends only on lower triangular part of the innermost matrices of
     *  this tensor.
     * @return a new instance of CholeskyGrad
     * @see org.tensorflow.op.LinalgOps.choleskyGrad
     */
    public fun <T : TNumber> choleskyGrad(l: Operand<T>, grad: Operand<T>): CholeskyGrad<T> =
            java.choleskyGrad<T>(    
        l,
        grad
        )

    /**
     * Shuffle dimensions of x according to a permutation and conjugate the result.
     *  
     *  The output `y` has the same rank as `x`. The shapes of `x` and `y` satisfy:
     *    `y.shape&#91;i] == x.shape&#91;perm&#91;i]] for i in &#91;0, 1, ..., rank(x) - 1]`
     *    `y&#91;i,j,k,...,s,t,u] == conj(x&#91;perm&#91;i], perm&#91;j],
     * perm&#91;k],...,perm&#91;s], perm&#91;t], perm&#91;u]])`
     * 
     * @param T data type for ` y()` output
     * @param x
     * @param perm
     * @return a new instance of ConjugateTranspose
     * @see org.tensorflow.op.LinalgOps.conjugateTranspose
     */
    public fun <T : TType> conjugateTranspose(x: Operand<T>, perm: Operand<out TNumber>):
            ConjugateTranspose<T> = java.conjugateTranspose<T>(    
        x,
        perm
        )

    /**
     * Compute the pairwise cross product.
     *  
     *  `a` and `b` must be the same shape; they can either be simple 3-element vectors,
     *  or any shape where the innermost dimension is 3. In the latter case, each pair
     *  of corresponding 3-element vectors is cross-multiplied independently.
     * 
     * @param T data type for ` product()` output
     * @param a A tensor containing 3-element vectors.
     * @param b Another tensor, of same type and shape as `a`.
     * @return a new instance of Cross
     * @see org.tensorflow.op.LinalgOps.cross
     */
    public fun <T : TNumber> cross(a: Operand<T>, b: Operand<T>): Cross<T> = java.cross<T>(    
        a,
        b
        )

    /**
     * Computes the determinant of one or more square matrices.
     *  
     *  The input is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions
     *  form square matrices. The output is a tensor containing the determinants
     *  for all input submatrices `&#91;..., :, :]`.
     * 
     * @param T data type for ` output()` output
     * @param input Shape is `&#91;..., M, M]`.
     * @return a new instance of Det
     * @see org.tensorflow.op.LinalgOps.det
     */
    public fun <T : TType> det(input: Operand<T>): Det<T> = java.det<T>(    
        input
        )

    /**
     * Computes the eigen decomposition of one or more square matrices.
     *  
     *  Computes the eigenvalues and (optionally) right eigenvectors of each inner matrix in
     *  `input` such that `input&#91;..., :, :] = v&#91;..., :, :] * diag(e&#91;..., :])`. The
     * eigenvalues
     *  are sorted in non-decreasing order.
     *  ```
     *  # a is a tensor.
     *  # e is a tensor of eigenvalues.
     *  # v is a tensor of eigenvectors.
     *  e, v = eig(a)
     *  e = eig(a, compute_v=False)
     *  ```
     * 
     * 
     * @param U data type for ` e()` output
     * @param input `Tensor` input of shape `&#91;N, N]`.
     * @param Tout
     * @param options carries optional attributes values
     * @return a new instance of Eig
     * @see org.tensorflow.op.LinalgOps.eig
     * @param computeV If `True` then eigenvectors will be computed and returned in `v`.
     *  Otherwise, only the eigenvalues will be computed.
     */
    public fun <U : TType> eig(
        input: Operand<out TType>,
        Tout: Class<U>,
        computeV: Boolean? = null
    ): Eig<U> = java.eig<U>(    
        input,
        Tout,
        *listOfNotNull(
            computeV?.let{ org.tensorflow.op.linalg.Eig.computeV(it) }
        ).toTypedArray()
        )

    /**
     * Tensor contraction according to Einstein summation convention.
     *  
     *  Implements generalized Tensor contraction and reduction. Each input Tensor must
     *  have a corresponding input subscript appearing in the comma-separated left-hand
     *  side of the equation. The right-hand side of the equation consists of the
     *  output subscript. The input subscripts and the output subscript should consist
     *  of zero or more named axis labels and at most one ellipsis (`...`).
     *  
     *  The named axis labels may be any single character other than those having
     *  special meaning, namely `,.->`. The behavior of this Op is undefined if it
     *  receives an ill-formatted equation; since the validation is done at
     *  graph-building time, we omit format validation checks at runtime.
     *  
     *  Note: This Op is <i>not</i> intended to be called by the user; instead users should
     *  call `tf.einsum` directly. It is a hidden Op used by `tf.einsum`.
     *  
     *  Operations are applied to the input(s) according to the following rules:
     *  
     *   (a) Generalized Diagonals: For input dimensions corresponding to axis labels
     *       appearing more than once in the same input subscript, we take the
     *       generalized (`k`-dimensional) diagonal.
     *       For example, in the equation `iii->i` with input shape `&#91;3, 3, 3]`, the
     *       generalized diagonal would consist of `3` elements at indices `(0, 0, 0)`,
     *       `(1, 1, 1)` and `(2, 2, 2)` to create a Tensor of shape `&#91;3]`.
     *  
     *   (b) Reduction: Axes corresponding to labels appearing only in one input
     *       subscript but not in the output subscript are summed over prior to Tensor
     *       contraction.
     *       For example, in the equation `ab,bc->b`, the axis labels `a` and `c` are
     *       the reduction axis labels.
     *  
     *   (c) Batch Dimensions: Axes corresponding to labels appearing in each of the
     *       input subscripts and also in the output subscript make up the batch
     *       dimensions in Tensor contraction. Unnamed axis labels corresponding to
     *       ellipsis (`...`) also correspond to batch dimensions.
     *       For example, for the equation denoting batch matrix multiplication,
     *       `bij,bjk->bik`, the axis label `b` corresponds to a batch dimension.
     *  
     *   (d) Contraction: In case of binary einsum, axes corresponding to labels
     *       appearing in two different inputs (and not in the output) are contracted
     *       against each other.
     *       Considering the batch matrix multiplication equation again
     *       (`bij,bjk->bik`), the contracted axis label is `j`.
     *  
     *   (e) Expand Diagonal: If the output subscripts contain repeated (explicit) axis
     *       labels, the opposite operation of (a) is applied. For example, in the
     *       equation `i->iii`, and input shape `&#91;3]`, the output of shape `&#91;3, 3, 3]`
     *       are all zeros, except for the (generalized) diagonal which is populated
     *       with values from the input.
     *       Note: This operation is not supported by `np.einsum` or `tf.einsum`; it is
     *       provided to enable computing the symbolic gradient of `tf.einsum`.
     *  
     *  The output subscripts must contain only labels appearing in at least one of the
     *  input subscripts. Furthermore, all dimensions mapping to the same axis label
     *  must be equal.
     *  
     *  Any of the input and output subscripts may contain at most a single ellipsis
     *  (`...`). These ellipsis are mapped against dimensions not corresponding to any
     *  named axis label. If two inputs contain ellipsis, then they are broadcasted
     *  according to standard NumPy broadcasting
     *  &#91;rules](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html).
     *  
     *  The broadcasted dimensions are placed in the corresponding location of the
     *  ellipsis in the output subscript. If the broadcasted dimensions are non-empty
     *  and the output subscripts do not contain ellipsis, then an InvalidArgument error
     *  is raised.
     *  
     * 
     * @compatibility(numpy) Similar to
     * &#91;`numpy.einsum`](https://docs.scipy.org/doc/numpy/reference/generated/numpy.einsum.html).
     *  
     *  Comparison with `numpy.einsum`:
     *  
     * This Op only supports unary and binary forms of `numpy.einsum`.
     * This Op does not support implicit form. (i.e. equations without `->`).
     * This Op also supports repeated indices in the output subscript, which is not
     *     supported by `numpy.einsum`.
     * @end_compatibility
     * @param T data type for ` output()` output
     * @param inputs List of 1 or 2 Tensors.
     * @param equation String describing the Einstein Summation operation; in the format of
     * np.einsum.
     * @return a new instance of Einsum
     * @see org.tensorflow.op.LinalgOps.einsum
     */
    public fun <T : TType> einsum(inputs: Iterable<Operand<T>>, equation: String): Einsum<T> =
            java.einsum<T>(    
        inputs,
        equation
        )

    /**
     * Computes the euclidean norm of elements across dimensions of a tensor.
     *  
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     * 
     * @param T data type for ` output()` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `&#91;-rank(input), rank(input))`.
     * @param options carries optional attributes values
     * @return a new instance of EuclideanNorm
     * @see org.tensorflow.op.LinalgOps.euclideanNorm
     * @param keepDims If true, retain reduced dimensions with length 1.
     */
    public fun <T : TType> euclideanNorm(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): EuclideanNorm<T> = java.euclideanNorm<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.linalg.EuclideanNorm.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the inverse of one or more square invertible matrices or their
     *  
     *  adjoints (conjugate transposes).
     *  
     *  The input is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions
     *  form square matrices. The output is a tensor of the same shape as the input
     *  containing the inverse for all input submatrices `&#91;..., :, :]`.
     *  
     *  The op uses LU decomposition with partial pivoting to compute the inverses.
     *  
     *  If a matrix is not invertible there is no guarantee what the op does. It
     *  may detect the condition and raise an exception or it may simply return a
     *  garbage result.
     * 
     * @param T data type for ` output()` output
     * @param input Shape is `&#91;..., M, M]`.
     * @param options carries optional attributes values
     * @return a new instance of Inv
     * @see org.tensorflow.op.LinalgOps.inv
     * @param adjoint @param adjoint
     */
    public fun <T : TType> inv(input: Operand<T>, adjoint: Boolean? = null): Inv<T> = java.inv<T>(    
        input,
        *listOfNotNull(
            adjoint?.let{ org.tensorflow.op.linalg.Inv.adjoint(it) }
        ).toTypedArray()
        )

    /**
     * Loads a 2-D (matrix) `Tensor` with name `old_tensor_name` from the checkpoint
     *  
     *  at `ckpt_path` and potentially reorders its rows and columns using the
     *  specified remappings.
     *  
     *  Most users should use one of the wrapper initializers (such as
     *  `tf.contrib.framework.load_and_remap_matrix_initializer`) instead of this
     *  function directly.
     *  
     *  The remappings are 1-D tensors with the following properties:
     *  <ul>
     *  <li>
     *  `row_remapping` must have exactly `num_rows` entries. Row `i` of the output
     *    matrix will be initialized from the row corresponding to index
     *    `row_remapping&#91;i]` in the old `Tensor` from the checkpoint.
     *  </li>
     *  <li>
     *  `col_remapping` must have either 0 entries (indicating that no column
     *    reordering is needed) or `num_cols` entries. If specified, column `j` of the
     *    output matrix will be initialized from the column corresponding to index
     *    `col_remapping&#91;j]` in the old `Tensor` from the checkpoint.
     *  </li>
     *  <li>
     *  A value of -1 in either of the remappings signifies a "missing" entry. In that
     *    case, values from the `initializing_values` tensor will be used to fill that
     *    missing row or column. If `row_remapping` has `r` missing entries and
     *    `col_remapping` has `c` missing entries, then the following condition must be
     *    true:
     *  </li>
     *  </ul>
     *  `(r * num_cols) + (c * num_rows) - (r * c) == len(initializing_values)`
     *  
     *  The remapping tensors can be generated using the GenerateVocabRemapping op.
     *  
     *  As an example, with row_remapping = &#91;1, 0, -1], col_remapping = &#91;0, 2, -1],
     *  initializing_values = &#91;0.5, -0.5, 0.25, -0.25, 42], and w(i, j) representing
     *  the value from row i, column j of the old tensor in the checkpoint, the output
     *  matrix will look like the following:
     *  
     *  &#91;&#91;w(1, 0),  w(1, 2),  0.5],
     *   &#91;w(0, 0),  w(0, 2), -0.5],
     *   &#91;0.25,    -0.25,      42]]
     * 
     * @param ckptPath Path to the TensorFlow checkpoint (version 2, `TensorBundle`) from
     *  which the old matrix `Tensor` will be loaded.
     * @param oldTensorName Name of the 2-D `Tensor` to load from checkpoint.
     * @param rowRemapping An int `Tensor` of row remappings (generally created by
     *  `generate_vocab_remapping`).  Even if no row remapping is needed, this must
     *  still be an index-valued Tensor (e.g. &#91;0, 1, 2, ...]), or a shifted
     *  index-valued `Tensor` (e.g. &#91;8, 9, 10, ...], for partitioned `Variables`).
     * @param colRemapping An int `Tensor` of column remappings (generally created by
     *  `generate_vocab_remapping`).  May be a size-0 `Tensor` if only row remapping
     *  is to be done (e.g. column ordering is the same).
     * @param initializingValues A float `Tensor` containing  values to fill in for cells
     *  in the output matrix that are not loaded from the checkpoint. Length must be
     *  exactly the same as the number of missing / new cells.
     * @param numRows Number of rows (length of the 1st dimension) in the output matrix.
     * @param numCols Number of columns (length of the 2nd dimension) in the output matrix.
     * @param options carries optional attributes values
     * @return a new instance of LoadAndRemapMatrix
     * @see org.tensorflow.op.LinalgOps.loadAndRemapMatrix
     * @param maxRowsInMemory The maximum number of rows to load from the checkpoint at
     *  once. If less than or equal to 0, the entire matrix will be loaded into
     *  memory. Setting this arg trades increased disk reads for lower memory usage.
     */
    public fun loadAndRemapMatrix(
        ckptPath: Operand<TString>,
        oldTensorName: Operand<TString>,
        rowRemapping: Operand<TInt64>,
        colRemapping: Operand<TInt64>,
        initializingValues: Operand<TFloat32>,
        numRows: Long,
        numCols: Long,
        maxRowsInMemory: Long? = null
    ): LoadAndRemapMatrix = java.loadAndRemapMatrix(    
        ckptPath,
        oldTensorName,
        rowRemapping,
        colRemapping,
        initializingValues,
        numRows,
        numCols,
        *listOfNotNull(
            maxRowsInMemory?.let{ org.tensorflow.op.linalg.LoadAndRemapMatrix.maxRowsInMemory(it) }
        ).toTypedArray()
        )

    /**
     * Computes the sign and the log of the absolute value of the determinant of
     *  
     *  one or more square matrices.
     *  
     *  The input is a tensor of shape `&#91;N, M, M]` whose inner-most 2 dimensions
     *  form square matrices. The outputs are two tensors containing the signs and
     *  absolute values of the log determinants for all N input submatrices
     *  `&#91;..., :, :]` such that the determinant = sign*exp(log_abs_determinant).
     *  The log_abs_determinant is computed as det(P)*sum(log(diag(LU))) where LU
     *  is the LU decomposition of the input and P is the corresponding
     *  permutation matrix.
     * 
     * @param T data type for ` sign()` output
     * @param input Shape is `&#91;N, M, M]`.
     * @return a new instance of LogMatrixDeterminant
     * @see org.tensorflow.op.LinalgOps.logMatrixDeterminant
     */
    public fun <T : TType> logMatrixDeterminant(input: Operand<T>): LogMatrixDeterminant<T> =
            java.logMatrixDeterminant<T>(    
        input
        )

    /**
     * Computes the LU decomposition of one or more square matrices.
     *  
     *  The input is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions
     *  form square matrices.
     *  
     *  The input has to be invertible.
     *  
     *  The output consists of two tensors LU and P containing the LU decomposition
     *  of all input submatrices `&#91;..., :, :]`. LU encodes the lower triangular and
     *  upper triangular factors.
     *  
     *  For each input submatrix of shape `&#91;M, M]`, L is a lower triangular matrix of
     *  shape `&#91;M, M]` with unit diagonal whose entries correspond to the strictly lower
     *  triangular part of LU. U is a upper triangular matrix of shape `&#91;M, M]` whose
     *  entries correspond to the upper triangular part, including the diagonal, of LU.
     *  
     *  P represents a permutation matrix encoded as a list of indices each between `0`
     *  and `M-1`, inclusive. If P_mat denotes the permutation matrix corresponding to
     *  P, then the L, U and P satisfies P_mat * input = L * U.
     * 
     * @param T data type for ` lu()` output
     * @param U data type for ` p()` output
     * @param input A tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions form matrices
     * of
     *  size `&#91;M, M]`.
     * @return a new instance of Lu
     * @see org.tensorflow.op.LinalgOps.lu
     */
    public fun <T : TType> lu(input: Operand<T>): Lu<T, TInt32> = java.lu<T>(    
        input
        )

    /**
     * Computes the LU decomposition of one or more square matrices.
     *  
     *  The input is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions
     *  form square matrices.
     *  
     *  The input has to be invertible.
     *  
     *  The output consists of two tensors LU and P containing the LU decomposition
     *  of all input submatrices `&#91;..., :, :]`. LU encodes the lower triangular and
     *  upper triangular factors.
     *  
     *  For each input submatrix of shape `&#91;M, M]`, L is a lower triangular matrix of
     *  shape `&#91;M, M]` with unit diagonal whose entries correspond to the strictly lower
     *  triangular part of LU. U is a upper triangular matrix of shape `&#91;M, M]` whose
     *  entries correspond to the upper triangular part, including the diagonal, of LU.
     *  
     *  P represents a permutation matrix encoded as a list of indices each between `0`
     *  and `M-1`, inclusive. If P_mat denotes the permutation matrix corresponding to
     *  P, then the L, U and P satisfies P_mat * input = L * U.
     * 
     * @param T data type for ` lu()` output
     * @param U data type for ` p()` output
     * @param input A tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions form matrices
     * of
     *  size `&#91;M, M]`.
     * @param outputIdxType
     * @return a new instance of Lu
     * @see org.tensorflow.op.LinalgOps.lu
     */
    public fun <T : TType, U : TNumber> lu(input: Operand<T>, outputIdxType: Class<U>): Lu<T, U> =
            java.lu<T, U>(    
        input,
        outputIdxType
        )

    /**
     * Multiply the matrix "a" by the matrix "b".
     *  
     *  The inputs must be two-dimensional matrices and the inner dimension of
     *  "a" (after being transposed if transpose_a is true) must match the
     *  outer dimension of "b" (after being transposed if transposed_b is
     *  true).
     *  
     *  <i>Note</i>: The default kernel implementation for MatMul on GPUs uses
     *  cublas.
     * 
     * @param T data type for ` product()` output
     * @param a
     * @param b
     * @param options carries optional attributes values
     * @return a new instance of MatMul
     * @see org.tensorflow.op.LinalgOps.matMul
     * @param transposeA If true, "a" is transposed before multiplication.
     * @param transposeB If true, "b" is transposed before multiplication.
     */
    public fun <T : TType> matMul(
        a: Operand<T>,
        b: Operand<T>,
        transposeA: Boolean? = null,
        transposeB: Boolean? = null
    ): MatMul<T> = java.matMul<T>(    
        a,
        b,
        *listOfNotNull(
            transposeA?.let{ org.tensorflow.op.linalg.MatMul.transposeA(it) },
            transposeB?.let{ org.tensorflow.op.linalg.MatMul.transposeB(it) }
        ).toTypedArray()
        )

    /**
     * Returns a batched diagonal tensor with given batched diagonal values.
     *  
     *  Returns a tensor with the contents in `diagonal` as `k&#91;0]`-th to `k&#91;1]`-th
     *  diagonals of a matrix, with everything else padded with `padding`. `num_rows`
     *  and `num_cols` specify the dimension of the innermost matrix of the output. If
     *  both are not specified, the op assumes the innermost matrix is square and infers
     *  its size from `k` and the innermost dimension of `diagonal`. If only one of them
     *  is specified, the op assumes the unspecified value is the smallest possible
     *  based on other criteria.
     *  
     *  Let `diagonal` have `r` dimensions `&#91;I, J, ..., L, M, N]`. The output tensor has
     *  rank `r+1` with shape `&#91;I, J, ..., L, M, num_rows, num_cols]` when only one
     *  diagonal is given (`k` is an integer or `k&#91;0] == k&#91;1]`). Otherwise, it has rank
     *  `r` with shape `&#91;I, J, ..., L, num_rows, num_cols]`.
     *  
     *  The second innermost dimension of `diagonal` has double meaning.
     *  When `k` is scalar or `k&#91;0] == k&#91;1]`, `M` is part of the batch size
     *  &#91;I, J, ..., M], and the output tensor is:
     *  ```
     *  output[i, j, ..., l, m, n]
     *    = diagonal[i, j, ..., l, n-max(d_upper, 0)] ; if n - m == d_upper
     *      padding_value                             ; otherwise
     *  ```
     * 
     *  Otherwise, `M` is treated as the number of diagonals for the matrix in the
     *  same batch (`M = k&#91;1]-k&#91;0]+1`), and the output tensor is:
     *  ```
     *  output[i, j, ..., l, m, n]
     *    = diagonal[i, j, ..., l, diag_index, index_in_diag] ; if k[0] <= d <= k[1]
     *      padding_value                                     ; otherwise
     *  ```
     * 
     *  where `d = n - m`, `diag_index = k&#91;1] - d`, and `index_in_diag = n - max(d, 0)`.
     *  
     *  For example:
     *  ```
     *  # The main diagonal.
     *  diagonal = np.array([[1, 2, 3, 4],            # Input shape: (2, 4)
     *                       [5, 6, 7, 8]])
     *  tf.matrix_diag(diagonal) ==> [[[1, 0, 0, 0],  # Output shape: (2, 4, 4)
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
     *    ==> [[[0, 1, 0, 0],  # Output shape: (2, 4, 4)
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
     *    ==> [[[1, 0, 0],  # Output shape: (2, 3, 3)
     *          [4, 2, 0],
     *          [0, 5, 3]],
     *         [[6, 0, 0],
     *          [9, 7, 0],
     *          [0, 1, 9]]]
     * 
     *  # Rectangular matrix.
     *  diagonal = np.array([1, 2])  # Input shape: (2)
     *  tf.matrix_diag(diagonal, k = -1, num_rows = 3, num_cols = 4)
     *    ==> [[0, 0, 0, 0],  # Output shape: (3, 4)
     *         [1, 0, 0, 0],
     *         [0, 2, 0, 0]]
     * 
     *  # Rectangular matrix with inferred num_cols and padding_value = 9.
     *  tf.matrix_diag(diagonal, k = -1, num_rows = 3, padding_value = 9)
     *    ==> [[9, 9],  # Output shape: (3, 2)
     *         [1, 9],
     *         [9, 2]]
     *  ```
     * 
     * 
     * @param T data type for ` output()` output
     * @param diagonal Rank `r`, where `r >= 1`
     * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
     *  diagonal, and negative value means subdiagonals. `k` can be a single integer
     *  (for a single diagonal) or a pair of integers specifying the low and high ends
     *  of a matrix band. `k&#91;0]` must not be larger than `k&#91;1]`.
     * @param numRows The number of rows of the output matrix. If it is not provided, the op
     * assumes
     *  the output matrix is a square matrix and infers the matrix size from k and the
     *  innermost dimension of `diagonal`.
     * @param numCols The number of columns of the output matrix. If it is not provided, the op
     *  assumes the output matrix is a square matrix and infers the matrix size from
     *  k and the innermost dimension of `diagonal`.
     * @param paddingValue The number to fill the area outside the specified diagonal band with.
     *  Default is 0.
     * @return a new instance of MatrixDiag
     * @see org.tensorflow.op.LinalgOps.matrixDiag
     */
    public fun <T : TType> matrixDiag(
        diagonal: Operand<T>,
        k: Operand<TInt32>,
        numRows: Operand<TInt32>,
        numCols: Operand<TInt32>,
        paddingValue: Operand<T>
    ): MatrixDiag<T> = java.matrixDiag<T>(    
        diagonal,
        k,
        numRows,
        numCols,
        paddingValue
        )

    /**
     * Returns the batched diagonal part of a batched tensor.
     *  
     *  Returns a tensor with the `k&#91;0]`-th to `k&#91;1]`-th diagonals of the batched
     *  `input`.
     *  
     *  Assume `input` has `r` dimensions `&#91;I, J, ..., L, M, N]`.
     *  Let `max_diag_len` be the maximum length among all diagonals to be extracted,
     *  `max_diag_len = min(M + min(k&#91;1], 0), N + min(-k&#91;0], 0))`
     *  Let `num_diags` be the number of diagonals to extract,
     *  `num_diags = k&#91;1] - k&#91;0] + 1`.
     *  
     *  If `num_diags == 1`, the output tensor is of rank `r - 1` with shape
     *  `&#91;I, J, ..., L, max_diag_len]` and values:
     *  ```
     *  diagonal[i, j, ..., l, n]
     *    = input[i, j, ..., l, n+y, n+x] ; if 0 <= n+y < M and 0 <= n+x < N,
     *      padding_value                 ; otherwise.
     *  ```
     * 
     *  where `y = max(-k&#91;1], 0)`, `x = max(k&#91;1], 0)`.
     *  
     *  Otherwise, the output tensor has rank `r` with dimensions
     *  `&#91;I, J, ..., L, num_diags, max_diag_len]` with values:
     *  ```
     *  diagonal[i, j, ..., l, m, n]
     *    = input[i, j, ..., l, n+y, n+x] ; if 0 <= n+y < M and 0 <= n+x < N,
     *      padding_value                 ; otherwise.
     *  ```
     * 
     *  where `d = k&#91;1] - m`, `y = max(-d, 0)`, and `x = max(d, 0)`.
     *  
     *  The input must be at least a matrix.
     *  
     *  For example:
     *  ```
     *  input = np.array([[[1, 2, 3, 4],  # Input shape: (2, 3, 4)
     *                     [5, 6, 7, 8],
     *                     [9, 8, 7, 6]],
     *                    [[5, 4, 3, 2],
     *                     [1, 2, 3, 4],
     *                     [5, 6, 7, 8]]])
     * 
     *  # A main diagonal from each batch.
     *  tf.matrix_diag_part(input) ==> [[1, 6, 7],  # Output shape: (2, 3)
     *                                  [5, 2, 7]]
     * 
     *  # A superdiagonal from each batch.
     *  tf.matrix_diag_part(input, k = 1)
     *    ==> [[2, 7, 6],  # Output shape: (2, 3)
     *         [4, 3, 8]]
     * 
     *  # A tridiagonal band from each batch.
     *  tf.matrix_diag_part(input, k = (-1, 1))
     *    ==> [[[2, 7, 6],  # Output shape: (2, 3, 3)
     *          [1, 6, 7],
     *          [5, 8, 0]],
     *         [[4, 3, 8],
     *          [5, 2, 7],
     *          [1, 6, 0]]]
     * 
     *  # Padding value = 9
     *  tf.matrix_diag_part(input, k = (1, 3), padding_value = 9)
     *    ==> [[[4, 9, 9],  # Output shape: (2, 3, 3)
     *          [3, 8, 9],
     *          [2, 7, 6]],
     *         [[2, 9, 9],
     *          [3, 4, 9],
     *          [4, 3, 8]]]
     *  ```
     * 
     * 
     * @param T data type for ` diagonal()` output
     * @param input Rank `r` tensor where `r >= 2`.
     * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
     *  diagonal, and negative value means subdiagonals. `k` can be a single integer
     *  (for a single diagonal) or a pair of integers specifying the low and high ends
     *  of a matrix band. `k&#91;0]` must not be larger than `k&#91;1]`.
     * @param paddingValue The value to fill the area outside the specified diagonal band with.
     *  Default is 0.
     * @return a new instance of MatrixDiagPart
     * @see org.tensorflow.op.LinalgOps.matrixDiagPart
     */
    public fun <T : TType> matrixDiagPart(
        input: Operand<T>,
        k: Operand<TInt32>,
        paddingValue: Operand<T>
    ): MatrixDiagPart<T> = java.matrixDiagPart<T>(    
        input,
        k,
        paddingValue
        )

    /**
     * Returns the batched diagonal part of a batched tensor.
     *  
     *  Returns a tensor with the `k&#91;0]`-th to `k&#91;1]`-th diagonals of the batched
     *  `input`.
     *  
     *  Assume `input` has `r` dimensions `&#91;I, J, ..., L, M, N]`.
     *  Let `max_diag_len` be the maximum length among all diagonals to be extracted,
     *  `max_diag_len = min(M + min(k&#91;1], 0), N + min(-k&#91;0], 0))`
     *  Let `num_diags` be the number of diagonals to extract,
     *  `num_diags = k&#91;1] - k&#91;0] + 1`.
     *  
     *  If `num_diags == 1`, the output tensor is of rank `r - 1` with shape
     *  `&#91;I, J, ..., L, max_diag_len]` and values:
     *  ```
     *  diagonal[i, j, ..., l, n]
     *    = input[i, j, ..., l, n+y, n+x] ; if 0 <= n+y < M and 0 <= n+x < N,
     *      padding_value                 ; otherwise.
     *  ```
     * 
     *  where `y = max(-k&#91;1], 0)`, `x = max(k&#91;1], 0)`.
     *  
     *  Otherwise, the output tensor has rank `r` with dimensions
     *  `&#91;I, J, ..., L, num_diags, max_diag_len]` with values:
     *  ```
     *  diagonal[i, j, ..., l, m, n]
     *    = input[i, j, ..., l, n+y, n+x] ; if 0 <= n+y < M and 0 <= n+x < N,
     *      padding_value                 ; otherwise.
     *  ```
     * 
     *  where `d = k&#91;1] - m`, `y = max(-d, 0) - offset`, and `x = max(d, 0) - offset`.
     *  
     *  `offset` is zero except when the alignment of the diagonal is to the right.
     *  ```
     *  offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
     *                                             and `d >= 0`) or
     *                                           (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
     *                                             and `d <= 0`)
     *           0                          ; otherwise
     *  ```
     * 
     *  where `diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))`.
     *  
     *  The input must be at least a matrix.
     *  
     *  For example:
     *  ```
     *  input = np.array([[[1, 2, 3, 4],  # Input shape: (2, 3, 4)
     *                     [5, 6, 7, 8],
     *                     [9, 8, 7, 6]],
     *                    [[5, 4, 3, 2],
     *                     [1, 2, 3, 4],
     *                     [5, 6, 7, 8]]])
     * 
     *  # A main diagonal from each batch.
     *  tf.matrix_diag_part(input) ==> [[1, 6, 7],  # Output shape: (2, 3)
     *                                  [5, 2, 7]]
     * 
     *  # A superdiagonal from each batch.
     *  tf.matrix_diag_part(input, k = 1)
     *    ==> [[2, 7, 6],  # Output shape: (2, 3)
     *         [4, 3, 8]]
     * 
     *  # A band from each batch.
     *  tf.matrix_diag_part(input, k = (-1, 2))
     *    ==> [[[0, 3, 8],  # Output shape: (2, 4, 3)
     *          [2, 7, 6],
     *          [1, 6, 7],
     *          [5, 8, 0]],
     *         [[0, 3, 4],
     *          [4, 3, 8],
     *          [5, 2, 7],
     *          [1, 6, 0]]]
     * 
     *  # LEFT_RIGHT alignment.
     *  tf.matrix_diag_part(input, k = (-1, 2), align="LEFT_RIGHT")
     *    ==> [[[3, 8, 0],  # Output shape: (2, 4, 3)
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
     *    ==> [[[5, 8],
     *          [9, 0]],
     *         [[1, 6],
     *          [5, 0]]]
     * 
     *  # padding_value = 9
     *  tf.matrix_diag_part(input, k = (1, 3), padding_value = 9)
     *    ==> [[[9, 9, 4],  # Output shape: (2, 3, 3)
     *          [9, 3, 8],
     *          [2, 7, 6]],
     *         [[9, 9, 2],
     *          [9, 3, 4],
     *          [4, 3, 8]]]
     * 
     *  ```
     * 
     * 
     * @param T data type for ` diagonal()` output
     * @param input Rank `r` tensor where `r >= 2`.
     * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
     *  diagonal, and negative value means subdiagonals. `k` can be a single integer
     *  (for a single diagonal) or a pair of integers specifying the low and high ends
     *  of a matrix band. `k&#91;0]` must not be larger than `k&#91;1]`.
     * @param paddingValue The value to fill the area outside the specified diagonal band with.
     *  Default is 0.
     * @param options carries optional attributes values
     * @return a new instance of MatrixDiagPartV3
     * @see org.tensorflow.op.LinalgOps.matrixDiagPartV3
     * @param align Some diagonals are shorter than `max_diag_len` and need to be padded. `align`
     * is
     *  a string specifying how superdiagonals and subdiagonals should be aligned,
     *  respectively. There are four possible alignments: "RIGHT_LEFT" (default),
     *  "LEFT_RIGHT", "LEFT_LEFT", and "RIGHT_RIGHT". "RIGHT_LEFT" aligns superdiagonals
     *  to the right (left-pads the row) and subdiagonals to the left (right-pads the
     *  row). It is the packing format LAPACK uses. cuSPARSE uses "LEFT_RIGHT", which is
     *  the opposite alignment.
     */
    public fun <T : TType> matrixDiagPartV3(
        input: Operand<T>,
        k: Operand<TInt32>,
        paddingValue: Operand<T>,
        align: String? = null
    ): MatrixDiagPartV3<T> = java.matrixDiagPartV3<T>(    
        input,
        k,
        paddingValue,
        *listOfNotNull(
            align?.let{ org.tensorflow.op.linalg.MatrixDiagPartV3.align(it) }
        ).toTypedArray()
        )

    /**
     * Returns a batched diagonal tensor with given batched diagonal values.
     *  
     *  Returns a tensor with the contents in `diagonal` as `k&#91;0]`-th to `k&#91;1]`-th
     *  diagonals of a matrix, with everything else padded with `padding`. `num_rows`
     *  and `num_cols` specify the dimension of the innermost matrix of the output. If
     *  both are not specified, the op assumes the innermost matrix is square and infers
     *  its size from `k` and the innermost dimension of `diagonal`. If only one of them
     *  is specified, the op assumes the unspecified value is the smallest possible
     *  based on other criteria.
     *  
     *  Let `diagonal` have `r` dimensions `&#91;I, J, ..., L, M, N]`. The output tensor has
     *  rank `r+1` with shape `&#91;I, J, ..., L, M, num_rows, num_cols]` when only one
     *  diagonal is given (`k` is an integer or `k&#91;0] == k&#91;1]`). Otherwise, it has rank
     *  `r` with shape `&#91;I, J, ..., L, num_rows, num_cols]`.
     *  
     *  The second innermost dimension of `diagonal` has double meaning.
     *  When `k` is scalar or `k&#91;0] == k&#91;1]`, `M` is part of the batch size
     *  &#91;I, J, ..., M], and the output tensor is:
     *  ```
     *  output[i, j, ..., l, m, n]
     *    = diagonal[i, j, ..., l, n-max(d_upper, 0)] ; if n - m == d_upper
     *      padding_value                             ; otherwise
     *  ```
     * 
     *  Otherwise, `M` is treated as the number of diagonals for the matrix in the
     *  same batch (`M = k&#91;1]-k&#91;0]+1`), and the output tensor is:
     *  ```
     *  output[i, j, ..., l, m, n]
     *    = diagonal[i, j, ..., l, diag_index, index_in_diag] ; if k[0] <= d <= k[1]
     *      padding_value                                     ; otherwise
     *  ```
     * 
     *  where `d = n - m`, `diag_index = &#91;k] - d`, and
     *  `index_in_diag = n - max(d, 0) + offset`.
     *  
     *  `offset` is zero except when the alignment of the diagonal is to the right.
     *  ```
     *  offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
     *                                             and `d >= 0`) or
     *                                           (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
     *                                             and `d <= 0`)
     *           0                          ; otherwise
     *  ```
     * 
     *  where `diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))`.
     *  
     *  For example:
     *  ```
     *  # The main diagonal.
     *  diagonal = np.array([[1, 2, 3, 4],            # Input shape: (2, 4)
     *                       [5, 6, 7, 8]])
     *  tf.matrix_diag(diagonal) ==> [[[1, 0, 0, 0],  # Output shape: (2, 4, 4)
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
     *    ==> [[[0, 1, 0, 0],  # Output shape: (2, 4, 4)
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
     *    ==> [[[1, 8, 0],  # Output shape: (2, 3, 3)
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
     *  tf.matrix_diag(diagonals, k = (-1, 1), align="LEFT_RIGHT")
     *    ==> [[[1, 8, 0],  # Output shape: (2, 3, 3)
     *          [4, 2, 9],
     *          [0, 5, 3]],
     *         [[6, 2, 0],
     *          [9, 7, 3],
     *          [0, 1, 9]]]
     * 
     *  # Rectangular matrix.
     *  diagonal = np.array([1, 2])  # Input shape: (2)
     *  tf.matrix_diag(diagonal, k = -1, num_rows = 3, num_cols = 4)
     *    ==> [[0, 0, 0, 0],  # Output shape: (3, 4)
     *         [1, 0, 0, 0],
     *         [0, 2, 0, 0]]
     * 
     *  # Rectangular matrix with inferred num_cols and padding_value = 9.
     *  tf.matrix_diag(diagonal, k = -1, num_rows = 3, padding_value = 9)
     *    ==> [[9, 9],  # Output shape: (3, 2)
     *         [1, 9],
     *         [9, 2]]
     * 
     *  ```
     * 
     * 
     * @param T data type for ` output()` output
     * @param diagonal Rank `r`, where `r >= 1`
     * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
     *  diagonal, and negative value means subdiagonals. `k` can be a single integer
     *  (for a single diagonal) or a pair of integers specifying the low and high ends
     *  of a matrix band. `k&#91;0]` must not be larger than `k&#91;1]`.
     * @param numRows The number of rows of the output matrix. If it is not provided, the op
     * assumes
     *  the output matrix is a square matrix and infers the matrix size from k and the
     *  innermost dimension of `diagonal`.
     * @param numCols The number of columns of the output matrix. If it is not provided, the op
     *  assumes the output matrix is a square matrix and infers the matrix size from
     *  k and the innermost dimension of `diagonal`.
     * @param paddingValue The number to fill the area outside the specified diagonal band with.
     *  Default is 0.
     * @param options carries optional attributes values
     * @return a new instance of MatrixDiagV3
     * @see org.tensorflow.op.LinalgOps.matrixDiagV3
     * @param align Some diagonals are shorter than `max_diag_len` and need to be padded. `align`
     * is
     *  a string specifying how superdiagonals and subdiagonals should be aligned,
     *  respectively. There are four possible alignments: "RIGHT_LEFT" (default),
     *  "LEFT_RIGHT", "LEFT_LEFT", and "RIGHT_RIGHT". "RIGHT_LEFT" aligns superdiagonals
     *  to the right (left-pads the row) and subdiagonals to the left (right-pads the
     *  row). It is the packing format LAPACK uses. cuSPARSE uses "LEFT_RIGHT", which is
     *  the opposite alignment.
     */
    public fun <T : TType> matrixDiagV3(
        diagonal: Operand<T>,
        k: Operand<TInt32>,
        numRows: Operand<TInt32>,
        numCols: Operand<TInt32>,
        paddingValue: Operand<T>,
        align: String? = null
    ): MatrixDiagV3<T> = java.matrixDiagV3<T>(    
        diagonal,
        k,
        numRows,
        numCols,
        paddingValue,
        *listOfNotNull(
            align?.let{ org.tensorflow.op.linalg.MatrixDiagV3.align(it) }
        ).toTypedArray()
        )

    /**
     * Returns a batched matrix tensor with new batched diagonal values.
     *  
     *  Given `input` and `diagonal`, this operation returns a tensor with the
     *  same shape and values as `input`, except for the specified diagonals of the
     *  innermost matrices. These will be overwritten by the values in `diagonal`.
     *  
     *  `input` has `r+1` dimensions `&#91;I, J, ..., L, M, N]`. When `k` is scalar or
     *  `k&#91;0] == k&#91;1]`, `diagonal` has `r` dimensions `&#91;I, J, ..., L, max_diag_len]`.
     *  Otherwise, it has `r+1` dimensions `&#91;I, J, ..., L, num_diags, max_diag_len]`.
     *  `num_diags` is the number of diagonals, `num_diags = k&#91;1] - k&#91;0] + 1`.
     *  `max_diag_len` is the longest diagonal in the range `&#91;k&#91;0], k&#91;1]]`,
     *  `max_diag_len = min(M + min(k&#91;1], 0), N + min(-k&#91;0], 0))`
     *  
     *  The output is a tensor of rank `k+1` with dimensions `&#91;I, J, ..., L, M, N]`.
     *  If `k` is scalar or `k&#91;0] == k&#91;1]`:
     *  ```
     *  output[i, j, ..., l, m, n]
     *    = diagonal[i, j, ..., l, n-max(k[1], 0)] ; if n - m == k[1]
     *      input[i, j, ..., l, m, n]              ; otherwise
     *  ```
     * 
     *  Otherwise,
     *  ```
     *  output[i, j, ..., l, m, n]
     *    = diagonal[i, j, ..., l, diag_index, index_in_diag] ; if k[0] <= d <= k[1]
     *      input[i, j, ..., l, m, n]                         ; otherwise
     *  ```
     * 
     *  where `d = n - m`, `diag_index = k&#91;1] - d`, and
     *  `index_in_diag = n - max(d, 0) + offset`.
     *  
     *  `offset` is zero except when the alignment of the diagonal is to the right.
     *  ```
     *  offset = max_diag_len - diag_len(d) ; if (`align` in {RIGHT_LEFT, RIGHT_RIGHT}
     *                                             and `d >= 0`) or
     *                                           (`align` in {LEFT_RIGHT, RIGHT_RIGHT}
     *                                             and `d <= 0`)
     *           0                          ; otherwise
     *  ```
     * 
     *  where `diag_len(d) = min(cols - max(d, 0), rows + min(d, 0))`.
     *  
     *  For example:
     *  ```
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
     *    ==> [[[1, 7, 7, 7],  # Output shape: (2, 3, 4)
     *          [7, 2, 7, 7],
     *          [7, 7, 3, 7]],
     *         [[4, 7, 7, 7],
     *          [7, 5, 7, 7],
     *          [7, 7, 6, 7]]]
     * 
     *  # A superdiagonal (per batch).
     *  tf.matrix_set_diag(input, diagonal, k = 1)
     *    ==> [[[7, 1, 7, 7],  # Output shape: (2, 3, 4)
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
     *    ==> [[[1, 6, 9, 7],  # Output shape: (2, 3, 4)
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
     *  tf.matrix_set_diag(input, diagonals, k = (-1, 2), align="LEFT_RIGHT")
     *    ==> [[[1, 6, 9, 7],  # Output shape: (2, 3, 4)
     *          [4, 2, 5, 1],
     *          [7, 5, 3, 8]],
     *         [[6, 5, 1, 7],
     *          [3, 1, 6, 2],
     *          [7, 4, 2, 4]]]
     * 
     *  ```
     * 
     * 
     * @param T data type for ` output()` output
     * @param input Rank `r+1`, where `r >= 1`.
     * @param diagonal Rank `r` when `k` is an integer or `k&#91;0] == k&#91;1]`. Otherwise, it has
     * rank `r+1`.
     *  `k >= 1`.
     * @param k Diagonal offset(s). Positive value means superdiagonal, 0 refers to the main
     *  diagonal, and negative value means subdiagonals. `k` can be a single integer
     *  (for a single diagonal) or a pair of integers specifying the low and high ends
     *  of a matrix band. `k&#91;0]` must not be larger than `k&#91;1]`.
     * @param options carries optional attributes values
     * @return a new instance of MatrixSetDiag
     * @see org.tensorflow.op.LinalgOps.matrixSetDiag
     * @param align Some diagonals are shorter than `max_diag_len` and need to be padded. `align`
     * is
     *  a string specifying how superdiagonals and subdiagonals should be aligned,
     *  respectively. There are four possible alignments: "RIGHT_LEFT" (default),
     *  "LEFT_RIGHT", "LEFT_LEFT", and "RIGHT_RIGHT". "RIGHT_LEFT" aligns superdiagonals
     *  to the right (left-pads the row) and subdiagonals to the left (right-pads the
     *  row). It is the packing format LAPACK uses. cuSPARSE uses "LEFT_RIGHT", which is
     *  the opposite alignment.
     */
    public fun <T : TType> matrixSetDiag(
        input: Operand<T>,
        diagonal: Operand<T>,
        k: Operand<TInt32>,
        align: String? = null
    ): MatrixSetDiag<T> = java.matrixSetDiag<T>(    
        input,
        diagonal,
        k,
        *listOfNotNull(
            align?.let{ org.tensorflow.op.linalg.MatrixSetDiag.align(it) }
        ).toTypedArray()
        )

    /**
     * Solves one or more linear least-squares problems.
     *  
     *  `matrix` is a tensor of shape `&#91;..., M, N]` whose inner-most 2 dimensions
     *  form real or complex matrices of size `&#91;M, N]`. `Rhs` is a tensor of the same
     *  type as `matrix` and shape `&#91;..., M, K]`.
     *  The output is a tensor shape `&#91;..., N, K]` where each output matrix solves
     *  each of the equations
     *  `matrix&#91;..., :, :]` * `output&#91;..., :, :]` = `rhs&#91;..., :, :]`
     *  in the least squares sense.
     *  
     *  We use the following notation for (complex) matrix and right-hand sides
     *  in the batch:
     *  
     *  `matrix`=\\(A \in \mathbb{C}^{m \times n}\\),
     *  `rhs`=\\(B  \in \mathbb{C}^{m \times k}\\),
     *  `output`=\\(X  \in \mathbb{C}^{n \times k}\\),
     *  `l2_regularizer`=\\(\lambda \in \mathbb{R}\\).
     *  
     *  If `fast` is `True`, then the solution is computed by solving the normal
     *  equations using Cholesky decomposition. Specifically, if \\(m \ge n\\) then
     *  \\(X = (A^H A + \lambda I)^{-1} A^H B\\), which solves the least-squares
     *  problem \\(X = \mathrm{argmin}_{Z \in \Re^{n \times k} } ||A Z - B||_F^2 + \lambda
     * ||Z||_F^2\\).
     *  If \\(m \lt n\\) then `output` is computed as
     *  \\(X = A^H (A A^H + \lambda I)^{-1} B\\), which (for \\(\lambda = 0\\)) is the
     *  minimum-norm solution to the under-determined linear system, i.e.
     *  \\(X = \mathrm{argmin}_{Z \in \mathbb{C}^{n \times k} } ||Z||_F^2 \\),
     *  subject to \\(A Z = B\\). Notice that the fast path is only numerically stable
     *  when \\(A\\) is numerically full rank and has a condition number
     *  \\(\mathrm{cond}(A) \lt \frac{1}{\sqrt{\epsilon_{mach} } }\\) or \\(\lambda\\) is
     *  sufficiently large.
     *  
     *  If `fast` is `False` an algorithm based on the numerically robust complete
     *  orthogonal decomposition is used. This computes the minimum-norm
     *  least-squares solution, even when \\(A\\) is rank deficient. This path is
     *  typically 6-7 times slower than the fast path. If `fast` is `False` then
     *  `l2_regularizer` is ignored.
     * 
     * @param T data type for ` output()` output
     * @param matrix Shape is `&#91;..., M, N]`.
     * @param rhs Shape is `&#91;..., M, K]`.
     * @param l2Regularizer Scalar tensor.
     *  
     * @compatibility(numpy) Equivalent to np.linalg.lstsq
     * @end_compatibility
     * @param options carries optional attributes values
     * @return a new instance of MatrixSolveLs
     * @see org.tensorflow.op.LinalgOps.matrixSolveLs
     * @param fast @param fast
     */
    public fun <T : TType> matrixSolveLs(
        matrix: Operand<T>,
        rhs: Operand<T>,
        l2Regularizer: Operand<TFloat64>,
        fast: Boolean? = null
    ): MatrixSolveLs<T> = java.matrixSolveLs<T>(    
        matrix,
        rhs,
        l2Regularizer,
        *listOfNotNull(
            fast?.let{ org.tensorflow.op.linalg.MatrixSolveLs.fast(it) }
        ).toTypedArray()
        )

    /**
     * Computes the QR decompositions of one or more matrices.
     *  
     *  Computes the QR decomposition of each inner matrix in `tensor` such that
     *  `tensor&#91;..., :, :] = q&#91;..., :, :] * r&#91;..., :,:])`
     *  ```
     *  # a is a tensor.
     *  # q is a tensor of orthonormal matrices.
     *  # r is a tensor of upper triangular matrices.
     *  q, r = qr(a)
     *  q_full, r_full = qr(a, full_matrices=True)
     *  ```
     * 
     * 
     * @param T data type for ` q()` output
     * @param input A tensor of shape `&#91;..., M, N]` whose inner-most 2 dimensions
     *  form matrices of size `&#91;M, N]`. Let `P` be the minimum of `M` and `N`.
     * @param options carries optional attributes values
     * @return a new instance of Qr
     * @see org.tensorflow.op.LinalgOps.qr
     * @param fullMatrices If true, compute full-sized `q` and `r`. If false
     *  (the default), compute only the leading `P` columns of `q`.
     */
    public fun <T : TType> qr(input: Operand<T>, fullMatrices: Boolean? = null): Qr<T> =
            java.qr<T>(    
        input,
        *listOfNotNull(
            fullMatrices?.let{ org.tensorflow.op.linalg.Qr.fullMatrices(it) }
        ).toTypedArray()
        )

    /**
     * Perform a quantized matrix multiplication of  `a` by the matrix `b`.
     *  
     *  The inputs must be two-dimensional matrices and the inner dimension of
     *  `a` (after being transposed if `transpose_a` is non-zero) must match the
     *  outer dimension of `b` (after being transposed if `transposed_b` is
     *  non-zero).
     * 
     * @param V data type for ` out()` output
     * @param a Must be a two-dimensional tensor.
     * @param b Must be a two-dimensional tensor.
     * @param minA The float value that the lowest quantized `a` value represents.
     * @param maxA The float value that the highest quantized `a` value represents.
     * @param minB The float value that the lowest quantized `b` value represents.
     * @param maxB The float value that the highest quantized `b` value represents.
     * @param Toutput
     * @param Tactivation The type of output produced by activation function
     *  following this operation.
     * @param options carries optional attributes values
     * @return a new instance of QuantizedMatMul
     * @see org.tensorflow.op.LinalgOps.quantizedMatMul
     * @param transposeA If true, `a` is transposed before multiplication.
     * @param transposeB If true, `b` is transposed before multiplication.
     */
    public fun <V : TType, W : TType> quantizedMatMul(
        a: Operand<out TType>,
        b: Operand<out TType>,
        minA: Operand<TFloat32>,
        maxA: Operand<TFloat32>,
        minB: Operand<TFloat32>,
        maxB: Operand<TFloat32>,
        Toutput: Class<V>,
        Tactivation: Class<W>,
        transposeA: Boolean? = null,
        transposeB: Boolean? = null
    ): QuantizedMatMul<V> = java.quantizedMatMul<V, W>(    
        a,
        b,
        minA,
        maxA,
        minB,
        maxB,
        Toutput,
        Tactivation,
        *listOfNotNull(
            transposeA?.let{ org.tensorflow.op.linalg.QuantizedMatMul.transposeA(it) },
            transposeB?.let{ org.tensorflow.op.linalg.QuantizedMatMul.transposeB(it) }
        ).toTypedArray()
        )

    /**
     * Computes the eigen decomposition of one or more square self-adjoint matrices.
     *  
     *  Computes the eigenvalues and (optionally) eigenvectors of each inner matrix in
     *  `input` such that `input&#91;..., :, :] = v&#91;..., :, :] * diag(e&#91;..., :])`. The
     * eigenvalues
     *  are sorted in non-decreasing order.
     *  ```
     *  # a is a tensor.
     *  # e is a tensor of eigenvalues.
     *  # v is a tensor of eigenvectors.
     *  e, v = self_adjoint_eig(a)
     *  e = self_adjoint_eig(a, compute_v=False)
     *  ```
     * 
     * 
     * @param T data type for ` e()` output
     * @param input `Tensor` input of shape `&#91;N, N]`.
     * @param options carries optional attributes values
     * @return a new instance of SelfAdjointEig
     * @see org.tensorflow.op.LinalgOps.selfAdjointEig
     * @param computeV If `True` then eigenvectors will be computed and returned in `v`.
     *  Otherwise, only the eigenvalues will be computed.
     */
    public fun <T : TType> selfAdjointEig(input: Operand<T>, computeV: Boolean? = null):
            SelfAdjointEig<T> = java.selfAdjointEig<T>(    
        input,
        *listOfNotNull(
            computeV?.let{ org.tensorflow.op.linalg.SelfAdjointEig.computeV(it) }
        ).toTypedArray()
        )

    /**
     * Solves systems of linear equations.
     *  
     *  `Matrix` is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions
     *  form square matrices. `Rhs` is a tensor of shape `&#91;..., M, K]`. The `output` is
     *  a tensor shape `&#91;..., M, K]`.  If `adjoint` is `False` then each output matrix
     *  satisfies `matrix&#91;..., :, :] * output&#91;..., :, :] = rhs&#91;..., :, :]`.
     *  If `adjoint` is `True` then each output matrix satisfies
     *  `adjoint(matrix&#91;..., :, :]) * output&#91;..., :, :] = rhs&#91;..., :, :]`.
     * 
     * @param T data type for ` output()` output
     * @param matrix Shape is `&#91;..., M, M]`.
     * @param rhs Shape is `&#91;..., M, K]`.
     * @param options carries optional attributes values
     * @return a new instance of Solve
     * @see org.tensorflow.op.LinalgOps.solve
     * @param adjoint Boolean indicating whether to solve with `matrix` or its (block-wise)
     *  adjoint.
     */
    public fun <T : TType> solve(
        matrix: Operand<T>,
        rhs: Operand<T>,
        adjoint: Boolean? = null
    ): Solve<T> = java.solve<T>(    
        matrix,
        rhs,
        *listOfNotNull(
            adjoint?.let{ org.tensorflow.op.linalg.Solve.adjoint(it) }
        ).toTypedArray()
        )

    /**
     * Computes the matrix square root of one or more square matrices:
     *  
     *  matmul(sqrtm(A), sqrtm(A)) = A
     *  
     *  The input matrix should be invertible. If the input matrix is real, it should
     *  have no eigenvalues which are real and negative (pairs of complex conjugate
     *  eigenvalues are allowed).
     *  
     *  The matrix square root is computed by first reducing the matrix to
     *  quasi-triangular form with the real Schur decomposition. The square root
     *  of the quasi-triangular matrix is then computed directly. Details of
     *  the algorithm can be found in: Nicholas J. Higham, "Computing real
     *  square roots of a real matrix", Linear Algebra Appl., 1987.
     *  
     *  The input is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions
     *  form square matrices. The output is a tensor of the same shape as the input
     *  containing the matrix square root for all input submatrices `&#91;..., :, :]`.
     * 
     * @param T data type for ` output()` output
     * @param input Shape is `&#91;..., M, M]`.
     * @return a new instance of Sqrtm
     * @see org.tensorflow.op.LinalgOps.sqrtm
     */
    public fun <T : TType> sqrtm(input: Operand<T>): Sqrtm<T> = java.sqrtm<T>(    
        input
        )

    /**
     * Computes the singular value decompositions of one or more matrices.
     *  
     *  Computes the SVD of each inner matrix in `input` such that
     *  `input&#91;..., :, :] = u&#91;..., :, :] * diag(s&#91;..., :, :]) * transpose(v&#91;..., :,
     * :])`
     *  ```
     *  # a is a tensor containing a batch of matrices.
     *  # s is a tensor of singular values for each matrix.
     *  # u is the tensor containing the left singular vectors for each matrix.
     *  # v is the tensor containing the right singular vectors for each matrix.
     *  s, u, v = svd(a)
     *  s, _, _ = svd(a, compute_uv=False)
     *  ```
     * 
     * 
     * @param T data type for ` s()` output
     * @param input A tensor of shape `&#91;..., M, N]` whose inner-most 2 dimensions
     *  form matrices of size `&#91;M, N]`. Let `P` be the minimum of `M` and `N`.
     * @param options carries optional attributes values
     * @return a new instance of Svd
     * @see org.tensorflow.op.LinalgOps.svd
     * @param computeUv If true, left and right singular vectors will be
     *  computed and returned in `u` and `v`, respectively.
     *  If false, `u` and `v` are not set and should never referenced.
     * @param fullMatrices If true, compute full-sized `u` and `v`. If false
     *  (the default), compute only the leading `P` singular vectors.
     *  Ignored if `compute_uv` is `False`.
     */
    public fun <T : TType> svd(
        input: Operand<T>,
        computeUv: Boolean? = null,
        fullMatrices: Boolean? = null
    ): Svd<T> = java.svd<T>(    
        input,
        *listOfNotNull(
            computeUv?.let{ org.tensorflow.op.linalg.Svd.computeUv(it) },
            fullMatrices?.let{ org.tensorflow.op.linalg.Svd.fullMatrices(it) }
        ).toTypedArray()
        )

    /**
     * Returns a diagonal tensor with a given diagonal values.
     *  
     *  Given a `diagonal`, this operation returns a tensor with the `diagonal` and
     *  everything else padded with zeros. The diagonal is computed as follows:
     *  
     *  Assume `diagonal` has dimensions &#91;D1,..., Dk], then the output is a tensor of
     *  rank 2k with dimensions &#91;D1,..., Dk, D1,..., Dk] where:
     *  
     *  `output&#91;i1,..., ik, i1,..., ik] = diagonal&#91;i1, ..., ik]` and 0 everywhere else.
     *  
     *  For example:
     *  ```
     *  # 'diagonal' is [1, 2, 3, 4]
     *  tf.diag(diagonal) ==> [[1, 0, 0, 0]
     *                         [0, 2, 0, 0]
     *                         [0, 0, 3, 0]
     *                         [0, 0, 0, 4]]
     *  ```
     * 
     * 
     * @param T data type for ` output()` output
     * @param diagonal Rank k tensor where k is at most 1.
     * @return a new instance of TensorDiag
     * @see org.tensorflow.op.LinalgOps.tensorDiag
     */
    public fun <T : TType> tensorDiag(diagonal: Operand<T>): TensorDiag<T> = java.tensorDiag<T>(    
        diagonal
        )

    /**
     * Returns the diagonal part of the tensor.
     *  
     *  This operation returns a tensor with the `diagonal` part
     *  of the `input`. The `diagonal` part is computed as follows:
     *  
     *  Assume `input` has dimensions `&#91;D1,..., Dk, D1,..., Dk]`, then the output is a
     *  tensor of rank `k` with dimensions `&#91;D1,..., Dk]` where:
     *  
     *  `diagonal&#91;i1,..., ik] = input&#91;i1, ..., ik, i1,..., ik]`.
     *  
     *  For example:
     *  ```
     *  # 'input' is [[1, 0, 0, 0]
     *                [0, 2, 0, 0]
     *                [0, 0, 3, 0]
     *                [0, 0, 0, 4]]
     * 
     *  tf.diag_part(input) ==> [1, 2, 3, 4]
     *  ```
     * 
     * 
     * @param T data type for ` diagonal()` output
     * @param input Rank k tensor where k is even and not zero.
     * @return a new instance of TensorDiagPart
     * @see org.tensorflow.op.LinalgOps.tensorDiagPart
     */
    public fun <T : TType> tensorDiagPart(input: Operand<T>): TensorDiagPart<T> =
            java.tensorDiagPart<T>(    
        input
        )

    /**
     * Shuffle dimensions of x according to a permutation.
     *  
     *  The output `y` has the same rank as `x`. The shapes of `x` and `y` satisfy:
     *    `y.shape&#91;i] == x.shape&#91;perm&#91;i]] for i in &#91;0, 1, ..., rank(x) - 1]`
     * 
     * @param T data type for ` y()` output
     * @param x
     * @param perm
     * @return a new instance of Transpose
     * @see org.tensorflow.op.LinalgOps.transpose
     */
    public fun <T : TType> transpose(x: Operand<T>, perm: Operand<out TNumber>): Transpose<T> =
            java.transpose<T>(    
        x,
        perm
        )

    /**
     * Solves systems of linear equations with upper or lower triangular matrices by
     * backsubstitution.
     *  
     * 
     *  `matrix` is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions form
     *  square matrices. If `lower` is `True` then the strictly upper triangular part
     *  of each inner-most matrix is assumed to be zero and not accessed.
     *  If `lower` is False then the strictly lower triangular part of each inner-most
     *  matrix is assumed to be zero and not accessed.
     *  `rhs` is a tensor of shape `&#91;..., M, N]`.
     *  
     *  The output is a tensor of shape `&#91;..., M, N]`. If `adjoint` is
     *  `True` then the innermost matrices in `output` satisfy matrix equations
     *  `matrix&#91;..., :, :] * output&#91;..., :, :] = rhs&#91;..., :, :]`.
     *  If `adjoint` is `False` then the strictly then the  innermost matrices in
     *  `output` satisfy matrix equations
     *  `adjoint(matrix&#91;..., i, k]) * output&#91;..., k, j] = rhs&#91;..., i, j]`.
     *  
     *  Note, the batch shapes for the inputs only need to broadcast.
     *  
     *  Example:
     *  {@code
     *  a = tf.constant(&#91;&#91;3,  0,  0,  0],
     *                   &#91;2,  1,  0,  0],
     *                   &#91;1,  0,  1,  0],
     *                   &#91;1,  1,  1,  1]], dtype=tf.float32)
     * 
     *  b = tf.constant(&#91;&#91;4],
     *                   &#91;2],
     *                   &#91;4],
     *                   &#91;2]], dtype=tf.float32)
     * 
     *  x = tf.linalg.triangular_solve(a, b, lower=True)
     *  x
     *  # <tf.Tensor: shape=(4, 1), dtype=float32, numpy=
     *  # array(&#91;&#91; 1.3333334 ],
     *  #        &#91;-0.66666675],
     *  #        &#91; 2.6666665 ],
     *  #        &#91;-1.3333331 ]], dtype=float32)>
     * 
     *  # in python3 one can use `a@x`
     *  tf.matmul(a, x)
     *  # <tf.Tensor: shape=(4, 1), dtype=float32, numpy=
     *  # array(&#91;&#91;4.       ],
     *  #        &#91;2.       ],
     *  #        &#91;4.       ],
     *  #        &#91;1.9999999]], dtype=float32)>
     *  }
     * 
     * @param T data type for ` output()` output
     * @param matrix Shape is `&#91;..., M, M]`.
     * @param rhs Shape is `&#91;..., M, K]`.
     * @param options carries optional attributes values
     * @return a new instance of TriangularSolve
     * @see org.tensorflow.op.LinalgOps.triangularSolve
     * @param lower Boolean indicating whether the innermost matrices in `matrix` are
     *  lower or upper triangular.
     * @param adjoint Boolean indicating whether to solve with `matrix` or its (block-wise)
     *           adjoint.
     *  
     * @compatibility(numpy) Equivalent to scipy.linalg.solve_triangular
     * @end_compatibility
     */
    public fun <T : TType> triangularSolve(
        matrix: Operand<T>,
        rhs: Operand<T>,
        lower: Boolean? = null,
        adjoint: Boolean? = null
    ): TriangularSolve<T> = java.triangularSolve<T>(    
        matrix,
        rhs,
        *listOfNotNull(
            lower?.let{ org.tensorflow.op.linalg.TriangularSolve.lower(it) },
            adjoint?.let{ org.tensorflow.op.linalg.TriangularSolve.adjoint(it) }
        ).toTypedArray()
        )

    /**
     * Computes the eigen decomposition of one or more square matrices.
     *  
     *  Computes the eigenvalues and (optionally) right eigenvectors of each inner matrix in
     *  `input` such that `input&#91;..., :, :] = v&#91;..., :, :] * diag(e&#91;..., :])`. The
     * eigenvalues
     *  are sorted in non-decreasing order.
     *  ```
     *  # a is a tensor.
     *  # e is a tensor of eigenvalues.
     *  # v is a tensor of eigenvectors.
     *  e, v = eig(a)
     *  e = eig(a, compute_v=False)
     *  ```
     * 
     * 
     * @param U data type for ` e()` output
     * @param input `Tensor` input of shape `&#91;N, N]`.
     * @param Tout
     * @param options carries optional attributes values
     * @return a new instance of Eig
     * @see org.tensorflow.op.LinalgOps.eig
     * @param computeV If `True` then eigenvectors will be computed and returned in `v`.
     *  Otherwise, only the eigenvalues will be computed.
     */
    @JvmName("eigReified")
    public inline fun <reified U : TType> eig(input: Operand<out TType>, computeV: Boolean? = null):
            Eig<U> = eig<U>(input, U::class.java, computeV)

    /**
     * Computes the LU decomposition of one or more square matrices.
     *  
     *  The input is a tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions
     *  form square matrices.
     *  
     *  The input has to be invertible.
     *  
     *  The output consists of two tensors LU and P containing the LU decomposition
     *  of all input submatrices `&#91;..., :, :]`. LU encodes the lower triangular and
     *  upper triangular factors.
     *  
     *  For each input submatrix of shape `&#91;M, M]`, L is a lower triangular matrix of
     *  shape `&#91;M, M]` with unit diagonal whose entries correspond to the strictly lower
     *  triangular part of LU. U is a upper triangular matrix of shape `&#91;M, M]` whose
     *  entries correspond to the upper triangular part, including the diagonal, of LU.
     *  
     *  P represents a permutation matrix encoded as a list of indices each between `0`
     *  and `M-1`, inclusive. If P_mat denotes the permutation matrix corresponding to
     *  P, then the L, U and P satisfies P_mat * input = L * U.
     * 
     * @param T data type for ` lu()` output
     * @param U data type for ` p()` output
     * @param input A tensor of shape `&#91;..., M, M]` whose inner-most 2 dimensions form matrices
     * of
     *  size `&#91;M, M]`.
     * @param outputIdxType
     * @return a new instance of Lu
     * @see org.tensorflow.op.LinalgOps.lu
     */
    @JvmName("luReified")
    public inline fun <T : TType, reified U : TNumber> luTyped(input: Operand<T>): Lu<T, U> = lu<T,
            U>(input, U::class.java)

    /**
     * Perform a quantized matrix multiplication of  `a` by the matrix `b`.
     *  
     *  The inputs must be two-dimensional matrices and the inner dimension of
     *  `a` (after being transposed if `transpose_a` is non-zero) must match the
     *  outer dimension of `b` (after being transposed if `transposed_b` is
     *  non-zero).
     * 
     * @param V data type for ` out()` output
     * @param a Must be a two-dimensional tensor.
     * @param b Must be a two-dimensional tensor.
     * @param minA The float value that the lowest quantized `a` value represents.
     * @param maxA The float value that the highest quantized `a` value represents.
     * @param minB The float value that the lowest quantized `b` value represents.
     * @param maxB The float value that the highest quantized `b` value represents.
     * @param Toutput
     * @param Tactivation The type of output produced by activation function
     *  following this operation.
     * @param options carries optional attributes values
     * @return a new instance of QuantizedMatMul
     * @see org.tensorflow.op.LinalgOps.quantizedMatMul
     * @param transposeA If true, `a` is transposed before multiplication.
     * @param transposeB If true, `b` is transposed before multiplication.
     */
    @JvmName("quantizedMatMulReified")
    public inline fun <reified V : TType, reified W : TType> quantizedMatMul(
        a: Operand<out TType>,
        b: Operand<out TType>,
        minA: Operand<TFloat32>,
        maxA: Operand<TFloat32>,
        minB: Operand<TFloat32>,
        maxB: Operand<TFloat32>,
        transposeA: Boolean? = null,
        transposeB: Boolean? = null
    ): QuantizedMatMul<V> = quantizedMatMul<V, W>(a, b, minA, maxA, minB, maxB, V::class.java,
            W::class.java, transposeA, transposeB)
}
