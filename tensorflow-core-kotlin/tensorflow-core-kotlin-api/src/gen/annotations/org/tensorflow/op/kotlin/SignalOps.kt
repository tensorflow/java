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
import org.tensorflow.op.signal.BatchFft
import org.tensorflow.op.signal.BatchFft2d
import org.tensorflow.op.signal.BatchFft3d
import org.tensorflow.op.signal.BatchIfft
import org.tensorflow.op.signal.BatchIfft2d
import org.tensorflow.op.signal.BatchIfft3d
import org.tensorflow.op.signal.Fft
import org.tensorflow.op.signal.Fft2d
import org.tensorflow.op.signal.Fft3d
import org.tensorflow.op.signal.Ifft
import org.tensorflow.op.signal.Ifft2d
import org.tensorflow.op.signal.Ifft3d
import org.tensorflow.op.signal.Irfft
import org.tensorflow.op.signal.Irfft2d
import org.tensorflow.op.signal.Irfft3d
import org.tensorflow.op.signal.Rfft
import org.tensorflow.op.signal.Rfft2d
import org.tensorflow.op.signal.Rfft3d
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code signal} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class SignalOps(
	/**
	 * Get the parent {@link KotlinOps} object.
	 */
	public val ops: KotlinOps
) {
	public val java: org.tensorflow.op.SignalOps = ops.java.signal

	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = ops.scope

	public fun batchFft(input: Operand<*>): BatchFft = java.batchFft(	
		input
		)

	public fun batchFft2d(input: Operand<*>): BatchFft2d = java.batchFft2d(	
		input
		)

	public fun batchFft3d(input: Operand<*>): BatchFft3d = java.batchFft3d(	
		input
		)

	public fun batchIfft(input: Operand<*>): BatchIfft = java.batchIfft(	
		input
		)

	public fun batchIfft2d(input: Operand<*>): BatchIfft2d = java.batchIfft2d(	
		input
		)

	public fun batchIfft3d(input: Operand<*>): BatchIfft3d = java.batchIfft3d(	
		input
		)

	public fun <T : TType> fft(input: Operand<T>): Fft<T> = java.fft<T>(	
		input
		)

	public fun <T : TType> fft2d(input: Operand<T>): Fft2d<T> = java.fft2d<T>(	
		input
		)

	public fun <T : TType> fft3d(input: Operand<T>): Fft3d<T> = java.fft3d<T>(	
		input
		)

	public fun <T : TType> ifft(input: Operand<T>): Ifft<T> = java.ifft<T>(	
		input
		)

	public fun <T : TType> ifft2d(input: Operand<T>): Ifft2d<T> = java.ifft2d<T>(	
		input
		)

	public fun <T : TType> ifft3d(input: Operand<T>): Ifft3d<T> = java.ifft3d<T>(	
		input
		)

	public fun <T : TType> irfft(input: Operand<T>, fftLength: Operand<TInt32>): Irfft<TFloat32> =
			java.irfft<T>(	
		input,
		fftLength
		)

	public fun <U : TNumber, T : TType> irfft(
		input: Operand<T>,
		fftLength: Operand<TInt32>,
		Treal: DataType<U>
	): Irfft<U> = java.irfft<U, T>(	
		input,
		fftLength,
		Treal
		)

	public fun <T : TType> irfft2d(input: Operand<T>, fftLength: Operand<TInt32>): Irfft2d<TFloat32> =
			java.irfft2d<T>(	
		input,
		fftLength
		)

	public fun <U : TNumber, T : TType> irfft2d(
		input: Operand<T>,
		fftLength: Operand<TInt32>,
		Treal: DataType<U>
	): Irfft2d<U> = java.irfft2d<U, T>(	
		input,
		fftLength,
		Treal
		)

	public fun <T : TType> irfft3d(input: Operand<T>, fftLength: Operand<TInt32>): Irfft3d<TFloat32> =
			java.irfft3d<T>(	
		input,
		fftLength
		)

	public fun <U : TNumber, T : TType> irfft3d(
		input: Operand<T>,
		fftLength: Operand<TInt32>,
		Treal: DataType<U>
	): Irfft3d<U> = java.irfft3d<U, T>(	
		input,
		fftLength,
		Treal
		)

	public fun <U : TType, T : TNumber> rfft(
		input: Operand<T>,
		fftLength: Operand<TInt32>,
		Tcomplex: DataType<U>
	): Rfft<U> = java.rfft<U, T>(	
		input,
		fftLength,
		Tcomplex
		)

	public fun <U : TType, T : TNumber> rfft2d(
		input: Operand<T>,
		fftLength: Operand<TInt32>,
		Tcomplex: DataType<U>
	): Rfft2d<U> = java.rfft2d<U, T>(	
		input,
		fftLength,
		Tcomplex
		)

	public fun <U : TType, T : TNumber> rfft3d(
		input: Operand<T>,
		fftLength: Operand<TInt32>,
		Tcomplex: DataType<U>
	): Rfft3d<U> = java.rfft3d<U, T>(	
		input,
		fftLength,
		Tcomplex
		)
}
