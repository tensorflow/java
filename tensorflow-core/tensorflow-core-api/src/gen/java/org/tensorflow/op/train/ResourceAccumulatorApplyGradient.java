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

package org.tensorflow.op.train;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Applies a gradient to a given accumulator.
 * <p>
 * Does not add if local_step is lesser than the accumulator's global_step.
 */
public final class ResourceAccumulatorApplyGradient extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new ResourceAccumulatorApplyGradient operation.
   * 
   * @param scope current scope
   * @param handle The handle to a accumulator.
   * @param localStep The local_step value at which the gradient was computed.
   * @param gradient A tensor of the gradient to be accumulated.
   * @return a new instance of ResourceAccumulatorApplyGradient
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ResourceAccumulatorApplyGradient create(Scope scope, Operand<?> handle, Operand<TInt64> localStep, Operand<T> gradient) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceAccumulatorApplyGradient", scope.makeOpName("ResourceAccumulatorApplyGradient"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(localStep.asOutput());
    opBuilder.addInput(gradient.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ResourceAccumulatorApplyGradient(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ResourceAccumulatorApplyGradient";
  
  private ResourceAccumulatorApplyGradient(Operation operation) {
    super(operation);
  }
}
