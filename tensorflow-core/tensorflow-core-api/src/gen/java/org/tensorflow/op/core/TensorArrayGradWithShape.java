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
import org.tensorflow.types.TInt32;

/**
 * Creates a TensorArray for storing multiple gradients of values in the given handle.
 * <p>
 * Similar to TensorArrayGradV3. However it creates an accumulator with an
 * expanded shape compared to the input TensorArray whose gradient is being
 * computed. This enables multiple gradients for the same TensorArray to be
 * calculated using the same accumulator.
 */
@Operator
public final class TensorArrayGradWithShape extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new TensorArrayGradWithShape operation.
   * 
   * @param scope current scope
   * @param handle The handle to the forward TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param shapeToPrepend An int32 vector representing a shape. Elements in the gradient accumulator will
   * have shape which is this shape_to_prepend value concatenated with shape of the
   * elements in the TensorArray corresponding to the input handle.
   * @param source The gradient source string, used to decide which gradient TensorArray
   * to return.
   * @return a new instance of TensorArrayGradWithShape
   */
  @Endpoint(describeByClass = true)
  public static TensorArrayGradWithShape create(Scope scope, Operand<?> handle, Operand<TFloat32> flowIn, Operand<TInt32> shapeToPrepend, String source) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorArrayGradWithShape", scope.makeOpName("TensorArrayGradWithShape"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder.addInput(shapeToPrepend.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("source", source);
    return new TensorArrayGradWithShape(opBuilder.build());
  }
  
  /**
   */
  public Output<?> gradHandle() {
    return gradHandle;
  }
  
  /**
   */
  public Output<TFloat32> flowOut() {
    return flowOut;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorArrayGradWithShape";
  
  private Output<?> gradHandle;
  private Output<TFloat32> flowOut;
  
  private TensorArrayGradWithShape(Operation operation) {
    super(operation);
    int outputIdx = 0;
    gradHandle = operation.output(outputIdx++);
    flowOut = operation.output(outputIdx++);
  }
}
