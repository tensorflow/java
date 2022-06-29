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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * The RiscConcat operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscConcat.OP_NAME,
    inputsClass = RiscConcat.Inputs.class
)
public final class RiscConcat<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscConcat";

  private Output<T> output;

  public RiscConcat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscConcat operation.
   *
   * @param scope current scope
   * @param values The values value
   * @param axis The axis value
   * @param <T> data type for {@code RiscConcat} output and operands
   * @return a new instance of RiscConcat
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RiscConcat<T> create(Scope scope, Iterable<Operand<T>> values,
      Operand<? extends TNumber> axis) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscConcat");
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInput(axis.asOutput());
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

  @OpInputsMetadata(
      outputsClass = RiscConcat.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RiscConcat<T>> {
    /**
     * The values input
     */
    public final Iterable<Operand<T>> values;

    /**
     * The axis input
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    public Inputs(GraphOperation op) {
      super(new RiscConcat<>(op), op, Arrays.asList("T", "Tidx"));
      int inputIndex = 0;
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tidx = op.attributes().getAttrType("Tidx");
    }
  }
}
