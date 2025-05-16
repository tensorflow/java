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
import org.tensorflow.types.TInt32;

/**
 * An op returns the TPU task ID from TPU topology.
 * This op is to return the TPU task ID from TPU topology.
 */
@OpMetadata(
    opType = GetTpuTaskId.OP_NAME,
    inputsClass = GetTpuTaskId.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class GetTpuTaskId extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GetTpuTaskId";

  private Output<TInt32> tpuTaskId;

  public GetTpuTaskId(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    tpuTaskId = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GetTpuTaskId operation.
   *
   * @param scope current scope
   * @return a new instance of GetTpuTaskId
   */
  @Endpoint(
      describeByClass = true
  )
  public static GetTpuTaskId create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GetTpuTaskId");
    return new GetTpuTaskId(opBuilder.build());
  }

  /**
   * Gets tpuTaskId.
   * The TPU task ID from TPU topology.
   * @return tpuTaskId.
   */
  public Output<TInt32> tpuTaskId() {
    return tpuTaskId;
  }

  @Override
  public Output<TInt32> asOutput() {
    return tpuTaskId;
  }

  @OpInputsMetadata(
      outputsClass = GetTpuTaskId.class
  )
  public static class Inputs extends RawOpInputs<GetTpuTaskId> {
    public Inputs(GraphOperation op) {
      super(new GetTpuTaskId(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
