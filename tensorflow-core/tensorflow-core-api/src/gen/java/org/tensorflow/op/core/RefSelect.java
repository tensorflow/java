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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Forwards the {@code index}th element of {@code inputs} to {@code output}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RefSelect.OP_NAME,
    inputsClass = RefSelect.Inputs.class
)
@Operator
public final class RefSelect<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RefSelect";

  private Output<T> output;

  public RefSelect(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RefSelect operation.
   *
   * @param scope current scope
   * @param index A scalar that determines the input that gets selected.
   * @param inputs A list of ref tensors, one of which will be forwarded to {@code output}.
   * @param <T> data type for {@code RefSelect} output and operands
   * @return a new instance of RefSelect
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RefSelect<T> create(Scope scope, Operand<TInt32> index,
      Iterable<Operand<T>> inputs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RefSelect");
    opBuilder.addInput(index.asOutput());
    opBuilder.addInputList(Operands.asOutputs(inputs));
    return new RefSelect<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The forwarded tensor.
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
      outputsClass = RefSelect.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RefSelect<T>> {
    /**
     * A scalar that determines the input that gets selected.
     */
    public final Operand<TInt32> index;

    /**
     * A list of ref tensors, one of which will be forwarded to {@code output}.
     */
    public final Iterable<Operand<T>> inputs;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RefSelect<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      index = (Operand<TInt32>) op.input(inputIndex++);
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      T = op.attributes().getAttrType("T");
    }
  }
}
