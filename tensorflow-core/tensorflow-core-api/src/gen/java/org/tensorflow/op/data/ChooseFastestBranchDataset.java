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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The ChooseFastestBranchDataset operation
 */
@OpMetadata(
    opType = ChooseFastestBranchDataset.OP_NAME,
    inputsClass = ChooseFastestBranchDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ChooseFastestBranchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ChooseFastestBranchDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ChooseFastestBranchDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ChooseFastestBranchDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param ratioNumerator The ratioNumerator value
   * @param ratioDenominator The ratioDenominator value
   * @param otherArguments The otherArguments value
   * @param numElementsPerBranch The value of the numElementsPerBranch attribute
   * @param branches The value of the branches attribute
   * @param otherArgumentsLengths The value of the otherArgumentsLengths attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of ChooseFastestBranchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ChooseFastestBranchDataset create(Scope scope,
      Operand<? extends TType> inputDataset, Operand<TInt64> ratioNumerator,
      Operand<TInt64> ratioDenominator, Iterable<Operand<?>> otherArguments,
      Long numElementsPerBranch, List<ConcreteFunction> branches, List<Long> otherArgumentsLengths,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ChooseFastestBranchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(ratioNumerator.asOutput());
    opBuilder.addInput(ratioDenominator.asOutput());
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
    opBuilder.setAttr("num_elements_per_branch", numElementsPerBranch);
    ConcreteFunction[] branchesArray = new ConcreteFunction[branches.size()];
    for (int i = 0 ; i < branchesArray.length ; i++) {
      branchesArray[i] = branches.get(i);
    }
    opBuilder.setAttr("branches", branchesArray);
    long[] otherArgumentsLengthsArray = new long[otherArgumentsLengths.size()];
    for (int i = 0 ; i < otherArgumentsLengthsArray.length ; i++) {
      otherArgumentsLengthsArray[i] = otherArgumentsLengths.get(i);
    }
    opBuilder.setAttr("other_arguments_lengths", otherArgumentsLengthsArray);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new ChooseFastestBranchDataset(opBuilder.build());
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
      outputsClass = ChooseFastestBranchDataset.class
  )
  public static class Inputs extends RawOpInputs<ChooseFastestBranchDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The ratioNumerator input
     */
    public final Operand<TInt64> ratioNumerator;

    /**
     * The ratioDenominator input
     */
    public final Operand<TInt64> ratioDenominator;

    /**
     * The otherArguments input
     */
    public final Iterable<Operand<?>> otherArguments;

    /**
     * The Targuments attribute
     */
    public final DataType[] Targuments;

    /**
     * The numElementsPerBranch attribute
     */
    public final long numElementsPerBranch;

    /**
     * The otherArgumentsLengths attribute
     */
    public final long[] otherArgumentsLengths;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new ChooseFastestBranchDataset(op), op, Arrays.asList("Targuments", "num_elements_per_branch", "other_arguments_lengths", "output_types", "output_shapes"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      ratioNumerator = (Operand<TInt64>) op.input(inputIndex++);
      ratioDenominator = (Operand<TInt64>) op.input(inputIndex++);
      int otherArgumentsLength = op.inputListLength("other_arguments");
      otherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, otherArgumentsLength));
      inputIndex += otherArgumentsLength;
      Targuments = op.attributes().getAttrTypeList("Targuments");
      numElementsPerBranch = op.attributes().getAttrInt("num_elements_per_branch");
      otherArgumentsLengths = op.attributes().getAttrIntList("other_arguments_lengths");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
