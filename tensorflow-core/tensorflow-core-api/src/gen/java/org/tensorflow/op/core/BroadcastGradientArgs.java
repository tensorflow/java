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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Return the reduction indices for computing gradients of s0 op s1 with broadcast.
 * <p>
 * This is typically used by gradient computations for a broadcasting operation.
 * 
 * @param <T> data type for {@code r0()} output
 */
public final class BroadcastGradientArgs<T extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new BroadcastGradientArgs operation.
   * 
   * @param scope current scope
   * @param s0 
   * @param s1 
   * @return a new instance of BroadcastGradientArgs
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> BroadcastGradientArgs<T> create(Scope scope, Operand<T> s0, Operand<T> s1) {
    OperationBuilder opBuilder = scope.env().opBuilder("BroadcastGradientArgs", scope.makeOpName("BroadcastGradientArgs"));
    opBuilder.addInput(s0.asOutput());
    opBuilder.addInput(s1.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new BroadcastGradientArgs<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> r0() {
    return r0;
  }
  
  /**
   */
  public Output<T> r1() {
    return r1;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BroadcastGradientArgs";
  
  private Output<T> r0;
  private Output<T> r1;
  
  private BroadcastGradientArgs(Operation operation) {
    super(operation);
    int outputIdx = 0;
    r0 = operation.output(outputIdx++);
    r1 = operation.output(outputIdx++);
  }
}
