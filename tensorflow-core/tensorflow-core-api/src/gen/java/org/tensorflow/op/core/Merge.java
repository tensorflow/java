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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Forwards the value of an available tensor from `inputs` to `output`.
 * <p>
 * `Merge` waits for at least one of the tensors in `inputs` to become available.
 * It is usually combined with `Switch` to implement branching.
 * <p>
 * `Merge` forwards the first tensor to become available to `output`, and sets
 * `value_index` to its index in `inputs`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Merge<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new Merge operation.
   * 
   * @param scope current scope
   * @param inputs The input tensors, exactly one of which will become available.
   * @return a new instance of Merge
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Merge<T> create(Scope scope, Iterable<Operand<T>> inputs) {
    OperationBuilder opBuilder = scope.env().opBuilder("Merge", scope.makeOpName("Merge"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    return new Merge<T>(opBuilder.build());
  }
  
  /**
   * Will be set to the available input tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  /**
   * The index of the chosen input tensor in `inputs`.
   */
  public Output<TInt32> valueIndex() {
    return valueIndex;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Merge";
  
  private Output<T> output;
  private Output<TInt32> valueIndex;
  
  private Merge(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    valueIndex = operation.output(outputIdx++);
  }
}
