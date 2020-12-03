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
 * Computes exponential of x element-wise.  \\(y = e^x\\).
 * <p>
 *   This function computes the exponential of every element in the input tensor.
 *   i.e. `exp(x)` or `e^(x)`, where `x` is the input tensor.
 *   `e` denotes Euler's number and is approximately equal to 2.718281.
 *   Output is positive for any real input.
 * <p>
 *   <pre>{@code
 *   x = tf.constant(2.0)
 *   tf.math.exp(x) ==> 7.389056
 * 
 *   x = tf.constant([2.0, 8.0])
 *   tf.math.exp(x) ==> array([7.389056, 2980.958], dtype=float32)
 *   }</pre>
 * For complex numbers, the exponential value is calculated as follows:
 * <p>
 *   <pre>{@code
 *   e^(x+iy) = e^x * e^iy = e^x * (cos y + i sin y)
 *   }</pre>
 * Let's consider complex number 1+1j as an example.
 *   e^1 * (cos 1 + i sin 1) = 2.7182818284590 * (0.54030230586+0.8414709848j)
 * <p>
 *   <pre>{@code
 *   x = tf.constant(1 + 1j)
 *   tf.math.exp(x) ==> 1.4686939399158851+2.2873552871788423j
 *   }</pre>
 * 
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "math")
public final class Exp<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Exp operation.
   * 
   * @param scope current scope
   * @param x 
   * @return a new instance of Exp
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Exp<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Exp", scope.makeOpName("Exp"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Exp<T>(opBuilder.build());
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
  public static final String OP_NAME = "Exp";
  
  private Output<T> y;
  
  private Exp(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
