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
 * The ShuffleAndRepeatDatasetV2 operation
 */
@OpMetadata(
    opType = ShuffleAndRepeatDataset.OP_NAME,
    inputsClass = ShuffleAndRepeatDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ShuffleAndRepeatDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ShuffleAndRepeatDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ShuffleAndRepeatDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ShuffleAndRepeatDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param bufferSize The bufferSize value
   * @param seed The seed value
   * @param seed2 The seed2 value
   * @param count The count value
   * @param seedGenerator The seedGenerator value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ShuffleAndRepeatDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ShuffleAndRepeatDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> bufferSize, Operand<TInt64> seed, Operand<TInt64> seed2,
      Operand<TInt64> count, Operand<? extends TType> seedGenerator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ShuffleAndRepeatDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder.addInput(count.asOutput());
    opBuilder.addInput(seedGenerator.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.reshuffleEachIteration != null) {
          opBuilder.setAttr("reshuffle_each_iteration", opts.reshuffleEachIteration);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new ShuffleAndRepeatDataset(opBuilder.build());
  }

  /**
   * Sets the reshuffleEachIteration option.
   *
   * @param reshuffleEachIteration the reshuffleEachIteration option
   * @return this Options instance.
   */
  public static Options reshuffleEachIteration(Boolean reshuffleEachIteration) {
    return new Options().reshuffleEachIteration(reshuffleEachIteration);
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
   * Optional attributes for {@link org.tensorflow.op.data.ShuffleAndRepeatDataset}
   */
  public static class Options {
    private Boolean reshuffleEachIteration;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the reshuffleEachIteration option.
     *
     * @param reshuffleEachIteration the reshuffleEachIteration option
     * @return this Options instance.
     */
    public Options reshuffleEachIteration(Boolean reshuffleEachIteration) {
      this.reshuffleEachIteration = reshuffleEachIteration;
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
      outputsClass = ShuffleAndRepeatDataset.class
  )
  public static class Inputs extends RawOpInputs<ShuffleAndRepeatDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The bufferSize input
     */
    public final Operand<TInt64> bufferSize;

    /**
     * The seed input
     */
    public final Operand<TInt64> seed;

    /**
     * The seed2 input
     */
    public final Operand<TInt64> seed2;

    /**
     * The count input
     */
    public final Operand<TInt64> count;

    /**
     * The seedGenerator input
     */
    public final Operand<? extends TType> seedGenerator;

    /**
     * The reshuffleEachIteration attribute
     */
    public final boolean reshuffleEachIteration;

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
      super(new ShuffleAndRepeatDataset(op), op, Arrays.asList("reshuffle_each_iteration", "output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      bufferSize = (Operand<TInt64>) op.input(inputIndex++);
      seed = (Operand<TInt64>) op.input(inputIndex++);
      seed2 = (Operand<TInt64>) op.input(inputIndex++);
      count = (Operand<TInt64>) op.input(inputIndex++);
      seedGenerator = (Operand<? extends TType>) op.input(inputIndex++);
      reshuffleEachIteration = op.attributes().getAttrBool("reshuffle_each_iteration");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
