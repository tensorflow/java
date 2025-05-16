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

package org.tensorflow.op.xla;

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
import org.tensorflow.types.family.TType;

/**
 * Receives deduplication data (indices and weights) from the embedding core.
 * The deduplication data is a Tensor with type=DT_VARIANT. The tensor itself is an
 * XLA nested tuple containing N elements (where N is the ratio of the number of
 * embedding to tensor cores per TPU chip). Each element of the nested tuple is a
 * tuple of rank 1 tensors. Each tensor either contains indices (DT_UINT32) for
 * embedding lookup on the TensorCore or weights (DT_FLOAT) to apply to the output
 * of the embedding lookup operation.
 */
@OpMetadata(
    opType = XlaRecvTPUEmbeddingDeduplicationData.OP_NAME,
    inputsClass = XlaRecvTPUEmbeddingDeduplicationData.Inputs.class
)
public final class XlaRecvTPUEmbeddingDeduplicationData extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaRecvTPUEmbeddingDeduplicationDataV2";

  private Output<? extends TType> output;

  @SuppressWarnings("unchecked")
  public XlaRecvTPUEmbeddingDeduplicationData(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaRecvTPUEmbeddingDeduplicationDataV2 operation.
   *
   * @param scope current scope
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @param embeddingPartitions Serialized EmbeddingPartitionsProto proto.
   * @param hbmBuffersConfig Serialized HbmBuffersConfig proto.
   * @param tpuTopology Serialized TpuTopologyArgsProto proto.
   * @return a new instance of XlaRecvTPUEmbeddingDeduplicationData
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaRecvTPUEmbeddingDeduplicationData create(Scope scope, String config,
      String embeddingPartitions, String hbmBuffersConfig, String tpuTopology) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaRecvTPUEmbeddingDeduplicationData");
    opBuilder.setAttr("config", config);
    opBuilder.setAttr("embedding_partitions", embeddingPartitions);
    opBuilder.setAttr("hbm_buffers_config", hbmBuffersConfig);
    opBuilder.setAttr("tpu_topology", tpuTopology);
    return new XlaRecvTPUEmbeddingDeduplicationData(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<? extends TType> output() {
    return output;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }

  @OpInputsMetadata(
      outputsClass = XlaRecvTPUEmbeddingDeduplicationData.class
  )
  public static class Inputs extends RawOpInputs<XlaRecvTPUEmbeddingDeduplicationData> {
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
      super(new XlaRecvTPUEmbeddingDeduplicationData(op), op, Arrays.asList("config", "embedding_partitions", "hbm_buffers_config", "tpu_topology"));
      int inputIndex = 0;
      config = op.attributes().getAttrString("config");
      embeddingPartitions = op.attributes().getAttrString("embedding_partitions");
      hbmBuffersConfig = op.attributes().getAttrString("hbm_buffers_config");
      tpuTopology = op.attributes().getAttrString("tpu_topology");
    }
  }
}
