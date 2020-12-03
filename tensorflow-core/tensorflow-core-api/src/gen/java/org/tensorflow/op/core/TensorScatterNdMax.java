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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class TensorScatterNdMax<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TensorScatterNdMax operation.
   * 
   * @param scope current scope
   * @param tensor Tensor to update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @return a new instance of TensorScatterNdMax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> TensorScatterNdMax<T> create(Scope scope, Operand<T> tensor, Operand<U> indices, Operand<T> updates) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorScatterMax", scope.makeOpName("TensorScatterNdMax"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorScatterNdMax<T>(opBuilder.build());
  }
  
  /**
   * A new tensor copied from tensor whose values are element-wise maximum between tensor and updates according to the indices.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorScatterMax";
  
  private Output<T> output;
  
  private TensorScatterNdMax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
