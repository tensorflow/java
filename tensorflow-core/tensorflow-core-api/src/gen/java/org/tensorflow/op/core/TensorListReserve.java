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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * List of the given size with empty elements.
 * element_shape: the shape of the future elements of the list
 * num_elements: the number of elements to reserve
 * handle: the output list
 * element_dtype: the desired type of elements in the list.
 */
@OpMetadata(
    opType = TensorListReserve.OP_NAME,
    inputsClass = TensorListReserve.Inputs.class
)
@Operator
public final class TensorListReserve extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListReserve";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public TensorListReserve(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListReserve operation.
   *
   * @param scope current scope
   * @param elementShape The elementShape value
   * @param numElements The numElements value
   * @param elementDtype The value of the elementDtype attribute
   * @param <U> data type for {@code TensorListReserve} output and operands
   * @return a new instance of TensorListReserve
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> TensorListReserve create(Scope scope,
      Operand<? extends TNumber> elementShape, Operand<TInt32> numElements, Class<U> elementDtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListReserve");
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.addInput(numElements.asOutput());
    opBuilder.setAttr("element_dtype", Operands.toDataType(elementDtype));
    return new TensorListReserve(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  @OpInputsMetadata(
      outputsClass = TensorListReserve.class
  )
  public static class Inputs extends RawOpInputs<TensorListReserve> {
    /**
     * The elementShape input
     */
    public final Operand<? extends TNumber> elementShape;

    /**
     * The numElements input
     */
    public final Operand<TInt32> numElements;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    /**
     * The shapeType attribute
     */
    public final DataType shapeType;

    public Inputs(GraphOperation op) {
      super(new TensorListReserve(op), op, Arrays.asList("element_dtype", "shape_type"));
      int inputIndex = 0;
      elementShape = (Operand<? extends TNumber>) op.input(inputIndex++);
      numElements = (Operand<TInt32>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
      shapeType = op.attributes().getAttrType("shape_type");
    }
  }
}
