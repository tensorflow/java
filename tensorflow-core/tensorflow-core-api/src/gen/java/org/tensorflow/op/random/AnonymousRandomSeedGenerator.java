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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The AnonymousRandomSeedGenerator operation
 */
public final class AnonymousRandomSeedGenerator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AnonymousRandomSeedGenerator";

  private Output<? extends TType> handle;

  private Output<? extends TType> deleter;

  @SuppressWarnings("unchecked")
  private AnonymousRandomSeedGenerator(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
    deleter = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AnonymousRandomSeedGenerator operation.
   *
   * @param scope current scope
   * @param seed the seed value
   * @param seed2 the seed2 value
   * @return a new instance of AnonymousRandomSeedGenerator
   */
  @Endpoint(
      describeByClass = true
  )
  public static AnonymousRandomSeedGenerator create(Scope scope, Operand<TInt64> seed,
      Operand<TInt64> seed2) {
    OperationBuilder opBuilder = scope.env().opBuilder("AnonymousRandomSeedGenerator", scope.makeOpName("AnonymousRandomSeedGenerator"));
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder = scope.apply(opBuilder);
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
}
