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
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Extracts the average gradient in the given ConditionalAccumulator.
 * <p>
 * The op blocks until sufficient (i.e., more than num_required)
 * gradients have been accumulated.  If the accumulator has already
 * aggregated more than num_required gradients, it returns the average of
 * the accumulated gradients.  Also automatically increments the recorded
 * global_step in the accumulator by 1, and resets the aggregate to 0.
 * 
 * @param <T> data type for {@code average()} output
 */
public final class ResourceAccumulatorTakeGradient<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ResourceAccumulatorTakeGradient operation.
   * 
   * @param scope current scope
   * @param handle The handle to an accumulator.
   * @param numRequired Number of gradients required before we return an aggregate.
   * @param dtype The data type of accumulated gradients. Needs to correspond to the type
   * of the accumulator.
   * @return a new instance of ResourceAccumulatorTakeGradient
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ResourceAccumulatorTakeGradient<T> create(Scope scope, Operand<?> handle, Operand<TInt32> numRequired, Class<T> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceAccumulatorTakeGradient", scope.makeOpName("ResourceAccumulatorTakeGradient"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(numRequired.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new ResourceAccumulatorTakeGradient<T>(opBuilder.build());
  }
  
  /**
   * The average of the accumulated gradients.
   */
  public Output<T> average() {
    return average;
  }
  
  @Override
  public Output<T> asOutput() {
    return average;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ResourceAccumulatorTakeGradient";
  
  private Output<T> average;
  
  private ResourceAccumulatorTakeGradient(Operation operation) {
    super(operation);
    int outputIdx = 0;
    average = operation.output(outputIdx++);
  }
}
