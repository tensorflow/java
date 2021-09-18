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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * Set a summary_writer_interface to record statistics using given stats_aggregator.
 */
public final class StatsAggregatorSetSummaryWriter extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatsAggregatorSetSummaryWriter";

  private StatsAggregatorSetSummaryWriter(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new StatsAggregatorSetSummaryWriter operation.
   *
   * @param scope current scope
   * @param statsAggregator the statsAggregator value
   * @param summary the summary value
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
}
