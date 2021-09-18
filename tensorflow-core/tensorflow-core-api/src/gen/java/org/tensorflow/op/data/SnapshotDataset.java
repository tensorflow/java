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

import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that will write to / read from a snapshot.
 * This dataset attempts to determine whether a valid snapshot exists at the
 * {@code snapshot_path}, and reads from the snapshot in lieu of using {@code input_dataset}.
 * If not, it will run the preprocessing pipeline as usual, and write out a
 * snapshot of the data processed for future use.
 */
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
  private SnapshotDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SnapshotDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param path The path we should write snapshots to / read snapshots from.
   * @param readerFuncOtherArgs the readerFuncOtherArgs value
   * @param shardFuncOtherArgs the shardFuncOtherArgs value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
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
  }
}
