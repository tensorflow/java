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

package org.tensorflow.op.random;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Emits randomized records.
 */
@OpMetadata(
    opType = RecordInput.OP_NAME,
    inputsClass = RecordInput.Inputs.class
)
@Operator(
    group = "random"
)
public final class RecordInput extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RecordInput";

  private Output<TString> records;

  public RecordInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    records = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RecordInput operation.
   *
   * @param scope current scope
   * @param filePattern Glob pattern for the data files.
   * @param options carries optional attribute values
   * @return a new instance of RecordInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static RecordInput create(Scope scope, String filePattern, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RecordInput");
    opBuilder.setAttr("file_pattern", filePattern);
    if (options != null) {
      for (Options opts : options) {
        if (opts.fileRandomSeed != null) {
          opBuilder.setAttr("file_random_seed", opts.fileRandomSeed);
        }
        if (opts.fileShuffleShiftRatio != null) {
          opBuilder.setAttr("file_shuffle_shift_ratio", opts.fileShuffleShiftRatio);
        }
        if (opts.fileBufferSize != null) {
          opBuilder.setAttr("file_buffer_size", opts.fileBufferSize);
        }
        if (opts.fileParallelism != null) {
          opBuilder.setAttr("file_parallelism", opts.fileParallelism);
        }
        if (opts.batchSize != null) {
          opBuilder.setAttr("batch_size", opts.batchSize);
        }
        if (opts.compressionType != null) {
          opBuilder.setAttr("compression_type", opts.compressionType);
        }
      }
    }
    return new RecordInput(opBuilder.build());
  }

  /**
   * Sets the fileRandomSeed option.
   *
   * @param fileRandomSeed Random seeds used to produce randomized records.
   * @return this Options instance.
   */
  public static Options fileRandomSeed(Long fileRandomSeed) {
    return new Options().fileRandomSeed(fileRandomSeed);
  }

  /**
   * Sets the fileShuffleShiftRatio option.
   *
   * @param fileShuffleShiftRatio Shifts the list of files after the list is randomly
   * shuffled.
   * @return this Options instance.
   */
  public static Options fileShuffleShiftRatio(Float fileShuffleShiftRatio) {
    return new Options().fileShuffleShiftRatio(fileShuffleShiftRatio);
  }

  /**
   * Sets the fileBufferSize option.
   *
   * @param fileBufferSize The randomization shuffling buffer.
   * @return this Options instance.
   */
  public static Options fileBufferSize(Long fileBufferSize) {
    return new Options().fileBufferSize(fileBufferSize);
  }

  /**
   * Sets the fileParallelism option.
   *
   * @param fileParallelism How many sstables are opened and concurrently iterated over.
   * @return this Options instance.
   */
  public static Options fileParallelism(Long fileParallelism) {
    return new Options().fileParallelism(fileParallelism);
  }

  /**
   * Sets the batchSize option.
   *
   * @param batchSize The batch size.
   * @return this Options instance.
   */
  public static Options batchSize(Long batchSize) {
    return new Options().batchSize(batchSize);
  }

  /**
   * Sets the compressionType option.
   *
   * @param compressionType The type of compression for the file. Currently ZLIB and
   * GZIP are supported. Defaults to none.
   * @return this Options instance.
   */
  public static Options compressionType(String compressionType) {
    return new Options().compressionType(compressionType);
  }

  /**
   * Gets records.
   * A tensor of shape [batch_size].
   * @return records.
   */
  public Output<TString> records() {
    return records;
  }

  @Override
  public Output<TString> asOutput() {
    return records;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.random.RecordInput}
   */
  public static class Options {
    private Long fileRandomSeed;

    private Float fileShuffleShiftRatio;

    private Long fileBufferSize;

    private Long fileParallelism;

    private Long batchSize;

    private String compressionType;

    private Options() {
    }

    /**
     * Sets the fileRandomSeed option.
     *
     * @param fileRandomSeed Random seeds used to produce randomized records.
     * @return this Options instance.
     */
    public Options fileRandomSeed(Long fileRandomSeed) {
      this.fileRandomSeed = fileRandomSeed;
      return this;
    }

    /**
     * Sets the fileShuffleShiftRatio option.
     *
     * @param fileShuffleShiftRatio Shifts the list of files after the list is randomly
     * shuffled.
     * @return this Options instance.
     */
    public Options fileShuffleShiftRatio(Float fileShuffleShiftRatio) {
      this.fileShuffleShiftRatio = fileShuffleShiftRatio;
      return this;
    }

    /**
     * Sets the fileBufferSize option.
     *
     * @param fileBufferSize The randomization shuffling buffer.
     * @return this Options instance.
     */
    public Options fileBufferSize(Long fileBufferSize) {
      this.fileBufferSize = fileBufferSize;
      return this;
    }

    /**
     * Sets the fileParallelism option.
     *
     * @param fileParallelism How many sstables are opened and concurrently iterated over.
     * @return this Options instance.
     */
    public Options fileParallelism(Long fileParallelism) {
      this.fileParallelism = fileParallelism;
      return this;
    }

    /**
     * Sets the batchSize option.
     *
     * @param batchSize The batch size.
     * @return this Options instance.
     */
    public Options batchSize(Long batchSize) {
      this.batchSize = batchSize;
      return this;
    }

    /**
     * Sets the compressionType option.
     *
     * @param compressionType The type of compression for the file. Currently ZLIB and
     * GZIP are supported. Defaults to none.
     * @return this Options instance.
     */
    public Options compressionType(String compressionType) {
      this.compressionType = compressionType;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RecordInput.class
  )
  public static class Inputs extends RawOpInputs<RecordInput> {
    /**
     * Glob pattern for the data files.
     */
    public final String filePattern;

    /**
     * Random seeds used to produce randomized records.
     */
    public final long fileRandomSeed;

    /**
     * Shifts the list of files after the list is randomly
     * shuffled.
     */
    public final float fileShuffleShiftRatio;

    /**
     * The randomization shuffling buffer.
     */
    public final long fileBufferSize;

    /**
     * How many sstables are opened and concurrently iterated over.
     */
    public final long fileParallelism;

    /**
     * The batch size.
     */
    public final long batchSize;

    /**
     * The type of compression for the file. Currently ZLIB and
     * GZIP are supported. Defaults to none.
     */
    public final String compressionType;

    public Inputs(GraphOperation op) {
      super(new RecordInput(op), op, Arrays.asList("file_pattern", "file_random_seed", "file_shuffle_shift_ratio", "file_buffer_size", "file_parallelism", "batch_size", "compression_type"));
      int inputIndex = 0;
      filePattern = op.attributes().getAttrString("file_pattern");
      fileRandomSeed = op.attributes().getAttrInt("file_random_seed");
      fileShuffleShiftRatio = op.attributes().getAttrFloat("file_shuffle_shift_ratio");
      fileBufferSize = op.attributes().getAttrInt("file_buffer_size");
      fileParallelism = op.attributes().getAttrInt("file_parallelism");
      batchSize = op.attributes().getAttrInt("batch_size");
      compressionType = op.attributes().getAttrString("compression_type");
    }
  }
}
