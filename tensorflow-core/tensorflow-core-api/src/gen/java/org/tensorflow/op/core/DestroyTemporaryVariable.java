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
 * Destroys the temporary variable and returns its final value.
 * <p>
 * Sets output to the value of the Tensor pointed to by 'ref', then destroys
 * the temporary variable called 'var_name'.
 * All other uses of 'ref' <i>must</i> have executed before this op.
 * This is typically achieved by chaining the ref through each assign op, or by
 * using control dependencies.
 * <p>
 * Outputs the final value of the tensor pointed to by 'ref'.
 * 
 * @param <T> data type for {@code value()} output
 */
@Operator
public final class DestroyTemporaryVariable<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new DestroyTemporaryVariable operation.
   * 
   * @param scope current scope
   * @param ref A reference to the temporary variable tensor.
   * @param varName Name of the temporary variable, usually the name of the matching
   * 'TemporaryVariable' op.
   * @return a new instance of DestroyTemporaryVariable
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> DestroyTemporaryVariable<T> create(Scope scope, Operand<T> ref, String varName) {
    OperationBuilder opBuilder = scope.env().opBuilder("DestroyTemporaryVariable", scope.makeOpName("DestroyTemporaryVariable"));
    opBuilder.addInput(ref.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("var_name", varName);
    return new DestroyTemporaryVariable<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> value() {
    return value;
  }
  
  @Override
  public Output<T> asOutput() {
    return value;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DestroyTemporaryVariable";
  
  private Output<T> value;
  
  private DestroyTemporaryVariable(Operation operation) {
    super(operation);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
  }
}
