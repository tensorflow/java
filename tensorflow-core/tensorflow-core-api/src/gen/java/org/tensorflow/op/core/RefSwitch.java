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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Forwards the ref tensor {@code data} to the output port determined by {@code pred}.
 * If {@code pred} is true, the {@code data} input is forwarded to {@code output_true}. Otherwise,
 * the data goes to {@code output_false}.
 * <p>See also {@code Switch} and {@code Merge}.
 *
 * @param <T> data type for {@code output_false} output
 */
@Operator
public final class RefSwitch<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RefSwitch";

  private Output<T> outputFalse;

  private Output<T> outputTrue;

  private RefSwitch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputFalse = operation.output(outputIdx++);
    outputTrue = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RefSwitch operation.
   *
   * @param scope current scope
   * @param data The ref tensor to be forwarded to the appropriate output.
   * @param pred A scalar that specifies which output port will receive data.
   * @param <T> data type for {@code RefSwitch} output and operands
   * @return a new instance of RefSwitch
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RefSwitch<T> create(Scope scope, Operand<T> data,
      Operand<TBool> pred) {
    OperationBuilder opBuilder = scope.env().opBuilder("RefSwitch", scope.makeOpName("RefSwitch"));
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(pred.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RefSwitch<>(opBuilder.build());
  }

  /**
   * Gets outputFalse.
   * If {@code pred} is false, data will be forwarded to this output.
   * @return outputFalse.
   */
  public Output<T> outputFalse() {
    return outputFalse;
  }

  /**
   * Gets outputTrue.
   * If {@code pred} is true, data will be forwarded to this output.
   * @return outputTrue.
   */
  public Output<T> outputTrue() {
    return outputTrue;
  }
}
