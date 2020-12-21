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

package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Computes gradients for the scaled exponential linear (Selu) operation.
 * 
 * @param <T> data type for {@code backprops()} output
 */
public final class SeluGrad<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SeluGrad operation.
   * 
   * @param scope current scope
   * @param gradients The backpropagated gradients to the corresponding Selu operation.
   * @param outputs The outputs of the corresponding Selu operation.
   * @return a new instance of SeluGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> SeluGrad<T> create(Scope scope, Operand<T> gradients, Operand<T> outputs) {
    OperationBuilder opBuilder = scope.env().opBuilder("SeluGrad", scope.makeOpName("SeluGrad"));
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(outputs.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SeluGrad<T>(opBuilder.build());
  }
  
  /**
   * The gradients: `gradients * (outputs + scale * alpha)`
   * if outputs < 0, `scale * gradients` otherwise.
   */
  public Output<T> backprops() {
    return backprops;
  }
  
  @Override
  public Output<T> asOutput() {
    return backprops;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SeluGrad";
  
  private Output<T> backprops;
  
  private SeluGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    backprops = operation.output(outputIdx++);
  }
}
