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
import org.tensorflow.op.Operands;
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
 * Pop the element at the top of the stack.
 */
@OpMetadata(
    opType = StackPop.OP_NAME,
    inputsClass = StackPop.Inputs.class
)
@Operator
public final class StackPop<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StackPopV2";

  private Output<T> elem;

  public StackPop(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    elem = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StackPopV2 operation.
   *
   * @param scope current scope
   * @param handle The handle to a stack.
   * @param elemType The type of the elem that is popped.
   * @param <T> data type for {@code StackPopV2} output and operands
   * @return a new instance of StackPop
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> StackPop<T> create(Scope scope, Operand<? extends TType> handle,
      Class<T> elemType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StackPop");
    opBuilder.addInput(handle.asOutput());
    opBuilder.setAttr("elem_type", Operands.toDataType(elemType));
    return new StackPop<>(opBuilder.build());
  }

  /**
   * Gets elem.
   * The tensor that is popped from the top of the stack.
   * @return elem.
   */
  public Output<T> elem() {
    return elem;
  }

  @Override
  public Output<T> asOutput() {
    return elem;
  }

  @OpInputsMetadata(
      outputsClass = StackPop.class
  )
  public static class Inputs extends RawOpInputs<StackPop<?>> {
    /**
     * The handle to a stack.
     */
    public final Operand<? extends TType> handle;

    /**
     * The type of the elem that is popped.
     */
    public final DataType elemType;

    public Inputs(GraphOperation op) {
      super(new StackPop<>(op), op, Arrays.asList("elem_type"));
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      elemType = op.attributes().getAttrType("elem_type");
    }
  }
}
