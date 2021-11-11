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
import org.tensorflow.types.family.TNumber;

/**
 * Outputs a {@code Summary} protocol buffer with scalar values.
 * The input {@code tags} and {@code values} must have the same shape.  The generated summary
 * has a summary value for each tag-value pair in {@code tags} and {@code values}.
 */
@OpMetadata(
    opType = ScalarSummary.OP_NAME,
    inputsClass = ScalarSummary.Inputs.class
)
@Operator(
    group = "summary"
)
public final class ScalarSummary extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ScalarSummary";

  private Output<TString> summary;

  public ScalarSummary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ScalarSummary operation.
   *
   * @param scope current scope
   * @param tags Tags for the summary.
   * @param values Same shape as `tags.  Values for the summary.
   * @return a new instance of ScalarSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static ScalarSummary create(Scope scope, Operand<TString> tags,
      Operand<? extends TNumber> values) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ScalarSummary");
    opBuilder.addInput(tags.asOutput());
    opBuilder.addInput(values.asOutput());
    return new ScalarSummary(opBuilder.build());
  }

  /**
   * Gets summary.
   * Scalar.  Serialized {@code Summary} protocol buffer.
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
      outputsClass = ScalarSummary.class
  )
  public static class Inputs extends RawOpInputs<ScalarSummary> {
    /**
     * Tags for the summary.
     */
    public final Operand<TString> tags;

    /**
     * Same shape as `tags.  Values for the summary.
     */
    public final Operand<? extends TNumber> values;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new ScalarSummary(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      tags = (Operand<TString>) op.input(inputIndex++);
      values = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
