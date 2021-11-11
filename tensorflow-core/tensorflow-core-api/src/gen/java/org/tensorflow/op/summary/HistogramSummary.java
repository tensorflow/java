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
 * Outputs a {@code Summary} protocol buffer with a histogram.
 * The generated
 *  <a href="https://www.tensorflow.org/code/tensorflow/core/framework/summary.proto">{@code Summary}</a> 
 * has one summary value containing a histogram for {@code values}.
 * <p>This op reports an {@code InvalidArgument} error if any value is not finite.
 */
@OpMetadata(
    opType = HistogramSummary.OP_NAME,
    inputsClass = HistogramSummary.Inputs.class
)
@Operator(
    group = "summary"
)
public final class HistogramSummary extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "HistogramSummary";

  private Output<TString> summary;

  public HistogramSummary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new HistogramSummary operation.
   *
   * @param scope current scope
   * @param tag Scalar.  Tag to use for the {@code Summary.Value}.
   * @param values Any shape. Values to use to build the histogram.
   * @return a new instance of HistogramSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static HistogramSummary create(Scope scope, Operand<TString> tag,
      Operand<? extends TNumber> values) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "HistogramSummary");
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(values.asOutput());
    return new HistogramSummary(opBuilder.build());
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
      outputsClass = HistogramSummary.class
  )
  public static class Inputs extends RawOpInputs<HistogramSummary> {
    /**
     * Scalar.  Tag to use for the {@code Summary.Value}.
     */
    public final Operand<TString> tag;

    /**
     * Any shape. Values to use to build the histogram.
     */
    public final Operand<? extends TNumber> values;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new HistogramSummary(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      tag = (Operand<TString>) op.input(inputIndex++);
      values = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
