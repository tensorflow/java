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

package org.tensorflow.op.xla;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA Pad operator, documented at
 * <p>
 *  https://www.tensorflow.org/performance/xla/operation_semantics#pad
 * .
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "xla")
public final class Pad<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Pad operation.
   * 
   * @param scope current scope
   * @param input A `Tensor` of type T.
   * @param paddingValue A scalar `Tensor` of type T.
   * @param paddingLow the padding to apply at the start of each input dimensions
   * @param paddingHigh the padding to apply at the end of each input dimension.
   * @param paddingInterior the padding to apply between each input element.
   * @return a new instance of Pad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> Pad<T> create(Scope scope, Operand<T> input, Operand<T> paddingValue, Operand<U> paddingLow, Operand<U> paddingHigh, Operand<U> paddingInterior) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaPad", scope.makeOpName("Pad"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddingValue.asOutput());
    opBuilder.addInput(paddingLow.asOutput());
    opBuilder.addInput(paddingHigh.asOutput());
    opBuilder.addInput(paddingInterior.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Pad<T>(opBuilder.build());
  }
  
  /**
   * A `Tensor` of type T.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "XlaPad";
  
  private Output<T> output;
  
  private Pad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
