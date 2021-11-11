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
 * A Reader that outputs the records from a TensorFlow Records file.
 */
@OpMetadata(
    opType = TfRecordReader.OP_NAME,
    inputsClass = TfRecordReader.Inputs.class
)
@Operator(
    group = "io"
)
public final class TfRecordReader extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TFRecordReaderV2";

  private Output<? extends TType> readerHandle;

  @SuppressWarnings("unchecked")
  public TfRecordReader(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    readerHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TFRecordReaderV2 operation.
   *
   * @param scope current scope
   * @param options carries optional attribute values
   * @return a new instance of TfRecordReader
   */
  @Endpoint(
      describeByClass = true
  )
  public static TfRecordReader create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TfRecordReader");
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.compressionType != null) {
          opBuilder.setAttr("compression_type", opts.compressionType);
        }
      }
    }
    return new TfRecordReader(opBuilder.build());
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
   * Sets the compressionType option.
   *
   * @param compressionType the compressionType option
   * @return this Options instance.
   */
  public static Options compressionType(String compressionType) {
    return new Options().compressionType(compressionType);
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
   * Optional attributes for {@link org.tensorflow.op.io.TfRecordReader}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private String compressionType;

    private Options() {
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
     * Sets the compressionType option.
     *
     * @param compressionType the compressionType option
     * @return this Options instance.
     */
    public Options compressionType(String compressionType) {
      this.compressionType = compressionType;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TfRecordReader.class
  )
  public static class Inputs extends RawOpInputs<TfRecordReader> {
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
     * The compressionType attribute
     */
    public final String compressionType;

    public Inputs(GraphOperation op) {
      super(new TfRecordReader(op), op, Arrays.asList("container", "shared_name", "compression_type"));
      int inputIndex = 0;
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
      compressionType = op.attributes().getAttrString("compression_type");
    }
  }
}
