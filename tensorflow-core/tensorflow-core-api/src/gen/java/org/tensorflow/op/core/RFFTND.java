/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.core;

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
 * ND fast real Fourier transform.
 * Computes the n-dimensional real discrete Fourier transform over designated
 * dimensions of {@code input}. The designated dimensions of {@code input} are assumed to be
 * the result of {@code RFFTND}. The length of the last axis transformed will be
 * fft_length[-1]//2+1.
 * <p>If fft_length[i]&lt;shape(input)[i], the input is cropped. If
 * fft_length[i]&gt;shape(input)[i], the input is padded with zeros. If fft_length
 * is not given, the default shape(input) is used.
 * <p>Axes mean the dimensions to perform the transform on. Default is to perform on
 * all axes.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = RFFTND.OP_NAME,
    inputsClass = RFFTND.Inputs.class
)
@Operator
public final class RFFTND<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RFFTND";

  private Output<U> output;

  public RFFTND(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RFFTND operation.
   *
   * @param scope current scope
   * @param input A complex tensor.
   * @param fftLength An int32 tensor. The FFT length for each dimension.
   * @param axes An int32 tensor with a same shape as fft_length. Axes to perform the transform.
   * @param Tcomplex The value of the Tcomplex attribute
   * @param <U> data type for {@code RFFTND} output and operands
   * @return a new instance of RFFTND
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> RFFTND<U> create(Scope scope, Operand<? extends TNumber> input,
      Operand<TInt32> fftLength, Operand<TInt32> axes, Class<U> Tcomplex) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RFFTND");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(fftLength.asOutput());
    opBuilder.addInput(axes.asOutput());
    opBuilder.setAttr("Tcomplex", Operands.toDataType(Tcomplex));
    return new RFFTND<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A complex tensor of the same shape as {@code input}. The designated
   * dimensions of {@code input} are replaced with their real Fourier transforms.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.fft.rfftn.
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
      outputsClass = RFFTND.class
  )
  public static class Inputs extends RawOpInputs<RFFTND<?>> {
    /**
     * A complex tensor.
     */
    public final Operand<? extends TNumber> input;

    /**
     * An int32 tensor. The FFT length for each dimension.
     */
    public final Operand<TInt32> fftLength;

    /**
     * An int32 tensor with a same shape as fft_length. Axes to perform the transform.
     */
    public final Operand<TInt32> axes;

    /**
     * The Treal attribute
     */
    public final DataType Treal;

    /**
     * The Tcomplex attribute
     */
    public final DataType Tcomplex;

    public Inputs(GraphOperation op) {
      super(new RFFTND<>(op), op, Arrays.asList("Treal", "Tcomplex"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      fftLength = (Operand<TInt32>) op.input(inputIndex++);
      axes = (Operand<TInt32>) op.input(inputIndex++);
      Treal = op.attributes().getAttrType("Treal");
      Tcomplex = op.attributes().getAttrType("Tcomplex");
    }
  }
}
