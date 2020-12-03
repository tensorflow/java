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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Constructs an Optional variant from a tuple of tensors.
 */
@Operator(group = "data")
public final class OptionalFromValue extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new OptionalFromValue operation.
   * 
   * @param scope current scope
   * @param components 
   * @return a new instance of OptionalFromValue
   */
  @Endpoint(describeByClass = true)
  public static OptionalFromValue create(Scope scope, Iterable<Operand<?>> components) {
    OperationBuilder opBuilder = scope.env().opBuilder("OptionalFromValue", scope.makeOpName("OptionalFromValue"));
    opBuilder.addInputList(Operands.asOutputs(components));
    opBuilder = scope.apply(opBuilder);
    return new OptionalFromValue(opBuilder.build());
  }
  
  /**
   */
  public Output<?> optional() {
    return optional;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) optional;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "OptionalFromValue";
  
  private Output<?> optional;
  
  private OptionalFromValue(Operation operation) {
    super(operation);
    int outputIdx = 0;
    optional = operation.output(outputIdx++);
  }
}
