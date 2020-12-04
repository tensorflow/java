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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Increments 'ref' until it reaches 'limit'.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class CountUpTo<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new CountUpTo operation.
   * 
   * @param scope current scope
   * @param ref Should be from a scalar `Variable` node.
   * @param limit If incrementing ref would bring it above limit, instead generates an
   * 'OutOfRange' error.
   * @return a new instance of CountUpTo
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> CountUpTo<T> create(Scope scope, Operand<T> ref, Long limit) {
    OperationBuilder opBuilder = scope.env().opBuilder("CountUpTo", scope.makeOpName("CountUpTo"));
    opBuilder.addInput(ref.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("limit", limit);
    return new CountUpTo<T>(opBuilder.build());
  }
  
  /**
   * A copy of the input before increment. If nothing else modifies the
   * input, the values produced will all be distinct.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CountUpTo";
  
  private Output<T> output;
  
  private CountUpTo(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
