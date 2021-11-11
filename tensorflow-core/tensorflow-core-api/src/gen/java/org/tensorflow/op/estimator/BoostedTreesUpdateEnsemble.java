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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Updates the tree ensemble by either adding a layer to the last tree being grown
 * or by starting a new tree.
 */
@OpMetadata(
    opType = BoostedTreesUpdateEnsemble.OP_NAME,
    inputsClass = BoostedTreesUpdateEnsemble.Inputs.class
)
public final class BoostedTreesUpdateEnsemble extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesUpdateEnsemble";

  public BoostedTreesUpdateEnsemble(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesUpdateEnsemble operation.
   *
   * @param scope current scope
   * @param treeEnsembleHandle Handle to the ensemble variable.
   * @param featureIds Rank 1 tensor with ids for each feature. This is the real id of
   * the feature that will be used in the split.
   * @param nodeIds List of rank 1 tensors representing the nodes for which this feature
   * has a split.
   * @param gains List of rank 1 tensors representing the gains for each of the feature's
   * split.
   * @param thresholds List of rank 1 tensors representing the thesholds for each of the
   * feature's split.
   * @param leftNodeContribs List of rank 2 tensors with left leaf contribs for each of
   * the feature's splits. Will be added to the previous node values to constitute
   * the values of the left nodes.
   * @param rightNodeContribs List of rank 2 tensors with right leaf contribs for each
   * of the feature's splits. Will be added to the previous node values to constitute
   * the values of the right nodes.
   * @param maxDepth Max depth of the tree to build.
   * @param learningRate shrinkage const for each new tree.
   * @param pruningMode 0-No pruning, 1-Pre-pruning, 2-Post-pruning.
   * @return a new instance of BoostedTreesUpdateEnsemble
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesUpdateEnsemble create(Scope scope,
      Operand<? extends TType> treeEnsembleHandle, Operand<TInt32> featureIds,
      Iterable<Operand<TInt32>> nodeIds, Iterable<Operand<TFloat32>> gains,
      Iterable<Operand<TInt32>> thresholds, Iterable<Operand<TFloat32>> leftNodeContribs,
      Iterable<Operand<TFloat32>> rightNodeContribs, Operand<TInt32> maxDepth,
      Operand<TFloat32> learningRate, Long pruningMode) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesUpdateEnsemble");
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    opBuilder.addInput(featureIds.asOutput());
    opBuilder.addInputList(Operands.asOutputs(nodeIds));
    opBuilder.addInputList(Operands.asOutputs(gains));
    opBuilder.addInputList(Operands.asOutputs(thresholds));
    opBuilder.addInputList(Operands.asOutputs(leftNodeContribs));
    opBuilder.addInputList(Operands.asOutputs(rightNodeContribs));
    opBuilder.addInput(maxDepth.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.setAttr("pruning_mode", pruningMode);
    return new BoostedTreesUpdateEnsemble(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesUpdateEnsemble.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesUpdateEnsemble> {
    /**
     * Handle to the ensemble variable.
     */
    public final Operand<? extends TType> treeEnsembleHandle;

    /**
     * Rank 1 tensor with ids for each feature. This is the real id of
     * the feature that will be used in the split.
     */
    public final Operand<TInt32> featureIds;

    /**
     * List of rank 1 tensors representing the nodes for which this feature
     * has a split.
     */
    public final Iterable<Operand<TInt32>> nodeIds;

    /**
     * List of rank 1 tensors representing the gains for each of the feature's
     * split.
     */
    public final Iterable<Operand<TFloat32>> gains;

    /**
     * List of rank 1 tensors representing the thesholds for each of the
     * feature's split.
     */
    public final Iterable<Operand<TInt32>> thresholds;

    /**
     * List of rank 2 tensors with left leaf contribs for each of
     * the feature's splits. Will be added to the previous node values to constitute
     * the values of the left nodes.
     */
    public final Iterable<Operand<TFloat32>> leftNodeContribs;

    /**
     * List of rank 2 tensors with right leaf contribs for each
     * of the feature's splits. Will be added to the previous node values to constitute
     * the values of the right nodes.
     */
    public final Iterable<Operand<TFloat32>> rightNodeContribs;

    /**
     * Max depth of the tree to build.
     */
    public final Operand<TInt32> maxDepth;

    /**
     * shrinkage const for each new tree.
     */
    public final Operand<TFloat32> learningRate;

    /**
     * 0-No pruning, 1-Pre-pruning, 2-Post-pruning.
     */
    public final long pruningMode;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesUpdateEnsemble(op), op, Arrays.asList("pruning_mode"));
      int inputIndex = 0;
      treeEnsembleHandle = (Operand<? extends TType>) op.input(inputIndex++);
      featureIds = (Operand<TInt32>) op.input(inputIndex++);
      int nodeIdsLength = op.inputListLength("node_ids");
      nodeIds = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, nodeIdsLength));
      inputIndex += nodeIdsLength;
      int gainsLength = op.inputListLength("gains");
      gains = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, gainsLength));
      inputIndex += gainsLength;
      int thresholdsLength = op.inputListLength("thresholds");
      thresholds = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, thresholdsLength));
      inputIndex += thresholdsLength;
      int leftNodeContribsLength = op.inputListLength("left_node_contribs");
      leftNodeContribs = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, leftNodeContribsLength));
      inputIndex += leftNodeContribsLength;
      int rightNodeContribsLength = op.inputListLength("right_node_contribs");
      rightNodeContribs = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, rightNodeContribsLength));
      inputIndex += rightNodeContribsLength;
      maxDepth = (Operand<TInt32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      pruningMode = op.attributes().getAttrInt("pruning_mode");
    }
  }
}
