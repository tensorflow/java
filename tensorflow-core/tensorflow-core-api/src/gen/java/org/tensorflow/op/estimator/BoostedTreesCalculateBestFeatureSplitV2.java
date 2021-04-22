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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * Calculates gains for each feature and returns the best possible split information for each node. However, if no split is found, then no split information is returned for that node.
 * The split information is the best threshold (bucket id), gains and left/right node contributions per node for each feature.
 * <p>It is possible that not all nodes can be split on each feature. Hence, the list of possible nodes can differ between the features. Therefore, we return {@code node_ids_list} for each feature, containing the list of nodes that this feature can be used to split.
 * <p>In this manner, the output is the best split per features and per node, so that it needs to be combined later to produce the best split for each node (among all possible features).
 * <p>The output shapes are compatible in a way that the first dimension of all tensors are the same and equal to the number of possible split nodes for each feature.
 */
public final class BoostedTreesCalculateBestFeatureSplitV2 extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesCalculateBestFeatureSplitV2";

  private Output<TInt32> nodeIds;

  private Output<TFloat32> gains;

  private Output<TInt32> featureIds;

  private Output<TInt32> featureDimensions;

  private Output<TInt32> thresholds;

  private Output<TFloat32> leftNodeContribs;

  private Output<TFloat32> rightNodeContribs;

  private Output<TString> splitWithDefaultDirections;

  private BoostedTreesCalculateBestFeatureSplitV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    nodeIds = operation.output(outputIdx++);
    gains = operation.output(outputIdx++);
    featureIds = operation.output(outputIdx++);
    featureDimensions = operation.output(outputIdx++);
    thresholds = operation.output(outputIdx++);
    leftNodeContribs = operation.output(outputIdx++);
    rightNodeContribs = operation.output(outputIdx++);
    splitWithDefaultDirections = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesCalculateBestFeatureSplitV2 operation.
   *
   * @param scope current scope
   * @param nodeIdRange A Rank 1 tensor (shape=[2]) to specify the range [first, last) of node ids to process within {@code stats_summary_list}. The nodes are iterated between the two nodes specified by the tensor, as like {@code for node_id in range(node_id_range[0], node_id_range[1])} (Note that the last index node_id_range[1] is exclusive).
   * @param statsSummariesList A list of Rank 4 tensor (#shape=[max_splits, feature_dims, bucket, stats_dims]) for accumulated stats summary (gradient/hessian) per node, per dimension, per buckets for each feature.
   * The first dimension of the tensor is the maximum number of splits, and thus not all elements of it will be used, but only the indexes specified by node_ids will be used.
   * @param splitTypes A Rank 1 tensor indicating if this Op should perform inequality split or equality split per feature.
   * @param candidateFeatureIds Rank 1 tensor with ids for each feature. This is the real id of the feature.
   * @param l1 l1 regularization factor on leaf weights, per instance based.
   * @param l2 l2 regularization factor on leaf weights, per instance based.
   * @param treeComplexity adjustment to the gain, per leaf based.
   * @param minNodeWeight minimum avg of hessians in a node before required for the node to be considered for splitting.
   * @param logitsDimension The dimension of logit, i.e., number of classes.
   * @return a new instance of BoostedTreesCalculateBestFeatureSplitV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesCalculateBestFeatureSplitV2 create(Scope scope,
      Operand<TInt32> nodeIdRange, Iterable<Operand<TFloat32>> statsSummariesList,
      Operand<TString> splitTypes, Operand<TInt32> candidateFeatureIds, Operand<TFloat32> l1,
      Operand<TFloat32> l2, Operand<TFloat32> treeComplexity, Operand<TFloat32> minNodeWeight,
      Long logitsDimension) {
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesCalculateBestFeatureSplitV2", scope.makeOpName("BoostedTreesCalculateBestFeatureSplitV2"));
    opBuilder.addInput(nodeIdRange.asOutput());
    opBuilder.addInputList(Operands.asOutputs(statsSummariesList));
    opBuilder.addInput(splitTypes.asOutput());
    opBuilder.addInput(candidateFeatureIds.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(treeComplexity.asOutput());
    opBuilder.addInput(minNodeWeight.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("logits_dimension", logitsDimension);
    return new BoostedTreesCalculateBestFeatureSplitV2(opBuilder.build());
  }

  /**
   * Gets nodeIds.
   * A Rank 1 tensors indicating possible split node ids for each feature. The length of the list is num_features, but each tensor has different size as each feature provides different possible nodes. See above for details like shapes and sizes.
   * @return nodeIds.
   */
  public Output<TInt32> nodeIds() {
    return nodeIds;
  }

  /**
   * Gets gains.
   * A Rank 1 tensor indicating the best gains for each feature to split for certain nodes. See above for details like shapes and sizes.
   * @return gains.
   */
  public Output<TFloat32> gains() {
    return gains;
  }

  /**
   * Gets featureIds.
   * A Rank 1 tensors indicating the best feature id for each node. See above for details like shapes and sizes.
   * @return featureIds.
   */
  public Output<TInt32> featureIds() {
    return featureIds;
  }

  /**
   * Gets featureDimensions.
   * A Rank 1 tensors indicating the best feature dimension for each feature to split for certain nodes if the feature is multi-dimension. See above for details like shapes and sizes.
   * @return featureDimensions.
   */
  public Output<TInt32> featureDimensions() {
    return featureDimensions;
  }

  /**
   * Gets thresholds.
   * A Rank 1 tensors indicating the bucket id to compare with (as a threshold) for split in each node. See above for details like shapes and sizes.
   * @return thresholds.
   */
  public Output<TInt32> thresholds() {
    return thresholds;
  }

  /**
   * Gets leftNodeContribs.
   * A Rank 2 tensors indicating the contribution of the left nodes when branching from parent nodes (given by the tensor element in the output node_ids_list) to the left direction by the given threshold for each feature. This value will be used to make the left node value by adding to the parent node value. Second dimension size is 1 for 1-dimensional logits, but would be larger for multi-class problems. See above for details like shapes and sizes.
   * @return leftNodeContribs.
   */
  public Output<TFloat32> leftNodeContribs() {
    return leftNodeContribs;
  }

  /**
   * Gets rightNodeContribs.
   * A Rank 2 tensors, with the same shape/conditions as left_node_contribs_list, but just that the value is for the right node.
   * @return rightNodeContribs.
   */
  public Output<TFloat32> rightNodeContribs() {
    return rightNodeContribs;
  }

  /**
   * Gets splitWithDefaultDirections.
   * A Rank 1 tensors indicating the which direction to go if data is missing. See above for details like shapes and sizes.
   * Inequality with default left returns 0, inequality with default right returns 1, equality with default right returns 2.
   * @return splitWithDefaultDirections.
   */
  public Output<TString> splitWithDefaultDirections() {
    return splitWithDefaultDirections;
  }
}
