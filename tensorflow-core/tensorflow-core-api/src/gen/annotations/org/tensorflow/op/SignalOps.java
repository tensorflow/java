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
import org.tensorflow.op.signal.BatchFft;
import org.tensorflow.op.signal.BatchFft2d;
import org.tensorflow.op.signal.BatchFft3d;
import org.tensorflow.op.signal.BatchIfft;
import org.tensorflow.op.signal.BatchIfft2d;
import org.tensorflow.op.signal.BatchIfft3d;
import org.tensorflow.op.signal.Fft;
import org.tensorflow.op.signal.Fft2d;
import org.tensorflow.op.signal.Fft3d;
import org.tensorflow.op.signal.Ifft;
import org.tensorflow.op.signal.Ifft2d;
import org.tensorflow.op.signal.Ifft3d;
import org.tensorflow.op.signal.Irfft;
import org.tensorflow.op.signal.Irfft2d;
import org.tensorflow.op.signal.Irfft3d;
import org.tensorflow.op.signal.Rfft;
import org.tensorflow.op.signal.Rfft2d;
import org.tensorflow.op.signal.Rfft3d;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code signal} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class SignalOps {
  private final Scope scope;

  private final Ops ops;

  SignalOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   *
   * @param input
   * @return a new instance of BatchFft
   */
  public BatchFft batchFft(Operand<?> input) {
    return BatchFft.create(scope, input);
  }

  /**
   *
   * @param input
   * @return a new instance of BatchFft2d
   */
  public BatchFft2d batchFft2d(Operand<?> input) {
    return BatchFft2d.create(scope, input);
  }

  /**
   *
   * @param input
   * @return a new instance of BatchFft3d
   */
  public BatchFft3d batchFft3d(Operand<?> input) {
    return BatchFft3d.create(scope, input);
  }

  /**
   *
   * @param input
   * @return a new instance of BatchIfft
   */
  public BatchIfft batchIfft(Operand<?> input) {
    return BatchIfft.create(scope, input);
  }

  /**
   *
   * @param input
   * @return a new instance of BatchIfft2d
   */
  public BatchIfft2d batchIfft2d(Operand<?> input) {
    return BatchIfft2d.create(scope, input);
  }

  /**
   *
   * @param input
   * @return a new instance of BatchIfft3d
   */
  public BatchIfft3d batchIfft3d(Operand<?> input) {
    return BatchIfft3d.create(scope, input);
  }

  /**
   * Fast Fourier transform.
   *  <p>
   *  Computes the 1-dimensional discrete Fourier transform over the inner-most
   *  dimension of `input`.
   *
   * @param <T> data type for {@code output()} output
   * @param input A complex tensor.
   * @return a new instance of Fft
   */
  public <T extends TType> Fft<T> fft(Operand<T> input) {
    return Fft.create(scope, input);
  }

  /**
   * 2D fast Fourier transform.
   *  <p>
   *  Computes the 2-dimensional discrete Fourier transform over the inner-most
   *  2 dimensions of `input`.
   *
   * @param <T> data type for {@code output()} output
   * @param input A complex tensor.
   * @return a new instance of Fft2d
   */
  public <T extends TType> Fft2d<T> fft2d(Operand<T> input) {
    return Fft2d.create(scope, input);
  }

  /**
   * 3D fast Fourier transform.
   *  <p>
   *  Computes the 3-dimensional discrete Fourier transform over the inner-most 3
   *  dimensions of `input`.
   *
   * @param <T> data type for {@code output()} output
   * @param input A complex tensor.
   * @return a new instance of Fft3d
   */
  public <T extends TType> Fft3d<T> fft3d(Operand<T> input) {
    return Fft3d.create(scope, input);
  }

  /**
   * Inverse fast Fourier transform.
   *  <p>
   *  Computes the inverse 1-dimensional discrete Fourier transform over the
   *  inner-most dimension of `input`.
   *
   * @param <T> data type for {@code output()} output
   * @param input A complex tensor.
   * @return a new instance of Ifft
   */
  public <T extends TType> Ifft<T> ifft(Operand<T> input) {
    return Ifft.create(scope, input);
  }

  /**
   * Inverse 2D fast Fourier transform.
   *  <p>
   *  Computes the inverse 2-dimensional discrete Fourier transform over the
   *  inner-most 2 dimensions of `input`.
   *
   * @param <T> data type for {@code output()} output
   * @param input A complex tensor.
   * @return a new instance of Ifft2d
   */
  public <T extends TType> Ifft2d<T> ifft2d(Operand<T> input) {
    return Ifft2d.create(scope, input);
  }

  /**
   * Inverse 3D fast Fourier transform.
   *  <p>
   *  Computes the inverse 3-dimensional discrete Fourier transform over the
   *  inner-most 3 dimensions of `input`.
   *
   * @param <T> data type for {@code output()} output
   * @param input A complex tensor.
   * @return a new instance of Ifft3d
   */
  public <T extends TType> Ifft3d<T> ifft3d(Operand<T> input) {
    return Ifft3d.create(scope, input);
  }

  /**
   * Inverse real-valued fast Fourier transform.
   *  <p>
   *  Computes the inverse 1-dimensional discrete Fourier transform of a real-valued
   *  signal over the inner-most dimension of `input`.
   *  <p>
   *  The inner-most dimension of `input` is assumed to be the result of `RFFT`: the
   *  `fft_length / 2 + 1` unique components of the DFT of a real-valued signal. If
   *  `fft_length` is not provided, it is computed from the size of the inner-most
   *  dimension of `input` (`fft_length = 2 * (inner - 1)`). If the FFT length used to
   *  compute `input` is odd, it should be provided since it cannot be inferred
   *  properly.
   *  <p>
   *  Along the axis `signal.Irfft` is computed on, if `fft_length / 2 + 1` is smaller
   *  than the corresponding dimension of `input`, the dimension is cropped. If it is
   *  larger, the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @return a new instance of Irfft
   */
  public <T extends TType> Irfft<TFloat32> irfft(Operand<T> input, Operand<TInt32> fftLength) {
    return Irfft.create(scope, input, fftLength);
  }

  /**
   * Inverse real-valued fast Fourier transform.
   *  <p>
   *  Computes the inverse 1-dimensional discrete Fourier transform of a real-valued
   *  signal over the inner-most dimension of `input`.
   *  <p>
   *  The inner-most dimension of `input` is assumed to be the result of `RFFT`: the
   *  `fft_length / 2 + 1` unique components of the DFT of a real-valued signal. If
   *  `fft_length` is not provided, it is computed from the size of the inner-most
   *  dimension of `input` (`fft_length = 2 * (inner - 1)`). If the FFT length used to
   *  compute `input` is odd, it should be provided since it cannot be inferred
   *  properly.
   *  <p>
   *  Along the axis `signal.Irfft` is computed on, if `fft_length / 2 + 1` is smaller
   *  than the corresponding dimension of `input`, the dimension is cropped. If it is
   *  larger, the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @param Treal
   * @return a new instance of Irfft
   */
  public <U extends TNumber, T extends TType> Irfft<U> irfft(Operand<T> input,
      Operand<TInt32> fftLength, Class<U> Treal) {
    return Irfft.create(scope, input, fftLength, Treal);
  }

  /**
   * Inverse 2D real-valued fast Fourier transform.
   *  <p>
   *  Computes the inverse 2-dimensional discrete Fourier transform of a real-valued
   *  signal over the inner-most 2 dimensions of `input`.
   *  <p>
   *  The inner-most 2 dimensions of `input` are assumed to be the result of `RFFT2D`:
   *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
   *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
   *  from the size of the inner-most 2 dimensions of `input`. If the FFT length used
   *  to compute `input` is odd, it should be provided since it cannot be inferred
   *  properly.
   *  <p>
   *  Along each axis `signal.Irfft2d` is computed on, if `fft_length` (or
   *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
   *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
   *  the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @return a new instance of Irfft2d
   */
  public <T extends TType> Irfft2d<TFloat32> irfft2d(Operand<T> input, Operand<TInt32> fftLength) {
    return Irfft2d.create(scope, input, fftLength);
  }

  /**
   * Inverse 2D real-valued fast Fourier transform.
   *  <p>
   *  Computes the inverse 2-dimensional discrete Fourier transform of a real-valued
   *  signal over the inner-most 2 dimensions of `input`.
   *  <p>
   *  The inner-most 2 dimensions of `input` are assumed to be the result of `RFFT2D`:
   *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
   *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
   *  from the size of the inner-most 2 dimensions of `input`. If the FFT length used
   *  to compute `input` is odd, it should be provided since it cannot be inferred
   *  properly.
   *  <p>
   *  Along each axis `signal.Irfft2d` is computed on, if `fft_length` (or
   *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
   *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
   *  the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @param Treal
   * @return a new instance of Irfft2d
   */
  public <U extends TNumber, T extends TType> Irfft2d<U> irfft2d(Operand<T> input,
      Operand<TInt32> fftLength, Class<U> Treal) {
    return Irfft2d.create(scope, input, fftLength, Treal);
  }

  /**
   * Inverse 3D real-valued fast Fourier transform.
   *  <p>
   *  Computes the inverse 3-dimensional discrete Fourier transform of a real-valued
   *  signal over the inner-most 3 dimensions of `input`.
   *  <p>
   *  The inner-most 3 dimensions of `input` are assumed to be the result of `RFFT3D`:
   *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
   *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
   *  from the size of the inner-most 3 dimensions of `input`. If the FFT length used
   *  to compute `input` is odd, it should be provided since it cannot be inferred
   *  properly.
   *  <p>
   *  Along each axis `signal.Irfft3d` is computed on, if `fft_length` (or
   *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
   *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
   *  the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [3]. The FFT length for each dimension.
   * @return a new instance of Irfft3d
   */
  public <T extends TType> Irfft3d<TFloat32> irfft3d(Operand<T> input, Operand<TInt32> fftLength) {
    return Irfft3d.create(scope, input, fftLength);
  }

  /**
   * Inverse 3D real-valued fast Fourier transform.
   *  <p>
   *  Computes the inverse 3-dimensional discrete Fourier transform of a real-valued
   *  signal over the inner-most 3 dimensions of `input`.
   *  <p>
   *  The inner-most 3 dimensions of `input` are assumed to be the result of `RFFT3D`:
   *  The inner-most dimension contains the `fft_length / 2 + 1` unique components of
   *  the DFT of a real-valued signal. If `fft_length` is not provided, it is computed
   *  from the size of the inner-most 3 dimensions of `input`. If the FFT length used
   *  to compute `input` is odd, it should be provided since it cannot be inferred
   *  properly.
   *  <p>
   *  Along each axis `signal.Irfft3d` is computed on, if `fft_length` (or
   *  `fft_length / 2 + 1` for the inner-most dimension) is smaller than the
   *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
   *  the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [3]. The FFT length for each dimension.
   * @param Treal
   * @return a new instance of Irfft3d
   */
  public <U extends TNumber, T extends TType> Irfft3d<U> irfft3d(Operand<T> input,
      Operand<TInt32> fftLength, Class<U> Treal) {
    return Irfft3d.create(scope, input, fftLength, Treal);
  }

  /**
   * Real-valued fast Fourier transform.
   *  <p>
   *  Computes the 1-dimensional discrete Fourier transform of a real-valued signal
   *  over the inner-most dimension of `input`.
   *  <p>
   *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft` only returns the
   *  `fft_length / 2 + 1` unique components of the FFT: the zero-frequency term,
   *  followed by the `fft_length / 2` positive-frequency terms.
   *  <p>
   *  Along the axis `signal.Rfft` is computed on, if `fft_length` is smaller than the
   *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
   *  the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @param Tcomplex
   * @return a new instance of Rfft
   */
  public <U extends TType, T extends TNumber> Rfft<U> rfft(Operand<T> input,
      Operand<TInt32> fftLength, Class<U> Tcomplex) {
    return Rfft.create(scope, input, fftLength, Tcomplex);
  }

  /**
   * 2D real-valued fast Fourier transform.
   *  <p>
   *  Computes the 2-dimensional discrete Fourier transform of a real-valued signal
   *  over the inner-most 2 dimensions of `input`.
   *  <p>
   *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft2d` only returns the
   *  `fft_length / 2 + 1` unique components of the FFT for the inner-most dimension
   *  of `output`: the zero-frequency term, followed by the `fft_length / 2`
   *  positive-frequency terms.
   *  <p>
   *  Along each axis `signal.Rfft2d` is computed on, if `fft_length` is smaller than the
   *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
   *  the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @param Tcomplex
   * @return a new instance of Rfft2d
   */
  public <U extends TType, T extends TNumber> Rfft2d<U> rfft2d(Operand<T> input,
      Operand<TInt32> fftLength, Class<U> Tcomplex) {
    return Rfft2d.create(scope, input, fftLength, Tcomplex);
  }

  /**
   * 3D real-valued fast Fourier transform.
   *  <p>
   *  Computes the 3-dimensional discrete Fourier transform of a real-valued signal
   *  over the inner-most 3 dimensions of `input`.
   *  <p>
   *  Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft3d` only returns the
   *  `fft_length / 2 + 1` unique components of the FFT for the inner-most dimension
   *  of `output`: the zero-frequency term, followed by the `fft_length / 2`
   *  positive-frequency terms.
   *  <p>
   *  Along each axis `signal.Rfft3d` is computed on, if `fft_length` is smaller than the
   *  corresponding dimension of `input`, the dimension is cropped. If it is larger,
   *  the dimension is padded with zeros.
   *
   * @param <U> data type for {@code output()} output
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [3]. The FFT length for each dimension.
   * @param Tcomplex
   * @return a new instance of Rfft3d
   */
  public <U extends TType, T extends TNumber> Rfft3d<U> rfft3d(Operand<T> input,
      Operand<TInt32> fftLength, Class<U> Tcomplex) {
    return Rfft3d.create(scope, input, fftLength, Tcomplex);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
