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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Non-deterministically generates some integers.
 * This op may use some OS-provided source of non-determinism (e.g. an RNG), so each execution will give different results.
 *
 * @param <U> data type for {@code output} output
 */
public final class NonDeterministicInts<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "NonDeterministicInts";

  private Output<U> output;

  private NonDeterministicInts(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new NonDeterministicInts operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param <U> data type for {@code NonDeterministicInts} output and operands
   * @return a new instance of NonDeterministicInts
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> NonDeterministicInts<U> create(Scope scope,
      Operand<? extends TType> shape, Class<U> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("NonDeterministicInts", scope.makeOpName("NonDeterministicInts"));
    opBuilder.addInput(shape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new NonDeterministicInts<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new NonDeterministicInts operation, with the default output types.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @return a new instance of NonDeterministicInts, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static NonDeterministicInts<TInt64> create(Scope scope, Operand<? extends TType> shape) {
    return create(scope, shape, TInt64.class);
  }

  /**
   * Gets output.
   * Non-deterministic integer values with specified shape.
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }
}
