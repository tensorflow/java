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
 * Computes inverse hyperbolic tangent of x element-wise.
 * <p>
 *   Given an input tensor, this function computes inverse hyperbolic tangent
 *   for every element in the tensor. Input range is `[-1,1]` and output range is
 *   `[-inf, inf]`. If input is `-1`, output will be `-inf` and if the
 *   input is `1`, output will be `inf`. Values outside the range will have
 *   `nan` as output.
 * <p>
 *   <pre>{@code
 *   x = tf.constant([-float("inf"), -1, -0.5, 1, 0, 0.5, 10, float("inf")])
 *   tf.math.atanh(x) ==> [nan -inf -0.54930615 inf  0. 0.54930615 nan nan]
 *   }</pre>
 * 
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "math")
public final class Atanh<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Atanh operation.
   * 
   * @param scope current scope
   * @param x 
   * @return a new instance of Atanh
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Atanh<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Atanh", scope.makeOpName("Atanh"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Atanh<T>(opBuilder.build());
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
  public static final String OP_NAME = "Atanh";
  
  private Output<T> y;
  
  private Atanh(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
