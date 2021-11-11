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
 * Creates a dataset that shards the input dataset.
 * Creates a dataset that shards the input dataset by num_workers, returning a
 * sharded dataset for the index-th worker. This attempts to automatically shard
 * a dataset by examining the Dataset graph and inserting a shard op before the
 * inputs to a reader Dataset (e.g. CSVDataset, TFRecordDataset).
 * <p>This dataset will throw a NotFound error if we cannot shard the dataset
 * automatically.
 */
@OpMetadata(
    opType = AutoShardDataset.OP_NAME,
    inputsClass = AutoShardDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class AutoShardDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AutoShardDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public AutoShardDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AutoShardDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param numWorkers A scalar representing the number of workers to distribute this dataset across.
   * @param index A scalar representing the index of the current worker out of num_workers.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of AutoShardDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static AutoShardDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> numWorkers, Operand<TInt64> index, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AutoShardDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(numWorkers.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.autoShardPolicy != null) {
          opBuilder.setAttr("auto_shard_policy", opts.autoShardPolicy);
        }
        if (opts.numReplicas != null) {
          opBuilder.setAttr("num_replicas", opts.numReplicas);
        }
      }
    }
    return new AutoShardDataset(opBuilder.build());
  }

  /**
   * Sets the autoShardPolicy option.
   *
   * @param autoShardPolicy the autoShardPolicy option
   * @return this Options instance.
   */
  public static Options autoShardPolicy(Long autoShardPolicy) {
    return new Options().autoShardPolicy(autoShardPolicy);
  }

  /**
   * Sets the numReplicas option.
   *
   * @param numReplicas the numReplicas option
   * @return this Options instance.
   */
  public static Options numReplicas(Long numReplicas) {
    return new Options().numReplicas(numReplicas);
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
   * Optional attributes for {@link org.tensorflow.op.data.AutoShardDataset}
   */
  public static class Options {
    private Long autoShardPolicy;

    private Long numReplicas;

    private Options() {
    }

    /**
     * Sets the autoShardPolicy option.
     *
     * @param autoShardPolicy the autoShardPolicy option
     * @return this Options instance.
     */
    public Options autoShardPolicy(Long autoShardPolicy) {
      this.autoShardPolicy = autoShardPolicy;
      return this;
    }

    /**
     * Sets the numReplicas option.
     *
     * @param numReplicas the numReplicas option
     * @return this Options instance.
     */
    public Options numReplicas(Long numReplicas) {
      this.numReplicas = numReplicas;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AutoShardDataset.class
  )
  public static class Inputs extends RawOpInputs<AutoShardDataset> {
    /**
     * A variant tensor representing the input dataset.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A scalar representing the number of workers to distribute this dataset across.
     */
    public final Operand<TInt64> numWorkers;

    /**
     * A scalar representing the index of the current worker out of num_workers.
     */
    public final Operand<TInt64> index;

    /**
     * The autoShardPolicy attribute
     */
    public final long autoShardPolicy;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The numReplicas attribute
     */
    public final long numReplicas;

    public Inputs(GraphOperation op) {
      super(new AutoShardDataset(op), op, Arrays.asList("auto_shard_policy", "output_types", "output_shapes", "num_replicas"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      numWorkers = (Operand<TInt64>) op.input(inputIndex++);
      index = (Operand<TInt64>) op.input(inputIndex++);
      autoShardPolicy = op.attributes().getAttrInt("auto_shard_policy");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      numReplicas = op.attributes().getAttrInt("num_replicas");
    }
  }
}
