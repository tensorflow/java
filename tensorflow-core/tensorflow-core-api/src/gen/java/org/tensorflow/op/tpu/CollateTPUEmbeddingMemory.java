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

package org.tensorflow.op.tpu;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * An op that merges the string-encoded memory config protos from all hosts.
 */
@OpMetadata(
    opType = CollateTPUEmbeddingMemory.OP_NAME,
    inputsClass = CollateTPUEmbeddingMemory.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class CollateTPUEmbeddingMemory extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollateTPUEmbeddingMemory";

  private Output<TString> mergedMemoryConfig;

  public CollateTPUEmbeddingMemory(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    mergedMemoryConfig = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollateTPUEmbeddingMemory operation.
   *
   * @param scope current scope
   * @param memoryConfigs String-encoded memory config protos containing metadata about
   * the memory allocations reserved for TPUEmbedding across all hosts.
   * @return a new instance of CollateTPUEmbeddingMemory
   */
  @Endpoint(
      describeByClass = true
  )
  public static CollateTPUEmbeddingMemory create(Scope scope,
      Iterable<Operand<TString>> memoryConfigs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CollateTPUEmbeddingMemory");
    opBuilder.addInputList(Operands.asOutputs(memoryConfigs));
    return new CollateTPUEmbeddingMemory(opBuilder.build());
  }

  /**
   * Gets mergedMemoryConfig.
   *
   * @return mergedMemoryConfig.
   */
  public Output<TString> mergedMemoryConfig() {
    return mergedMemoryConfig;
  }

  @Override
  public Output<TString> asOutput() {
    return mergedMemoryConfig;
  }

  @OpInputsMetadata(
      outputsClass = CollateTPUEmbeddingMemory.class
  )
  public static class Inputs extends RawOpInputs<CollateTPUEmbeddingMemory> {
    /**
     * String-encoded memory config protos containing metadata about
     * the memory allocations reserved for TPUEmbedding across all hosts.
     */
    public final Iterable<Operand<TString>> memoryConfigs;

    public Inputs(GraphOperation op) {
      super(new CollateTPUEmbeddingMemory(op), op, Arrays.asList());
      int inputIndex = 0;
      int memoryConfigsLength = op.inputListLength("memory_configs");
      memoryConfigs = Arrays.asList((Operand<TString>[]) op.inputList(inputIndex, memoryConfigsLength));
      inputIndex += memoryConfigsLength;
    }
  }
}
