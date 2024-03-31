/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Makes the summary of accumulated stats for the batch.
 * The summary stats contains gradients and hessians accumulated into the corresponding node and bucket for each example.
 */
@OpMetadata(
    opType = BoostedTreesMakeStatsSummary.OP_NAME,
    inputsClass = BoostedTreesMakeStatsSummary.Inputs.class
)
@Operator(
    group = "estimator"
)
public final class BoostedTreesMakeStatsSummary extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesMakeStatsSummary";

  private Output<TFloat32> statsSummary;

  public BoostedTreesMakeStatsSummary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    statsSummary = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesMakeStatsSummary operation.
   *
   * @param scope current scope
   * @param nodeIds int32 Rank 1 Tensor containing node ids, which each example falls into for the requested layer.
   * @param gradients float32; Rank 2 Tensor (shape=[#examples, 1]) for gradients.
   * @param hessians float32; Rank 2 Tensor (shape=[#examples, 1]) for hessians.
   * @param bucketizedFeaturesList int32 list of Rank 1 Tensors, each containing the bucketized feature (for each feature column).
   * @param maxSplits int; the maximum number of splits possible in the whole tree.
   * @param numBuckets int; equals to the maximum possible value of bucketized feature.
   * @return a new instance of BoostedTreesMakeStatsSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesMakeStatsSummary create(Scope scope, Operand<TInt32> nodeIds,
      Operand<TFloat32> gradients, Operand<TFloat32> hessians,
      Iterable<Operand<TInt32>> bucketizedFeaturesList, Long maxSplits, Long numBuckets) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesMakeStatsSummary");
    opBuilder.addInput(nodeIds.asOutput());
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(hessians.asOutput());
    opBuilder.addInputList(Operands.asOutputs(bucketizedFeaturesList));
    opBuilder.setAttr("max_splits", maxSplits);
    opBuilder.setAttr("num_buckets", numBuckets);
    return new BoostedTreesMakeStatsSummary(opBuilder.build());
  }

  /**
   * Gets statsSummary.
   * output Rank 4 Tensor (shape=[#features, #splits, #buckets, 2]) containing accumulated stats put into the corresponding node and bucket. The first index of 4th dimension refers to gradients, and the second to hessians.
   * @return statsSummary.
   */
  public Output<TFloat32> statsSummary() {
    return statsSummary;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return statsSummary;
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesMakeStatsSummary.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesMakeStatsSummary> {
    /**
     * int32 Rank 1 Tensor containing node ids, which each example falls into for the requested layer.
     */
    public final Operand<TInt32> nodeIds;

    /**
     * float32; Rank 2 Tensor (shape=[#examples, 1]) for gradients.
     */
    public final Operand<TFloat32> gradients;

    /**
     * float32; Rank 2 Tensor (shape=[#examples, 1]) for hessians.
     */
    public final Operand<TFloat32> hessians;

    /**
     * int32 list of Rank 1 Tensors, each containing the bucketized feature (for each feature column).
     */
    public final Iterable<Operand<TInt32>> bucketizedFeaturesList;

    /**
     * int; the maximum number of splits possible in the whole tree.
     */
    public final long maxSplits;

    /**
     * int; equals to the maximum possible value of bucketized feature.
     */
    public final long numBuckets;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesMakeStatsSummary(op), op, Arrays.asList("max_splits", "num_buckets"));
      int inputIndex = 0;
      nodeIds = (Operand<TInt32>) op.input(inputIndex++);
      gradients = (Operand<TFloat32>) op.input(inputIndex++);
      hessians = (Operand<TFloat32>) op.input(inputIndex++);
      int bucketizedFeaturesListLength = op.inputListLength("bucketized_features_list");
      bucketizedFeaturesList = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, bucketizedFeaturesListLength));
      inputIndex += bucketizedFeaturesListLength;
      maxSplits = op.attributes().getAttrInt("max_splits");
      numBuckets = op.attributes().getAttrInt("num_buckets");
    }
  }
}
