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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * An op that executes the TPUEmbedding partitioner on the central configuration
 * device and computes the HBM size (in bytes) required for TPUEmbedding operation.
 */
@OpMetadata(
    opType = ExecuteTPUEmbeddingPartitioner.OP_NAME,
    inputsClass = ExecuteTPUEmbeddingPartitioner.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class ExecuteTPUEmbeddingPartitioner extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExecuteTPUEmbeddingPartitioner";

  private Output<TString> commonConfig;

  public ExecuteTPUEmbeddingPartitioner(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    commonConfig = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExecuteTPUEmbeddingPartitioner operation.
   *
   * @param scope current scope
   * @param config An TPUEmbeddingConfiguration proto serialized to a string,
   * describing the desired TPUEmbedding configuration.
   * @return a new instance of ExecuteTPUEmbeddingPartitioner
   */
  @Endpoint(
      describeByClass = true
  )
  public static ExecuteTPUEmbeddingPartitioner create(Scope scope, String config) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ExecuteTPUEmbeddingPartitioner");
    opBuilder.setAttr("config", config);
    return new ExecuteTPUEmbeddingPartitioner(opBuilder.build());
  }

  /**
   * Gets commonConfig.
   * A string-encoded common configuration proto
   * containing metadata about the TPUEmbedding partitioner output and
   * the HBM size (in bytes) required for operation.
   * @return commonConfig.
   */
  public Output<TString> commonConfig() {
    return commonConfig;
  }

  @Override
  public Output<TString> asOutput() {
    return commonConfig;
  }

  @OpInputsMetadata(
      outputsClass = ExecuteTPUEmbeddingPartitioner.class
  )
  public static class Inputs extends RawOpInputs<ExecuteTPUEmbeddingPartitioner> {
    /**
     * An TPUEmbeddingConfiguration proto serialized to a string,
     * describing the desired TPUEmbedding configuration.
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new ExecuteTPUEmbeddingPartitioner(op), op, Arrays.asList("config"));
      int inputIndex = 0;
      config = op.attributes().getAttrString("config");
    }
  }
}
