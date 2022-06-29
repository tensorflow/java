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
 * Runs multiple additive regression ensemble predictors on input instances and
 * computes the logits. It is designed to be used during prediction.
 * It traverses all the trees and calculates the final score for each instance.
 */
@OpMetadata(
    opType = BoostedTreesPredict.OP_NAME,
    inputsClass = BoostedTreesPredict.Inputs.class
)
public final class BoostedTreesPredict extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesPredict";

  private Output<TFloat32> logits;

  public BoostedTreesPredict(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    logits = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesPredict operation.
   *
   * @param scope current scope
   * @param treeEnsembleHandle The treeEnsembleHandle value
   * @param bucketizedFeatures A list of rank 1 Tensors containing bucket id for each
   * feature.
   * @param logitsDimension scalar, dimension of the logits, to be used for partial logits
   * shape.
   * @return a new instance of BoostedTreesPredict
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesPredict create(Scope scope, Operand<? extends TType> treeEnsembleHandle,
      Iterable<Operand<TInt32>> bucketizedFeatures, Long logitsDimension) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesPredict");
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    opBuilder.addInputList(Operands.asOutputs(bucketizedFeatures));
    opBuilder.setAttr("logits_dimension", logitsDimension);
    return new BoostedTreesPredict(opBuilder.build());
  }

  /**
   * Gets logits.
   * Output rank 2 Tensor containing logits for each example.
   * @return logits.
   */
  public Output<TFloat32> logits() {
    return logits;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return logits;
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesPredict.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesPredict> {
    /**
     * The treeEnsembleHandle input
     */
    public final Operand<? extends TType> treeEnsembleHandle;

    /**
     * A list of rank 1 Tensors containing bucket id for each
     * feature.
     */
    public final Iterable<Operand<TInt32>> bucketizedFeatures;

    /**
     * scalar, dimension of the logits, to be used for partial logits
     * shape.
     */
    public final long logitsDimension;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesPredict(op), op, Arrays.asList("logits_dimension"));
      int inputIndex = 0;
      treeEnsembleHandle = (Operand<? extends TType>) op.input(inputIndex++);
      int bucketizedFeaturesLength = op.inputListLength("bucketized_features");
      bucketizedFeatures = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, bucketizedFeaturesLength));
      inputIndex += bucketizedFeaturesLength;
      logitsDimension = op.attributes().getAttrInt("logits_dimension");
    }
  }
}
