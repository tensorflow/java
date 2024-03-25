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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * The GetMinibatchesInCsrWithPhysicalReplica operation
 */
@OpMetadata(
    opType = GetMinibatchesInCsrWithPhysicalReplica.OP_NAME,
    inputsClass = GetMinibatchesInCsrWithPhysicalReplica.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class GetMinibatchesInCsrWithPhysicalReplica extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GetMinibatchesInCsrWithPhysicalReplica";

  private Output<TInt32> rowPointers;

  private Output<TInt32> sortedSampleIds;

  private Output<TInt32> sortedTokenIds;

  private Output<TFloat32> sortedGains;

  private Output<TInt32> rowPointersUnpaddedSize;

  private Output<TInt32> idsUnpaddedSize;

  private Output<TInt32> numMinibatchesPerPhysicalSparseCore;

  public GetMinibatchesInCsrWithPhysicalReplica(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    rowPointers = operation.output(outputIdx++);
    sortedSampleIds = operation.output(outputIdx++);
    sortedTokenIds = operation.output(outputIdx++);
    sortedGains = operation.output(outputIdx++);
    rowPointersUnpaddedSize = operation.output(outputIdx++);
    idsUnpaddedSize = operation.output(outputIdx++);
    numMinibatchesPerPhysicalSparseCore = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GetMinibatchesInCsrWithPhysicalReplica operation.
   *
   * @param scope current scope
   * @param programKey The programKey value
   * @param rowIds The rowIds value
   * @param colIds The colIds value
   * @param gains The gains value
   * @param splits The splits value
   * @param idCounts The idCounts value
   * @param sampleCount The value of the sampleCount attribute
   * @param numReplica The value of the numReplica attribute
   * @param maxMinibatchesPerSc The value of the maxMinibatchesPerSc attribute
   * @param maxIdsPerChipPerSample The value of the maxIdsPerChipPerSample attribute
   * @param tableVocabSize The value of the tableVocabSize attribute
   * @param featureWidth The value of the featureWidth attribute
   * @param numScPerChip The value of the numScPerChip attribute
   * @param tableName The value of the tableName attribute
   * @param miniBatchInCsr The value of the miniBatchInCsr attribute
   * @return a new instance of GetMinibatchesInCsrWithPhysicalReplica
   */
  @Endpoint(
      describeByClass = true
  )
  public static GetMinibatchesInCsrWithPhysicalReplica create(Scope scope,
      Operand<TString> programKey, Operand<TInt32> rowIds, Operand<TInt32> colIds,
      Operand<TFloat32> gains, Operand<TInt64> splits, Operand<TInt32> idCounts, Long sampleCount,
      Long numReplica, Long maxMinibatchesPerSc, Long maxIdsPerChipPerSample, Long tableVocabSize,
      Long featureWidth, Long numScPerChip, String tableName, String miniBatchInCsr) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GetMinibatchesInCsrWithPhysicalReplica");
    opBuilder.addInput(programKey.asOutput());
    opBuilder.addInput(rowIds.asOutput());
    opBuilder.addInput(colIds.asOutput());
    opBuilder.addInput(gains.asOutput());
    opBuilder.addInput(splits.asOutput());
    opBuilder.addInput(idCounts.asOutput());
    opBuilder.setAttr("sample_count", sampleCount);
    opBuilder.setAttr("num_replica", numReplica);
    opBuilder.setAttr("max_minibatches_per_sc", maxMinibatchesPerSc);
    opBuilder.setAttr("max_ids_per_chip_per_sample", maxIdsPerChipPerSample);
    opBuilder.setAttr("table_vocab_size", tableVocabSize);
    opBuilder.setAttr("feature_width", featureWidth);
    opBuilder.setAttr("num_sc_per_chip", numScPerChip);
    opBuilder.setAttr("table_name", tableName);
    opBuilder.setAttr("mini_batch_in_csr", miniBatchInCsr);
    return new GetMinibatchesInCsrWithPhysicalReplica(opBuilder.build());
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
   * Gets numMinibatchesPerPhysicalSparseCore.
   *
   * @return numMinibatchesPerPhysicalSparseCore.
   */
  public Output<TInt32> numMinibatchesPerPhysicalSparseCore() {
    return numMinibatchesPerPhysicalSparseCore;
  }

  @OpInputsMetadata(
      outputsClass = GetMinibatchesInCsrWithPhysicalReplica.class
  )
  public static class Inputs extends RawOpInputs<GetMinibatchesInCsrWithPhysicalReplica> {
    /**
     * The programKey input
     */
    public final Operand<TString> programKey;

    /**
     * The rowIds input
     */
    public final Operand<TInt32> rowIds;

    /**
     * The colIds input
     */
    public final Operand<TInt32> colIds;

    /**
     * The gains input
     */
    public final Operand<TFloat32> gains;

    /**
     * The splits input
     */
    public final Operand<TInt64> splits;

    /**
     * The idCounts input
     */
    public final Operand<TInt32> idCounts;

    /**
     * The sampleCount attribute
     */
    public final long sampleCount;

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
     * The numScPerChip attribute
     */
    public final long numScPerChip;

    /**
     * The tableName attribute
     */
    public final String tableName;

    /**
     * The miniBatchInCsr attribute
     */
    public final String miniBatchInCsr;

    public Inputs(GraphOperation op) {
      super(new GetMinibatchesInCsrWithPhysicalReplica(op), op, Arrays.asList("sample_count", "num_replica", "max_minibatches_per_sc", "max_ids_per_chip_per_sample", "table_vocab_size", "feature_width", "num_sc_per_chip", "table_name", "mini_batch_in_csr"));
      int inputIndex = 0;
      programKey = (Operand<TString>) op.input(inputIndex++);
      rowIds = (Operand<TInt32>) op.input(inputIndex++);
      colIds = (Operand<TInt32>) op.input(inputIndex++);
      gains = (Operand<TFloat32>) op.input(inputIndex++);
      splits = (Operand<TInt64>) op.input(inputIndex++);
      idCounts = (Operand<TInt32>) op.input(inputIndex++);
      sampleCount = op.attributes().getAttrInt("sample_count");
      numReplica = op.attributes().getAttrInt("num_replica");
      maxMinibatchesPerSc = op.attributes().getAttrInt("max_minibatches_per_sc");
      maxIdsPerChipPerSample = op.attributes().getAttrInt("max_ids_per_chip_per_sample");
      tableVocabSize = op.attributes().getAttrInt("table_vocab_size");
      featureWidth = op.attributes().getAttrInt("feature_width");
      numScPerChip = op.attributes().getAttrInt("num_sc_per_chip");
      tableName = op.attributes().getAttrString("table_name");
      miniBatchInCsr = op.attributes().getAttrString("mini_batch_in_csr");
    }
  }
}
