/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * 2D real-valued fast Fourier transform.
 * Computes the 2-dimensional discrete Fourier transform of a real-valued signal
 * over the inner-most 2 dimensions of {@code input}.
 * <p>Since the DFT of a real signal is Hermitian-symmetric, {@code signal.Rfft2d} only returns the
 * {@code fft_length / 2 + 1} unique components of the FFT for the inner-most dimension
 * of {@code output}: the zero-frequency term, followed by the {@code fft_length / 2}
 * positive-frequency terms.
 * <p>Along each axis {@code signal.Rfft2d} is computed on, if {@code fft_length} is smaller than the
 * corresponding dimension of {@code input}, the dimension is cropped. If it is larger,
 * the dimension is padded with zeros.
 */
@OpMetadata(
    opType = Rfft2d.OP_NAME,
    inputsClass = Rfft2d.Inputs.class
)
@Operator(
    group = "signal"
)
public final class Rfft2d<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RFFT2D";

  private Output<U> output;

  public Rfft2d(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RFFT2D operation.
   *
   * @param scope current scope
   * @param input A float32 tensor.
   * @param fftLength An int32 tensor of shape [2]. The FFT length for each dimension.
   * @param Tcomplex The value of the Tcomplex attribute
   * @param <U> data type for {@code RFFT2D} output and operands
   * @return a new instance of Rfft2d
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> Rfft2d<U> create(Scope scope, Operand<? extends TNumber> input,
      Operand<TInt32> fftLength, Class<U> Tcomplex) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Rfft2d");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(fftLength.asOutput());
    opBuilder.setAttr("Tcomplex", Operands.toDataType(Tcomplex));
    return new Rfft2d<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A complex64 tensor of the same rank as {@code input}. The inner-most 2
   * dimensions of {@code input} are replaced with their 2D Fourier transform. The
   * inner-most dimension contains {@code fft_length / 2 + 1} unique frequency
   * components.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.fft.rfft2
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
      outputsClass = Rfft2d.class
  )
  public static class Inputs extends RawOpInputs<Rfft2d<?>> {
    /**
     * A float32 tensor.
     */
    public final Operand<? extends TNumber> input;

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
      super(new Rfft2d<>(op), op, Arrays.asList("Treal", "Tcomplex"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      fftLength = (Operand<TInt32>) op.input(inputIndex++);
      Treal = op.attributes().getAttrType("Treal");
      Tcomplex = op.attributes().getAttrType("Tcomplex");
    }
  }
}
