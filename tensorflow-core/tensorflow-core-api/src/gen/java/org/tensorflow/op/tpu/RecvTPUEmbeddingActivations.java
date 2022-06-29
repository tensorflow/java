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

/**
 * An op that receives embedding activations on the TPU.
 * The TPU system performs the embedding lookups and aggregations specified by
 * the arguments to TPUEmbeddingEnqueue(Integer/Sparse/SparseTensor)Batch. The
 * results of these aggregations are visible to the Tensorflow Graph as the
 * outputs of a RecvTPUEmbeddingActivations op. This op returns a list containing
 * one Tensor of activations per table specified in the model. There can be at
 * most one RecvTPUEmbeddingActivations op in the TPU graph.
 */
@OpMetadata(
    opType = RecvTPUEmbeddingActivations.OP_NAME,
    inputsClass = RecvTPUEmbeddingActivations.Inputs.class
)
public final class RecvTPUEmbeddingActivations extends RawOp implements Iterable<Operand<TFloat32>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RecvTPUEmbeddingActivations";

  private List<Output<TFloat32>> outputs;

  @SuppressWarnings("unchecked")
  public RecvTPUEmbeddingActivations(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new RecvTPUEmbeddingActivations operation.
   *
   * @param scope current scope
   * @param numOutputs The number of output activation tensors, equal to the number of
   * embedding tables in the model.
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @return a new instance of RecvTPUEmbeddingActivations
   */
  @Endpoint(
      describeByClass = true
  )
  public static RecvTPUEmbeddingActivations create(Scope scope, Long numOutputs, String config) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RecvTPUEmbeddingActivations");
    opBuilder.setAttr("num_outputs", numOutputs);
    opBuilder.setAttr("config", config);
    return new RecvTPUEmbeddingActivations(opBuilder.build());
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
      outputsClass = RecvTPUEmbeddingActivations.class
  )
  public static class Inputs extends RawOpInputs<RecvTPUEmbeddingActivations> {
    /**
     * Serialized TPUEmbeddingConfiguration proto.
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new RecvTPUEmbeddingActivations(op), op, Arrays.asList("config"));
      int inputIndex = 0;
      config = op.attributes().getAttrString("config");
    }
  }
}
