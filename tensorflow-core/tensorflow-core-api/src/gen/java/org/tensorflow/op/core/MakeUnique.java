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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;

/**
 * Make all elements in the non-Batch dimension unique, but \"close\" to
 * <p>
 * their initial value. Never returns a sub-normal number. Never returns
 * zero. The sign of each input element is always identical to the sign
 * of the corresponding output element. Behavior for infinite elements is
 * undefined. Behavior for subnormal elements is undefined.
 */
@Operator
public final class MakeUnique extends RawOp implements Operand<TFloat32> {
  
  /**
   * Factory method to create a class wrapping a new MakeUnique operation.
   * 
   * @param scope current scope
   * @param input 
   * @return a new instance of MakeUnique
   */
  @Endpoint(describeByClass = true)
  public static MakeUnique create(Scope scope, Operand<TFloat32> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("MakeUnique", scope.makeOpName("MakeUnique"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new MakeUnique(opBuilder.build());
  }
  
  /**
   */
  public Output<TFloat32> output() {
    return output;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MakeUnique";
  
  private Output<TFloat32> output;
  
  private MakeUnique(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
