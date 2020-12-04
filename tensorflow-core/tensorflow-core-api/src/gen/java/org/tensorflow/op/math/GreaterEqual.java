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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns the truth value of (x >= y) element-wise.
 * <p>
 * <i>NOTE</i>: `math.GreaterEqual` supports broadcasting. More about broadcasting
 * [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
 * <p>
 * Example:
 * <pre>{@code
 * x = tf.constant([5, 4, 6, 7])
 * y = tf.constant([5, 2, 5, 10])
 * tf.math.greater_equal(x, y) ==> [True, True, True, False]
 * 
 * x = tf.constant([5, 4, 6, 7])
 * y = tf.constant([5])
 * tf.math.greater_equal(x, y) ==> [True, False, True, True]
 * }</pre>
 * 
 */
@Operator(group = "math")
public final class GreaterEqual extends RawOp implements Operand<TBool> {
  
  /**
   * Factory method to create a class wrapping a new GreaterEqual operation.
   * 
   * @param scope current scope
   * @param x 
   * @param y 
   * @return a new instance of GreaterEqual
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> GreaterEqual create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.env().opBuilder("GreaterEqual", scope.makeOpName("GreaterEqual"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new GreaterEqual(opBuilder.build());
  }
  
  /**
   */
  public Output<TBool> z() {
    return z;
  }
  
  @Override
  public Output<TBool> asOutput() {
    return z;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "GreaterEqual";
  
  private Output<TBool> z;
  
  private GreaterEqual(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }
}
