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
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * A container for an iterator resource.
 */
@Operator(group = "data")
public final class AnonymousIterator extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new AnonymousIterator operation.
   * 
   * @param scope current scope
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of AnonymousIterator
   */
  @Endpoint(describeByClass = true)
  public static AnonymousIterator create(Scope scope, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("AnonymousIteratorV2", scope.makeOpName("AnonymousIterator"));
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
    return new AnonymousIterator(opBuilder.build());
  }
  
  /**
   * A handle to the iterator that can be passed to a "MakeIterator" or
   * "IteratorGetNext" op. In contrast to Iterator, AnonymousIterator prevents
   * resource sharing by name, and does not keep a reference to the resource
   * container.
   */
  public Output<?> handle() {
    return handle;
  }
  
  /**
   * A variant deleter that should be passed into the op that deletes the iterator.
   */
  public Output<?> deleter() {
    return deleter;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "AnonymousIteratorV2";
  
  private Output<?> handle;
  private Output<?> deleter;
  
  private AnonymousIterator(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
    deleter = operation.output(outputIdx++);
  }
}
