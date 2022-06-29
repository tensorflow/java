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

package org.tensorflow.op.math;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;

/**
 * Returns the truth value of {@code NOT x} element-wise.
 */
@OpMetadata(
    opType = LogicalNot.OP_NAME,
    inputsClass = LogicalNot.Inputs.class
)
@Operator(
    group = "math"
)
public final class LogicalNot extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LogicalNot";

  private Output<TBool> y;

  public LogicalNot(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LogicalNot operation.
   *
   * @param scope current scope
   * @param x A {@code Tensor} of type {@code bool}.
   * @return a new instance of LogicalNot
   */
  @Endpoint(
      describeByClass = true
  )
  public static LogicalNot create(Scope scope, Operand<TBool> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LogicalNot");
    opBuilder.addInput(x.asOutput());
    return new LogicalNot(opBuilder.build());
  }

  /**
   * Gets y.
   * A {@code Tensor} of type {@code bool} with the same shape as {@code x}. The logical negation of {@code x}.
   * @return y.
   */
  public Output<TBool> y() {
    return y;
  }

  @Override
  public Output<TBool> asOutput() {
    return y;
  }

  @OpInputsMetadata(
      outputsClass = LogicalNot.class
  )
  public static class Inputs extends RawOpInputs<LogicalNot> {
    /**
     * A {@code Tensor} of type {@code bool}.
     */
    public final Operand<TBool> x;

    public Inputs(GraphOperation op) {
      super(new LogicalNot(op), op, Arrays.asList());
      int inputIndex = 0;
      x = (Operand<TBool>) op.input(inputIndex++);
    }
  }
}
