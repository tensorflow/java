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
import org.tensorflow.types.TInt32;

/**
 * An op computes the size of the deduplication data from embedding core and returns the updated config.
 * This op is to compute size of the deduplication data so to provide this
 * information to the op that computes the tuple mask of deduplication data can
 * have static output shape.
 */
@OpMetadata(
    opType = ComputeDedupDataSize.OP_NAME,
    inputsClass = ComputeDedupDataSize.Inputs.class
)
public final class ComputeDedupDataSize extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ComputeDedupDataSizeV2";

  private Output<TInt32> numElements;

  public ComputeDedupDataSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    numElements = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ComputeDedupDataSizeV2 operation.
   *
   * @param scope current scope
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @param embeddingPartitions Serialized EmbeddingPartitionsProto proto.
   * @param hbmBuffersConfig Serialized HbmBuffersConfig proto.
   * @param tpuTopology Serialized TpuTopologyArgsProto proto.
   * @return a new instance of ComputeDedupDataSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static ComputeDedupDataSize create(Scope scope, String config, String embeddingPartitions,
      String hbmBuffersConfig, String tpuTopology) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ComputeDedupDataSize");
    opBuilder.setAttr("config", config);
    opBuilder.setAttr("embedding_partitions", embeddingPartitions);
    opBuilder.setAttr("hbm_buffers_config", hbmBuffersConfig);
    opBuilder.setAttr("tpu_topology", tpuTopology);
    return new ComputeDedupDataSize(opBuilder.build());
  }

  /**
   * Gets numElements.
   * The size of the deduplicated data from infeed.
   * @return numElements.
   */
  public Output<TInt32> numElements() {
    return numElements;
  }

  @Override
  public Output<TInt32> asOutput() {
    return numElements;
  }

  @OpInputsMetadata(
      outputsClass = ComputeDedupDataSize.class
  )
  public static class Inputs extends RawOpInputs<ComputeDedupDataSize> {
    /**
     * Serialized TPUEmbeddingConfiguration proto.
     */
    public final String config;

    /**
     * Serialized EmbeddingPartitionsProto proto.
     */
    public final String embeddingPartitions;

    /**
     * Serialized HbmBuffersConfig proto.
     */
    public final String hbmBuffersConfig;

    /**
     * Serialized TpuTopologyArgsProto proto.
     */
    public final String tpuTopology;

    public Inputs(GraphOperation op) {
      super(new ComputeDedupDataSize(op), op, Arrays.asList("config", "embedding_partitions", "hbm_buffers_config", "tpu_topology"));
      int inputIndex = 0;
      config = op.attributes().getAttrString("config");
      embeddingPartitions = op.attributes().getAttrString("embedding_partitions");
      hbmBuffersConfig = op.attributes().getAttrString("hbm_buffers_config");
      tpuTopology = op.attributes().getAttrString("tpu_topology");
    }
  }
}
