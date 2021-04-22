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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TFloat32;

/**
 * Load ADAM embedding parameters with debug support.
 * An op that loads optimization parameters into HBM for embedding. Must be
 * preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
 * embedding table configuration. For example, this op is used to install
 * parameters that are loaded from a checkpoint before a training loop is
 * executed.
 */
public final class LoadTPUEmbeddingADAMParametersGradAccumDebug extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LoadTPUEmbeddingADAMParametersGradAccumDebug";

  private LoadTPUEmbeddingADAMParametersGradAccumDebug(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new LoadTPUEmbeddingADAMParametersGradAccumDebug operation.
   *
   * @param scope current scope
   * @param parameters Value of parameters used in the ADAM optimization algorithm.
   * @param momenta Value of momenta used in the ADAM optimization algorithm.
   * @param velocities Value of velocities used in the ADAM optimization algorithm.
   * @param gradientAccumulators Value of gradient_accumulators used in the ADAM optimization algorithm.
   * @param numShards the value of the numShards property
   * @param shardId the value of the shardId property
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingADAMParametersGradAccumDebug
   */
  @Endpoint(
      describeByClass = true
  )
  public static LoadTPUEmbeddingADAMParametersGradAccumDebug create(Scope scope,
      Operand<TFloat32> parameters, Operand<TFloat32> momenta, Operand<TFloat32> velocities,
      Operand<TFloat32> gradientAccumulators, Long numShards, Long shardId, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("LoadTPUEmbeddingADAMParametersGradAccumDebug", scope.makeOpName("LoadTPUEmbeddingADAMParametersGradAccumDebug"));
    opBuilder.addInput(parameters.asOutput());
    opBuilder.addInput(momenta.asOutput());
    opBuilder.addInput(velocities.asOutput());
    opBuilder.addInput(gradientAccumulators.asOutput());
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
    return new LoadTPUEmbeddingADAMParametersGradAccumDebug(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.tpu.LoadTPUEmbeddingADAMParametersGradAccumDebug}
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
}
