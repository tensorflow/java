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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Advance the counter of a counter-based RNG.
 * The state of the RNG after
 * {@code rng_skip(n)} will be the same as that after {@code stateful_uniform([n])}
 * (or any other distribution). The actual increment added to the
 * counter is an unspecified implementation detail.
 */
@OpMetadata(
    opType = RngSkip.OP_NAME,
    inputsClass = RngSkip.Inputs.class
)
public final class RngSkip extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RngSkip";

  public RngSkip(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new RngSkip operation.
   *
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param delta The amount of advancement.
   * @return a new instance of RngSkip
   */
  @Endpoint(
      describeByClass = true
  )
  public static RngSkip create(Scope scope, Operand<? extends TType> resource,
      Operand<TInt64> algorithm, Operand<TInt64> delta) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RngSkip");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(delta.asOutput());
    return new RngSkip(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = RngSkip.class
  )
  public static class Inputs extends RawOpInputs<RngSkip> {
    /**
     * The handle of the resource variable that stores the state of the RNG.
     */
    public final Operand<? extends TType> resource;

    /**
     * The RNG algorithm.
     */
    public final Operand<TInt64> algorithm;

    /**
     * The amount of advancement.
     */
    public final Operand<TInt64> delta;

    public Inputs(GraphOperation op) {
      super(new RngSkip(op), op, Arrays.asList());
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      algorithm = (Operand<TInt64>) op.input(inputIndex++);
      delta = (Operand<TInt64>) op.input(inputIndex++);
    }
  }
}
