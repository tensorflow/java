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
import org.tensorflow.types.TFloat32;

/**
 * The LoadTPUEmbeddingProximalYogiParameters operation
 */
@OpMetadata(
    opType = LoadTPUEmbeddingProximalYogiParameters.OP_NAME,
    inputsClass = LoadTPUEmbeddingProximalYogiParameters.Inputs.class
)
public final class LoadTPUEmbeddingProximalYogiParameters extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LoadTPUEmbeddingProximalYogiParameters";

  public LoadTPUEmbeddingProximalYogiParameters(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new LoadTPUEmbeddingProximalYogiParameters operation.
   *
   * @param scope current scope
   * @param parameters The parameters value
   * @param v The v value
   * @param m The m value
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingProximalYogiParameters
   */
  @Endpoint(
      describeByClass = true
  )
  public static LoadTPUEmbeddingProximalYogiParameters create(Scope scope,
      Operand<TFloat32> parameters, Operand<TFloat32> v, Operand<TFloat32> m, Long numShards,
      Long shardId, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LoadTPUEmbeddingProximalYogiParameters");
    opBuilder.addInput(parameters.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder.addInput(m.asOutput());
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
    return new LoadTPUEmbeddingProximalYogiParameters(opBuilder.build());
  }

  /**
   * Sets the tableId option.
   *
   * @param tableId the tableId option
   * @return this Options instance.
   */
  public static Options tableId(Long tableId) {
    return new Options().tableId(tableId);
  }

  /**
   * Sets the tableName option.
   *
   * @param tableName the tableName option
   * @return this Options instance.
   */
  public static Options tableName(String tableName) {
    return new Options().tableName(tableName);
  }

  /**
   * Sets the config option.
   *
   * @param config the config option
   * @return this Options instance.
   */
  public static Options config(String config) {
    return new Options().config(config);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.LoadTPUEmbeddingProximalYogiParameters}
   */
  public static class Options {
    private Long tableId;

    private String tableName;

    private String config;

    private Options() {
    }

    /**
     * Sets the tableId option.
     *
     * @param tableId the tableId option
     * @return this Options instance.
     */
    public Options tableId(Long tableId) {
      this.tableId = tableId;
      return this;
    }

    /**
     * Sets the tableName option.
     *
     * @param tableName the tableName option
     * @return this Options instance.
     */
    public Options tableName(String tableName) {
      this.tableName = tableName;
      return this;
    }

    /**
     * Sets the config option.
     *
     * @param config the config option
     * @return this Options instance.
     */
    public Options config(String config) {
      this.config = config;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = LoadTPUEmbeddingProximalYogiParameters.class
  )
  public static class Inputs extends RawOpInputs<LoadTPUEmbeddingProximalYogiParameters> {
    /**
     * The parameters input
     */
    public final Operand<TFloat32> parameters;

    /**
     * The v input
     */
    public final Operand<TFloat32> v;

    /**
     * The m input
     */
    public final Operand<TFloat32> m;

    /**
     * The tableId attribute
     */
    public final long tableId;

    /**
     * The tableName attribute
     */
    public final String tableName;

    /**
     * The numShards attribute
     */
    public final long numShards;

    /**
     * The shardId attribute
     */
    public final long shardId;

    /**
     * The config attribute
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new LoadTPUEmbeddingProximalYogiParameters(op), op, Arrays.asList("table_id", "table_name", "num_shards", "shard_id", "config"));
      int inputIndex = 0;
      parameters = (Operand<TFloat32>) op.input(inputIndex++);
      v = (Operand<TFloat32>) op.input(inputIndex++);
      m = (Operand<TFloat32>) op.input(inputIndex++);
      tableId = op.attributes().getAttrInt("table_id");
      tableName = op.attributes().getAttrString("table_name");
      numShards = op.attributes().getAttrInt("num_shards");
      shardId = op.attributes().getAttrInt("shard_id");
      config = op.attributes().getAttrString("config");
    }
  }
}
