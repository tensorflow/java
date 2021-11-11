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
 * Computes requantization range per channel.
 */
@OpMetadata(
    opType = RequantizationRangePerChannel.OP_NAME,
    inputsClass = RequantizationRangePerChannel.Inputs.class
)
public final class RequantizationRangePerChannel extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RequantizationRangePerChannel";

  private Output<TFloat32> outputMin;

  private Output<TFloat32> outputMax;

  public RequantizationRangePerChannel(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RequantizationRangePerChannel operation.
   *
   * @param scope current scope
   * @param input The original input tensor.
   * @param inputMin The minimum value of the input tensor
   * @param inputMax The maximum value of the input tensor.
   * @param clipValueMax The maximum value of the output that needs to be clipped.
   * Example: set this to 6 for Relu6.
   * @return a new instance of RequantizationRangePerChannel
   */
  @Endpoint(
      describeByClass = true
  )
  public static RequantizationRangePerChannel create(Scope scope, Operand<? extends TNumber> input,
      Operand<TFloat32> inputMin, Operand<TFloat32> inputMax, Float clipValueMax) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RequantizationRangePerChannel");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder.setAttr("clip_value_max", clipValueMax);
    return new RequantizationRangePerChannel(opBuilder.build());
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
      outputsClass = RequantizationRangePerChannel.class
  )
  public static class Inputs extends RawOpInputs<RequantizationRangePerChannel> {
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
     * The quantized type of input tensor that needs to be converted.
     */
    public final DataType T;

    /**
     * The maximum value of the output that needs to be clipped.
     * Example: set this to 6 for Relu6.
     */
    public final float clipValueMax;

    public Inputs(GraphOperation op) {
      super(new RequantizationRangePerChannel(op), op, Arrays.asList("T", "clip_value_max"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      inputMin = (Operand<TFloat32>) op.input(inputIndex++);
      inputMax = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      clipValueMax = op.attributes().getAttrFloat("clip_value_max");
    }
  }
}
