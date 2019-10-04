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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Forwards the ref tensor `data` to the output port determined by `pred`.
 * <p>
 * If `pred` is true, the `data` input is forwarded to `output_true`. Otherwise,
 * the data goes to `output_false`.
 * <p>
 * See also `Switch` and `Merge`.
 * 
 * @param <T> data type for {@code outputFalse()} output
 */
@Operator
public final class RefSwitch<T> extends PrimitiveOp {
  
  /**
   * Factory method to create a class wrapping a new RefSwitch operation.
   * 
   * @param scope current scope
   * @param data The ref tensor to be forwarded to the appropriate output.
   * @param pred A scalar that specifies which output port will receive data.
   * @return a new instance of RefSwitch
   */
  public static <T> RefSwitch<T> create(Scope scope, Operand<T> data, Operand<Boolean> pred) {
    OperationBuilder opBuilder = scope.env().opBuilder("RefSwitch", scope.makeOpName("RefSwitch"));
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(pred.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new RefSwitch<T>(opBuilder.build());
  }
  
  /**
   * If `pred` is false, data will be forwarded to this output.
   */
  public Output<T> outputFalse() {
    return outputFalse;
  }
  
  /**
   * If `pred` is true, data will be forwarded to this output.
   */
  public Output<T> outputTrue() {
    return outputTrue;
  }
  
  private Output<T> outputFalse;
  private Output<T> outputTrue;
  
  private RefSwitch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputFalse = operation.output(outputIdx++);
    outputTrue = operation.output(outputIdx++);
  }
}
