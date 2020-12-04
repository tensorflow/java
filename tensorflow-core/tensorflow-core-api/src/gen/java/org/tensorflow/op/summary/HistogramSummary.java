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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Outputs a `Summary` protocol buffer with a histogram.
 * <p>
 * The generated
 * [`Summary`](https://www.tensorflow.org/code/tensorflow/core/framework/summary.proto)
 * has one summary value containing a histogram for `values`.
 * <p>
 * This op reports an `InvalidArgument` error if any value is not finite.
 */
@Operator(group = "summary")
public final class HistogramSummary extends RawOp implements Operand<TString> {
  
  /**
   * Factory method to create a class wrapping a new HistogramSummary operation.
   * 
   * @param scope current scope
   * @param tag Scalar.  Tag to use for the `Summary.Value`.
   * @param values Any shape. Values to use to build the histogram.
   * @return a new instance of HistogramSummary
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> HistogramSummary create(Scope scope, Operand<TString> tag, Operand<T> values) {
    OperationBuilder opBuilder = scope.env().opBuilder("HistogramSummary", scope.makeOpName("HistogramSummary"));
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new HistogramSummary(opBuilder.build());
  }
  
  /**
   * Scalar. Serialized `Summary` protocol buffer.
   */
  public Output<TString> summary() {
    return summary;
  }
  
  @Override
  public Output<TString> asOutput() {
    return summary;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "HistogramSummary";
  
  private Output<TString> summary;
  
  private HistogramSummary(Operation operation) {
    super(operation);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }
}
