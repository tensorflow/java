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
import org.tensorflow.types.family.TType;

/**
 * Makes its input available to the next iteration.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = NextIteration.OP_NAME,
    inputsClass = NextIteration.Inputs.class
)
@Operator
public final class NextIteration<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "NextIteration";

  private Output<T> output;

  public NextIteration(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new NextIteration operation.
   *
   * @param scope current scope
   * @param data The tensor to be made available to the next iteration.
   * @param <T> data type for {@code NextIteration} output and operands
   * @return a new instance of NextIteration
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> NextIteration<T> create(Scope scope, Operand<T> data) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "NextIteration");
    opBuilder.addInput(data.asOutput());
    return new NextIteration<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The same tensor as {@code data}.
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
      outputsClass = NextIteration.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<NextIteration<T>> {
    /**
     * The tensor to be made available to the next iteration.
     */
    public final Operand<T> data;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new NextIteration<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      data = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
