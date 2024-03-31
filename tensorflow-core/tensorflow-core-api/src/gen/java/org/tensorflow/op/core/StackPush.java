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
 * Push an element onto the stack.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = StackPush.OP_NAME,
    inputsClass = StackPush.Inputs.class
)
@Operator
public final class StackPush<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StackPushV2";

  private Output<T> output;

  public StackPush(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StackPushV2 operation.
   *
   * @param scope current scope
   * @param handle The handle to a stack.
   * @param elem The tensor to be pushed onto the stack.
   * @param options carries optional attribute values
   * @param <T> data type for {@code StackPushV2} output and operands
   * @return a new instance of StackPush
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> StackPush<T> create(Scope scope, Operand<? extends TType> handle,
      Operand<T> elem, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StackPush");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(elem.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.swapMemory != null) {
          opBuilder.setAttr("swap_memory", opts.swapMemory);
        }
      }
    }
    return new StackPush<>(opBuilder.build());
  }

  /**
   * Sets the swapMemory option.
   *
   * @param swapMemory Swap {@code elem} to CPU. Default to false.
   * @return this Options instance.
   */
  public static Options swapMemory(Boolean swapMemory) {
    return new Options().swapMemory(swapMemory);
  }

  /**
   * Gets output.
   * The same tensor as the input 'elem'.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.StackPush}
   */
  public static class Options {
    private Boolean swapMemory;

    private Options() {
    }

    /**
     * Sets the swapMemory option.
     *
     * @param swapMemory Swap {@code elem} to CPU. Default to false.
     * @return this Options instance.
     */
    public Options swapMemory(Boolean swapMemory) {
      this.swapMemory = swapMemory;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = StackPush.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<StackPush<T>> {
    /**
     * The handle to a stack.
     */
    public final Operand<? extends TType> handle;

    /**
     * The tensor to be pushed onto the stack.
     */
    public final Operand<T> elem;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Swap `elem` to CPU. Default to false.
     */
    public final boolean swapMemory;

    public Inputs(GraphOperation op) {
      super(new StackPush<>(op), op, Arrays.asList("T", "swap_memory"));
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      elem = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      swapMemory = op.attributes().getAttrBool("swap_memory");
    }
  }
}
