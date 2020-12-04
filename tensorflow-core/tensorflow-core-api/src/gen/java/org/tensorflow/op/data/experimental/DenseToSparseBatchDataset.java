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
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that batches input elements into a SparseTensor.
 */
public final class DenseToSparseBatchDataset extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new DenseToSparseBatchDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset A handle to an input dataset. Must have a single component.
   * @param batchSize A scalar representing the number of elements to accumulate in a
   * batch.
   * @param rowShape A vector representing the dense shape of each row in the produced
   * SparseTensor. The shape may be partially specified, using `-1` to indicate
   * that a particular dimension should use the maximum size of all batch elements.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of DenseToSparseBatchDataset
   */
  @Endpoint(describeByClass = true)
  public static DenseToSparseBatchDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> batchSize, Operand<TInt64> rowShape, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalDenseToSparseBatchDataset", scope.makeOpName("DenseToSparseBatchDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(batchSize.asOutput());
    opBuilder.addInput(rowShape.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new DenseToSparseBatchDataset(opBuilder.build());
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
  public static final String OP_NAME = "ExperimentalDenseToSparseBatchDataset";
  
  private Output<?> handle;
  
  private DenseToSparseBatchDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
