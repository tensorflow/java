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
import java.util.Iterator;
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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Gets next element for the provided shard number.
 */
public final class MultiDeviceIteratorGetNextFromShard extends RawOp implements Iterable<Operand<TType>> {
  
  /**
   * Factory method to create a class wrapping a new MultiDeviceIteratorGetNextFromShard operation.
   * 
   * @param scope current scope
   * @param multiDeviceIterator A MultiDeviceIterator resource.
   * @param shardNum Integer representing which shard to fetch data for.
   * @param incarnationId Which incarnation of the MultiDeviceIterator is running.
   * @param outputTypes The type list for the return values.
   * @param outputShapes The list of shapes being produced.
   * @return a new instance of MultiDeviceIteratorGetNextFromShard
   */
  @Endpoint(describeByClass = true)
  public static MultiDeviceIteratorGetNextFromShard create(Scope scope, Operand<?> multiDeviceIterator, Operand<TInt32> shardNum, Operand<TInt64> incarnationId, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("MultiDeviceIteratorGetNextFromShard", scope.makeOpName("MultiDeviceIteratorGetNextFromShard"));
    opBuilder.addInput(multiDeviceIterator.asOutput());
    opBuilder.addInput(shardNum.asOutput());
    opBuilder.addInput(incarnationId.asOutput());
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
    return new MultiDeviceIteratorGetNextFromShard(opBuilder.build());
  }
  
  /**
   * Result of the get_next on the dataset.
   */
  public List<Output<?>> components() {
    return components;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) components.iterator();
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MultiDeviceIteratorGetNextFromShard";
  
  private List<Output<?>> components;
  
  private MultiDeviceIteratorGetNextFromShard(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int componentsLength = operation.outputListLength("components");
    components = Arrays.asList(operation.outputList(outputIdx, componentsLength));
    outputIdx += componentsLength;
  }
}
