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
import java.util.List;
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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * The SortListOfSparseCoreCooTensors operation
 */
@OpMetadata(
    opType = SortListOfSparseCoreCooTensors.OP_NAME,
    inputsClass = SortListOfSparseCoreCooTensors.Inputs.class
)
public final class SortListOfSparseCoreCooTensors extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SortListOfSparseCoreCooTensors";

  private Output<TInt32> sortedRowIds;

  private Output<TInt32> sortedColIds;

  private Output<TFloat32> sortedGains;

  private Output<TInt32> idCounts;

  public SortListOfSparseCoreCooTensors(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sortedRowIds = operation.output(outputIdx++);
    sortedColIds = operation.output(outputIdx++);
    sortedGains = operation.output(outputIdx++);
    idCounts = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SortListOfSparseCoreCooTensors operation.
   *
   * @param scope current scope
   * @param rowIdsList The rowIdsList value
   * @param colIdsList The colIdsList value
   * @param gainsList The gainsList value
   * @param sampleCountList The value of the sampleCountList attribute
   * @param colOffsetList The value of the colOffsetList attribute
   * @param numReplica The value of the numReplica attribute
   * @param tableVocabSize The value of the tableVocabSize attribute
   * @param featureWidth The value of the featureWidth attribute
   * @param numScPerChip The value of the numScPerChip attribute
   * @param maxIdsPerSparseCore The value of the maxIdsPerSparseCore attribute
   * @param maxUniqueIdsPerSparseCore The value of the maxUniqueIdsPerSparseCore attribute
   * @param tableName The value of the tableName attribute
   * @return a new instance of SortListOfSparseCoreCooTensors
   */
  @Endpoint(
      describeByClass = true
  )
  public static SortListOfSparseCoreCooTensors create(Scope scope,
      Iterable<Operand<TInt32>> rowIdsList, Iterable<Operand<TInt32>> colIdsList,
      Iterable<Operand<TFloat32>> gainsList, List<Long> sampleCountList, List<Long> colOffsetList,
      Long numReplica, Long tableVocabSize, Long featureWidth, Long numScPerChip,
      Long maxIdsPerSparseCore, Long maxUniqueIdsPerSparseCore, String tableName) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SortListOfSparseCoreCooTensors");
    opBuilder.addInputList(Operands.asOutputs(rowIdsList));
    opBuilder.addInputList(Operands.asOutputs(colIdsList));
    opBuilder.addInputList(Operands.asOutputs(gainsList));
    long[] sampleCountListArray = new long[sampleCountList.size()];
    for (int i = 0 ; i < sampleCountListArray.length ; i++) {
      sampleCountListArray[i] = sampleCountList.get(i);
    }
    opBuilder.setAttr("sample_count_list", sampleCountListArray);
    long[] colOffsetListArray = new long[colOffsetList.size()];
    for (int i = 0 ; i < colOffsetListArray.length ; i++) {
      colOffsetListArray[i] = colOffsetList.get(i);
    }
    opBuilder.setAttr("col_offset_list", colOffsetListArray);
    opBuilder.setAttr("num_replica", numReplica);
    opBuilder.setAttr("table_vocab_size", tableVocabSize);
    opBuilder.setAttr("feature_width", featureWidth);
    opBuilder.setAttr("num_sc_per_chip", numScPerChip);
    opBuilder.setAttr("max_ids_per_sparse_core", maxIdsPerSparseCore);
    opBuilder.setAttr("max_unique_ids_per_sparse_core", maxUniqueIdsPerSparseCore);
    opBuilder.setAttr("table_name", tableName);
    return new SortListOfSparseCoreCooTensors(opBuilder.build());
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
   * Gets idCounts.
   *
   * @return idCounts.
   */
  public Output<TInt32> idCounts() {
    return idCounts;
  }

  @OpInputsMetadata(
      outputsClass = SortListOfSparseCoreCooTensors.class
  )
  public static class Inputs extends RawOpInputs<SortListOfSparseCoreCooTensors> {
    /**
     * The rowIdsList input
     */
    public final Iterable<Operand<TInt32>> rowIdsList;

    /**
     * The colIdsList input
     */
    public final Iterable<Operand<TInt32>> colIdsList;

    /**
     * The gainsList input
     */
    public final Iterable<Operand<TFloat32>> gainsList;

    /**
     * The sampleCountList attribute
     */
    public final long[] sampleCountList;

    /**
     * The colOffsetList attribute
     */
    public final long[] colOffsetList;

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
     * The maxIdsPerSparseCore attribute
     */
    public final long maxIdsPerSparseCore;

    /**
     * The maxUniqueIdsPerSparseCore attribute
     */
    public final long maxUniqueIdsPerSparseCore;

    /**
     * The tableName attribute
     */
    public final String tableName;

    public Inputs(GraphOperation op) {
      super(new SortListOfSparseCoreCooTensors(op), op, Arrays.asList("sample_count_list", "col_offset_list", "num_replica", "table_vocab_size", "feature_width", "num_sc_per_chip", "max_ids_per_sparse_core", "max_unique_ids_per_sparse_core", "table_name"));
      int inputIndex = 0;
      int rowIdsListLength = op.inputListLength("row_ids_list");
      rowIdsList = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, rowIdsListLength));
      inputIndex += rowIdsListLength;
      int colIdsListLength = op.inputListLength("col_ids_list");
      colIdsList = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, colIdsListLength));
      inputIndex += colIdsListLength;
      int gainsListLength = op.inputListLength("gains_list");
      gainsList = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, gainsListLength));
      inputIndex += gainsListLength;
      sampleCountList = op.attributes().getAttrIntList("sample_count_list");
      colOffsetList = op.attributes().getAttrIntList("col_offset_list");
      numReplica = op.attributes().getAttrInt("num_replica");
      tableVocabSize = op.attributes().getAttrInt("table_vocab_size");
      featureWidth = op.attributes().getAttrInt("feature_width");
      numScPerChip = op.attributes().getAttrInt("num_sc_per_chip");
      maxIdsPerSparseCore = op.attributes().getAttrInt("max_ids_per_sparse_core");
      maxUniqueIdsPerSparseCore = op.attributes().getAttrInt("max_unique_ids_per_sparse_core");
      tableName = op.attributes().getAttrString("table_name");
    }
  }
}
