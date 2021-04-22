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
import org.tensorflow.types.TInt32;

/**
 * Set a bound for the given input value as a hint to Xla compiler,
 * <pre>
 *     returns the same value.
 * </pre>
 */
@Operator(
    group = "xla"
)
public final class XlaSetBound extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSetBound";

  private Output<TInt32> output;

  private XlaSetBound(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSetBound operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param bound the bound value
   * @return a new instance of XlaSetBound
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSetBound create(Scope scope, Operand<TInt32> input, Operand<TInt32> bound) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaSetBound", scope.makeOpName("XlaSetBound"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(bound.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new XlaSetBound(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }
}
