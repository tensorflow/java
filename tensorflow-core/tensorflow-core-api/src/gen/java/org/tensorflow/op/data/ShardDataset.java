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
 * Creates a {@code Dataset} that includes only 1/{@code num_shards} of this dataset.
 */
@OpMetadata(
    opType = ShardDataset.OP_NAME,
    inputsClass = ShardDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ShardDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ShardDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ShardDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ShardDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param numShards An integer representing the number of shards operating in parallel.
   * @param index An integer representing the current worker index.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ShardDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ShardDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> numShards, Operand<TInt64> index, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ShardDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(numShards.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.requireNonEmpty != null) {
          opBuilder.setAttr("require_non_empty", opts.requireNonEmpty);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new ShardDataset(opBuilder.build());
  }

  /**
   * Sets the requireNonEmpty option.
   *
   * @param requireNonEmpty the requireNonEmpty option
   * @return this Options instance.
   */
  public static Options requireNonEmpty(Boolean requireNonEmpty) {
    return new Options().requireNonEmpty(requireNonEmpty);
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
   * Optional attributes for {@link org.tensorflow.op.data.ShardDataset}
   */
  public static class Options {
    private Boolean requireNonEmpty;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the requireNonEmpty option.
     *
     * @param requireNonEmpty the requireNonEmpty option
     * @return this Options instance.
     */
    public Options requireNonEmpty(Boolean requireNonEmpty) {
      this.requireNonEmpty = requireNonEmpty;
      return this;
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
      outputsClass = ShardDataset.class
  )
  public static class Inputs extends RawOpInputs<ShardDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * An integer representing the number of shards operating in parallel.
     */
    public final Operand<TInt64> numShards;

    /**
     * An integer representing the current worker index.
     */
    public final Operand<TInt64> index;

    /**
     * The requireNonEmpty attribute
     */
    public final boolean requireNonEmpty;

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
      super(new ShardDataset(op), op, Arrays.asList("require_non_empty", "output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      numShards = (Operand<TInt64>) op.input(inputIndex++);
      index = (Operand<TInt64>) op.input(inputIndex++);
      requireNonEmpty = op.attributes().getAttrBool("require_non_empty");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
