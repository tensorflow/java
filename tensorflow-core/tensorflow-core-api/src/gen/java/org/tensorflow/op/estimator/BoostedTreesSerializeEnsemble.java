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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Serializes the tree ensemble to a proto.
 */
@OpMetadata(
    opType = BoostedTreesSerializeEnsemble.OP_NAME,
    inputsClass = BoostedTreesSerializeEnsemble.Inputs.class
)
public final class BoostedTreesSerializeEnsemble extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesSerializeEnsemble";

  private Output<TInt64> stampToken;

  private Output<TString> treeEnsembleSerialized;

  public BoostedTreesSerializeEnsemble(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    stampToken = operation.output(outputIdx++);
    treeEnsembleSerialized = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesSerializeEnsemble operation.
   *
   * @param scope current scope
   * @param treeEnsembleHandle Handle to the tree ensemble.
   * @return a new instance of BoostedTreesSerializeEnsemble
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesSerializeEnsemble create(Scope scope,
      Operand<? extends TType> treeEnsembleHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesSerializeEnsemble");
    opBuilder.addInput(treeEnsembleHandle.asOutput());
    return new BoostedTreesSerializeEnsemble(opBuilder.build());
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
   * Gets treeEnsembleSerialized.
   * Serialized proto of the ensemble.
   * @return treeEnsembleSerialized.
   */
  public Output<TString> treeEnsembleSerialized() {
    return treeEnsembleSerialized;
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesSerializeEnsemble.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesSerializeEnsemble> {
    /**
     * Handle to the tree ensemble.
     */
    public final Operand<? extends TType> treeEnsembleHandle;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesSerializeEnsemble(op), op, Arrays.asList());
      int inputIndex = 0;
      treeEnsembleHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
