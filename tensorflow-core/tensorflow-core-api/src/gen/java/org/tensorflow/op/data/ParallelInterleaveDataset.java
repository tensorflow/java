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
 * The resulting dataset is similar to the {@code InterleaveDataset}, except that the
 * dataset will fetch records from the interleaved datasets in parallel.
 * <p>The {@code tf.data} Python API creates instances of this op from
 * {@code Dataset.interleave()} when the {@code num_parallel_calls} parameter of that method
 * is set to any value other than {@code None}.
 * <p>By default, the output of this dataset will be deterministic, which may result
 * in the dataset blocking if the next data item to be returned isn't available.
 * In order to avoid head-of-line blocking, one can either set the {@code deterministic}
 * attribute to &quot;false&quot;, or leave it as &quot;default&quot; and set the
 * {@code experimental_deterministic} parameter of {@code tf.data.Options} to {@code False}.
 * This can improve performance at the expense of non-determinism.
 */
@OpMetadata(
    opType = ParallelInterleaveDataset.OP_NAME,
    inputsClass = ParallelInterleaveDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ParallelInterleaveDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParallelInterleaveDatasetV4";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ParallelInterleaveDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ParallelInterleaveDatasetV4 operation.
   *
   * @param scope current scope
   * @param inputDataset Dataset that produces a stream of arguments for the function {@code f}.
   * @param otherArguments Additional arguments to pass to {@code f} beyond those produced by {@code input_dataset}.
   * Evaluated once when the dataset is instantiated.
   * @param cycleLength Number of datasets (each created by applying {@code f} to the elements of
   * {@code input_dataset}) among which the {@code ParallelInterleaveDatasetV2} will cycle in a
   * round-robin fashion.
   * @param blockLength Number of elements at a time to produce from each interleaved invocation of a
   * dataset returned by {@code f}.
   * @param bufferOutputElements The number of elements each iterator being interleaved should buffer (similar
   * to the {@code .prefetch()} transformation for each interleaved iterator).
   * @param prefetchInputElements Determines the number of iterators to prefetch, allowing buffers to warm up and
   * data to be pre-fetched without blocking the main thread.
   * @param numParallelCalls Determines the number of threads that should be used for fetching data from
   * input datasets in parallel. The Python API {@code tf.data.experimental.AUTOTUNE}
   * constant can be used to indicate that the level of parallelism should be autotuned.
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   * {@code other_arguments}, to a Dataset variant that contains elements matching
   * {@code output_types} and {@code output_shapes}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ParallelInterleaveDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ParallelInterleaveDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> cycleLength, Operand<TInt64> blockLength,
      Operand<TInt64> bufferOutputElements, Operand<TInt64> prefetchInputElements,
      Operand<TInt64> numParallelCalls, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ParallelInterleaveDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
    opBuilder.addInput(cycleLength.asOutput());
    opBuilder.addInput(blockLength.asOutput());
    opBuilder.addInput(bufferOutputElements.asOutput());
    opBuilder.addInput(prefetchInputElements.asOutput());
    opBuilder.addInput(numParallelCalls.asOutput());
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
    return new ParallelInterleaveDataset(opBuilder.build());
  }

  /**
   * Sets the deterministic option.
   *
   * @param deterministic A string indicating the op-level determinism to use. Deterministic controls
   * whether the interleave is allowed to return elements out of order if the next
   * element to be returned isn't available, but a later element is. Options are
   * &quot;true&quot;, &quot;false&quot;, and &quot;default&quot;. &quot;default&quot; indicates that determinism should be
   * decided by the {@code experimental_deterministic} parameter of {@code tf.data.Options}.
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
   * Optional attributes for {@link org.tensorflow.op.data.ParallelInterleaveDataset}
   */
  public static class Options {
    private String deterministic;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the deterministic option.
     *
     * @param deterministic A string indicating the op-level determinism to use. Deterministic controls
     * whether the interleave is allowed to return elements out of order if the next
     * element to be returned isn't available, but a later element is. Options are
     * &quot;true&quot;, &quot;false&quot;, and &quot;default&quot;. &quot;default&quot; indicates that determinism should be
     * decided by the {@code experimental_deterministic} parameter of {@code tf.data.Options}.
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
      outputsClass = ParallelInterleaveDataset.class
  )
  public static class Inputs extends RawOpInputs<ParallelInterleaveDataset> {
    /**
     * Dataset that produces a stream of arguments for the function {@code f}.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * Additional arguments to pass to {@code f} beyond those produced by {@code input_dataset}.
     * Evaluated once when the dataset is instantiated.
     */
    public final Iterable<Operand<?>> otherArguments;

    /**
     * Number of datasets (each created by applying {@code f} to the elements of
     * {@code input_dataset}) among which the {@code ParallelInterleaveDatasetV2} will cycle in a
     * round-robin fashion.
     */
    public final Operand<TInt64> cycleLength;

    /**
     * Number of elements at a time to produce from each interleaved invocation of a
     * dataset returned by {@code f}.
     */
    public final Operand<TInt64> blockLength;

    /**
     * The number of elements each iterator being interleaved should buffer (similar
     * to the {@code .prefetch()} transformation for each interleaved iterator).
     */
    public final Operand<TInt64> bufferOutputElements;

    /**
     * Determines the number of iterators to prefetch, allowing buffers to warm up and
     * data to be pre-fetched without blocking the main thread.
     */
    public final Operand<TInt64> prefetchInputElements;

    /**
     * Determines the number of threads that should be used for fetching data from
     * input datasets in parallel. The Python API {@code tf.data.experimental.AUTOTUNE}
     * constant can be used to indicate that the level of parallelism should be autotuned.
     */
    public final Operand<TInt64> numParallelCalls;

    /**
     * A string indicating the op-level determinism to use. Deterministic controls
     * whether the interleave is allowed to return elements out of order if the next
     * element to be returned isn't available, but a later element is. Options are
     * "true", "false", and "default". "default" indicates that determinism should be
     * decided by the `experimental_deterministic` parameter of `tf.data.Options`.
     */
    public final String deterministic;

    /**
     * Types of the elements of `other_arguments`.
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
      super(new ParallelInterleaveDataset(op), op, Arrays.asList("deterministic", "Targuments", "output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int otherArgumentsLength = op.inputListLength("other_arguments");
      otherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, otherArgumentsLength));
      inputIndex += otherArgumentsLength;
      cycleLength = (Operand<TInt64>) op.input(inputIndex++);
      blockLength = (Operand<TInt64>) op.input(inputIndex++);
      bufferOutputElements = (Operand<TInt64>) op.input(inputIndex++);
      prefetchInputElements = (Operand<TInt64>) op.input(inputIndex++);
      numParallelCalls = (Operand<TInt64>) op.input(inputIndex++);
      deterministic = op.attributes().getAttrString("deterministic");
      Targuments = op.attributes().getAttrTypeList("Targuments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
