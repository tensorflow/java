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
import java.util.List;
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
import org.tensorflow.types.TFloat32;

/**
 * An op that retrieves optimization parameters from embedding to host memory.
 * An op that retrieves optimization parameters from embedding to host memory.
 * Must be preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
 * embedding table configuration. For example, this op is used to retrieve updated
 * parameters before saving a checkpoint.  For Adagrad, auxiliary1 will contain the
 * accumulators after running this op. For SGD, all of the auxiliary* values will
 * be empty (0x0 tensors for that table). For FTRL, auxiliary1 will contain the
 * accumulators and auxiliary2 will contain the linear terms. For ADAM, auxiliary1
 * will contain the momenta and auxiliary2 will contain the velocities.
 */
@OpMetadata(
    opType = RetrieveAllTPUEmbeddingParameters.OP_NAME,
    inputsClass = RetrieveAllTPUEmbeddingParameters.Inputs.class
)
public final class RetrieveAllTPUEmbeddingParameters extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RetrieveAllTPUEmbeddingParameters";

  private List<Output<TFloat32>> parameters;

  private List<Output<TFloat32>> auxiliary1;

  private List<Output<TFloat32>> auxiliary2;

  private List<Output<TFloat32>> auxiliary3;

  private List<Output<TFloat32>> auxiliary4;

  private List<Output<TFloat32>> auxiliary5;

  private List<Output<TFloat32>> auxiliary6;

  private List<Output<TFloat32>> auxiliary7;

  @SuppressWarnings("unchecked")
  public RetrieveAllTPUEmbeddingParameters(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int parametersLength = operation.outputListLength("parameters");
    parameters = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, parametersLength));
    outputIdx += parametersLength;
    int auxiliary1Length = operation.outputListLength("auxiliary1");
    auxiliary1 = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, auxiliary1Length));
    outputIdx += auxiliary1Length;
    int auxiliary2Length = operation.outputListLength("auxiliary2");
    auxiliary2 = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, auxiliary2Length));
    outputIdx += auxiliary2Length;
    int auxiliary3Length = operation.outputListLength("auxiliary3");
    auxiliary3 = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, auxiliary3Length));
    outputIdx += auxiliary3Length;
    int auxiliary4Length = operation.outputListLength("auxiliary4");
    auxiliary4 = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, auxiliary4Length));
    outputIdx += auxiliary4Length;
    int auxiliary5Length = operation.outputListLength("auxiliary5");
    auxiliary5 = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, auxiliary5Length));
    outputIdx += auxiliary5Length;
    int auxiliary6Length = operation.outputListLength("auxiliary6");
    auxiliary6 = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, auxiliary6Length));
    outputIdx += auxiliary6Length;
    int auxiliary7Length = operation.outputListLength("auxiliary7");
    auxiliary7 = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, auxiliary7Length));
    outputIdx += auxiliary7Length;
  }

  /**
   * Factory method to create a class wrapping a new RetrieveAllTPUEmbeddingParameters operation.
   *
   * @param scope current scope
   * @param NumTables The number of embedding tables.
   * @param config An TPUEmbeddingConfiguration proto describing the
   * table parameters being loaded, serialized to a string.
   * @param numShards Number of shards into which the embedding tables are divided.
   * @param shardId Identifier of shard for this operation.
   * @return a new instance of RetrieveAllTPUEmbeddingParameters
   */
  @Endpoint(
      describeByClass = true
  )
  public static RetrieveAllTPUEmbeddingParameters create(Scope scope, Long NumTables, String config,
      Long numShards, Long shardId) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RetrieveAllTPUEmbeddingParameters");
    opBuilder.setAttr("NumTables", NumTables);
    opBuilder.setAttr("config", config);
    opBuilder.setAttr("num_shards", numShards);
    opBuilder.setAttr("shard_id", shardId);
    return new RetrieveAllTPUEmbeddingParameters(opBuilder.build());
  }

  /**
   * Gets parameters.
   * A list of tensors, one for each embedding table, containing the
   * stored embedding table parameters.
   * @return parameters.
   */
  public List<Output<TFloat32>> parameters() {
    return parameters;
  }

  /**
   * Gets auxiliary1.
   * A list of tensors, one for each embedding table, containing the
   * first auxiliary optimization parameter stored. Elements are
   * present in the list, but have zero size, for unused optimization parameters
   * (based on the algorithm in use for each table).
   * @return auxiliary1.
   */
  public List<Output<TFloat32>> auxiliary1() {
    return auxiliary1;
  }

  /**
   * Gets auxiliary2.
   * A list of tensors, one for each embedding table, containing the
   * second auxiliary optimization parameter stored. Elements are
   * present in the list, but have zero size, for unused optimization parameters
   * (based on the algorithm in use for each table).
   * @return auxiliary2.
   */
  public List<Output<TFloat32>> auxiliary2() {
    return auxiliary2;
  }

  /**
   * Gets auxiliary3.
   * A list of tensors, one for each embedding table, containing the
   * third auxiliary optimization parameter stored. Elements are
   * present in the list, but have zero size, for unused optimization parameters
   * (based on the algorithm in use for each table).
   * @return auxiliary3.
   */
  public List<Output<TFloat32>> auxiliary3() {
    return auxiliary3;
  }

  /**
   * Gets auxiliary4.
   * A list of tensors, one for each embedding table, containing the
   * fourth auxiliary optimization parameter stored. Elements are
   * present in the list, but have zero size, for unused optimization parameters
   * (based on the algorithm in use for each table).
   * @return auxiliary4.
   */
  public List<Output<TFloat32>> auxiliary4() {
    return auxiliary4;
  }

  /**
   * Gets auxiliary5.
   * A list of tensors, one for each embedding table, containing the
   * fifth auxiliary optimization parameter stored. Elements are
   * present in the list, but have zero size, for unused optimization parameters
   * (based on the algorithm in use for each table).
   * @return auxiliary5.
   */
  public List<Output<TFloat32>> auxiliary5() {
    return auxiliary5;
  }

  /**
   * Gets auxiliary6.
   * A list of tensors, one for each embedding table, containing the
   * six auxiliary optimization parameter stored. Elements are
   * present in the list, but have zero size, for unused optimization parameters
   * (based on the algorithm in use for each table).
   * @return auxiliary6.
   */
  public List<Output<TFloat32>> auxiliary6() {
    return auxiliary6;
  }

  /**
   * Gets auxiliary7.
   * A list of tensors, one for each embedding table, containing the
   * seventh auxiliary optimization parameter stored. Elements are
   * present in the list, but have zero size, for unused optimization parameters
   * (based on the algorithm in use for each table).
   * @return auxiliary7.
   */
  public List<Output<TFloat32>> auxiliary7() {
    return auxiliary7;
  }

  @OpInputsMetadata(
      outputsClass = RetrieveAllTPUEmbeddingParameters.class
  )
  public static class Inputs extends RawOpInputs<RetrieveAllTPUEmbeddingParameters> {
    /**
     * An TPUEmbeddingConfiguration proto describing the
     * table parameters being loaded, serialized to a string.
     */
    public final String config;

    /**
     * Number of shards into which the embedding tables are divided.
     */
    public final long numShards;

    /**
     * Identifier of shard for this operation.
     */
    public final long shardId;

    public Inputs(GraphOperation op) {
      super(new RetrieveAllTPUEmbeddingParameters(op), op, Arrays.asList("config", "num_shards", "shard_id"));
      int inputIndex = 0;
      config = op.attributes().getAttrString("config");
      numShards = op.attributes().getAttrInt("num_shards");
      shardId = op.attributes().getAttrInt("shard_id");
    }
  }
}
