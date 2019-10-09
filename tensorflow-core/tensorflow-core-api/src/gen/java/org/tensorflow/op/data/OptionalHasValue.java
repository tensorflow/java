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

package org.tensorflow.op.data;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;

/**
 * Returns true if and only if the given Optional variant has a value.
 */
@Operator(group = "data")
public final class OptionalHasValue extends PrimitiveOp implements Operand<TBool> {
  
  /**
   * Factory method to create a class wrapping a new OptionalHasValue operation.
   * 
   * @param scope current scope
   * @param optional 
   * @return a new instance of OptionalHasValue
   */
  public static OptionalHasValue create(Scope scope, Operand<?> optional) {
    OperationBuilder opBuilder = scope.env().opBuilder("OptionalHasValue", scope.makeOpName("OptionalHasValue"));
    opBuilder.addInput(optional.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new OptionalHasValue(opBuilder.build());
  }
  
  /**
   */
  public Output<TBool> hasValue() {
    return hasValue;
  }
  
  @Override
  public Output<TBool> asOutput() {
    return hasValue;
  }
  
  private Output<TBool> hasValue;
  
  private OptionalHasValue(Operation operation) {
    super(operation);
    int outputIdx = 0;
    hasValue = operation.output(outputIdx++);
  }
}
