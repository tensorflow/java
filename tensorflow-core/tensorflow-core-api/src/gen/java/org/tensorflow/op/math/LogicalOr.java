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
 * Returns the truth value of x OR y element-wise.
 * <em>NOTE</em>: {@code math.LogicalOr} supports broadcasting. More about broadcasting
 *  <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">here</a>
 */
@OpMetadata(
    opType = LogicalOr.OP_NAME,
    inputsClass = LogicalOr.Inputs.class
)
@Operator(
    group = "math"
)
public final class LogicalOr extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LogicalOr";

  private Output<TBool> z;

  public LogicalOr(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LogicalOr operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param y The y value
   * @return a new instance of LogicalOr
   */
  @Endpoint(
      describeByClass = true
  )
  public static LogicalOr create(Scope scope, Operand<TBool> x, Operand<TBool> y) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LogicalOr");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    return new LogicalOr(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = LogicalOr.class
  )
  public static class Inputs extends RawOpInputs<LogicalOr> {
    /**
     * The x input
     */
    public final Operand<TBool> x;

    /**
     * The y input
     */
    public final Operand<TBool> y;

    public Inputs(GraphOperation op) {
      super(new LogicalOr(op), op, Arrays.asList());
      int inputIndex = 0;
      x = (Operand<TBool>) op.input(inputIndex++);
      y = (Operand<TBool>) op.input(inputIndex++);
    }
  }
}
