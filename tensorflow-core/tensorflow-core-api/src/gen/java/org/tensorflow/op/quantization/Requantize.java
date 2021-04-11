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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Converts the quantized {@code input} tensor into a lower-precision {@code output}.
 * Converts the quantized {@code input} tensor into a lower-precision {@code output}, using the
 * output range specified with {@code requested_output_min} and {@code requested_output_max}.
 * <p>{@code [input_min, input_max]} are scalar floats that specify the range for the float
 * interpretation of the {@code input} data. For example, if {@code input_min} is -1.0f and
 * {@code input_max} is 1.0f, and we are dealing with {@code quint16} quantized data, then a 0
 * value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
 *
 * @param <U> data type for {@code output} output
 */
@Operator(
    group = "quantization"
)
public final class Requantize<U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Requantize";

  private Output<U> output;

  private Output<TFloat32> outputMin;

  private Output<TFloat32> outputMax;

  private Requantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Requantize operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @param requestedOutputMin The float value that the minimum quantized output value represents.
   * @param requestedOutputMax The float value that the maximum quantized output value represents.
   * @param outType The type of the output. Should be a lower bit depth than Tinput.
   * @param <U> data type for {@code Requantize} output and operands
   * @return a new instance of Requantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Requantize<U> create(Scope scope,
      Operand<? extends TNumber> input, Operand<TFloat32> inputMin, Operand<TFloat32> inputMax,
      Operand<TFloat32> requestedOutputMin, Operand<TFloat32> requestedOutputMax,
      Class<U> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("Requantize", scope.makeOpName("Requantize"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder.addInput(requestedOutputMin.asOutput());
    opBuilder.addInput(requestedOutputMax.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new Requantize<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  /**
   * Gets outputMin.
   * The requested_output_min value is copied into this output.
   * @return outputMin.
   */
  public Output<TFloat32> outputMin() {
    return outputMin;
  }

  /**
   * Gets outputMax.
   * The requested_output_max value is copied into this output.
   * @return outputMax.
   */
  public Output<TFloat32> outputMax() {
    return outputMax;
  }
}
