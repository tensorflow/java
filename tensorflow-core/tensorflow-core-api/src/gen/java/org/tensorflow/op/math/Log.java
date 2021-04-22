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
 * Computes natural logarithm of x element-wise.
 * I.e., \(y = \log_e x\).
 * <p>Example:
 * <pre>
 * x = tf.constant([0, 0.5, 1, 5])
 * tf.math.log(x) ==&gt; [-inf, -0.6931472,  0. ,  1.609438]
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@Operator(
    group = "math"
)
public final class Log<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Log";

  private Output<T> y;

  private Log(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Log operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param <T> data type for {@code Log} output and operands
   * @return a new instance of Log
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Log<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Log", scope.makeOpName("Log"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Log<>(opBuilder.build());
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
