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
 * The RiscReduce operation
 *
 * @param <T> data type for {@code output} output
 */
public final class RiscReduce<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscReduce";

  private Output<T> output;

  private RiscReduce(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscReduce operation.
   *
   * @param scope current scope
   * @param tensor the tensor value
   * @param axis the axis value
   * @param reduceType the value of the reduceType property
   * @param <T> data type for {@code RiscReduce} output and operands
   * @return a new instance of RiscReduce
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscReduce<T> create(Scope scope, Operand<T> tensor,
      Operand<? extends TNumber> axis, String reduceType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscReduce");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder.setAttr("reduce_type", reduceType);
    return new RiscReduce<>(opBuilder.build());
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
