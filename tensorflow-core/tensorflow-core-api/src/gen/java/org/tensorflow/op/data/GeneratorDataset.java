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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that invokes a function to generate elements.
 */
@Operator(
    group = "data"
)
public final class GeneratorDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GeneratorDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private GeneratorDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GeneratorDataset operation.
   *
   * @param scope current scope
   * @param initFuncOtherArgs the initFuncOtherArgs value
   * @param nextFuncOtherArgs the nextFuncOtherArgs value
   * @param finalizeFuncOtherArgs the finalizeFuncOtherArgs value
   * @param initFunc the value of the initFunc property
   * @param nextFunc the value of the nextFunc property
   * @param finalizeFunc the value of the finalizeFunc property
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of GeneratorDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static GeneratorDataset create(Scope scope, Iterable<Operand<?>> initFuncOtherArgs,
      Iterable<Operand<?>> nextFuncOtherArgs, Iterable<Operand<?>> finalizeFuncOtherArgs,
      ConcreteFunction initFunc, ConcreteFunction nextFunc, ConcreteFunction finalizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GeneratorDataset");
    opBuilder.addInputList(Operands.asOutputs(initFuncOtherArgs));
    opBuilder.addInputList(Operands.asOutputs(nextFuncOtherArgs));
    opBuilder.addInputList(Operands.asOutputs(finalizeFuncOtherArgs));
    opBuilder.setAttr("init_func", initFunc);
    opBuilder.setAttr("next_func", nextFunc);
    opBuilder.setAttr("finalize_func", finalizeFunc);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new GeneratorDataset(opBuilder.build());
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
