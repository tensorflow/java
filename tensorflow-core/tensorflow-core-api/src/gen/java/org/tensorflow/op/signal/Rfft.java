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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Real-valued fast Fourier transform.
 * Computes the 1-dimensional discrete Fourier transform of a real-valued signal
 * over the inner-most dimension of {@code input}.
 * <p>Since the DFT of a real signal is Hermitian-symmetric, {@code signal.Rfft} only returns the
 * {@code fft_length / 2 + 1} unique components of the FFT: the zero-frequency term,
 * followed by the {@code fft_length / 2} positive-frequency terms.
 * <p>Along the axis {@code signal.Rfft} is computed on, if {@code fft_length} is smaller than the
 * corresponding dimension of {@code input}, the dimension is cropped. If it is larger,
 * the dimension is padded with zeros.
 *
 * @param <U> data type for {@code output} output
 */
@Operator(
    group = "signal"
)
public final class Rfft<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RFFT";

  private Output<U> output;

  private Rfft(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RFFT operation.
   *
   * @param scope current scope
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @param Tcomplex the value of the Tcomplex property
   * @param <U> data type for {@code RFFT} output and operands
   * @return a new instance of Rfft
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> Rfft<U> create(Scope scope, Operand<? extends TNumber> input,
      Operand<TInt32> fftLength, Class<U> Tcomplex) {
    OperationBuilder opBuilder = scope.env().opBuilder("RFFT", scope.makeOpName("Rfft"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(fftLength.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Tcomplex", Operands.toDataType(Tcomplex));
    return new Rfft<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A complex64 tensor of the same rank as {@code input}. The inner-most
   * dimension of {@code input} is replaced with the {@code fft_length / 2 + 1} unique
   * frequency components of its 1D Fourier transform.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.fft.rfft
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
