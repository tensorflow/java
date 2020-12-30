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

package org.tensorflow.op.io;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * A Reader that outputs fixed-length records from a file.
 */
@Operator(group = "io")
public final class FixedLengthRecordReader extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.io.FixedLengthRecordReader}
   */
  public static class Options {
    
    /**
     * @param headerBytes Number of bytes in the header, defaults to 0.
     */
    public Options headerBytes(Long headerBytes) {
      this.headerBytes = headerBytes;
      return this;
    }
    
    /**
     * @param footerBytes Number of bytes in the footer, defaults to 0.
     */
    public Options footerBytes(Long footerBytes) {
      this.footerBytes = footerBytes;
      return this;
    }
    
    /**
     * @param hopBytes Number of bytes to hop before each read. Default of 0 means using
     * record_bytes.
     */
    public Options hopBytes(Long hopBytes) {
      this.hopBytes = hopBytes;
      return this;
    }
    
    /**
     * @param container If non-empty, this reader is placed in the given container.
     * Otherwise, a default container is used.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName If non-empty, this reader is named in the given bucket
     * with this shared_name. Otherwise, the node name is used instead.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    /**
     * @param encoding The type of encoding for the file. Currently ZLIB and GZIP
     * are supported. Defaults to none.
     */
    public Options encoding(String encoding) {
      this.encoding = encoding;
      return this;
    }
    
    private Long headerBytes;
    private Long footerBytes;
    private Long hopBytes;
    private String container;
    private String sharedName;
    private String encoding;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new FixedLengthRecordReader operation.
   * 
   * @param scope current scope
   * @param recordBytes Number of bytes in the record.
   * @param options carries optional attributes values
   * @return a new instance of FixedLengthRecordReader
   */
  @Endpoint(describeByClass = true)
  public static FixedLengthRecordReader create(Scope scope, Long recordBytes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FixedLengthRecordReaderV2", scope.makeOpName("FixedLengthRecordReader"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("record_bytes", recordBytes);
    if (options != null) {
      for (Options opts : options) {
        if (opts.headerBytes != null) {
          opBuilder.setAttr("header_bytes", opts.headerBytes);
        }
        if (opts.footerBytes != null) {
          opBuilder.setAttr("footer_bytes", opts.footerBytes);
        }
        if (opts.hopBytes != null) {
          opBuilder.setAttr("hop_bytes", opts.hopBytes);
        }
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.encoding != null) {
          opBuilder.setAttr("encoding", opts.encoding);
        }
      }
    }
    return new FixedLengthRecordReader(opBuilder.build());
  }
  
  /**
   * @param headerBytes Number of bytes in the header, defaults to 0.
   */
  public static Options headerBytes(Long headerBytes) {
    return new Options().headerBytes(headerBytes);
  }
  
  /**
   * @param footerBytes Number of bytes in the footer, defaults to 0.
   */
  public static Options footerBytes(Long footerBytes) {
    return new Options().footerBytes(footerBytes);
  }
  
  /**
   * @param hopBytes Number of bytes to hop before each read. Default of 0 means using
   * record_bytes.
   */
  public static Options hopBytes(Long hopBytes) {
    return new Options().hopBytes(hopBytes);
  }
  
  /**
   * @param container If non-empty, this reader is placed in the given container.
   * Otherwise, a default container is used.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName If non-empty, this reader is named in the given bucket
   * with this shared_name. Otherwise, the node name is used instead.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * @param encoding The type of encoding for the file. Currently ZLIB and GZIP
   * are supported. Defaults to none.
   */
  public static Options encoding(String encoding) {
    return new Options().encoding(encoding);
  }
  
  /**
   * The handle to reference the Reader.
   */
  public Output<?> readerHandle() {
    return readerHandle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) readerHandle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "FixedLengthRecordReaderV2";
  
  private Output<?> readerHandle;
  
  private FixedLengthRecordReader(Operation operation) {
    super(operation);
    int outputIdx = 0;
    readerHandle = operation.output(outputIdx++);
  }
}
