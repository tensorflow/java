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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Splits a tensor into a list.
 * list[i] corresponds to lengths[i] tensors from the input tensor.
 * The tensor must have rank at least 1 and contain exactly sum(lengths) elements.
 * <p>tensor: The input tensor.
 * element_shape: A shape compatible with that of elements in the tensor.
 * lengths: Vector of sizes of the 0th dimension of tensors in the list.
 * output_handle: The list.
 */
@Operator
public final class TensorListSplit extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListSplit";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  private TensorListSplit(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListSplit operation.
   *
   * @param scope current scope
   * @param tensor the tensor value
   * @param elementShape the elementShape value
   * @param lengths the lengths value
   * @return a new instance of TensorListSplit
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListSplit create(Scope scope, Operand<? extends TType> tensor,
      Operand<? extends TNumber> elementShape, Operand<TInt64> lengths) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListSplit", scope.makeOpName("TensorListSplit"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.addInput(lengths.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorListSplit(opBuilder.build());
  }

  /**
   * Gets outputHandle.
   *
   * @return outputHandle.
   */
  public Output<? extends TType> outputHandle() {
    return outputHandle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) outputHandle;
  }
}
