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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Concats all tensors in the list along the 0th dimension.
 * Requires that all tensors have the same shape except the first dimension.
 * <p>input_handle: The input list.
 * element_shape: The shape of the uninitialized elements in the list. If the first
 * dimension is not -1, it is assumed that all list elements have the same
 * leading dim.
 * leading_dims: The list of leading dims of uninitialized list elements. Used if
 * the leading dim of input_handle.element_shape or the element_shape input arg
 * is not already set.
 * tensor: The concated result.
 * lengths: Output tensor containing sizes of the 0th dimension of tensors in the list, used for computing the gradient.
 *
 * @param <U> data type for {@code tensor} output
 */
@OpMetadata(
    opType = TensorListConcat.OP_NAME,
    inputsClass = TensorListConcat.Inputs.class
)
@Operator
public final class TensorListConcat<U extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListConcatV2";

  private Output<U> tensor;

  private Output<TInt64> lengths;

  public TensorListConcat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
    lengths = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListConcatV2 operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param elementShape The elementShape value
   * @param leadingDims The leadingDims value
   * @param elementDtype The value of the elementDtype attribute
   * @param <U> data type for {@code TensorListConcatV2} output and operands
   * @return a new instance of TensorListConcat
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> TensorListConcat<U> create(Scope scope,
      Operand<? extends TType> inputHandle, Operand<? extends TNumber> elementShape,
      Operand<TInt64> leadingDims, Class<U> elementDtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListConcat");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.addInput(leadingDims.asOutput());
    opBuilder.setAttr("element_dtype", Operands.toDataType(elementDtype));
    return new TensorListConcat<>(opBuilder.build());
  }

  /**
   * Gets tensor.
   *
   * @return tensor.
   */
  public Output<U> tensor() {
    return tensor;
  }

  /**
   * Gets lengths.
   *
   * @return lengths.
   */
  public Output<TInt64> lengths() {
    return lengths;
  }

  @OpInputsMetadata(
      outputsClass = TensorListConcat.class
  )
  public static class Inputs extends RawOpInputs<TensorListConcat<?>> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The elementShape input
     */
    public final Operand<? extends TNumber> elementShape;

    /**
     * The leadingDims input
     */
    public final Operand<TInt64> leadingDims;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    /**
     * The shapeType attribute
     */
    public final DataType shapeType;

    public Inputs(GraphOperation op) {
      super(new TensorListConcat<>(op), op, Arrays.asList("element_dtype", "shape_type"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      elementShape = (Operand<? extends TNumber>) op.input(inputIndex++);
      leadingDims = (Operand<TInt64>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
      shapeType = op.attributes().getAttrType("shape_type");
    }
  }
}
