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
import org.tensorflow.types.TBool;

/**
 * The RiscLogicalOr operation
 */
@OpMetadata(
    opType = RiscLogicalOr.OP_NAME,
    inputsClass = RiscLogicalOr.Inputs.class
)
public final class RiscLogicalOr extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscLogicalOr";

  private Output<TBool> z;

  public RiscLogicalOr(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscLogicalOr operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param y The y value
   * @return a new instance of RiscLogicalOr
   */
  @Endpoint(
      describeByClass = true
  )
  public static RiscLogicalOr create(Scope scope, Operand<TBool> x, Operand<TBool> y) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscLogicalOr");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    return new RiscLogicalOr(opBuilder.build());
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
      outputsClass = RiscLogicalOr.class
  )
  public static class Inputs extends RawOpInputs<RiscLogicalOr> {
    /**
     * The x input
     */
    public final Operand<TBool> x;

    /**
     * The y input
     */
    public final Operand<TBool> y;

    public Inputs(GraphOperation op) {
      super(new RiscLogicalOr(op), op, Arrays.asList());
      int inputIndex = 0;
      x = (Operand<TBool>) op.input(inputIndex++);
      y = (Operand<TBool>) op.input(inputIndex++);
    }
  }
}
