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
import org.tensorflow.types.family.TType;

/**
 * Computes {@code exp(x) - 1} element-wise.
 * i.e. {@code exp(x) - 1} or {@code e^(x) - 1}, where {@code x} is the input tensor.
 * {@code e} denotes Euler's number and is approximately equal to 2.718281.
 * <pre>
 * x = tf.constant(2.0)
 * tf.math.expm1(x) ==&gt; 6.389056
 *
 * x = tf.constant([2.0, 8.0])
 * tf.math.expm1(x) ==&gt; array([6.389056, 2979.958], dtype=float32)
 *
 * x = tf.constant(1 + 1j)
 * tf.math.expm1(x) ==&gt; (0.46869393991588515+2.2873552871788423j)
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@Operator(
    group = "math"
)
public final class Expm1<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Expm1";

  private Output<T> y;

  private Expm1(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Expm1 operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param <T> data type for {@code Expm1} output and operands
   * @return a new instance of Expm1
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Expm1<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Expm1", scope.makeOpName("Expm1"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Expm1<>(opBuilder.build());
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
