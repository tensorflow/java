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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * The DynamicEnqueueTPUEmbeddingRaggedTensorBatch operation
 */
@OpMetadata(
    opType = DynamicEnqueueTPUEmbeddingRaggedTensorBatch.OP_NAME,
    inputsClass = DynamicEnqueueTPUEmbeddingRaggedTensorBatch.Inputs.class
)
public final class DynamicEnqueueTPUEmbeddingRaggedTensorBatch extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DynamicEnqueueTPUEmbeddingRaggedTensorBatch";

  public DynamicEnqueueTPUEmbeddingRaggedTensorBatch(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DynamicEnqueueTPUEmbeddingRaggedTensorBatch operation.
   *
   * @param scope current scope
   * @param sampleSplits The sampleSplits value
   * @param embeddingIndices The embeddingIndices value
   * @param aggregationWeights The aggregationWeights value
   * @param modeOverride The modeOverride value
   * @param deviceOrdinal The deviceOrdinal value
   * @param tableIds The value of the tableIds attribute
   * @param options carries optional attribute values
   * @return a new instance of DynamicEnqueueTPUEmbeddingRaggedTensorBatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static DynamicEnqueueTPUEmbeddingRaggedTensorBatch create(Scope scope,
      Iterable<Operand<? extends TNumber>> sampleSplits,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      Operand<TInt32> deviceOrdinal, List<Long> tableIds, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DynamicEnqueueTPUEmbeddingRaggedTensorBatch");
    opBuilder.addInputList(Operands.asOutputs(sampleSplits));
    opBuilder.addInputList(Operands.asOutputs(embeddingIndices));
    opBuilder.addInputList(Operands.asOutputs(aggregationWeights));
    opBuilder.addInput(modeOverride.asOutput());
    opBuilder.addInput(deviceOrdinal.asOutput());
    long[] tableIdsArray = new long[tableIds.size()];
    for (int i = 0 ; i < tableIdsArray.length ; i++) {
      tableIdsArray[i] = tableIds.get(i);
    }
    opBuilder.setAttr("table_ids", tableIdsArray);
    if (options != null) {
      for (Options opts : options) {
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
    return new DynamicEnqueueTPUEmbeddingRaggedTensorBatch(opBuilder.build());
  }

  /**
   * Sets the combiners option.
   *
   * @param combiners the combiners option
   * @return this Options instance.
   */
  public static Options combiners(List<String> combiners) {
    return new Options().combiners(combiners);
  }

  /**
   * Sets the combiners option.
   *
   * @param combiners the combiners option
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
   * Optional attributes for {@link org.tensorflow.op.tpu.DynamicEnqueueTPUEmbeddingRaggedTensorBatch}
   */
  public static class Options {
    private List<String> combiners;

    private List<Long> maxSequenceLengths;

    private List<Long> numFeatures;

    private Options() {
    }

    /**
     * Sets the combiners option.
     *
     * @param combiners the combiners option
     * @return this Options instance.
     */
    public Options combiners(List<String> combiners) {
      this.combiners = combiners;
      return this;
    }

    /**
     * Sets the combiners option.
     *
     * @param combiners the combiners option
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
      outputsClass = DynamicEnqueueTPUEmbeddingRaggedTensorBatch.class
  )
  public static class Inputs extends RawOpInputs<DynamicEnqueueTPUEmbeddingRaggedTensorBatch> {
    /**
     * The sampleSplits input
     */
    public final Iterable<Operand<? extends TNumber>> sampleSplits;

    /**
     * The embeddingIndices input
     */
    public final Iterable<Operand<? extends TNumber>> embeddingIndices;

    /**
     * The aggregationWeights input
     */
    public final Iterable<Operand<? extends TNumber>> aggregationWeights;

    /**
     * The modeOverride input
     */
    public final Operand<TString> modeOverride;

    /**
     * The deviceOrdinal input
     */
    public final Operand<TInt32> deviceOrdinal;

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
     * The combiners attribute
     */
    public final String[] combiners;

    /**
     * The tableIds attribute
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
      super(new DynamicEnqueueTPUEmbeddingRaggedTensorBatch(op), op, Arrays.asList("T1", "T2", "T3", "combiners", "table_ids", "max_sequence_lengths", "num_features"));
      int inputIndex = 0;
      int sampleSplitsLength = op.inputListLength("sample_splits");
      sampleSplits = Arrays.asList((Operand<? extends TNumber>[]) op.inputList(inputIndex, sampleSplitsLength));
      inputIndex += sampleSplitsLength;
      int embeddingIndicesLength = op.inputListLength("embedding_indices");
      embeddingIndices = Arrays.asList((Operand<? extends TNumber>[]) op.inputList(inputIndex, embeddingIndicesLength));
      inputIndex += embeddingIndicesLength;
      int aggregationWeightsLength = op.inputListLength("aggregation_weights");
      aggregationWeights = Arrays.asList((Operand<? extends TNumber>[]) op.inputList(inputIndex, aggregationWeightsLength));
      inputIndex += aggregationWeightsLength;
      modeOverride = (Operand<TString>) op.input(inputIndex++);
      deviceOrdinal = (Operand<TInt32>) op.input(inputIndex++);
      T1 = op.attributes().getAttrType("T1");
      T2 = op.attributes().getAttrType("T2");
      T3 = op.attributes().getAttrType("T3");
      combiners = op.attributes().getAttrStringList("combiners");
      tableIds = op.attributes().getAttrIntList("table_ids");
      maxSequenceLengths = op.attributes().getAttrIntList("max_sequence_lengths");
      numFeatures = op.attributes().getAttrIntList("num_features");
    }
  }
}
