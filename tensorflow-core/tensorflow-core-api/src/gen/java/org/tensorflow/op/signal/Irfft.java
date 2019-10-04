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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Inverse real-valued fast Fourier transform.
 * <p>
 * Computes the inverse 1-dimensional discrete Fourier transform of a real-valued
 * signal over the inner-most dimension of `input`.
 * <p>
 * The inner-most dimension of `input` is assumed to be the result of `RFFT`: the
 * `fft_length / 2 + 1` unique components of the DFT of a real-valued signal. If
 * `fft_length` is not provided, it is computed from the size of the inner-most
 * dimension of `input` (`fft_length = 2 * (inner - 1)`). If the FFT length used to
 * compute `input` is odd, it should be provided since it cannot be inferred
 * properly.
 * <p>
 * Along the axis `signal.Irfft` is computed on, if `fft_length / 2 + 1` is smaller
 * than the corresponding dimension of `input`, the dimension is cropped. If it is
 * larger, the dimension is padded with zeros.
 */
@Operator(group = "signal")
public final class Irfft extends PrimitiveOp implements Operand<Float> {
  
  /**
   * Factory method to create a class wrapping a new Irfft operation.
   * 
   * @param scope current scope
   * @param input A complex64 tensor.
   * @param fftLength An int32 tensor of shape [1]. The FFT length.
   * @return a new instance of Irfft
   */
  public static Irfft create(Scope scope, Operand<?> input, Operand<Integer> fftLength) {
    OperationBuilder opBuilder = scope.env().opBuilder("IRFFT", scope.makeOpName("Irfft"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(fftLength.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new Irfft(opBuilder.build());
  }
  
  /**
   * A float32 tensor of the same rank as `input`. The inner-most
   *   dimension of `input` is replaced with the `fft_length` samples of its inverse
   *   1D Fourier transform.
   * <p>
   * @compatibility(numpy)
   * Equivalent to np.fft.irfft
   * @end_compatibility
   */
  public Output<Float> output() {
    return output;
  }
  
  @Override
  public Output<Float> asOutput() {
    return output;
  }
  
  private Output<Float> output;
  
  private Irfft(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
