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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * The RiscConcat operation
 *
 * @param <T> data type for {@code output} output
 */
public final class RiscConcat<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscConcat";

  private Output<T> output;

  private RiscConcat(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscConcat operation.
   *
   * @param scope current scope
   * @param values the values value
   * @param axis the axis value
   * @param <T> data type for {@code RiscConcat} output and operands
   * @return a new instance of RiscConcat
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RiscConcat<T> create(Scope scope, Iterable<Operand<T>> values,
      Operand<? extends TNumber> axis) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("RiscConcat"));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInput(axis.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RiscConcat<>(opBuilder.build());
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
