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
 * Unlike a &quot;MapDataset&quot;, which applies {@code f} sequentially, this dataset invokes up
 * to {@code num_parallel_calls} copies of {@code f} in parallel.
 */
@OpMetadata(
    opType = ParallelMapDataset.OP_NAME,
    inputsClass = ParallelMapDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ParallelMapDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParallelMapDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ParallelMapDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ParallelMapDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param numParallelCalls The number of concurrent invocations of {@code f} that process
   * elements from {@code input_dataset} in parallel.
   * @param f The value of the f attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ParallelMapDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ParallelMapDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> numParallelCalls, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ParallelMapDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
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
        if (opts.useInterOpParallelism != null) {
          opBuilder.setAttr("use_inter_op_parallelism", opts.useInterOpParallelism);
        }
        if (opts.deterministic != null) {
          opBuilder.setAttr("deterministic", opts.deterministic);
        }
        if (opts.preserveCardinality != null) {
          opBuilder.setAttr("preserve_cardinality", opts.preserveCardinality);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new ParallelMapDataset(opBuilder.build());
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
   * Sets the deterministic option.
   *
   * @param deterministic the deterministic option
   * @return this Options instance.
   */
  public static Options deterministic(String deterministic) {
    return new Options().deterministic(deterministic);
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
   * Optional attributes for {@link org.tensorflow.op.data.ParallelMapDataset}
   */
  public static class Options {
    private Boolean useInterOpParallelism;

    private String deterministic;

    private Boolean preserveCardinality;

    private String metadata;

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
     * Sets the preserveCardinality option.
     *
     * @param preserveCardinality the preserveCardinality option
     * @return this Options instance.
     */
    public Options preserveCardinality(Boolean preserveCardinality) {
      this.preserveCardinality = preserveCardinality;
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
      outputsClass = ParallelMapDataset.class
  )
  public static class Inputs extends RawOpInputs<ParallelMapDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The otherArguments input
     */
    public final Iterable<Operand<?>> otherArguments;

    /**
     * The number of concurrent invocations of {@code f} that process
     * elements from {@code input_dataset} in parallel.
     */
    public final Operand<TInt64> numParallelCalls;

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
     * The deterministic attribute
     */
    public final String deterministic;

    /**
     * The preserveCardinality attribute
     */
    public final boolean preserveCardinality;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new ParallelMapDataset(op), op, Arrays.asList("Targuments", "output_types", "output_shapes", "use_inter_op_parallelism", "deterministic", "preserve_cardinality", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int otherArgumentsLength = op.inputListLength("other_arguments");
      otherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, otherArgumentsLength));
      inputIndex += otherArgumentsLength;
      numParallelCalls = (Operand<TInt64>) op.input(inputIndex++);
      Targuments = op.attributes().getAttrTypeList("Targuments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      useInterOpParallelism = op.attributes().getAttrBool("use_inter_op_parallelism");
      deterministic = op.attributes().getAttrString("deterministic");
      preserveCardinality = op.attributes().getAttrBool("preserve_cardinality");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
