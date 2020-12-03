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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Reduces `input` from `num_devices` using `reduction` to a single device.
 * <p>
 * Reduces `input` from `num_devices` using `reduction` to a single device.
 * <p>
 * The graph should be constructed so that all inputs have a valid device
 * assignment, and the op itself is assigned one of these devices.
 * <p>
 * input: The input to the reduction.
 * data: the value of the reduction across all `num_devices` devices.
 * reduction: the reduction operation to perform.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class NcclReduce<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new NcclReduce operation.
   * 
   * @param scope current scope
   * @param input 
   * @param reduction 
   * @return a new instance of NcclReduce
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> NcclReduce<T> create(Scope scope, Iterable<Operand<T>> input, String reduction) {
    OperationBuilder opBuilder = scope.env().opBuilder("NcclReduce", scope.makeOpName("NcclReduce"));
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("reduction", reduction);
    return new NcclReduce<T>(opBuilder.build());
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
  public static final String OP_NAME = "NcclReduce";
  
  private Output<T> output;
  
  private NcclReduce(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
