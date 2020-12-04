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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Produces a summary of any statistics recorded by the given statistics manager.
 */
public final class StatsAggregatorSummary extends RawOp implements Operand<TString> {
  
  /**
   * Factory method to create a class wrapping a new StatsAggregatorSummary operation.
   * 
   * @param scope current scope
   * @param iterator 
   * @return a new instance of StatsAggregatorSummary
   */
  @Endpoint(describeByClass = true)
  public static StatsAggregatorSummary create(Scope scope, Operand<?> iterator) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalStatsAggregatorSummary", scope.makeOpName("StatsAggregatorSummary"));
    opBuilder.addInput(iterator.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new StatsAggregatorSummary(opBuilder.build());
  }
  
  /**
   */
  public Output<TString> summary() {
    return summary;
  }
  
  @Override
  public Output<TString> asOutput() {
    return summary;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ExperimentalStatsAggregatorSummary";
  
  private Output<TString> summary;
  
  private StatsAggregatorSummary(Operation operation) {
    super(operation);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }
}
