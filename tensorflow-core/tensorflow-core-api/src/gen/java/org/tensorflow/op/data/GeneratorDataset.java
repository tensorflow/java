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
 * Creates a dataset that invokes a function to generate elements.
 */
@OpMetadata(
    opType = GeneratorDataset.OP_NAME,
    inputsClass = GeneratorDataset.Inputs.class
)
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
  public GeneratorDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GeneratorDataset operation.
   *
   * @param scope current scope
   * @param initFuncOtherArgs The initFuncOtherArgs value
   * @param nextFuncOtherArgs The nextFuncOtherArgs value
   * @param finalizeFuncOtherArgs The finalizeFuncOtherArgs value
   * @param initFunc The value of the initFunc attribute
   * @param nextFunc The value of the nextFunc attribute
   * @param finalizeFunc The value of the finalizeFunc attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of GeneratorDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static GeneratorDataset create(Scope scope, Iterable<Operand<?>> initFuncOtherArgs,
      Iterable<Operand<?>> nextFuncOtherArgs, Iterable<Operand<?>> finalizeFuncOtherArgs,
      ConcreteFunction initFunc, ConcreteFunction nextFunc, ConcreteFunction finalizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
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
    if (options != null) {
      for (Options opts : options) {
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new GeneratorDataset(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.data.GeneratorDataset}
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
      outputsClass = GeneratorDataset.class
  )
  public static class Inputs extends RawOpInputs<GeneratorDataset> {
    /**
     * The initFuncOtherArgs input
     */
    public final Iterable<Operand<?>> initFuncOtherArgs;

    /**
     * The nextFuncOtherArgs input
     */
    public final Iterable<Operand<?>> nextFuncOtherArgs;

    /**
     * The finalizeFuncOtherArgs input
     */
    public final Iterable<Operand<?>> finalizeFuncOtherArgs;

    /**
     * The TinitFuncArgs attribute
     */
    public final DataType[] TinitFuncArgs;

    /**
     * The TnextFuncArgs attribute
     */
    public final DataType[] TnextFuncArgs;

    /**
     * The TfinalizeFuncArgs attribute
     */
    public final DataType[] TfinalizeFuncArgs;

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
      super(new GeneratorDataset(op), op, Arrays.asList("Tinit_func_args", "Tnext_func_args", "Tfinalize_func_args", "output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      int initFuncOtherArgsLength = op.inputListLength("init_func_other_args");
      initFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, initFuncOtherArgsLength));
      inputIndex += initFuncOtherArgsLength;
      int nextFuncOtherArgsLength = op.inputListLength("next_func_other_args");
      nextFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, nextFuncOtherArgsLength));
      inputIndex += nextFuncOtherArgsLength;
      int finalizeFuncOtherArgsLength = op.inputListLength("finalize_func_other_args");
      finalizeFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, finalizeFuncOtherArgsLength));
      inputIndex += finalizeFuncOtherArgsLength;
      TinitFuncArgs = op.attributes().getAttrTypeList("Tinit_func_args");
      TnextFuncArgs = op.attributes().getAttrTypeList("Tnext_func_args");
      TfinalizeFuncArgs = op.attributes().getAttrTypeList("Tfinalize_func_args");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
