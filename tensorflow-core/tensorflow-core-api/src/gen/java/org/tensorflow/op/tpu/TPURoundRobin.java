/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

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
 * Round-robin load balancing on TPU cores.
 * A load balancing op that round-robins among TPU cores.
 * <p>This op round-robins between the integers in [0, NumTPUCoresVisiblePerHost]. It
 * is useful for interfacing with TensorFlow ops that take as input a TPU core on
 * which to execute computations, such as {@code TPUPartitionedCall}.
 * <p>device_ordinal: An integer in [0, NumTPUCoresVisiblePerHost].
 */
@OpMetadata(
    opType = TPURoundRobin.OP_NAME,
    inputsClass = TPURoundRobin.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class TPURoundRobin extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPURoundRobin";

  private Output<TInt32> deviceOrdinal;

  public TPURoundRobin(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    deviceOrdinal = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TPURoundRobin operation.
   *
   * @param scope current scope
   * @return a new instance of TPURoundRobin
   */
  @Endpoint(
      describeByClass = true
  )
  public static TPURoundRobin create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TPURoundRobin");
    return new TPURoundRobin(opBuilder.build());
  }

  /**
   * Gets deviceOrdinal.
   *
   * @return deviceOrdinal.
   */
  public Output<TInt32> deviceOrdinal() {
    return deviceOrdinal;
  }

  @Override
  public Output<TInt32> asOutput() {
    return deviceOrdinal;
  }

  @OpInputsMetadata(
      outputsClass = TPURoundRobin.class
  )
  public static class Inputs extends RawOpInputs<TPURoundRobin> {
    public Inputs(GraphOperation op) {
      super(new TPURoundRobin(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
