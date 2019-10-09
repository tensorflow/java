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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;

/**
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class SelectV2<T> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SelectV2 operation.
   * 
   * @param scope current scope
   * @param condition 
   * @param t 
   * @param e 
   * @return a new instance of SelectV2
   */
  public static <T> SelectV2<T> create(Scope scope, Operand<TBool> condition, Operand<T> t, Operand<T> e) {
    OperationBuilder opBuilder = scope.env().opBuilder("SelectV2", scope.makeOpName("SelectV2"));
    opBuilder.addInput(condition.asOutput());
    opBuilder.addInput(t.asOutput());
    opBuilder.addInput(e.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new SelectV2<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  private SelectV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
