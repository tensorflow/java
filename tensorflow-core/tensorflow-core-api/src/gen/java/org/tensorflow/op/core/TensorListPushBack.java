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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Returns a list which has the passed-in {@code Tensor} as last element and the other elements of the given list in {@code input_handle}.
 * tensor: The tensor to put on the list.
 * input_handle: The old list.
 * output_handle: A list with the elements of the old list followed by tensor.
 * element_dtype: the type of elements in the list.
 * element_shape: a shape compatible with that of elements in the list.
 */
@OpMetadata(
    opType = TensorListPushBack.OP_NAME,
    inputsClass = TensorListPushBack.Inputs.class
)
@Operator
public final class TensorListPushBack extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListPushBack";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  public TensorListPushBack(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListPushBack operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param tensor The tensor value
   * @return a new instance of TensorListPushBack
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListPushBack create(Scope scope, Operand<? extends TType> inputHandle,
      Operand<? extends TType> tensor) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListPushBack");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(tensor.asOutput());
    return new TensorListPushBack(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = TensorListPushBack.class
  )
  public static class Inputs extends RawOpInputs<TensorListPushBack> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The tensor input
     */
    public final Operand<? extends TType> tensor;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    public Inputs(GraphOperation op) {
      super(new TensorListPushBack(op), op, Arrays.asList("element_dtype"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      tensor = (Operand<? extends TType>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
    }
  }
}
