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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * 3D fast Fourier transform.
 * Computes the 3-dimensional discrete Fourier transform over the inner-most 3
 * dimensions of {@code input}.
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "signal"
)
public final class Fft3d<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FFT3D";

  private Output<T> output;

  private Fft3d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FFT3D operation.
   *
   * @param scope current scope
   * @param input A complex tensor.
   * @param <T> data type for {@code FFT3D} output and operands
   * @return a new instance of Fft3d
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Fft3d<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("FFT3D", scope.makeOpName("Fft3d"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Fft3d<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A complex tensor of the same shape as {@code input}. The inner-most 3
   * dimensions of {@code input} are replaced with their 3D Fourier transform.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.fft.fftn with 3 dimensions.
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
}
