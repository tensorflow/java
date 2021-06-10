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

package org.tensorflow.op.risc;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscPad operation
 *
 * @param <T> data type for {@code output} output
 */
public final class RiscPad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscPad";

  private Output<T> output;

  private RiscPad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscPad operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param paddings the paddings value
   * @param constantValues the constantValues value
   * @param <T> data type for {@code RiscPad} output and operands
   * @return a new instance of RiscPad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscPad<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> paddings, Operand<T> constantValues) {
    OperationBuilder opBuilder = scope.env().opBuilder("RiscPad", scope.makeOpName("RiscPad"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder.addInput(constantValues.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RiscPad<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }
}
