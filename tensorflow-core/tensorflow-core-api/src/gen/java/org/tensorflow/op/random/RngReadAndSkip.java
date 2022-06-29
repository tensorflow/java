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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Advance the counter of a counter-based RNG.
 * The state of the RNG after
 * {@code rng_read_and_skip(n)} will be the same as that after {@code uniform([n])}
 * (or any other distribution). The actual increment added to the
 * counter is an unspecified implementation choice.
 */
@OpMetadata(
    opType = RngReadAndSkip.OP_NAME,
    inputsClass = RngReadAndSkip.Inputs.class
)
public final class RngReadAndSkip extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RngReadAndSkip";

  private Output<TInt64> value;

  public RngReadAndSkip(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RngReadAndSkip operation.
   *
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param alg The RNG algorithm.
   * @param delta The amount of advancement.
   * @return a new instance of RngReadAndSkip
   */
  @Endpoint(
      describeByClass = true
  )
  public static RngReadAndSkip create(Scope scope, Operand<? extends TType> resource,
      Operand<TInt32> alg, Operand<? extends TType> delta) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RngReadAndSkip");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(alg.asOutput());
    opBuilder.addInput(delta.asOutput());
    return new RngReadAndSkip(opBuilder.build());
  }

  /**
   * Gets value.
   * The old value of the resource variable, before incrementing. Since state size is algorithm-dependent, this output will be right-padded with zeros to reach shape int64[3] (the current maximal state size among algorithms).
   * @return value.
   */
  public Output<TInt64> value() {
    return value;
  }

  @Override
  public Output<TInt64> asOutput() {
    return value;
  }

  @OpInputsMetadata(
      outputsClass = RngReadAndSkip.class
  )
  public static class Inputs extends RawOpInputs<RngReadAndSkip> {
    /**
     * The handle of the resource variable that stores the state of the RNG.
     */
    public final Operand<? extends TType> resource;

    /**
     * The RNG algorithm.
     */
    public final Operand<TInt32> alg;

    /**
     * The amount of advancement.
     */
    public final Operand<? extends TType> delta;

    public Inputs(GraphOperation op) {
      super(new RngReadAndSkip(op), op, Arrays.asList());
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      alg = (Operand<TInt32>) op.input(inputIndex++);
      delta = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
