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
 * Eases the porting of code that uses tf.nn.embedding_lookup_sparse().
 * sample_indices[i], embedding_indices[i] and aggregation_weights[i] correspond
 * to the ith feature. table_ids[i] indicates which embedding table to look up ith
 * feature.
 * <p>The tensors at corresponding positions in the three input lists (sample_indices,
 * embedding_indices and aggregation_weights) must have the same shape, i.e. rank 1
 * with dim_size() equal to the total number of lookups into the table described by
 * the corresponding feature.
 */
@OpMetadata(
    opType = EnqueueTPUEmbeddingSparseTensorBatch.OP_NAME,
    inputsClass = EnqueueTPUEmbeddingSparseTensorBatch.Inputs.class
)
public final class EnqueueTPUEmbeddingSparseTensorBatch extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EnqueueTPUEmbeddingSparseTensorBatch";

  public EnqueueTPUEmbeddingSparseTensorBatch(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new EnqueueTPUEmbeddingSparseTensorBatch operation.
   *
   * @param scope current scope
   * @param sampleIndices A list of rank 1 Tensors specifying the training example to
   * which the corresponding embedding_indices and aggregation_weights values
   * belong. It corresponds to sp_ids.indices[:,0] in  embedding_lookup_sparse().
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding tables.
   * It corresponds to sp_ids.values in embedding_lookup_sparse().
   * @param aggregationWeights A list of rank 1 Tensors containing per training example
   * aggregation weights. It corresponds to sp_weights.values in
   * embedding_lookup_sparse().
   * @param modeOverride A string input that overrides the mode specified in the
   * TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   * 'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   * in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param tableIds A list of integers specifying the identifier of the embedding table
   * (offset of TableDescriptor in the TPUEmbeddingConfiguration) to lookup the
   * corresponding input. The ith input is looked up using table_ids[i]. The size
   * of the table_ids list must be equal to that of sample_indices,
   * embedding_indices and aggregation_weights.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingSparseTensorBatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static EnqueueTPUEmbeddingSparseTensorBatch create(Scope scope,
      Iterable<Operand<? extends TNumber>> sampleIndices,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      List<Long> tableIds, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EnqueueTPUEmbeddingSparseTensorBatch");
    opBuilder.addInputList(Operands.asOutputs(sampleIndices));
    opBuilder.addInputList(Operands.asOutputs(embeddingIndices));
    opBuilder.addInputList(Operands.asOutputs(aggregationWeights));
    opBuilder.addInput(modeOverride.asOutput());
    long[] tableIdsArray = new long[tableIds.size()];
    for (int i = 0 ; i < tableIdsArray.length ; i++) {
      tableIdsArray[i] = tableIds.get(i);
    }
    opBuilder.setAttr("table_ids", tableIdsArray);
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
        if (opts.maxSequenceLengths != null) {
          long[] maxSequenceLengthsArray = new long[opts.maxSequenceLengths.size()];
          for (int i = 0 ; i < maxSequenceLengthsArray.length ; i++) {
            maxSequenceLengthsArray[i] = opts.maxSequenceLengths.get(i);
          }
          opBuilder.setAttr("max_sequence_lengths", maxSequenceLengthsArray);
        }
        if (opts.numFeatures != null) {
          long[] numFeaturesArray = new long[opts.numFeatures.size()];
          for (int i = 0 ; i < numFeaturesArray.length ; i++) {
            numFeaturesArray[i] = opts.numFeatures.get(i);
          }
          opBuilder.setAttr("num_features", numFeaturesArray);
        }
      }
    }
    return new EnqueueTPUEmbeddingSparseTensorBatch(opBuilder.build());
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
   * Sets the maxSequenceLengths option.
   *
   * @param maxSequenceLengths the maxSequenceLengths option
   * @return this Options instance.
   */
  public static Options maxSequenceLengths(List<Long> maxSequenceLengths) {
    return new Options().maxSequenceLengths(maxSequenceLengths);
  }

  /**
   * Sets the maxSequenceLengths option.
   *
   * @param maxSequenceLengths the maxSequenceLengths option
   * @return this Options instance.
   */
  public static Options maxSequenceLengths(Long... maxSequenceLengths) {
    return new Options().maxSequenceLengths(maxSequenceLengths);
  }

  /**
   * Sets the numFeatures option.
   *
   * @param numFeatures the numFeatures option
   * @return this Options instance.
   */
  public static Options numFeatures(List<Long> numFeatures) {
    return new Options().numFeatures(numFeatures);
  }

  /**
   * Sets the numFeatures option.
   *
   * @param numFeatures the numFeatures option
   * @return this Options instance.
   */
  public static Options numFeatures(Long... numFeatures) {
    return new Options().numFeatures(numFeatures);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.EnqueueTPUEmbeddingSparseTensorBatch}
   */
  public static class Options {
    private Long deviceOrdinal;

    private List<String> combiners;

    private List<Long> maxSequenceLengths;

    private List<Long> numFeatures;

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

    /**
     * Sets the maxSequenceLengths option.
     *
     * @param maxSequenceLengths the maxSequenceLengths option
     * @return this Options instance.
     */
    public Options maxSequenceLengths(List<Long> maxSequenceLengths) {
      this.maxSequenceLengths = maxSequenceLengths;
      return this;
    }

    /**
     * Sets the maxSequenceLengths option.
     *
     * @param maxSequenceLengths the maxSequenceLengths option
     * @return this Options instance.
     */
    public Options maxSequenceLengths(Long... maxSequenceLengths) {
      this.maxSequenceLengths = Arrays.asList(maxSequenceLengths);
      return this;
    }

    /**
     * Sets the numFeatures option.
     *
     * @param numFeatures the numFeatures option
     * @return this Options instance.
     */
    public Options numFeatures(List<Long> numFeatures) {
      this.numFeatures = numFeatures;
      return this;
    }

    /**
     * Sets the numFeatures option.
     *
     * @param numFeatures the numFeatures option
     * @return this Options instance.
     */
    public Options numFeatures(Long... numFeatures) {
      this.numFeatures = Arrays.asList(numFeatures);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = EnqueueTPUEmbeddingSparseTensorBatch.class
  )
  public static class Inputs extends RawOpInputs<EnqueueTPUEmbeddingSparseTensorBatch> {
    /**
     * A list of rank 1 Tensors specifying the training example to
     * which the corresponding embedding_indices and aggregation_weights values
     * belong. It corresponds to sp_ids.indices[:,0] in  embedding_lookup_sparse().
     */
    public final Iterable<Operand<? extends TNumber>> sampleIndices;

    /**
     * A list of rank 1 Tensors, indices into the embedding tables.
     * It corresponds to sp_ids.values in embedding_lookup_sparse().
     */
    public final Iterable<Operand<? extends TNumber>> embeddingIndices;

    /**
     * A list of rank 1 Tensors containing per training example
     * aggregation weights. It corresponds to sp_weights.values in
     * embedding_lookup_sparse().
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

    /**
     * A list of integers specifying the identifier of the embedding table
     * (offset of TableDescriptor in the TPUEmbeddingConfiguration) to lookup the
     * corresponding input. The ith input is looked up using table_ids[i]. The size
     * of the table_ids list must be equal to that of sample_indices,
     * embedding_indices and aggregation_weights.
     */
    public final long[] tableIds;

    /**
     * The maxSequenceLengths attribute
     */
    public final long[] maxSequenceLengths;

    /**
     * The numFeatures attribute
     */
    public final long[] numFeatures;

    public Inputs(GraphOperation op) {
      super(new EnqueueTPUEmbeddingSparseTensorBatch(op), op, Arrays.asList("T1", "T2", "T3", "device_ordinal", "combiners", "table_ids", "max_sequence_lengths", "num_features"));
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
      tableIds = op.attributes().getAttrIntList("table_ids");
      maxSequenceLengths = op.attributes().getAttrIntList("max_sequence_lengths");
      numFeatures = op.attributes().getAttrIntList("num_features");
    }
  }
}
