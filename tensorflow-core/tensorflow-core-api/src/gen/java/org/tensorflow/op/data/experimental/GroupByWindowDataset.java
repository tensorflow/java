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

package org.tensorflow.op.data.experimental;

import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that computes a windowed group-by on {@code input_dataset}.
 * // TODO(mrry): Support non-int64 keys.
 */
public final class GroupByWindowDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalGroupByWindowDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private GroupByWindowDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalGroupByWindowDataset operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @param keyFuncOtherArguments the keyFuncOtherArguments value
   * @param reduceFuncOtherArguments the reduceFuncOtherArguments value
   * @param windowSizeFuncOtherArguments the windowSizeFuncOtherArguments value
   * @param keyFunc A function mapping an element of {@code input_dataset}, concatenated
   * with {@code key_func_other_arguments} to a scalar value of type DT_INT64.
   * @param reduceFunc the value of the reduceFunc property
   * @param windowSizeFunc the value of the windowSizeFunc property
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of GroupByWindowDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static GroupByWindowDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> keyFuncOtherArguments, Iterable<Operand<?>> reduceFuncOtherArguments,
      Iterable<Operand<?>> windowSizeFuncOtherArguments, ConcreteFunction keyFunc,
      ConcreteFunction reduceFunc, ConcreteFunction windowSizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("GroupByWindowDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(keyFuncOtherArguments));
    opBuilder.addInputList(Operands.asOutputs(reduceFuncOtherArguments));
    opBuilder.addInputList(Operands.asOutputs(windowSizeFuncOtherArguments));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("key_func", keyFunc);
    opBuilder.setAttr("reduce_func", reduceFunc);
    opBuilder.setAttr("window_size_func", windowSizeFunc);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new GroupByWindowDataset(opBuilder.build());
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
}
