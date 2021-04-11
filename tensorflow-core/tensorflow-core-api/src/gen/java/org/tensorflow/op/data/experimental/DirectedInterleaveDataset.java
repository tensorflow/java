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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * A substitute for {@code InterleaveDataset} on a fixed list of {@code N} datasets.
 */
public final class DirectedInterleaveDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalDirectedInterleaveDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private DirectedInterleaveDataset(Operation operation) {
    super(operation);
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
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of DirectedInterleaveDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static DirectedInterleaveDataset create(Scope scope,
      Operand<? extends TType> selectorInputDataset,
      Iterable<Operand<? extends TType>> dataInputDatasets,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalDirectedInterleaveDataset", scope.makeOpName("DirectedInterleaveDataset"));
    opBuilder.addInput(selectorInputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(dataInputDatasets));
    opBuilder = scope.apply(opBuilder);
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
}
