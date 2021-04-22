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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Eases the porting of code that uses tf.nn.embedding_lookup().
 * sample_splits[i], embedding_indices[i] and aggregation_weights[i] correspond
 * to the ith feature. table_ids[i] indicates which embedding table to look up ith
 * feature.
 * <p>The tensors at corresponding positions in two of the input lists,
 * embedding_indices and aggregation_weights, must have the same shape, i.e. rank 1
 * with dim_size() equal to the total number of lookups into the table described by
 * the corresponding feature.
 */
public final class EnqueueTPUEmbeddingRaggedTensorBatch extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EnqueueTPUEmbeddingRaggedTensorBatch";

  private EnqueueTPUEmbeddingRaggedTensorBatch(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new EnqueueTPUEmbeddingRaggedTensorBatch operation.
   *
   * @param scope current scope
   * @param sampleSplits A list of rank 1 Tensors specifying the break points for splitting
   * embedding_indices and aggregation_weights into rows.
   * It corresponds to ids.row_splits in embedding_lookup(), when ids is a
   * RaggedTensor.
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding tables.
   * It corresponds to ids.values in embedding_lookup(), when ids is a RaggedTensor.
   * @param aggregationWeights A list of rank 1 Tensors containing per training example
   * aggregation weights. It corresponds to the values field of a RaggedTensor
   * with the same row_splits as ids in embedding_lookup(), when ids is a
   * RaggedTensor.
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
   * @return a new instance of EnqueueTPUEmbeddingRaggedTensorBatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static EnqueueTPUEmbeddingRaggedTensorBatch create(Scope scope,
      Iterable<Operand<? extends TNumber>> sampleSplits,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      List<Long> tableIds, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("EnqueueTPUEmbeddingRaggedTensorBatch", scope.makeOpName("EnqueueTPUEmbeddingRaggedTensorBatch"));
    opBuilder.addInputList(Operands.asOutputs(sampleSplits));
    opBuilder.addInputList(Operands.asOutputs(embeddingIndices));
    opBuilder.addInputList(Operands.asOutputs(aggregationWeights));
    opBuilder.addInput(modeOverride.asOutput());
    opBuilder = scope.apply(opBuilder);
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
      }
    }
    return new EnqueueTPUEmbeddingRaggedTensorBatch(opBuilder.build());
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
  public static Options combiners(String[] combiners) {
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
  public static Options maxSequenceLengths(Long[] maxSequenceLengths) {
    return new Options().maxSequenceLengths(maxSequenceLengths);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.EnqueueTPUEmbeddingRaggedTensorBatch}
   */
  public static class Options {
    private Long deviceOrdinal;

    private List<String> combiners;

    private List<Long> maxSequenceLengths;

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
  }
}
