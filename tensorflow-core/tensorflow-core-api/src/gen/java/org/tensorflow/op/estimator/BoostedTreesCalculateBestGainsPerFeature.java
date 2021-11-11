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
import java.util.List;
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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Calculates gains for each feature and returns the best possible split information for the feature.
 * The split information is the best threshold (bucket id), gains and left/right node contributions per node for each feature.
 * <p>It is possible that not all nodes can be split on each feature. Hence, the list of possible nodes can differ between the features. Therefore, we return {@code node_ids_list} for each feature, containing the list of nodes that this feature can be used to split.
 * <p>In this manner, the output is the best split per features and per node, so that it needs to be combined later to produce the best split for each node (among all possible features).
 * <p>The length of output lists are all of the same length, {@code num_features}.
 * The output shapes are compatible in a way that the first dimension of all tensors of all lists are the same and equal to the number of possible split nodes for each feature.
 */
@OpMetadata(
    opType = BoostedTreesCalculateBestGainsPerFeature.OP_NAME,
    inputsClass = BoostedTreesCalculateBestGainsPerFeature.Inputs.class
)
public final class BoostedTreesCalculateBestGainsPerFeature extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesCalculateBestGainsPerFeature";

  private List<Output<TInt32>> nodeIdsList;

  private List<Output<TFloat32>> gainsList;

  private List<Output<TInt32>> thresholdsList;

  private List<Output<TFloat32>> leftNodeContribsList;

  private List<Output<TFloat32>> rightNodeContribsList;

  @SuppressWarnings("unchecked")
  public BoostedTreesCalculateBestGainsPerFeature(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int nodeIdsListLength = operation.outputListLength("node_ids_list");
    nodeIdsList = Arrays.asList((Output<TInt32>[]) operation.outputList(outputIdx, nodeIdsListLength));
    outputIdx += nodeIdsListLength;
    int gainsListLength = operation.outputListLength("gains_list");
    gainsList = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, gainsListLength));
    outputIdx += gainsListLength;
    int thresholdsListLength = operation.outputListLength("thresholds_list");
    thresholdsList = Arrays.asList((Output<TInt32>[]) operation.outputList(outputIdx, thresholdsListLength));
    outputIdx += thresholdsListLength;
    int leftNodeContribsListLength = operation.outputListLength("left_node_contribs_list");
    leftNodeContribsList = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, leftNodeContribsListLength));
    outputIdx += leftNodeContribsListLength;
    int rightNodeContribsListLength = operation.outputListLength("right_node_contribs_list");
    rightNodeContribsList = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, rightNodeContribsListLength));
    outputIdx += rightNodeContribsListLength;
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesCalculateBestGainsPerFeature operation.
   *
   * @param scope current scope
   * @param nodeIdRange A Rank 1 tensor (shape=[2]) to specify the range [first, last) of node ids to process within {@code stats_summary_list}. The nodes are iterated between the two nodes specified by the tensor, as like {@code for node_id in range(node_id_range[0], node_id_range[1])} (Note that the last index node_id_range[1] is exclusive).
   * @param statsSummaryList A list of Rank 3 tensor (#shape=[max_splits, bucket, 2]) for accumulated stats summary (gradient/hessian) per node per buckets for each feature. The first dimension of the tensor is the maximum number of splits, and thus not all elements of it will be used, but only the indexes specified by node_ids will be used.
   * @param l1 l1 regularization factor on leaf weights, per instance based.
   * @param l2 l2 regularization factor on leaf weights, per instance based.
   * @param treeComplexity adjustment to the gain, per leaf based.
   * @param minNodeWeight minimum avg of hessians in a node before required for the node to be considered for splitting.
   * @param maxSplits the number of nodes that can be split in the whole tree. Used as a dimension of output tensors.
   * @return a new instance of BoostedTreesCalculateBestGainsPerFeature
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesCalculateBestGainsPerFeature create(Scope scope,
      Operand<TInt32> nodeIdRange, Iterable<Operand<TFloat32>> statsSummaryList,
      Operand<TFloat32> l1, Operand<TFloat32> l2, Operand<TFloat32> treeComplexity,
      Operand<TFloat32> minNodeWeight, Long maxSplits) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesCalculateBestGainsPerFeature");
    opBuilder.addInput(nodeIdRange.asOutput());
    opBuilder.addInputList(Operands.asOutputs(statsSummaryList));
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(treeComplexity.asOutput());
    opBuilder.addInput(minNodeWeight.asOutput());
    opBuilder.setAttr("max_splits", maxSplits);
    return new BoostedTreesCalculateBestGainsPerFeature(opBuilder.build());
  }

  /**
   * Gets nodeIdsList.
   * An output list of Rank 1 tensors indicating possible split node ids for each feature. The length of the list is num_features, but each tensor has different size as each feature provides different possible nodes. See above for details like shapes and sizes.
   * @return nodeIdsList.
   */
  public List<Output<TInt32>> nodeIdsList() {
    return nodeIdsList;
  }

  /**
   * Gets gainsList.
   * An output list of Rank 1 tensors indicating the best gains for each feature to split for certain nodes. See above for details like shapes and sizes.
   * @return gainsList.
   */
  public List<Output<TFloat32>> gainsList() {
    return gainsList;
  }

  /**
   * Gets thresholdsList.
   * An output list of Rank 1 tensors indicating the bucket id to compare with (as a threshold) for split in each node. See above for details like shapes and sizes.
   * @return thresholdsList.
   */
  public List<Output<TInt32>> thresholdsList() {
    return thresholdsList;
  }

  /**
   * Gets leftNodeContribsList.
   * A list of Rank 2 tensors indicating the contribution of the left nodes when branching from parent nodes (given by the tensor element in the output node_ids_list) to the left direction by the given threshold for each feature. This value will be used to make the left node value by adding to the parent node value. Second dimension size is 1 for 1-dimensional logits, but would be larger for multi-class problems. See above for details like shapes and sizes.
   * @return leftNodeContribsList.
   */
  public List<Output<TFloat32>> leftNodeContribsList() {
    return leftNodeContribsList;
  }

  /**
   * Gets rightNodeContribsList.
   * A list of Rank 2 tensors, with the same shape/conditions as left_node_contribs_list, but just that the value is for the right node.
   * @return rightNodeContribsList.
   */
  public List<Output<TFloat32>> rightNodeContribsList() {
    return rightNodeContribsList;
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesCalculateBestGainsPerFeature.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesCalculateBestGainsPerFeature> {
    /**
     * A Rank 1 tensor (shape=[2]) to specify the range [first, last) of node ids to process within {@code stats_summary_list}. The nodes are iterated between the two nodes specified by the tensor, as like {@code for node_id in range(node_id_range[0], node_id_range[1])} (Note that the last index node_id_range[1] is exclusive).
     */
    public final Operand<TInt32> nodeIdRange;

    /**
     * A list of Rank 3 tensor (#shape=[max_splits, bucket, 2]) for accumulated stats summary (gradient/hessian) per node per buckets for each feature. The first dimension of the tensor is the maximum number of splits, and thus not all elements of it will be used, but only the indexes specified by node_ids will be used.
     */
    public final Iterable<Operand<TFloat32>> statsSummaryList;

    /**
     * l1 regularization factor on leaf weights, per instance based.
     */
    public final Operand<TFloat32> l1;

    /**
     * l2 regularization factor on leaf weights, per instance based.
     */
    public final Operand<TFloat32> l2;

    /**
     * adjustment to the gain, per leaf based.
     */
    public final Operand<TFloat32> treeComplexity;

    /**
     * minimum avg of hessians in a node before required for the node to be considered for splitting.
     */
    public final Operand<TFloat32> minNodeWeight;

    /**
     * the number of nodes that can be split in the whole tree. Used as a dimension of output tensors.
     */
    public final long maxSplits;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesCalculateBestGainsPerFeature(op), op, Arrays.asList("max_splits"));
      int inputIndex = 0;
      nodeIdRange = (Operand<TInt32>) op.input(inputIndex++);
      int statsSummaryListLength = op.inputListLength("stats_summary_list");
      statsSummaryList = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, statsSummaryListLength));
      inputIndex += statsSummaryListLength;
      l1 = (Operand<TFloat32>) op.input(inputIndex++);
      l2 = (Operand<TFloat32>) op.input(inputIndex++);
      treeComplexity = (Operand<TFloat32>) op.input(inputIndex++);
      minNodeWeight = (Operand<TFloat32>) op.input(inputIndex++);
      maxSplits = op.attributes().getAttrInt("max_splits");
    }
  }
}
