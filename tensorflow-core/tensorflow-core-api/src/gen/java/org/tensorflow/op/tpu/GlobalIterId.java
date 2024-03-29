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
import org.tensorflow.types.TInt64;

/**
 * The GlobalIterId operation
 */
@OpMetadata(
    opType = GlobalIterId.OP_NAME,
    inputsClass = GlobalIterId.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class GlobalIterId extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GlobalIterId";

  private Output<TInt64> iterId;

  public GlobalIterId(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    iterId = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GlobalIterId operation.
   *
   * @param scope current scope
   * @return a new instance of GlobalIterId
   */
  @Endpoint(
      describeByClass = true
  )
  public static GlobalIterId create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GlobalIterId");
    return new GlobalIterId(opBuilder.build());
  }

  /**
   * Gets iterId.
   *
   * @return iterId.
   */
  public Output<TInt64> iterId() {
    return iterId;
  }

  @Override
  public Output<TInt64> asOutput() {
    return iterId;
  }

  @OpInputsMetadata(
      outputsClass = GlobalIterId.class
  )
  public static class Inputs extends RawOpInputs<GlobalIterId> {
    public Inputs(GraphOperation op) {
      super(new GlobalIterId(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
