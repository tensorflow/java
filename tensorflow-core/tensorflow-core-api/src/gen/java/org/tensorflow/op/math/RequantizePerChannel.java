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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Requantizes input with min and max values known per channel.
 * 
 * @param <U> data type for {@code output()} output
 */
public final class RequantizePerChannel<U extends TType> extends RawOp {
  
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
   * @return a new instance of RequantizePerChannel
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TType> RequantizePerChannel<U> create(Scope scope, Operand<T> input, Operand<TFloat32> inputMin, Operand<TFloat32> inputMax, Operand<TFloat32> requestedOutputMin, Operand<TFloat32> requestedOutputMax, DataType<U> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("RequantizePerChannel", scope.makeOpName("RequantizePerChannel"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder.addInput(requestedOutputMin.asOutput());
    opBuilder.addInput(requestedOutputMax.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", outType);
    return new RequantizePerChannel<U>(opBuilder.build());
  }
  
  /**
   * Output tensor.
   */
  public Output<U> output() {
    return output;
  }
  
  /**
   * The minimum value of the final output tensor
   */
  public Output<TFloat32> outputMin() {
    return outputMin;
  }
  
  /**
   * The maximum value of the final output tensor.
   */
  public Output<TFloat32> outputMax() {
    return outputMax;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RequantizePerChannel";
  
  private Output<U> output;
  private Output<TFloat32> outputMin;
  private Output<TFloat32> outputMax;
  
  private RequantizePerChannel(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }
}
