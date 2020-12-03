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
 * Computes the trignometric inverse tangent of x element-wise.
 * <p>
 * The `tf.math.atan` operation returns the inverse of `tf.math.tan`, such that
 * if `y = tf.math.tan(x)` then, `x = tf.math.atan(y)`.
 * <p>
 * <b>Note</b>: The output of `tf.math.atan` will lie within the invertible range
 * of tan, i.e (-pi/2, pi/2).
 * <p>
 * For example:
 * <pre>{@code
 * # Note: [1.047, 0.785] ~= [(pi/3), (pi/4)]
 * x = tf.constant([1.047, 0.785])
 * y = tf.math.tan(x) # [1.731261, 0.99920404]
 * 
 * tf.math.atan(y) # [1.047, 0.785] = x
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "math")
public final class Atan<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Atan operation.
   * 
   * @param scope current scope
   * @param x 
   * @return a new instance of Atan
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Atan<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Atan", scope.makeOpName("Atan"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Atan<T>(opBuilder.build());
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
  public static final String OP_NAME = "Atan";
  
  private Output<T> y;
  
  private Atan(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
