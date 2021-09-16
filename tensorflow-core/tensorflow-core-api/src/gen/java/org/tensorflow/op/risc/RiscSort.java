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
 * The RiscSort operation
 *
 * @param <T> data type for {@code output} output
 */
public final class RiscSort<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscSort";

  private Output<T> output;

  private RiscSort(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscSort operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param axis the axis value
   * @param direction the value of the direction property
   * @param <T> data type for {@code RiscSort} output and operands
   * @return a new instance of RiscSort
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscSort<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> axis, String direction) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscSort");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder.setAttr("direction", direction);
    return new RiscSort<>(opBuilder.build());
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
