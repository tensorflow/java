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
 * Computes sine of x element-wise.
 * Given an input tensor, this function computes sine of every
 * element in the tensor. Input range is {@code (-inf, inf)} and
 * output range is {@code [-1,1]}.
 * <pre>
 * x = tf.constant([-float(&quot;inf&quot;), -9, -0.5, 1, 1.2, 200, 10, float(&quot;inf&quot;)])
 * tf.math.sin(x) ==&gt; [nan -0.4121185 -0.47942555 0.84147096 0.9320391 -0.87329733 -0.54402107 nan]
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@Operator(
    group = "math"
)
public final class Sin<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Sin";

  private Output<T> y;

  private Sin(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Sin operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param <T> data type for {@code Sin} output and operands
   * @return a new instance of Sin
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Sin<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Sin", scope.makeOpName("Sin"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Sin<>(opBuilder.build());
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
