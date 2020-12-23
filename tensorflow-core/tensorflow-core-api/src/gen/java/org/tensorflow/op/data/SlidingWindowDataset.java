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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that passes a sliding window over `input_dataset`.
 */
public final class SlidingWindowDataset extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new SlidingWindowDataset operation.
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
   * @return a new instance of SlidingWindowDataset
   */
  @Endpoint(describeByClass = true)
  public static SlidingWindowDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> windowSize, Operand<TInt64> windowShift, Operand<TInt64> windowStride, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("SlidingWindowDataset", scope.makeOpName("SlidingWindowDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(windowSize.asOutput());
    opBuilder.addInput(windowShift.asOutput());
    opBuilder.addInput(windowStride.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new SlidingWindowDataset(opBuilder.build());
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SlidingWindowDataset";
  
  private Output<?> handle;
  
  private SlidingWindowDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
