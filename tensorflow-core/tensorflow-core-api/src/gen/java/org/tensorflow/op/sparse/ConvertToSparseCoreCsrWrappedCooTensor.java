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

package org.tensorflow.op.sparse;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

/**
 * The ConvertToSparseCoreCsrWrappedCooTensor operation
 */
@OpMetadata(
    opType = ConvertToSparseCoreCsrWrappedCooTensor.OP_NAME,
    inputsClass = ConvertToSparseCoreCsrWrappedCooTensor.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class ConvertToSparseCoreCsrWrappedCooTensor extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConvertToSparseCoreCsrWrappedCooTensor";

  private Output<TInt32> rowPointers;

  private Output<TInt32> sortedSampleIds;

  private Output<TInt32> sortedTokenIds;

  private Output<TFloat32> sortedGains;

  private Output<TInt32> rowPointersUnpaddedSize;

  private Output<TInt32> idsUnpaddedSize;

  private Output<TInt32> numMinibatchesPerSc;

  public ConvertToSparseCoreCsrWrappedCooTensor(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    rowPointers = operation.output(outputIdx++);
    sortedSampleIds = operation.output(outputIdx++);
    sortedTokenIds = operation.output(outputIdx++);
    sortedGains = operation.output(outputIdx++);
    rowPointersUnpaddedSize = operation.output(outputIdx++);
    idsUnpaddedSize = operation.output(outputIdx++);
    numMinibatchesPerSc = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ConvertToSparseCoreCsrWrappedCooTensor operation.
   *
   * @param scope current scope
   * @param sortedRowIdsList The sortedRowIdsList value
   * @param sortedColIdsList The sortedColIdsList value
   * @param sortedGainsList The sortedGainsList value
   * @param idCountsList The idCountsList value
   * @param splits The splits value
   * @param sampleCountPerSc The value of the sampleCountPerSc attribute
   * @param numReplica The value of the numReplica attribute
   * @param maxMinibatchesPerSc The value of the maxMinibatchesPerSc attribute
   * @param maxIdsPerChipPerSample The value of the maxIdsPerChipPerSample attribute
   * @param tableVocabSize The value of the tableVocabSize attribute
   * @param featureWidth The value of the featureWidth attribute
   * @param tableName The value of the tableName attribute
   * @param allowIdDropping The value of the allowIdDropping attribute
   * @return a new instance of ConvertToSparseCoreCsrWrappedCooTensor
   */
  @Endpoint(
      describeByClass = true
  )
  public static ConvertToSparseCoreCsrWrappedCooTensor create(Scope scope,
      Iterable<Operand<TInt32>> sortedRowIdsList, Iterable<Operand<TInt32>> sortedColIdsList,
      Iterable<Operand<TFloat32>> sortedGainsList, Iterable<Operand<TInt32>> idCountsList,
      Operand<TInt64> splits, Long sampleCountPerSc, Long numReplica, Long maxMinibatchesPerSc,
      Long maxIdsPerChipPerSample, Long tableVocabSize, Long featureWidth, String tableName,
      Boolean allowIdDropping) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConvertToSparseCoreCsrWrappedCooTensor");
    opBuilder.addInputList(Operands.asOutputs(sortedRowIdsList));
    opBuilder.addInputList(Operands.asOutputs(sortedColIdsList));
    opBuilder.addInputList(Operands.asOutputs(sortedGainsList));
    opBuilder.addInputList(Operands.asOutputs(idCountsList));
    opBuilder.addInput(splits.asOutput());
    opBuilder.setAttr("sample_count_per_sc", sampleCountPerSc);
    opBuilder.setAttr("num_replica", numReplica);
    opBuilder.setAttr("max_minibatches_per_sc", maxMinibatchesPerSc);
    opBuilder.setAttr("max_ids_per_chip_per_sample", maxIdsPerChipPerSample);
    opBuilder.setAttr("table_vocab_size", tableVocabSize);
    opBuilder.setAttr("feature_width", featureWidth);
    opBuilder.setAttr("table_name", tableName);
    opBuilder.setAttr("allow_id_dropping", allowIdDropping);
    return new ConvertToSparseCoreCsrWrappedCooTensor(opBuilder.build());
  }

  /**
   * Gets rowPointers.
   *
   * @return rowPointers.
   */
  public Output<TInt32> rowPointers() {
    return rowPointers;
  }

  /**
   * Gets sortedSampleIds.
   *
   * @return sortedSampleIds.
   */
  public Output<TInt32> sortedSampleIds() {
    return sortedSampleIds;
  }

  /**
   * Gets sortedTokenIds.
   *
   * @return sortedTokenIds.
   */
  public Output<TInt32> sortedTokenIds() {
    return sortedTokenIds;
  }

  /**
   * Gets sortedGains.
   *
   * @return sortedGains.
   */
  public Output<TFloat32> sortedGains() {
    return sortedGains;
  }

  /**
   * Gets rowPointersUnpaddedSize.
   *
   * @return rowPointersUnpaddedSize.
   */
  public Output<TInt32> rowPointersUnpaddedSize() {
    return rowPointersUnpaddedSize;
  }

  /**
   * Gets idsUnpaddedSize.
   *
   * @return idsUnpaddedSize.
   */
  public Output<TInt32> idsUnpaddedSize() {
    return idsUnpaddedSize;
  }

  /**
   * Gets numMinibatchesPerSc.
   *
   * @return numMinibatchesPerSc.
   */
  public Output<TInt32> numMinibatchesPerSc() {
    return numMinibatchesPerSc;
  }

  @OpInputsMetadata(
      outputsClass = ConvertToSparseCoreCsrWrappedCooTensor.class
  )
  public static class Inputs extends RawOpInputs<ConvertToSparseCoreCsrWrappedCooTensor> {
    /**
     * The sortedRowIdsList input
     */
    public final Iterable<Operand<TInt32>> sortedRowIdsList;

    /**
     * The sortedColIdsList input
     */
    public final Iterable<Operand<TInt32>> sortedColIdsList;

    /**
     * The sortedGainsList input
     */
    public final Iterable<Operand<TFloat32>> sortedGainsList;

    /**
     * The idCountsList input
     */
    public final Iterable<Operand<TInt32>> idCountsList;

    /**
     * The splits input
     */
    public final Operand<TInt64> splits;

    /**
     * The sampleCountPerSc attribute
     */
    public final long sampleCountPerSc;

    /**
     * The numReplica attribute
     */
    public final long numReplica;

    /**
     * The maxMinibatchesPerSc attribute
     */
    public final long maxMinibatchesPerSc;

    /**
     * The maxIdsPerChipPerSample attribute
     */
    public final long maxIdsPerChipPerSample;

    /**
     * The tableVocabSize attribute
     */
    public final long tableVocabSize;

    /**
     * The featureWidth attribute
     */
    public final long featureWidth;

    /**
     * The tableName attribute
     */
    public final String tableName;

    /**
     * The allowIdDropping attribute
     */
    public final boolean allowIdDropping;

    public Inputs(GraphOperation op) {
      super(new ConvertToSparseCoreCsrWrappedCooTensor(op), op, Arrays.asList("sample_count_per_sc", "num_replica", "max_minibatches_per_sc", "max_ids_per_chip_per_sample", "table_vocab_size", "feature_width", "table_name", "allow_id_dropping"));
      int inputIndex = 0;
      int sortedRowIdsListLength = op.inputListLength("sorted_row_ids_list");
      sortedRowIdsList = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, sortedRowIdsListLength));
      inputIndex += sortedRowIdsListLength;
      int sortedColIdsListLength = op.inputListLength("sorted_col_ids_list");
      sortedColIdsList = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, sortedColIdsListLength));
      inputIndex += sortedColIdsListLength;
      int sortedGainsListLength = op.inputListLength("sorted_gains_list");
      sortedGainsList = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, sortedGainsListLength));
      inputIndex += sortedGainsListLength;
      int idCountsListLength = op.inputListLength("id_counts_list");
      idCountsList = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, idCountsListLength));
      inputIndex += idCountsListLength;
      splits = (Operand<TInt64>) op.input(inputIndex++);
      sampleCountPerSc = op.attributes().getAttrInt("sample_count_per_sc");
      numReplica = op.attributes().getAttrInt("num_replica");
      maxMinibatchesPerSc = op.attributes().getAttrInt("max_minibatches_per_sc");
      maxIdsPerChipPerSample = op.attributes().getAttrInt("max_ids_per_chip_per_sample");
      tableVocabSize = op.attributes().getAttrInt("table_vocab_size");
      featureWidth = op.attributes().getAttrInt("feature_width");
      tableName = op.attributes().getAttrString("table_name");
      allowIdDropping = op.attributes().getAttrBool("allow_id_dropping");
    }
  }
}
