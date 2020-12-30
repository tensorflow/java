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
import org.tensorflow.types.family.TType;

/**
 * Outputs the single element from the given dataset.
 */
public final class DatasetToSingleElement extends RawOp implements Iterable<Operand<TType>> {
  
  /**
   * Factory method to create a class wrapping a new DatasetToSingleElement operation.
   * 
   * @param scope current scope
   * @param dataset A handle to a dataset that contains a single element.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of DatasetToSingleElement
   */
  @Endpoint(describeByClass = true)
  public static DatasetToSingleElement create(Scope scope, Operand<?> dataset, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("DatasetToSingleElement", scope.makeOpName("DatasetToSingleElement"));
    opBuilder.addInput(dataset.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new DatasetToSingleElement(opBuilder.build());
  }
  
  /**
   * The components of the single element of `input`.
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
  public static final String OP_NAME = "DatasetToSingleElement";
  
  private List<Output<?>> components;
  
  private DatasetToSingleElement(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int componentsLength = operation.outputListLength("components");
    components = Arrays.asList(operation.outputList(outputIdx, componentsLength));
    outputIdx += componentsLength;
  }
}
