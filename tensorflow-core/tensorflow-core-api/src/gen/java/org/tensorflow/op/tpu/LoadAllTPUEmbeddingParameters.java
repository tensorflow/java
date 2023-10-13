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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TFloat32;

/**
 * An op that loads optimization parameters into embedding memory.
 * An op that loads optimization parameters into embedding memory. Must be
 * preceded by a ConfigureTPUEmbeddingHost op that sets up the correct embedding
 * table configuration. For example, this op is used to install parameters that are
 * loaded from a checkpoint before a training loop is executed.  For Adagrad,
 * auxiliary1 should be the accumulators. For SGD, all of the auxiliary* values
 * should be empty. For FTRL, auxiliary1 should be the accumulators and auxiliary2
 * should be the linear terms. For ADAM, auxiliary1 should be the momenta and
 * auxiliary2 should be the velocities.
 */
@OpMetadata(
    opType = LoadAllTPUEmbeddingParameters.OP_NAME,
    inputsClass = LoadAllTPUEmbeddingParameters.Inputs.class
)
public final class LoadAllTPUEmbeddingParameters extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LoadAllTPUEmbeddingParameters";

  public LoadAllTPUEmbeddingParameters(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new LoadAllTPUEmbeddingParameters operation.
   *
   * @param scope current scope
   * @param parameters A list of tensors, one for each embedding table,
   * containing the initial embedding table parameters to use in embedding
   * lookups.
   * @param auxiliary1 A list of tensors, one for each embedding table, containing the
   * initial values of the first auxiliary optimization parameter to use in embedding
   * training loop updates. The shape of each entry is ignored (and thus can be
   * empty) for those tables whose optimization algorithms do not have at least one
   * auxiliary parameter.
   * @param auxiliary2 A list of tensors, one for each embedding table, containing the
   * initial values of the second auxiliary optimization parameter to use in
   * embedding training loop updates. The shape of each entry is ignored (and thus
   * can be empty) for those tables whose optimization algorithms do not have at
   * least two auxiliary
   * @param auxiliary3 A list of tensors, one for each embedding table, containing the
   * initial values of the third auxiliary optimization parameter to use in embedding
   * training loop updates. The shape of each entry is ignored (and thus can be
   * empty) for those tables whose optimization algorithms do not have three
   * auxiliary parameters.
   * @param auxiliary4 A list of tensors, one for each embedding table, containing the
   * initial values of the second auxiliary optimization parameter to use in
   * embedding training loop updates. The shape of each entry is ignored (and thus
   * can be empty) for those tables whose optimization algorithms do not have at
   * least four auxiliary
   * @param auxiliary5 A list of tensors, one for each embedding table, containing the
   * initial values of the third auxiliary optimization parameter to use in embedding
   * training loop updates. The shape of each entry is ignored (and thus can be
   * empty) for those tables whose optimization algorithms do not have five
   * auxiliary parameters.
   * @param auxiliary6 A list of tensors, one for each embedding table, containing the
   * initial values of the second auxiliary optimization parameter to use in
   * embedding training loop updates. The shape of each entry is ignored (and thus
   * can be empty) for those tables whose optimization algorithms do not have at
   * least six auxiliary
   * @param auxiliary7 A list of tensors, one for each embedding table, containing the
   * initial values of the third auxiliary optimization parameter to use in embedding
   * training loop updates. The shape of each entry is ignored (and thus can be
   * empty) for those tables whose optimization algorithms do not have sevan
   * auxiliary parameters.
   * @param config An TPUEmbeddingConfiguration proto describing the
   * table parameters being loaded, serialized to a string.
   * @param numShards Number of shards into which the embedding tables are divided.
   * @param shardId Identifier of shard for this operation.
   * @return a new instance of LoadAllTPUEmbeddingParameters
   */
  @Endpoint(
      describeByClass = true
  )
  public static LoadAllTPUEmbeddingParameters create(Scope scope,
      Iterable<Operand<TFloat32>> parameters, Iterable<Operand<TFloat32>> auxiliary1,
      Iterable<Operand<TFloat32>> auxiliary2, Iterable<Operand<TFloat32>> auxiliary3,
      Iterable<Operand<TFloat32>> auxiliary4, Iterable<Operand<TFloat32>> auxiliary5,
      Iterable<Operand<TFloat32>> auxiliary6, Iterable<Operand<TFloat32>> auxiliary7, String config,
      Long numShards, Long shardId) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LoadAllTPUEmbeddingParameters");
    opBuilder.addInputList(Operands.asOutputs(parameters));
    opBuilder.addInputList(Operands.asOutputs(auxiliary1));
    opBuilder.addInputList(Operands.asOutputs(auxiliary2));
    opBuilder.addInputList(Operands.asOutputs(auxiliary3));
    opBuilder.addInputList(Operands.asOutputs(auxiliary4));
    opBuilder.addInputList(Operands.asOutputs(auxiliary5));
    opBuilder.addInputList(Operands.asOutputs(auxiliary6));
    opBuilder.addInputList(Operands.asOutputs(auxiliary7));
    opBuilder.setAttr("config", config);
    opBuilder.setAttr("num_shards", numShards);
    opBuilder.setAttr("shard_id", shardId);
    return new LoadAllTPUEmbeddingParameters(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = LoadAllTPUEmbeddingParameters.class
  )
  public static class Inputs extends RawOpInputs<LoadAllTPUEmbeddingParameters> {
    /**
     * A list of tensors, one for each embedding table,
     * containing the initial embedding table parameters to use in embedding
     * lookups.
     */
    public final Iterable<Operand<TFloat32>> parameters;

    /**
     * A list of tensors, one for each embedding table, containing the
     * initial values of the first auxiliary optimization parameter to use in embedding
     * training loop updates. The shape of each entry is ignored (and thus can be
     * empty) for those tables whose optimization algorithms do not have at least one
     * auxiliary parameter.
     */
    public final Iterable<Operand<TFloat32>> auxiliary1;

    /**
     * A list of tensors, one for each embedding table, containing the
     * initial values of the second auxiliary optimization parameter to use in
     * embedding training loop updates. The shape of each entry is ignored (and thus
     * can be empty) for those tables whose optimization algorithms do not have at
     * least two auxiliary
     */
    public final Iterable<Operand<TFloat32>> auxiliary2;

    /**
     * A list of tensors, one for each embedding table, containing the
     * initial values of the third auxiliary optimization parameter to use in embedding
     * training loop updates. The shape of each entry is ignored (and thus can be
     * empty) for those tables whose optimization algorithms do not have three
     * auxiliary parameters.
     */
    public final Iterable<Operand<TFloat32>> auxiliary3;

    /**
     * A list of tensors, one for each embedding table, containing the
     * initial values of the second auxiliary optimization parameter to use in
     * embedding training loop updates. The shape of each entry is ignored (and thus
     * can be empty) for those tables whose optimization algorithms do not have at
     * least four auxiliary
     */
    public final Iterable<Operand<TFloat32>> auxiliary4;

    /**
     * A list of tensors, one for each embedding table, containing the
     * initial values of the third auxiliary optimization parameter to use in embedding
     * training loop updates. The shape of each entry is ignored (and thus can be
     * empty) for those tables whose optimization algorithms do not have five
     * auxiliary parameters.
     */
    public final Iterable<Operand<TFloat32>> auxiliary5;

    /**
     * A list of tensors, one for each embedding table, containing the
     * initial values of the second auxiliary optimization parameter to use in
     * embedding training loop updates. The shape of each entry is ignored (and thus
     * can be empty) for those tables whose optimization algorithms do not have at
     * least six auxiliary
     */
    public final Iterable<Operand<TFloat32>> auxiliary6;

    /**
     * A list of tensors, one for each embedding table, containing the
     * initial values of the third auxiliary optimization parameter to use in embedding
     * training loop updates. The shape of each entry is ignored (and thus can be
     * empty) for those tables whose optimization algorithms do not have sevan
     * auxiliary parameters.
     */
    public final Iterable<Operand<TFloat32>> auxiliary7;

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
      super(new LoadAllTPUEmbeddingParameters(op), op, Arrays.asList("config", "num_shards", "shard_id"));
      int inputIndex = 0;
      int parametersLength = op.inputListLength("parameters");
      parameters = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, parametersLength));
      inputIndex += parametersLength;
      int auxiliary1Length = op.inputListLength("auxiliary1");
      auxiliary1 = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, auxiliary1Length));
      inputIndex += auxiliary1Length;
      int auxiliary2Length = op.inputListLength("auxiliary2");
      auxiliary2 = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, auxiliary2Length));
      inputIndex += auxiliary2Length;
      int auxiliary3Length = op.inputListLength("auxiliary3");
      auxiliary3 = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, auxiliary3Length));
      inputIndex += auxiliary3Length;
      int auxiliary4Length = op.inputListLength("auxiliary4");
      auxiliary4 = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, auxiliary4Length));
      inputIndex += auxiliary4Length;
      int auxiliary5Length = op.inputListLength("auxiliary5");
      auxiliary5 = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, auxiliary5Length));
      inputIndex += auxiliary5Length;
      int auxiliary6Length = op.inputListLength("auxiliary6");
      auxiliary6 = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, auxiliary6Length));
      inputIndex += auxiliary6Length;
      int auxiliary7Length = op.inputListLength("auxiliary7");
      auxiliary7 = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, auxiliary7Length));
      inputIndex += auxiliary7Length;
      config = op.attributes().getAttrString("config");
      numShards = op.attributes().getAttrInt("num_shards");
      shardId = op.attributes().getAttrInt("shard_id");
    }
  }
}
