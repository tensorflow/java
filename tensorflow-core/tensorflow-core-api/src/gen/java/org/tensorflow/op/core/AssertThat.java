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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;

/**
 * Asserts that the given condition is true.
 * If {@code condition} evaluates to false, print the list of tensors in {@code data}.
 * {@code summarize} determines how many entries of the tensors to print.
 */
@OpMetadata(
    opType = AssertThat.OP_NAME,
    inputsClass = AssertThat.Inputs.class
)
@Operator
public final class AssertThat extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Assert";

  public AssertThat(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new Assert operation.
   *
   * @param scope current scope
   * @param condition The condition to evaluate.
   * @param data The tensors to print out when condition is false.
   * @param options carries optional attribute values
   * @return a new instance of AssertThat
   */
  @Endpoint(
      describeByClass = true
  )
  public static AssertThat create(Scope scope, Operand<TBool> condition, Iterable<Operand<?>> data,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AssertThat");
    opBuilder.addInput(condition.asOutput());
    opBuilder.addInputList(Operands.asOutputs(data));
    if (options != null) {
      for (Options opts : options) {
        if (opts.summarize != null) {
          opBuilder.setAttr("summarize", opts.summarize);
        }
      }
    }
    return new AssertThat(opBuilder.build());
  }

  /**
   * Sets the summarize option.
   *
   * @param summarize Print this many entries of each tensor.
   * @return this Options instance.
   */
  public static Options summarize(Long summarize) {
    return new Options().summarize(summarize);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.AssertThat}
   */
  public static class Options {
    private Long summarize;

    private Options() {
    }

    /**
     * Sets the summarize option.
     *
     * @param summarize Print this many entries of each tensor.
     * @return this Options instance.
     */
    public Options summarize(Long summarize) {
      this.summarize = summarize;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AssertThat.class
  )
  public static class Inputs extends RawOpInputs<AssertThat> {
    /**
     * The condition to evaluate.
     */
    public final Operand<TBool> condition;

    /**
     * The tensors to print out when condition is false.
     */
    public final Iterable<Operand<?>> data;

    /**
     * The T attribute
     */
    public final DataType[] T;

    /**
     * Print this many entries of each tensor.
     */
    public final long summarize;

    public Inputs(GraphOperation op) {
      super(new AssertThat(op), op, Arrays.asList("T", "summarize"));
      int inputIndex = 0;
      condition = (Operand<TBool>) op.input(inputIndex++);
      int dataLength = op.inputListLength("data");
      data = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, dataLength));
      inputIndex += dataLength;
      T = op.attributes().getAttrTypeList("T");
      summarize = op.attributes().getAttrInt("summarize");
    }
  }
}
