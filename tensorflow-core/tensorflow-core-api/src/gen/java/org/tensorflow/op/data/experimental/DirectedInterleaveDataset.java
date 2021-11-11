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

package org.tensorflow.op.data.experimental;

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
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * A substitute for {@code InterleaveDataset} on a fixed list of {@code N} datasets.
 */
@OpMetadata(
    opType = DirectedInterleaveDataset.OP_NAME,
    inputsClass = DirectedInterleaveDataset.Inputs.class
)
public final class DirectedInterleaveDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalDirectedInterleaveDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public DirectedInterleaveDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalDirectedInterleaveDataset operation.
   *
   * @param scope current scope
   * @param selectorInputDataset A dataset of scalar {@code DT_INT64} elements that determines which of the
   * {@code N} data inputs should produce the next output element.
   * @param dataInputDatasets {@code N} datasets with the same type that will be interleaved according to
   * the values of {@code selector_input_dataset}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of DirectedInterleaveDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static DirectedInterleaveDataset create(Scope scope,
      Operand<? extends TType> selectorInputDataset,
      Iterable<Operand<? extends TType>> dataInputDatasets,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DirectedInterleaveDataset");
    opBuilder.addInput(selectorInputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(dataInputDatasets));
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new DirectedInterleaveDataset(opBuilder.build());
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
      outputsClass = DirectedInterleaveDataset.class
  )
  public static class Inputs extends RawOpInputs<DirectedInterleaveDataset> {
    /**
     * A dataset of scalar {@code DT_INT64} elements that determines which of the
     * {@code N} data inputs should produce the next output element.
     */
    public final Operand<? extends TType> selectorInputDataset;

    /**
     * {@code N} datasets with the same type that will be interleaved according to
     * the values of {@code selector_input_dataset}.
     */
    public final Iterable<Operand<? extends TType>> dataInputDatasets;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new DirectedInterleaveDataset(op), op, Arrays.asList("output_types", "output_shapes"));
      int inputIndex = 0;
      selectorInputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int dataInputDatasetsLength = op.inputListLength("data_input_datasets");
      dataInputDatasets = Arrays.asList((Operand<? extends TType>[]) op.inputList(inputIndex, dataInputDatasetsLength));
      inputIndex += dataInputDatasetsLength;
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
