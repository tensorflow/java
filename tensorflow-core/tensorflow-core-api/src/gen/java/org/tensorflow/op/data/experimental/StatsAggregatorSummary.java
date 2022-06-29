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

package org.tensorflow.op.data.experimental;

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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Produces a summary of any statistics recorded by the given statistics manager.
 */
@OpMetadata(
    opType = StatsAggregatorSummary.OP_NAME,
    inputsClass = StatsAggregatorSummary.Inputs.class
)
public final class StatsAggregatorSummary extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalStatsAggregatorSummary";

  private Output<TString> summary;

  public StatsAggregatorSummary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalStatsAggregatorSummary operation.
   *
   * @param scope current scope
   * @param iterator The iterator value
   * @return a new instance of StatsAggregatorSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatsAggregatorSummary create(Scope scope, Operand<? extends TType> iterator) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatsAggregatorSummary");
    opBuilder.addInput(iterator.asOutput());
    return new StatsAggregatorSummary(opBuilder.build());
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
      outputsClass = StatsAggregatorSummary.class
  )
  public static class Inputs extends RawOpInputs<StatsAggregatorSummary> {
    /**
     * The iterator input
     */
    public final Operand<? extends TType> iterator;

    public Inputs(GraphOperation op) {
      super(new StatsAggregatorSummary(op), op, Arrays.asList());
      int inputIndex = 0;
      iterator = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
