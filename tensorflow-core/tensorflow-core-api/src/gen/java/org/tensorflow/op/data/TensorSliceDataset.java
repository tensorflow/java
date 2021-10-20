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

package org.tensorflow.op.data;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that emits each dim-0 slice of {@code components} once.
 */
@Operator(
    group = "data"
)
public final class TensorSliceDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorSliceDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private TensorSliceDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorSliceDataset operation.
   *
   * @param scope current scope
   * @param components The components value
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of TensorSliceDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorSliceDataset create(Scope scope, Iterable<Operand<?>> components,
      List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorSliceDataset");
    opBuilder.addInputList(Operands.asOutputs(components));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new TensorSliceDataset(opBuilder.build());
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

  public static class Inputs extends RawOpInputs<TensorSliceDataset> {
    /**
     * The components input
     */
    public final Iterable<Operand<?>> components;

    /**
     * The ToutputTypes attribute
     */
    public final DataType[] ToutputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new TensorSliceDataset(op), op, Arrays.asList("Toutput_types", "output_shapes"));
      int inputIndex = 0;
      int componentsLength = op.inputListLength("components");
      components = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, componentsLength));
      inputIndex += componentsLength;
      ToutputTypes = op.attributes().getAttrTypeList("Toutput_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
