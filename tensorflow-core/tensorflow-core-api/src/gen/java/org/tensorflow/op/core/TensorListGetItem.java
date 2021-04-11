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
 * The TensorListGetItem operation
 *
 * @param <T> data type for {@code item} output
 */
@Operator
public final class TensorListGetItem<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListGetItem";

  private Output<T> item;

  private TensorListGetItem(Operation operation) {
    super(operation);
    int outputIdx = 0;
    item = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListGetItem operation.
   *
   * @param scope current scope
   * @param inputHandle the inputHandle value
   * @param index the index value
   * @param elementShape the elementShape value
   * @param elementDtype the value of the elementDtype property
   * @param <T> data type for {@code TensorListGetItem} output and operands
   * @return a new instance of TensorListGetItem
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorListGetItem<T> create(Scope scope,
      Operand<? extends TType> inputHandle, Operand<TInt32> index, Operand<TInt32> elementShape,
      Class<T> elementDtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListGetItem", scope.makeOpName("TensorListGetItem"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("element_dtype", Operands.toDataType(elementDtype));
    return new TensorListGetItem<>(opBuilder.build());
  }

  /**
   * Gets item.
   *
   * @return item.
   */
  public Output<T> item() {
    return item;
  }

  @Override
  public Output<T> asOutput() {
    return item;
  }
}
