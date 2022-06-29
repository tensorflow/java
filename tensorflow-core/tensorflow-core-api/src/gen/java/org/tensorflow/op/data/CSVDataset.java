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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The CSVDatasetV2 operation
 */
@OpMetadata(
    opType = CSVDataset.OP_NAME,
    inputsClass = CSVDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class CSVDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CSVDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public CSVDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CSVDatasetV2 operation.
   *
   * @param scope current scope
   * @param filenames The filenames value
   * @param compressionType The compressionType value
   * @param bufferSize The bufferSize value
   * @param header The header value
   * @param fieldDelim The fieldDelim value
   * @param useQuoteDelim The useQuoteDelim value
   * @param naValue The naValue value
   * @param selectCols The selectCols value
   * @param recordDefaults The recordDefaults value
   * @param excludeCols The excludeCols value
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of CSVDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static CSVDataset create(Scope scope, Operand<TString> filenames,
      Operand<TString> compressionType, Operand<TInt64> bufferSize, Operand<TBool> header,
      Operand<TString> fieldDelim, Operand<TBool> useQuoteDelim, Operand<TString> naValue,
      Operand<TInt64> selectCols, Iterable<Operand<?>> recordDefaults, Operand<TInt64> excludeCols,
      List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CSVDataset");
    opBuilder.addInput(filenames.asOutput());
    opBuilder.addInput(compressionType.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(header.asOutput());
    opBuilder.addInput(fieldDelim.asOutput());
    opBuilder.addInput(useQuoteDelim.asOutput());
    opBuilder.addInput(naValue.asOutput());
    opBuilder.addInput(selectCols.asOutput());
    opBuilder.addInputList(Operands.asOutputs(recordDefaults));
    opBuilder.addInput(excludeCols.asOutput());
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new CSVDataset(opBuilder.build());
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
      outputsClass = CSVDataset.class
  )
  public static class Inputs extends RawOpInputs<CSVDataset> {
    /**
     * The filenames input
     */
    public final Operand<TString> filenames;

    /**
     * The compressionType input
     */
    public final Operand<TString> compressionType;

    /**
     * The bufferSize input
     */
    public final Operand<TInt64> bufferSize;

    /**
     * The header input
     */
    public final Operand<TBool> header;

    /**
     * The fieldDelim input
     */
    public final Operand<TString> fieldDelim;

    /**
     * The useQuoteDelim input
     */
    public final Operand<TBool> useQuoteDelim;

    /**
     * The naValue input
     */
    public final Operand<TString> naValue;

    /**
     * The selectCols input
     */
    public final Operand<TInt64> selectCols;

    /**
     * The recordDefaults input
     */
    public final Iterable<Operand<?>> recordDefaults;

    /**
     * The excludeCols input
     */
    public final Operand<TInt64> excludeCols;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new CSVDataset(op), op, Arrays.asList("output_types", "output_shapes"));
      int inputIndex = 0;
      filenames = (Operand<TString>) op.input(inputIndex++);
      compressionType = (Operand<TString>) op.input(inputIndex++);
      bufferSize = (Operand<TInt64>) op.input(inputIndex++);
      header = (Operand<TBool>) op.input(inputIndex++);
      fieldDelim = (Operand<TString>) op.input(inputIndex++);
      useQuoteDelim = (Operand<TBool>) op.input(inputIndex++);
      naValue = (Operand<TString>) op.input(inputIndex++);
      selectCols = (Operand<TInt64>) op.input(inputIndex++);
      int recordDefaultsLength = op.inputListLength("record_defaults");
      recordDefaults = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, recordDefaultsLength));
      inputIndex += recordDefaultsLength;
      excludeCols = (Operand<TInt64>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
