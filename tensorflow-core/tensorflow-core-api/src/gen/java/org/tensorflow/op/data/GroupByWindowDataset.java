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
 * Creates a dataset that computes a windowed group-by on {@code input_dataset}.
 * // TODO(mrry): Support non-int64 keys.
 */
@OpMetadata(
    opType = GroupByWindowDataset.OP_NAME,
    inputsClass = GroupByWindowDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class GroupByWindowDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GroupByWindowDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public GroupByWindowDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GroupByWindowDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param keyFuncOtherArguments The keyFuncOtherArguments value
   * @param reduceFuncOtherArguments The reduceFuncOtherArguments value
   * @param windowSizeFuncOtherArguments The windowSizeFuncOtherArguments value
   * @param keyFunc A function mapping an element of {@code input_dataset}, concatenated
   * with {@code key_func_other_arguments} to a scalar value of type DT_INT64.
   * @param reduceFunc The value of the reduceFunc attribute
   * @param windowSizeFunc The value of the windowSizeFunc attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of GroupByWindowDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static GroupByWindowDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> keyFuncOtherArguments, Iterable<Operand<?>> reduceFuncOtherArguments,
      Iterable<Operand<?>> windowSizeFuncOtherArguments, ConcreteFunction keyFunc,
      ConcreteFunction reduceFunc, ConcreteFunction windowSizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GroupByWindowDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(keyFuncOtherArguments));
    opBuilder.addInputList(Operands.asOutputs(reduceFuncOtherArguments));
    opBuilder.addInputList(Operands.asOutputs(windowSizeFuncOtherArguments));
    opBuilder.setAttr("key_func", keyFunc);
    opBuilder.setAttr("reduce_func", reduceFunc);
    opBuilder.setAttr("window_size_func", windowSizeFunc);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new GroupByWindowDataset(opBuilder.build());
  }

  /**
   * Sets the metadata option.
   *
   * @param metadata the metadata option
   * @return this Options instance.
   */
  public static Options metadata(String metadata) {
    return new Options().metadata(metadata);
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

  /**
   * Optional attributes for {@link org.tensorflow.op.data.GroupByWindowDataset}
   */
  public static class Options {
    private String metadata;

    private Options() {
    }

    /**
     * Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public Options metadata(String metadata) {
      this.metadata = metadata;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = GroupByWindowDataset.class
  )
  public static class Inputs extends RawOpInputs<GroupByWindowDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The keyFuncOtherArguments input
     */
    public final Iterable<Operand<?>> keyFuncOtherArguments;

    /**
     * The reduceFuncOtherArguments input
     */
    public final Iterable<Operand<?>> reduceFuncOtherArguments;

    /**
     * The windowSizeFuncOtherArguments input
     */
    public final Iterable<Operand<?>> windowSizeFuncOtherArguments;

    /**
     * The TkeyFuncOtherArguments attribute
     */
    public final DataType[] TkeyFuncOtherArguments;

    /**
     * The TreduceFuncOtherArguments attribute
     */
    public final DataType[] TreduceFuncOtherArguments;

    /**
     * The TwindowSizeFuncOtherArguments attribute
     */
    public final DataType[] TwindowSizeFuncOtherArguments;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new GroupByWindowDataset(op), op, Arrays.asList("Tkey_func_other_arguments", "Treduce_func_other_arguments", "Twindow_size_func_other_arguments", "output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int keyFuncOtherArgumentsLength = op.inputListLength("key_func_other_arguments");
      keyFuncOtherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, keyFuncOtherArgumentsLength));
      inputIndex += keyFuncOtherArgumentsLength;
      int reduceFuncOtherArgumentsLength = op.inputListLength("reduce_func_other_arguments");
      reduceFuncOtherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, reduceFuncOtherArgumentsLength));
      inputIndex += reduceFuncOtherArgumentsLength;
      int windowSizeFuncOtherArgumentsLength = op.inputListLength("window_size_func_other_arguments");
      windowSizeFuncOtherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, windowSizeFuncOtherArgumentsLength));
      inputIndex += windowSizeFuncOtherArgumentsLength;
      TkeyFuncOtherArguments = op.attributes().getAttrTypeList("Tkey_func_other_arguments");
      TreduceFuncOtherArguments = op.attributes().getAttrTypeList("Treduce_func_other_arguments");
      TwindowSizeFuncOtherArguments = op.attributes().getAttrTypeList("Twindow_size_func_other_arguments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
