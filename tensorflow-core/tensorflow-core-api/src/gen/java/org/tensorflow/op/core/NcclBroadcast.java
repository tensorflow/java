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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Sends `input` to all devices that are connected to the output.
 * <p>
 * Sends `input` to all devices that are connected to the output.
 * <p>
 * The graph should be constructed so that all ops connected to the output have a
 * valid device assignment, and the op itself is assigned one of these devices.
 * <p>
 * input: The input to the broadcast.
 * output: The same as input.
 * shape: The shape of the input tensor.
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
public final class NcclBroadcast<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new NcclBroadcast operation.
   * 
   * @param scope current scope
   * @param input 
   * @param shape 
   * @return a new instance of NcclBroadcast
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> NcclBroadcast<T> create(Scope scope, Operand<T> input, Shape shape) {
    OperationBuilder opBuilder = scope.env().opBuilder("NcclBroadcast", scope.makeOpName("NcclBroadcast"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("shape", shape);
    return new NcclBroadcast<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "NcclBroadcast";
  
  private Output<T> output;
  
  private NcclBroadcast(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
