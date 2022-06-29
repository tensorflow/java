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
 * Creates a dataset that computes a group-by on {@code input_dataset}.
 * Creates a dataset that computes a group-by on {@code input_dataset}.
 */
@OpMetadata(
    opType = GroupByReducerDataset.OP_NAME,
    inputsClass = GroupByReducerDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class GroupByReducerDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GroupByReducerDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public GroupByReducerDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GroupByReducerDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param keyFuncOtherArguments A list of tensors, typically values that were captured when
   * building a closure for {@code key_func}.
   * @param initFuncOtherArguments A list of tensors, typically values that were captured when
   * building a closure for {@code init_func}.
   * @param reduceFuncOtherArguments A list of tensors, typically values that were captured when
   * building a closure for {@code reduce_func}.
   * @param finalizeFuncOtherArguments A list of tensors, typically values that were captured when
   * building a closure for {@code finalize_func}.
   * @param keyFunc A function mapping an element of {@code input_dataset}, concatenated
   * with {@code key_func_other_arguments} to a scalar value of type DT_INT64.
   * @param initFunc A function mapping a key of type DT_INT64, concatenated with
   * {@code init_func_other_arguments} to the initial reducer state.
   * @param reduceFunc A function mapping the current reducer state and an element of {@code input_dataset},
   * concatenated with {@code reduce_func_other_arguments} to a new reducer state.
   * @param finalizeFunc A function mapping the final reducer state to an output element.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of GroupByReducerDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static GroupByReducerDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> keyFuncOtherArguments, Iterable<Operand<?>> initFuncOtherArguments,
      Iterable<Operand<?>> reduceFuncOtherArguments,
      Iterable<Operand<?>> finalizeFuncOtherArguments, ConcreteFunction keyFunc,
      ConcreteFunction initFunc, ConcreteFunction reduceFunc, ConcreteFunction finalizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GroupByReducerDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(keyFuncOtherArguments));
    opBuilder.addInputList(Operands.asOutputs(initFuncOtherArguments));
    opBuilder.addInputList(Operands.asOutputs(reduceFuncOtherArguments));
    opBuilder.addInputList(Operands.asOutputs(finalizeFuncOtherArguments));
    opBuilder.setAttr("key_func", keyFunc);
    opBuilder.setAttr("init_func", initFunc);
    opBuilder.setAttr("reduce_func", reduceFunc);
    opBuilder.setAttr("finalize_func", finalizeFunc);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new GroupByReducerDataset(opBuilder.build());
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
      outputsClass = GroupByReducerDataset.class
  )
  public static class Inputs extends RawOpInputs<GroupByReducerDataset> {
    /**
     * A variant tensor representing the input dataset.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A list of tensors, typically values that were captured when
     * building a closure for {@code key_func}.
     */
    public final Iterable<Operand<?>> keyFuncOtherArguments;

    /**
     * A list of tensors, typically values that were captured when
     * building a closure for {@code init_func}.
     */
    public final Iterable<Operand<?>> initFuncOtherArguments;

    /**
     * A list of tensors, typically values that were captured when
     * building a closure for {@code reduce_func}.
     */
    public final Iterable<Operand<?>> reduceFuncOtherArguments;

    /**
     * A list of tensors, typically values that were captured when
     * building a closure for {@code finalize_func}.
     */
    public final Iterable<Operand<?>> finalizeFuncOtherArguments;

    /**
     * The TkeyFuncOtherArguments attribute
     */
    public final DataType[] TkeyFuncOtherArguments;

    /**
     * The TinitFuncOtherArguments attribute
     */
    public final DataType[] TinitFuncOtherArguments;

    /**
     * The TreduceFuncOtherArguments attribute
     */
    public final DataType[] TreduceFuncOtherArguments;

    /**
     * The TfinalizeFuncOtherArguments attribute
     */
    public final DataType[] TfinalizeFuncOtherArguments;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new GroupByReducerDataset(op), op, Arrays.asList("Tkey_func_other_arguments", "Tinit_func_other_arguments", "Treduce_func_other_arguments", "Tfinalize_func_other_arguments", "output_types", "output_shapes"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int keyFuncOtherArgumentsLength = op.inputListLength("key_func_other_arguments");
      keyFuncOtherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, keyFuncOtherArgumentsLength));
      inputIndex += keyFuncOtherArgumentsLength;
      int initFuncOtherArgumentsLength = op.inputListLength("init_func_other_arguments");
      initFuncOtherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, initFuncOtherArgumentsLength));
      inputIndex += initFuncOtherArgumentsLength;
      int reduceFuncOtherArgumentsLength = op.inputListLength("reduce_func_other_arguments");
      reduceFuncOtherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, reduceFuncOtherArgumentsLength));
      inputIndex += reduceFuncOtherArgumentsLength;
      int finalizeFuncOtherArgumentsLength = op.inputListLength("finalize_func_other_arguments");
      finalizeFuncOtherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, finalizeFuncOtherArgumentsLength));
      inputIndex += finalizeFuncOtherArgumentsLength;
      TkeyFuncOtherArguments = op.attributes().getAttrTypeList("Tkey_func_other_arguments");
      TinitFuncOtherArguments = op.attributes().getAttrTypeList("Tinit_func_other_arguments");
      TreduceFuncOtherArguments = op.attributes().getAttrTypeList("Treduce_func_other_arguments");
      TfinalizeFuncOtherArguments = op.attributes().getAttrTypeList("Tfinalize_func_other_arguments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
