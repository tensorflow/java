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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * The SelectV2 operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Select.OP_NAME,
    inputsClass = Select.Inputs.class
)
@Operator
public final class Select<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SelectV2";

  private Output<T> output;

  public Select(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SelectV2 operation.
   *
   * @param scope current scope
   * @param condition The condition value
   * @param t The t value
   * @param e The e value
   * @param <T> data type for {@code SelectV2} output and operands
   * @return a new instance of Select
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Select<T> create(Scope scope, Operand<TBool> condition,
      Operand<T> t, Operand<T> e) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Select");
    opBuilder.addInput(condition.asOutput());
    opBuilder.addInput(t.asOutput());
    opBuilder.addInput(e.asOutput());
    return new Select<>(opBuilder.build());
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
      outputsClass = Select.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Select<T>> {
    /**
     * The condition input
     */
    public final Operand<TBool> condition;

    /**
     * The t input
     */
    public final Operand<T> t;

    /**
     * The e input
     */
    public final Operand<T> e;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Select<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      condition = (Operand<TBool>) op.input(inputIndex++);
      t = (Operand<T>) op.input(inputIndex++);
      e = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
