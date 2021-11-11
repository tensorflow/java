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

package org.tensorflow.op.summary;

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
import org.tensorflow.types.TString;

/**
 * Merges summaries.
 * This op creates a
 *  <a href="https://www.tensorflow.org/code/tensorflow/core/framework/summary.proto">{@code Summary}</a> 
 * protocol buffer that contains the union of all the values in the input
 * summaries.
 * <p>When the Op is run, it reports an {@code InvalidArgument} error if multiple values
 * in the summaries to merge use the same tag.
 */
@OpMetadata(
    opType = MergeSummary.OP_NAME,
    inputsClass = MergeSummary.Inputs.class
)
@Operator(
    group = "summary"
)
public final class MergeSummary extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MergeSummary";

  private Output<TString> summary;

  public MergeSummary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MergeSummary operation.
   *
   * @param scope current scope
   * @param inputs Can be of any shape.  Each must contain serialized {@code Summary} protocol
   * buffers.
   * @return a new instance of MergeSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static MergeSummary create(Scope scope, Iterable<Operand<TString>> inputs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MergeSummary");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    return new MergeSummary(opBuilder.build());
  }

  /**
   * Gets summary.
   * Scalar. Serialized {@code Summary} protocol buffer.
   * @return summary.
   */
  public Output<TString> summary() {
    return summary;
  }

  @Override
  public Output<TString> asOutput() {
    return summary;
  }

  @OpInputsMetadata(
      outputsClass = MergeSummary.class
  )
  public static class Inputs extends RawOpInputs<MergeSummary> {
    /**
     * Can be of any shape.  Each must contain serialized {@code Summary} protocol
     * buffers.
     */
    public final Iterable<Operand<TString>> inputs;

    public Inputs(GraphOperation op) {
      super(new MergeSummary(op), op, Arrays.asList());
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<TString>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
    }
  }
}
