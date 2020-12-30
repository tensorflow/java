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
 * 3D real-valued fast Fourier transform.
 * <p>
 * Computes the 3-dimensional discrete Fourier transform of a real-valued signal
 * over the inner-most 3 dimensions of `input`.
 * <p>
 * Since the DFT of a real signal is Hermitian-symmetric, `signal.Rfft3d` only returns the
 * `fft_length / 2 + 1` unique components of the FFT for the inner-most dimension
 * of `output`: the zero-frequency term, followed by the `fft_length / 2`
 * positive-frequency terms.
 * <p>
 * Along each axis `signal.Rfft3d` is computed on, if `fft_length` is smaller than the
 * corresponding dimension of `input`, the dimension is cropped. If it is larger,
 * the dimension is padded with zeros.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator(group = "signal")
public final class Rfft3d<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new Rfft3d operation.
   * 
   * @param scope current scope
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [3]. The FFT length for each dimension.
   * @param Tcomplex 
   * @return a new instance of Rfft3d
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> Rfft3d<U> create(Scope scope, Operand<T> input, Operand<TInt32> fftLength, Class<U> Tcomplex) {
    OperationBuilder opBuilder = scope.env().opBuilder("RFFT3D", scope.makeOpName("Rfft3d"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(fftLength.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Tcomplex", Operands.toDataType(Tcomplex));
    return new Rfft3d<U>(opBuilder.build());
  }
  
  /**
   * A complex64 tensor of the same rank as `input`. The inner-most 3
   *   dimensions of `input` are replaced with the their 3D Fourier transform. The
   *   inner-most dimension contains `fft_length / 2 + 1` unique frequency
   *   components.
   * <p>
   * @compatibility(numpy)
   * Equivalent to np.fft.rfftn with 3 dimensions.
   * @end_compatibility
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RFFT3D";
  
  private Output<U> output;
  
  private Rfft3d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
