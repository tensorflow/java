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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Outputs a {@code Summary} protocol buffer with a tensor and per-plugin data.
 */
@OpMetadata(
    opType = TensorSummary.OP_NAME,
    inputsClass = TensorSummary.Inputs.class
)
@Operator(
    group = "summary"
)
public final class TensorSummary extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorSummaryV2";

  private Output<TString> summary;

  public TensorSummary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorSummaryV2 operation.
   *
   * @param scope current scope
   * @param tag A string attached to this summary. Used for organization in TensorBoard.
   * @param tensor A tensor to serialize.
   * @param serializedSummaryMetadata A serialized SummaryMetadata proto. Contains plugin
   * data.
   * @return a new instance of TensorSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorSummary create(Scope scope, Operand<TString> tag,
      Operand<? extends TType> tensor, Operand<TString> serializedSummaryMetadata) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorSummary");
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(serializedSummaryMetadata.asOutput());
    return new TensorSummary(opBuilder.build());
  }

  /**
   * Gets summary.
   *
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
      outputsClass = TensorSummary.class
  )
  public static class Inputs extends RawOpInputs<TensorSummary> {
    /**
     * A string attached to this summary. Used for organization in TensorBoard.
     */
    public final Operand<TString> tag;

    /**
     * A tensor to serialize.
     */
    public final Operand<? extends TType> tensor;

    /**
     * A serialized SummaryMetadata proto. Contains plugin
     * data.
     */
    public final Operand<TString> serializedSummaryMetadata;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new TensorSummary(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      tag = (Operand<TString>) op.input(inputIndex++);
      tensor = (Operand<? extends TType>) op.input(inputIndex++);
      serializedSummaryMetadata = (Operand<TString>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
