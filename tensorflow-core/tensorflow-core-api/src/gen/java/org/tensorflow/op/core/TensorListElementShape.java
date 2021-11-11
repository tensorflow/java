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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * The shape of the elements of the given list, as a tensor.
 * input_handle: the list
 * element_shape: the shape of elements of the list
 *
 * @param <T> data type for {@code element_shape} output
 */
@OpMetadata(
    opType = TensorListElementShape.OP_NAME,
    inputsClass = TensorListElementShape.Inputs.class
)
@Operator
public final class TensorListElementShape<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListElementShape";

  private Output<T> elementShape;

  public TensorListElementShape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    elementShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListElementShape operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param shapeType The value of the shapeType attribute
   * @param <T> data type for {@code TensorListElementShape} output and operands
   * @return a new instance of TensorListElementShape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> TensorListElementShape<T> create(Scope scope,
      Operand<? extends TType> inputHandle, Class<T> shapeType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListElementShape");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.setAttr("shape_type", Operands.toDataType(shapeType));
    return new TensorListElementShape<>(opBuilder.build());
  }

  /**
   * Gets elementShape.
   *
   * @return elementShape.
   */
  public Output<T> elementShape() {
    return elementShape;
  }

  @Override
  public Output<T> asOutput() {
    return elementShape;
  }

  @OpInputsMetadata(
      outputsClass = TensorListElementShape.class
  )
  public static class Inputs extends RawOpInputs<TensorListElementShape<?>> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The shapeType attribute
     */
    public final DataType shapeType;

    public Inputs(GraphOperation op) {
      super(new TensorListElementShape<>(op), op, Arrays.asList("shape_type"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      shapeType = op.attributes().getAttrType("shape_type");
    }
  }
}
