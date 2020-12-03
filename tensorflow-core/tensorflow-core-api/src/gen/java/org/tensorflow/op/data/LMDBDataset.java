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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that emits the key-value pairs in one or more LMDB files.
 * <p>
 * The Lightning Memory-Mapped Database Manager, or LMDB, is an embedded binary
 * key-value database. This dataset can read the contents of LMDB database files,
 * the names of which generally have the `.mdb` suffix.
 * <p>
 * Each output element consists of a key-value pair represented as a pair of
 * scalar string `Tensor`s, where the first `Tensor` contains the key and the
 * second `Tensor` contains the value.
 * <p>
 * LMDB uses different file formats on big- and little-endian machines.
 * `data.LMDBDataset` can only read files in the format of the host machine.
 */
public final class LMDBDataset extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new LMDBDataset operation.
   * 
   * @param scope current scope
   * @param filenames A scalar or a vector containing the name(s) of the binary file(s) to be
   * read.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of LMDBDataset
   */
  @Endpoint(describeByClass = true)
  public static LMDBDataset create(Scope scope, Operand<TString> filenames, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("LMDBDataset", scope.makeOpName("LMDBDataset"));
    opBuilder.addInput(filenames.asOutput());
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
    return new LMDBDataset(opBuilder.build());
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
  public static final String OP_NAME = "LMDBDataset";
  
  private Output<?> handle;
  
  private LMDBDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
