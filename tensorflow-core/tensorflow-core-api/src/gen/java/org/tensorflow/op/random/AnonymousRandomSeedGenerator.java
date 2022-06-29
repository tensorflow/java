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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The AnonymousRandomSeedGenerator operation
 */
@OpMetadata(
    opType = AnonymousRandomSeedGenerator.OP_NAME,
    inputsClass = AnonymousRandomSeedGenerator.Inputs.class
)
public final class AnonymousRandomSeedGenerator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AnonymousRandomSeedGenerator";

  private Output<? extends TType> handle;

  private Output<? extends TType> deleter;

  @SuppressWarnings("unchecked")
  public AnonymousRandomSeedGenerator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
    deleter = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AnonymousRandomSeedGenerator operation.
   *
   * @param scope current scope
   * @param seed The seed value
   * @param seed2 The seed2 value
   * @return a new instance of AnonymousRandomSeedGenerator
   */
  @Endpoint(
      describeByClass = true
  )
  public static AnonymousRandomSeedGenerator create(Scope scope, Operand<TInt64> seed,
      Operand<TInt64> seed2) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AnonymousRandomSeedGenerator");
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    return new AnonymousRandomSeedGenerator(opBuilder.build());
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
      outputsClass = AnonymousRandomSeedGenerator.class
  )
  public static class Inputs extends RawOpInputs<AnonymousRandomSeedGenerator> {
    /**
     * The seed input
     */
    public final Operand<TInt64> seed;

    /**
     * The seed2 input
     */
    public final Operand<TInt64> seed2;

    public Inputs(GraphOperation op) {
      super(new AnonymousRandomSeedGenerator(op), op, Arrays.asList());
      int inputIndex = 0;
      seed = (Operand<TInt64>) op.input(inputIndex++);
      seed2 = (Operand<TInt64>) op.input(inputIndex++);
    }
  }
}
