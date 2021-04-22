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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Creates a TensorList by indexing into a Tensor.
 * Each member of the TensorList corresponds to one row of the input tensor,
 * specified by the given index (see {@code tf.gather}).
 * <p>tensor: The input tensor.
 * indices: The indices used to index into the list.
 * element_shape: The shape of the elements in the list (can be less specified than
 * the shape of the tensor).
 * num_elements: The size of the output list. Must be large enough to accommodate
 * the largest index in indices. If -1, the list is just large enough to include
 * the largest index in indices.
 * output_handle: The TensorList.
 */
@Operator
public final class TensorListScatter extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListScatterV2";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  private TensorListScatter(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListScatterV2 operation.
   *
   * @param scope current scope
   * @param tensor the tensor value
   * @param indices the indices value
   * @param elementShape the elementShape value
   * @param numElements the numElements value
   * @return a new instance of TensorListScatter
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListScatter create(Scope scope, Operand<? extends TType> tensor,
      Operand<TInt32> indices, Operand<? extends TNumber> elementShape,
      Operand<TInt32> numElements) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListScatterV2", scope.makeOpName("TensorListScatter"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.addInput(numElements.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorListScatter(opBuilder.build());
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
