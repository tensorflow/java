/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TType;

/**
 * The Relayout operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Relayout.OP_NAME,
    inputsClass = Relayout.Inputs.class
)
@Operator
public final class Relayout<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Relayout";

  private Output<T> output;

  public Relayout(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Relayout operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param layout The value of the layout attribute
   * @param <T> data type for {@code Relayout} output and operands
   * @return a new instance of Relayout
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Relayout<T> create(Scope scope, Operand<T> input, String layout) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Relayout");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("layout", layout);
    return new Relayout<>(opBuilder.build());
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
      outputsClass = Relayout.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Relayout<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The layout attribute
     */
    public final String layout;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Relayout<>(op), op, Arrays.asList("layout", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      layout = op.attributes().getAttrString("layout");
      T = op.attributes().getAttrType("T");
    }
  }
}
