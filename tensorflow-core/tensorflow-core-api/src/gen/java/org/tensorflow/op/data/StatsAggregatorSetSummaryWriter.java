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

package org.tensorflow.op.data;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.family.TType;

/**
 * Set a summary_writer_interface to record statistics using given stats_aggregator.
 */
@OpMetadata(
    opType = StatsAggregatorSetSummaryWriter.OP_NAME,
    inputsClass = StatsAggregatorSetSummaryWriter.Inputs.class
)
public final class StatsAggregatorSetSummaryWriter extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatsAggregatorSetSummaryWriter";

  public StatsAggregatorSetSummaryWriter(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new StatsAggregatorSetSummaryWriter operation.
   *
   * @param scope current scope
   * @param statsAggregator The statsAggregator value
   * @param summary The summary value
   * @return a new instance of StatsAggregatorSetSummaryWriter
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatsAggregatorSetSummaryWriter create(Scope scope,
      Operand<? extends TType> statsAggregator, Operand<? extends TType> summary) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatsAggregatorSetSummaryWriter");
    opBuilder.addInput(statsAggregator.asOutput());
    opBuilder.addInput(summary.asOutput());
    return new StatsAggregatorSetSummaryWriter(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = StatsAggregatorSetSummaryWriter.class
  )
  public static class Inputs extends RawOpInputs<StatsAggregatorSetSummaryWriter> {
    /**
     * The statsAggregator input
     */
    public final Operand<? extends TType> statsAggregator;

    /**
     * The summary input
     */
    public final Operand<? extends TType> summary;

    public Inputs(GraphOperation op) {
      super(new StatsAggregatorSetSummaryWriter(op), op, Arrays.asList());
      int inputIndex = 0;
      statsAggregator = (Operand<? extends TType>) op.input(inputIndex++);
      summary = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
