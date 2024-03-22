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

package org.tensorflow.op.xla;

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
import org.tensorflow.types.family.TType;

/**
 * The XlaSparseDenseMatmul operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmul.OP_NAME,
    inputsClass = XlaSparseDenseMatmul.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseDenseMatmul extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmul";

  private Output<TFloat32> activations;

  private Output<TInt32> rowPointers;

  private Output<TInt32> sortedEmbeddingIds;

  private Output<TInt32> sortedSampleIds;

  private Output<TFloat32> sortedGains;

  public XlaSparseDenseMatmul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
    rowPointers = operation.output(outputIdx++);
    sortedEmbeddingIds = operation.output(outputIdx++);
    sortedSampleIds = operation.output(outputIdx++);
    sortedGains = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmul operation.
   *
   * @param scope current scope
   * @param rowIds The rowIds value
   * @param colIds The colIds value
   * @param values The values value
   * @param offsets The offsets value
   * @param embeddingTable The embeddingTable value
   * @param maxIdsPerPartition The value of the maxIdsPerPartition attribute
   * @param maxUniqueIdsPerPartition The value of the maxUniqueIdsPerPartition attribute
   * @param inputSize The value of the inputSize attribute
   * @return a new instance of XlaSparseDenseMatmul
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseDenseMatmul create(Scope scope, Operand<TInt32> rowIds,
      Operand<? extends TType> colIds, Operand<TFloat32> values, Operand<? extends TType> offsets,
      Operand<TFloat32> embeddingTable, Long maxIdsPerPartition, Long maxUniqueIdsPerPartition,
      Long inputSize) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmul");
    opBuilder.addInput(rowIds.asOutput());
    opBuilder.addInput(colIds.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(offsets.asOutput());
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.setAttr("max_ids_per_partition", maxIdsPerPartition);
    opBuilder.setAttr("max_unique_ids_per_partition", maxUniqueIdsPerPartition);
    opBuilder.setAttr("input_size", inputSize);
    return new XlaSparseDenseMatmul(opBuilder.build());
  }

  /**
   * Gets activations.
   *
   * @return activations.
   */
  public Output<TFloat32> activations() {
    return activations;
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
   * Gets sortedEmbeddingIds.
   *
   * @return sortedEmbeddingIds.
   */
  public Output<TInt32> sortedEmbeddingIds() {
    return sortedEmbeddingIds;
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
   * Gets sortedGains.
   *
   * @return sortedGains.
   */
  public Output<TFloat32> sortedGains() {
    return sortedGains;
  }

  @OpInputsMetadata(
      outputsClass = XlaSparseDenseMatmul.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseDenseMatmul> {
    /**
     * The rowIds input
     */
    public final Operand<TInt32> rowIds;

    /**
     * The colIds input
     */
    public final Operand<? extends TType> colIds;

    /**
     * The values input
     */
    public final Operand<TFloat32> values;

    /**
     * The offsets input
     */
    public final Operand<? extends TType> offsets;

    /**
     * The embeddingTable input
     */
    public final Operand<TFloat32> embeddingTable;

    /**
     * The maxIdsPerPartition attribute
     */
    public final long maxIdsPerPartition;

    /**
     * The maxUniqueIdsPerPartition attribute
     */
    public final long maxUniqueIdsPerPartition;

    /**
     * The inputSize attribute
     */
    public final long inputSize;

    public Inputs(GraphOperation op) {
      super(new XlaSparseDenseMatmul(op), op, Arrays.asList("max_ids_per_partition", "max_unique_ids_per_partition", "input_size"));
      int inputIndex = 0;
      rowIds = (Operand<TInt32>) op.input(inputIndex++);
      colIds = (Operand<? extends TType>) op.input(inputIndex++);
      values = (Operand<TFloat32>) op.input(inputIndex++);
      offsets = (Operand<? extends TType>) op.input(inputIndex++);
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      maxIdsPerPartition = op.attributes().getAttrInt("max_ids_per_partition");
      maxUniqueIdsPerPartition = op.attributes().getAttrInt("max_unique_ids_per_partition");
      inputSize = op.attributes().getAttrInt("input_size");
    }
  }
}
