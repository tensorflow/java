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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscBinaryComparison operation
 */
public final class RiscBinaryComparison extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscBinaryComparison";

  private Output<TBool> z;

  private RiscBinaryComparison(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscBinaryComparison operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param y the y value
   * @param opType the value of the opType property
   * @param <T> data type for {@code RiscBinaryComparison} output and operands
   * @return a new instance of RiscBinaryComparison
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscBinaryComparison create(Scope scope, Operand<T> x,
      Operand<T> y, String opType) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("RiscBinaryComparison"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("op_type", opType);
    return new RiscBinaryComparison(opBuilder.build());
  }

  /**
   * Gets z.
   *
   * @return z.
   */
  public Output<TBool> z() {
    return z;
  }

  @Override
  public Output<TBool> asOutput() {
    return z;
  }
}
