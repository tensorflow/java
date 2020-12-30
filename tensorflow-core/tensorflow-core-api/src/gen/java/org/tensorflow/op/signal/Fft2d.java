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
 * 2D fast Fourier transform.
 * <p>
 * Computes the 2-dimensional discrete Fourier transform over the inner-most
 * 2 dimensions of `input`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "signal")
public final class Fft2d<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Fft2d operation.
   * 
   * @param scope current scope
   * @param input A complex tensor.
   * @return a new instance of Fft2d
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Fft2d<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("FFT2D", scope.makeOpName("Fft2d"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Fft2d<T>(opBuilder.build());
  }
  
  /**
   * A complex tensor of the same shape as `input`. The inner-most 2
   *   dimensions of `input` are replaced with their 2D Fourier transform.
   * <p>
   * @compatibility(numpy)
   * Equivalent to np.fft.fft2
   * @end_compatibility
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "FFT2D";
  
  private Output<T> output;
  
  private Fft2d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
