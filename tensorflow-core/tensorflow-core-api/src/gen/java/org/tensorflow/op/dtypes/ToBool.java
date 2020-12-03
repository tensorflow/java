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

package org.tensorflow.op.dtypes;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Converts a tensor to a scalar predicate.
 * <p>
 * Converts a tensor to a scalar predicate with the following rules:
 * <p>
 * - For 0D tensors, truthiness is determined by comparing against a "zero"
 *   value. For numerical types it is the obvious zero. For strings it is the
 *   empty string.
 * <p>
 * - For >0D tensors, truthiness is determined by looking at the number of
 *   elements. If has zero elements, then the result is false. Otherwise the
 *   result is true.
 * <p>
 * This matches the behavior of If and While for determining if a tensor counts
 * as true/false for a branch condition.
 */
public final class ToBool extends RawOp implements Operand<TBool> {
  
  /**
   * Factory method to create a class wrapping a new ToBool operation.
   * 
   * @param scope current scope
   * @param input 
   * @return a new instance of ToBool
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ToBool create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("ToBool", scope.makeOpName("ToBool"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ToBool(opBuilder.build());
  }
  
  /**
   */
  public Output<TBool> output() {
    return output;
  }
  
  @Override
  public Output<TBool> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ToBool";
  
  private Output<TBool> output;
  
  private ToBool(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
