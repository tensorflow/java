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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TInt32;

/**
 * An op to update the task ID and global core array.
 * This op is to update the task ID and global core array.
 */
@OpMetadata(
    opType = UpdateTaskIdAndGlobalCoreArray.OP_NAME,
    inputsClass = UpdateTaskIdAndGlobalCoreArray.Inputs.class
)
public final class UpdateTaskIdAndGlobalCoreArray extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UpdateTaskIdAndGlobalCoreArray";

  public UpdateTaskIdAndGlobalCoreArray(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new UpdateTaskIdAndGlobalCoreArray operation.
   *
   * @param scope current scope
   * @param tpuTaskIdToShardId An array of int32 that maps TPU task ID to shard ID.
   * @return a new instance of UpdateTaskIdAndGlobalCoreArray
   */
  @Endpoint(
      describeByClass = true
  )
  public static UpdateTaskIdAndGlobalCoreArray create(Scope scope,
      Iterable<Operand<TInt32>> tpuTaskIdToShardId) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UpdateTaskIdAndGlobalCoreArray");
    opBuilder.addInputList(Operands.asOutputs(tpuTaskIdToShardId));
    return new UpdateTaskIdAndGlobalCoreArray(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = UpdateTaskIdAndGlobalCoreArray.class
  )
  public static class Inputs extends RawOpInputs<UpdateTaskIdAndGlobalCoreArray> {
    /**
     * An array of int32 that maps TPU task ID to shard ID.
     */
    public final Iterable<Operand<TInt32>> tpuTaskIdToShardId;

    public Inputs(GraphOperation op) {
      super(new UpdateTaskIdAndGlobalCoreArray(op), op, Arrays.asList());
      int inputIndex = 0;
      int tpuTaskIdToShardIdLength = op.inputListLength("tpu_task_id_to_shard_id");
      tpuTaskIdToShardId = Arrays.asList((Operand<TInt32>[]) op.inputList(inputIndex, tpuTaskIdToShardIdLength));
      inputIndex += tpuTaskIdToShardIdLength;
    }
  }
}
