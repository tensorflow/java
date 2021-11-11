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
 * The RiscLogicalAnd operation
 */
@OpMetadata(
    opType = RiscLogicalAnd.OP_NAME,
    inputsClass = RiscLogicalAnd.Inputs.class
)
public final class RiscLogicalAnd extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscLogicalAnd";

  private Output<TBool> z;

  public RiscLogicalAnd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscLogicalAnd operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param y The y value
   * @return a new instance of RiscLogicalAnd
   */
  @Endpoint(
      describeByClass = true
  )
  public static RiscLogicalAnd create(Scope scope, Operand<TBool> x, Operand<TBool> y) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscLogicalAnd");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    return new RiscLogicalAnd(opBuilder.build());
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
      outputsClass = RiscLogicalAnd.class
  )
  public static class Inputs extends RawOpInputs<RiscLogicalAnd> {
    /**
     * The x input
     */
    public final Operand<TBool> x;

    /**
     * The y input
     */
    public final Operand<TBool> y;

    public Inputs(GraphOperation op) {
      super(new RiscLogicalAnd(op), op, Arrays.asList());
      int inputIndex = 0;
      x = (Operand<TBool>) op.input(inputIndex++);
      y = (Operand<TBool>) op.input(inputIndex++);
    }
  }
}
