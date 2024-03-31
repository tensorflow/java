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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * A stack that produces elements in first-in last-out order.
 */
@OpMetadata(
    opType = StackCreate.OP_NAME,
    inputsClass = StackCreate.Inputs.class
)
@Operator
public final class StackCreate extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StackV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public StackCreate(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StackV2 operation.
   *
   * @param scope current scope
   * @param maxSize The maximum size of the stack if non-negative. If negative, the stack
   * size is unlimited.
   * @param elemType The type of the elements on the stack.
   * @param options carries optional attribute values
   * @param <T> data type for {@code StackV2} output and operands
   * @return a new instance of StackCreate
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> StackCreate create(Scope scope, Operand<TInt32> maxSize,
      Class<T> elemType, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StackCreate");
    opBuilder.addInput(maxSize.asOutput());
    opBuilder.setAttr("elem_type", Operands.toDataType(elemType));
    if (options != null) {
      for (Options opts : options) {
        if (opts.stackName != null) {
          opBuilder.setAttr("stack_name", opts.stackName);
        }
      }
    }
    return new StackCreate(opBuilder.build());
  }

  /**
   * Sets the stackName option.
   *
   * @param stackName Overrides the name used for the temporary stack resource. Default
   * value is the name of the 'Stack' op (which is guaranteed unique).
   * @return this Options instance.
   */
  public static Options stackName(String stackName) {
    return new Options().stackName(stackName);
  }

  /**
   * Gets handle.
   * The handle to the stack.
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.StackCreate}
   */
  public static class Options {
    private String stackName;

    private Options() {
    }

    /**
     * Sets the stackName option.
     *
     * @param stackName Overrides the name used for the temporary stack resource. Default
     * value is the name of the 'Stack' op (which is guaranteed unique).
     * @return this Options instance.
     */
    public Options stackName(String stackName) {
      this.stackName = stackName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = StackCreate.class
  )
  public static class Inputs extends RawOpInputs<StackCreate> {
    /**
     * The maximum size of the stack if non-negative. If negative, the stack
     * size is unlimited.
     */
    public final Operand<TInt32> maxSize;

    /**
     * The type of the elements on the stack.
     */
    public final DataType elemType;

    /**
     * Overrides the name used for the temporary stack resource. Default
     * value is the name of the 'Stack' op (which is guaranteed unique).
     */
    public final String stackName;

    public Inputs(GraphOperation op) {
      super(new StackCreate(op), op, Arrays.asList("elem_type", "stack_name"));
      int inputIndex = 0;
      maxSize = (Operand<TInt32>) op.input(inputIndex++);
      elemType = op.attributes().getAttrType("elem_type");
      stackName = op.attributes().getAttrString("stack_name");
    }
  }
}
