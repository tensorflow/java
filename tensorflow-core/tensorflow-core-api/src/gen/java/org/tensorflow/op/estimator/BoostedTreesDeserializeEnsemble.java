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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Deserializes a serialized tree ensemble config and replaces current tree
 * ensemble.
 */
@OpMetadata(
    opType = BoostedTreesDeserializeEnsemble.OP_NAME,
    inputsClass = BoostedTreesDeserializeEnsemble.Inputs.class
)
public final class BoostedTreesDeserializeEnsemble extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesDeserializeEnsemble";

  public BoostedTreesDeserializeEnsemble(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesDeserializeEnsemble operation.
   *
   * @param scope current scope
   * @param treeEnsembleHandle Handle to the tree ensemble.
   * @param stampToken Token to use as the new value of the resource stamp.
   * @param treeEnsembleSerialized Serialized proto of the ensemble.
   * @return a new instance of BoostedTreesDeserializeEnsemble
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesDeserializeEnsemble create(Scope scope,
      Operand<? extends TType> treeEnsembleHandle, Operand<TInt64> stampToken,
      Operand<TString> treeEnsembleSerialized) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesDeserializeEnsemble");
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    opBuilder.addInput(stampToken.asOutput());
    opBuilder.addInput(treeEnsembleSerialized.asOutput());
    return new BoostedTreesDeserializeEnsemble(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesDeserializeEnsemble.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesDeserializeEnsemble> {
    /**
     * Handle to the tree ensemble.
     */
    public final Operand<? extends TType> treeEnsembleHandle;

    /**
     * Token to use as the new value of the resource stamp.
     */
    public final Operand<TInt64> stampToken;

    /**
     * Serialized proto of the ensemble.
     */
    public final Operand<TString> treeEnsembleSerialized;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesDeserializeEnsemble(op), op, Arrays.asList());
      int inputIndex = 0;
      treeEnsembleHandle = (Operand<? extends TType>) op.input(inputIndex++);
      stampToken = (Operand<TInt64>) op.input(inputIndex++);
      treeEnsembleSerialized = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
