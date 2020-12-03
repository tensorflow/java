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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Aggregates the summary of accumulated stats for the batch.
 * <p>
 * The summary stats contains gradients and hessians accumulated for each node, bucket and dimension id.
 */
public final class BoostedTreesSparseAggregateStats extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new BoostedTreesSparseAggregateStats operation.
   * 
   * @param scope current scope
   * @param nodeIds int32; Rank 1 Tensor containing node ids for each example, shape [batch_size].
   * @param gradients float32; Rank 2 Tensor (shape=[batch_size, logits_dimension]) with gradients for each example.
   * @param hessians float32; Rank 2 Tensor (shape=[batch_size, hessian_dimension]) with hessians for each example.
   * @param featureIndices int32; Rank 2 indices of feature sparse Tensors (shape=[number of sparse entries, 2]).
   * Number of sparse entries across all instances from the batch. The first value is
   * the index of the instance, the second is dimension of the feature. The second axis
   * can only have 2 values, i.e., the input dense version of Tensor can only be matrix.
   * @param featureValues int32; Rank 1 values of feature sparse Tensors (shape=[number of sparse entries]).
   * Number of sparse entries across all instances from the batch. The first value is
   * the index of the instance, the second is dimension of the feature.
   * @param featureShape int32; Rank 1 dense shape of feature sparse Tensors (shape=[2]).
   * The first axis can only have 2 values, [batch_size, feature_dimension].
   * @param maxSplits int; the maximum number of splits possible in the whole tree.
   * @param numBuckets int; equals to the maximum possible value of bucketized feature + 1.
   * @return a new instance of BoostedTreesSparseAggregateStats
   */
  @Endpoint(describeByClass = true)
  public static BoostedTreesSparseAggregateStats create(Scope scope, Operand<TInt32> nodeIds, Operand<TFloat32> gradients, Operand<TFloat32> hessians, Operand<TInt32> featureIndices, Operand<TInt32> featureValues, Operand<TInt32> featureShape, Long maxSplits, Long numBuckets) {
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesSparseAggregateStats", scope.makeOpName("BoostedTreesSparseAggregateStats"));
    opBuilder.addInput(nodeIds.asOutput());
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(hessians.asOutput());
    opBuilder.addInput(featureIndices.asOutput());
    opBuilder.addInput(featureValues.asOutput());
    opBuilder.addInput(featureShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("max_splits", maxSplits);
    opBuilder.setAttr("num_buckets", numBuckets);
    return new BoostedTreesSparseAggregateStats(opBuilder.build());
  }
  
  /**
   * int32; Rank 2 indices of summary sparse Tensors (shape=[number of non zero statistics, 4])
   * The second axis can only be 4 including node id, feature dimension, bucket id, and statistics_dimension.
   * statistics_dimension = logits_dimension + hessian_dimension.
   */
  public Output<TInt32> statsSummaryIndices() {
    return statsSummaryIndices;
  }
  
  /**
   * output Rank 1 Tensor (shape=[number of non zero statistics])
   */
  public Output<TFloat32> statsSummaryValues() {
    return statsSummaryValues;
  }
  
  /**
   * output Rank 1 Tensor (shape=[4])
   * The tensor has following 4 values: [max_splits, feature_dimension, num_buckets, statistics_dimension],
   * where statistics_dimension = gradient_dimension + hessian_dimension. gradient_dimension
   * is the same as label_dimension, i.e., the output space. hessian_dimension can be the same
   * as logits dimension when diagonal hessian is used, or label_dimension^2 when full
   * hessian is used.
   */
  public Output<TInt32> statsSummaryShape() {
    return statsSummaryShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BoostedTreesSparseAggregateStats";
  
  private Output<TInt32> statsSummaryIndices;
  private Output<TFloat32> statsSummaryValues;
  private Output<TInt32> statsSummaryShape;
  
  private BoostedTreesSparseAggregateStats(Operation operation) {
    super(operation);
    int outputIdx = 0;
    statsSummaryIndices = operation.output(outputIdx++);
    statsSummaryValues = operation.output(outputIdx++);
    statsSummaryShape = operation.output(outputIdx++);
  }
}
