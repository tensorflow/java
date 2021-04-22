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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Computes arctangent of {@code y/x} element-wise, respecting signs of the arguments.
 * This is the angle ( \theta \in [-\pi, \pi] ) such that
 * [ x = r \cos(\theta) ]
 * and
 * [ y = r \sin(\theta) ]
 * where (r = \sqrt(x^2 + y^2) ).
 *
 * @param <T> data type for {@code z} output
 */
@Operator(
    group = "math"
)
public final class Atan2<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Atan2";

  private Output<T> z;

  private Atan2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Atan2 operation.
   *
   * @param scope current scope
   * @param y the y value
   * @param x the x value
   * @param <T> data type for {@code Atan2} output and operands
   * @return a new instance of Atan2
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Atan2<T> create(Scope scope, Operand<T> y, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Atan2", scope.makeOpName("Atan2"));
    opBuilder.addInput(y.asOutput());
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Atan2<>(opBuilder.build());
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
