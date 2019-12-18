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
import org.tensorflow.op.Operands;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class QuantizedConcatV2<T extends TType> extends PrimitiveOp {
  
  /**
   * Factory method to create a class wrapping a new QuantizedConcatV2 operation.
   * 
   * @param scope current scope
   * @param values 
   * @param axis 
   * @param inputMins 
   * @param inputMaxes 
   * @return a new instance of QuantizedConcatV2
   */
  public static <T extends TType, U extends TNumber> QuantizedConcatV2<T> create(Scope scope, Iterable<Operand<T>> values, Operand<U> axis, Iterable<Operand<TFloat>> inputMins, Iterable<Operand<TFloat>> inputMaxes) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedConcatV2", scope.makeOpName("QuantizedConcatV2"));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInput(axis.asOutput());
    opBuilder.addInputList(Operands.asOutputs(inputMins));
    opBuilder.addInputList(Operands.asOutputs(inputMaxes));
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new QuantizedConcatV2<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  /**
   */
  public Output<TFloat> outputMin() {
    return outputMin;
  }
  
  /**
   */
  public Output<TFloat> outputMax() {
    return outputMax;
  }
  
  private Output<T> output;
  private Output<TFloat> outputMin;
  private Output<TFloat> outputMax;
  
  private QuantizedConcatV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }
}
