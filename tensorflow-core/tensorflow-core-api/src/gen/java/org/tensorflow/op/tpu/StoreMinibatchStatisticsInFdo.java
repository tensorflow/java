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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * The StoreMinibatchStatisticsInFdo operation
 */
@OpMetadata(
    opType = StoreMinibatchStatisticsInFdo.OP_NAME,
    inputsClass = StoreMinibatchStatisticsInFdo.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class StoreMinibatchStatisticsInFdo extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StoreMinibatchStatisticsInFdo";

  public StoreMinibatchStatisticsInFdo(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new StoreMinibatchStatisticsInFdo operation.
   *
   * @param scope current scope
   * @param programKey The programKey value
   * @param maxIds The maxIds value
   * @param maxUniques The maxUniques value
   * @param sampleCount The value of the sampleCount attribute
   * @param numReplica The value of the numReplica attribute
   * @param featureWidth The value of the featureWidth attribute
   * @param numScPerChip The value of the numScPerChip attribute
   * @param tableName The value of the tableName attribute
   * @param miniBatchSplits The value of the miniBatchSplits attribute
   * @return a new instance of StoreMinibatchStatisticsInFdo
   */
  @Endpoint(
      describeByClass = true
  )
  public static StoreMinibatchStatisticsInFdo create(Scope scope, Operand<TString> programKey,
      Operand<TInt32> maxIds, Operand<TInt32> maxUniques, Long sampleCount, Long numReplica,
      Long featureWidth, Long numScPerChip, String tableName, String miniBatchSplits) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StoreMinibatchStatisticsInFdo");
    opBuilder.addInput(programKey.asOutput());
    opBuilder.addInput(maxIds.asOutput());
    opBuilder.addInput(maxUniques.asOutput());
    opBuilder.setAttr("sample_count", sampleCount);
    opBuilder.setAttr("num_replica", numReplica);
    opBuilder.setAttr("feature_width", featureWidth);
    opBuilder.setAttr("num_sc_per_chip", numScPerChip);
    opBuilder.setAttr("table_name", tableName);
    opBuilder.setAttr("mini_batch_splits", miniBatchSplits);
    return new StoreMinibatchStatisticsInFdo(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = StoreMinibatchStatisticsInFdo.class
  )
  public static class Inputs extends RawOpInputs<StoreMinibatchStatisticsInFdo> {
    /**
     * The programKey input
     */
    public final Operand<TString> programKey;

    /**
     * The maxIds input
     */
    public final Operand<TInt32> maxIds;

    /**
     * The maxUniques input
     */
    public final Operand<TInt32> maxUniques;

    /**
     * The sampleCount attribute
     */
    public final long sampleCount;

    /**
     * The numReplica attribute
     */
    public final long numReplica;

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
      super(new StoreMinibatchStatisticsInFdo(op), op, Arrays.asList("sample_count", "num_replica", "feature_width", "num_sc_per_chip", "table_name", "mini_batch_splits"));
      int inputIndex = 0;
      programKey = (Operand<TString>) op.input(inputIndex++);
      maxIds = (Operand<TInt32>) op.input(inputIndex++);
      maxUniques = (Operand<TInt32>) op.input(inputIndex++);
      sampleCount = op.attributes().getAttrInt("sample_count");
      numReplica = op.attributes().getAttrInt("num_replica");
      featureWidth = op.attributes().getAttrInt("feature_width");
      numScPerChip = op.attributes().getAttrInt("num_sc_per_chip");
      tableName = op.attributes().getAttrString("table_name");
      miniBatchSplits = op.attributes().getAttrString("mini_batch_splits");
    }
  }
}
