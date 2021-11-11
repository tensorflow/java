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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Add the quantile summaries to each quantile stream resource.
 * An op that adds a list of quantile summaries to a quantile stream resource. Each
 * summary Tensor is rank 2, containing summaries (value, weight, min_rank, max_rank)
 * for a single feature.
 */
@OpMetadata(
    opType = BoostedTreesQuantileStreamResourceAddSummaries.OP_NAME,
    inputsClass = BoostedTreesQuantileStreamResourceAddSummaries.Inputs.class
)
public final class BoostedTreesQuantileStreamResourceAddSummaries extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesQuantileStreamResourceAddSummaries";

  public BoostedTreesQuantileStreamResourceAddSummaries(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesQuantileStreamResourceAddSummaries operation.
   *
   * @param scope current scope
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param summaries string; List of Rank 2 Tensor each containing the summaries for a single feature.
   * @return a new instance of BoostedTreesQuantileStreamResourceAddSummaries
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesQuantileStreamResourceAddSummaries create(Scope scope,
      Operand<? extends TType> quantileStreamResourceHandle,
      Iterable<Operand<TFloat32>> summaries) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesQuantileStreamResourceAddSummaries");
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    opBuilder.addInputList(Operands.asOutputs(summaries));
    return new BoostedTreesQuantileStreamResourceAddSummaries(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesQuantileStreamResourceAddSummaries.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesQuantileStreamResourceAddSummaries> {
    /**
     * resource handle referring to a QuantileStreamResource.
     */
    public final Operand<? extends TType> quantileStreamResourceHandle;

    /**
     * string; List of Rank 2 Tensor each containing the summaries for a single feature.
     */
    public final Iterable<Operand<TFloat32>> summaries;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesQuantileStreamResourceAddSummaries(op), op, Arrays.asList());
      int inputIndex = 0;
      quantileStreamResourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
      int summariesLength = op.inputListLength("summaries");
      summaries = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, summariesLength));
      inputIndex += summariesLength;
    }
  }
}
