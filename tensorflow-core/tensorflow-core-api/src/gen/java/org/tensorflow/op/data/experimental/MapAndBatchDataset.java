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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that fuses mapping with batching.
 * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset} and then
 * batches {@code batch_size} of them.
 * <p>Unlike a &quot;MapDataset&quot;, which applies {@code f} sequentially, this dataset invokes up
 * to {@code batch_size * num_parallel_batches} copies of {@code f} in parallel.
 */
@OpMetadata(
    opType = MapAndBatchDataset.OP_NAME,
    inputsClass = MapAndBatchDataset.Inputs.class
)
public final class MapAndBatchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalMapAndBatchDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public MapAndBatchDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalMapAndBatchDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param otherArguments A list of tensors, typically values that were captured when building a closure
   * for {@code f}.
   * @param batchSize A scalar representing the number of elements to accumulate in a
   * batch. It determines the number of concurrent invocations of {@code f} that process
   * elements from {@code input_dataset} in parallel.
   * @param numParallelCalls A scalar representing the maximum number of parallel invocations of the {@code map_fn}
   * function. Applying the {@code map_fn} on consecutive input elements in parallel has
   * the potential to improve input pipeline throughput.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   * is smaller than desired.
   * @param f A function to apply to the outputs of {@code input_dataset}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of MapAndBatchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static MapAndBatchDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> batchSize,
      Operand<TInt64> numParallelCalls, Operand<TBool> dropRemainder, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MapAndBatchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
    opBuilder.addInput(batchSize.asOutput());
    opBuilder.addInput(numParallelCalls.asOutput());
    opBuilder.addInput(dropRemainder.asOutput());
    opBuilder.setAttr("f", f);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.preserveCardinality != null) {
          opBuilder.setAttr("preserve_cardinality", opts.preserveCardinality);
        }
      }
    }
    return new MapAndBatchDataset(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.data.experimental.MapAndBatchDataset}
   */
  public static class Options {
    private Boolean preserveCardinality;

    private Options() {
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
      outputsClass = MapAndBatchDataset.class
  )
  public static class Inputs extends RawOpInputs<MapAndBatchDataset> {
    /**
     * A variant tensor representing the input dataset.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A list of tensors, typically values that were captured when building a closure
     * for {@code f}.
     */
    public final Iterable<Operand<?>> otherArguments;

    /**
     * A scalar representing the number of elements to accumulate in a
     * batch. It determines the number of concurrent invocations of {@code f} that process
     * elements from {@code input_dataset} in parallel.
     */
    public final Operand<TInt64> batchSize;

    /**
     * A scalar representing the maximum number of parallel invocations of the {@code map_fn}
     * function. Applying the {@code map_fn} on consecutive input elements in parallel has
     * the potential to improve input pipeline throughput.
     */
    public final Operand<TInt64> numParallelCalls;

    /**
     * A scalar representing whether the last batch should be dropped in case its size
     * is smaller than desired.
     */
    public final Operand<TBool> dropRemainder;

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
     * The preserveCardinality attribute
     */
    public final boolean preserveCardinality;

    public Inputs(GraphOperation op) {
      super(new MapAndBatchDataset(op), op, Arrays.asList("Targuments", "output_types", "output_shapes", "preserve_cardinality"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int otherArgumentsLength = op.inputListLength("other_arguments");
      otherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, otherArgumentsLength));
      inputIndex += otherArgumentsLength;
      batchSize = (Operand<TInt64>) op.input(inputIndex++);
      numParallelCalls = (Operand<TInt64>) op.input(inputIndex++);
      dropRemainder = (Operand<TBool>) op.input(inputIndex++);
      Targuments = op.attributes().getAttrTypeList("Targuments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      preserveCardinality = op.attributes().getAttrBool("preserve_cardinality");
    }
  }
}
