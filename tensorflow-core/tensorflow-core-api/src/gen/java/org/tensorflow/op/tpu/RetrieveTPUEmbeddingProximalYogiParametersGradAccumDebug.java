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

import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;

/**
 */
public final class RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug}
   */
  public static class Options {
    
    /**
     * @param tableId 
     */
    public Options tableId(Long tableId) {
      this.tableId = tableId;
      return this;
    }
    
    /**
     * @param tableName 
     */
    public Options tableName(String tableName) {
      this.tableName = tableName;
      return this;
    }
    
    /**
     * @param config 
     */
    public Options config(String config) {
      this.config = config;
      return this;
    }
    
    private Long tableId;
    private String tableName;
    private String config;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug operation.
   * 
   * @param scope current scope
   * @param numShards 
   * @param shardId 
   * @param options carries optional attributes values
   * @return a new instance of RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug
   */
  @Endpoint(describeByClass = true)
  public static RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug create(Scope scope, Long numShards, Long shardId, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug", scope.makeOpName("RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("num_shards", numShards);
    opBuilder.setAttr("shard_id", shardId);
    if (options != null) {
      for (Options opts : options) {
        if (opts.tableId != null) {
          opBuilder.setAttr("table_id", opts.tableId);
        }
        if (opts.tableName != null) {
          opBuilder.setAttr("table_name", opts.tableName);
        }
        if (opts.config != null) {
          opBuilder.setAttr("config", opts.config);
        }
      }
    }
    return new RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug(opBuilder.build());
  }
  
  /**
   * @param tableId 
   */
  public static Options tableId(Long tableId) {
    return new Options().tableId(tableId);
  }
  
  /**
   * @param tableName 
   */
  public static Options tableName(String tableName) {
    return new Options().tableName(tableName);
  }
  
  /**
   * @param config 
   */
  public static Options config(String config) {
    return new Options().config(config);
  }
  
  /**
   */
  public Output<TFloat32> parameters() {
    return parameters;
  }
  
  /**
   */
  public Output<TFloat32> v() {
    return v;
  }
  
  /**
   */
  public Output<TFloat32> m() {
    return m;
  }
  
  /**
   */
  public Output<TFloat32> gradientAccumulators() {
    return gradientAccumulators;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug";
  
  private Output<TFloat32> parameters;
  private Output<TFloat32> v;
  private Output<TFloat32> m;
  private Output<TFloat32> gradientAccumulators;
  
  private RetrieveTPUEmbeddingProximalYogiParametersGradAccumDebug(Operation operation) {
    super(operation);
    int outputIdx = 0;
    parameters = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
    m = operation.output(outputIdx++);
    gradientAccumulators = operation.output(outputIdx++);
  }
}
