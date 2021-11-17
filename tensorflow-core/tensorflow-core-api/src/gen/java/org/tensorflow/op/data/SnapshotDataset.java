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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that will write to / read from a snapshot.
 * This dataset attempts to determine whether a valid snapshot exists at the
 * {@code snapshot_path}, and reads from the snapshot in lieu of using {@code input_dataset}.
 * If not, it will run the preprocessing pipeline as usual, and write out a
 * snapshot of the data processed for future use.
 */
@OpMetadata(
    opType = SnapshotDataset.OP_NAME,
    inputsClass = SnapshotDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class SnapshotDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SnapshotDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public SnapshotDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SnapshotDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param path The path we should write snapshots to / read snapshots from.
   * @param readerFuncOtherArgs The readerFuncOtherArgs value
   * @param shardFuncOtherArgs The shardFuncOtherArgs value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param readerFunc Optional. A function to control how to read data from snapshot shards.
   * @param shardFunc Optional. A function to control how to shard data when writing a snapshot.
   * @param options carries optional attribute values
   * @return a new instance of SnapshotDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static SnapshotDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TString> path, Iterable<Operand<?>> readerFuncOtherArgs,
      Iterable<Operand<?>> shardFuncOtherArgs, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, ConcreteFunction readerFunc, ConcreteFunction shardFunc,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SnapshotDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(path.asOutput());
    opBuilder.addInputList(Operands.asOutputs(readerFuncOtherArgs));
    opBuilder.addInputList(Operands.asOutputs(shardFuncOtherArgs));
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    opBuilder.setAttr("reader_func", readerFunc);
    opBuilder.setAttr("shard_func", shardFunc);
    if (options != null) {
      for (Options opts : options) {
        if (opts.compression != null) {
          opBuilder.setAttr("compression", opts.compression);
        }
        if (opts.readerPrefix != null) {
          opBuilder.setAttr("reader_prefix", opts.readerPrefix);
        }
        if (opts.writerPrefix != null) {
          opBuilder.setAttr("writer_prefix", opts.writerPrefix);
        }
        if (opts.hashValid != null) {
          opBuilder.setAttr("hash_valid", opts.hashValid);
        }
        if (opts.hash != null) {
          opBuilder.setAttr("hash", opts.hash);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new SnapshotDataset(opBuilder.build());
  }

  /**
   * Sets the compression option.
   *
   * @param compression The type of compression to be applied to the saved snapshot files.
   * @return this Options instance.
   */
  public static Options compression(String compression) {
    return new Options().compression(compression);
  }

  /**
   * Sets the readerPrefix option.
   *
   * @param readerPrefix the readerPrefix option
   * @return this Options instance.
   */
  public static Options readerPrefix(String readerPrefix) {
    return new Options().readerPrefix(readerPrefix);
  }

  /**
   * Sets the writerPrefix option.
   *
   * @param writerPrefix the writerPrefix option
   * @return this Options instance.
   */
  public static Options writerPrefix(String writerPrefix) {
    return new Options().writerPrefix(writerPrefix);
  }

  /**
   * Sets the hashValid option.
   *
   * @param hashValid the hashValid option
   * @return this Options instance.
   */
  public static Options hashValid(Boolean hashValid) {
    return new Options().hashValid(hashValid);
  }

  /**
   * Sets the hash option.
   *
   * @param hash the hash option
   * @return this Options instance.
   */
  public static Options hash(Long hash) {
    return new Options().hash(hash);
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
   * Optional attributes for {@link org.tensorflow.op.data.SnapshotDataset}
   */
  public static class Options {
    private String compression;

    private String readerPrefix;

    private String writerPrefix;

    private Boolean hashValid;

    private Long hash;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the compression option.
     *
     * @param compression The type of compression to be applied to the saved snapshot files.
     * @return this Options instance.
     */
    public Options compression(String compression) {
      this.compression = compression;
      return this;
    }

    /**
     * Sets the readerPrefix option.
     *
     * @param readerPrefix the readerPrefix option
     * @return this Options instance.
     */
    public Options readerPrefix(String readerPrefix) {
      this.readerPrefix = readerPrefix;
      return this;
    }

    /**
     * Sets the writerPrefix option.
     *
     * @param writerPrefix the writerPrefix option
     * @return this Options instance.
     */
    public Options writerPrefix(String writerPrefix) {
      this.writerPrefix = writerPrefix;
      return this;
    }

    /**
     * Sets the hashValid option.
     *
     * @param hashValid the hashValid option
     * @return this Options instance.
     */
    public Options hashValid(Boolean hashValid) {
      this.hashValid = hashValid;
      return this;
    }

    /**
     * Sets the hash option.
     *
     * @param hash the hash option
     * @return this Options instance.
     */
    public Options hash(Long hash) {
      this.hash = hash;
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
      outputsClass = SnapshotDataset.class
  )
  public static class Inputs extends RawOpInputs<SnapshotDataset> {
    /**
     * A variant tensor representing the input dataset.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The path we should write snapshots to / read snapshots from.
     */
    public final Operand<TString> path;

    /**
     * The readerFuncOtherArgs input
     */
    public final Iterable<Operand<?>> readerFuncOtherArgs;

    /**
     * The shardFuncOtherArgs input
     */
    public final Iterable<Operand<?>> shardFuncOtherArgs;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The type of compression to be applied to the saved snapshot files.
     */
    public final String compression;

    /**
     * The readerPrefix attribute
     */
    public final String readerPrefix;

    /**
     * The writerPrefix attribute
     */
    public final String writerPrefix;

    /**
     * The hashValid attribute
     */
    public final boolean hashValid;

    /**
     * The hash attribute
     */
    public final long hash;

    /**
     * The TreaderFuncArgs attribute
     */
    public final DataType[] TreaderFuncArgs;

    /**
     * The TshardFuncArgs attribute
     */
    public final DataType[] TshardFuncArgs;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new SnapshotDataset(op), op, Arrays.asList("output_types", "output_shapes", "compression", "reader_prefix", "writer_prefix", "hash_valid", "hash", "Treader_func_args", "Tshard_func_args", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      path = (Operand<TString>) op.input(inputIndex++);
      int readerFuncOtherArgsLength = op.inputListLength("reader_func_other_args");
      readerFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, readerFuncOtherArgsLength));
      inputIndex += readerFuncOtherArgsLength;
      int shardFuncOtherArgsLength = op.inputListLength("shard_func_other_args");
      shardFuncOtherArgs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, shardFuncOtherArgsLength));
      inputIndex += shardFuncOtherArgsLength;
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      compression = op.attributes().getAttrString("compression");
      readerPrefix = op.attributes().getAttrString("reader_prefix");
      writerPrefix = op.attributes().getAttrString("writer_prefix");
      hashValid = op.attributes().getAttrBool("hash_valid");
      hash = op.attributes().getAttrInt("hash");
      TreaderFuncArgs = op.attributes().getAttrTypeList("Treader_func_args");
      TshardFuncArgs = op.attributes().getAttrTypeList("Tshard_func_args");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
