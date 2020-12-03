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

/**
 * Returns the truth value of x AND y element-wise.
 * <p>
 * <i>NOTE</i>: `math.LogicalAnd` supports broadcasting. More about broadcasting
 * [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
 */
@Operator(group = "math")
public final class LogicalAnd extends RawOp implements Operand<TBool> {
  
  /**
   * Factory method to create a class wrapping a new LogicalAnd operation.
   * 
   * @param scope current scope
   * @param x 
   * @param y 
   * @return a new instance of LogicalAnd
   */
  @Endpoint(describeByClass = true)
  public static LogicalAnd create(Scope scope, Operand<TBool> x, Operand<TBool> y) {
    OperationBuilder opBuilder = scope.env().opBuilder("LogicalAnd", scope.makeOpName("LogicalAnd"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new LogicalAnd(opBuilder.build());
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
  public static final String OP_NAME = "LogicalAnd";
  
  private Output<TBool> z;
  
  private LogicalAnd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }
}
