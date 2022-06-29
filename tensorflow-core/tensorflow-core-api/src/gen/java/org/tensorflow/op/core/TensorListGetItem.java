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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * The TensorListGetItem operation
 *
 * @param <T> data type for {@code item} output
 */
@OpMetadata(
    opType = TensorListGetItem.OP_NAME,
    inputsClass = TensorListGetItem.Inputs.class
)
@Operator
public final class TensorListGetItem<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListGetItem";

  private Output<T> item;

  public TensorListGetItem(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    item = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListGetItem operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param index The index value
   * @param elementShape The elementShape value
   * @param elementDtype The value of the elementDtype attribute
   * @param <T> data type for {@code TensorListGetItem} output and operands
   * @return a new instance of TensorListGetItem
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorListGetItem<T> create(Scope scope,
      Operand<? extends TType> inputHandle, Operand<TInt32> index, Operand<TInt32> elementShape,
      Class<T> elementDtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListGetItem");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.addInput(elementShape.asOutput());
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

  @OpInputsMetadata(
      outputsClass = TensorListGetItem.class
  )
  public static class Inputs extends RawOpInputs<TensorListGetItem<?>> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The index input
     */
    public final Operand<TInt32> index;

    /**
     * The elementShape input
     */
    public final Operand<TInt32> elementShape;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    public Inputs(GraphOperation op) {
      super(new TensorListGetItem<>(op), op, Arrays.asList("element_dtype"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      index = (Operand<TInt32>) op.input(inputIndex++);
      elementShape = (Operand<TInt32>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
    }
  }
}
