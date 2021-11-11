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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscPad operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscPad.OP_NAME,
    inputsClass = RiscPad.Inputs.class
)
public final class RiscPad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscPad";

  private Output<T> output;

  public RiscPad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscPad operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param paddings The paddings value
   * @param constantValues The constantValues value
   * @param <T> data type for {@code RiscPad} output and operands
   * @return a new instance of RiscPad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscPad<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> paddings, Operand<T> constantValues) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscPad");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder.addInput(constantValues.asOutput());
    return new RiscPad<>(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = RiscPad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RiscPad<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The paddings input
     */
    public final Operand<? extends TNumber> paddings;

    /**
     * The constantValues input
     */
    public final Operand<T> constantValues;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tpaddings attribute
     */
    public final DataType Tpaddings;

    public Inputs(GraphOperation op) {
      super(new RiscPad<>(op), op, Arrays.asList("T", "Tpaddings"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      paddings = (Operand<? extends TNumber>) op.input(inputIndex++);
      constantValues = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tpaddings = op.attributes().getAttrType("Tpaddings");
    }
  }
}
