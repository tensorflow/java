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
import org.tensorflow.ConcreteFunction;
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
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
 * Unlike MapDataset, the {@code f} in FlatMapDataset is expected to return a
 * Dataset variant, and FlatMapDataset will flatten successive results
 * into a single Dataset.
 */
@OpMetadata(
    opType = FlatMapDataset.OP_NAME,
    inputsClass = FlatMapDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class FlatMapDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FlatMapDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public FlatMapDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FlatMapDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   * {@code other_arguments}, to a Dataset variant that contains elements matching
   * {@code output_types} and {@code output_shapes}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of FlatMapDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static FlatMapDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FlatMapDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
    opBuilder.setAttr("f", f);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new FlatMapDataset(opBuilder.build());
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
      outputsClass = FlatMapDataset.class
  )
  public static class Inputs extends RawOpInputs<FlatMapDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The otherArguments input
     */
    public final Iterable<Operand<?>> otherArguments;

    /**
     * The Targuments attribute
     */
    public final DataType[] Targuments;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new FlatMapDataset(op), op, Arrays.asList("Targuments", "output_types", "output_shapes"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int otherArgumentsLength = op.inputListLength("other_arguments");
      otherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, otherArgumentsLength));
      inputIndex += otherArgumentsLength;
      Targuments = op.attributes().getAttrTypeList("Targuments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
