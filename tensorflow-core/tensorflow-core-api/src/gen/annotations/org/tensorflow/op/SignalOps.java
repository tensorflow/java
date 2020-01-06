package org.tensorflow.op;

import org.tensorflow.DataType;
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
import org.tensorflow.types.TFloat;
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

  SignalOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link Ifft3d} operation
   *
   * @param input A complex tensor.
   * @return a new instance of Ifft3d
   * @see org.tensorflow.op.signal.Ifft3d
   */
  public <T extends TType> Ifft3d<T> ifft3d(Operand<T> input) {
    return Ifft3d.create(scope, input);
  }

  /**
   * Builds an {@link Rfft3d} operation
   *
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [3]. The FFT length for each dimension.
   * @param Tcomplex 
   * @return a new instance of Rfft3d
   * @see org.tensorflow.op.signal.Rfft3d
   */
  public <U extends TType, T extends TNumber> Rfft3d<U> rfft3d(Operand<T> input,
      Operand<TInt32> fftLength, DataType<U> Tcomplex) {
    return Rfft3d.create(scope, input, fftLength, Tcomplex);
  }

  /**
   * Builds an {@link Rfft2d} operation
   *
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @param Tcomplex 
   * @return a new instance of Rfft2d
   * @see org.tensorflow.op.signal.Rfft2d
   */
  public <U extends TType, T extends TNumber> Rfft2d<U> rfft2d(Operand<T> input,
      Operand<TInt32> fftLength, DataType<U> Tcomplex) {
    return Rfft2d.create(scope, input, fftLength, Tcomplex);
  }

  /**
   * Builds an {@link BatchIfft} operation
   *
   * @param input 
   * @return a new instance of BatchIfft
   * @see org.tensorflow.op.signal.BatchIfft
   */
  public BatchIfft batchIfft(Operand<?> input) {
    return BatchIfft.create(scope, input);
  }

  /**
   * Builds an {@link Ifft2d} operation
   *
   * @param input A complex tensor.
   * @return a new instance of Ifft2d
   * @see org.tensorflow.op.signal.Ifft2d
   */
  public <T extends TType> Ifft2d<T> ifft2d(Operand<T> input) {
    return Ifft2d.create(scope, input);
  }

  /**
   * Builds an {@link Irfft3d} operation
   *
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [3]. The FFT length for each dimension.
   * @param Treal 
   * @return a new instance of Irfft3d
   * @see org.tensorflow.op.signal.Irfft3d
   */
  public <U extends TNumber, T extends TType> Irfft3d<U> irfft3d(Operand<T> input,
      Operand<TInt32> fftLength, DataType<U> Treal) {
    return Irfft3d.create(scope, input, fftLength, Treal);
  }

  /**
   * Builds an {@link Irfft2d} operation
   *
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @return a new instance of Irfft2d
   * @see org.tensorflow.op.signal.Irfft2d
   */
  public <T extends TType> Irfft2d<TFloat> irfft2d(Operand<T> input, Operand<TInt32> fftLength) {
    return Irfft2d.create(scope, input, fftLength);
  }

  /**
   * Builds an {@link BatchIfft3d} operation
   *
   * @param input 
   * @return a new instance of BatchIfft3d
   * @see org.tensorflow.op.signal.BatchIfft3d
   */
  public BatchIfft3d batchIfft3d(Operand<?> input) {
    return BatchIfft3d.create(scope, input);
  }

  /**
   * Builds an {@link BatchFft3d} operation
   *
   * @param input 
   * @return a new instance of BatchFft3d
   * @see org.tensorflow.op.signal.BatchFft3d
   */
  public BatchFft3d batchFft3d(Operand<?> input) {
    return BatchFft3d.create(scope, input);
  }

  /**
   * Builds an {@link Irfft3d} operation
   *
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [3]. The FFT length for each dimension.
   * @return a new instance of Irfft3d
   * @see org.tensorflow.op.signal.Irfft3d
   */
  public <T extends TType> Irfft3d<TFloat> irfft3d(Operand<T> input, Operand<TInt32> fftLength) {
    return Irfft3d.create(scope, input, fftLength);
  }

  /**
   * Builds an {@link Fft} operation
   *
   * @param input A complex tensor.
   * @return a new instance of Fft
   * @see org.tensorflow.op.signal.Fft
   */
  public <T extends TType> Fft<T> fft(Operand<T> input) {
    return Fft.create(scope, input);
  }

  /**
   * Builds an {@link BatchFft2d} operation
   *
   * @param input 
   * @return a new instance of BatchFft2d
   * @see org.tensorflow.op.signal.BatchFft2d
   */
  public BatchFft2d batchFft2d(Operand<?> input) {
    return BatchFft2d.create(scope, input);
  }

  /**
   * Builds an {@link Rfft} operation
   *
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @param Tcomplex 
   * @return a new instance of Rfft
   * @see org.tensorflow.op.signal.Rfft
   */
  public <U extends TType, T extends TNumber> Rfft<U> rfft(Operand<T> input,
      Operand<TInt32> fftLength, DataType<U> Tcomplex) {
    return Rfft.create(scope, input, fftLength, Tcomplex);
  }

  /**
   * Builds an {@link Ifft} operation
   *
   * @param input A complex tensor.
   * @return a new instance of Ifft
   * @see org.tensorflow.op.signal.Ifft
   */
  public <T extends TType> Ifft<T> ifft(Operand<T> input) {
    return Ifft.create(scope, input);
  }

  /**
   * Builds an {@link Irfft2d} operation
   *
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @param Treal 
   * @return a new instance of Irfft2d
   * @see org.tensorflow.op.signal.Irfft2d
   */
  public <U extends TNumber, T extends TType> Irfft2d<U> irfft2d(Operand<T> input,
      Operand<TInt32> fftLength, DataType<U> Treal) {
    return Irfft2d.create(scope, input, fftLength, Treal);
  }

  /**
   * Builds an {@link Fft3d} operation
   *
   * @param input A complex tensor.
   * @return a new instance of Fft3d
   * @see org.tensorflow.op.signal.Fft3d
   */
  public <T extends TType> Fft3d<T> fft3d(Operand<T> input) {
    return Fft3d.create(scope, input);
  }

  /**
   * Builds an {@link Irfft} operation
   *
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @param Treal 
   * @return a new instance of Irfft
   * @see org.tensorflow.op.signal.Irfft
   */
  public <U extends TNumber, T extends TType> Irfft<U> irfft(Operand<T> input,
      Operand<TInt32> fftLength, DataType<U> Treal) {
    return Irfft.create(scope, input, fftLength, Treal);
  }

  /**
   * Builds an {@link Fft2d} operation
   *
   * @param input A complex tensor.
   * @return a new instance of Fft2d
   * @see org.tensorflow.op.signal.Fft2d
   */
  public <T extends TType> Fft2d<T> fft2d(Operand<T> input) {
    return Fft2d.create(scope, input);
  }

  /**
   * Builds an {@link Irfft} operation
   *
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @return a new instance of Irfft
   * @see org.tensorflow.op.signal.Irfft
   */
  public <T extends TType> Irfft<TFloat> irfft(Operand<T> input, Operand<TInt32> fftLength) {
    return Irfft.create(scope, input, fftLength);
  }

  /**
   * Builds an {@link BatchFft} operation
   *
   * @param input 
   * @return a new instance of BatchFft
   * @see org.tensorflow.op.signal.BatchFft
   */
  public BatchFft batchFft(Operand<?> input) {
    return BatchFft.create(scope, input);
  }

  /**
   * Builds an {@link BatchIfft2d} operation
   *
   * @param input 
   * @return a new instance of BatchIfft2d
   * @see org.tensorflow.op.signal.BatchIfft2d
   */
  public BatchIfft2d batchIfft2d(Operand<?> input) {
    return BatchIfft2d.create(scope, input);
  }
}
