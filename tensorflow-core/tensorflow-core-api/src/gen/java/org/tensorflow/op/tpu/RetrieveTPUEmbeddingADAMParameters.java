/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.types.TFloat32;

/**
 * Retrieve ADAM embedding parameters.
 * An op that retrieves optimization parameters from embedding to host
 * memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
 * the correct embedding table configuration. For example, this op is
 * used to retrieve updated parameters before saving a checkpoint.
 */
@OpMetadata(
    opType = RetrieveTPUEmbeddingADAMParameters.OP_NAME,
    inputsClass = RetrieveTPUEmbeddingADAMParameters.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class RetrieveTPUEmbeddingADAMParameters extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RetrieveTPUEmbeddingADAMParameters";

  private Output<TFloat32> parameters;

  private Output<TFloat32> momenta;

  private Output<TFloat32> velocities;

  public RetrieveTPUEmbeddingADAMParameters(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    parameters = operation.output(outputIdx++);
    momenta = operation.output(outputIdx++);
    velocities = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RetrieveTPUEmbeddingADAMParameters operation.
   *
   * @param scope current scope
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingADAMParameters
   */
  @Endpoint(
      describeByClass = true
  )
  public static RetrieveTPUEmbeddingADAMParameters create(Scope scope, Long numShards, Long shardId,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RetrieveTPUEmbeddingADAMParameters");
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
    return new RetrieveTPUEmbeddingADAMParameters(opBuilder.build());
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
   * Gets parameters.
   * Parameter parameters updated by the ADAM optimization algorithm.
   * @return parameters.
   */
  public Output<TFloat32> parameters() {
    return parameters;
  }

  /**
   * Gets momenta.
   * Parameter momenta updated by the ADAM optimization algorithm.
   * @return momenta.
   */
  public Output<TFloat32> momenta() {
    return momenta;
  }

  /**
   * Gets velocities.
   * Parameter velocities updated by the ADAM optimization algorithm.
   * @return velocities.
   */
  public Output<TFloat32> velocities() {
    return velocities;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.RetrieveTPUEmbeddingADAMParameters}
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
      outputsClass = RetrieveTPUEmbeddingADAMParameters.class
  )
  public static class Inputs extends RawOpInputs<RetrieveTPUEmbeddingADAMParameters> {
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
      super(new RetrieveTPUEmbeddingADAMParameters(op), op, Arrays.asList("table_id", "table_name", "num_shards", "shard_id", "config"));
      int inputIndex = 0;
      tableId = op.attributes().getAttrInt("table_id");
      tableName = op.attributes().getAttrString("table_name");
      numShards = op.attributes().getAttrInt("num_shards");
      shardId = op.attributes().getAttrInt("shard_id");
      config = op.attributes().getAttrString("config");
    }
  }
}
