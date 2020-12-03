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

package org.tensorflow.op.nn;

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
 * L2 Loss.
 * <p>
 * Computes half the L2 norm of a tensor without the `sqrt`:
 * <p>
 *     output = sum(t ** 2) / 2
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class L2Loss<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new L2Loss operation.
   * 
   * @param scope current scope
   * @param t Typically 2-D, but may have any dimensions.
   * @return a new instance of L2Loss
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> L2Loss<T> create(Scope scope, Operand<T> t) {
    OperationBuilder opBuilder = scope.env().opBuilder("L2Loss", scope.makeOpName("L2Loss"));
    opBuilder.addInput(t.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new L2Loss<T>(opBuilder.build());
  }
  
  /**
   * 0-D.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "L2Loss";
  
  private Output<T> output;
  
  private L2Loss(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
