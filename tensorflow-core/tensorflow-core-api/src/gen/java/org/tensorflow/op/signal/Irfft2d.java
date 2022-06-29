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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Inverse 2D real-valued fast Fourier transform.
 * Computes the inverse 2-dimensional discrete Fourier transform of a real-valued
 * signal over the inner-most 2 dimensions of {@code input}.
 * <p>The inner-most 2 dimensions of {@code input} are assumed to be the result of {@code RFFT2D}:
 * The inner-most dimension contains the {@code fft_length / 2 + 1} unique components of
 * the DFT of a real-valued signal. If {@code fft_length} is not provided, it is computed
 * from the size of the inner-most 2 dimensions of {@code input}. If the FFT length used
 * to compute {@code input} is odd, it should be provided since it cannot be inferred
 * properly.
 * <p>Along each axis {@code signal.Irfft2d} is computed on, if {@code fft_length} (or
 * {@code fft_length / 2 + 1} for the inner-most dimension) is smaller than the
 * corresponding dimension of {@code input}, the dimension is cropped. If it is larger,
 * the dimension is padded with zeros.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = Irfft2d.OP_NAME,
    inputsClass = Irfft2d.Inputs.class
)
@Operator(
    group = "signal"
)
public final class Irfft2d<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IRFFT2D";

  private Output<U> output;

  public Irfft2d(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IRFFT2D operation.
   *
   * @param scope current scope
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @param Treal The value of the Treal attribute
   * @param <U> data type for {@code IRFFT2D} output and operands
   * @return a new instance of Irfft2d
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Irfft2d<U> create(Scope scope, Operand<? extends TType> input,
      Operand<TInt32> fftLength, Class<U> Treal) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Irfft2d");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(fftLength.asOutput());
    opBuilder.setAttr("Treal", Operands.toDataType(Treal));
    return new Irfft2d<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new IRFFT2D operation, with the default output types.
   *
   * @param scope current scope
   * @param input A complex tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @return a new instance of Irfft2d, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static Irfft2d<TFloat32> create(Scope scope, Operand<? extends TType> input,
      Operand<TInt32> fftLength) {
    return create(scope, input, fftLength, TFloat32.class);
  }

  /**
   * Gets output.
   * A float32 tensor of the same rank as {@code input}. The inner-most 2
   * dimensions of {@code input} are replaced with the {@code fft_length} samples of their
   * inverse 2D Fourier transform.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.fft.irfft2
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

  @OpInputsMetadata(
      outputsClass = Irfft2d.class
  )
  public static class Inputs extends RawOpInputs<Irfft2d<?>> {
    /**
     * A complex tensor.
     */
    public final Operand<? extends TType> input;

    /**
     * An int32 tensor of shape [2]. The FFT length for each dimension.
     */
    public final Operand<TInt32> fftLength;

    /**
     * The Treal attribute
     */
    public final DataType Treal;

    /**
     * The Tcomplex attribute
     */
    public final DataType Tcomplex;

    public Inputs(GraphOperation op) {
      super(new Irfft2d<>(op), op, Arrays.asList("Treal", "Tcomplex"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      fftLength = (Operand<TInt32>) op.input(inputIndex++);
      Treal = op.attributes().getAttrType("Treal");
      Tcomplex = op.attributes().getAttrType("Tcomplex");
    }
  }
}
