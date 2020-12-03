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

package org.tensorflow.op.estimator;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;

/**
 * Add the quantile summaries to each quantile stream resource.
 * <p>
 * An op that adds a list of quantile summaries to a quantile stream resource. Each
 * summary Tensor is rank 2, containing summaries (value, weight, min_rank, max_rank)
 * for a single feature.
 */
public final class BoostedTreesQuantileStreamResourceAddSummaries extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new BoostedTreesQuantileStreamResourceAddSummaries operation.
   * 
   * @param scope current scope
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param summaries string; List of Rank 2 Tensor each containing the summaries for a single feature.
   * @return a new instance of BoostedTreesQuantileStreamResourceAddSummaries
   */
  @Endpoint(describeByClass = true)
  public static BoostedTreesQuantileStreamResourceAddSummaries create(Scope scope, Operand<?> quantileStreamResourceHandle, Iterable<Operand<TFloat32>> summaries) {
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesQuantileStreamResourceAddSummaries", scope.makeOpName("BoostedTreesQuantileStreamResourceAddSummaries"));
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    opBuilder.addInputList(Operands.asOutputs(summaries));
    opBuilder = scope.apply(opBuilder);
    return new BoostedTreesQuantileStreamResourceAddSummaries(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BoostedTreesQuantileStreamResourceAddSummaries";
  
  private BoostedTreesQuantileStreamResourceAddSummaries(Operation operation) {
    super(operation);
  }
}
