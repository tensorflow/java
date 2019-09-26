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

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

/**
 * A substitute for `InterleaveDataset` on a fixed list of `N` datasets.
 */
public final class ExperimentalDirectedInterleaveDataset extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class wrapping a new ExperimentalDirectedInterleaveDataset operation.
   * 
   * @param scope current scope
   * @param selectorInputDataset A dataset of scalar `DT_INT64` elements that determines which of the
   * `N` data inputs should produce the next output element.
   * @param dataInputDatasets `N` datasets with the same type that will be interleaved according to
   * the values of `selector_input_dataset`.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of ExperimentalDirectedInterleaveDataset
   */
  public static ExperimentalDirectedInterleaveDataset create(Scope scope, Operand<?> selectorInputDataset, Iterable<Operand<?>> dataInputDatasets, List<Class<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalDirectedInterleaveDataset", scope.makeOpName("ExperimentalDirectedInterleaveDataset"));
    opBuilder.addInput(selectorInputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(dataInputDatasets));
    opBuilder = scope.applyControlDependencies(opBuilder);
    DataType[] outputTypesArray = new DataType[outputTypes.size()];
    for (int i = 0; i < outputTypesArray.length; ++i) {
      outputTypesArray[i] = DataType.fromClass(outputTypes.get(i));
    }
    opBuilder.setAttr("output_types", outputTypesArray);
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new ExperimentalDirectedInterleaveDataset(opBuilder.build());
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<Object> asOutput() {
    return (Output<Object>) handle;
  }
  
  private Output<?> handle;
  
  private ExperimentalDirectedInterleaveDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
