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
 * Computes inverse hyperbolic cosine of x element-wise.
 * <p>
 * Given an input tensor, the function computes inverse hyperbolic cosine of every element.
 * Input range is `[1, inf]`. It returns `nan` if the input lies outside the range.
 * <pre>{@code
 * x = tf.constant([-2, -0.5, 1, 1.2, 200, 10000, float("inf")])
 * tf.math.acosh(x) ==> [nan nan 0. 0.62236255 5.9914584 9.903487 inf]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "math")
public final class Acosh<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Acosh operation.
   * 
   * @param scope current scope
   * @param x 
   * @return a new instance of Acosh
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Acosh<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Acosh", scope.makeOpName("Acosh"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Acosh<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> y() {
    return y;
  }
  
  @Override
  public Output<T> asOutput() {
    return y;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Acosh";
  
  private Output<T> y;
  
  private Acosh(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
