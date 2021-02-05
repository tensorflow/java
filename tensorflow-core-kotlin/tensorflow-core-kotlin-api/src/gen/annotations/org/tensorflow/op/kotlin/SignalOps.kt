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
 * An API for building `signal` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class SignalOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.SignalOps = ops.java.signal

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * 
     * @param input
     * @return a new instance of BatchFft
     * @see org.tensorflow.op.SignalOps.batchFft
     */
    public fun batchFft(input: Operand<*>): BatchFft = java.batchFft(    
        input
        )

    /**
     * 
     * @param input
     * @return a new instance of BatchFft2d
     * @see org.tensorflow.op.SignalOps.batchFft2d
     */
    public fun batchFft2d(input: Operand<*>): BatchFft2d = java.batchFft2d(    
        input
        )

    /**
     * 
     * @param input
     * @return a new instance of BatchFft3d
     * @see org.tensorflow.op.SignalOps.batchFft3d
     */
    public fun batchFft3d(input: Operand<*>): BatchFft3d = java.batchFft3d(    
        input
        )

    /**
     * 
     * @param input
     * @return a new instance of BatchIfft
     * @see org.tensorflow.op.SignalOps.batchIfft
     */
    public fun batchIfft(input: Operand<*>): BatchIfft = java.batchIfft(    
        input
        )

    /**
     * 
     * @param input
     * @return a new instance of BatchIfft2d
     * @see org.tensorflow.op.SignalOps.batchIfft2d
     */
    public fun batchIfft2d(input: Operand<*>): BatchIfft2d = java.batchIfft2d(    
        input
        )

    /**
     * 
     * @param input
     * @return a new instance of BatchIfft3d
     * @see org.tensorflow.op.SignalOps.batchIfft3d
     */
    public fun batchIfft3d(input: Operand<*>): BatchIfft3d = java.batchIfft3d(    
        input
        )

    /**
     * Fast Fourier transform.
     *  
     *  Computes the 1-dimensional discrete Fourier transform over the inner-most
     *  dimension of `input`.
     * 
     * @param T data type for ` output()` output
     * @param input A complex tensor.
     * @return a new instance of Fft
     * @see org.tensorflow.op.SignalOps.fft
     */
    public fun <T : TType> fft(input: Operand<T>): Fft<T> = java.fft<T>(    
        input
        )

    /**
     * 2D fast Fourier transform.
     *  
     *  Computes the 2-dimensional discrete Fourier transform over the inner-most
     *  2 dimensions of `input`.
     * 
     * @param T data type for ` output()` output
     * @param input A complex tensor.
     * @return a new instance of Fft2d
     * @see org.tensorflow.op.SignalOps.fft2d
     */
    public fun <T : TType> fft2d(input: Operand<T>): Fft2d<T> = java.fft2d<T>(    
        input
        )

    /**
     * 3D fast Fourier transform.
     *  
     *  Computes the 3-dimensional discrete Fourier transform over the inner-most 3
     *  dimensions of `input`.
     * 
     * @param T data type for ` output()` output
     * @param input A complex tensor.
     * @return a new instance of Fft3d
     * @see org.tensorflow.op.SignalOps.fft3d
     */
    public fun <T : TType> fft3d(input: Operand<T>): Fft3d<T> = java.fft3d<T>(    
        input
        )

    /**
     * Inverse fast Fourier transform.
     *  
     *  Computes the inverse 1-dimensional discrete Fourier transform over the
     *  inner-most dimension of `input`.
     * 
     * @param T data type for ` output()` output
     * @param input A complex tensor.
     * @return a new instance of Ifft
     * @see org.tensorflow.op.SignalOps.ifft
     */
    public fun <T : TType> ifft(input: Operand<T>): Ifft<T> = java.ifft<T>(    
        input
        )

    /**
     * Inverse 2D fast Fourier transform.
     *  
     *  Computes the inverse 2-dimensional discrete Fourier transform over the
     *  inner-most 2 dimensions of `input`.
     * 
     * @param T data type for ` output()` output
     * @param input A complex tensor.
     * @return a new instance of Ifft2d
     * @see org.tensorflow.op.SignalOps.ifft2d
     */
    public fun <T : TType> ifft2d(input: Operand<T>): Ifft2d<T> = java.ifft2d<T>(    
        input
        )

    /**
     * Inverse 3D fast Fourier transform.
     *  
     *  Computes the inverse 3-dimensional discrete Fourier transform over the
     *  inner-most 3 dimensions of `input`.
     * 
     * @param T data type for ` output()` output
     * @param input A complex tensor.
     * @return a new instance of Ifft3d
     * @see org.tensorflow.op.SignalOps.ifft3d
     */
    public fun <T : TType> ifft3d(input: Operand<T>): Ifft3d<T> = java.ifft3d<T>(    
        input
        )

    /**
     * Inverse real-valued fast Fourier transform.
     *  
     *  Computes the inverse 1-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most dimension of `input`.
     *  
     *  The inner-most dimension of `input` is assumed to be the result of `RFFT`: the
     *  `fft_length / 2 + 1` unique components of the DFT of a real-valued signal. If
     *  `fft_length` is not provided, it is computed from the size of the inner-most
     *  dimension of `input` (`fft_length = 2 * (inner - 1)`). If the FFT length used to
     *  compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along the axis `signal.Irfft` is computed on, if `fft_length / 2 + 1` is smaller
     *  than the corresponding dimension of `input`, the dimension is cropped. If it is
     *  larger, the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;1]. The FFT length.
     * @return a new instance of Irfft
     * @see org.tensorflow.op.SignalOps.irfft
     */
    public fun irfft(input: Operand<out TType>, fftLength: Operand<TInt32>): Irfft<TFloat32> =
            java.irfft(    
        input,
        fftLength
        )

    /**
     * Inverse real-valued fast Fourier transform.
     *  
     *  Computes the inverse 1-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most dimension of `input`.
     *  
     *  The inner-most dimension of `input` is assumed to be the result of `RFFT`: the
     *  `fft_length / 2 + 1` unique components of the DFT of a real-valued signal. If
     *  `fft_length` is not provided, it is computed from the size of the inner-most
     *  dimension of `input` (`fft_length = 2 * (inner - 1)`). If the FFT length used to
     *  compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along the axis `signal.Irfft` is computed on, if `fft_length / 2 + 1` is smaller
     *  than the corresponding dimension of `input`, the dimension is cropped. If it is
     *  larger, the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;1]. The FFT length.
     * @param Treal
     * @return a new instance of Irfft
     * @see org.tensorflow.op.SignalOps.irfft
     */
    public fun <U : TNumber> irfft(
        input: Operand<out TType>,
        fftLength: Operand<TInt32>,
        Treal: Class<U>
    ): Irfft<U> = java.irfft<U>(    
        input,
        fftLength,
        Treal
        )

    /**
     * Inverse 2D real-valued fast Fourier transform.
     *  
     *  Computes the inverse 2-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most 2 dimensions of `input`.
     *  
     *  The inner-most 2 dimensions of `input` are assumed to be the result of `RFFT2D`:
     *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
     *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
     *  from the size of the inner-most 2 dimensions of `input`. If the FFT length used
     *  to compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along each axis `signal.Irfft2d` is computed on, if `fft_length` (or
     *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;2]. The FFT length for each dimension.
     * @return a new instance of Irfft2d
     * @see org.tensorflow.op.SignalOps.irfft2d
     */
    public fun irfft2d(input: Operand<out TType>, fftLength: Operand<TInt32>): Irfft2d<TFloat32> =
            java.irfft2d(    
        input,
        fftLength
        )

    /**
     * Inverse 2D real-valued fast Fourier transform.
     *  
     *  Computes the inverse 2-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most 2 dimensions of `input`.
     *  
     *  The inner-most 2 dimensions of `input` are assumed to be the result of `RFFT2D`:
     *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
     *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
     *  from the size of the inner-most 2 dimensions of `input`. If the FFT length used
     *  to compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along each axis `signal.Irfft2d` is computed on, if `fft_length` (or
     *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;2]. The FFT length for each dimension.
     * @param Treal
     * @return a new instance of Irfft2d
     * @see org.tensorflow.op.SignalOps.irfft2d
     */
    public fun <U : TNumber> irfft2d(
        input: Operand<out TType>,
        fftLength: Operand<TInt32>,
        Treal: Class<U>
    ): Irfft2d<U> = java.irfft2d<U>(    
        input,
        fftLength,
        Treal
        )

    /**
     * Inverse 3D real-valued fast Fourier transform.
     *  
     *  Computes the inverse 3-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most 3 dimensions of `input`.
     *  
     *  The inner-most 3 dimensions of `input` are assumed to be the result of `RFFT3D`:
     *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
     *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
     *  from the size of the inner-most 3 dimensions of `input`. If the FFT length used
     *  to compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along each axis `signal.Irfft3d` is computed on, if `fft_length` (or
     *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;3]. The FFT length for each dimension.
     * @return a new instance of Irfft3d
     * @see org.tensorflow.op.SignalOps.irfft3d
     */
    public fun irfft3d(input: Operand<out TType>, fftLength: Operand<TInt32>): Irfft3d<TFloat32> =
            java.irfft3d(    
        input,
        fftLength
        )

    /**
     * Inverse 3D real-valued fast Fourier transform.
     *  
     *  Computes the inverse 3-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most 3 dimensions of `input`.
     *  
     *  The inner-most 3 dimensions of `input` are assumed to be the result of `RFFT3D`:
     *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
     *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
     *  from the size of the inner-most 3 dimensions of `input`. If the FFT length used
     *  to compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along each axis `signal.Irfft3d` is computed on, if `fft_length` (or
     *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;3]. The FFT length for each dimension.
     * @param Treal
     * @return a new instance of Irfft3d
     * @see org.tensorflow.op.SignalOps.irfft3d
     */
    public fun <U : TNumber> irfft3d(
        input: Operand<out TType>,
        fftLength: Operand<TInt32>,
        Treal: Class<U>
    ): Irfft3d<U> = java.irfft3d<U>(    
        input,
        fftLength,
        Treal
        )

    /**
     * Real-valued fast Fourier transform.
     *  
     *  Computes the 1-dimensional discrete Fourier transform of a real-valued signal
     *  over the inner-most dimension of `input`.
     *  
     *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft` only returns the
     *  `fft_length / 2 + 1` unique components of the FFT: the zero-frequency term,
     *  followed by the `fft_length / 2` positive-frequency terms.
     *  
     *  Along the axis `signal.Rfft` is computed on, if `fft_length` is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A float32 tensor.
     * @param fftLength An int32 tensor of shape &#91;1]. The FFT length.
     * @param Tcomplex
     * @return a new instance of Rfft
     * @see org.tensorflow.op.SignalOps.rfft
     */
    public fun <U : TType> rfft(
        input: Operand<out TNumber>,
        fftLength: Operand<TInt32>,
        Tcomplex: Class<U>
    ): Rfft<U> = java.rfft<U>(    
        input,
        fftLength,
        Tcomplex
        )

    /**
     * 2D real-valued fast Fourier transform.
     *  
     *  Computes the 2-dimensional discrete Fourier transform of a real-valued signal
     *  over the inner-most 2 dimensions of `input`.
     *  
     *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft2d` only returns the
     *  `fft_length / 2 + 1` unique components of the FFT for the inner-most dimension
     *  of `output`: the zero-frequency term, followed by the `fft_length / 2`
     *  positive-frequency terms.
     *  
     *  Along each axis `signal.Rfft2d` is computed on, if `fft_length` is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A float32 tensor.
     * @param fftLength An int32 tensor of shape &#91;2]. The FFT length for each dimension.
     * @param Tcomplex
     * @return a new instance of Rfft2d
     * @see org.tensorflow.op.SignalOps.rfft2d
     */
    public fun <U : TType> rfft2d(
        input: Operand<out TNumber>,
        fftLength: Operand<TInt32>,
        Tcomplex: Class<U>
    ): Rfft2d<U> = java.rfft2d<U>(    
        input,
        fftLength,
        Tcomplex
        )

    /**
     * 3D real-valued fast Fourier transform.
     *  
     *  Computes the 3-dimensional discrete Fourier transform of a real-valued signal
     *  over the inner-most 3 dimensions of `input`.
     *  
     *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft3d` only returns the
     *  `fft_length / 2 + 1` unique components of the FFT for the inner-most dimension
     *  of `output`: the zero-frequency term, followed by the `fft_length / 2`
     *  positive-frequency terms.
     *  
     *  Along each axis `signal.Rfft3d` is computed on, if `fft_length` is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A float32 tensor.
     * @param fftLength An int32 tensor of shape &#91;3]. The FFT length for each dimension.
     * @param Tcomplex
     * @return a new instance of Rfft3d
     * @see org.tensorflow.op.SignalOps.rfft3d
     */
    public fun <U : TType> rfft3d(
        input: Operand<out TNumber>,
        fftLength: Operand<TInt32>,
        Tcomplex: Class<U>
    ): Rfft3d<U> = java.rfft3d<U>(    
        input,
        fftLength,
        Tcomplex
        )

    /**
     * Inverse real-valued fast Fourier transform.
     *  
     *  Computes the inverse 1-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most dimension of `input`.
     *  
     *  The inner-most dimension of `input` is assumed to be the result of `RFFT`: the
     *  `fft_length / 2 + 1` unique components of the DFT of a real-valued signal. If
     *  `fft_length` is not provided, it is computed from the size of the inner-most
     *  dimension of `input` (`fft_length = 2 * (inner - 1)`). If the FFT length used to
     *  compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along the axis `signal.Irfft` is computed on, if `fft_length / 2 + 1` is smaller
     *  than the corresponding dimension of `input`, the dimension is cropped. If it is
     *  larger, the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;1]. The FFT length.
     * @param Treal
     * @return a new instance of Irfft
     * @see org.tensorflow.op.SignalOps.irfft
     */
    @JvmName("irfftReified")
    public inline fun <reified U : TNumber> irfftTyped(input: Operand<out TType>,
            fftLength: Operand<TInt32>): Irfft<U> = irfft<U>(input, fftLength, U::class.java)

    /**
     * Inverse 2D real-valued fast Fourier transform.
     *  
     *  Computes the inverse 2-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most 2 dimensions of `input`.
     *  
     *  The inner-most 2 dimensions of `input` are assumed to be the result of `RFFT2D`:
     *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
     *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
     *  from the size of the inner-most 2 dimensions of `input`. If the FFT length used
     *  to compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along each axis `signal.Irfft2d` is computed on, if `fft_length` (or
     *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;2]. The FFT length for each dimension.
     * @param Treal
     * @return a new instance of Irfft2d
     * @see org.tensorflow.op.SignalOps.irfft2d
     */
    @JvmName("irfft2dReified")
    public inline fun <reified U : TNumber> irfft2dTyped(input: Operand<out TType>,
            fftLength: Operand<TInt32>): Irfft2d<U> = irfft2d<U>(input, fftLength, U::class.java)

    /**
     * Inverse 3D real-valued fast Fourier transform.
     *  
     *  Computes the inverse 3-dimensional discrete Fourier transform of a real-valued
     *  signal over the inner-most 3 dimensions of `input`.
     *  
     *  The inner-most 3 dimensions of `input` are assumed to be the result of `RFFT3D`:
     *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
     *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
     *  from the size of the inner-most 3 dimensions of `input`. If the FFT length used
     *  to compute `input` is odd, it should be provided since it cannot be inferred
     *  properly.
     *  
     *  Along each axis `signal.Irfft3d` is computed on, if `fft_length` (or
     *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A complex tensor.
     * @param fftLength An int32 tensor of shape &#91;3]. The FFT length for each dimension.
     * @param Treal
     * @return a new instance of Irfft3d
     * @see org.tensorflow.op.SignalOps.irfft3d
     */
    @JvmName("irfft3dReified")
    public inline fun <reified U : TNumber> irfft3dTyped(input: Operand<out TType>,
            fftLength: Operand<TInt32>): Irfft3d<U> = irfft3d<U>(input, fftLength, U::class.java)

    /**
     * Real-valued fast Fourier transform.
     *  
     *  Computes the 1-dimensional discrete Fourier transform of a real-valued signal
     *  over the inner-most dimension of `input`.
     *  
     *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft` only returns the
     *  `fft_length / 2 + 1` unique components of the FFT: the zero-frequency term,
     *  followed by the `fft_length / 2` positive-frequency terms.
     *  
     *  Along the axis `signal.Rfft` is computed on, if `fft_length` is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A float32 tensor.
     * @param fftLength An int32 tensor of shape &#91;1]. The FFT length.
     * @param Tcomplex
     * @return a new instance of Rfft
     * @see org.tensorflow.op.SignalOps.rfft
     */
    @JvmName("rfftReified")
    public inline fun <reified U : TType> rfft(input: Operand<out TNumber>,
            fftLength: Operand<TInt32>): Rfft<U> = rfft<U>(input, fftLength, U::class.java)

    /**
     * 2D real-valued fast Fourier transform.
     *  
     *  Computes the 2-dimensional discrete Fourier transform of a real-valued signal
     *  over the inner-most 2 dimensions of `input`.
     *  
     *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft2d` only returns the
     *  `fft_length / 2 + 1` unique components of the FFT for the inner-most dimension
     *  of `output`: the zero-frequency term, followed by the `fft_length / 2`
     *  positive-frequency terms.
     *  
     *  Along each axis `signal.Rfft2d` is computed on, if `fft_length` is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A float32 tensor.
     * @param fftLength An int32 tensor of shape &#91;2]. The FFT length for each dimension.
     * @param Tcomplex
     * @return a new instance of Rfft2d
     * @see org.tensorflow.op.SignalOps.rfft2d
     */
    @JvmName("rfft2dReified")
    public inline fun <reified U : TType> rfft2d(input: Operand<out TNumber>,
            fftLength: Operand<TInt32>): Rfft2d<U> = rfft2d<U>(input, fftLength, U::class.java)

    /**
     * 3D real-valued fast Fourier transform.
     *  
     *  Computes the 3-dimensional discrete Fourier transform of a real-valued signal
     *  over the inner-most 3 dimensions of `input`.
     *  
     *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft3d` only returns the
     *  `fft_length / 2 + 1` unique components of the FFT for the inner-most dimension
     *  of `output`: the zero-frequency term, followed by the `fft_length / 2`
     *  positive-frequency terms.
     *  
     *  Along each axis `signal.Rfft3d` is computed on, if `fft_length` is smaller than the
     *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
     *  the dimension is padded with zeros.
     * 
     * @param U data type for ` output()` output
     * @param input A float32 tensor.
     * @param fftLength An int32 tensor of shape &#91;3]. The FFT length for each dimension.
     * @param Tcomplex
     * @return a new instance of Rfft3d
     * @see org.tensorflow.op.SignalOps.rfft3d
     */
    @JvmName("rfft3dReified")
    public inline fun <reified U : TType> rfft3d(input: Operand<out TNumber>,
            fftLength: Operand<TInt32>): Rfft3d<U> = rfft3d<U>(input, fftLength, U::class.java)
}
