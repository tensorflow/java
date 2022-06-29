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
import org.tensorflow.types.family.TType;

/**
 * A Reader that outputs fixed-length records from a file.
 */
@OpMetadata(
    opType = FixedLengthRecordReader.OP_NAME,
    inputsClass = FixedLengthRecordReader.Inputs.class
)
@Operator(
    group = "io"
)
public final class FixedLengthRecordReader extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FixedLengthRecordReaderV2";

  private Output<? extends TType> readerHandle;

  @SuppressWarnings("unchecked")
  public FixedLengthRecordReader(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    readerHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FixedLengthRecordReaderV2 operation.
   *
   * @param scope current scope
   * @param recordBytes Number of bytes in the record.
   * @param options carries optional attribute values
   * @return a new instance of FixedLengthRecordReader
   */
  @Endpoint(
      describeByClass = true
  )
  public static FixedLengthRecordReader create(Scope scope, Long recordBytes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FixedLengthRecordReader");
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
   * Sets the headerBytes option.
   *
   * @param headerBytes Number of bytes in the header, defaults to 0.
   * @return this Options instance.
   */
  public static Options headerBytes(Long headerBytes) {
    return new Options().headerBytes(headerBytes);
  }

  /**
   * Sets the footerBytes option.
   *
   * @param footerBytes Number of bytes in the footer, defaults to 0.
   * @return this Options instance.
   */
  public static Options footerBytes(Long footerBytes) {
    return new Options().footerBytes(footerBytes);
  }

  /**
   * Sets the hopBytes option.
   *
   * @param hopBytes Number of bytes to hop before each read. Default of 0 means using
   * record_bytes.
   * @return this Options instance.
   */
  public static Options hopBytes(Long hopBytes) {
    return new Options().hopBytes(hopBytes);
  }

  /**
   * Sets the container option.
   *
   * @param container If non-empty, this reader is placed in the given container.
   * Otherwise, a default container is used.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName If non-empty, this reader is named in the given bucket
   * with this shared_name. Otherwise, the node name is used instead.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Sets the encoding option.
   *
   * @param encoding The type of encoding for the file. Currently ZLIB and GZIP
   * are supported. Defaults to none.
   * @return this Options instance.
   */
  public static Options encoding(String encoding) {
    return new Options().encoding(encoding);
  }

  /**
   * Gets readerHandle.
   * The handle to reference the Reader.
   * @return readerHandle.
   */
  public Output<? extends TType> readerHandle() {
    return readerHandle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) readerHandle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.FixedLengthRecordReader}
   */
  public static class Options {
    private Long headerBytes;

    private Long footerBytes;

    private Long hopBytes;

    private String container;

    private String sharedName;

    private String encoding;

    private Options() {
    }

    /**
     * Sets the headerBytes option.
     *
     * @param headerBytes Number of bytes in the header, defaults to 0.
     * @return this Options instance.
     */
    public Options headerBytes(Long headerBytes) {
      this.headerBytes = headerBytes;
      return this;
    }

    /**
     * Sets the footerBytes option.
     *
     * @param footerBytes Number of bytes in the footer, defaults to 0.
     * @return this Options instance.
     */
    public Options footerBytes(Long footerBytes) {
      this.footerBytes = footerBytes;
      return this;
    }

    /**
     * Sets the hopBytes option.
     *
     * @param hopBytes Number of bytes to hop before each read. Default of 0 means using
     * record_bytes.
     * @return this Options instance.
     */
    public Options hopBytes(Long hopBytes) {
      this.hopBytes = hopBytes;
      return this;
    }

    /**
     * Sets the container option.
     *
     * @param container If non-empty, this reader is placed in the given container.
     * Otherwise, a default container is used.
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName If non-empty, this reader is named in the given bucket
     * with this shared_name. Otherwise, the node name is used instead.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }

    /**
     * Sets the encoding option.
     *
     * @param encoding The type of encoding for the file. Currently ZLIB and GZIP
     * are supported. Defaults to none.
     * @return this Options instance.
     */
    public Options encoding(String encoding) {
      this.encoding = encoding;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = FixedLengthRecordReader.class
  )
  public static class Inputs extends RawOpInputs<FixedLengthRecordReader> {
    /**
     * Number of bytes in the header, defaults to 0.
     */
    public final long headerBytes;

    /**
     * Number of bytes in the record.
     */
    public final long recordBytes;

    /**
     * Number of bytes in the footer, defaults to 0.
     */
    public final long footerBytes;

    /**
     * Number of bytes to hop before each read. Default of 0 means using
     * record_bytes.
     */
    public final long hopBytes;

    /**
     * If non-empty, this reader is placed in the given container.
     * Otherwise, a default container is used.
     */
    public final String container;

    /**
     * If non-empty, this reader is named in the given bucket
     * with this shared_name. Otherwise, the node name is used instead.
     */
    public final String sharedName;

    /**
     * The type of encoding for the file. Currently ZLIB and GZIP
     * are supported. Defaults to none.
     */
    public final String encoding;

    public Inputs(GraphOperation op) {
      super(new FixedLengthRecordReader(op), op, Arrays.asList("header_bytes", "record_bytes", "footer_bytes", "hop_bytes", "container", "shared_name", "encoding"));
      int inputIndex = 0;
      headerBytes = op.attributes().getAttrInt("header_bytes");
      recordBytes = op.attributes().getAttrInt("record_bytes");
      footerBytes = op.attributes().getAttrInt("footer_bytes");
      hopBytes = op.attributes().getAttrInt("hop_bytes");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
      encoding = op.attributes().getAttrString("encoding");
    }
  }
}
