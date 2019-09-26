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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Returns element-wise smallest integer not less than x.
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "math")
public final class Ceil<T extends Number> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Ceil operation.
   * 
   * @param scope current scope
   * @param x 
   * @return a new instance of Ceil
   */
  public static <T extends Number> Ceil<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Ceil", scope.makeOpName("Ceil"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new Ceil<T>(opBuilder.build());
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
  
  private Output<T> y;
  
  private Ceil(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
