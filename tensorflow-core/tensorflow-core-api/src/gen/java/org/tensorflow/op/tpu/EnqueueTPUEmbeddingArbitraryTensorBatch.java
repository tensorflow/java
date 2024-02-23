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
 * Eases the porting of code that uses tf.nn.embedding_lookup_sparse().
 * embedding_indices[i] and aggregation_weights[i] correspond
 * to the ith feature.
 * <p>The tensors at corresponding positions in the three input lists (sample_indices,
 * embedding_indices and aggregation_weights) must have the same shape, i.e. rank 1
 * with dim_size() equal to the total number of lookups into the table described by
 * the corresponding feature.
 */
@OpMetadata(
    opType = EnqueueTPUEmbeddingArbitraryTensorBatch.OP_NAME,
    inputsClass = EnqueueTPUEmbeddingArbitraryTensorBatch.Inputs.class
)
public final class EnqueueTPUEmbeddingArbitraryTensorBatch extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EnqueueTPUEmbeddingArbitraryTensorBatch";

  public EnqueueTPUEmbeddingArbitraryTensorBatch(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new EnqueueTPUEmbeddingArbitraryTensorBatch operation.
   *
   * @param scope current scope
   * @param sampleIndicesOrRowSplits A list of rank 2 Tensors specifying the training example to which the
   * corresponding embedding_indices and aggregation_weights values belong.
   * If the size of its first dimension is 0, we assume each embedding_indices
   * belongs to a different sample. Both int32 and int64 are allowed and will
   * be converted to int32 internally.
   * <p>Or a list of rank 1 Tensors specifying the row splits for splitting
   * embedding_indices and aggregation_weights into rows. It corresponds to
   * ids.row_splits in embedding_lookup(), when ids is a RaggedTensor. When
   * enqueuing N-D ragged tensor, only the last dimension is allowed to be ragged.
   * the row splits is 1-D dense tensor. When empty, we assume a dense tensor is
   * passed to the op Both int32 and int64 are allowed and will be converted to
   * int32 internally.
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding
   * tables. Both int32 and int64 are allowed and will be converted to
   * int32 internally.
   * @param aggregationWeights A list of rank 1 Tensors containing per training
   * example aggregation weights. Both float32 and float64 are allowed and will
   * be converted to float32 internally.
   * @param modeOverride A string input that overrides the mode specified in the
   * TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   * 'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   * in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingArbitraryTensorBatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static EnqueueTPUEmbeddingArbitraryTensorBatch create(Scope scope,
      Iterable<Operand<? extends TNumber>> sampleIndicesOrRowSplits,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EnqueueTPUEmbeddingArbitraryTensorBatch");
    opBuilder.addInputList(Operands.asOutputs(sampleIndicesOrRowSplits));
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
    return new EnqueueTPUEmbeddingArbitraryTensorBatch(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.tpu.EnqueueTPUEmbeddingArbitraryTensorBatch}
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
      outputsClass = EnqueueTPUEmbeddingArbitraryTensorBatch.class
  )
  public static class Inputs extends RawOpInputs<EnqueueTPUEmbeddingArbitraryTensorBatch> {
    /**
     * A list of rank 2 Tensors specifying the training example to which the
     * corresponding embedding_indices and aggregation_weights values belong.
     * If the size of its first dimension is 0, we assume each embedding_indices
     * belongs to a different sample. Both int32 and int64 are allowed and will
     * be converted to int32 internally.
     * <p>Or a list of rank 1 Tensors specifying the row splits for splitting
     * embedding_indices and aggregation_weights into rows. It corresponds to
     * ids.row_splits in embedding_lookup(), when ids is a RaggedTensor. When
     * enqueuing N-D ragged tensor, only the last dimension is allowed to be ragged.
     * the row splits is 1-D dense tensor. When empty, we assume a dense tensor is
     * passed to the op Both int32 and int64 are allowed and will be converted to
     * int32 internally.
     */
    public final Iterable<Operand<? extends TNumber>> sampleIndicesOrRowSplits;

    /**
     * A list of rank 1 Tensors, indices into the embedding
     * tables. Both int32 and int64 are allowed and will be converted to
     * int32 internally.
     */
    public final Iterable<Operand<? extends TNumber>> embeddingIndices;

    /**
     * A list of rank 1 Tensors containing per training
     * example aggregation weights. Both float32 and float64 are allowed and will
     * be converted to float32 internally.
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
      super(new EnqueueTPUEmbeddingArbitraryTensorBatch(op), op, Arrays.asList("T1", "T2", "T3", "device_ordinal", "combiners"));
      int inputIndex = 0;
      int sampleIndicesOrRowSplitsLength = op.inputListLength("sample_indices_or_row_splits");
      sampleIndicesOrRowSplits = Arrays.asList((Operand<? extends TNumber>[]) op.inputList(inputIndex, sampleIndicesOrRowSplitsLength));
      inputIndex += sampleIndicesOrRowSplitsLength;
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
