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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * This op consumes a lock created by `MutexLock`.
 * <p>
 * This op exists to consume a tensor created by `MutexLock` (other than
 * direct control dependencies).  It should be the only that consumes the tensor,
 * and will raise an error if it is not.  Its only purpose is to keep the
 * mutex lock tensor alive until it is consumed by this op.
 * <p>
 * <b>NOTE</b>: This operation must run on the same device as its input.  This may
 * be enforced via the `colocate_with` mechanism.
 */
@Operator
public final class ConsumeMutexLock extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new ConsumeMutexLock operation.
   * 
   * @param scope current scope
   * @param mutexLock A tensor returned by `MutexLock`.
   * @return a new instance of ConsumeMutexLock
   */
  @Endpoint(describeByClass = true)
  public static ConsumeMutexLock create(Scope scope, Operand<?> mutexLock) {
    OperationBuilder opBuilder = scope.env().opBuilder("ConsumeMutexLock", scope.makeOpName("ConsumeMutexLock"));
    opBuilder.addInput(mutexLock.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ConsumeMutexLock(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ConsumeMutexLock";
  
  private ConsumeMutexLock(Operation operation) {
    super(operation);
  }
}
