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
@OpMetadata(
    opType = TensorListSplit.OP_NAME,
    inputsClass = TensorListSplit.Inputs.class
)
@Operator
public final class TensorListSplit extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListSplit";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  public TensorListSplit(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListSplit operation.
   *
   * @param scope current scope
   * @param tensor The tensor value
   * @param elementShape The elementShape value
   * @param lengths The lengths value
   * @return a new instance of TensorListSplit
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListSplit create(Scope scope, Operand<? extends TType> tensor,
      Operand<? extends TNumber> elementShape, Operand<TInt64> lengths) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListSplit");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.addInput(lengths.asOutput());
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

  @OpInputsMetadata(
      outputsClass = TensorListSplit.class
  )
  public static class Inputs extends RawOpInputs<TensorListSplit> {
    /**
     * The tensor input
     */
    public final Operand<? extends TType> tensor;

    /**
     * The elementShape input
     */
    public final Operand<? extends TNumber> elementShape;

    /**
     * The lengths input
     */
    public final Operand<TInt64> lengths;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    /**
     * The shapeType attribute
     */
    public final DataType shapeType;

    public Inputs(GraphOperation op) {
      super(new TensorListSplit(op), op, Arrays.asList("element_dtype", "shape_type"));
      int inputIndex = 0;
      tensor = (Operand<? extends TType>) op.input(inputIndex++);
      elementShape = (Operand<? extends TNumber>) op.input(inputIndex++);
      lengths = (Operand<TInt64>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
      shapeType = op.attributes().getAttrType("shape_type");
    }
  }
}
