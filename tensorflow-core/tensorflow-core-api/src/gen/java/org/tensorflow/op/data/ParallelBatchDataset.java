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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The ParallelBatchDataset operation
 */
@OpMetadata(
    opType = ParallelBatchDataset.OP_NAME,
    inputsClass = ParallelBatchDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ParallelBatchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParallelBatchDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ParallelBatchDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ParallelBatchDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param batchSize The batchSize value
   * @param numParallelCalls The numParallelCalls value
   * @param dropRemainder The dropRemainder value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ParallelBatchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ParallelBatchDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSize, Operand<TInt64> numParallelCalls, Operand<TBool> dropRemainder,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ParallelBatchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(batchSize.asOutput());
    opBuilder.addInput(numParallelCalls.asOutput());
    opBuilder.addInput(dropRemainder.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.parallelCopy != null) {
          opBuilder.setAttr("parallel_copy", opts.parallelCopy);
        }
        if (opts.deterministic != null) {
          opBuilder.setAttr("deterministic", opts.deterministic);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new ParallelBatchDataset(opBuilder.build());
  }

  /**
   * Sets the parallelCopy option.
   *
   * @param parallelCopy the parallelCopy option
   * @return this Options instance.
   */
  public static Options parallelCopy(Boolean parallelCopy) {
    return new Options().parallelCopy(parallelCopy);
  }

  /**
   * Sets the deterministic option.
   *
   * @param deterministic the deterministic option
   * @return this Options instance.
   */
  public static Options deterministic(String deterministic) {
    return new Options().deterministic(deterministic);
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
   * Optional attributes for {@link org.tensorflow.op.data.ParallelBatchDataset}
   */
  public static class Options {
    private Boolean parallelCopy;

    private String deterministic;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the parallelCopy option.
     *
     * @param parallelCopy the parallelCopy option
     * @return this Options instance.
     */
    public Options parallelCopy(Boolean parallelCopy) {
      this.parallelCopy = parallelCopy;
      return this;
    }

    /**
     * Sets the deterministic option.
     *
     * @param deterministic the deterministic option
     * @return this Options instance.
     */
    public Options deterministic(String deterministic) {
      this.deterministic = deterministic;
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
      outputsClass = ParallelBatchDataset.class
  )
  public static class Inputs extends RawOpInputs<ParallelBatchDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The batchSize input
     */
    public final Operand<TInt64> batchSize;

    /**
     * The numParallelCalls input
     */
    public final Operand<TInt64> numParallelCalls;

    /**
     * The dropRemainder input
     */
    public final Operand<TBool> dropRemainder;

    /**
     * The parallelCopy attribute
     */
    public final boolean parallelCopy;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The deterministic attribute
     */
    public final String deterministic;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new ParallelBatchDataset(op), op, Arrays.asList("parallel_copy", "output_types", "output_shapes", "deterministic", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      batchSize = (Operand<TInt64>) op.input(inputIndex++);
      numParallelCalls = (Operand<TInt64>) op.input(inputIndex++);
      dropRemainder = (Operand<TBool>) op.input(inputIndex++);
      parallelCopy = op.attributes().getAttrBool("parallel_copy");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      deterministic = op.attributes().getAttrString("deterministic");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
