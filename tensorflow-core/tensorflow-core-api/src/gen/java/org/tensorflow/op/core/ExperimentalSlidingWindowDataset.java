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

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.Shape;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

/**
 * Creates a dataset that passes a sliding window over `input_dataset`.
 */
public final class ExperimentalSlidingWindowDataset extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class wrapping a new ExperimentalSlidingWindowDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param windowSize A scalar representing the number of elements in the
   * sliding window.
   * @param windowShift A scalar representing the steps moving the sliding window
   * forward in one iteration. It must be positive.
   * @param windowStride A scalar representing the stride of the input elements of the sliding window.
   * It must be positive.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of ExperimentalSlidingWindowDataset
   */
  public static ExperimentalSlidingWindowDataset create(Scope scope, Operand<?> inputDataset, Operand<Long> windowSize, Operand<Long> windowShift, Operand<Long> windowStride, List<Class<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalSlidingWindowDataset", scope.makeOpName("ExperimentalSlidingWindowDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(windowSize.asOutput());
    opBuilder.addInput(windowShift.asOutput());
    opBuilder.addInput(windowStride.asOutput());
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
    return new ExperimentalSlidingWindowDataset(opBuilder.build());
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
  
  private ExperimentalSlidingWindowDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
