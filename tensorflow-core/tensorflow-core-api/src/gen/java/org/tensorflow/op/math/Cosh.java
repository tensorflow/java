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
 * Computes hyperbolic cosine of x element-wise.
 * Given an input tensor, this function computes hyperbolic cosine of every
 * element in the tensor. Input range is {@code [-inf, inf]} and output range
 * is {@code [1, inf]}.
 * <pre>
 * x = tf.constant([-float(&quot;inf&quot;), -9, -0.5, 1, 1.2, 2, 10, float(&quot;inf&quot;)])
 * tf.math.cosh(x) ==&gt; [inf 4.0515420e+03 1.1276259e+00 1.5430807e+00 1.8106556e+00 3.7621956e+00 1.1013233e+04 inf]
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@Operator(
    group = "math"
)
public final class Cosh<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Cosh";

  private Output<T> y;

  private Cosh(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Cosh operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param <T> data type for {@code Cosh} output and operands
   * @return a new instance of Cosh
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Cosh<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Cosh");
    opBuilder.addInput(x.asOutput());
    return new Cosh<>(opBuilder.build());
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
