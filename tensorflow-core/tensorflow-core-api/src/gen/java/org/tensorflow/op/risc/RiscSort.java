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
 * The RiscSort operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscSort.OP_NAME,
    inputsClass = RiscSort.Inputs.class
)
public final class RiscSort<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscSort";

  private Output<T> output;

  public RiscSort(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscSort operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param axis The axis value
   * @param direction The value of the direction attribute
   * @param <T> data type for {@code RiscSort} output and operands
   * @return a new instance of RiscSort
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscSort<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> axis, String direction) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscSort");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder.setAttr("direction", direction);
    return new RiscSort<>(opBuilder.build());
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
      outputsClass = RiscSort.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RiscSort<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The axis input
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The Index attribute
     */
    public final DataType Index;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The direction attribute
     */
    public final String direction;

    public Inputs(GraphOperation op) {
      super(new RiscSort<>(op), op, Arrays.asList("Index", "T", "direction"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      Index = op.attributes().getAttrType("Index");
      T = op.attributes().getAttrType("T");
      direction = op.attributes().getAttrString("direction");
    }
  }
}
