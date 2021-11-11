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
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that emits the key-value pairs in one or more LMDB files.
 * The Lightning Memory-Mapped Database Manager, or LMDB, is an embedded binary
 * key-value database. This dataset can read the contents of LMDB database files,
 * the names of which generally have the {@code .mdb} suffix.
 * <p>Each output element consists of a key-value pair represented as a pair of
 * scalar string {@code Tensor}s, where the first {@code Tensor} contains the key and the
 * second {@code Tensor} contains the value.
 * <p>LMDB uses different file formats on big- and little-endian machines.
 * {@code data.LMDBDataset} can only read files in the format of the host machine.
 */
@OpMetadata(
    opType = LMDBDataset.OP_NAME,
    inputsClass = LMDBDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class LMDBDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LMDBDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public LMDBDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LMDBDataset operation.
   *
   * @param scope current scope
   * @param filenames A scalar or a vector containing the name(s) of the binary file(s) to be
   * read.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of LMDBDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static LMDBDataset create(Scope scope, Operand<TString> filenames,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LMDBDataset");
    opBuilder.addInput(filenames.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new LMDBDataset(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  @OpInputsMetadata(
      outputsClass = LMDBDataset.class
  )
  public static class Inputs extends RawOpInputs<LMDBDataset> {
    /**
     * A scalar or a vector containing the name(s) of the binary file(s) to be
     * read.
     */
    public final Operand<TString> filenames;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new LMDBDataset(op), op, Arrays.asList("output_types", "output_shapes"));
      int inputIndex = 0;
      filenames = (Operand<TString>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
