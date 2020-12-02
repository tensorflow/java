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

import org.tensorflow.DataType
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
 * An API for building {@code linalg} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class LinalgOps(
	/**
	 * Get the parent {@link KotlinOps} object.
	 */
	public val ops: KotlinOps
) {
	public val java: org.tensorflow.op.LinalgOps = ops.java.linalg

	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = ops.scope

	public fun <T : TType, U : TNumber> bandPart(
		input: Operand<T>,
		numLower: Operand<U>,
		numUpper: Operand<U>
	): BandPart<T> = java.bandPart<T, U>(	
		input,
		numLower,
		numUpper
		)

	public fun <T : TNumber> batchCholesky(input: Operand<T>): BatchCholesky<T> =
			java.batchCholesky<T>(	
		input
		)

	public fun <T : TNumber> batchCholeskyGrad(l: Operand<T>, grad: Operand<T>): BatchCholeskyGrad<T> =
			java.batchCholeskyGrad<T>(	
		l,
		grad
		)

	public fun <T : TType> batchMatrixBandPart(
		input: Operand<T>,
		numLower: Operand<TInt64>,
		numUpper: Operand<TInt64>
	): BatchMatrixBandPart<T> = java.batchMatrixBandPart<T>(	
		input,
		numLower,
		numUpper
		)

	public fun <T : TType> batchMatrixDeterminant(input: Operand<T>): BatchMatrixDeterminant<T> =
			java.batchMatrixDeterminant<T>(	
		input
		)

	public fun <T : TType> batchMatrixDiag(diagonal: Operand<T>): BatchMatrixDiag<T> =
			java.batchMatrixDiag<T>(	
		diagonal
		)

	public fun <T : TType> batchMatrixDiagPart(input: Operand<T>): BatchMatrixDiagPart<T> =
			java.batchMatrixDiagPart<T>(	
		input
		)

	public fun <T : TNumber> batchMatrixInverse(input: Operand<T>, adjoint: Boolean? = null):
			BatchMatrixInverse<T> = java.batchMatrixInverse<T>(	
		input,
		*listOfNotNull(
			adjoint?.let{ org.tensorflow.op.linalg.BatchMatrixInverse.adjoint(it) }
		).toTypedArray()
		)

	public fun <T : TType> batchMatrixSetDiag(input: Operand<T>, diagonal: Operand<T>):
			BatchMatrixSetDiag<T> = java.batchMatrixSetDiag<T>(	
		input,
		diagonal
		)

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

	public fun <T : TNumber> batchSelfAdjointEig(input: Operand<T>, computeV: Boolean? = null):
			BatchSelfAdjointEig<T> = java.batchSelfAdjointEig<T>(	
		input,
		*listOfNotNull(
			computeV?.let{ org.tensorflow.op.linalg.BatchSelfAdjointEig.computeV(it) }
		).toTypedArray()
		)

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

	public fun <T : TType> cholesky(input: Operand<T>): Cholesky<T> = java.cholesky<T>(	
		input
		)

	public fun <T : TNumber> choleskyGrad(l: Operand<T>, grad: Operand<T>): CholeskyGrad<T> =
			java.choleskyGrad<T>(	
		l,
		grad
		)

	public fun <T : TType, U : TNumber> conjugateTranspose(x: Operand<T>, perm: Operand<U>):
			ConjugateTranspose<T> = java.conjugateTranspose<T, U>(	
		x,
		perm
		)

	public fun <T : TNumber> cross(a: Operand<T>, b: Operand<T>): Cross<T> = java.cross<T>(	
		a,
		b
		)

	public fun <T : TType> det(input: Operand<T>): Det<T> = java.det<T>(	
		input
		)

	public fun <U : TType, T : TType> eig(
		input: Operand<T>,
		Tout: DataType<U>,
		computeV: Boolean? = null
	): Eig<U> = java.eig<U, T>(	
		input,
		Tout,
		*listOfNotNull(
			computeV?.let{ org.tensorflow.op.linalg.Eig.computeV(it) }
		).toTypedArray()
		)

	public fun <T : TType> einsum(inputs: Iterable<Operand<T>>, equation: String): Einsum<T> =
			java.einsum<T>(	
		inputs,
		equation
		)

	public fun <T : TType, U : TNumber> euclideanNorm(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): EuclideanNorm<T> = java.euclideanNorm<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.linalg.EuclideanNorm.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType> inv(input: Operand<T>, adjoint: Boolean? = null): Inv<T> = java.inv<T>(	
		input,
		*listOfNotNull(
			adjoint?.let{ org.tensorflow.op.linalg.Inv.adjoint(it) }
		).toTypedArray()
		)

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

	public fun <T : TType> logMatrixDeterminant(input: Operand<T>): LogMatrixDeterminant<T> =
			java.logMatrixDeterminant<T>(	
		input
		)

	public fun <T : TType> lu(input: Operand<T>): Lu<T, TInt32> = java.lu<T>(	
		input
		)

	public fun <T : TType, U : TNumber> lu(input: Operand<T>, outputIdxType: DataType<U>): Lu<T, U> =
			java.lu<T, U>(	
		input,
		outputIdxType
		)

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

	public fun <T : TType> matrixDiagPart(
		input: Operand<T>,
		k: Operand<TInt32>,
		paddingValue: Operand<T>
	): MatrixDiagPart<T> = java.matrixDiagPart<T>(	
		input,
		k,
		paddingValue
		)

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

	public fun <T : TType> qr(input: Operand<T>, fullMatrices: Boolean? = null): Qr<T> = java.qr<T>(	
		input,
		*listOfNotNull(
			fullMatrices?.let{ org.tensorflow.op.linalg.Qr.fullMatrices(it) }
		).toTypedArray()
		)

	public fun <V : TType, T : TType, U : TType, W : TType> quantizedMatMul(
		a: Operand<T>,
		b: Operand<U>,
		minA: Operand<TFloat32>,
		maxA: Operand<TFloat32>,
		minB: Operand<TFloat32>,
		maxB: Operand<TFloat32>,
		Toutput: DataType<V>,
		Tactivation: DataType<W>,
		transposeA: Boolean? = null,
		transposeB: Boolean? = null
	): QuantizedMatMul<V> = java.quantizedMatMul<V, T, U, W>(	
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

	public fun <T : TType> selfAdjointEig(input: Operand<T>, computeV: Boolean? = null):
			SelfAdjointEig<T> = java.selfAdjointEig<T>(	
		input,
		*listOfNotNull(
			computeV?.let{ org.tensorflow.op.linalg.SelfAdjointEig.computeV(it) }
		).toTypedArray()
		)

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

	public fun <T : TType> sqrtm(input: Operand<T>): Sqrtm<T> = java.sqrtm<T>(	
		input
		)

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

	public fun <T : TType> tensorDiag(diagonal: Operand<T>): TensorDiag<T> = java.tensorDiag<T>(	
		diagonal
		)

	public fun <T : TType> tensorDiagPart(input: Operand<T>): TensorDiagPart<T> =
			java.tensorDiagPart<T>(	
		input
		)

	public fun <T : TType, U : TNumber> transpose(x: Operand<T>, perm: Operand<U>): Transpose<T> =
			java.transpose<T, U>(	
		x,
		perm
		)

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
}
