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
import org.tensorflow.types.family.TType;

/**
 * Clips tensor values to a specified min and max.
 * <p>
 * Given a tensor `t`, this operation returns a tensor of the same type and
 * shape as `t` with its values clipped to `clip_value_min` and `clip_value_max`.
 * Any values less than `clip_value_min` are set to `clip_value_min`. Any values
 * greater than `clip_value_max` are set to `clip_value_max`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class ClipByValue<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ClipByValue operation.
   * 
   * @param scope current scope
   * @param t A `Tensor`.
   * @param clipValueMin A 0-D (scalar) `Tensor`, or a `Tensor` with the same shape
   * as `t`. The minimum value to clip by.
   * @param clipValueMax A 0-D (scalar) `Tensor`, or a `Tensor` with the same shape
   * as `t`. The maximum value to clip by.
   * @return a new instance of ClipByValue
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ClipByValue<T> create(Scope scope, Operand<T> t, Operand<T> clipValueMin, Operand<T> clipValueMax) {
    OperationBuilder opBuilder = scope.env().opBuilder("ClipByValue", scope.makeOpName("ClipByValue"));
    opBuilder.addInput(t.asOutput());
    opBuilder.addInput(clipValueMin.asOutput());
    opBuilder.addInput(clipValueMax.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ClipByValue<T>(opBuilder.build());
  }
  
  /**
   * A clipped `Tensor` with the same shape as input 't'.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ClipByValue";
  
  private Output<T> output;
  
  private ClipByValue(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
