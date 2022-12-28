/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import java.util.Iterator;
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
 * The DTensorRestoreV2 operation
 */
@OpMetadata(
    opType = DTensorRestore.OP_NAME,
    inputsClass = DTensorRestore.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class DTensorRestore extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DTensorRestoreV2";

  private List<Output<?>> tensors;

  @SuppressWarnings("unchecked")
  public DTensorRestore(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int tensorsLength = operation.outputListLength("tensors");
    tensors = Arrays.asList(operation.outputList(outputIdx, tensorsLength));
    outputIdx += tensorsLength;
  }

  /**
   * Factory method to create a class wrapping a new DTensorRestoreV2 operation.
   *
   * @param scope current scope
   * @param prefix The prefix value
   * @param tensorNames The tensorNames value
   * @param shapeAndSlices The shapeAndSlices value
   * @param inputShapes The value of the inputShapes attribute
   * @param inputLayouts The value of the inputLayouts attribute
   * @param dtypes The value of the dtypes attribute
   * @return a new instance of DTensorRestore
   */
  @Endpoint(
      describeByClass = true
  )
  public static DTensorRestore create(Scope scope, Operand<TString> prefix,
      Operand<TString> tensorNames, Operand<TString> shapeAndSlices, List<Shape> inputShapes,
      List<String> inputLayouts, List<Class<? extends TType>> dtypes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DTensorRestore");
    opBuilder.addInput(prefix.asOutput());
    opBuilder.addInput(tensorNames.asOutput());
    opBuilder.addInput(shapeAndSlices.asOutput());
    Shape[] inputShapesArray = new Shape[inputShapes.size()];
    for (int i = 0 ; i < inputShapesArray.length ; i++) {
      inputShapesArray[i] = inputShapes.get(i);
    }
    opBuilder.setAttr("input_shapes", inputShapesArray);
    String[] inputLayoutsArray = new String[inputLayouts.size()];
    for (int i = 0 ; i < inputLayoutsArray.length ; i++) {
      inputLayoutsArray[i] = inputLayouts.get(i);
    }
    opBuilder.setAttr("input_layouts", inputLayoutsArray);
    opBuilder.setAttr("dtypes", Operands.toDataTypes(dtypes));
    return new DTensorRestore(opBuilder.build());
  }

  /**
   * Gets tensors.
   *
   * @return tensors.
   */
  public List<Output<?>> tensors() {
    return tensors;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) tensors.iterator();
  }

  @OpInputsMetadata(
      outputsClass = DTensorRestore.class
  )
  public static class Inputs extends RawOpInputs<DTensorRestore> {
    /**
     * The prefix input
     */
    public final Operand<TString> prefix;

    /**
     * The tensorNames input
     */
    public final Operand<TString> tensorNames;

    /**
     * The shapeAndSlices input
     */
    public final Operand<TString> shapeAndSlices;

    /**
     * The inputShapes attribute
     */
    public final Shape[] inputShapes;

    /**
     * The inputLayouts attribute
     */
    public final String[] inputLayouts;

    /**
     * The dtypes attribute
     */
    public final DataType[] dtypes;

    public Inputs(GraphOperation op) {
      super(new DTensorRestore(op), op, Arrays.asList("input_shapes", "input_layouts", "dtypes"));
      int inputIndex = 0;
      prefix = (Operand<TString>) op.input(inputIndex++);
      tensorNames = (Operand<TString>) op.input(inputIndex++);
      shapeAndSlices = (Operand<TString>) op.input(inputIndex++);
      inputShapes = op.attributes().getAttrShapeList("input_shapes");
      inputLayouts = op.attributes().getAttrStringList("input_layouts");
      dtypes = op.attributes().getAttrTypeList("dtypes");
    }
  }
}
