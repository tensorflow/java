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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The AnonymousSeedGenerator operation
 */
@OpMetadata(
    opType = AnonymousSeedGenerator.OP_NAME,
    inputsClass = AnonymousSeedGenerator.Inputs.class
)
public final class AnonymousSeedGenerator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AnonymousSeedGenerator";

  private Output<? extends TType> handle;

  private Output<? extends TType> deleter;

  @SuppressWarnings("unchecked")
  public AnonymousSeedGenerator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
    deleter = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AnonymousSeedGenerator operation.
   *
   * @param scope current scope
   * @param seed The seed value
   * @param seed2 The seed2 value
   * @param reshuffle The reshuffle value
   * @return a new instance of AnonymousSeedGenerator
   */
  @Endpoint(
      describeByClass = true
  )
  public static AnonymousSeedGenerator create(Scope scope, Operand<TInt64> seed,
      Operand<TInt64> seed2, Operand<TBool> reshuffle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AnonymousSeedGenerator");
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder.addInput(reshuffle.asOutput());
    return new AnonymousSeedGenerator(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  /**
   * Gets deleter.
   *
   * @return deleter.
   */
  public Output<? extends TType> deleter() {
    return deleter;
  }

  @OpInputsMetadata(
      outputsClass = AnonymousSeedGenerator.class
  )
  public static class Inputs extends RawOpInputs<AnonymousSeedGenerator> {
    /**
     * The seed input
     */
    public final Operand<TInt64> seed;

    /**
     * The seed2 input
     */
    public final Operand<TInt64> seed2;

    /**
     * The reshuffle input
     */
    public final Operand<TBool> reshuffle;

    public Inputs(GraphOperation op) {
      super(new AnonymousSeedGenerator(op), op, Arrays.asList());
      int inputIndex = 0;
      seed = (Operand<TInt64>) op.input(inputIndex++);
      seed2 = (Operand<TInt64>) op.input(inputIndex++);
      reshuffle = (Operand<TBool>) op.input(inputIndex++);
    }
  }
}
