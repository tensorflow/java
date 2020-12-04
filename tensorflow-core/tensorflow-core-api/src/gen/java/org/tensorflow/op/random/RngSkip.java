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

package org.tensorflow.op.random;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;

/**
 * Advance the counter of a counter-based RNG.
 * <p>
 * The state of the RNG after
 * `rng_skip(n)` will be the same as that after `stateful_uniform([n])`
 * (or any other distribution). The actual increment added to the
 * counter is an unspecified implementation detail.
 */
public final class RngSkip extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new RngSkip operation.
   * 
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param delta The amount of advancement.
   * @return a new instance of RngSkip
   */
  @Endpoint(describeByClass = true)
  public static RngSkip create(Scope scope, Operand<?> resource, Operand<TInt64> algorithm, Operand<TInt64> delta) {
    OperationBuilder opBuilder = scope.env().opBuilder("RngSkip", scope.makeOpName("RngSkip"));
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(delta.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RngSkip(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RngSkip";
  
  private RngSkip(Operation operation) {
    super(operation);
  }
}
