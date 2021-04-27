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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TNumber;

/**
 * Returns x + y element-wise.
 * <em>NOTE</em>: {@code RiscAdd} does not supports broadcasting.
 * <p>Given two input tensors, the {@code tf.risc_add} operation computes the sum for every element in the tensor.
 * <p>Both input and output have a range {@code (-inf, inf)}.
 *
 * @param <T> data type for {@code z} output
 */
public final class RiscAdd<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscAdd";

  private Output<T> z;

  private RiscAdd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscAdd operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param y the y value
   * @param <T> data type for {@code RiscAdd} output and operands
   * @return a new instance of RiscAdd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscAdd<T> create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.env().opBuilder("RiscAdd", scope.makeOpName("RiscAdd"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RiscAdd<>(opBuilder.build());
  }

  /**
   * Gets z.
   *
   * @return z.
   */
  public Output<T> z() {
    return z;
  }

  @Override
  public Output<T> asOutput() {
    return z;
  }
}
