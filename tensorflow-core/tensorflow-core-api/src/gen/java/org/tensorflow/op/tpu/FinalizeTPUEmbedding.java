/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.tpu;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * An op that finalizes the TPUEmbedding configuration.
 */
@OpMetadata(
    opType = FinalizeTPUEmbedding.OP_NAME,
    inputsClass = FinalizeTPUEmbedding.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class FinalizeTPUEmbedding extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FinalizeTPUEmbedding";

  public FinalizeTPUEmbedding(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new FinalizeTPUEmbedding operation.
   *
   * @param scope current scope
   * @param commonConfig A string-encoded common configuration proto containing metadata
   * about the TPUEmbedding partitioner output and the HBM size (in bytes) required
   * for operation.
   * @param memoryConfig A string-encoded memory config proto containing metadata about
   * the memory allocations reserved for TPUEmbedding.
   * @return a new instance of FinalizeTPUEmbedding
   */
  @Endpoint(
      describeByClass = true
  )
  public static FinalizeTPUEmbedding create(Scope scope, Operand<TString> commonConfig,
      Operand<TString> memoryConfig) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FinalizeTPUEmbedding");
    opBuilder.addInput(commonConfig.asOutput());
    opBuilder.addInput(memoryConfig.asOutput());
    return new FinalizeTPUEmbedding(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = FinalizeTPUEmbedding.class
  )
  public static class Inputs extends RawOpInputs<FinalizeTPUEmbedding> {
    /**
     * A string-encoded common configuration proto containing metadata
     * about the TPUEmbedding partitioner output and the HBM size (in bytes) required
     * for operation.
     */
    public final Operand<TString> commonConfig;

    /**
     * A string-encoded memory config proto containing metadata about
     * the memory allocations reserved for TPUEmbedding.
     */
    public final Operand<TString> memoryConfig;

    public Inputs(GraphOperation op) {
      super(new FinalizeTPUEmbedding(op), op, Arrays.asList());
      int inputIndex = 0;
      commonConfig = (Operand<TString>) op.input(inputIndex++);
      memoryConfig = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
