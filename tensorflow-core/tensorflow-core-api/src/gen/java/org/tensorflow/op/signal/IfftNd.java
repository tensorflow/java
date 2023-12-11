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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * ND inverse fast Fourier transform.
 * Computes the n-dimensional inverse discrete Fourier transform over designated
 * dimensions of {@code input}. The designated dimensions of {@code input} are assumed to be
 * the result of {@code signal.IfftNd}.
 * <p>If fft_length[i]&lt;shape(input)[i], the input is cropped. If
 * fft_length[i]&gt;shape(input)[i], the input is padded with zeros. If fft_length
 * is not given, the default shape(input) is used.
 * <p>Axes mean the dimensions to perform the transform on. Default is to perform on
 * all axes.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = IfftNd.OP_NAME,
    inputsClass = IfftNd.Inputs.class
)
@Operator(
    group = "signal"
)
public final class IfftNd<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IFFTND";

  private Output<T> output;

  public IfftNd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IFFTND operation.
   *
   * @param scope current scope
   * @param input A complex tensor.
   * @param fftLength An int32 tensor. The FFT length for each dimension.
   * @param axes An int32 tensor with a same shape as fft_length. Axes to perform the transform.
   * @param <T> data type for {@code IFFTND} output and operands
   * @return a new instance of IfftNd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> IfftNd<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> fftLength, Operand<TInt32> axes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IfftNd");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(fftLength.asOutput());
    opBuilder.addInput(axes.asOutput());
    return new IfftNd<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A complex tensor of the same shape as {@code input}. The designated dimensions of
   * {@code input} are replaced with their inverse Fourier
   * transforms.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.fft.fftn.
   * <br>{@literal @}end_compatibility
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = IfftNd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<IfftNd<T>> {
    /**
     * A complex tensor.
     */
    public final Operand<T> input;

    /**
     * An int32 tensor. The FFT length for each dimension.
     */
    public final Operand<TInt32> fftLength;

    /**
     * An int32 tensor with a same shape as fft_length. Axes to perform the transform.
     */
    public final Operand<TInt32> axes;

    /**
     * The Tcomplex attribute
     */
    public final DataType Tcomplex;

    public Inputs(GraphOperation op) {
      super(new IfftNd<>(op), op, Arrays.asList("Tcomplex"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      fftLength = (Operand<TInt32>) op.input(inputIndex++);
      axes = (Operand<TInt32>) op.input(inputIndex++);
      Tcomplex = op.attributes().getAttrType("Tcomplex");
    }
  }
}
