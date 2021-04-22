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

package org.tensorflow.op.signal;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Inverse real-valued fast Fourier transform.
 * Computes the inverse 1-dimensional discrete Fourier transform of a real-valued
 * signal over the inner-most dimension of {@code input}.
 * <p>The inner-most dimension of {@code input} is assumed to be the result of {@code RFFT}: the
 * {@code fft_length / 2 + 1} unique components of the DFT of a real-valued signal. If
 * {@code fft_length} is not provided, it is computed from the size of the inner-most
 * dimension of {@code input} ({@code fft_length = 2 * (inner - 1)}). If the FFT length used to
 * compute {@code input} is odd, it should be provided since it cannot be inferred
 * properly.
 * <p>Along the axis {@code signal.Irfft} is computed on, if {@code fft_length / 2 + 1} is smaller
 * than the corresponding dimension of {@code input}, the dimension is cropped. If it is
 * larger, the dimension is padded with zeros.
 *
 * @param <U> data type for {@code output} output
 */
@Operator(
    group = "signal"
)
public final class Irfft<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IRFFT";

  private Output<U> output;

  private Irfft(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IRFFT operation.
   *
   * @param scope current scope
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @param Treal the value of the Treal property
   * @param <U> data type for {@code IRFFT} output and operands
   * @return a new instance of Irfft
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Irfft<U> create(Scope scope, Operand<? extends TType> input,
      Operand<TInt32> fftLength, Class<U> Treal) {
    OperationBuilder opBuilder = scope.env().opBuilder("IRFFT", scope.makeOpName("Irfft"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(fftLength.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Treal", Operands.toDataType(Treal));
    return new Irfft<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new IRFFT operation, with the default output types.
   *
   * @param scope current scope
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @return a new instance of Irfft, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static Irfft<TFloat32> create(Scope scope, Operand<? extends TType> input,
      Operand<TInt32> fftLength) {
    return create(scope, input, fftLength, TFloat32.class);
  }

  /**
   * Gets output.
   * A float32 tensor of the same rank as {@code input}. The inner-most
   * dimension of {@code input} is replaced with the {@code fft_length} samples of its inverse
   * 1D Fourier transform.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.fft.irfft
   * <br>{@literal @}end_compatibility
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }
}
