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
 * The SaveDatasetV2 operation
 */
@OpMetadata(
    opType = SaveDataset.OP_NAME,
    inputsClass = SaveDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class SaveDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SaveDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public SaveDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SaveDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param path The path value
   * @param shardFuncOtherArgs The shardFuncOtherArgs value
   * @param shardFunc The value of the shardFunc attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of SaveDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static SaveDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TString> path, Iterable<Operand<?>> shardFuncOtherArgs, ConcreteFunction shardFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SaveDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(path.asOutput());
    opBuilder.addInputList(Operands.asOutputs(shardFuncOtherArgs));
    opBuilder.setAttr("shard_func", shardFunc);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.compression != null) {
          opBuilder.setAttr("compression", opts.compression);
        }
        if (opts.useShardFunc != null) {
          opBuilder.setAttr("use_shard_func", opts.useShardFunc);
        }
      }
    }
    return new SaveDataset(opBuilder.build());
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
   * Sets the useShardFunc option.
   *
   * @param useShardFunc the useShardFunc option
   * @return this Options instance.
   */
  public static Options useShardFunc(Boolean useShardFunc) {
    return new Options().useShardFunc(useShardFunc);
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
   * Optional attributes for {@link org.tensorflow.op.data.SaveDataset}
   */
  public static class Options {
    private String compression;

    private Boolean useShardFunc;

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

    /**
     * Sets the useShardFunc option.
     *
     * @param useShardFunc the useShardFunc option
     * @return this Options instance.
     */
    public Options useShardFunc(Boolean useShardFunc) {
      this.useShardFunc = useShardFunc;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SaveDataset.class
  )
  public static class Inputs extends RawOpInputs<SaveDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The path input
     */
    public final Operand<TString> path;

    /**
     * The shardFuncOtherArgs input
     */
    public final Iterable<Operand<?>> shardFuncOtherArgs;

    /**
     * The compression attribute
     */
    public final String compression;

    /**
     * The useShardFunc attribute
     */
    public final boolean useShardFunc;

    /**
     * The TshardFuncArgs attribute
     */
    public final DataType[] TshardFuncArgs;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new SaveDataset(op), op, Arrays.asList("compression", "use_shard_func", "Tshard_func_args", "output_types", "output_shapes"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      path = (Operand<TString>) op.input(inputIndex++);
      int shardFuncOtherArgsLength = op.inputListLength("shard_func_other_args");
      shardFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, shardFuncOtherArgsLength));
      inputIndex += shardFuncOtherArgsLength;
      compression = op.attributes().getAttrString("compression");
      useShardFunc = op.attributes().getAttrBool("use_shard_func");
      TshardFuncArgs = op.attributes().getAttrTypeList("Tshard_func_args");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
