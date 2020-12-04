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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * Debugging/model interpretability outputs for each example.
 * <p>
 * It traverses all the trees and computes debug metrics for individual examples,
 * such as getting split feature ids and logits after each split along the decision
 * path used to compute directional feature contributions.
 */
public final class BoostedTreesExampleDebugOutputs extends RawOp implements Operand<TString> {
  
  /**
   * Factory method to create a class wrapping a new BoostedTreesExampleDebugOutputs operation.
   * 
   * @param scope current scope
   * @param treeEnsembleHandle 
   * @param bucketizedFeatures A list of rank 1 Tensors containing bucket id for each
   * feature.
   * @param logitsDimension scalar, dimension of the logits, to be used for constructing the protos in
   * examples_debug_outputs_serialized.
   * @return a new instance of BoostedTreesExampleDebugOutputs
   */
  @Endpoint(describeByClass = true)
  public static BoostedTreesExampleDebugOutputs create(Scope scope, Operand<?> treeEnsembleHandle, Iterable<Operand<TInt32>> bucketizedFeatures, Long logitsDimension) {
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesExampleDebugOutputs", scope.makeOpName("BoostedTreesExampleDebugOutputs"));
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    opBuilder.addInputList(Operands.asOutputs(bucketizedFeatures));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("logits_dimension", logitsDimension);
    return new BoostedTreesExampleDebugOutputs(opBuilder.build());
  }
  
  /**
   * Output rank 1 Tensor containing a proto serialized as a string for each example.
   */
  public Output<TString> examplesDebugOutputsSerialized() {
    return examplesDebugOutputsSerialized;
  }
  
  @Override
  public Output<TString> asOutput() {
    return examplesDebugOutputsSerialized;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BoostedTreesExampleDebugOutputs";
  
  private Output<TString> examplesDebugOutputsSerialized;
  
  private BoostedTreesExampleDebugOutputs(Operation operation) {
    super(operation);
    int outputIdx = 0;
    examplesDebugOutputsSerialized = operation.output(outputIdx++);
  }
}
