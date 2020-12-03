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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Sets up the centralized structures for a distributed TPU system.
 */
public final class ConfigureDistributedTPU extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.ConfigureDistributedTPU}
   */
  public static class Options {
    
    /**
     * @param embeddingConfig Reserved. Do not use.
     */
    public Options embeddingConfig(String embeddingConfig) {
      this.embeddingConfig = embeddingConfig;
      return this;
    }
    
    /**
     * @param tpuEmbeddingConfig Serialized tensorflow.tpu.TPUEmbeddingConfiguration that
     * describes the embedding lookups of the program.
     */
    public Options tpuEmbeddingConfig(String tpuEmbeddingConfig) {
      this.tpuEmbeddingConfig = tpuEmbeddingConfig;
      return this;
    }
    
    /**
     * @param isGlobalInit Reserved. Do not use.
     */
    public Options isGlobalInit(Boolean isGlobalInit) {
      this.isGlobalInit = isGlobalInit;
      return this;
    }
    
    /**
     * @param enableWholeMeshCompilations 
     */
    public Options enableWholeMeshCompilations(Boolean enableWholeMeshCompilations) {
      this.enableWholeMeshCompilations = enableWholeMeshCompilations;
      return this;
    }
    
    /**
     * @param compilationFailureClosesChips 
     */
    public Options compilationFailureClosesChips(Boolean compilationFailureClosesChips) {
      this.compilationFailureClosesChips = compilationFailureClosesChips;
      return this;
    }
    
    private String embeddingConfig;
    private String tpuEmbeddingConfig;
    private Boolean isGlobalInit;
    private Boolean enableWholeMeshCompilations;
    private Boolean compilationFailureClosesChips;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ConfigureDistributedTPU operation.
   * 
   * @param scope current scope
   * @param options carries optional attributes values
   * @return a new instance of ConfigureDistributedTPU
   */
  @Endpoint(describeByClass = true)
  public static ConfigureDistributedTPU create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ConfigureDistributedTPU", scope.makeOpName("ConfigureDistributedTPU"));
    opBuilder = scope.apply(opBuilder);
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
      }
    }
    return new ConfigureDistributedTPU(opBuilder.build());
  }
  
  /**
   * @param embeddingConfig Reserved. Do not use.
   */
  public static Options embeddingConfig(String embeddingConfig) {
    return new Options().embeddingConfig(embeddingConfig);
  }
  
  /**
   * @param tpuEmbeddingConfig Serialized tensorflow.tpu.TPUEmbeddingConfiguration that
   * describes the embedding lookups of the program.
   */
  public static Options tpuEmbeddingConfig(String tpuEmbeddingConfig) {
    return new Options().tpuEmbeddingConfig(tpuEmbeddingConfig);
  }
  
  /**
   * @param isGlobalInit Reserved. Do not use.
   */
  public static Options isGlobalInit(Boolean isGlobalInit) {
    return new Options().isGlobalInit(isGlobalInit);
  }
  
  /**
   * @param enableWholeMeshCompilations 
   */
  public static Options enableWholeMeshCompilations(Boolean enableWholeMeshCompilations) {
    return new Options().enableWholeMeshCompilations(enableWholeMeshCompilations);
  }
  
  /**
   * @param compilationFailureClosesChips 
   */
  public static Options compilationFailureClosesChips(Boolean compilationFailureClosesChips) {
    return new Options().compilationFailureClosesChips(compilationFailureClosesChips);
  }
  
  /**
   * A serialized tensorflow.tpu.TopologyProto that describes the TPU
   * topology.
   */
  public Output<TString> topology() {
    return topology;
  }
  
  @Override
  public Output<TString> asOutput() {
    return topology;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ConfigureDistributedTPU";
  
  private Output<TString> topology;
  
  private ConfigureDistributedTPU(Operation operation) {
    super(operation);
    int outputIdx = 0;
    topology = operation.output(outputIdx++);
  }
}
