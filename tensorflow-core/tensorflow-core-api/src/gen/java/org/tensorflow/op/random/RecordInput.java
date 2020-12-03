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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Emits randomized records.
 */
@Operator(group = "random")
public final class RecordInput extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.random.RecordInput}
   */
  public static class Options {
    
    /**
     * @param fileRandomSeed Random seeds used to produce randomized records.
     */
    public Options fileRandomSeed(Long fileRandomSeed) {
      this.fileRandomSeed = fileRandomSeed;
      return this;
    }
    
    /**
     * @param fileShuffleShiftRatio Shifts the list of files after the list is randomly
     * shuffled.
     */
    public Options fileShuffleShiftRatio(Float fileShuffleShiftRatio) {
      this.fileShuffleShiftRatio = fileShuffleShiftRatio;
      return this;
    }
    
    /**
     * @param fileBufferSize The randomization shuffling buffer.
     */
    public Options fileBufferSize(Long fileBufferSize) {
      this.fileBufferSize = fileBufferSize;
      return this;
    }
    
    /**
     * @param fileParallelism How many sstables are opened and concurrently iterated over.
     */
    public Options fileParallelism(Long fileParallelism) {
      this.fileParallelism = fileParallelism;
      return this;
    }
    
    /**
     * @param batchSize The batch size.
     */
    public Options batchSize(Long batchSize) {
      this.batchSize = batchSize;
      return this;
    }
    
    /**
     * @param compressionType The type of compression for the file. Currently ZLIB and
     * GZIP are supported. Defaults to none.
     */
    public Options compressionType(String compressionType) {
      this.compressionType = compressionType;
      return this;
    }
    
    private Long fileRandomSeed;
    private Float fileShuffleShiftRatio;
    private Long fileBufferSize;
    private Long fileParallelism;
    private Long batchSize;
    private String compressionType;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new RecordInput operation.
   * 
   * @param scope current scope
   * @param filePattern Glob pattern for the data files.
   * @param options carries optional attributes values
   * @return a new instance of RecordInput
   */
  @Endpoint(describeByClass = true)
  public static RecordInput create(Scope scope, String filePattern, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("RecordInput", scope.makeOpName("RecordInput"));
    opBuilder = scope.apply(opBuilder);
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
   * @param fileRandomSeed Random seeds used to produce randomized records.
   */
  public static Options fileRandomSeed(Long fileRandomSeed) {
    return new Options().fileRandomSeed(fileRandomSeed);
  }
  
  /**
   * @param fileShuffleShiftRatio Shifts the list of files after the list is randomly
   * shuffled.
   */
  public static Options fileShuffleShiftRatio(Float fileShuffleShiftRatio) {
    return new Options().fileShuffleShiftRatio(fileShuffleShiftRatio);
  }
  
  /**
   * @param fileBufferSize The randomization shuffling buffer.
   */
  public static Options fileBufferSize(Long fileBufferSize) {
    return new Options().fileBufferSize(fileBufferSize);
  }
  
  /**
   * @param fileParallelism How many sstables are opened and concurrently iterated over.
   */
  public static Options fileParallelism(Long fileParallelism) {
    return new Options().fileParallelism(fileParallelism);
  }
  
  /**
   * @param batchSize The batch size.
   */
  public static Options batchSize(Long batchSize) {
    return new Options().batchSize(batchSize);
  }
  
  /**
   * @param compressionType The type of compression for the file. Currently ZLIB and
   * GZIP are supported. Defaults to none.
   */
  public static Options compressionType(String compressionType) {
    return new Options().compressionType(compressionType);
  }
  
  /**
   * A tensor of shape [batch_size].
   */
  public Output<TString> records() {
    return records;
  }
  
  @Override
  public Output<TString> asOutput() {
    return records;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RecordInput";
  
  private Output<TString> records;
  
  private RecordInput(Operation operation) {
    super(operation);
    int outputIdx = 0;
    records = operation.output(outputIdx++);
  }
}
