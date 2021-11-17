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
 * Creates a dataset that batches and pads {@code batch_size} elements from the input.
 */
@OpMetadata(
    opType = PaddedBatchDataset.OP_NAME,
    inputsClass = PaddedBatchDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class PaddedBatchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "PaddedBatchDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public PaddedBatchDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new PaddedBatchDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param batchSize A scalar representing the number of elements to accumulate in a
   * batch.
   * @param paddedShapes A list of int64 tensors representing the desired padded shapes
   * of the corresponding output components. These shapes may be partially
   * specified, using {@code -1} to indicate that a particular dimension should be
   * padded to the maximum size of all batch elements.
   * @param paddingValues A list of scalars containing the padding value to use for
   * each of the outputs.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   * is smaller than desired.
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of PaddedBatchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static PaddedBatchDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSize, Iterable<Operand<TInt64>> paddedShapes,
      Iterable<Operand<?>> paddingValues, Operand<TBool> dropRemainder, List<Shape> outputShapes,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "PaddedBatchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(batchSize.asOutput());
    opBuilder.addInputList(Operands.asOutputs(paddedShapes));
    opBuilder.addInputList(Operands.asOutputs(paddingValues));
    opBuilder.addInput(dropRemainder.asOutput());
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
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new PaddedBatchDataset(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.data.PaddedBatchDataset}
   */
  public static class Options {
    private Boolean parallelCopy;

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
      outputsClass = PaddedBatchDataset.class
  )
  public static class Inputs extends RawOpInputs<PaddedBatchDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A scalar representing the number of elements to accumulate in a
     * batch.
     */
    public final Operand<TInt64> batchSize;

    /**
     * A list of int64 tensors representing the desired padded shapes
     * of the corresponding output components. These shapes may be partially
     * specified, using {@code -1} to indicate that a particular dimension should be
     * padded to the maximum size of all batch elements.
     */
    public final Iterable<Operand<TInt64>> paddedShapes;

    /**
     * A list of scalars containing the padding value to use for
     * each of the outputs.
     */
    public final Iterable<Operand<?>> paddingValues;

    /**
     * A scalar representing whether the last batch should be dropped in case its size
     * is smaller than desired.
     */
    public final Operand<TBool> dropRemainder;

    /**
     * The parallelCopy attribute
     */
    public final boolean parallelCopy;

    /**
     * The ToutputTypes attribute
     */
    public final DataType[] ToutputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new PaddedBatchDataset(op), op, Arrays.asList("parallel_copy", "Toutput_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      batchSize = (Operand<TInt64>) op.input(inputIndex++);
      int paddedShapesLength = op.inputListLength("padded_shapes");
      paddedShapes = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, paddedShapesLength));
      inputIndex += paddedShapesLength;
      int paddingValuesLength = op.inputListLength("padding_values");
      paddingValues = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, paddingValuesLength));
      inputIndex += paddingValuesLength;
      dropRemainder = (Operand<TBool>) op.input(inputIndex++);
      parallelCopy = op.attributes().getAttrBool("parallel_copy");
      ToutputTypes = op.attributes().getAttrTypeList("Toutput_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
