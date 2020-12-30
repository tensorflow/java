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
 * Creates a TensorArray for storing the gradients of values in the given handle.
 * <p>
 * If the given TensorArray gradient already exists, returns a reference to it.
 * <p>
 * Locks the size of the original TensorArray by disabling its dynamic size flag.
 * <p>
 * **A note about the input flow_in:**
 * <p>
 * The handle flow_in forces the execution of the gradient lookup to occur
 * only after certain other operations have occurred.  For example, when
 * the forward TensorArray is dynamically sized, writes to this TensorArray
 * may resize the object.  The gradient TensorArray is statically sized based
 * on the size of the forward TensorArray when this operation executes.
 * Furthermore, the size of the forward TensorArray is frozen by this call.
 * As a result, the flow is used to ensure that the call to generate the gradient
 * TensorArray only happens after all writes are executed.
 * <p>
 * In the case of dynamically sized TensorArrays, gradient computation should
 * only be performed on read operations that have themselves been chained via
 * flow to occur only after all writes have executed. That way the final size
 * of the forward TensorArray is known when this operation is called.
 * <p>
 * **A note about the source attribute:**
 * <p>
 * TensorArray gradient calls use an accumulator TensorArray object.  If
 * multiple gradients are calculated and run in the same session, the multiple
 * gradient nodes may accidentally flow through the same accumulator TensorArray.
 * This double counts and generally breaks the TensorArray gradient flow.
 * <p>
 * The solution is to identify which gradient call this particular
 * TensorArray gradient is being called in.  This is performed by identifying
 * a unique string (e.g. "gradients", "gradients_1", ...) from the input
 * gradient Tensor's name.  This string is used as a suffix when creating
 * the TensorArray gradient object here (the attribute `source`).
 * <p>
 * The attribute `source` is added as a suffix to the forward TensorArray's
 * name when performing the creation / lookup, so that each separate gradient
 * calculation gets its own TensorArray accumulator.
 */
@Operator
public final class TensorArrayGrad extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new TensorArrayGrad operation.
   * 
   * @param scope current scope
   * @param handle The handle to the forward TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param source The gradient source string, used to decide which gradient TensorArray
   * to return.
   * @return a new instance of TensorArrayGrad
   */
  @Endpoint(describeByClass = true)
  public static TensorArrayGrad create(Scope scope, Operand<?> handle, Operand<TFloat32> flowIn, String source) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorArrayGradV3", scope.makeOpName("TensorArrayGrad"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("source", source);
    return new TensorArrayGrad(opBuilder.build());
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
  public static final String OP_NAME = "TensorArrayGradV3";
  
  private Output<?> gradHandle;
  private Output<TFloat32> flowOut;
  
  private TensorArrayGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    gradHandle = operation.output(outputIdx++);
    flowOut = operation.output(outputIdx++);
  }
}
