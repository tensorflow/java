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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The LoadDataset operation
 */
@OpMetadata(
    opType = LoadDataset.OP_NAME,
    inputsClass = LoadDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class LoadDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LoadDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public LoadDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LoadDataset operation.
   *
   * @param scope current scope
   * @param path The path value
   * @param readerFuncOtherArgs The readerFuncOtherArgs value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param readerFunc The value of the readerFunc attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static LoadDataset create(Scope scope, Operand<TString> path,
      Iterable<Operand<?>> readerFuncOtherArgs, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, ConcreteFunction readerFunc, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LoadDataset");
    opBuilder.addInput(path.asOutput());
    opBuilder.addInputList(Operands.asOutputs(readerFuncOtherArgs));
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    opBuilder.setAttr("reader_func", readerFunc);
    if (options != null) {
      for (Options opts : options) {
        if (opts.compression != null) {
          opBuilder.setAttr("compression", opts.compression);
        }
      }
    }
    return new LoadDataset(opBuilder.build());
  }

  /**
   * Sets the compression option.
   *
   * @param compression the compression option
   * @return this Options instance.
   */
  public static Options compression(String compression) {
    return new Options().compression(compression);
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
   * Optional attributes for {@link org.tensorflow.op.data.LoadDataset}
   */
  public static class Options {
    private String compression;

    private Options() {
    }

    /**
     * Sets the compression option.
     *
     * @param compression the compression option
     * @return this Options instance.
     */
    public Options compression(String compression) {
      this.compression = compression;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = LoadDataset.class
  )
  public static class Inputs extends RawOpInputs<LoadDataset> {
    /**
     * The path input
     */
    public final Operand<TString> path;

    /**
     * The readerFuncOtherArgs input
     */
    public final Iterable<Operand<?>> readerFuncOtherArgs;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The compression attribute
     */
    public final String compression;

    /**
     * The TreaderFuncArgs attribute
     */
    public final DataType[] TreaderFuncArgs;

    public Inputs(GraphOperation op) {
      super(new LoadDataset(op), op, Arrays.asList("output_types", "output_shapes", "compression", "Treader_func_args"));
      int inputIndex = 0;
      path = (Operand<TString>) op.input(inputIndex++);
      int readerFuncOtherArgsLength = op.inputListLength("reader_func_other_args");
      readerFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, readerFuncOtherArgsLength));
      inputIndex += readerFuncOtherArgsLength;
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      compression = op.attributes().getAttrString("compression");
      TreaderFuncArgs = op.attributes().getAttrTypeList("Treader_func_args");
    }
  }
}
