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
 * An op that configures the TPUEmbedding software on a host.
 */
@OpMetadata(
    opType = ConfigureTPUEmbeddingHost.OP_NAME,
    inputsClass = ConfigureTPUEmbeddingHost.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class ConfigureTPUEmbeddingHost extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConfigureTPUEmbeddingHost";

  private Output<TString> networkConfig;

  public ConfigureTPUEmbeddingHost(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    networkConfig = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ConfigureTPUEmbeddingHost operation.
   *
   * @param scope current scope
   * @param commonConfig A string-encoded common configuration proto containing metadata
   * about the TPUEmbedding partitioner output.
   * @param memoryConfig A string-encoded memory config proto containing metadata about
   * the memory allocations reserved for TPUEmbedding.
   * @param config An TPUEmbeddingConfiguration proto serialized to a string,
   * describing the desired TPUEmbedding configuration.
   * @return a new instance of ConfigureTPUEmbeddingHost
   */
  @Endpoint(
      describeByClass = true
  )
  public static ConfigureTPUEmbeddingHost create(Scope scope, Operand<TString> commonConfig,
      Operand<TString> memoryConfig, String config) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConfigureTPUEmbeddingHost");
    opBuilder.addInput(commonConfig.asOutput());
    opBuilder.addInput(memoryConfig.asOutput());
    opBuilder.setAttr("config", config);
    return new ConfigureTPUEmbeddingHost(opBuilder.build());
  }

  /**
   * Gets networkConfig.
   * A string containing metadata about the hostname and RPC port
   * used for communication with this host.
   * @return networkConfig.
   */
  public Output<TString> networkConfig() {
    return networkConfig;
  }

  @Override
  public Output<TString> asOutput() {
    return networkConfig;
  }

  @OpInputsMetadata(
      outputsClass = ConfigureTPUEmbeddingHost.class
  )
  public static class Inputs extends RawOpInputs<ConfigureTPUEmbeddingHost> {
    /**
     * A string-encoded common configuration proto containing metadata
     * about the TPUEmbedding partitioner output.
     */
    public final Operand<TString> commonConfig;

    /**
     * A string-encoded memory config proto containing metadata about
     * the memory allocations reserved for TPUEmbedding.
     */
    public final Operand<TString> memoryConfig;

    /**
     * An TPUEmbeddingConfiguration proto serialized to a string,
     * describing the desired TPUEmbedding configuration.
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new ConfigureTPUEmbeddingHost(op), op, Arrays.asList("config"));
      int inputIndex = 0;
      commonConfig = (Operand<TString>) op.input(inputIndex++);
      memoryConfig = (Operand<TString>) op.input(inputIndex++);
      config = op.attributes().getAttrString("config");
    }
  }
}
