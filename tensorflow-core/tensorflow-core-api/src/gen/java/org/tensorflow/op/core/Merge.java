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
 * Forwards the value of an available tensor from {@code inputs} to {@code output}.
 * {@code Merge} waits for at least one of the tensors in {@code inputs} to become available.
 * It is usually combined with {@code Switch} to implement branching.
 * <p>{@code Merge} forwards the first tensor to become available to {@code output}, and sets
 * {@code value_index} to its index in {@code inputs}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Merge.OP_NAME,
    inputsClass = Merge.Inputs.class
)
@Operator
public final class Merge<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Merge";

  private Output<T> output;

  private Output<TInt32> valueIndex;

  public Merge(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    valueIndex = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Merge operation.
   *
   * @param scope current scope
   * @param inputs The input tensors, exactly one of which will become available.
   * @param <T> data type for {@code Merge} output and operands
   * @return a new instance of Merge
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Merge<T> create(Scope scope, Iterable<Operand<T>> inputs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Merge");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    return new Merge<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Will be set to the available input tensor.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  /**
   * Gets valueIndex.
   * The index of the chosen input tensor in {@code inputs}.
   * @return valueIndex.
   */
  public Output<TInt32> valueIndex() {
    return valueIndex;
  }

  @OpInputsMetadata(
      outputsClass = Merge.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Merge<T>> {
    /**
     * The input tensors, exactly one of which will become available.
     */
    public final Iterable<Operand<T>> inputs;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Merge<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      T = op.attributes().getAttrType("T");
    }
  }
}
