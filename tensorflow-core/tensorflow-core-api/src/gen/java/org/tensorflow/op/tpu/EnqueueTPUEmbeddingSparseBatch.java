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
import java.util.List;
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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * An op that enqueues TPUEmbedding input indices from a SparseTensor.
 * This Op eases the porting of code that uses embedding_lookup_sparse(),
 * although some Python preprocessing of the SparseTensor arguments to
 * embedding_lookup_sparse() is required to produce the arguments to this Op,
 * since only a single EnqueueTPUEmbeddingSparseBatch Op is allowed per training
 * step.
 * <p>The tensors at corresponding positions in the three input lists
 * must have the same shape, i.e. rank 1 with dim_size() equal to the total
 * number of lookups into the table described by the corresponding table_id.
 */
@OpMetadata(
    opType = EnqueueTPUEmbeddingSparseBatch.OP_NAME,
    inputsClass = EnqueueTPUEmbeddingSparseBatch.Inputs.class
)
public final class EnqueueTPUEmbeddingSparseBatch extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EnqueueTPUEmbeddingSparseBatch";

  public EnqueueTPUEmbeddingSparseBatch(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new EnqueueTPUEmbeddingSparseBatch operation.
   *
   * @param scope current scope
   * @param sampleIndices A list of rank 1 Tensors specifying the training example and
   * feature to which the corresponding embedding_indices and aggregation_weights
   * values belong. sample_indices[i] must equal b * nf + f, where nf is the
   * number of features from the corresponding table, f is in [0, nf), and
   * b is in [0, batch size).
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding tables.
   * @param aggregationWeights A list of rank 1 Tensors containing per sample -- i.e. per
   * (training example, feature) -- aggregation weights.
   * @param modeOverride A string input that overrides the mode specified in the
   * TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   * 'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   * in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingSparseBatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static EnqueueTPUEmbeddingSparseBatch create(Scope scope,
      Iterable<Operand<? extends TNumber>> sampleIndices,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EnqueueTPUEmbeddingSparseBatch");
    opBuilder.addInputList(Operands.asOutputs(sampleIndices));
    opBuilder.addInputList(Operands.asOutputs(embeddingIndices));
    opBuilder.addInputList(Operands.asOutputs(aggregationWeights));
    opBuilder.addInput(modeOverride.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.deviceOrdinal != null) {
          opBuilder.setAttr("device_ordinal", opts.deviceOrdinal);
        }
        if (opts.combiners != null) {
          String[] combinersArray = new String[opts.combiners.size()];
          for (int i = 0 ; i < combinersArray.length ; i++) {
            combinersArray[i] = opts.combiners.get(i);
          }
          opBuilder.setAttr("combiners", combinersArray);
        }
      }
    }
    return new EnqueueTPUEmbeddingSparseBatch(opBuilder.build());
  }

  /**
   * Sets the deviceOrdinal option.
   *
   * @param deviceOrdinal The TPU device to use. Should be &gt;= 0 and less than the number
   * of TPU cores in the task on which the node is placed.
   * @return this Options instance.
   */
  public static Options deviceOrdinal(Long deviceOrdinal) {
    return new Options().deviceOrdinal(deviceOrdinal);
  }

  /**
   * Sets the combiners option.
   *
   * @param combiners A list of string scalars, one for each embedding table that specify
   * how to normalize the embedding activations after weighted summation.
   * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
   * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
   * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
   * all tables.
   * @return this Options instance.
   */
  public static Options combiners(List<String> combiners) {
    return new Options().combiners(combiners);
  }

  /**
   * Sets the combiners option.
   *
   * @param combiners A list of string scalars, one for each embedding table that specify
   * how to normalize the embedding activations after weighted summation.
   * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
   * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
   * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
   * all tables.
   * @return this Options instance.
   */
  public static Options combiners(String... combiners) {
    return new Options().combiners(combiners);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.EnqueueTPUEmbeddingSparseBatch}
   */
  public static class Options {
    private Long deviceOrdinal;

    private List<String> combiners;

    private Options() {
    }

    /**
     * Sets the deviceOrdinal option.
     *
     * @param deviceOrdinal The TPU device to use. Should be &gt;= 0 and less than the number
     * of TPU cores in the task on which the node is placed.
     * @return this Options instance.
     */
    public Options deviceOrdinal(Long deviceOrdinal) {
      this.deviceOrdinal = deviceOrdinal;
      return this;
    }

    /**
     * Sets the combiners option.
     *
     * @param combiners A list of string scalars, one for each embedding table that specify
     * how to normalize the embedding activations after weighted summation.
     * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
     * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
     * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
     * all tables.
     * @return this Options instance.
     */
    public Options combiners(List<String> combiners) {
      this.combiners = combiners;
      return this;
    }

    /**
     * Sets the combiners option.
     *
     * @param combiners A list of string scalars, one for each embedding table that specify
     * how to normalize the embedding activations after weighted summation.
     * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
     * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
     * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
     * all tables.
     * @return this Options instance.
     */
    public Options combiners(String... combiners) {
      this.combiners = Arrays.asList(combiners);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = EnqueueTPUEmbeddingSparseBatch.class
  )
  public static class Inputs extends RawOpInputs<EnqueueTPUEmbeddingSparseBatch> {
    /**
     * A list of rank 1 Tensors specifying the training example and
     * feature to which the corresponding embedding_indices and aggregation_weights
     * values belong. sample_indices[i] must equal b * nf + f, where nf is the
     * number of features from the corresponding table, f is in [0, nf), and
     * b is in [0, batch size).
     */
    public final Iterable<Operand<? extends TNumber>> sampleIndices;

    /**
     * A list of rank 1 Tensors, indices into the embedding tables.
     */
    public final Iterable<Operand<? extends TNumber>> embeddingIndices;

    /**
     * A list of rank 1 Tensors containing per sample -- i.e. per
     * (training example, feature) -- aggregation weights.
     */
    public final Iterable<Operand<? extends TNumber>> aggregationWeights;

    /**
     * A string input that overrides the mode specified in the
     * TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
     * 'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
     * in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
     */
    public final Operand<TString> modeOverride;

    /**
     * The T1 attribute
     */
    public final DataType T1;

    /**
     * The T2 attribute
     */
    public final DataType T2;

    /**
     * The T3 attribute
     */
    public final DataType T3;

    /**
     * The TPU device to use. Should be >= 0 and less than the number
     * of TPU cores in the task on which the node is placed.
     */
    public final long deviceOrdinal;

    /**
     * A list of string scalars, one for each embedding table that specify
     * how to normalize the embedding activations after weighted summation.
     * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
     * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
     * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
     * all tables.
     */
    public final String[] combiners;

    public Inputs(GraphOperation op) {
      super(new EnqueueTPUEmbeddingSparseBatch(op), op, Arrays.asList("T1", "T2", "T3", "device_ordinal", "combiners"));
      int inputIndex = 0;
      int sampleIndicesLength = op.inputListLength("sample_indices");
      sampleIndices = Arrays.asList((Operand<? extends TNumber>[]) op.inputList(inputIndex, sampleIndicesLength));
      inputIndex += sampleIndicesLength;
      int embeddingIndicesLength = op.inputListLength("embedding_indices");
      embeddingIndices = Arrays.asList((Operand<? extends TNumber>[]) op.inputList(inputIndex, embeddingIndicesLength));
      inputIndex += embeddingIndicesLength;
      int aggregationWeightsLength = op.inputListLength("aggregation_weights");
      aggregationWeights = Arrays.asList((Operand<? extends TNumber>[]) op.inputList(inputIndex, aggregationWeightsLength));
      inputIndex += aggregationWeightsLength;
      modeOverride = (Operand<TString>) op.input(inputIndex++);
      T1 = op.attributes().getAttrType("T1");
      T2 = op.attributes().getAttrType("T2");
      T3 = op.attributes().getAttrType("T3");
      deviceOrdinal = op.attributes().getAttrInt("device_ordinal");
      combiners = op.attributes().getAttrStringList("combiners");
    }
  }
}
