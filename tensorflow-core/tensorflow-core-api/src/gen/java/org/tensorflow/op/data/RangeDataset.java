/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
 * Creates a dataset with a range of values. Corresponds to python's xrange.
 */
@OpMetadata(
    opType = RangeDataset.OP_NAME,
    inputsClass = RangeDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class RangeDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RangeDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public RangeDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RangeDataset operation.
   *
   * @param scope current scope
   * @param start corresponds to start in python's xrange().
   * @param stop corresponds to stop in python's xrange().
   * @param step corresponds to step in python's xrange().
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of RangeDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static RangeDataset create(Scope scope, Operand<TInt64> start, Operand<TInt64> stop,
      Operand<TInt64> step, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RangeDataset");
    opBuilder.addInput(start.asOutput());
    opBuilder.addInput(stop.asOutput());
    opBuilder.addInput(step.asOutput());
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
        if (opts.replicateOnSplit != null) {
          opBuilder.setAttr("replicate_on_split", opts.replicateOnSplit);
        }
      }
    }
    return new RangeDataset(opBuilder.build());
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
   * Sets the replicateOnSplit option.
   *
   * @param replicateOnSplit the replicateOnSplit option
   * @return this Options instance.
   */
  public static Options replicateOnSplit(Boolean replicateOnSplit) {
    return new Options().replicateOnSplit(replicateOnSplit);
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
   * Optional attributes for {@link org.tensorflow.op.data.RangeDataset}
   */
  public static class Options {
    private String metadata;

    private Boolean replicateOnSplit;

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

    /**
     * Sets the replicateOnSplit option.
     *
     * @param replicateOnSplit the replicateOnSplit option
     * @return this Options instance.
     */
    public Options replicateOnSplit(Boolean replicateOnSplit) {
      this.replicateOnSplit = replicateOnSplit;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RangeDataset.class
  )
  public static class Inputs extends RawOpInputs<RangeDataset> {
    /**
     * corresponds to start in python's xrange().
     */
    public final Operand<TInt64> start;

    /**
     * corresponds to stop in python's xrange().
     */
    public final Operand<TInt64> stop;

    /**
     * corresponds to step in python's xrange().
     */
    public final Operand<TInt64> step;

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

    /**
     * The replicateOnSplit attribute
     */
    public final boolean replicateOnSplit;

    public Inputs(GraphOperation op) {
      super(new RangeDataset(op), op, Arrays.asList("output_types", "output_shapes", "metadata", "replicate_on_split"));
      int inputIndex = 0;
      start = (Operand<TInt64>) op.input(inputIndex++);
      stop = (Operand<TInt64>) op.input(inputIndex++);
      step = (Operand<TInt64>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
      replicateOnSplit = op.attributes().getAttrBool("replicate_on_split");
    }
  }
}
