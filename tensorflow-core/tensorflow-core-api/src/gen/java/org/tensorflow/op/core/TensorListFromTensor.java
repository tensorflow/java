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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Creates a TensorList which, when stacked, has the value of {@code tensor}.
 * Each tensor in the result list corresponds to one row of the input tensor.
 * <p>tensor: The input tensor.
 * output_handle: The list.
 */
@OpMetadata(
    opType = TensorListFromTensor.OP_NAME,
    inputsClass = TensorListFromTensor.Inputs.class
)
@Operator
public final class TensorListFromTensor extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListFromTensor";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  public TensorListFromTensor(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListFromTensor operation.
   *
   * @param scope current scope
   * @param tensor The tensor value
   * @param elementShape The elementShape value
   * @return a new instance of TensorListFromTensor
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListFromTensor create(Scope scope, Operand<? extends TType> tensor,
      Operand<? extends TNumber> elementShape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListFromTensor");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    return new TensorListFromTensor(opBuilder.build());
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
      outputsClass = TensorListFromTensor.class
  )
  public static class Inputs extends RawOpInputs<TensorListFromTensor> {
    /**
     * The tensor input
     */
    public final Operand<? extends TType> tensor;

    /**
     * The elementShape input
     */
    public final Operand<? extends TNumber> elementShape;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    /**
     * The shapeType attribute
     */
    public final DataType shapeType;

    public Inputs(GraphOperation op) {
      super(new TensorListFromTensor(op), op, Arrays.asList("element_dtype", "shape_type"));
      int inputIndex = 0;
      tensor = (Operand<? extends TType>) op.input(inputIndex++);
      elementShape = (Operand<? extends TNumber>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
      shapeType = op.attributes().getAttrType("shape_type");
    }
  }
}
