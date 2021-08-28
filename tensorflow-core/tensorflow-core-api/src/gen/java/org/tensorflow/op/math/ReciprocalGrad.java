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

package org.tensorflow.op.math;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * Computes the gradient for the inverse of {@code x} wrt its input.
 * Specifically, {@code grad = -dy * y*y}, where {@code y = 1/x}, and {@code dy}
 * is the corresponding input gradient.
 *
 * @param <T> data type for {@code z} output
 */
public final class ReciprocalGrad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReciprocalGrad";

  private Output<T> z;

  private ReciprocalGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReciprocalGrad operation.
   *
   * @param scope current scope
   * @param y the y value
   * @param dy the dy value
   * @param <T> data type for {@code ReciprocalGrad} output and operands
   * @return a new instance of ReciprocalGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ReciprocalGrad<T> create(Scope scope, Operand<T> y,
      Operand<T> dy) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReciprocalGrad");
    opBuilder.addInput(y.asOutput());
    opBuilder.addInput(dy.asOutput());
    return new ReciprocalGrad<>(opBuilder.build());
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
