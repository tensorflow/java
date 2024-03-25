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
 * The GetMinibatchSplitsWithPhysicalReplica operation
 */
@OpMetadata(
    opType = GetMinibatchSplitsWithPhysicalReplica.OP_NAME,
    inputsClass = GetMinibatchSplitsWithPhysicalReplica.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class GetMinibatchSplitsWithPhysicalReplica extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GetMinibatchSplitsWithPhysicalReplica";

  private Output<TInt32> sortedRowIds;

  private Output<TInt32> sortedColIds;

  private Output<TFloat32> sortedGains;

  private Output<TInt64> splits;

  private Output<TInt32> idCounts;

  private Output<TInt32> maxIds;

  private Output<TInt32> maxUniques;

  public GetMinibatchSplitsWithPhysicalReplica(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sortedRowIds = operation.output(outputIdx++);
    sortedColIds = operation.output(outputIdx++);
    sortedGains = operation.output(outputIdx++);
    splits = operation.output(outputIdx++);
    idCounts = operation.output(outputIdx++);
    maxIds = operation.output(outputIdx++);
    maxUniques = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GetMinibatchSplitsWithPhysicalReplica operation.
   *
   * @param scope current scope
   * @param programKey The programKey value
   * @param rowIds The rowIds value
   * @param colIds The colIds value
   * @param gains The gains value
   * @param sampleCount The value of the sampleCount attribute
   * @param numReplica The value of the numReplica attribute
   * @param tableVocabSize The value of the tableVocabSize attribute
   * @param featureWidth The value of the featureWidth attribute
   * @param numScPerChip The value of the numScPerChip attribute
   * @param tableName The value of the tableName attribute
   * @param miniBatchSplits The value of the miniBatchSplits attribute
   * @return a new instance of GetMinibatchSplitsWithPhysicalReplica
   */
  @Endpoint(
      describeByClass = true
  )
  public static GetMinibatchSplitsWithPhysicalReplica create(Scope scope,
      Operand<TString> programKey, Operand<TInt32> rowIds, Operand<TInt32> colIds,
      Operand<TFloat32> gains, Long sampleCount, Long numReplica, Long tableVocabSize,
      Long featureWidth, Long numScPerChip, String tableName, String miniBatchSplits) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GetMinibatchSplitsWithPhysicalReplica");
    opBuilder.addInput(programKey.asOutput());
    opBuilder.addInput(rowIds.asOutput());
    opBuilder.addInput(colIds.asOutput());
    opBuilder.addInput(gains.asOutput());
    opBuilder.setAttr("sample_count", sampleCount);
    opBuilder.setAttr("num_replica", numReplica);
    opBuilder.setAttr("table_vocab_size", tableVocabSize);
    opBuilder.setAttr("feature_width", featureWidth);
    opBuilder.setAttr("num_sc_per_chip", numScPerChip);
    opBuilder.setAttr("table_name", tableName);
    opBuilder.setAttr("mini_batch_splits", miniBatchSplits);
    return new GetMinibatchSplitsWithPhysicalReplica(opBuilder.build());
  }

  /**
   * Gets sortedRowIds.
   *
   * @return sortedRowIds.
   */
  public Output<TInt32> sortedRowIds() {
    return sortedRowIds;
  }

  /**
   * Gets sortedColIds.
   *
   * @return sortedColIds.
   */
  public Output<TInt32> sortedColIds() {
    return sortedColIds;
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
   * Gets splits.
   *
   * @return splits.
   */
  public Output<TInt64> splits() {
    return splits;
  }

  /**
   * Gets idCounts.
   *
   * @return idCounts.
   */
  public Output<TInt32> idCounts() {
    return idCounts;
  }

  /**
   * Gets maxIds.
   *
   * @return maxIds.
   */
  public Output<TInt32> maxIds() {
    return maxIds;
  }

  /**
   * Gets maxUniques.
   *
   * @return maxUniques.
   */
  public Output<TInt32> maxUniques() {
    return maxUniques;
  }

  @OpInputsMetadata(
      outputsClass = GetMinibatchSplitsWithPhysicalReplica.class
  )
  public static class Inputs extends RawOpInputs<GetMinibatchSplitsWithPhysicalReplica> {
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
     * The sampleCount attribute
     */
    public final long sampleCount;

    /**
     * The numReplica attribute
     */
    public final long numReplica;

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
     * The miniBatchSplits attribute
     */
    public final String miniBatchSplits;

    public Inputs(GraphOperation op) {
      super(new GetMinibatchSplitsWithPhysicalReplica(op), op, Arrays.asList("sample_count", "num_replica", "table_vocab_size", "feature_width", "num_sc_per_chip", "table_name", "mini_batch_splits"));
      int inputIndex = 0;
      programKey = (Operand<TString>) op.input(inputIndex++);
      rowIds = (Operand<TInt32>) op.input(inputIndex++);
      colIds = (Operand<TInt32>) op.input(inputIndex++);
      gains = (Operand<TFloat32>) op.input(inputIndex++);
      sampleCount = op.attributes().getAttrInt("sample_count");
      numReplica = op.attributes().getAttrInt("num_replica");
      tableVocabSize = op.attributes().getAttrInt("table_vocab_size");
      featureWidth = op.attributes().getAttrInt("feature_width");
      numScPerChip = op.attributes().getAttrInt("num_sc_per_chip");
      tableName = op.attributes().getAttrString("table_name");
      miniBatchSplits = op.attributes().getAttrString("mini_batch_splits");
    }
  }
}
