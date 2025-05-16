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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * The ConvertToListOfSparseCoreCooTensors operation
 */
@OpMetadata(
    opType = ConvertToListOfSparseCoreCooTensors.OP_NAME,
    inputsClass = ConvertToListOfSparseCoreCooTensors.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class ConvertToListOfSparseCoreCooTensors extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConvertToListOfSparseCoreCooTensors";

  private List<Output<TInt32>> rowIdsList;

  private List<Output<TInt32>> colIdsList;

  private List<Output<TFloat32>> gainsList;

  @SuppressWarnings("unchecked")
  public ConvertToListOfSparseCoreCooTensors(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int rowIdsListLength = operation.outputListLength("row_ids_list");
    rowIdsList = Arrays.asList((Output<TInt32>[]) operation.outputList(outputIdx, rowIdsListLength));
    outputIdx += rowIdsListLength;
    int colIdsListLength = operation.outputListLength("col_ids_list");
    colIdsList = Arrays.asList((Output<TInt32>[]) operation.outputList(outputIdx, colIdsListLength));
    outputIdx += colIdsListLength;
    int gainsListLength = operation.outputListLength("gains_list");
    gainsList = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, gainsListLength));
    outputIdx += gainsListLength;
  }

  /**
   * Factory method to create a class wrapping a new ConvertToListOfSparseCoreCooTensors operation.
   *
   * @param scope current scope
   * @param indicesOrRowSplits The indicesOrRowSplits value
   * @param values The values value
   * @param weights The weights value
   * @param sampleCount The value of the sampleCount attribute
   * @param numScPerChip The value of the numScPerChip attribute
   * @param rowOffset The value of the rowOffset attribute
   * @param colOffset The value of the colOffset attribute
   * @param colShift The value of the colShift attribute
   * @param numScShards The value of the numScShards attribute
   * @param stackedTableSampleCount The value of the stackedTableSampleCount attribute
   * @param combiner The value of the combiner attribute
   * @return a new instance of ConvertToListOfSparseCoreCooTensors
   */
  @Endpoint(
      describeByClass = true
  )
  public static ConvertToListOfSparseCoreCooTensors create(Scope scope,
      Operand<TInt32> indicesOrRowSplits, Operand<TInt32> values, Operand<TFloat32> weights,
      Long sampleCount, Long numScPerChip, Long rowOffset, Long colOffset, Long colShift,
      Long numScShards, Long stackedTableSampleCount, String combiner) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConvertToListOfSparseCoreCooTensors");
    opBuilder.addInput(indicesOrRowSplits.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(weights.asOutput());
    opBuilder.setAttr("sample_count", sampleCount);
    opBuilder.setAttr("num_sc_per_chip", numScPerChip);
    opBuilder.setAttr("row_offset", rowOffset);
    opBuilder.setAttr("col_offset", colOffset);
    opBuilder.setAttr("col_shift", colShift);
    opBuilder.setAttr("num_sc_shards", numScShards);
    opBuilder.setAttr("stacked_table_sample_count", stackedTableSampleCount);
    opBuilder.setAttr("combiner", combiner);
    return new ConvertToListOfSparseCoreCooTensors(opBuilder.build());
  }

  /**
   * Gets rowIdsList.
   *
   * @return rowIdsList.
   */
  public List<Output<TInt32>> rowIdsList() {
    return rowIdsList;
  }

  /**
   * Gets colIdsList.
   *
   * @return colIdsList.
   */
  public List<Output<TInt32>> colIdsList() {
    return colIdsList;
  }

  /**
   * Gets gainsList.
   *
   * @return gainsList.
   */
  public List<Output<TFloat32>> gainsList() {
    return gainsList;
  }

  @OpInputsMetadata(
      outputsClass = ConvertToListOfSparseCoreCooTensors.class
  )
  public static class Inputs extends RawOpInputs<ConvertToListOfSparseCoreCooTensors> {
    /**
     * The indicesOrRowSplits input
     */
    public final Operand<TInt32> indicesOrRowSplits;

    /**
     * The values input
     */
    public final Operand<TInt32> values;

    /**
     * The weights input
     */
    public final Operand<TFloat32> weights;

    /**
     * The sampleCount attribute
     */
    public final long sampleCount;

    /**
     * The rowOffset attribute
     */
    public final long rowOffset;

    /**
     * The colOffset attribute
     */
    public final long colOffset;

    /**
     * The colShift attribute
     */
    public final long colShift;

    /**
     * The numScShards attribute
     */
    public final long numScShards;

    /**
     * The stackedTableSampleCount attribute
     */
    public final long stackedTableSampleCount;

    /**
     * The combiner attribute
     */
    public final String combiner;

    public Inputs(GraphOperation op) {
      super(new ConvertToListOfSparseCoreCooTensors(op), op, Arrays.asList("sample_count", "row_offset", "col_offset", "col_shift", "num_sc_shards", "stacked_table_sample_count", "combiner"));
      int inputIndex = 0;
      indicesOrRowSplits = (Operand<TInt32>) op.input(inputIndex++);
      values = (Operand<TInt32>) op.input(inputIndex++);
      weights = (Operand<TFloat32>) op.input(inputIndex++);
      sampleCount = op.attributes().getAttrInt("sample_count");
      rowOffset = op.attributes().getAttrInt("row_offset");
      colOffset = op.attributes().getAttrInt("col_offset");
      colShift = op.attributes().getAttrInt("col_shift");
      numScShards = op.attributes().getAttrInt("num_sc_shards");
      stackedTableSampleCount = op.attributes().getAttrInt("stacked_table_sample_count");
      combiner = op.attributes().getAttrString("combiner");
    }
  }
}
