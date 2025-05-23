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
 * The RelayoutLike operation
 */
@OpMetadata(
    opType = RelayoutLike.OP_NAME,
    inputsClass = RelayoutLike.Inputs.class
)
@Operator
public final class RelayoutLike<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RelayoutLike";

  private Output<T> output;

  public RelayoutLike(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RelayoutLike operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param layoutInput The layoutInput value
   * @param <T> data type for {@code RelayoutLike} output and operands
   * @return a new instance of RelayoutLike
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RelayoutLike<T> create(Scope scope, Operand<T> input,
      Operand<? extends TType> layoutInput) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RelayoutLike");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(layoutInput.asOutput());
    return new RelayoutLike<>(opBuilder.build());
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
      outputsClass = RelayoutLike.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RelayoutLike<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The layoutInput input
     */
    public final Operand<? extends TType> layoutInput;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The U attribute
     */
    public final DataType U;

    public Inputs(GraphOperation op) {
      super(new RelayoutLike<>(op), op, Arrays.asList("T", "U"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      layoutInput = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      U = op.attributes().getAttrType("U");
    }
  }
}
