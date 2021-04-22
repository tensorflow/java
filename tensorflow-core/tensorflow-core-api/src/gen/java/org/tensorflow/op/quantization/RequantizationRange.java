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

package org.tensorflow.op.quantization;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes a range that covers the actual values present in a quantized tensor.
 * Given a quantized tensor described by {@code (input, input_min, input_max)}, outputs a
 * range that covers the actual values present in that tensor. This op is typically
 * used to produce the {@code requested_output_min} and {@code requested_output_max} for
 * {@code Requantize}.
 */
@Operator(
    group = "quantization"
)
public final class RequantizationRange extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RequantizationRange";

  private Output<TFloat32> outputMin;

  private Output<TFloat32> outputMax;

  private RequantizationRange(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RequantizationRange operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @return a new instance of RequantizationRange
   */
  @Endpoint(
      describeByClass = true
  )
  public static RequantizationRange create(Scope scope, Operand<? extends TNumber> input,
      Operand<TFloat32> inputMin, Operand<TFloat32> inputMax) {
    OperationBuilder opBuilder = scope.env().opBuilder("RequantizationRange", scope.makeOpName("RequantizationRange"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RequantizationRange(opBuilder.build());
  }

  /**
   * Gets outputMin.
   * The computed min output.
   * @return outputMin.
   */
  public Output<TFloat32> outputMin() {
    return outputMin;
  }

  /**
   * Gets outputMax.
   * the computed max output.
   * @return outputMax.
   */
  public Output<TFloat32> outputMax() {
    return outputMax;
  }
}
