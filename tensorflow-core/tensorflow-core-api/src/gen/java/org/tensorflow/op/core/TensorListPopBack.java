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
 * Returns the last element of the input list as well as a list with all but that element.
 * Fails if the list is empty.
 * <p>input_handle: the input list
 * tensor: the withdrawn last element of the list
 * element_dtype: the type of elements in the list
 * element_shape: the shape of the output tensor
 *
 * @param <T> data type for {@code tensor} output
 */
@Operator
public final class TensorListPopBack<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListPopBack";

  private Output<? extends TType> outputHandle;

  private Output<T> tensor;

  @SuppressWarnings("unchecked")
  private TensorListPopBack(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
    tensor = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListPopBack operation.
   *
   * @param scope current scope
   * @param inputHandle the inputHandle value
   * @param elementShape the elementShape value
   * @param elementDtype the value of the elementDtype property
   * @param <T> data type for {@code TensorListPopBack} output and operands
   * @return a new instance of TensorListPopBack
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorListPopBack<T> create(Scope scope,
      Operand<? extends TType> inputHandle, Operand<TInt32> elementShape, Class<T> elementDtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListPopBack");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.setAttr("element_dtype", Operands.toDataType(elementDtype));
    return new TensorListPopBack<>(opBuilder.build());
  }

  /**
   * Gets outputHandle.
   *
   * @return outputHandle.
   */
  public Output<? extends TType> outputHandle() {
    return outputHandle;
  }

  /**
   * Gets tensor.
   *
   * @return tensor.
   */
  public Output<T> tensor() {
    return tensor;
  }
}
