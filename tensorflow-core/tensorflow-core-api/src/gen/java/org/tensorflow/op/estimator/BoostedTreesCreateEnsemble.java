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
 * Creates a tree ensemble model and returns a handle to it.
 */
@OpMetadata(
    opType = BoostedTreesCreateEnsemble.OP_NAME,
    inputsClass = BoostedTreesCreateEnsemble.Inputs.class
)
public final class BoostedTreesCreateEnsemble extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesCreateEnsemble";

  public BoostedTreesCreateEnsemble(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesCreateEnsemble operation.
   *
   * @param scope current scope
   * @param treeEnsembleHandle Handle to the tree ensemble resource to be created.
   * @param stampToken Token to use as the initial value of the resource stamp.
   * @param treeEnsembleSerialized Serialized proto of the tree ensemble.
   * @return a new instance of BoostedTreesCreateEnsemble
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesCreateEnsemble create(Scope scope,
      Operand<? extends TType> treeEnsembleHandle, Operand<TInt64> stampToken,
      Operand<TString> treeEnsembleSerialized) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesCreateEnsemble");
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    opBuilder.addInput(stampToken.asOutput());
    opBuilder.addInput(treeEnsembleSerialized.asOutput());
    return new BoostedTreesCreateEnsemble(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesCreateEnsemble.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesCreateEnsemble> {
    /**
     * Handle to the tree ensemble resource to be created.
     */
    public final Operand<? extends TType> treeEnsembleHandle;

    /**
     * Token to use as the initial value of the resource stamp.
     */
    public final Operand<TInt64> stampToken;

    /**
     * Serialized proto of the tree ensemble.
     */
    public final Operand<TString> treeEnsembleSerialized;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesCreateEnsemble(op), op, Arrays.asList());
      int inputIndex = 0;
      treeEnsembleHandle = (Operand<? extends TType>) op.input(inputIndex++);
      stampToken = (Operand<TInt64>) op.input(inputIndex++);
      treeEnsembleSerialized = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
