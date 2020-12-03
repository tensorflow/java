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
 * Computes the power of one value to another.
 * <p>
 * Given a tensor `x` and a tensor `y`, this operation computes \\(x^y\\) for
 * corresponding elements in `x` and `y`. For example:
 * <pre>{@code
 * # tensor 'x' is [[2, 2]], [3, 3]]
 * # tensor 'y' is [[8, 16], [2, 3]]
 * tf.pow(x, y) ==> [[256, 65536], [9, 27]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code z()} output
 */
@Operator(group = "math")
public final class Pow<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Pow operation.
   * 
   * @param scope current scope
   * @param x 
   * @param y 
   * @return a new instance of Pow
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Pow<T> create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.env().opBuilder("Pow", scope.makeOpName("Pow"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Pow<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> z() {
    return z;
  }
  
  @Override
  public Output<T> asOutput() {
    return z;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Pow";
  
  private Output<T> z;
  
  private Pow(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }
}
