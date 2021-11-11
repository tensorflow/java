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
 * The RiscLogicalNot operation
 */
@OpMetadata(
    opType = RiscLogicalNot.OP_NAME,
    inputsClass = RiscLogicalNot.Inputs.class
)
public final class RiscLogicalNot extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscLogicalNot";

  private Output<TBool> z;

  public RiscLogicalNot(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscLogicalNot operation.
   *
   * @param scope current scope
   * @param x The x value
   * @return a new instance of RiscLogicalNot
   */
  @Endpoint(
      describeByClass = true
  )
  public static RiscLogicalNot create(Scope scope, Operand<TBool> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscLogicalNot");
    opBuilder.addInput(x.asOutput());
    return new RiscLogicalNot(opBuilder.build());
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
      outputsClass = RiscLogicalNot.class
  )
  public static class Inputs extends RawOpInputs<RiscLogicalNot> {
    /**
     * The x input
     */
    public final Operand<TBool> x;

    public Inputs(GraphOperation op) {
      super(new RiscLogicalNot(op), op, Arrays.asList());
      int inputIndex = 0;
      x = (Operand<TBool>) op.input(inputIndex++);
    }
  }
}
