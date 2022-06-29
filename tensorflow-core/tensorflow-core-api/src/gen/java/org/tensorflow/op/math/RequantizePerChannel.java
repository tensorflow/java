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

package org.tensorflow.op.math;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Requantizes input with min and max values known per channel.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = RequantizePerChannel.OP_NAME,
    inputsClass = RequantizePerChannel.Inputs.class
)
public final class RequantizePerChannel<U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RequantizePerChannel";

  private Output<U> output;

  private Output<TFloat32> outputMin;

  private Output<TFloat32> outputMax;

  public RequantizePerChannel(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RequantizePerChannel operation.
   *
   * @param scope current scope
   * @param input The original input tensor.
   * @param inputMin The minimum value of the input tensor
   * @param inputMax The maximum value of the input tensor.
   * @param requestedOutputMin The minimum value of the output tensor requested.
   * @param requestedOutputMax The maximum value of the output tensor requested.
   * @param outType The quantized type of output tensor that needs to be converted.
   * @param <U> data type for {@code RequantizePerChannel} output and operands
   * @return a new instance of RequantizePerChannel
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> RequantizePerChannel<U> create(Scope scope,
      Operand<? extends TNumber> input, Operand<TFloat32> inputMin, Operand<TFloat32> inputMax,
      Operand<TFloat32> requestedOutputMin, Operand<TFloat32> requestedOutputMax,
      Class<U> outType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RequantizePerChannel");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder.addInput(requestedOutputMin.asOutput());
    opBuilder.addInput(requestedOutputMax.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new RequantizePerChannel<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Output tensor.
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  /**
   * Gets outputMin.
   * The minimum value of the final output tensor
   * @return outputMin.
   */
  public Output<TFloat32> outputMin() {
    return outputMin;
  }

  /**
   * Gets outputMax.
   * The maximum value of the final output tensor.
   * @return outputMax.
   */
  public Output<TFloat32> outputMax() {
    return outputMax;
  }

  @OpInputsMetadata(
      outputsClass = RequantizePerChannel.class
  )
  public static class Inputs extends RawOpInputs<RequantizePerChannel<?>> {
    /**
     * The original input tensor.
     */
    public final Operand<? extends TNumber> input;

    /**
     * The minimum value of the input tensor
     */
    public final Operand<TFloat32> inputMin;

    /**
     * The maximum value of the input tensor.
     */
    public final Operand<TFloat32> inputMax;

    /**
     * The minimum value of the output tensor requested.
     */
    public final Operand<TFloat32> requestedOutputMin;

    /**
     * The maximum value of the output tensor requested.
     */
    public final Operand<TFloat32> requestedOutputMax;

    /**
     * The quantized type of input tensor that needs to be converted.
     */
    public final DataType T;

    /**
     * The quantized type of output tensor that needs to be converted.
     */
    public final DataType outType;

    public Inputs(GraphOperation op) {
      super(new RequantizePerChannel<>(op), op, Arrays.asList("T", "out_type"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      inputMin = (Operand<TFloat32>) op.input(inputIndex++);
      inputMax = (Operand<TFloat32>) op.input(inputIndex++);
      requestedOutputMin = (Operand<TFloat32>) op.input(inputIndex++);
      requestedOutputMax = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      outType = op.attributes().getAttrType("out_type");
    }
  }
}
