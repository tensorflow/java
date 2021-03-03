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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

/**
 * Advance the counter of a counter-based RNG.
 * <p>
 * The state of the RNG after
 * `rng_read_and_skip(n)` will be the same as that after `uniform([n])`
 * (or any other distribution). The actual increment added to the
 * counter is an unspecified implementation choice.
 */
public final class RngReadAndSkip extends RawOp implements Operand<TInt64> {
  
  /**
   * Factory method to create a class wrapping a new RngReadAndSkip operation.
   * 
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param alg The RNG algorithm.
   * @param delta The amount of advancement.
   * @return a new instance of RngReadAndSkip
   */
  @Endpoint(describeByClass = true)
  public static RngReadAndSkip create(Scope scope, Operand<?> resource, Operand<TInt32> alg, Operand<?> delta) {
    OperationBuilder opBuilder = scope.env().opBuilder("RngReadAndSkip", scope.makeOpName("RngReadAndSkip"));
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(alg.asOutput());
    opBuilder.addInput(delta.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RngReadAndSkip(opBuilder.build());
  }
  
  /**
   * The old value of the resource variable, before incrementing. Since state size is algorithm-dependent, this output will be right-padded with zeros to reach shape int64[3] (the current maximal state size among algorithms).
   */
  public Output<TInt64> value() {
    return value;
  }
  
  @Override
  public Output<TInt64> asOutput() {
    return value;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RngReadAndSkip";
  
  private Output<TInt64> value;
  
  private RngReadAndSkip(Operation operation) {
    super(operation);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
  }
}
