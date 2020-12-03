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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Eases the porting of code that uses tf.nn.embedding_lookup_sparse().
 * <p>
 * sample_indices[i], embedding_indices[i] and aggregation_weights[i] correspond
 * to the ith feature. table_ids[i] indicates which embedding table to look up ith
 * feature.
 * <p>
 * The tensors at corresponding positions in the three input lists (sample_indices,
 * embedding_indices and aggregation_weights) must have the same shape, i.e. rank 1
 * with dim_size() equal to the total number of lookups into the table described by
 * the corresponding feature.
 */
public final class EnqueueTPUEmbeddingSparseTensorBatch extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.EnqueueTPUEmbeddingSparseTensorBatch}
   */
  public static class Options {
    
    /**
     * @param deviceOrdinal The TPU device to use. Should be >= 0 and less than the number
     * of TPU cores in the task on which the node is placed.
     */
    public Options deviceOrdinal(Long deviceOrdinal) {
      this.deviceOrdinal = deviceOrdinal;
      return this;
    }
    
    /**
     * @param combiners A list of string scalars, one for each embedding table that specify
     * how to normalize the embedding activations after weighted summation.
     * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
     * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
     * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
     * all tables.
     */
    public Options combiners(List<String> combiners) {
      this.combiners = combiners;
      return this;
    }
    
    /**
     * @param maxSequenceLengths 
     */
    public Options maxSequenceLengths(List<Long> maxSequenceLengths) {
      this.maxSequenceLengths = maxSequenceLengths;
      return this;
    }
    
    private Long deviceOrdinal;
    private List<String> combiners;
    private List<Long> maxSequenceLengths;
    
    private Options() {
    }
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
   * @param options carries optional attributes values
   * @return a new instance of EnqueueTPUEmbeddingSparseTensorBatch
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber, V extends TNumber> EnqueueTPUEmbeddingSparseTensorBatch create(Scope scope, Iterable<Operand<T>> sampleIndices, Iterable<Operand<U>> embeddingIndices, Iterable<Operand<V>> aggregationWeights, Operand<TString> modeOverride, List<Long> tableIds, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("EnqueueTPUEmbeddingSparseTensorBatch", scope.makeOpName("EnqueueTPUEmbeddingSparseTensorBatch"));
    opBuilder.addInputList(Operands.asOutputs(sampleIndices));
    opBuilder.addInputList(Operands.asOutputs(embeddingIndices));
    opBuilder.addInputList(Operands.asOutputs(aggregationWeights));
    opBuilder.addInput(modeOverride.asOutput());
    opBuilder = scope.apply(opBuilder);
    long[] tableIdsArray = new long[tableIds.size()];
    for (int i = 0; i < tableIdsArray.length; ++i) {
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
          for (int i = 0; i < combinersArray.length; ++i) {
            combinersArray[i] = opts.combiners.get(i);
          }
          opBuilder.setAttr("combiners", combinersArray);
        }
        if (opts.maxSequenceLengths != null) {
          long[] maxSequenceLengthsArray = new long[opts.maxSequenceLengths.size()];
          for (int i = 0; i < maxSequenceLengthsArray.length; ++i) {
            maxSequenceLengthsArray[i] = opts.maxSequenceLengths.get(i);
          }
          opBuilder.setAttr("max_sequence_lengths", maxSequenceLengthsArray);
        }
      }
    }
    return new EnqueueTPUEmbeddingSparseTensorBatch(opBuilder.build());
  }
  
  /**
   * @param deviceOrdinal The TPU device to use. Should be >= 0 and less than the number
   * of TPU cores in the task on which the node is placed.
   */
  public static Options deviceOrdinal(Long deviceOrdinal) {
    return new Options().deviceOrdinal(deviceOrdinal);
  }
  
  /**
   * @param combiners A list of string scalars, one for each embedding table that specify
   * how to normalize the embedding activations after weighted summation.
   * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
   * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
   * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
   * all tables.
   */
  public static Options combiners(List<String> combiners) {
    return new Options().combiners(combiners);
  }
  
  /**
   * @param maxSequenceLengths 
   */
  public static Options maxSequenceLengths(List<Long> maxSequenceLengths) {
    return new Options().maxSequenceLengths(maxSequenceLengths);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "EnqueueTPUEmbeddingSparseTensorBatch";
  
  private EnqueueTPUEmbeddingSparseTensorBatch(Operation operation) {
    super(operation);
  }
}
