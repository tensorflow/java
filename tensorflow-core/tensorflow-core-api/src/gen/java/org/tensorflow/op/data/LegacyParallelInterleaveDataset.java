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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
 * The resulting dataset is similar to the {@code InterleaveDataset}, with the exception
 * that if retrieving the next value from a dataset would cause the requester to
 * block, it will skip that input dataset. This dataset is especially useful
 * when loading data from a variable-latency datastores (e.g. HDFS, GCS), as it
 * allows the training step to proceed so long as some data is available.
 * <p>!! WARNING !! This dataset is not deterministic!
 */
@OpMetadata(
    opType = LegacyParallelInterleaveDataset.OP_NAME,
    inputsClass = LegacyParallelInterleaveDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class LegacyParallelInterleaveDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LegacyParallelInterleaveDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public LegacyParallelInterleaveDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LegacyParallelInterleaveDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param cycleLength The cycleLength value
   * @param blockLength The blockLength value
   * @param bufferOutputElements The bufferOutputElements value
   * @param prefetchInputElements The prefetchInputElements value
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   * {@code other_arguments}, to a Dataset variant that contains elements matching
   * {@code output_types} and {@code output_shapes}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of LegacyParallelInterleaveDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static LegacyParallelInterleaveDataset create(Scope scope,
      Operand<? extends TType> inputDataset, Iterable<Operand<?>> otherArguments,
      Operand<TInt64> cycleLength, Operand<TInt64> blockLength,
      Operand<TInt64> bufferOutputElements, Operand<TInt64> prefetchInputElements,
      ConcreteFunction f, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LegacyParallelInterleaveDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
    opBuilder.addInput(cycleLength.asOutput());
    opBuilder.addInput(blockLength.asOutput());
    opBuilder.addInput(bufferOutputElements.asOutput());
    opBuilder.addInput(prefetchInputElements.asOutput());
    opBuilder.setAttr("f", f);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.deterministic != null) {
          opBuilder.setAttr("deterministic", opts.deterministic);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new LegacyParallelInterleaveDataset(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.data.LegacyParallelInterleaveDataset}
   */
  public static class Options {
    private String deterministic;

    private String metadata;

    private Options() {
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
      outputsClass = LegacyParallelInterleaveDataset.class
  )
  public static class Inputs extends RawOpInputs<LegacyParallelInterleaveDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The otherArguments input
     */
    public final Iterable<Operand<?>> otherArguments;

    /**
     * The cycleLength input
     */
    public final Operand<TInt64> cycleLength;

    /**
     * The blockLength input
     */
    public final Operand<TInt64> blockLength;

    /**
     * The bufferOutputElements input
     */
    public final Operand<TInt64> bufferOutputElements;

    /**
     * The prefetchInputElements input
     */
    public final Operand<TInt64> prefetchInputElements;

    /**
     * The deterministic attribute
     */
    public final String deterministic;

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
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new LegacyParallelInterleaveDataset(op), op, Arrays.asList("deterministic", "Targuments", "output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int otherArgumentsLength = op.inputListLength("other_arguments");
      otherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, otherArgumentsLength));
      inputIndex += otherArgumentsLength;
      cycleLength = (Operand<TInt64>) op.input(inputIndex++);
      blockLength = (Operand<TInt64>) op.input(inputIndex++);
      bufferOutputElements = (Operand<TInt64>) op.input(inputIndex++);
      prefetchInputElements = (Operand<TInt64>) op.input(inputIndex++);
      deterministic = op.attributes().getAttrString("deterministic");
      Targuments = op.attributes().getAttrTypeList("Targuments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
