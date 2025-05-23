/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The IndexFlatMapDataset operation
 */
@OpMetadata(
    opType = IndexFlatMapDataset.OP_NAME,
    inputsClass = IndexFlatMapDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class IndexFlatMapDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IndexFlatMapDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public IndexFlatMapDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IndexFlatMapDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param mapFuncOtherArgs The mapFuncOtherArgs value
   * @param indexMapFuncOtherArgs The indexMapFuncOtherArgs value
   * @param outputCardinality The outputCardinality value
   * @param mapFunc The value of the mapFunc attribute
   * @param indexMapFunc The value of the indexMapFunc attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of IndexFlatMapDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static IndexFlatMapDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> mapFuncOtherArgs, Iterable<Operand<?>> indexMapFuncOtherArgs,
      Operand<TInt64> outputCardinality, ConcreteFunction mapFunc, ConcreteFunction indexMapFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IndexFlatMapDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(mapFuncOtherArgs));
    opBuilder.addInputList(Operands.asOutputs(indexMapFuncOtherArgs));
    opBuilder.addInput(outputCardinality.asOutput());
    opBuilder.setAttr("map_func", mapFunc);
    opBuilder.setAttr("index_map_func", indexMapFunc);
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
    return new IndexFlatMapDataset(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.data.IndexFlatMapDataset}
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
      outputsClass = IndexFlatMapDataset.class
  )
  public static class Inputs extends RawOpInputs<IndexFlatMapDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The mapFuncOtherArgs input
     */
    public final Iterable<Operand<?>> mapFuncOtherArgs;

    /**
     * The indexMapFuncOtherArgs input
     */
    public final Iterable<Operand<?>> indexMapFuncOtherArgs;

    /**
     * The outputCardinality input
     */
    public final Operand<TInt64> outputCardinality;

    /**
     * The TmapFuncArgs attribute
     */
    public final DataType[] TmapFuncArgs;

    /**
     * The TindexMapFuncArgs attribute
     */
    public final DataType[] TindexMapFuncArgs;

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
      super(new IndexFlatMapDataset(op), op, Arrays.asList("Tmap_func_args", "Tindex_map_func_args", "output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int mapFuncOtherArgsLength = op.inputListLength("map_func_other_args");
      mapFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, mapFuncOtherArgsLength));
      inputIndex += mapFuncOtherArgsLength;
      int indexMapFuncOtherArgsLength = op.inputListLength("index_map_func_other_args");
      indexMapFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, indexMapFuncOtherArgsLength));
      inputIndex += indexMapFuncOtherArgsLength;
      outputCardinality = (Operand<TInt64>) op.input(inputIndex++);
      TmapFuncArgs = op.attributes().getAttrTypeList("Tmap_func_args");
      TindexMapFuncArgs = op.attributes().getAttrTypeList("Tindex_map_func_args");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
