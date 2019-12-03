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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.util.ndarray.Shape;

/**
 * A dataset that creates window datasets from the input dataset.
 */
public final class WindowDataset extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class wrapping a new WindowDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param size A scalar representing the number of elements to accumulate in a window.
   * @param shift A scalar representing the steps moving the sliding window forward in one
   * iteration. It must be positive.
   * @param stride A scalar representing the stride of the input elements of the sliding window.
   * It must be positive.
   * @param dropRemainder A scalar representing whether a window should be dropped in case its size is
   * smaller than desired.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of WindowDataset
   */
  public static WindowDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> size, Operand<TInt64> shift, Operand<TInt64> stride, Operand<TBool> dropRemainder, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("WindowDataset", scope.makeOpName("WindowDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder.addInput(shift.asOutput());
    opBuilder.addInput(stride.asOutput());
    opBuilder.addInput(dropRemainder.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    DataType[] outputTypesArray = new DataType[outputTypes.size()];
    for (int i = 0; i < outputTypesArray.length; ++i) {
      outputTypesArray[i] = outputTypes.get(i);
    }
    opBuilder.setAttr("output_types", outputTypesArray);
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new WindowDataset(opBuilder.build());
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
  
  private WindowDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
