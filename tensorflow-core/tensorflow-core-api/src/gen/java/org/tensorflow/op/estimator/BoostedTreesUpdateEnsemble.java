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
import org.tensorflow.types.TInt32;

/**
 * Updates the tree ensemble by either adding a layer to the last tree being grown
 * <p>
 * or by starting a new tree.
 */
public final class BoostedTreesUpdateEnsemble extends RawOp {
  
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
  @Endpoint(describeByClass = true)
  public static BoostedTreesUpdateEnsemble create(Scope scope, Operand<?> treeEnsembleHandle, Operand<TInt32> featureIds, Iterable<Operand<TInt32>> nodeIds, Iterable<Operand<TFloat32>> gains, Iterable<Operand<TInt32>> thresholds, Iterable<Operand<TFloat32>> leftNodeContribs, Iterable<Operand<TFloat32>> rightNodeContribs, Operand<TInt32> maxDepth, Operand<TFloat32> learningRate, Long pruningMode) {
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesUpdateEnsemble", scope.makeOpName("BoostedTreesUpdateEnsemble"));
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    opBuilder.addInput(featureIds.asOutput());
    opBuilder.addInputList(Operands.asOutputs(nodeIds));
    opBuilder.addInputList(Operands.asOutputs(gains));
    opBuilder.addInputList(Operands.asOutputs(thresholds));
    opBuilder.addInputList(Operands.asOutputs(leftNodeContribs));
    opBuilder.addInputList(Operands.asOutputs(rightNodeContribs));
    opBuilder.addInput(maxDepth.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("pruning_mode", pruningMode);
    return new BoostedTreesUpdateEnsemble(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BoostedTreesUpdateEnsemble";
  
  private BoostedTreesUpdateEnsemble(Operation operation) {
    super(operation);
  }
}
