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

package org.tensorflow.op.core;

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
import org.tensorflow.types.family.TType;

/**
 * This op consumes a lock created by {@code MutexLock}.
 * This op exists to consume a tensor created by {@code MutexLock} (other than
 * direct control dependencies).  It should be the only that consumes the tensor,
 * and will raise an error if it is not.  Its only purpose is to keep the
 * mutex lock tensor alive until it is consumed by this op.
 * <p><strong>NOTE</strong>: This operation must run on the same device as its input.  This may
 * be enforced via the {@code colocate_with} mechanism.
 */
@OpMetadata(
    opType = ConsumeMutexLock.OP_NAME,
    inputsClass = ConsumeMutexLock.Inputs.class
)
@Operator
public final class ConsumeMutexLock extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConsumeMutexLock";

  public ConsumeMutexLock(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ConsumeMutexLock operation.
   *
   * @param scope current scope
   * @param mutexLock A tensor returned by {@code MutexLock}.
   * @return a new instance of ConsumeMutexLock
   */
  @Endpoint(
      describeByClass = true
  )
  public static ConsumeMutexLock create(Scope scope, Operand<? extends TType> mutexLock) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConsumeMutexLock");
    opBuilder.addInput(mutexLock.asOutput());
    return new ConsumeMutexLock(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = ConsumeMutexLock.class
  )
  public static class Inputs extends RawOpInputs<ConsumeMutexLock> {
    /**
     * A tensor returned by {@code MutexLock}.
     */
    public final Operand<? extends TType> mutexLock;

    public Inputs(GraphOperation op) {
      super(new ConsumeMutexLock(op), op, Arrays.asList());
      int inputIndex = 0;
      mutexLock = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
