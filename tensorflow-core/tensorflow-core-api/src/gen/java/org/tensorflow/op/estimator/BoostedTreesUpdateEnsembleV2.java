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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Updates the tree ensemble by adding a layer to the last tree being grown
 * or by starting a new tree.
 */
@OpMetadata(
    opType = BoostedTreesUpdateEnsembleV2.OP_NAME,
    inputsClass = BoostedTreesUpdateEnsembleV2.Inputs.class
)
public final class BoostedTreesUpdateEnsembleV2 extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesUpdateEnsembleV2";

  public BoostedTreesUpdateEnsembleV2(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesUpdateEnsembleV2 operation.
   *
   * @param scope current scope
   * @param treeEnsembleHandle Handle to the ensemble variable.
   * @param featureIds Rank 1 tensor with ids for each feature. This is the real id of
   * the feature that will be used in the split.
   * @param dimensionIds List of rank 1 tensors representing the dimension in each feature.
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
   * @param splitTypes List of rank 1 tensors representing the split type for each feature.
   * @param maxDepth Max depth of the tree to build.
   * @param learningRate shrinkage const for each new tree.
   * @param pruningMode 0-No pruning, 1-Pre-pruning, 2-Post-pruning.
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesUpdateEnsembleV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesUpdateEnsembleV2 create(Scope scope,
      Operand<? extends TType> treeEnsembleHandle, Iterable<Operand<TInt32>> featureIds,
      Iterable<Operand<TInt32>> dimensionIds, Iterable<Operand<TInt32>> nodeIds,
      Iterable<Operand<TFloat32>> gains, Iterable<Operand<TInt32>> thresholds,
      Iterable<Operand<TFloat32>> leftNodeContribs, Iterable<Operand<TFloat32>> rightNodeContribs,
      Iterable<Operand<TString>> splitTypes, Operand<TInt32> maxDepth,
      Operand<TFloat32> learningRate, Operand<TInt32> pruningMode, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesUpdateEnsembleV2");
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    opBuilder.addInputList(Operands.asOutputs(featureIds));
    opBuilder.addInputList(Operands.asOutputs(dimensionIds));
    opBuilder.addInputList(Operands.asOutputs(nodeIds));
    opBuilder.addInputList(Operands.asOutputs(gains));
    opBuilder.addInputList(Operands.asOutputs(thresholds));
    opBuilder.addInputList(Operands.asOutputs(leftNodeContribs));
    opBuilder.addInputList(Operands.asOutputs(rightNodeContribs));
    opBuilder.addInputList(Operands.asOutputs(splitTypes));
    opBuilder.addInput(maxDepth.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.addInput(pruningMode.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.logitsDimension != null) {
          opBuilder.setAttr("logits_dimension", opts.logitsDimension);
        }
        if (opts.numGroups != null) {
          opBuilder.setAttr("num_groups", opts.numGroups);
        }
      }
    }
    return new BoostedTreesUpdateEnsembleV2(opBuilder.build());
  }

  /**
   * Sets the logitsDimension option.
   *
   * @param logitsDimension scalar, dimension of the logits
   * @return this Options instance.
   */
  public static Options logitsDimension(Long logitsDimension) {
    return new Options().logitsDimension(logitsDimension);
  }

  /**
   * Sets the numGroups option.
   *
   * @param numGroups Number of groups of split information to process, where a group contains feature
   * ids that are processed together in BoostedTreesCalculateBestFeatureSplitOpV2.
   * INFERRED.
   * @return this Options instance.
   */
  public static Options numGroups(Long numGroups) {
    return new Options().numGroups(numGroups);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.estimator.BoostedTreesUpdateEnsembleV2}
   */
  public static class Options {
    private Long logitsDimension;

    private Long numGroups;

    private Options() {
    }

    /**
     * Sets the logitsDimension option.
     *
     * @param logitsDimension scalar, dimension of the logits
     * @return this Options instance.
     */
    public Options logitsDimension(Long logitsDimension) {
      this.logitsDimension = logitsDimension;
      return this;
    }

    /**
     * Sets the numGroups option.
     *
     * @param numGroups Number of groups of split information to process, where a group contains feature
     * ids that are processed together in BoostedTreesCalculateBestFeatureSplitOpV2.
     * INFERRED.
     * @return this Options instance.
     */
    public Options numGroups(Long numGroups) {
      this.numGroups = numGroups;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesUpdateEnsembleV2.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesUpdateEnsembleV2> {
    /**
     * Handle to the ensemble variable.
     */
    public final Operand<? extends TType> treeEnsembleHandle;

    /**
     * Rank 1 tensor with ids for each feature. This is the real id of
     * the feature that will be used in the split.
     */
    public final Iterable<Operand<TInt32>> featureIds;

    /**
     * List of rank 1 tensors representing the dimension in each feature.
     */
    public final Iterable<Operand<TInt32>> dimensionIds;

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
     * List of rank 1 tensors representing the split type for each feature.
     */
    public final Iterable<Operand<TString>> splitTypes;

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
    public final Operand<TInt32> pruningMode;

    /**
     * scalar, dimension of the logits
     */
    public final long logitsDimension;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesUpdateEnsembleV2(op), op, Arrays.asList("logits_dimension"));
      int inputIndex = 0;
      treeEnsembleHandle = (Operand<? extends TType>) op.input(inputIndex++);
      int featureIdsLength = op.inputListLength("feature_ids");
      featureIds = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, featureIdsLength));
      inputIndex += featureIdsLength;
      int dimensionIdsLength = op.inputListLength("dimension_ids");
      dimensionIds = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, dimensionIdsLength));
      inputIndex += dimensionIdsLength;
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
      int splitTypesLength = op.inputListLength("split_types");
      splitTypes = Arrays.asList((Operand<TString>[]) op.inputList(inputIndex, splitTypesLength));
      inputIndex += splitTypesLength;
      maxDepth = (Operand<TInt32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      pruningMode = (Operand<TInt32>) op.input(inputIndex++);
      logitsDimension = op.attributes().getAttrInt("logits_dimension");
    }
  }
}
