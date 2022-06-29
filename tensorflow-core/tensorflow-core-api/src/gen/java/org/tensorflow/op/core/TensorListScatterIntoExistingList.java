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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Scatters tensor at indices in an input list.
 * Each member of the TensorList corresponds to one row of the input tensor,
 * specified by the given index (see {@code tf.gather}).
 * <p>input_handle: The list to scatter into.
 * tensor: The input tensor.
 * indices: The indices used to index into the list.
 * output_handle: The TensorList.
 */
@OpMetadata(
    opType = TensorListScatterIntoExistingList.OP_NAME,
    inputsClass = TensorListScatterIntoExistingList.Inputs.class
)
@Operator
public final class TensorListScatterIntoExistingList extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListScatterIntoExistingList";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  public TensorListScatterIntoExistingList(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListScatterIntoExistingList operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param tensor The tensor value
   * @param indices The indices value
   * @return a new instance of TensorListScatterIntoExistingList
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListScatterIntoExistingList create(Scope scope,
      Operand<? extends TType> inputHandle, Operand<? extends TType> tensor,
      Operand<TInt32> indices) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListScatterIntoExistingList");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    return new TensorListScatterIntoExistingList(opBuilder.build());
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
      outputsClass = TensorListScatterIntoExistingList.class
  )
  public static class Inputs extends RawOpInputs<TensorListScatterIntoExistingList> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The tensor input
     */
    public final Operand<? extends TType> tensor;

    /**
     * The indices input
     */
    public final Operand<TInt32> indices;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    public Inputs(GraphOperation op) {
      super(new TensorListScatterIntoExistingList(op), op, Arrays.asList("element_dtype"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      tensor = (Operand<? extends TType>) op.input(inputIndex++);
      indices = (Operand<TInt32>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
    }
  }
}
