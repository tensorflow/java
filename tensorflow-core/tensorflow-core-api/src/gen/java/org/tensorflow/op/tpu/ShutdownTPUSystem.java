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
import org.tensorflow.types.TBool;

/**
 * An op that shuts down the TPU system.
 */
@OpMetadata(
    opType = ShutdownTPUSystem.OP_NAME,
    inputsClass = ShutdownTPUSystem.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class ShutdownTPUSystem extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ShutdownTPUSystem";

  private Output<TBool> success;

  public ShutdownTPUSystem(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    success = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ShutdownTPUSystem operation.
   *
   * @param scope current scope
   * @return a new instance of ShutdownTPUSystem
   */
  @Endpoint(
      describeByClass = true
  )
  public static ShutdownTPUSystem create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ShutdownTPUSystem");
    return new ShutdownTPUSystem(opBuilder.build());
  }

  /**
   * Gets success.
   * A boolean that indicates if the shut down process succeeds.
   * @return success.
   */
  public Output<TBool> success() {
    return success;
  }

  @Override
  public Output<TBool> asOutput() {
    return success;
  }

  @OpInputsMetadata(
      outputsClass = ShutdownTPUSystem.class
  )
  public static class Inputs extends RawOpInputs<ShutdownTPUSystem> {
    public Inputs(GraphOperation op) {
      super(new ShutdownTPUSystem(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
