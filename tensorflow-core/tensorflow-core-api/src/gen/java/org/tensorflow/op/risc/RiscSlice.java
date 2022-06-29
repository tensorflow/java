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
 * The RiscSlice operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscSlice.OP_NAME,
    inputsClass = RiscSlice.Inputs.class
)
public final class RiscSlice<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscSlice";

  private Output<T> output;

  public RiscSlice(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscSlice operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param begin The begin value
   * @param sizeOutput The sizeOutput value
   * @param <T> data type for {@code RiscSlice} output and operands
   * @param <U> data type for {@code RiscSlice} output and operands
   * @return a new instance of RiscSlice
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TNumber> RiscSlice<T> create(Scope scope,
      Operand<T> input, Operand<U> begin, Operand<U> sizeOutput) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscSlice");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    return new RiscSlice<>(opBuilder.build());
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
      outputsClass = RiscSlice.class
  )
  public static class Inputs<T extends TNumber, U extends TNumber> extends RawOpInputs<RiscSlice<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The begin input
     */
    public final Operand<U> begin;

    /**
     * The sizeOutput input
     */
    public final Operand<U> sizeOutput;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Index attribute
     */
    public final DataType Index;

    public Inputs(GraphOperation op) {
      super(new RiscSlice<>(op), op, Arrays.asList("T", "Index"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      begin = (Operand<U>) op.input(inputIndex++);
      sizeOutput = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Index = op.attributes().getAttrType("Index");
    }
  }
}
