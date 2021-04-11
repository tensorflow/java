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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Updates the tree ensemble by adding a layer to the last tree being grown
 * or by starting a new tree.
 */
public final class BoostedTreesUpdateEnsembleV2 extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesUpdateEnsembleV2";

  private BoostedTreesUpdateEnsembleV2(Operation operation) {
    super(operation);
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
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesUpdateEnsembleV2", scope.makeOpName("BoostedTreesUpdateEnsembleV2"));
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
    opBuilder = scope.apply(opBuilder);
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
}
