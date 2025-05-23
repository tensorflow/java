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
import java.util.Iterator;
import java.util.List;
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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * An op that receives embedding activations on the TPU.
 * The TPU system performs the embedding lookups and aggregations. The results of
 * these aggregations are visible to the Tensorflow Graph as the outputs of a
 * XlaRecvTPUEmbeddingActivations Op. This op returns a list containing one
 * Tensor of activations per table specified in the model.
 */
@OpMetadata(
    opType = XlaRecvTPUEmbeddingActivations.OP_NAME,
    inputsClass = XlaRecvTPUEmbeddingActivations.Inputs.class
)
public final class XlaRecvTPUEmbeddingActivations extends RawOp implements Iterable<Operand<TFloat32>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaRecvTPUEmbeddingActivationsV2";

  private List<Output<TFloat32>> outputs;

  @SuppressWarnings("unchecked")
  public XlaRecvTPUEmbeddingActivations(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaRecvTPUEmbeddingActivationsV2 operation.
   *
   * @param scope current scope
   * @param deduplicationData A Tensor with type=DT_VARIANT containing the deduplication
   * data. The tensor is an XLA nested tuple containing N elements (where N is
   * the ratio of the number of embedding to tensor cores per TPU chip). Each
   * element of the nested tuple is a tuple of rank 1 tensors. Each tensor either
   * contains indices (DT_UINT32) for embedding lookup on the TensorCore or
   * weights (DT_FLOAT) to apply to the output of the embedding lookup operation.
   * @param numTables The number of output activation tensors. If feature descriptor is
   * present in the tpu embedding config, it is equal to the number of features
   * otherwise equal to number of embedding tables in the model.
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @param embeddingPartitions Serialized EmbeddingPartitionsProto proto.
   * @param hbmBuffersConfig Serialized HbmBuffersConfig proto.
   * @param tpuTopology Serialized TpuTopologyArgsProto proto.
   * @return a new instance of XlaRecvTPUEmbeddingActivations
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaRecvTPUEmbeddingActivations create(Scope scope,
      Operand<? extends TType> deduplicationData, Long numTables, String config,
      String embeddingPartitions, String hbmBuffersConfig, String tpuTopology) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaRecvTPUEmbeddingActivations");
    opBuilder.addInput(deduplicationData.asOutput());
    opBuilder.setAttr("num_tables", numTables);
    opBuilder.setAttr("config", config);
    opBuilder.setAttr("embedding_partitions", embeddingPartitions);
    opBuilder.setAttr("hbm_buffers_config", hbmBuffersConfig);
    opBuilder.setAttr("tpu_topology", tpuTopology);
    return new XlaRecvTPUEmbeddingActivations(opBuilder.build());
  }

  /**
   * Gets outputs.
   * A TensorList of embedding activations containing one Tensor per
   * embedding table in the model.
   * @return outputs.
   */
  public List<Output<TFloat32>> outputs() {
    return outputs;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TFloat32>> iterator() {
    return (Iterator) outputs.iterator();
  }

  @OpInputsMetadata(
      outputsClass = XlaRecvTPUEmbeddingActivations.class
  )
  public static class Inputs extends RawOpInputs<XlaRecvTPUEmbeddingActivations> {
    /**
     * A Tensor with type=DT_VARIANT containing the deduplication
     * data. The tensor is an XLA nested tuple containing N elements (where N is
     * the ratio of the number of embedding to tensor cores per TPU chip). Each
     * element of the nested tuple is a tuple of rank 1 tensors. Each tensor either
     * contains indices (DT_UINT32) for embedding lookup on the TensorCore or
     * weights (DT_FLOAT) to apply to the output of the embedding lookup operation.
     */
    public final Operand<? extends TType> deduplicationData;

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
      super(new XlaRecvTPUEmbeddingActivations(op), op, Arrays.asList("config", "embedding_partitions", "hbm_buffers_config", "tpu_topology"));
      int inputIndex = 0;
      deduplicationData = (Operand<? extends TType>) op.input(inputIndex++);
      config = op.attributes().getAttrString("config");
      embeddingPartitions = op.attributes().getAttrString("embedding_partitions");
      hbmBuffersConfig = op.attributes().getAttrString("hbm_buffers_config");
      tpuTopology = op.attributes().getAttrString("tpu_topology");
    }
  }
}
