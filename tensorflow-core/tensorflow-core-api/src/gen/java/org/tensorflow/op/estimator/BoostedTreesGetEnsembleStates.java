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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Retrieves the tree ensemble resource stamp token, number of trees and growing statistics.
 */
@OpMetadata(
    opType = BoostedTreesGetEnsembleStates.OP_NAME,
    inputsClass = BoostedTreesGetEnsembleStates.Inputs.class
)
public final class BoostedTreesGetEnsembleStates extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesGetEnsembleStates";

  private Output<TInt64> stampToken;

  private Output<TInt32> numTrees;

  private Output<TInt32> numFinalizedTrees;

  private Output<TInt32> numAttemptedLayers;

  private Output<TInt32> lastLayerNodesRange;

  public BoostedTreesGetEnsembleStates(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    stampToken = operation.output(outputIdx++);
    numTrees = operation.output(outputIdx++);
    numFinalizedTrees = operation.output(outputIdx++);
    numAttemptedLayers = operation.output(outputIdx++);
    lastLayerNodesRange = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesGetEnsembleStates operation.
   *
   * @param scope current scope
   * @param treeEnsembleHandle Handle to the tree ensemble.
   * @return a new instance of BoostedTreesGetEnsembleStates
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesGetEnsembleStates create(Scope scope,
      Operand<? extends TType> treeEnsembleHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesGetEnsembleStates");
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    return new BoostedTreesGetEnsembleStates(opBuilder.build());
  }

  /**
   * Gets stampToken.
   * Stamp token of the tree ensemble resource.
   * @return stampToken.
   */
  public Output<TInt64> stampToken() {
    return stampToken;
  }

  /**
   * Gets numTrees.
   * The number of trees in the tree ensemble resource.
   * @return numTrees.
   */
  public Output<TInt32> numTrees() {
    return numTrees;
  }

  /**
   * Gets numFinalizedTrees.
   * The number of trees that were finished successfully.
   * @return numFinalizedTrees.
   */
  public Output<TInt32> numFinalizedTrees() {
    return numFinalizedTrees;
  }

  /**
   * Gets numAttemptedLayers.
   * The number of layers we attempted to build (but not necessarily succeeded).
   * @return numAttemptedLayers.
   */
  public Output<TInt32> numAttemptedLayers() {
    return numAttemptedLayers;
  }

  /**
   * Gets lastLayerNodesRange.
   * Rank size 2 tensor that contains start and end ids of the nodes in the latest
   * layer.
   * @return lastLayerNodesRange.
   */
  public Output<TInt32> lastLayerNodesRange() {
    return lastLayerNodesRange;
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesGetEnsembleStates.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesGetEnsembleStates> {
    /**
     * Handle to the tree ensemble.
     */
    public final Operand<? extends TType> treeEnsembleHandle;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesGetEnsembleStates(op), op, Arrays.asList());
      int inputIndex = 0;
      treeEnsembleHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
