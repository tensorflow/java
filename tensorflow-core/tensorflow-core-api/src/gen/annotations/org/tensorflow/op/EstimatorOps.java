// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.estimator.BoostedTreesAggregateStats;
import org.tensorflow.op.estimator.BoostedTreesBucketize;
import org.tensorflow.op.estimator.BoostedTreesCalculateBestFeatureSplit;
import org.tensorflow.op.estimator.BoostedTreesCalculateBestFeatureSplitV2;
import org.tensorflow.op.estimator.BoostedTreesCalculateBestGainsPerFeature;
import org.tensorflow.op.estimator.BoostedTreesCenterBias;
import org.tensorflow.op.estimator.BoostedTreesCreateEnsemble;
import org.tensorflow.op.estimator.BoostedTreesCreateQuantileStreamResource;
import org.tensorflow.op.estimator.BoostedTreesDeserializeEnsemble;
import org.tensorflow.op.estimator.BoostedTreesEnsembleResourceHandleOp;
import org.tensorflow.op.estimator.BoostedTreesExampleDebugOutputs;
import org.tensorflow.op.estimator.BoostedTreesFlushQuantileSummaries;
import org.tensorflow.op.estimator.BoostedTreesGetEnsembleStates;
import org.tensorflow.op.estimator.BoostedTreesMakeQuantileSummaries;
import org.tensorflow.op.estimator.BoostedTreesMakeStatsSummary;
import org.tensorflow.op.estimator.BoostedTreesPredict;
import org.tensorflow.op.estimator.BoostedTreesQuantileStreamResourceAddSummaries;
import org.tensorflow.op.estimator.BoostedTreesQuantileStreamResourceDeserialize;
import org.tensorflow.op.estimator.BoostedTreesQuantileStreamResourceFlush;
import org.tensorflow.op.estimator.BoostedTreesQuantileStreamResourceGetBucketBoundaries;
import org.tensorflow.op.estimator.BoostedTreesQuantileStreamResourceHandleOp;
import org.tensorflow.op.estimator.BoostedTreesSerializeEnsemble;
import org.tensorflow.op.estimator.BoostedTreesSparseAggregateStats;
import org.tensorflow.op.estimator.BoostedTreesSparseCalculateBestFeatureSplit;
import org.tensorflow.op.estimator.BoostedTreesTrainingPredict;
import org.tensorflow.op.estimator.BoostedTreesUpdateEnsemble;
import org.tensorflow.op.estimator.BoostedTreesUpdateEnsembleV2;
import org.tensorflow.op.estimator.IsBoostedTreesEnsembleInitialized;
import org.tensorflow.op.estimator.IsBoostedTreesQuantileStreamResourceInitialized;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code estimator} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class EstimatorOps {
  private final Scope scope;

  private final Ops ops;

  EstimatorOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Aggregates the summary of accumulated stats for the batch.
   *  The summary stats contains gradients and hessians accumulated for each node, feature dimension id and bucket.
   *
   * @param nodeIds int32; Rank 1 Tensor containing node ids for each example, shape [batch_size].
   * @param gradients float32; Rank 2 Tensor (shape=[batch_size, logits_dimension]) with gradients for each example.
   * @param hessians float32; Rank 2 Tensor (shape=[batch_size, hessian_dimension]) with hessians for each example.
   * @param feature int32; Rank 2 feature Tensors (shape=[batch_size, feature_dimension]).
   * @param maxSplits int; the maximum number of splits possible in the whole tree.
   * @param numBuckets int; equals to the maximum possible value of bucketized feature.
   * @return a new instance of BoostedTreesAggregateStats
   */
  public BoostedTreesAggregateStats boostedTreesAggregateStats(Operand<TInt32> nodeIds,
      Operand<TFloat32> gradients, Operand<TFloat32> hessians, Operand<TInt32> feature,
      Long maxSplits, Long numBuckets) {
    return BoostedTreesAggregateStats.create(scope, nodeIds, gradients, hessians, feature, maxSplits, numBuckets);
  }

  /**
   * Bucketize each feature based on bucket boundaries.
   *  An op that returns a list of float tensors, where each tensor represents the
   *  bucketized values for a single feature.
   *
   * @param floatValues float; List of Rank 1 Tensor each containing float values for a single feature.
   * @param bucketBoundaries float; List of Rank 1 Tensors each containing the bucket boundaries for a single
   *  feature.
   * @return a new instance of BoostedTreesBucketize
   */
  public BoostedTreesBucketize boostedTreesBucketize(Iterable<Operand<TFloat32>> floatValues,
      Iterable<Operand<TFloat32>> bucketBoundaries) {
    return BoostedTreesBucketize.create(scope, floatValues, bucketBoundaries);
  }

  /**
   * Calculates gains for each feature and returns the best possible split information for the feature.
   *  The split information is the best threshold (bucket id), gains and left/right node contributions per node for each feature.
   *  <p>It is possible that not all nodes can be split on each feature. Hence, the list of possible nodes can differ between the features. Therefore, we return {@code node_ids_list} for each feature, containing the list of nodes that this feature can be used to split.
   *  <p>In this manner, the output is the best split per features and per node, so that it needs to be combined later to produce the best split for each node (among all possible features).
   *  <p>The output shapes are compatible in a way that the first dimension of all tensors are the same and equal to the number of possible split nodes for each feature.
   *
   * @param nodeIdRange A Rank 1 tensor (shape=[2]) to specify the range [first, last) of node ids to process within {@code stats_summary_list}. The nodes are iterated between the two nodes specified by the tensor, as like {@code for node_id in range(node_id_range[0], node_id_range[1])} (Note that the last index node_id_range[1] is exclusive).
   * @param statsSummary A Rank 4 tensor (#shape=[max_splits, feature_dims, bucket, stats_dims]) for accumulated stats summary (gradient/hessian) per node, per dimension, per buckets for each feature.
   *  The first dimension of the tensor is the maximum number of splits, and thus not all elements of it will be used, but only the indexes specified by node_ids will be used.
   * @param l1 l1 regularization factor on leaf weights, per instance based.
   * @param l2 l2 regularization factor on leaf weights, per instance based.
   * @param treeComplexity adjustment to the gain, per leaf based.
   * @param minNodeWeight minimum avg of hessians in a node before required for the node to be considered for splitting.
   * @param logitsDimension The dimension of logit, i.e., number of classes.
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesCalculateBestFeatureSplit
   */
  public BoostedTreesCalculateBestFeatureSplit boostedTreesCalculateBestFeatureSplit(
      Operand<TInt32> nodeIdRange, Operand<TFloat32> statsSummary, Operand<TFloat32> l1,
      Operand<TFloat32> l2, Operand<TFloat32> treeComplexity, Operand<TFloat32> minNodeWeight,
      Long logitsDimension, BoostedTreesCalculateBestFeatureSplit.Options... options) {
    return BoostedTreesCalculateBestFeatureSplit.create(scope, nodeIdRange, statsSummary, l1, l2, treeComplexity, minNodeWeight, logitsDimension, options);
  }

  /**
   * Calculates gains for each feature and returns the best possible split information for each node. However, if no split is found, then no split information is returned for that node.
   *  The split information is the best threshold (bucket id), gains and left/right node contributions per node for each feature.
   *  <p>It is possible that not all nodes can be split on each feature. Hence, the list of possible nodes can differ between the features. Therefore, we return {@code node_ids_list} for each feature, containing the list of nodes that this feature can be used to split.
   *  <p>In this manner, the output is the best split per features and per node, so that it needs to be combined later to produce the best split for each node (among all possible features).
   *  <p>The output shapes are compatible in a way that the first dimension of all tensors are the same and equal to the number of possible split nodes for each feature.
   *
   * @param nodeIdRange A Rank 1 tensor (shape=[2]) to specify the range [first, last) of node ids to process within {@code stats_summary_list}. The nodes are iterated between the two nodes specified by the tensor, as like {@code for node_id in range(node_id_range[0], node_id_range[1])} (Note that the last index node_id_range[1] is exclusive).
   * @param statsSummariesList A list of Rank 4 tensor (#shape=[max_splits, feature_dims, bucket, stats_dims]) for accumulated stats summary (gradient/hessian) per node, per dimension, per buckets for each feature.
   *  The first dimension of the tensor is the maximum number of splits, and thus not all elements of it will be used, but only the indexes specified by node_ids will be used.
   * @param splitTypes A Rank 1 tensor indicating if this Op should perform inequality split or equality split per feature.
   * @param candidateFeatureIds Rank 1 tensor with ids for each feature. This is the real id of the feature.
   * @param l1 l1 regularization factor on leaf weights, per instance based.
   * @param l2 l2 regularization factor on leaf weights, per instance based.
   * @param treeComplexity adjustment to the gain, per leaf based.
   * @param minNodeWeight minimum avg of hessians in a node before required for the node to be considered for splitting.
   * @param logitsDimension The dimension of logit, i.e., number of classes.
   * @return a new instance of BoostedTreesCalculateBestFeatureSplitV2
   */
  public BoostedTreesCalculateBestFeatureSplitV2 boostedTreesCalculateBestFeatureSplitV2(
      Operand<TInt32> nodeIdRange, Iterable<Operand<TFloat32>> statsSummariesList,
      Operand<TString> splitTypes, Operand<TInt32> candidateFeatureIds, Operand<TFloat32> l1,
      Operand<TFloat32> l2, Operand<TFloat32> treeComplexity, Operand<TFloat32> minNodeWeight,
      Long logitsDimension) {
    return BoostedTreesCalculateBestFeatureSplitV2.create(scope, nodeIdRange, statsSummariesList, splitTypes, candidateFeatureIds, l1, l2, treeComplexity, minNodeWeight, logitsDimension);
  }

  /**
   * Calculates gains for each feature and returns the best possible split information for the feature.
   *  The split information is the best threshold (bucket id), gains and left/right node contributions per node for each feature.
   *  <p>It is possible that not all nodes can be split on each feature. Hence, the list of possible nodes can differ between the features. Therefore, we return {@code node_ids_list} for each feature, containing the list of nodes that this feature can be used to split.
   *  <p>In this manner, the output is the best split per features and per node, so that it needs to be combined later to produce the best split for each node (among all possible features).
   *  <p>The length of output lists are all of the same length, {@code num_features}.
   *  The output shapes are compatible in a way that the first dimension of all tensors of all lists are the same and equal to the number of possible split nodes for each feature.
   *
   * @param nodeIdRange A Rank 1 tensor (shape=[2]) to specify the range [first, last) of node ids to process within {@code stats_summary_list}. The nodes are iterated between the two nodes specified by the tensor, as like {@code for node_id in range(node_id_range[0], node_id_range[1])} (Note that the last index node_id_range[1] is exclusive).
   * @param statsSummaryList A list of Rank 3 tensor (#shape=[max_splits, bucket, 2]) for accumulated stats summary (gradient/hessian) per node per buckets for each feature. The first dimension of the tensor is the maximum number of splits, and thus not all elements of it will be used, but only the indexes specified by node_ids will be used.
   * @param l1 l1 regularization factor on leaf weights, per instance based.
   * @param l2 l2 regularization factor on leaf weights, per instance based.
   * @param treeComplexity adjustment to the gain, per leaf based.
   * @param minNodeWeight minimum avg of hessians in a node before required for the node to be considered for splitting.
   * @param maxSplits the number of nodes that can be split in the whole tree. Used as a dimension of output tensors.
   * @return a new instance of BoostedTreesCalculateBestGainsPerFeature
   */
  public BoostedTreesCalculateBestGainsPerFeature boostedTreesCalculateBestGainsPerFeature(
      Operand<TInt32> nodeIdRange, Iterable<Operand<TFloat32>> statsSummaryList,
      Operand<TFloat32> l1, Operand<TFloat32> l2, Operand<TFloat32> treeComplexity,
      Operand<TFloat32> minNodeWeight, Long maxSplits) {
    return BoostedTreesCalculateBestGainsPerFeature.create(scope, nodeIdRange, statsSummaryList, l1, l2, treeComplexity, minNodeWeight, maxSplits);
  }

  /**
   * Calculates the prior from the training data (the bias) and fills in the first node with the logits' prior. Returns a boolean indicating whether to continue centering.
   *
   * @param treeEnsembleHandle Handle to the tree ensemble.
   * @param meanGradients A tensor with shape=[logits_dimension] with mean of gradients for a first node.
   * @param meanHessians A tensor with shape=[logits_dimension] mean of hessians for a first node.
   * @param l1 l1 regularization factor on leaf weights, per instance based.
   * @param l2 l2 regularization factor on leaf weights, per instance based.
   * @return a new instance of BoostedTreesCenterBias
   */
  public BoostedTreesCenterBias boostedTreesCenterBias(Operand<? extends TType> treeEnsembleHandle,
      Operand<TFloat32> meanGradients, Operand<TFloat32> meanHessians, Operand<TFloat32> l1,
      Operand<TFloat32> l2) {
    return BoostedTreesCenterBias.create(scope, treeEnsembleHandle, meanGradients, meanHessians, l1, l2);
  }

  /**
   * Creates a tree ensemble model and returns a handle to it.
   *
   * @param treeEnsembleHandle Handle to the tree ensemble resource to be created.
   * @param stampToken Token to use as the initial value of the resource stamp.
   * @param treeEnsembleSerialized Serialized proto of the tree ensemble.
   * @return a new instance of BoostedTreesCreateEnsemble
   */
  public BoostedTreesCreateEnsemble boostedTreesCreateEnsemble(
      Operand<? extends TType> treeEnsembleHandle, Operand<TInt64> stampToken,
      Operand<TString> treeEnsembleSerialized) {
    return BoostedTreesCreateEnsemble.create(scope, treeEnsembleHandle, stampToken, treeEnsembleSerialized);
  }

  /**
   * Create the Resource for Quantile Streams.
   *
   * @param quantileStreamResourceHandle resource; Handle to quantile stream resource.
   * @param epsilon float; The required approximation error of the stream resource.
   * @param numStreams int; The number of streams managed by the resource that shares the same epsilon.
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesCreateQuantileStreamResource
   */
  public BoostedTreesCreateQuantileStreamResource boostedTreesCreateQuantileStreamResource(
      Operand<? extends TType> quantileStreamResourceHandle, Operand<TFloat32> epsilon,
      Operand<TInt64> numStreams, BoostedTreesCreateQuantileStreamResource.Options... options) {
    return BoostedTreesCreateQuantileStreamResource.create(scope, quantileStreamResourceHandle, epsilon, numStreams, options);
  }

  /**
   * Deserializes a serialized tree ensemble config and replaces current tree
   *  ensemble.
   *
   * @param treeEnsembleHandle Handle to the tree ensemble.
   * @param stampToken Token to use as the new value of the resource stamp.
   * @param treeEnsembleSerialized Serialized proto of the ensemble.
   * @return a new instance of BoostedTreesDeserializeEnsemble
   */
  public BoostedTreesDeserializeEnsemble boostedTreesDeserializeEnsemble(
      Operand<? extends TType> treeEnsembleHandle, Operand<TInt64> stampToken,
      Operand<TString> treeEnsembleSerialized) {
    return BoostedTreesDeserializeEnsemble.create(scope, treeEnsembleHandle, stampToken, treeEnsembleSerialized);
  }

  /**
   * Creates a handle to a BoostedTreesEnsembleResource
   *
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesEnsembleResourceHandleOp
   */
  public BoostedTreesEnsembleResourceHandleOp boostedTreesEnsembleResourceHandleOp(
      BoostedTreesEnsembleResourceHandleOp.Options... options) {
    return BoostedTreesEnsembleResourceHandleOp.create(scope, options);
  }

  /**
   * Debugging/model interpretability outputs for each example.
   *  It traverses all the trees and computes debug metrics for individual examples,
   *  such as getting split feature ids and logits after each split along the decision
   *  path used to compute directional feature contributions.
   *
   * @param treeEnsembleHandle The treeEnsembleHandle value
   * @param bucketizedFeatures A list of rank 1 Tensors containing bucket id for each
   *  feature.
   * @param logitsDimension scalar, dimension of the logits, to be used for constructing the protos in
   *  examples_debug_outputs_serialized.
   * @return a new instance of BoostedTreesExampleDebugOutputs
   */
  public BoostedTreesExampleDebugOutputs boostedTreesExampleDebugOutputs(
      Operand<? extends TType> treeEnsembleHandle, Iterable<Operand<TInt32>> bucketizedFeatures,
      Long logitsDimension) {
    return BoostedTreesExampleDebugOutputs.create(scope, treeEnsembleHandle, bucketizedFeatures, logitsDimension);
  }

  /**
   * Flush the quantile summaries from each quantile stream resource.
   *  An op that outputs a list of quantile summaries of a quantile stream resource.
   *  Each summary Tensor is rank 2, containing summaries (value, weight, min_rank,
   *  max_rank) for a single feature.
   *
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param numFeatures The value of the numFeatures attribute
   * @return a new instance of BoostedTreesFlushQuantileSummaries
   */
  public BoostedTreesFlushQuantileSummaries boostedTreesFlushQuantileSummaries(
      Operand<? extends TType> quantileStreamResourceHandle, Long numFeatures) {
    return BoostedTreesFlushQuantileSummaries.create(scope, quantileStreamResourceHandle, numFeatures);
  }

  /**
   * Retrieves the tree ensemble resource stamp token, number of trees and growing statistics.
   *
   * @param treeEnsembleHandle Handle to the tree ensemble.
   * @return a new instance of BoostedTreesGetEnsembleStates
   */
  public BoostedTreesGetEnsembleStates boostedTreesGetEnsembleStates(
      Operand<? extends TType> treeEnsembleHandle) {
    return BoostedTreesGetEnsembleStates.create(scope, treeEnsembleHandle);
  }

  /**
   * Makes the summary of quantiles for the batch.
   *  An op that takes a list of tensors (one tensor per feature) and outputs the
   *  quantile summaries for each tensor.
   *
   * @param floatValues float; List of Rank 1 Tensors each containing values for a single feature.
   * @param exampleWeights float; Rank 1 Tensor with weights per instance.
   * @param epsilon float; The required maximum approximation error.
   * @return a new instance of BoostedTreesMakeQuantileSummaries
   */
  public BoostedTreesMakeQuantileSummaries boostedTreesMakeQuantileSummaries(
      Iterable<Operand<TFloat32>> floatValues, Operand<TFloat32> exampleWeights,
      Operand<TFloat32> epsilon) {
    return BoostedTreesMakeQuantileSummaries.create(scope, floatValues, exampleWeights, epsilon);
  }

  /**
   * Makes the summary of accumulated stats for the batch.
   *  The summary stats contains gradients and hessians accumulated into the corresponding node and bucket for each example.
   *
   * @param nodeIds int32 Rank 1 Tensor containing node ids, which each example falls into for the requested layer.
   * @param gradients float32; Rank 2 Tensor (shape=[#examples, 1]) for gradients.
   * @param hessians float32; Rank 2 Tensor (shape=[#examples, 1]) for hessians.
   * @param bucketizedFeaturesList int32 list of Rank 1 Tensors, each containing the bucketized feature (for each feature column).
   * @param maxSplits int; the maximum number of splits possible in the whole tree.
   * @param numBuckets int; equals to the maximum possible value of bucketized feature.
   * @return a new instance of BoostedTreesMakeStatsSummary
   */
  public BoostedTreesMakeStatsSummary boostedTreesMakeStatsSummary(Operand<TInt32> nodeIds,
      Operand<TFloat32> gradients, Operand<TFloat32> hessians,
      Iterable<Operand<TInt32>> bucketizedFeaturesList, Long maxSplits, Long numBuckets) {
    return BoostedTreesMakeStatsSummary.create(scope, nodeIds, gradients, hessians, bucketizedFeaturesList, maxSplits, numBuckets);
  }

  /**
   * Runs multiple additive regression ensemble predictors on input instances and
   *  computes the logits. It is designed to be used during prediction.
   *  It traverses all the trees and calculates the final score for each instance.
   *
   * @param treeEnsembleHandle The treeEnsembleHandle value
   * @param bucketizedFeatures A list of rank 1 Tensors containing bucket id for each
   *  feature.
   * @param logitsDimension scalar, dimension of the logits, to be used for partial logits
   *  shape.
   * @return a new instance of BoostedTreesPredict
   */
  public BoostedTreesPredict boostedTreesPredict(Operand<? extends TType> treeEnsembleHandle,
      Iterable<Operand<TInt32>> bucketizedFeatures, Long logitsDimension) {
    return BoostedTreesPredict.create(scope, treeEnsembleHandle, bucketizedFeatures, logitsDimension);
  }

  /**
   * Add the quantile summaries to each quantile stream resource.
   *  An op that adds a list of quantile summaries to a quantile stream resource. Each
   *  summary Tensor is rank 2, containing summaries (value, weight, min_rank, max_rank)
   *  for a single feature.
   *
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param summaries string; List of Rank 2 Tensor each containing the summaries for a single feature.
   * @return a new instance of BoostedTreesQuantileStreamResourceAddSummaries
   */
  public BoostedTreesQuantileStreamResourceAddSummaries boostedTreesQuantileStreamResourceAddSummaries(
      Operand<? extends TType> quantileStreamResourceHandle,
      Iterable<Operand<TFloat32>> summaries) {
    return BoostedTreesQuantileStreamResourceAddSummaries.create(scope, quantileStreamResourceHandle, summaries);
  }

  /**
   * Deserialize bucket boundaries and ready flag into current QuantileAccumulator.
   *  An op that deserializes bucket boundaries and are boundaries ready flag into current QuantileAccumulator.
   *
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param bucketBoundaries float; List of Rank 1 Tensors each containing the bucket boundaries for a feature.
   * @return a new instance of BoostedTreesQuantileStreamResourceDeserialize
   */
  public BoostedTreesQuantileStreamResourceDeserialize boostedTreesQuantileStreamResourceDeserialize(
      Operand<? extends TType> quantileStreamResourceHandle,
      Iterable<Operand<TFloat32>> bucketBoundaries) {
    return BoostedTreesQuantileStreamResourceDeserialize.create(scope, quantileStreamResourceHandle, bucketBoundaries);
  }

  /**
   * Flush the summaries for a quantile stream resource.
   *  An op that flushes the summaries for a quantile stream resource.
   *
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param numBuckets int; approximate number of buckets unless using generate_quantiles.
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesQuantileStreamResourceFlush
   */
  public BoostedTreesQuantileStreamResourceFlush boostedTreesQuantileStreamResourceFlush(
      Operand<? extends TType> quantileStreamResourceHandle, Operand<TInt64> numBuckets,
      BoostedTreesQuantileStreamResourceFlush.Options... options) {
    return BoostedTreesQuantileStreamResourceFlush.create(scope, quantileStreamResourceHandle, numBuckets, options);
  }

  /**
   * Generate the bucket boundaries for each feature based on accumulated summaries.
   *  An op that returns a list of float tensors for a quantile stream resource. Each
   *  tensor is Rank 1 containing bucket boundaries for a single feature.
   *
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param numFeatures inferred int; number of features to get bucket boundaries for.
   * @return a new instance of BoostedTreesQuantileStreamResourceGetBucketBoundaries
   */
  public BoostedTreesQuantileStreamResourceGetBucketBoundaries boostedTreesQuantileStreamResourceGetBucketBoundaries(
      Operand<? extends TType> quantileStreamResourceHandle, Long numFeatures) {
    return BoostedTreesQuantileStreamResourceGetBucketBoundaries.create(scope, quantileStreamResourceHandle, numFeatures);
  }

  /**
   * Creates a handle to a BoostedTreesQuantileStreamResource.
   *
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesQuantileStreamResourceHandleOp
   */
  public BoostedTreesQuantileStreamResourceHandleOp boostedTreesQuantileStreamResourceHandleOp(
      BoostedTreesQuantileStreamResourceHandleOp.Options... options) {
    return BoostedTreesQuantileStreamResourceHandleOp.create(scope, options);
  }

  /**
   * Serializes the tree ensemble to a proto.
   *
   * @param treeEnsembleHandle Handle to the tree ensemble.
   * @return a new instance of BoostedTreesSerializeEnsemble
   */
  public BoostedTreesSerializeEnsemble boostedTreesSerializeEnsemble(
      Operand<? extends TType> treeEnsembleHandle) {
    return BoostedTreesSerializeEnsemble.create(scope, treeEnsembleHandle);
  }

  /**
   * Aggregates the summary of accumulated stats for the batch.
   *  The summary stats contains gradients and hessians accumulated for each node, bucket and dimension id.
   *
   * @param nodeIds int32; Rank 1 Tensor containing node ids for each example, shape [batch_size].
   * @param gradients float32; Rank 2 Tensor (shape=[batch_size, logits_dimension]) with gradients for each example.
   * @param hessians float32; Rank 2 Tensor (shape=[batch_size, hessian_dimension]) with hessians for each example.
   * @param featureIndices int32; Rank 2 indices of feature sparse Tensors (shape=[number of sparse entries, 2]).
   *  Number of sparse entries across all instances from the batch. The first value is
   *  the index of the instance, the second is dimension of the feature. The second axis
   *  can only have 2 values, i.e., the input dense version of Tensor can only be matrix.
   * @param featureValues int32; Rank 1 values of feature sparse Tensors (shape=[number of sparse entries]).
   *  Number of sparse entries across all instances from the batch. The first value is
   *  the index of the instance, the second is dimension of the feature.
   * @param featureShape int32; Rank 1 dense shape of feature sparse Tensors (shape=[2]).
   *  The first axis can only have 2 values, [batch_size, feature_dimension].
   * @param maxSplits int; the maximum number of splits possible in the whole tree.
   * @param numBuckets int; equals to the maximum possible value of bucketized feature + 1.
   * @return a new instance of BoostedTreesSparseAggregateStats
   */
  public BoostedTreesSparseAggregateStats boostedTreesSparseAggregateStats(Operand<TInt32> nodeIds,
      Operand<TFloat32> gradients, Operand<TFloat32> hessians, Operand<TInt32> featureIndices,
      Operand<TInt32> featureValues, Operand<TInt32> featureShape, Long maxSplits,
      Long numBuckets) {
    return BoostedTreesSparseAggregateStats.create(scope, nodeIds, gradients, hessians, featureIndices, featureValues, featureShape, maxSplits, numBuckets);
  }

  /**
   * Calculates gains for each feature and returns the best possible split information for the feature.
   *  The split information is the best threshold (bucket id), gains and left/right node contributions per node for each feature.
   *  <p>It is possible that not all nodes can be split on each feature. Hence, the list of possible nodes can differ between the features. Therefore, we return {@code node_ids_list} for each feature, containing the list of nodes that this feature can be used to split.
   *  <p>In this manner, the output is the best split per features and per node, so that it needs to be combined later to produce the best split for each node (among all possible features).
   *  <p>The output shapes are compatible in a way that the first dimension of all tensors are the same and equal to the number of possible split nodes for each feature.
   *
   * @param nodeIdRange A Rank 1 tensor (shape=[2]) to specify the range [first, last) of node ids to process within {@code stats_summary_list}. The nodes are iterated between the two nodes specified by the tensor, as like {@code for node_id in range(node_id_range[0], node_id_range[1])} (Note that the last index node_id_range[1] is exclusive).
   * @param statsSummaryIndices A Rank 2 int64 tensor of dense shape [N, 4] (N specifies the number of non-zero values) for accumulated stats summary (gradient/hessian) per node per bucket for each feature. The second dimension contains node id, feature dimension, bucket id, and stats dim.
   *  stats dim is the sum of logits dimension and hessian dimension, hessian dimension can either be logits dimension if diagonal hessian is used, or logits dimension^2 if full hessian is used.
   * @param statsSummaryValues A Rank 1 float tensor of dense shape [N] (N specifies the number of non-zero values), which supplies the values for each element in summary_indices.
   * @param statsSummaryShape A Rank 1 float tensor of dense shape [4], which specifies the dense shape of the sparse tensor, which is [num tree nodes, feature dimensions, num buckets, stats dim].
   * @param l1 l1 regularization factor on leaf weights, per instance based.
   * @param l2 l2 regularization factor on leaf weights, per instance based.
   * @param treeComplexity adjustment to the gain, per leaf based.
   * @param minNodeWeight minimum avg of hessians in a node before required for the node to be considered for splitting.
   * @param logitsDimension The dimension of logit, i.e., number of classes.
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesSparseCalculateBestFeatureSplit
   */
  public BoostedTreesSparseCalculateBestFeatureSplit boostedTreesSparseCalculateBestFeatureSplit(
      Operand<TInt32> nodeIdRange, Operand<TInt32> statsSummaryIndices,
      Operand<TFloat32> statsSummaryValues, Operand<TInt32> statsSummaryShape, Operand<TFloat32> l1,
      Operand<TFloat32> l2, Operand<TFloat32> treeComplexity, Operand<TFloat32> minNodeWeight,
      Long logitsDimension, BoostedTreesSparseCalculateBestFeatureSplit.Options... options) {
    return BoostedTreesSparseCalculateBestFeatureSplit.create(scope, nodeIdRange, statsSummaryIndices, statsSummaryValues, statsSummaryShape, l1, l2, treeComplexity, minNodeWeight, logitsDimension, options);
  }

  /**
   * Runs multiple additive regression ensemble predictors on input instances and
   *  computes the update to cached logits. It is designed to be used during training.
   *  It traverses the trees starting from cached tree id and cached node id and
   *  calculates the updates to be pushed to the cache.
   *
   * @param treeEnsembleHandle The treeEnsembleHandle value
   * @param cachedTreeIds Rank 1 Tensor containing cached tree ids which is the starting
   *  tree of prediction.
   * @param cachedNodeIds Rank 1 Tensor containing cached node id which is the starting
   *  node of prediction.
   * @param bucketizedFeatures A list of rank 1 Tensors containing bucket id for each
   *  feature.
   * @param logitsDimension scalar, dimension of the logits, to be used for partial logits
   *  shape.
   * @return a new instance of BoostedTreesTrainingPredict
   */
  public BoostedTreesTrainingPredict boostedTreesTrainingPredict(
      Operand<? extends TType> treeEnsembleHandle, Operand<TInt32> cachedTreeIds,
      Operand<TInt32> cachedNodeIds, Iterable<Operand<TInt32>> bucketizedFeatures,
      Long logitsDimension) {
    return BoostedTreesTrainingPredict.create(scope, treeEnsembleHandle, cachedTreeIds, cachedNodeIds, bucketizedFeatures, logitsDimension);
  }

  /**
   * Updates the tree ensemble by either adding a layer to the last tree being grown
   *  or by starting a new tree.
   *
   * @param treeEnsembleHandle Handle to the ensemble variable.
   * @param featureIds Rank 1 tensor with ids for each feature. This is the real id of
   *  the feature that will be used in the split.
   * @param nodeIds List of rank 1 tensors representing the nodes for which this feature
   *  has a split.
   * @param gains List of rank 1 tensors representing the gains for each of the feature's
   *  split.
   * @param thresholds List of rank 1 tensors representing the thesholds for each of the
   *  feature's split.
   * @param leftNodeContribs List of rank 2 tensors with left leaf contribs for each of
   *  the feature's splits. Will be added to the previous node values to constitute
   *  the values of the left nodes.
   * @param rightNodeContribs List of rank 2 tensors with right leaf contribs for each
   *  of the feature's splits. Will be added to the previous node values to constitute
   *  the values of the right nodes.
   * @param maxDepth Max depth of the tree to build.
   * @param learningRate shrinkage const for each new tree.
   * @param pruningMode 0-No pruning, 1-Pre-pruning, 2-Post-pruning.
   * @return a new instance of BoostedTreesUpdateEnsemble
   */
  public BoostedTreesUpdateEnsemble boostedTreesUpdateEnsemble(
      Operand<? extends TType> treeEnsembleHandle, Operand<TInt32> featureIds,
      Iterable<Operand<TInt32>> nodeIds, Iterable<Operand<TFloat32>> gains,
      Iterable<Operand<TInt32>> thresholds, Iterable<Operand<TFloat32>> leftNodeContribs,
      Iterable<Operand<TFloat32>> rightNodeContribs, Operand<TInt32> maxDepth,
      Operand<TFloat32> learningRate, Long pruningMode) {
    return BoostedTreesUpdateEnsemble.create(scope, treeEnsembleHandle, featureIds, nodeIds, gains, thresholds, leftNodeContribs, rightNodeContribs, maxDepth, learningRate, pruningMode);
  }

  /**
   * Updates the tree ensemble by adding a layer to the last tree being grown
   *  or by starting a new tree.
   *
   * @param treeEnsembleHandle Handle to the ensemble variable.
   * @param featureIds Rank 1 tensor with ids for each feature. This is the real id of
   *  the feature that will be used in the split.
   * @param dimensionIds List of rank 1 tensors representing the dimension in each feature.
   * @param nodeIds List of rank 1 tensors representing the nodes for which this feature
   *  has a split.
   * @param gains List of rank 1 tensors representing the gains for each of the feature's
   *  split.
   * @param thresholds List of rank 1 tensors representing the thesholds for each of the
   *  feature's split.
   * @param leftNodeContribs List of rank 2 tensors with left leaf contribs for each of
   *  the feature's splits. Will be added to the previous node values to constitute
   *  the values of the left nodes.
   * @param rightNodeContribs List of rank 2 tensors with right leaf contribs for each
   *  of the feature's splits. Will be added to the previous node values to constitute
   *  the values of the right nodes.
   * @param splitTypes List of rank 1 tensors representing the split type for each feature.
   * @param maxDepth Max depth of the tree to build.
   * @param learningRate shrinkage const for each new tree.
   * @param pruningMode 0-No pruning, 1-Pre-pruning, 2-Post-pruning.
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesUpdateEnsembleV2
   */
  public BoostedTreesUpdateEnsembleV2 boostedTreesUpdateEnsembleV2(
      Operand<? extends TType> treeEnsembleHandle, Iterable<Operand<TInt32>> featureIds,
      Iterable<Operand<TInt32>> dimensionIds, Iterable<Operand<TInt32>> nodeIds,
      Iterable<Operand<TFloat32>> gains, Iterable<Operand<TInt32>> thresholds,
      Iterable<Operand<TFloat32>> leftNodeContribs, Iterable<Operand<TFloat32>> rightNodeContribs,
      Iterable<Operand<TString>> splitTypes, Operand<TInt32> maxDepth,
      Operand<TFloat32> learningRate, Operand<TInt32> pruningMode,
      BoostedTreesUpdateEnsembleV2.Options... options) {
    return BoostedTreesUpdateEnsembleV2.create(scope, treeEnsembleHandle, featureIds, dimensionIds, nodeIds, gains, thresholds, leftNodeContribs, rightNodeContribs, splitTypes, maxDepth, learningRate, pruningMode, options);
  }

  /**
   * Checks whether a tree ensemble has been initialized.
   *
   * @param treeEnsembleHandle Handle to the tree ensemble resource.
   * @return a new instance of IsBoostedTreesEnsembleInitialized
   */
  public IsBoostedTreesEnsembleInitialized isBoostedTreesEnsembleInitialized(
      Operand<? extends TType> treeEnsembleHandle) {
    return IsBoostedTreesEnsembleInitialized.create(scope, treeEnsembleHandle);
  }

  /**
   * Checks whether a quantile stream has been initialized.
   *  An Op that checks if quantile stream resource is initialized.
   *
   * @param quantileStreamResourceHandle resource; The reference to quantile stream resource handle.
   * @return a new instance of IsBoostedTreesQuantileStreamResourceInitialized
   */
  public IsBoostedTreesQuantileStreamResourceInitialized isBoostedTreesQuantileStreamResourceInitialized(
      Operand<? extends TType> quantileStreamResourceHandle) {
    return IsBoostedTreesQuantileStreamResourceInitialized.create(scope, quantileStreamResourceHandle);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
