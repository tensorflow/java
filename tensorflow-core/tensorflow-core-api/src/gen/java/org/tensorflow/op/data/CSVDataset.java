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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 */
@Operator(group = "data")
public final class CSVDataset extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new CSVDataset operation.
   * 
   * @param scope current scope
   * @param filenames 
   * @param compressionType 
   * @param bufferSize 
   * @param header 
   * @param fieldDelim 
   * @param useQuoteDelim 
   * @param naValue 
   * @param selectCols 
   * @param recordDefaults 
   * @param outputShapes 
   * @return a new instance of CSVDataset
   */
  @Endpoint(describeByClass = true)
  public static CSVDataset create(Scope scope, Operand<TString> filenames, Operand<TString> compressionType, Operand<TInt64> bufferSize, Operand<TBool> header, Operand<TString> fieldDelim, Operand<TBool> useQuoteDelim, Operand<TString> naValue, Operand<TInt64> selectCols, Iterable<Operand<?>> recordDefaults, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("CSVDataset", scope.makeOpName("CSVDataset"));
    opBuilder.addInput(filenames.asOutput());
    opBuilder.addInput(compressionType.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(header.asOutput());
    opBuilder.addInput(fieldDelim.asOutput());
    opBuilder.addInput(useQuoteDelim.asOutput());
    opBuilder.addInput(naValue.asOutput());
    opBuilder.addInput(selectCols.asOutput());
    opBuilder.addInputList(Operands.asOutputs(recordDefaults));
    opBuilder = scope.apply(opBuilder);
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new CSVDataset(opBuilder.build());
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
  public static final String OP_NAME = "CSVDataset";
  
  private Output<?> handle;
  
  private CSVDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
