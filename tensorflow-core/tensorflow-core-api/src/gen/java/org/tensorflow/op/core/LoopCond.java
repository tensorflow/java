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

package org.tensorflow.op.core;

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
 * Forwards the input to the output.
 * This operator represents the loop termination condition used by the
 * &quot;pivot&quot; switches of a loop.
 */
@OpMetadata(
    opType = LoopCond.OP_NAME,
    inputsClass = LoopCond.Inputs.class
)
@Operator
public final class LoopCond extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LoopCond";

  private Output<TBool> output;

  public LoopCond(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LoopCond operation.
   *
   * @param scope current scope
   * @param input A boolean scalar, representing the branch predicate of the Switch op.
   * @return a new instance of LoopCond
   */
  @Endpoint(
      describeByClass = true
  )
  public static LoopCond create(Scope scope, Operand<TBool> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LoopCond");
    opBuilder.addInput(input.asOutput());
    return new LoopCond(opBuilder.build());
  }

  /**
   * Gets output.
   * The same tensor as {@code input}.
   * @return output.
   */
  public Output<TBool> output() {
    return output;
  }

  @Override
  public Output<TBool> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = LoopCond.class
  )
  public static class Inputs extends RawOpInputs<LoopCond> {
    /**
     * A boolean scalar, representing the branch predicate of the Switch op.
     */
    public final Operand<TBool> input;

    public Inputs(GraphOperation op) {
      super(new LoopCond(op), op, Arrays.asList());
      int inputIndex = 0;
      input = (Operand<TBool>) op.input(inputIndex++);
    }
  }
}
