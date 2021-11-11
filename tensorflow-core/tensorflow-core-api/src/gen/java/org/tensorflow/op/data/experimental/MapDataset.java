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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
 */
@OpMetadata(
    opType = MapDataset.OP_NAME,
    inputsClass = MapDataset.Inputs.class
)
public final class MapDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalMapDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public MapDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalMapDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param f The value of the f attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of MapDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static MapDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MapDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
    opBuilder.setAttr("f", f);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useInterOpParallelism != null) {
          opBuilder.setAttr("use_inter_op_parallelism", opts.useInterOpParallelism);
        }
        if (opts.preserveCardinality != null) {
          opBuilder.setAttr("preserve_cardinality", opts.preserveCardinality);
        }
      }
    }
    return new MapDataset(opBuilder.build());
  }

  /**
   * Sets the useInterOpParallelism option.
   *
   * @param useInterOpParallelism the useInterOpParallelism option
   * @return this Options instance.
   */
  public static Options useInterOpParallelism(Boolean useInterOpParallelism) {
    return new Options().useInterOpParallelism(useInterOpParallelism);
  }

  /**
   * Sets the preserveCardinality option.
   *
   * @param preserveCardinality the preserveCardinality option
   * @return this Options instance.
   */
  public static Options preserveCardinality(Boolean preserveCardinality) {
    return new Options().preserveCardinality(preserveCardinality);
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
   * Optional attributes for {@link org.tensorflow.op.data.experimental.MapDataset}
   */
  public static class Options {
    private Boolean useInterOpParallelism;

    private Boolean preserveCardinality;

    private Options() {
    }

    /**
     * Sets the useInterOpParallelism option.
     *
     * @param useInterOpParallelism the useInterOpParallelism option
     * @return this Options instance.
     */
    public Options useInterOpParallelism(Boolean useInterOpParallelism) {
      this.useInterOpParallelism = useInterOpParallelism;
      return this;
    }

    /**
     * Sets the preserveCardinality option.
     *
     * @param preserveCardinality the preserveCardinality option
     * @return this Options instance.
     */
    public Options preserveCardinality(Boolean preserveCardinality) {
      this.preserveCardinality = preserveCardinality;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = MapDataset.class
  )
  public static class Inputs extends RawOpInputs<MapDataset> {
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

    /**
     * The useInterOpParallelism attribute
     */
    public final boolean useInterOpParallelism;

    /**
     * The preserveCardinality attribute
     */
    public final boolean preserveCardinality;

    public Inputs(GraphOperation op) {
      super(new MapDataset(op), op, Arrays.asList("Targuments", "output_types", "output_shapes", "use_inter_op_parallelism", "preserve_cardinality"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int otherArgumentsLength = op.inputListLength("other_arguments");
      otherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, otherArgumentsLength));
      inputIndex += otherArgumentsLength;
      Targuments = op.attributes().getAttrTypeList("Targuments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      useInterOpParallelism = op.attributes().getAttrBool("use_inter_op_parallelism");
      preserveCardinality = op.attributes().getAttrBool("preserve_cardinality");
    }
  }
}
