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
import org.tensorflow.types.TString;

/**
 * Sets up the centralized structures for a distributed TPU system.
 */
@OpMetadata(
    opType = ConfigureDistributedTPU.OP_NAME,
    inputsClass = ConfigureDistributedTPU.Inputs.class
)
public final class ConfigureDistributedTPU extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConfigureDistributedTPU";

  private Output<TString> topology;

  public ConfigureDistributedTPU(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    topology = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ConfigureDistributedTPU operation.
   *
   * @param scope current scope
   * @param options carries optional attribute values
   * @return a new instance of ConfigureDistributedTPU
   */
  @Endpoint(
      describeByClass = true
  )
  public static ConfigureDistributedTPU create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConfigureDistributedTPU");
    if (options != null) {
      for (Options opts : options) {
        if (opts.embeddingConfig != null) {
          opBuilder.setAttr("embedding_config", opts.embeddingConfig);
        }
        if (opts.tpuEmbeddingConfig != null) {
          opBuilder.setAttr("tpu_embedding_config", opts.tpuEmbeddingConfig);
        }
        if (opts.isGlobalInit != null) {
          opBuilder.setAttr("is_global_init", opts.isGlobalInit);
        }
        if (opts.enableWholeMeshCompilations != null) {
          opBuilder.setAttr("enable_whole_mesh_compilations", opts.enableWholeMeshCompilations);
        }
        if (opts.compilationFailureClosesChips != null) {
          opBuilder.setAttr("compilation_failure_closes_chips", opts.compilationFailureClosesChips);
        }
        if (opts.tpuCancellationClosesChips != null) {
          opBuilder.setAttr("tpu_cancellation_closes_chips", opts.tpuCancellationClosesChips);
        }
      }
    }
    return new ConfigureDistributedTPU(opBuilder.build());
  }

  /**
   * Sets the embeddingConfig option.
   *
   * @param embeddingConfig Reserved. Do not use.
   * @return this Options instance.
   */
  public static Options embeddingConfig(String embeddingConfig) {
    return new Options().embeddingConfig(embeddingConfig);
  }

  /**
   * Sets the tpuEmbeddingConfig option.
   *
   * @param tpuEmbeddingConfig Serialized tensorflow.tpu.TPUEmbeddingConfiguration that
   * describes the embedding lookups of the program.
   * @return this Options instance.
   */
  public static Options tpuEmbeddingConfig(String tpuEmbeddingConfig) {
    return new Options().tpuEmbeddingConfig(tpuEmbeddingConfig);
  }

  /**
   * Sets the isGlobalInit option.
   *
   * @param isGlobalInit Reserved. Do not use.
   * @return this Options instance.
   */
  public static Options isGlobalInit(Boolean isGlobalInit) {
    return new Options().isGlobalInit(isGlobalInit);
  }

  /**
   * Sets the enableWholeMeshCompilations option.
   *
   * @param enableWholeMeshCompilations the enableWholeMeshCompilations option
   * @return this Options instance.
   */
  public static Options enableWholeMeshCompilations(Boolean enableWholeMeshCompilations) {
    return new Options().enableWholeMeshCompilations(enableWholeMeshCompilations);
  }

  /**
   * Sets the compilationFailureClosesChips option.
   *
   * @param compilationFailureClosesChips the compilationFailureClosesChips option
   * @return this Options instance.
   */
  public static Options compilationFailureClosesChips(Boolean compilationFailureClosesChips) {
    return new Options().compilationFailureClosesChips(compilationFailureClosesChips);
  }

  /**
   * Sets the tpuCancellationClosesChips option.
   *
   * @param tpuCancellationClosesChips the tpuCancellationClosesChips option
   * @return this Options instance.
   */
  public static Options tpuCancellationClosesChips(Long tpuCancellationClosesChips) {
    return new Options().tpuCancellationClosesChips(tpuCancellationClosesChips);
  }

  /**
   * Gets topology.
   * A serialized tensorflow.tpu.TopologyProto that describes the TPU
   * topology.
   * @return topology.
   */
  public Output<TString> topology() {
    return topology;
  }

  @Override
  public Output<TString> asOutput() {
    return topology;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.ConfigureDistributedTPU}
   */
  public static class Options {
    private String embeddingConfig;

    private String tpuEmbeddingConfig;

    private Boolean isGlobalInit;

    private Boolean enableWholeMeshCompilations;

    private Boolean compilationFailureClosesChips;

    private Long tpuCancellationClosesChips;

    private Options() {
    }

    /**
     * Sets the embeddingConfig option.
     *
     * @param embeddingConfig Reserved. Do not use.
     * @return this Options instance.
     */
    public Options embeddingConfig(String embeddingConfig) {
      this.embeddingConfig = embeddingConfig;
      return this;
    }

    /**
     * Sets the tpuEmbeddingConfig option.
     *
     * @param tpuEmbeddingConfig Serialized tensorflow.tpu.TPUEmbeddingConfiguration that
     * describes the embedding lookups of the program.
     * @return this Options instance.
     */
    public Options tpuEmbeddingConfig(String tpuEmbeddingConfig) {
      this.tpuEmbeddingConfig = tpuEmbeddingConfig;
      return this;
    }

    /**
     * Sets the isGlobalInit option.
     *
     * @param isGlobalInit Reserved. Do not use.
     * @return this Options instance.
     */
    public Options isGlobalInit(Boolean isGlobalInit) {
      this.isGlobalInit = isGlobalInit;
      return this;
    }

    /**
     * Sets the enableWholeMeshCompilations option.
     *
     * @param enableWholeMeshCompilations the enableWholeMeshCompilations option
     * @return this Options instance.
     */
    public Options enableWholeMeshCompilations(Boolean enableWholeMeshCompilations) {
      this.enableWholeMeshCompilations = enableWholeMeshCompilations;
      return this;
    }

    /**
     * Sets the compilationFailureClosesChips option.
     *
     * @param compilationFailureClosesChips the compilationFailureClosesChips option
     * @return this Options instance.
     */
    public Options compilationFailureClosesChips(Boolean compilationFailureClosesChips) {
      this.compilationFailureClosesChips = compilationFailureClosesChips;
      return this;
    }

    /**
     * Sets the tpuCancellationClosesChips option.
     *
     * @param tpuCancellationClosesChips the tpuCancellationClosesChips option
     * @return this Options instance.
     */
    public Options tpuCancellationClosesChips(Long tpuCancellationClosesChips) {
      this.tpuCancellationClosesChips = tpuCancellationClosesChips;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ConfigureDistributedTPU.class
  )
  public static class Inputs extends RawOpInputs<ConfigureDistributedTPU> {
    /**
     * Reserved. Do not use.
     */
    public final String embeddingConfig;

    /**
     * Serialized tensorflow.tpu.TPUEmbeddingConfiguration that
     * describes the embedding lookups of the program.
     */
    public final String tpuEmbeddingConfig;

    /**
     * Reserved. Do not use.
     */
    public final boolean isGlobalInit;

    /**
     * The enableWholeMeshCompilations attribute
     */
    public final boolean enableWholeMeshCompilations;

    /**
     * The compilationFailureClosesChips attribute
     */
    public final boolean compilationFailureClosesChips;

    /**
     * The tpuCancellationClosesChips attribute
     */
    public final long tpuCancellationClosesChips;

    public Inputs(GraphOperation op) {
      super(new ConfigureDistributedTPU(op), op, Arrays.asList("embedding_config", "tpu_embedding_config", "is_global_init", "enable_whole_mesh_compilations", "compilation_failure_closes_chips", "tpu_cancellation_closes_chips"));
      int inputIndex = 0;
      embeddingConfig = op.attributes().getAttrString("embedding_config");
      tpuEmbeddingConfig = op.attributes().getAttrString("tpu_embedding_config");
      isGlobalInit = op.attributes().getAttrBool("is_global_init");
      enableWholeMeshCompilations = op.attributes().getAttrBool("enable_whole_mesh_compilations");
      compilationFailureClosesChips = op.attributes().getAttrBool("compilation_failure_closes_chips");
      tpuCancellationClosesChips = op.attributes().getAttrInt("tpu_cancellation_closes_chips");
    }
  }
}
