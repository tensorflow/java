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

package org.tensorflow.op.debugging;

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
 * Checks a tensor for NaN, -Inf and +Inf values.
 * <p>
 * When run, reports an `InvalidArgument` error if `tensor` has any values
 * that are not a number (NaN) or infinity (Inf). Otherwise, passes `tensor` as-is.
 * Unlike CheckNumerics (V1), CheckNumericsV2 distinguishes -Inf and +Inf in the
 * errors it throws.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class CheckNumericsV2<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new CheckNumericsV2 operation.
   * 
   * @param scope current scope
   * @param tensor 
   * @param message Prefix of the error message.
   * @return a new instance of CheckNumericsV2
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> CheckNumericsV2<T> create(Scope scope, Operand<T> tensor, String message) {
    OperationBuilder opBuilder = scope.env().opBuilder("CheckNumericsV2", scope.makeOpName("CheckNumericsV2"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("message", message);
    return new CheckNumericsV2<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  private CheckNumericsV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
