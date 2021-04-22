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
 * Computes the log of the absolute value of {@code Gamma(x)} element-wise.
 * For positive numbers, this function computes log((input - 1)!) for every element in the tensor.
 * {@code lgamma(5) = log((5-1)!) = log(4!) = log(24) = 3.1780539}
 * <p>Example:
 * <pre>
 * x = tf.constant([0, 0.5, 1, 4.5, -4, -5.6])
 * tf.math.lgamma(x) ==&gt; [inf, 0.5723649, 0., 2.4537368, inf, -4.6477685]
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@Operator(
    group = "math"
)
public final class Lgamma<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Lgamma";

  private Output<T> y;

  private Lgamma(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Lgamma operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param <T> data type for {@code Lgamma} output and operands
   * @return a new instance of Lgamma
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Lgamma<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Lgamma", scope.makeOpName("Lgamma"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Lgamma<>(opBuilder.build());
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  @Override
  public Output<T> asOutput() {
    return y;
  }
}
