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
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that will write to / read from a snapshot.
 * <p>
 * This dataset attempts to determine whether a valid snapshot exists at the
 * `snapshot_path`, and reads from the snapshot in lieu of using `input_dataset`.
 * If not, it will run the preprocessing pipeline as usual, and write out a
 * snapshot of the data processed for future use.
 */
public final class SnapshotDataset extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.SnapshotDataset}
   */
  public static class Options {
    
    /**
     * @param compression 
     */
    public Options compression(String compression) {
      this.compression = compression;
      return this;
    }
    
    /**
     * @param readerPathPrefix 
     */
    public Options readerPathPrefix(String readerPathPrefix) {
      this.readerPathPrefix = readerPathPrefix;
      return this;
    }
    
    /**
     * @param writerPathPrefix 
     */
    public Options writerPathPrefix(String writerPathPrefix) {
      this.writerPathPrefix = writerPathPrefix;
      return this;
    }
    
    /**
     * @param shardSizeBytes 
     */
    public Options shardSizeBytes(Long shardSizeBytes) {
      this.shardSizeBytes = shardSizeBytes;
      return this;
    }
    
    /**
     * @param pendingSnapshotExpirySeconds 
     */
    public Options pendingSnapshotExpirySeconds(Long pendingSnapshotExpirySeconds) {
      this.pendingSnapshotExpirySeconds = pendingSnapshotExpirySeconds;
      return this;
    }
    
    /**
     * @param numReaderThreads 
     */
    public Options numReaderThreads(Long numReaderThreads) {
      this.numReaderThreads = numReaderThreads;
      return this;
    }
    
    /**
     * @param readerBufferSize 
     */
    public Options readerBufferSize(Long readerBufferSize) {
      this.readerBufferSize = readerBufferSize;
      return this;
    }
    
    /**
     * @param numWriterThreads 
     */
    public Options numWriterThreads(Long numWriterThreads) {
      this.numWriterThreads = numWriterThreads;
      return this;
    }
    
    /**
     * @param writerBufferSize 
     */
    public Options writerBufferSize(Long writerBufferSize) {
      this.writerBufferSize = writerBufferSize;
      return this;
    }
    
    /**
     * @param shuffleOnRead 
     */
    public Options shuffleOnRead(Boolean shuffleOnRead) {
      this.shuffleOnRead = shuffleOnRead;
      return this;
    }
    
    /**
     * @param seed 
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }
    
    /**
     * @param seed2 
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
    
    /**
     * @param mode 
     */
    public Options mode(String mode) {
      this.mode = mode;
      return this;
    }
    
    /**
     * @param snapshotName 
     */
    public Options snapshotName(String snapshotName) {
      this.snapshotName = snapshotName;
      return this;
    }
    
    private String compression;
    private String readerPathPrefix;
    private String writerPathPrefix;
    private Long shardSizeBytes;
    private Long pendingSnapshotExpirySeconds;
    private Long numReaderThreads;
    private Long readerBufferSize;
    private Long numWriterThreads;
    private Long writerBufferSize;
    private Boolean shuffleOnRead;
    private Long seed;
    private Long seed2;
    private String mode;
    private String snapshotName;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SnapshotDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param path The path we should write snapshots to / read snapshots from.
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of SnapshotDataset
   */
  @Endpoint(describeByClass = true)
  public static SnapshotDataset create(Scope scope, Operand<?> inputDataset, Operand<TString> path, List<DataType<?>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SnapshotDataset", scope.makeOpName("SnapshotDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(path.asOutput());
    opBuilder = scope.apply(opBuilder);
    DataType[] outputTypesArray = new DataType[outputTypes.size()];
    for (int i = 0; i < outputTypesArray.length; ++i) {
      outputTypesArray[i] = outputTypes.get(i);
    }
    opBuilder.setAttr("output_types", outputTypesArray);
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.compression != null) {
          opBuilder.setAttr("compression", opts.compression);
        }
        if (opts.readerPathPrefix != null) {
          opBuilder.setAttr("reader_path_prefix", opts.readerPathPrefix);
        }
        if (opts.writerPathPrefix != null) {
          opBuilder.setAttr("writer_path_prefix", opts.writerPathPrefix);
        }
        if (opts.shardSizeBytes != null) {
          opBuilder.setAttr("shard_size_bytes", opts.shardSizeBytes);
        }
        if (opts.pendingSnapshotExpirySeconds != null) {
          opBuilder.setAttr("pending_snapshot_expiry_seconds", opts.pendingSnapshotExpirySeconds);
        }
        if (opts.numReaderThreads != null) {
          opBuilder.setAttr("num_reader_threads", opts.numReaderThreads);
        }
        if (opts.readerBufferSize != null) {
          opBuilder.setAttr("reader_buffer_size", opts.readerBufferSize);
        }
        if (opts.numWriterThreads != null) {
          opBuilder.setAttr("num_writer_threads", opts.numWriterThreads);
        }
        if (opts.writerBufferSize != null) {
          opBuilder.setAttr("writer_buffer_size", opts.writerBufferSize);
        }
        if (opts.shuffleOnRead != null) {
          opBuilder.setAttr("shuffle_on_read", opts.shuffleOnRead);
        }
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
        if (opts.mode != null) {
          opBuilder.setAttr("mode", opts.mode);
        }
        if (opts.snapshotName != null) {
          opBuilder.setAttr("snapshot_name", opts.snapshotName);
        }
      }
    }
    return new SnapshotDataset(opBuilder.build());
  }
  
  /**
   * @param compression 
   */
  public static Options compression(String compression) {
    return new Options().compression(compression);
  }
  
  /**
   * @param readerPathPrefix 
   */
  public static Options readerPathPrefix(String readerPathPrefix) {
    return new Options().readerPathPrefix(readerPathPrefix);
  }
  
  /**
   * @param writerPathPrefix 
   */
  public static Options writerPathPrefix(String writerPathPrefix) {
    return new Options().writerPathPrefix(writerPathPrefix);
  }
  
  /**
   * @param shardSizeBytes 
   */
  public static Options shardSizeBytes(Long shardSizeBytes) {
    return new Options().shardSizeBytes(shardSizeBytes);
  }
  
  /**
   * @param pendingSnapshotExpirySeconds 
   */
  public static Options pendingSnapshotExpirySeconds(Long pendingSnapshotExpirySeconds) {
    return new Options().pendingSnapshotExpirySeconds(pendingSnapshotExpirySeconds);
  }
  
  /**
   * @param numReaderThreads 
   */
  public static Options numReaderThreads(Long numReaderThreads) {
    return new Options().numReaderThreads(numReaderThreads);
  }
  
  /**
   * @param readerBufferSize 
   */
  public static Options readerBufferSize(Long readerBufferSize) {
    return new Options().readerBufferSize(readerBufferSize);
  }
  
  /**
   * @param numWriterThreads 
   */
  public static Options numWriterThreads(Long numWriterThreads) {
    return new Options().numWriterThreads(numWriterThreads);
  }
  
  /**
   * @param writerBufferSize 
   */
  public static Options writerBufferSize(Long writerBufferSize) {
    return new Options().writerBufferSize(writerBufferSize);
  }
  
  /**
   * @param shuffleOnRead 
   */
  public static Options shuffleOnRead(Boolean shuffleOnRead) {
    return new Options().shuffleOnRead(shuffleOnRead);
  }
  
  /**
   * @param seed 
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }
  
  /**
   * @param seed2 
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }
  
  /**
   * @param mode 
   */
  public static Options mode(String mode) {
    return new Options().mode(mode);
  }
  
  /**
   * @param snapshotName 
   */
  public static Options snapshotName(String snapshotName) {
    return new Options().snapshotName(snapshotName);
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SnapshotDataset";
  
  private Output<?> handle;
  
  private SnapshotDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
